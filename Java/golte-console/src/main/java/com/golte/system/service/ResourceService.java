package com.golte.system.service;

import com.golte.common.data.JsonResult;

public interface ResourceService {
    /**
     * 根据角色查询权限（返回所有权限）
     * @param roleId
     * @return
     * @throws Exception
     */
    JsonResult selectByRoleResource(String roleId) throws Exception;
}
