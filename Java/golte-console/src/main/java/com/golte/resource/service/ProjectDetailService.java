package com.golte.resource.service;

import com.golte.common.data.JsonResult;
import com.golte.resource.service.data.GetPointExportInData;
import com.golte.resource.service.data.GetProjectDetailInData;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ProjectDetailService {
    /**
     * 查询资源项目详情列表
     * @param inData
     * @return
     * @throws Exception
     */
    JsonResult selectResourceProjectDetailList(GetProjectDetailInData inData) throws Exception;

    /**
     * 保存资源项目详情数据
     * @param inData
     * @return
     * @throws Exception
     */
    JsonResult saveResourceProjectDetail(GetProjectDetailInData inData) throws Exception;

    /**
     * 删除资源项目详情数据
     * @param inData
     * @return
     * @throws Exception
     */
    JsonResult deleteResourceProjectDetail(GetProjectDetailInData inData) throws Exception;

    /**
     * 更新资源项目详情数据
     * @param inData
     * @return
     * @throws Exception
     */
    JsonResult updateResourceProjectDetail(GetProjectDetailInData inData) throws Exception;


    /**
     * @author 作者 ws
     * @date 创建时间 2019年4月8日
     * @description 导出资源项目详情
     * @param @param inData
     * @param @return
     * @param @throws Exception
     * @return JsonResult
     */
    void exportOut(HttpServletResponse response, GetProjectDetailInData inData) throws Exception;

    /**
     * @author 作者 ws
     * @date 创建时间 2019年4月8日
     * @description 批量导入资源项目详情
     * @param @param inData
     * @param @return
     * @param @throws Exception
     * @return JsonResult
     */
    JsonResult exportIn(List<GetPointExportInData> inData, String loginName) throws Exception;

}
