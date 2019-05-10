package com.golte.system.controller;

import com.golte.common.data.GetCityOutData;
import com.golte.common.data.JsonResult;
import com.golte.common.exception.ErrorException;
import com.golte.common.util.BaseController;
import com.golte.common.util.UtilConst;
import com.golte.system.service.MainService;
import com.golte.system.service.data.GetMainInData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/pc/main/")
public class MainController extends BaseController {

    @Autowired
    private MainService mainService;

    /**
     * 指标信息查询
     * @param request
     * @param inData
     * @return
     */
    @PostMapping("select")
    public JsonResult selectYearTargetList(HttpServletRequest request, @RequestBody GetMainInData inData) {
        try {
            return mainService.selectYearTargetList(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 资源类型Top6
     * @param request
     * @return
     */
    @PostMapping("selectTop6")
    public JsonResult selectResourceTypeList(HttpServletRequest request, @RequestBody GetMainInData inData) {
        try {
            GetCityOutData cityInfo = getCityInfo(request.getHeader(UtilConst.LOGIN_TOKEN));
            //设置(城市)的ids集合
            inData.setCityIds(cityInfo.getCityIds());
            return mainService.selectResourceTypeList(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 资源类型Top6详情
     * @param request
     * @return
     */
    @PostMapping("selectTop6Detail")
    public JsonResult selectResourceTypeListDetail(HttpServletRequest request, @RequestBody GetMainInData inData) {
        try {
            return mainService.selectResourceTypeListDetail(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 项目成交Top10
     * @param request
     * @return
     */
    @PostMapping("selectTop10")
    public JsonResult selectPaybackRecordList(HttpServletRequest request, @RequestBody GetMainInData inData) {
        try {
            return mainService.selectPaybackRecordList(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 项目成交Top10详情
     * @param request
     * @return
     */
    @PostMapping("selectTop10Detail")
    public JsonResult selectPaybackRecordListDetail(HttpServletRequest request, @RequestBody GetMainInData inData) {
        try {
            return mainService.selectPaybackRecordListDetail(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 商家成交Top10
     * @param request
     * @return
     */
    @PostMapping("selectMerchantTop10")
    public JsonResult selectMerchantTop10(HttpServletRequest request, @RequestBody GetMainInData inData) {
        try {
            return mainService.selectMerchantTop10(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 商家成交Top10
     * @param request
     * @return
     */
    @PostMapping("selectMerchantTop10Detail")
    public JsonResult selectMerchantTop10Detail(HttpServletRequest request, @RequestBody GetMainInData inData) {
        try {
            return mainService.selectMerchantTop10Detail(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 不同合同类型的Top16
     * @param request
     * @return
     */
    @PostMapping("selectContractTop6")
    public JsonResult selectListByContractType(HttpServletRequest request, @RequestBody GetMainInData inData) {
        try {
            return mainService.selectListByContractType(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 不同合同类型的Top16
     * @param request
     * @return
     */
    @PostMapping("selectContractTop6Detail")
    public JsonResult selectListByContractTypeDetail(HttpServletRequest request, @RequestBody GetMainInData inData) {
        try {
            return mainService.selectListByContractTypeDetail(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }
}
