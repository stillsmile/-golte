package com.golte.system.controller;

import com.golte.common.data.JsonResult;
import com.golte.common.exception.ErrorException;
import com.golte.common.util.BaseController;
import com.golte.system.service.ConfigService;
import com.golte.system.service.data.GetConfigInData;
import com.golte.system.service.data.GetMainInData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/pc/config/")
public class ConfigController extends BaseController {

    @Autowired
    private ConfigService configService;

    /**
     * 指标信息查询
     * @param request
     * @param inData
     * @return
     */
    @PostMapping("select")
    public JsonResult selectConfigInfo(HttpServletRequest request, @RequestBody GetMainInData inData) {
        try {
            return configService.selectConfigInfoList(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 保存角色信息
     * @param request
     * @param inData
     * @return
     */
    @PostMapping("save")
    public JsonResult saveRoleConfig(HttpServletRequest request, @RequestBody GetConfigInData inData) {
        try {
            return configService.saveRoleConfig(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 更新角色百分比
     * @param request
     * @param inData
     * @return
     */
    @PostMapping("update")
    public JsonResult updateRoleConfig(HttpServletRequest request, @RequestBody GetConfigInData inData) {
        try {
            return configService.updateRoleConfig(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 删除激励角色
     * @param request
     * @param inData
     * @return
     */
    @PostMapping("delete")
    public JsonResult deleteRoleConfig(HttpServletRequest request, @RequestBody GetConfigInData inData) {
        try {
            return configService.deleteRoleConfig(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

}
