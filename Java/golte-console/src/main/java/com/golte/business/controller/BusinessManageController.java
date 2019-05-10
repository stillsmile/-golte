package com.golte.business.controller;

import com.golte.business.service.data.GetAreaInData;
import com.golte.business.service.data.GetFileInData;
import com.golte.business.service.data.GetMerchantExportInData;
import com.golte.business.service.data.GetMerchantInData;
import com.golte.business.service.MerchantManageService;
import com.golte.common.data.GetCityOutData;
import com.golte.common.data.JsonResult;
import com.golte.common.exception.ErrorException;
import com.golte.common.util.BaseController;
import com.golte.common.util.UtilConst;
import com.golte.system.service.data.GetLoginOutData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 作者 WS
 * @date 创建时间 2019年3月29日 上午11:44:07
 * @description 商家管理接口
 */

@RestController
@RequestMapping(value = "/pc/business/")
public class BusinessManageController extends BaseController{
    private final Logger log = LoggerFactory.getLogger(BusinessManageController.class);

    @Autowired
    private MerchantManageService merchantManageService;

    /**
     * 上传文件列表查询
     * @param indata
     * @return
     */
    @PostMapping(value = "uploadfilelist")
    public JsonResult selectUploadFileList(HttpServletRequest request, @RequestBody GetFileInData indata) {
        try {
            JsonResult jsonResult = merchantManageService.selectUploadFileList(indata);
            return jsonResult;
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 合同列表查询
     * @param indata
     * @return
     */
    @PostMapping(value = "contractlist")
    public JsonResult getContractList(HttpServletRequest request, @RequestBody GetMerchantInData indata) {
        try {
            GetCityOutData cityInfo = getCityInfo(request.getHeader(UtilConst.LOGIN_TOKEN));
            indata.setCityIds(cityInfo.getCityIds());
            JsonResult jsonResult = merchantManageService.selectContractList(indata);
            return jsonResult;
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 区域列表查询
     * @param indata
     * @return
     */
    @PostMapping(value = "arealist")
    public JsonResult selectAreaList(HttpServletRequest request, @RequestBody GetAreaInData indata) {
        try {
            JsonResult jsonResult = merchantManageService.selectAreaList(indata);
            return jsonResult;
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 查询商家评估信息
     * @param indata
     * @return
     */
    @PostMapping(value = "selectEvaluationInfo")
    public JsonResult selectEvaluationInfo(HttpServletRequest request, @RequestBody GetMerchantInData indata) {
        try {
            JsonResult jsonResult = merchantManageService.selectEvaluationInfo(indata);
            return jsonResult;
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 更新商家评估信息
     * @param indata
     * @return
     */
    @PostMapping(value = "updateEvaluationInfo")
    public JsonResult updateEvaluationInfo(HttpServletRequest request, @RequestBody GetMerchantInData indata) {
        try {
            JsonResult jsonResult = merchantManageService.updateEvaluationInfo(indata);
            return jsonResult;
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 商家列表查询
     * @param indata
     * @return
     */
    @PostMapping(value = "select")
    public JsonResult selectMerchantList(HttpServletRequest request, @RequestBody GetMerchantInData indata) {
        try {
            GetCityOutData cityInfo = getCityInfo(request.getHeader(UtilConst.LOGIN_TOKEN));
            indata.setCityIds(cityInfo.getCityIds());
            JsonResult jsonResult = merchantManageService.selectMerchantList(indata);
            return jsonResult;
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 保存商家评估
     * @param request
     * @param inData
     * @return
     */
    @PostMapping("saveEvaluationInfo")
    public JsonResult saveEvaluationInfo(HttpServletRequest request, @RequestBody GetMerchantInData inData) {
        try {
            return merchantManageService.saveEvaluationInfo(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 保存商家数据
     * @param request
     * @param inData
     * @return
     */
    @PostMapping("save")
    public JsonResult saveMerchant(HttpServletRequest request, @RequestBody GetMerchantInData inData) {
        try {
            GetLoginOutData outData = getLoginUser(request);
            inData.setLoginAccount(outData.getLoginName());
            return merchantManageService.saveMerchant(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 删除商家数据
     * @param request
     * @param inData
     * @return
     */
    @PostMapping("delete")
    public JsonResult deleteMerchant(HttpServletRequest request, @RequestBody GetMerchantInData inData) {
        try {
            return merchantManageService.deleteMerchant(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 更新商家信息
     * @param request
     * @param inData
     * @return
     */
    @PostMapping("update")
    public JsonResult updateMerchant(HttpServletRequest request, @RequestBody GetMerchantInData inData) {
        try {
            GetLoginOutData outData = getLoginUser(request);
            inData.setLoginAccount(outData.getLoginName());
            return merchantManageService.updateMerchant(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * @author 作者 ws
     * @date 创建时间 2019年3月26日
     * @description 导出商家信息
     * @param inData
     * @return
     */
    @RequestMapping(value = "exportOut", method = RequestMethod.POST)
    public void exportOut(HttpServletRequest request, HttpServletResponse response, GetMerchantInData inData) {
        try {
            GetCityOutData cityOutData = getCityInfo(inData.getToken());
            inData.setCityIds(cityOutData.getCityIds());
            merchantManageService.exportOut(response,inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * @Description: 批量商家信息
     * @author ws
     * @date 2019/4/8
     */
    @RequestMapping(value = "exportIn", method = RequestMethod.POST)
    public JsonResult exportIn(HttpServletRequest request, MultipartFile file) {
        try {
            List<GetMerchantExportInData> inData = importExcel(file, GetMerchantExportInData.class, 0,2);
            return merchantManageService.exportIn(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

}
