package com.golte.log.service;


import com.golte.common.data.JsonResult;
import com.golte.log.service.data.GetLogInData;
import com.golte.payBack.service.data.GetPayBackPlanInData;
import com.golte.payBack.service.data.GetPayBackPlanListInData;

public interface LogService {
	/**
	 *
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月26日
	 * @description 查询日志列表（分页）
	 * @param @param inData
	 * @param @return
	 * @param @throws Exception
	 * @return JsonResult
	 */
	JsonResult selectLogList(GetLogInData inData) throws Exception;

	/**
	 *
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月26日
	 * @description 获取变更日志详情
	 * @param @param inData
	 * @param @return
	 * @param @throws Exception
	 * @return JsonResult
	 */
	JsonResult getLogDetail(GetLogInData inData) throws Exception;

}
