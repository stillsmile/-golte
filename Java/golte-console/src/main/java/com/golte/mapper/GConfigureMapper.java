package com.golte.mapper;

import com.golte.business.service.data.GetMerchantOutData;
import com.golte.common.util.BaseMapper;
import com.golte.mapper.entity.GConfigure;

import java.util.List;

public interface GConfigureMapper extends BaseMapper<GConfigure> {


    /**
     * 配置信息查询
     * @return
     */
    List<GetMerchantOutData> selectConfigInfo();


}