package com.golte.resource.service;

import com.golte.common.data.JsonResult;
import com.golte.resource.service.data.GetProjectInData;
import com.golte.system.service.data.GetResourceProjectInData;

public interface ProjectService {
    /**
     * 查询对应权限城市下的资源项目列表
     * @param inData
     * @return
     * @throws Exception
     */
    JsonResult selectListByCity(GetResourceProjectInData inData) throws Exception;

    /**
     * 查询资源项目列表
     * @param inData
     * @return
     * @throws Exception
     */
    JsonResult selectResourceProjectList(GetProjectInData inData) throws Exception;

    /**
     * 保存资源项目数据
     * @param inData
     * @return
     * @throws Exception
     */
    JsonResult saveResourceProject(GetProjectInData inData) throws Exception;

    /**
     * 删除资源项目数据
     * @param inData
     * @return
     * @throws Exception
     */
    JsonResult deleteResourceProject(GetProjectInData inData) throws Exception;

    /**
     * 更新资源项目数据
     * @param inData
     * @return
     * @throws Exception
     */
    JsonResult updateResourceProject(GetProjectInData inData) throws Exception;

}
