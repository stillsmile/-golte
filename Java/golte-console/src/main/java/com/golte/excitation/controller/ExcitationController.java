package com.golte.excitation.controller;

import com.golte.common.data.GetCityOutData;
import com.golte.common.data.JsonResult;
import com.golte.common.exception.ErrorException;
import com.golte.common.util.BaseController;
import com.golte.common.util.UtilConst;
import com.golte.excitation.service.ExcitationService;
import com.golte.excitation.service.data.ExcitationInData;
import com.golte.excitation.service.data.GetExcitationExportInData;
import com.golte.excitation.service.data.GetExcitationYearExportInData;
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
 * @date 创建时间 2019年4月9日 上午11:44:07
 * @description 激励信息接口
 */

@RestController
@RequestMapping(value = "/pc/excitation/")
public class ExcitationController extends BaseController{
    private final Logger log = LoggerFactory.getLogger(ExcitationController.class);

    @Autowired
    private ExcitationService excitationService;

    /**
     * @author 作者 ws
     * @date 创建时间 2019年4月9日
     * @description 查询激励角色（分页）
     * @param inData
     * @return
     */
    @PostMapping("selectRoleList")
    public JsonResult selectRoleList(HttpServletRequest request,@RequestBody ExcitationInData inData) {
        try {
            return excitationService.selectRoleList(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * @author 作者 ws
     * @date 创建时间 2019年4月9日
     * @description 查询激励信息（分页）
     * @param inData
     * @return
     */
    @PostMapping("select")
    public JsonResult selectListByCondition(HttpServletRequest request,@RequestBody ExcitationInData inData) {
        try {
            GetCityOutData cityOutData = getCityInfo(request.getHeader(UtilConst.LOGIN_TOKEN));
            inData.setCityIds(cityOutData.getCityIds());
            return excitationService.selectListByCondition(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 保存激励信息
     * @param request
     * @param inData
     * @return
     */
    @PostMapping("save")
    public JsonResult saveExcitation(HttpServletRequest request, @RequestBody ExcitationInData inData) {
        try {
            GetLoginOutData outData = getLoginUser(request);
            inData.setLoginAccount(outData.getLoginName());
            return excitationService.saveExcitation(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }


    /**
     * 删除激励信息
     * @param request
     * @param inData
     * @return
     */
    @PostMapping("delete")
    public JsonResult deleteExcitation(HttpServletRequest request, @RequestBody ExcitationInData inData) {
        try {
            return excitationService.deleteExcitation(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 更新激励信息
     * @param request
     * @param inData
     * @return
     */
    @PostMapping("update")
    public JsonResult updateResourceProject(HttpServletRequest request, @RequestBody ExcitationInData inData) {
        try {
            GetLoginOutData outData = getLoginUser(request);
            inData.setLoginAccount(outData.getLoginName());
            return excitationService.updateExcitation(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * @author 作者 ws
     * @date 创建时间 2019年4月9日
     * @description 导出激励信息
     * @param inData
     * @return
     */
    @RequestMapping(value = "exportOut", method = RequestMethod.POST)
    public void exportOut(HttpServletRequest request, HttpServletResponse response, ExcitationInData inData) {
        try {
            GetCityOutData cityOutData = getCityInfo(inData.getToken());
            inData.setCityIds(cityOutData.getCityIds());
            excitationService.exportOut(response,inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * @Description: 月度导入激励信息
     * @author ws
     * @date 2019/4/9
     */
    @RequestMapping(value = "exportIn", method = RequestMethod.POST)
    public JsonResult exportIn(HttpServletRequest request, MultipartFile file) {
        try {
            List<GetExcitationExportInData> inData = importExcel(file, GetExcitationExportInData.class, 0,2);
            return excitationService.exportIn(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }
    /**
     * @Description: 年度导入激励信息
     * @author ws
     * @date 2019/4/9
     */
    @RequestMapping(value = "exportYearIn", method = RequestMethod.POST)
    public JsonResult exportYearIn(HttpServletRequest request, MultipartFile file) {
        try {
            List<GetExcitationYearExportInData> inData = importExcel(file, GetExcitationYearExportInData.class, 0,2);
            return excitationService.exportYearIn(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

}
