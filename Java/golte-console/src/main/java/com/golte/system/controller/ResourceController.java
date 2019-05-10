package com.golte.system.controller;

import com.golte.common.exception.ErrorException;
import com.golte.common.util.BaseController;
import com.golte.common.data.JsonResult;
import com.golte.system.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/pc/resource/")
public class ResourceController extends BaseController {

    @Autowired
    private ResourceService resourceService;

    /**
     * 根据角色查询权限（返回所有权限）
     * @param request
     * @param roleId
     * @return
     */
    @PostMapping("role/select")
    public JsonResult selectByRoleResource(HttpServletRequest request, @RequestParam(name = "roleId") String roleId) {
        try {
            return resourceService.selectByRoleResource(roleId);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }
}
