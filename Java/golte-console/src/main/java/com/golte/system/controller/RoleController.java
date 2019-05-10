package com.golte.system.controller;

import com.golte.common.exception.ErrorException;
import com.golte.common.util.BaseController;
import com.golte.common.data.JsonResult;
import com.golte.system.service.RoleService;
import com.golte.system.service.data.GetAuthorizationRoleInData;
import com.golte.system.service.data.GetLoginOutData;
import com.golte.system.service.data.GetRoleInData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/pc/role/")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    /**
     * 分页查询
     * @param request
     * @param inData
     * @return
     */
    @PostMapping("select")
    public JsonResult selectRole(HttpServletRequest request, @RequestBody GetRoleInData inData) {
        try {
            return roleService.selectRole(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 保存角色
     * @param request
     * @param inData
     * @return
     */
    @PostMapping("save")
    public JsonResult saveRole(HttpServletRequest request, @RequestBody GetRoleInData inData) {
        try {
            GetLoginOutData outData = getLoginUser(request);
            inData.setLoginAccount(outData.getLoginName());
            return roleService.saveRole(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 更新角色
     * @param request
     * @param inData
     * @return
     */
    @PostMapping("update")
    public JsonResult updateRole(HttpServletRequest request, @RequestBody GetRoleInData inData) {
        try {
            GetLoginOutData outData = getLoginUser(request);
            inData.setLoginAccount(outData.getLoginName());
            return roleService.updateRole(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 删除角色
     * @param request
     * @param inData
     * @return
     */
    @PostMapping("delete")
    public JsonResult deleteRole(HttpServletRequest request, @RequestBody GetRoleInData inData) {
        try {
            return roleService.deleteRole(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 角色授权
     * @param request
     * @param inData
     * @return
     */
    @PostMapping("authorization")
    public JsonResult authorizationRole(HttpServletRequest request, @RequestBody GetAuthorizationRoleInData inData) {
        try {
            return roleService.authorizationRole(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 查询所有角色
     * @param request
     * @param inData
     * @return
     */
    @PostMapping("all/select")
    public JsonResult selectRoleEnum(HttpServletRequest request, @RequestBody GetRoleInData inData) {
        try {
            return roleService.selectRoleEnum();
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }
}
