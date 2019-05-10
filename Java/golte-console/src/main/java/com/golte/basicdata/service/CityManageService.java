package com.golte.basicdata.service;

import com.golte.basicdata.service.data.GetCityInData;
import com.golte.common.data.JsonResult;

public interface CityManageService {
    /**
     * 城市信息列表
     * @param inData
     * @return
     * @throws Exception
     */
    JsonResult selectCitys(GetCityInData inData) throws Exception;

    /**
     * 城市管理列表
     * @param inData
     * @return
     * @throws Exception
     */
    JsonResult selectCityList(GetCityInData inData) throws Exception;

    /**
     * 保存城市管理数据
     * @param inData
     * @return
     * @throws Exception
     */
    JsonResult saveCity(GetCityInData inData) throws Exception;

    /**
     * 删除城市管理数据
     * @param inData
     * @return
     * @throws Exception
     */
    JsonResult deleteCity(GetCityInData inData) throws Exception;

    /**
     * 更新城市管理列表信息
     * @param inData
     * @return
     * @throws Exception
     */
    JsonResult updateCity(GetCityInData inData) throws Exception;
}
