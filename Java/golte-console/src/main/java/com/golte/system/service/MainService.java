package com.golte.system.service;

import com.golte.common.data.JsonResult;
import com.golte.system.service.data.GetMainInData;

public interface MainService {
    /**
     * 指标信息查询
     * @param inData
     * @return
     * @throws Exception
     */
    JsonResult selectYearTargetList(GetMainInData inData) throws Exception;

    /**
     * 资源类型Top6
     * @return
     * @throws Exception
     */
    JsonResult selectResourceTypeList(GetMainInData inData) throws Exception;

    /**
     * 资源类型Top6详情
     * @return
     * @throws Exception
     */
    JsonResult selectResourceTypeListDetail(GetMainInData inData) throws Exception;

    /**
     * 项目成交金额Top10
     * @return
     * @throws Exception
     */
    JsonResult selectPaybackRecordList(GetMainInData inData) throws Exception;

    /**
     * 项目成交金额详情
     * @return
     * @throws Exception
     */
    JsonResult selectPaybackRecordListDetail(GetMainInData inData) throws Exception;

    /**
     * 商家金额Top10
     * @return
     * @throws Exception
     */
    JsonResult selectMerchantTop10(GetMainInData inData) throws Exception;

    /**
     * 商家金额详情
     * @return
     * @throws Exception
     */
    JsonResult selectMerchantTop10Detail(GetMainInData inData) throws Exception;

    /**
     * 不同类型合同的Top10
     * @return
     * @throws Exception
     */
    JsonResult selectListByContractType(GetMainInData inData) throws Exception;

    /**
     * 不同类型合同的详情
     * @return
     * @throws Exception
     */
    JsonResult selectListByContractTypeDetail(GetMainInData inData) throws Exception;

}
