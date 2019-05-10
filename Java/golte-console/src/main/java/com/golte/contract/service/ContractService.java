package com.golte.contract.service;


import com.golte.common.data.JsonResult;
import com.golte.contract.service.data.GetContractExportInData;
import com.golte.contract.service.data.GetContractInData;
import com.golte.resource.service.data.GetPointExportInData;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ContractService {
	/**
	 *
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月26日
	 * @description 查询合同列表（分页）
	 * @param @param inData
	 * @param @return
	 * @param @throws Exception
	 * @return JsonResult
	 */
	JsonResult selectContractList(GetContractInData inData) throws Exception;

	/**
	 *
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月26日
	 * @description 查询合同详情
	 * @param @param inData
	 * @param @return
	 * @param @throws Exception
	 * @return JsonResult
	 */
	JsonResult detailContract(GetContractInData inData) throws Exception;

	/**
	 *
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月26日
	 * @description 新增合同
	 * @param @param inData
	 * @param @return
	 * @param @throws Exception
	 * @return JsonResult
	 */
	JsonResult addContract(GetContractInData inData) throws Exception;

	/**
	 *
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月26日
	 * @description 编辑合同
	 * @param @param inData
	 * @param @return
	 * @param @throws Exception
	 * @return JsonResult
	 */
	JsonResult updateContract(GetContractInData inData) throws Exception;

	/**
	 *
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月26日
	 * @description 终止合同
	 * @param @param inData
	 * @param @return
	 * @param @throws Exception
	 * @return JsonResult
	 */
	JsonResult stopContract(GetContractInData inData) throws Exception;

	/**
	 *
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月26日
	 * @description 获取终止审批信息
	 * @param @param inData
	 * @param @return
	 * @param @throws Exception
	 * @return JsonResult
	 */
	JsonResult stopContractDetail(GetContractInData inData) throws Exception;

	/**
	 *
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月26日
	 * @description 审批终止合同
	 * @param @param inData
	 * @param @return
	 * @param @throws Exception
	 * @return JsonResult
	 */
	JsonResult approveStopContract(GetContractInData inData) throws Exception;

	/**
	 *
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月26日
	 * @description 变更合同
	 * @param @param inData
	 * @param @return
	 * @param @throws Exception
	 * @return JsonResult
	 */
	JsonResult changeContract(GetContractInData inData) throws Exception;

	/**
	 *
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月26日
	 * @description 获取变更审批信息
	 * @param @param inData
	 * @param @return
	 * @param @throws Exception
	 * @return JsonResult
	 */
	JsonResult changeContractDetail(GetContractInData inData) throws Exception;

	/**
	 *
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月26日
	 * @description 审批变更合同
	 * @param @param inData
	 * @param @return
	 * @param @throws Exception
	 * @return JsonResult
	 */
	JsonResult approveChangeContract(GetContractInData inData) throws Exception;

	/**
	 *
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月26日
	 * @description 删除合同
	 * @param @param inData
	 * @param @return
	 * @param @throws Exception
	 * @return JsonResult
	 */
	JsonResult deleteContract(GetContractInData inData) throws Exception;

	/**
	 *
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月26日
	 * @description 获取城市列表
	 * @param @param inData
	 * @param @return
	 * @param @throws Exception
	 * @return JsonResult
	 */
	JsonResult getCityList(GetContractInData inData) throws Exception;

	/**
	 *
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月26日
	 * @description 获取城市列表
	 * @param @param inData
	 * @param @return
	 * @param @throws Exception
	 * @return JsonResult
	 */
	JsonResult getCityListByCenterCity(GetContractInData inData) throws Exception;

	/**
	 *
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月26日
	 * @description 获取中心城市列表
	 * @param @param inData
	 * @param @return
	 * @param @throws Exception
	 * @return JsonResult
	 */
	JsonResult getCenterCityList(GetContractInData inData) throws Exception;

	/**
	 *
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月26日
	 * @description 获取商家列表
	 * @param @param inData
	 * @param @return
	 * @param @throws Exception
	 * @return JsonResult
	 */
	JsonResult getMerchantList(GetContractInData inData) throws Exception;

	/**
	 *
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月26日
	 * @description 获取合同列表
	 * @param @param inData
	 * @param @return
	 * @param @throws Exception
	 * @return JsonResult
	 */
	JsonResult getContractList(GetContractInData inData) throws Exception;

	/**
	 *
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月26日
	 * @description 获取商家列表
	 * @param @param inData
	 * @param @return
	 * @param @throws Exception
	 * @return JsonResult
	 */
	JsonResult getProjectList(GetContractInData inData) throws Exception;

	/**
	 *
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月26日
	 * @description 获取一级点位列表
	 * @param @param inData
	 * @param @return
	 * @param @throws Exception
	 * @return JsonResult
	 */
	JsonResult getPointList(GetContractInData inData) throws Exception;

	/**
	 *
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月26日
	 * @description 获取二级点位列表
	 * @param @param inData
	 * @param @return
	 * @param @throws Exception
	 * @return JsonResult
	 */
	JsonResult getPointTwoList(GetContractInData inData) throws Exception;

	/**
	 *
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月26日
	 * @description 获取三级点位列表
	 * @param @param inData
	 * @param @return
	 * @param @throws Exception
	 * @return JsonResult
	 */
	JsonResult getPointThreeList(GetContractInData inData) throws Exception;

	/**
	 *
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月26日
	 * @description 获取子点位列表
	 * @param @param inData
	 * @param @return
	 * @param @throws Exception
	 * @return JsonResult
	 */
	JsonResult getPointChildList(Long pointId) throws Exception;

	/**
	 *
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月26日
	 * @description 获取资源列表
	 * @param @param inData
	 * @param @return
	 * @param @throws Exception
	 * @return JsonResult
	 */
	JsonResult getResourceList(GetContractInData inData) throws Exception;

	/**
	 *
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月26日
	 * @description 导出合同报表
	 * @param @param inData
	 * @param @return
	 * @param @throws Exception
	 * @return JsonResult
	 */
	void exportOut(HttpServletResponse response, GetContractInData inData) throws Exception;

	/**
	 *
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月26日
	 * @description 批量导入合同
	 * @param @param inData
	 * @param @return
	 * @param @throws Exception
	 * @return JsonResult
	 */
	JsonResult exportIn(List<GetContractExportInData> inData, String loginName);

	/**
	 *
	 * @author 作者 wyf
	 * @date 创建时间 2019年3月26日
	 * @description 获取未回款金额
	 * @param @param inData
	 * @param @return
	 * @param @throws Exception
	 * @return JsonResult
	 */
	JsonResult getAmount(GetContractInData inData) throws Exception;

}
