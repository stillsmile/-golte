package com.golte.system.controller;

import javax.servlet.http.HttpServletRequest;

import com.golte.common.util.BaseController;
import com.golte.system.service.data.GetLoginOutData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.golte.common.exception.ErrorException;
import com.golte.common.data.JsonResult;
import com.golte.common.util.CacheUtils;
import com.golte.common.util.UtilConst;
import com.golte.common.util.UtilMessage;
import com.golte.system.service.LoginService;
import com.golte.system.service.data.GetLoginInData;

@RestController
@RequestMapping(value = "/pc/")
public class LoginController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private LoginService loginService;

	/**
	 * 
	 * @author 作者 GXS
	 * @date 创建时间 2019年2月22日 上午10:56:50
	 * @Description TODO
	 * @param @param request
	 * @param @param object
	 * @param @return 登录
	 * @return JsonResult<GetLoginOutData>
	 */
	@PostMapping("login")
	public JsonResult login(HttpServletRequest request, @RequestBody GetLoginInData indata) {
		try {
			return loginService.checkUserInfo(indata);
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}

	/**
	 * 
	 * @author 作者 GXS
	 * @date 创建时间 2019年2月22日 上午11:08:39
	 * @Description 登出
	 * @param @param request
	 * @param @param object
	 * @param @return
	 * @return JsonResult<Boolean>
	 */
	@PostMapping("logout")
	public JsonResult logout(HttpServletRequest request) {
		try {
			String token = request.getHeader(UtilConst.LOGIN_TOKEN);
			CacheUtils.remove(token);
			return JsonResult.success(null, UtilMessage.LOGIN_OUT_SUCCESS);
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}

	/**
	 * 首页菜单
	 * @param request
	 * @return
	 */
	@PostMapping("getResByUser")
	public JsonResult getResByUser(HttpServletRequest request, @RequestBody GetLoginInData indata) {
		try {
			GetLoginOutData outData = getLoginUser(request);
			return loginService.getResByUser(outData.getEmpId());
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}

	/**
	 * 修改密码
	 * @param request
	 * @param indata
	 * @return
	 */
	@PostMapping("updatePwd")
	public JsonResult updatePwd(HttpServletRequest request, @RequestBody GetLoginInData indata) {
		try {
			GetLoginOutData outData = getLoginUser(request);
			indata.setLoginAccount(outData.getLoginAccount());
			return loginService.updatePwd(indata);
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}
}
