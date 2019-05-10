package com.golte.common.interceptor;

import com.golte.common.exception.ServiceException;
import com.golte.common.util.CacheUtils;
import com.golte.common.util.Util;
import com.golte.common.util.UtilConst;
import com.golte.system.service.data.GetLoginOutData;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * 
 * @author 作者 GXS
 * @date 创建时间 2017年11月21日 下午2:15:25
 * @description 权限拦截
 */
public class SecurityInterceptor implements HandlerInterceptor {

	/**
	 * 在调用controller具体方法前拦截
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		if (request.getServletPath().contains(UtilConst.URL_ERROR)) {
			throw new ServiceException(UtilConst.ERROR_HTTP_URL);
		}
		String method = request.getMethod();
		if (UtilConst.REQUEST_METHOD_OPTIONS.equalsIgnoreCase(method)) {
			return true;
		}

		String token = request.getHeader(UtilConst.LOGIN_TOKEN);
		GetLoginOutData outData   =(GetLoginOutData) CacheUtils.get(token);
		if (Util.isEmpty(outData)) {
			CacheUtils.remove(token);
			throw new ServiceException(UtilConst.ERROR_TOKEN_STATUS);
		}
		return true;
	}

	/**
	 * 完成页面的render后调用
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) throws Exception {
	}

	/**
	 * 在调用controller具体方法后拦截
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView) throws Exception {
	}
}
