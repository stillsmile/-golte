package com.golte.system.service;

import com.golte.common.data.JsonResult;
import com.golte.system.service.data.GetAuthorizationRoleInData;
import com.golte.system.service.data.GetRoleInData;

public interface RoleService {
    /**
     * 分页查询
     * @param inData
     * @return
     * @throws Exception
     */
    JsonResult selectRole(GetRoleInData inData) throws Exception;

    /**
     * 保存角色
     * @param inData
     * @return
     * @throws Exception
     */
    JsonResult saveRole(GetRoleInData inData) throws Exception;

    /**
     * 更新角色
     * @param inData
     * @return
     * @throws Exception
     */
    JsonResult updateRole(GetRoleInData inData) throws Exception;

    /**
     * 删除角色
     * @param inData
     * @return
     * @throws Exception
     */
    JsonResult deleteRole(GetRoleInData inData) throws Exception;

    /**
     * 角色授权
     * @param inData
     * @return
     * @throws Exception
     */
    JsonResult authorizationRole(GetAuthorizationRoleInData inData) throws Exception;

    /**
     * 查询所有角色
     * @return
     * @throws Exception
     */
    JsonResult selectRoleEnum() throws Exception;
}
