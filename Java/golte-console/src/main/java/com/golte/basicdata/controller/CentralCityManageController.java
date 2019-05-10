package com.golte.basicdata.controller;


import com.golte.basicdata.service.CentralCityManageService;
import com.golte.basicdata.service.data.GetCentralCityInData;
import com.golte.common.data.GetCityOutData;
import com.golte.common.data.JsonResult;
import com.golte.common.exception.ErrorException;
import com.golte.common.util.BaseController;
import com.golte.common.util.UtilConst;
import com.golte.system.service.data.GetLoginOutData;
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
 * @date 创建时间 2019年3月22日 上午11:44:07
 * @description 中心城市管理接口
 */
@RestController
@RequestMapping(value = "/pc/centralcity/")
public class CentralCityManageController extends BaseController {
    private final Logger log = LoggerFactory.getLogger(CentralCityManageController.class);

    @Autowired
    private CentralCityManageService centralCityManageService;

    /**
     * 根据角色类型获取对应的中心城市负责人列表
     * @param indata
     * @return
     */
    @PostMapping(value = "centraleader")
    public JsonResult getCentraLeaderList(HttpServletRequest request, @RequestBody GetCentralCityInData indata) {
        try {
            JsonResult jsonResult = centralCityManageService.getCentraLeaderList(indata);
            return jsonResult;
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 获取对应的权限下的中心城市列表
     * @param indata
     * @return
     */
    @PostMapping(value = "select")
    public JsonResult selectCentralCity(HttpServletRequest request, @RequestBody GetCentralCityInData indata) {
        try {
            GetCityOutData cityInfo = getCentralCityInfo(request);
            indata.setCityIds(cityInfo.getCentralCityIds());//设置中心城市的ids集合
            JsonResult jsonResult = centralCityManageService.selectCentralCity(indata);
            return jsonResult;
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 获取对应的权限下的中心城市列表
     * @param indata
     * @return
     */
    @PostMapping(value = "selectCentralCityByCitys")
    public JsonResult selectCentralCityByCitys(HttpServletRequest request, @RequestBody GetCentralCityInData indata) {
        try {
            GetCityOutData cityInfo = getCityInfo(request.getHeader(UtilConst.LOGIN_TOKEN));
            //设置(城市)的ids集合
            indata.setCityIds(cityInfo.getCityIds());
            JsonResult jsonResult = centralCityManageService.selectCentralCityByCitys(indata);
            return jsonResult;
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 保存中心城市数据
     * @param request
     * @param inData
     * @return
     */
    @PostMapping("save")
    public JsonResult saveCentralCity(HttpServletRequest request, @RequestBody GetCentralCityInData inData) {
        try {
            GetLoginOutData outData = getLoginUser(request);
            inData.setLoginAccount(outData.getLoginName());
            return centralCityManageService.saveCentralCity(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 删除中心城市数据
     * @param request
     * @param inData
     * @return
     */
    @PostMapping("delete")
    public JsonResult deleteCentralCity(HttpServletRequest request, @RequestBody GetCentralCityInData inData) {
        try {
            return centralCityManageService.deleteCentralCity(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 更新中心城市数据
     * @param request
     * @param inData
     * @return
     */
    @PostMapping("update")
    public JsonResult updateCentralCity(HttpServletRequest request, @RequestBody GetCentralCityInData inData) {
        try {
            GetLoginOutData outData = getLoginUser(request);
            inData.setLoginAccount(outData.getLoginName());
            return centralCityManageService.updateCentralCity(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }
}
