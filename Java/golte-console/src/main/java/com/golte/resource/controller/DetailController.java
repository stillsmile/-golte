package com.golte.resource.controller;
import com.alibaba.fastjson.JSON;
import com.golte.common.data.GetCityOutData;
import com.golte.common.data.JsonResult;
import com.golte.common.exception.ErrorException;
import com.golte.common.util.BaseController;
import com.golte.common.util.UtilConst;
import com.golte.resource.service.ProjectDetailService;
import com.golte.resource.service.data.GetPointExportInData;
import com.golte.resource.service.data.GetProjectDetailInData;
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
 * @date 创建时间 2019年4月3日 上午11:44:07
 * @description 资源项目详情接口
 */
@RestController
@RequestMapping(value = "/pc/resource/project/detail/")
public class DetailController extends BaseController {
    private final Logger log = LoggerFactory.getLogger(PointController.class);

    @Autowired
    private ProjectDetailService projectDetailService;

    /**
     * 资源项目详情查询
     * @param indata
     * @return
     */
    @PostMapping(value = "select")
    public JsonResult selectResourceProjectDetailList(HttpServletRequest request, @RequestBody GetProjectDetailInData indata) {
        try {
            GetCityOutData cityInfo = getCityInfo(request.getHeader(UtilConst.LOGIN_TOKEN));
            indata.setCityIds(cityInfo.getCityIds());//设置有权限城市的ids集合
            JsonResult jsonResult = projectDetailService.selectResourceProjectDetailList(indata);
            return jsonResult;
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }


    /**
     * 保存资源项目详情信息
     * @param request
     * @param inData
     * @return
     */
    @PostMapping("save")
    public JsonResult saveResourceProjectDetail(HttpServletRequest request, @RequestBody GetProjectDetailInData inData) {
        try {
            GetLoginOutData outData = getLoginUser(request);
            inData.setLoginAccount(outData.getLoginName());
            return projectDetailService.saveResourceProjectDetail(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 删除资源项目详情
     * @param request
     * @param inData
     * @return
     */
    @PostMapping("delete")
    public JsonResult deleteResourceProjectDetail(HttpServletRequest request, @RequestBody GetProjectDetailInData inData) {
        try {
            return projectDetailService.deleteResourceProjectDetail(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 更新资源项目详情
     * @param request
     * @param inData
     * @return
     */
    @PostMapping("update")
    public JsonResult updateResourceProjectDetail(HttpServletRequest request, @RequestBody GetProjectDetailInData inData) {
        try {
            GetLoginOutData outData = getLoginUser(request);
            inData.setLoginAccount(outData.getLoginName());
            return projectDetailService.updateResourceProjectDetail(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * @author 作者 ws
     * @date 创建时间 2019年3月26日
     * @description 导出资源明细
     * @param inData
     * @return
     */
    @RequestMapping(value = "exportOut", method = RequestMethod.POST)
    public void exportOut(HttpServletRequest request, HttpServletResponse response, GetProjectDetailInData inData) {
        try {
            GetCityOutData cityOutData = getCityInfo(inData.getToken());
            inData.setCityIds(cityOutData.getCityIds());
            projectDetailService.exportOut(response,inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * @Description: 批量导入资源明细
     * @author ws
     * @date 2019/4/8
     */
    @RequestMapping(value = "exportIn", method = RequestMethod.POST)
    public JsonResult exportIn(HttpServletRequest request, MultipartFile file) {
        try {
            GetLoginOutData outData = getLoginUser(request);
            String loginName = outData.getLoginName();
            List<GetPointExportInData> inData = importExcel(file, GetPointExportInData.class, 0,2);
            return projectDetailService.exportIn(inData,loginName);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }



}
