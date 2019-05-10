package com.golte.mapper;

import com.golte.business.service.data.GetMerchantInData;
import com.golte.common.util.BaseMapper;
import com.golte.contract.service.data.GetContractInData;
import com.golte.contract.service.data.GetContractOutData;
import com.golte.mapper.entity.GContract;
import com.golte.system.service.data.GetMainInData;
import com.golte.system.service.data.GetMainTopOutData;

import java.util.List;

public interface GContractMapper extends BaseMapper<GContract> {
    /**
     * @Description: 查询合同列表（分页）
     * @author wyf
     * @date 2019/3/11
     */
    List<GetContractOutData> selectListByCondition(GetContractInData inData);

    /**
     * @Description: 查询对应商家的合同列表
     * @author wyf
     * @date 2019/3/11
     */
    List<GetContractOutData> selectListByMerchantId(GetMerchantInData inData);

   /**
     * @Description: 查询对应合同类型的排行
     * @author ws
     * @date 2019/4/12
     */
    List<GetMainTopOutData> selectListByContractType(GetMainInData inData);

   /**
     * @Description: 查询对应合同详情
     * @author ws
     * @date 2019/4/12
     */
    List<GetMainTopOutData> selectListByContractTypeDetail(GetMainInData inData);
}