package com.golte.resource.controller;


import com.golte.common.data.JsonResult;
import com.golte.common.exception.ErrorException;
import com.golte.common.util.BaseController;
import com.golte.resource.service.PointService;
import com.golte.resource.service.data.GetPointInData;
import com.golte.system.service.data.GetLoginOutData;
import com.golte.system.service.data.GetResourcePointInData;
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
@RequestMapping(value = "/pc/resource/point/")
public class PointController extends BaseController {
    private final Logger log = LoggerFactory.getLogger(PointController.class);

    @Autowired
    private PointService pointService;


    /**
     * 资源点位Tree列表
     * @return
     */
    @PostMapping(value = "selecTree")
    public JsonResult selectPointTreeList(HttpServletRequest request,@RequestBody GetPointInData inData) {
        try {
            JsonResult jsonResult = pointService.selectPointTreeList(inData);
            return jsonResult;
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 资源点位Table列表
     * @return
     */
    @PostMapping(value = "selecTable")
    public JsonResult selectPointTableList(HttpServletRequest request,@RequestBody GetPointInData inData) {
        try {
            JsonResult jsonResult = pointService.selectPointTableList(inData);
            return jsonResult;
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 根据三级id查询一二三级id详情
     * @param indata
     * @return
     */
    @PostMapping(value = "selectByThirdId")
    public JsonResult selectPointInfoByid(HttpServletRequest request,@RequestBody GetPointInData inData) {
        try {
            JsonResult jsonResult = pointService.selectPointInfoByid(inData);
            return jsonResult;
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 根据父Id查询点位信息
     * @param indata
     * @return
     */
    @PostMapping(value = "selectBypid")
    public JsonResult selectPointInfoByid(HttpServletRequest request,@RequestBody GetResourcePointInData inData) {
        try {
            JsonResult jsonResult = pointService.selectPointByParentId(inData);
            return jsonResult;
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 保存资源点位信息
     * @param request
     * @param inData
     * @return
     */
    @PostMapping("save")
    public JsonResult saveResourcePoint(HttpServletRequest request, @RequestBody GetPointInData inData) {
        try {
            GetLoginOutData outData = getLoginUser(request);
            inData.setLoginAccount(outData.getLoginName());
            return pointService.saveResourcePoint(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 更新资源点位信息
     * @param request
     * @param inData
     * @return
     */
    @PostMapping("update")
    public JsonResult updateResourcePointDetail(HttpServletRequest request, @RequestBody GetPointInData inData) {
        try {
            GetLoginOutData outData = getLoginUser(request);
            inData.setLoginAccount(outData.getLoginName());
            return pointService.updateResourcePointDetail(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 删除资源点位信息
     * @param request
     * @param inData
     * @return
     */
    @PostMapping("delete")
    public JsonResult deleteEmployee(HttpServletRequest request, @RequestBody GetPointInData inData) {
        try {
            return pointService.deleteResourcePointInfo(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }


}
