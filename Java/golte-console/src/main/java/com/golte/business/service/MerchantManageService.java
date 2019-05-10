package com.golte.business.service;

import com.golte.business.service.data.GetAreaInData;
import com.golte.business.service.data.GetFileInData;
import com.golte.business.service.data.GetMerchantExportInData;
import com.golte.business.service.data.GetMerchantInData;
import com.golte.common.data.JsonResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface MerchantManageService {
    /**
     * 上传文件列表
     * @param inData
     * @return
     * @throws Exception
     */
    JsonResult selectUploadFileList(GetFileInData inData) throws Exception;
    /**
     * 已签约合同列表
     * @param inData
     * @return
     * @throws Exception
     */
    JsonResult selectContractList(GetMerchantInData inData) throws Exception;
    /**
     * 区域列表
     * @param inData
     * @return
     * @throws Exception
     */
    JsonResult selectAreaList(GetAreaInData inData) throws Exception;

    /**
     * 查询评估信息
     * @param inData
     * @return
     * @throws Exception
     */
    JsonResult selectEvaluationInfo(GetMerchantInData inData) throws Exception;

    /**
     * 更新评估信息
     * @param inData
     * @return
     * @throws Exception
     */
    JsonResult updateEvaluationInfo(GetMerchantInData inData) throws Exception;

    /**
     * 商家列表
     * @param inData
     * @return
     * @throws Exception
     */
    JsonResult selectMerchantList(GetMerchantInData inData) throws Exception;

    /**
     * 保存评估数据
     * @param inData
     * @return
     * @throws Exception
     */
    JsonResult saveEvaluationInfo(GetMerchantInData inData) throws Exception;

    /**
     * 保存商家数据
     * @param inData
     * @return
     * @throws Exception
     */
    JsonResult saveMerchant(GetMerchantInData inData) throws Exception;

    /**
     * 删除商家数据
     * @param inData
     * @return
     * @throws Exception
     */
    JsonResult deleteMerchant(GetMerchantInData inData) throws Exception;

    /**
     * 更新商家数据
     * @param inData
     * @return
     * @throws Exception
     */
    JsonResult updateMerchant(GetMerchantInData inData) throws Exception;

    /**
     *
     * @author 作者 ws
     * @date 创建时间 2019年4月8日
     * @description 导出商家信息详情
     * @param @param inData
     * @param @return
     * @param @throws Exception
     * @return JsonResult
     */
    void exportOut(HttpServletResponse response, GetMerchantInData inData) throws Exception;

    /**
     *
     * @author 作者 ws
     * @date 创建时间 2019年4月8日
     * @description 批量导入商家信息
     * @param @param inData
     * @param @return
     * @param @throws Exception
     * @return JsonResult
     */
    JsonResult exportIn(List<GetMerchantExportInData> inData) throws Exception;

}
