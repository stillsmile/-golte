package com.golte.excitation.service;

import com.golte.common.data.JsonResult;
import com.golte.excitation.service.data.ExcitationInData;
import com.golte.excitation.service.data.GetExcitationExportInData;
import com.golte.excitation.service.data.GetExcitationYearExportInData;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ExcitationService {
    /**
     * 保存激励信息
     * @param inData
     * @return
     * @throws Exception
     */
    JsonResult saveExcitation(ExcitationInData inData) throws Exception;

    /**
     * 查询激励信息列表
     * @return
     * @throws Exception
     */
    JsonResult selectListByCondition(ExcitationInData inData) throws Exception;

    /**
     * 查询激励角色
     * @return
     * @throws Exception
     */
    JsonResult selectRoleList(ExcitationInData inData) throws Exception;


    /**
     * 删除激励信息
     * @param inData
     * @return
     * @throws Exception
     */
    JsonResult deleteExcitation(ExcitationInData inData) throws Exception;

    /**
     * 更新激励信息
     * @param inData
     * @return
     * @throws Exception
     */
    JsonResult updateExcitation(ExcitationInData inData) throws Exception;

    /**
     *
     * @author 作者 ws
     * @date 创建时间 2019年4月11日
     * @description 导出激励信息
     * @param @param inData
     * @param @return
     * @param @throws Exception
     * @return JsonResult
     */
    void exportOut(HttpServletResponse response, ExcitationInData inData) throws Exception;

    /**
     *
     * @author 作者 ws
     * @date 创建时间 2019年4月10日
     * @description 批量月度导入激励信息
     * @param @param inData
     * @param @return
     * @param @throws Exception
     * @return JsonResult
     */
    JsonResult exportIn(List<GetExcitationExportInData> inData) throws Exception;

    /**
     *
     * @author 作者 ws
     * @date 创建时间 2019年4月10日
     * @description 批量年度导入激励信息
     * @param @param inData
     * @param @return
     * @param @throws Exception
     * @return JsonResult
     */
    JsonResult exportYearIn(List<GetExcitationYearExportInData> inData) throws Exception;

}
