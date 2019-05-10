package com.golte.basicdata.service;

import com.golte.basicdata.service.data.GetCentralCityInData;
import com.golte.common.data.JsonResult;

public interface CentralCityManageService {
    /**
     * 根据角色类型获取对应的中心城市负责人列表
     * @param inData
     * @return
     * @throws Exception
     */
    JsonResult getCentraLeaderList(GetCentralCityInData inData) throws Exception;

    /**
     * 获取对应的权限下的中心城市列表
     * @param inData
     * @return
     * @throws Exception
     */
    JsonResult selectCentralCity(GetCentralCityInData inData) throws Exception;

    /**
     * 根据对应的的城市查询中心城市
     * @param inData
     * @return
     * @throws Exception
     */
    JsonResult selectCentralCityByCitys(GetCentralCityInData inData) throws Exception;

    /**
     * 保存中心城市数据
     * @param inData
     * @return
     * @throws Exception
     */
    JsonResult saveCentralCity(GetCentralCityInData inData) throws Exception;

    /**
     * 删除中心城市数据
     * @param inData
     * @return
     * @throws Exception
     */
    JsonResult deleteCentralCity(GetCentralCityInData inData) throws Exception;

    /**
     * 更新中心城市
     * @param inData
     * @return
     * @throws Exception
     */
    JsonResult updateCentralCity(GetCentralCityInData inData) throws Exception;
}
