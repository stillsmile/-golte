package com.golte.mapper;

import com.golte.business.service.data.GetMerchantInData;
import com.golte.business.service.data.GetMerchantOutData;
import com.golte.common.util.BaseMapper;
import com.golte.mapper.entity.GMerchant;

import java.util.List;

public interface GMerchantMapper extends BaseMapper<GMerchant> {
    /**
     * 中心城市数据查询
     * @param inData
     * @return
     */
    List<GetMerchantOutData> selectListByCondition(GetMerchantInData inData);



}