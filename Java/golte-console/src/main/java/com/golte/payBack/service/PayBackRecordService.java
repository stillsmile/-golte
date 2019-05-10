package com.golte.payBack.service;


import com.golte.common.data.JsonResult;
import com.golte.payBack.service.data.GetPayBackPlanInData;
import com.golte.payBack.service.data.GetPayBackPlanListInData;
import com.golte.payBack.service.data.GetPayBackRecordInData;
import com.golte.payBack.service.data.GetPayBackRecordListInData;

public interface PayBackRecordService {

	/**
	 *
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月28日
	 * @description 查询回款记录详情
	 * @param @param inData
	 * @param @return
	 * @param @throws Exception
	 * @return JsonResult
	 */
	JsonResult detailPayBackRecord(GetPayBackRecordInData inData) throws Exception;

	/**
	 *
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月28日
	 * @description 查询合同回款记录详情
	 * @param @param inData
	 * @param @return
	 * @param @throws Exception
	 * @return JsonResult
	 */
	JsonResult detailPayBackRecordList(GetPayBackRecordInData inData) throws Exception;

	/**
	 *
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月28日
	 * @description 新增回款记录
	 * @param @param inData
	 * @param @return
	 * @param @throws Exception
	 * @return JsonResult
	 */
	JsonResult updatePayBackRecord(GetPayBackRecordListInData inData) throws Exception;

	/**
	 *
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月28日
	 * @description 获取收款人列表
	 * @param @param inData
	 * @param @return
	 * @param @throws Exception
	 * @return JsonResult
	 */
	JsonResult getEmpList() throws Exception;

}
