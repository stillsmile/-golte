package com.golte.system.service;

import com.golte.common.data.JsonResult;
import com.golte.system.service.data.GetConfigInData;
import com.golte.system.service.data.GetMainInData;

public interface ConfigService {
    /**
     * p配置信息查询
     * @param inData
     * @return
     * @throws Exception
     */
    JsonResult selectConfigInfoList(GetMainInData inData) throws Exception;


    /**
     * 保存角色配置信息
     * @param inData
     * @return
     * @throws Exception
     */
    JsonResult saveRoleConfig(GetConfigInData inData) throws Exception;

    /**
     * 更新角色配置百分比
     * @param inData
     * @return
     * @throws Exception
     */
    JsonResult updateRoleConfig(GetConfigInData inData) throws Exception;

    /**
     * 删除激励角色
     * @param inData
     * @throws Exception
     */
    JsonResult deleteRoleConfig(GetConfigInData inData) throws Exception;
}
