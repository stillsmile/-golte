package com.golte.resource.controller;


import com.golte.common.data.GetCityOutData;
import com.golte.common.data.JsonResult;
import com.golte.common.exception.ErrorException;
import com.golte.common.util.BaseController;
import com.golte.common.util.UtilConst;
import com.golte.resource.service.ProjectService;
import com.golte.resource.service.data.GetProjectInData;
import com.golte.system.service.data.GetLoginOutData;
import com.golte.system.service.data.GetResourceProjectInData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 作者 WS
 * @date 创建时间 2019年4月2日 上午11:44:07
 * @description 资源项目接口
 */
@RestController
@RequestMapping(value = "/pc/resource/project/")
public class ProjectController extends BaseController {
    private final Logger log = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectService projectService;

    /**
     * 对应城市权限下的资源项目查询
     * @param indata
     * @return
     */
    @PostMapping(value = "selectByCityIds")
    public JsonResult selectListByCity(HttpServletRequest request, @RequestBody GetResourceProjectInData indata) {
        try {
            GetCityOutData cityInfo = getCityInfo(request.getHeader(UtilConst.LOGIN_TOKEN));
            indata.setCityIds(cityInfo.getCityIds());
            JsonResult jsonResult = projectService.selectListByCity(indata);
            return jsonResult;
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }


    /**
     * 资源项目查询
     * @param indata
     * @return
     */
    @PostMapping(value = "select")
    public JsonResult selectResourceProjectList(HttpServletRequest request, @RequestBody GetProjectInData indata) {
        try {
            GetCityOutData cityInfo = getCityInfo(request.getHeader(UtilConst.LOGIN_TOKEN));
            indata.setCityIds(cityInfo.getCityIds());
            JsonResult jsonResult = projectService.selectResourceProjectList(indata);
            return jsonResult;
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 保存项目数据
     * @param request
     * @param inData
     * @return
     */
    @PostMapping("save")
    public JsonResult saveResourceProject(HttpServletRequest request, @RequestBody GetProjectInData inData) {
        try {
            GetLoginOutData outData = getLoginUser(request);
            inData.setLoginAccount(outData.getLoginName());
            return projectService.saveResourceProject(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 删除资源项目数据
     * @param request
     * @param inData
     * @return
     */
    @PostMapping("delete")
    public JsonResult deleteResourceProject(HttpServletRequest request, @RequestBody GetProjectInData inData) {
        try {
            return projectService.deleteResourceProject(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 更新资源项目数据
     * @param request
     * @param inData
     * @return
     */
    @PostMapping("update")
    public JsonResult updateResourceProject(HttpServletRequest request, @RequestBody GetProjectInData inData) {
        try {
            GetLoginOutData outData = getLoginUser(request);
            inData.setLoginAccount(outData.getLoginName());
            return projectService.updateResourceProject(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

}
