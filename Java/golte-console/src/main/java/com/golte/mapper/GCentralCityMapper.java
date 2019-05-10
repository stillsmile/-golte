package com.golte.mapper;

import com.golte.basicdata.service.data.GetCentralCityInData;
import com.golte.basicdata.service.data.GetCentralCityOutData;
import com.golte.common.util.BaseMapper;
import com.golte.mapper.entity.GCentralCity;

import java.util.List;

public interface GCentralCityMapper extends BaseMapper<GCentralCity> {
    /**
     * 中心城市数据查询
     * @param inData
     * @return
     */
    List<GetCentralCityOutData> selectListByCondition(GetCentralCityInData inData);

    /**
     * 中心城市负责人列表查询
     * @param inData
     * @return
     */
    List<GetCentralCityOutData> selectCentralLeaderListByCondition(GetCentralCityInData inData);


    /**
     * 根据对应的的城市查询中心城市
     * @param inData
     * @return
     */
    List<GetCentralCityOutData> selectCentralCityByCitys(GetCentralCityInData inData);


}