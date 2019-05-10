package com.golte.mapper;

import com.golte.business.service.data.GetAreaInData;
import com.golte.common.util.BaseMapper;
import com.golte.mapper.entity.GArea;

import java.util.List;

public interface GAreaMapper extends BaseMapper<GArea> {
    /**
     * 区域列表
     * @param inData
     * @return
     * @throws Exception
     */
    List<GArea> selectAreaList(GetAreaInData inData) throws Exception;

}