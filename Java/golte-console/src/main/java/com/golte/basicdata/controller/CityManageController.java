package com.golte.basicdata.controller;


import com.golte.basicdata.service.CityManageService;
import com.golte.basicdata.service.data.GetCityInData;
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
 * @description 会员管理接口
 */
@RestController
@RequestMapping(value = "/pc/citymanage/")
public class CityManageController extends BaseController {
    private final Logger log = LoggerFactory.getLogger(CityManageController.class);

    @Autowired
    private CityManageService cityManageService;

    /**
     * 城市信息查询
     * @param indata
     * @return
     */
    @PostMapping(value = "selectCitys")
    public JsonResult selectCitys(HttpServletRequest request, @RequestBody GetCityInData indata) {
        try {
            GetCityOutData cityInfo = getCityInfo(request.getHeader(UtilConst.LOGIN_TOKEN));
            indata.setCityIds(cityInfo.getCityIds());
            JsonResult jsonResult = cityManageService.selectCitys(indata);
            return jsonResult;
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 城市管理区域列表查询
     * @param indata
     * @return
     */
    @PostMapping(value = "select")
    public JsonResult selectCityList(HttpServletRequest request, @RequestBody GetCityInData indata) {
        try {
            GetCityOutData cityInfo = getCityInfo(request.getHeader(UtilConst.LOGIN_TOKEN));
            indata.setCityIds(cityInfo.getCityIds());
            JsonResult jsonResult = cityManageService.selectCityList(indata);
            return jsonResult;
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 保存城市管理数据
     * @param request
     * @param inData
     * @return
     */
    @PostMapping("save")
    public JsonResult saveCity(HttpServletRequest request, @RequestBody GetCityInData inData) {
        try {
            GetLoginOutData outData = getLoginUser(request);
            inData.setLoginAccount(outData.getLoginName());
            return cityManageService.saveCity(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 删除城市管理数据
     * @param request
     * @param inData
     * @return
     */
    @PostMapping("delete")
    public JsonResult deleteCity(HttpServletRequest request, @RequestBody GetCityInData inData) {
        try {
            return cityManageService.deleteCity(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 更新城市管理数据
     * @param request
     * @param inData
     * @return
     */
    @PostMapping("update")
    public JsonResult updateCity(HttpServletRequest request, @RequestBody GetCityInData inData) {
        try {
            GetLoginOutData outData = getLoginUser(request);
            inData.setLoginAccount(outData.getLoginName());
            return cityManageService.updateCity(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }



}
