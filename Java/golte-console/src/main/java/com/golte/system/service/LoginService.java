package com.golte.system.service;

import com.golte.common.data.JsonResult;
import com.golte.system.service.data.GetLoginInData;

public interface LoginService {
	/**
	 * 
	 * @author 作者 GXS
	 * @date 创建时间 2019年2月22日 上午11:08:12
	 * @Description 登录检查接口
	 * @param @param indata
	 * @param @return
	 * @param @throws Exception
	 * @return JsonResult
	 */
	JsonResult checkUserInfo(GetLoginInData indata) throws Exception;

	/**
	 * 首页菜单
	 * @param empId
	 * @return
	 * @throws Exception
	 */
	JsonResult getResByUser(Long empId) throws Exception;

	/**
	 * 修改密码
	 * @param indata
	 * @return
	 * @throws Exception
	 */
	JsonResult updatePwd(GetLoginInData indata) throws Exception;
}
