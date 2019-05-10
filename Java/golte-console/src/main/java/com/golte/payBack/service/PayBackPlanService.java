package com.golte.payBack.service;


import com.golte.common.data.JsonResult;
import com.golte.contract.service.data.GetContractInData;
import com.golte.payBack.service.data.GetPayBackPlanInData;
import com.golte.payBack.service.data.GetPayBackPlanListInData;

public interface PayBackPlanService {
	/**
	 *
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月26日
	 * @description 查询回款计划列表（分页）
	 * @param @param inData
	 * @param @return
	 * @param @throws Exception
	 * @return JsonResult
	 */
	JsonResult selectPayBackList(GetPayBackPlanInData inData) throws Exception;

	/**
	 *
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月28日
	 * @description 查询回款计划详情
	 * @param @param inData
	 * @param @return
	 * @param @throws Exception
	 * @return JsonResult
	 */
	JsonResult detailPayBack(GetPayBackPlanInData inData) throws Exception;

	/**
	 *
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月28日
	 * @description 编辑回款计划
	 * @param @param inData
	 * @param @return
	 * @param @throws Exception
	 * @return JsonResult
	 */
	JsonResult updatePayBack(GetPayBackPlanListInData inData) throws Exception;

}
