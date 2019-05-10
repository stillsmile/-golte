package com.golte.payBack.service;


import com.golte.common.data.JsonResult;
import com.golte.payBack.service.data.GetPayBackApprovalInData;
import com.golte.payBack.service.data.GetPayBackRecordInData;
import com.golte.payBack.service.data.GetPayBackRecordListInData;

public interface PayBackApprovalService {

	/**
	 *
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月28日
	 * @description 查询延期详情
	 * @param @param inData
	 * @param @return
	 * @param @throws Exception
	 * @return JsonResult
	 */
	JsonResult detailPayBackApproval(GetPayBackApprovalInData inData) throws Exception;

	/**
	 *
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月28日
	 * @description 申请延期
	 * @param @param inData
	 * @param @return
	 * @param @throws Exception
	 * @return JsonResult
	 */
	JsonResult delayPayBackApproval(GetPayBackApprovalInData inData) throws Exception;

	/**
	 *
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月28日
	 * @description 延期审批
	 * @param @param inData
	 * @param @return
	 * @param @throws Exception
	 * @return JsonResult
	 */
	JsonResult approvePayBackApproval(GetPayBackApprovalInData inData) throws Exception;

}
