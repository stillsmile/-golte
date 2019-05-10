package com.golte.common.exception;

import com.golte.common.data.JsonResult;
import com.golte.common.util.SpringUtil;
import com.golte.common.util.UtilConst;
import com.golte.common.util.UtilMessage;
import com.golte.mapper.GExceptionMapper;
import com.golte.mapper.entity.GException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author 作者 GXS
 * @date 创建时间 2018年3月21日 下午5:53:37
 * @description 统一异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = RuntimeException.class)
	@ResponseBody
	public JsonResult handlerError(HttpServletRequest request, Exception e) throws Exception {
		GException loggerException = new GException();
		loggerException.setCreateUser("admin");
		loggerException.setCreateTime(new Date());
		loggerException.setExceptionContent("异常信息：" + e.getCause());
		loggerException.setExceptionUrl(request.getRequestURI());
		GExceptionMapper loggerExceptionMapper = SpringUtil.getBean(GExceptionMapper.class);
		loggerExceptionMapper.insert(loggerException);
		return JsonResult.error();
	}

	@ExceptionHandler(value = ServiceException.class)
	@ResponseBody
	public JsonResult handlerService(Exception e) throws Exception {
		if (e.getMessage().equals(UtilConst.ERROR_HTTP_URL)) {
			return JsonResult.fail(UtilConst.CODE_401, UtilMessage.HTTP_ERROR);
		}
		if (e.getMessage().equals(UtilConst.ERROR_TOKEN_STATUS)) {
			return JsonResult.fail(UtilConst.CODE_404, UtilMessage.LOGIN_STATUS_ERROR);
		}
		if (e.getMessage().equals(UtilConst.ERROR_RESOURCE_STATUS)) {
			return JsonResult.fail(UtilConst.CODE_405, UtilMessage.RESOURCE_ERROR);
		}
		return JsonResult.fail(UtilConst.CODE_403, e.getMessage());
	}

}
