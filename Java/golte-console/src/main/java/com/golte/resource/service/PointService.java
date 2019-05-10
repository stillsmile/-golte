package com.golte.resource.service;

import com.golte.common.data.JsonResult;
import com.golte.resource.service.data.GetPointInData;
import com.golte.system.service.data.GetResourcePointInData;

public interface PointService {
    /**
     * 查询资源点位树列表
     * @return
     * @throws Exception
     */
    JsonResult selectPointTreeList(GetPointInData inData) throws Exception;

    /**
     * 查询资源点位table列表
     * @return
     * @throws Exception
     */
    JsonResult selectPointTableList(GetPointInData inData) throws Exception;

    /**
     * 根据三级id查询一二三级id详情
     * @return
     * @throws Exception
     */
    JsonResult selectPointInfoByid(GetPointInData inData) throws Exception;

    /**
     * 根据父Id查询点位信息
     * @return
     * @throws Exception
     */
    JsonResult selectPointByParentId(GetResourcePointInData inData) throws Exception;

    /**
     * 保存三级资源点位信息
     * @return
     * @throws Exception
     */
    JsonResult saveResourcePoint(GetPointInData inData) throws Exception;

    /**
     * 更新资源点位数据
     * @param inData
     * @return
     * @throws Exception
     */
    JsonResult updateResourcePointDetail(GetPointInData inData) throws Exception;

    /**
     * 删除资源项目数据
     * @param inData
     * @return
     * @throws Exception
     */
    JsonResult deleteResourcePointInfo(GetPointInData inData) throws Exception;


}
