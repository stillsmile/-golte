package com.golte.business.service.impl;

import com.auth0.jwt.internal.org.apache.commons.lang3.StringUtils;
import com.github.pagehelper.PageHelper;
import com.golte.business.service.MerchantManageService;
import com.golte.business.service.data.*;
import com.golte.common.data.JsonResult;
import com.golte.common.data.PageInfo;
import com.golte.common.exception.ServiceException;
import com.golte.common.util.*;
import com.golte.contract.service.data.GetContractOutData;
import com.golte.mapper.*;
import com.golte.mapper.entity.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class MerchantManageServiceImpl implements MerchantManageService{

    @Autowired
    private GMerchantMapper merchantMapper;

    @Autowired
    private GAreaMapper areaMapper;

    @Autowired
    private GAnnexMapper annexMapper;

    @Autowired
    private GCityMapper cityMapper;

    @Autowired
    private GContractMapper contractMapper;

    @Autowired
    private GMerchantEvaluationMapper merchantEvaluationMapper;

    @Override
    public JsonResult selectUploadFileList(GetFileInData inData) throws Exception {
        List<GetFileOutData> getFileOutData = annexMapper.selectUploadFileList(inData);
        return JsonResult.success(getFileOutData, UtilMessage.GET_MESSAGE_SUCCESS);
    }

    @Override
    public JsonResult selectContractList(GetMerchantInData inData) throws Exception {
        PageInfo<GetContractOutData> pageInfo;
        PageHelper.startPage(inData.getPageNum(), inData.getPageSize());
        List<GetContractOutData> getContractOutData = contractMapper.selectListByMerchantId(inData);
        if (Util.isEmptyList(getContractOutData)) {
            pageInfo = new PageInfo<>(new ArrayList<GetContractOutData>());
        } else {
            pageInfo = new PageInfo<>(getContractOutData);
        }
        return JsonResult.success(pageInfo, UtilMessage.GET_MESSAGE_SUCCESS);
    }

    @Override
    public JsonResult selectAreaList(GetAreaInData inData) throws Exception {
        List<GArea> list= areaMapper.selectAreaList(inData);
        return JsonResult.success(list, UtilMessage.GET_MESSAGE_SUCCESS);
    }

    @Override
    public JsonResult selectMerchantList(GetMerchantInData inData) throws Exception {
        PageInfo<GetMerchantOutData> pageInfo;
        PageHelper.startPage(inData.getPageNum(), inData.getPageSize());
        List<GetMerchantOutData> getMerchantOutData = merchantMapper.selectListByCondition(inData);
        if(Util.isEmptyList(inData.getCityIds())){
            for (GetMerchantOutData merchantOutData : getMerchantOutData) {
                merchantOutData.setAllCity("1");
            }
        }else{
            List<Long> cityIds = inData.getCityIds();
            for (GetMerchantOutData merchantOutData : getMerchantOutData) {
                String city = merchantOutData.getCityId()==null?"-1":merchantOutData.getCityId();
                boolean contains = cityIds.contains(Long.parseLong(city));
                if(contains){
                    merchantOutData.setEditCity("1");
                }
                merchantOutData.setAllCity("0");
            }
        }
        if (Util.isEmptyList(getMerchantOutData)) {
            pageInfo = new PageInfo<>(new ArrayList<GetMerchantOutData>());
        } else {
            pageInfo = new PageInfo<>(getMerchantOutData);
        }
        return JsonResult.success(pageInfo, UtilMessage.GET_MESSAGE_SUCCESS);
    }

    @Override
    public JsonResult selectEvaluationInfo(GetMerchantInData inData) throws Exception {

        GMerchantEvaluation gMerchantEvaluation;
        Example example = new Example(GMerchantEvaluation.class);
        example.createCriteria().andEqualTo("merchantId", inData.getId());
        List<GMerchantEvaluation> gMerchantEvaluations = merchantEvaluationMapper.selectByExample(example);
        if (Util.isEmptyList(gMerchantEvaluations)) {
            gMerchantEvaluation = new GMerchantEvaluation();
        } else {
            gMerchantEvaluation = gMerchantEvaluations.get(0);
        }
        return JsonResult.success(gMerchantEvaluation, UtilMessage.GET_MESSAGE_SUCCESS);
    }

    @Override
    @Transactional
    public JsonResult saveEvaluationInfo(GetMerchantInData inData) throws Exception {

        GMerchantEvaluation gMerchantEvaluation = new GMerchantEvaluation();
        gMerchantEvaluation.setMerchantId(inData.getId());
        gMerchantEvaluation.setCooperationEvaluation(inData.getCooperationEvaluation());
        merchantEvaluationMapper.insert(gMerchantEvaluation);
        List<GetFileInData> fileList = inData.getFileList();
        if(fileList.size()>0){
            for (int i=0;i<fileList.size();i++){
                GAnnex gAnnex = new GAnnex();
                gAnnex.setType("3");
                gAnnex.setAssociationId(inData.getId());
                gAnnex.setName(fileList.get(i).getName());
                gAnnex.setUrl(fileList.get(i).getUrl());
                gAnnex.setCreateTime(new Date());
                annexMapper.insert(gAnnex);
            }
        }

        return JsonResult.success("", UtilMessage.SAVE_MESSAGE_SUCCESS);
    }

    @Override
    @Transactional
    public JsonResult updateEvaluationInfo(GetMerchantInData inData) throws Exception {

        GMerchantEvaluation gMerchantEvaluation = merchantEvaluationMapper.selectByPrimaryKey(inData.getEvaluationId());
        gMerchantEvaluation.setCooperationEvaluation(inData.getCooperationEvaluation());
        merchantEvaluationMapper.updateByPrimaryKeySelective(gMerchantEvaluation);
        //删除全部已存在的文件
        Example example = new Example(GAnnex.class);
        example.createCriteria().andEqualTo("associationId", inData.getId());
        List<GAnnex> gAnnexes = annexMapper.selectByExample(example);
        for (GAnnex annex :gAnnexes ) {
            annexMapper.delete(annex);
        }
        List<GetFileInData> fileList = inData.getFileList();
        if(!Util.isEmptyList(fileList) &&fileList.size()>0){
            for (int i=0;i<fileList.size();i++){
                GAnnex gAnnex = new GAnnex();
                gAnnex.setType("3");
                gAnnex.setAssociationId(inData.getId());
                gAnnex.setName(fileList.get(i).getName());
                gAnnex.setUrl(fileList.get(i).getUrl());
                gAnnex.setCreateTime(new Date());
                annexMapper.insert(gAnnex);
            }
        }
        return JsonResult.success("", UtilMessage.SAVE_MESSAGE_SUCCESS);
    }

    @Override
    @Transactional
    public JsonResult saveMerchant(GetMerchantInData inData) throws Exception {
        //先判断此城市下商家名称是否重复
        Example example = new Example(GMerchant.class);
        example.createCriteria().andEqualTo("name", inData.getName())
                .andEqualTo("city", inData.getCity())
                .andEqualTo("delStatus", UtilConst.STR_NUMBER_1);
        int count  = merchantMapper.selectCountByExample(example);
        if(count>0){
            return JsonResult.fail(UtilConst.CODE_403, UtilMessage.MERCHANT_NAME_REPEAT);
        }
        //组装商家信息
        GMerchant gMerchant = new GMerchant();
        gMerchant.setCode(inData.getCode());
        gMerchant.setName(inData.getName());
        gMerchant.setContact(inData.getContact());
        gMerchant.setContactPhone(inData.getContactPhone());
        gMerchant.setContact2(inData.getContact2());
        gMerchant.setContactPhone2(inData.getContactPhone2());
        gMerchant.setConpanyAddress(inData.getConpanyAddress());
        gMerchant.setDetailedAddress(inData.getDetailedAddress());
        gMerchant.setCity(inData.getCity());
        gMerchant.setCentralCityId(inData.getCentralCityId());
        gMerchant.setSupplierType(inData.getSupplierType());
        gMerchant.setLevel(inData.getLevel());
        gMerchant.setDelStatus(UtilConst.STR_NUMBER_1);
        gMerchant.setRemarks(inData.getRemarks());
        gMerchant.setCreateName(inData.getLoginAccount());
        gMerchant.setCreateTime(new Date());
        merchantMapper.insert(gMerchant);
        List<GetFileInData> fileList = inData.getFileList();
        if(fileList.size()>0){
            for (int i=0;i<fileList.size();i++){
                GAnnex gAnnex = new GAnnex();
                gAnnex.setType("2");
                gAnnex.setAssociationId(gMerchant.getId());
                gAnnex.setName(fileList.get(i).getName());
                gAnnex.setUrl(fileList.get(i).getUrl());
                gAnnex.setCreateTime(new Date());
                annexMapper.insert(gAnnex);
            }
        }
        return JsonResult.success("", UtilMessage.SAVE_MESSAGE_SUCCESS);
    }

    @Override
    @Transactional
    public JsonResult deleteMerchant(GetMerchantInData inData) throws Exception {
        //删除的时候判断是否跟合同关联
        Example example = new Example(GContract.class);
        List<Long> ids = inData.getIds();
        for(Long id : ids){
            example.createCriteria().andEqualTo("merchantId", id);
            List<GContract> gContracts = contractMapper.selectByExample(example);
            if(gContracts.size()>0){
                GMerchant gMerchant = merchantMapper.selectByPrimaryKey(id);
                String exitMes = "提示：存在与合同关联的商家：" +   gMerchant.getName() + "，不可删除！";
                return JsonResult.fail(UtilConst.CODE_403, exitMes);
            }
        }
        //删除商家
        example = new Example(GMerchant.class);
        example.createCriteria().andIn("id", inData.getIds());
        GMerchant merchant = new GMerchant();
        merchant.setDelStatus(UtilConst.STR_NUMBER_0);
        merchantMapper.updateByExampleSelective(merchant,example);
        return JsonResult.success("", UtilMessage.DEL_MESSAGE_SUCCESS);
    }

    @Override
    @Transactional
    public JsonResult updateMerchant(GetMerchantInData inData) throws Exception {
        Example example = new Example(GMerchant.class);
        example.createCriteria().andEqualTo("name", inData.getName())
                .andEqualTo("city", inData.getCity())
                .andEqualTo("delStatus", UtilConst.STR_NUMBER_1);
        List<GMerchant> gMerchants = merchantMapper.selectByExample(example);
        if(gMerchants.size()>0){
            GMerchant gMerchant = gMerchants.get(0);
            //如果Id相同，则为同一个，不同则说明重复
            if(!gMerchant.getId().equals(inData.getId())){
                return JsonResult.fail(UtilConst.CODE_403, UtilMessage.CITY_MERCHANT_NAME_REPEAT);
            }
        }
        GMerchant gMerchant = merchantMapper.selectByPrimaryKey(inData.getId());
        gMerchant.setCode(inData.getCode());
        gMerchant.setName(inData.getName());
        gMerchant.setContact(inData.getContact());
        gMerchant.setContactPhone(inData.getContactPhone());
        gMerchant.setContact2(inData.getContact2());
        gMerchant.setContactPhone2(inData.getContactPhone2());
        gMerchant.setConpanyAddress(inData.getConpanyAddress());
        gMerchant.setDetailedAddress(inData.getDetailedAddress());
        gMerchant.setCity(inData.getCity());
        gMerchant.setCentralCityId(inData.getCentralCityId());
        gMerchant.setSupplierType(inData.getSupplierType());
        gMerchant.setLevel(inData.getLevel());
        //设置商家上传资料
        gMerchant.setRemarks(inData.getRemarks());
        gMerchant.setUpdateName(inData.getLoginAccount());
        gMerchant.setUpdateTime(new Date());
        //删除全部已存在的文件
        example = new Example(GAnnex.class);
        example.createCriteria().andEqualTo("associationId", inData.getId());
        List<GAnnex> gAnnexes = annexMapper.selectByExample(example);
        for (GAnnex annex :gAnnexes ) {
            annexMapper.delete(annex);
        }
        List<GetFileInData> fileList = inData.getFileList();
        if(fileList.size()>0){
            for (int i=0;i<fileList.size();i++){
                GAnnex gAnnex = new GAnnex();
                gAnnex.setType("2");
                gAnnex.setAssociationId(gMerchant.getId());
                gAnnex.setName(fileList.get(i).getName());
                gAnnex.setUrl(fileList.get(i).getUrl());
                gAnnex.setCreateTime(new Date());
                annexMapper.insert(gAnnex);
            }
        }
        merchantMapper.updateByPrimaryKeySelective(gMerchant);
        return JsonResult.success("", UtilMessage.SAVE_MESSAGE_SUCCESS);
    }

    @Override
    public void exportOut(HttpServletResponse response, GetMerchantInData inData) throws Exception {
        List<GetMerchantOutData> getMerchantOutData = merchantMapper.selectListByCondition(inData);
        List<GetMerchantExportOutData> outDataList = new ArrayList<>();
        for (GetMerchantOutData merchantOutData:getMerchantOutData){
            GetMerchantExportOutData getMerchantExportOut = new GetMerchantExportOutData();
            String level = merchantOutData.getLevel();
            if (UtilConst.STR_NUMBER_1.equals(level)){
                getMerchantExportOut.setLevel("全国");
            }
            if (UtilConst.STR_NUMBER_2.equals(level)){
                getMerchantExportOut.setLevel("城市");
            }
            if (UtilConst.STR_NUMBER_3.equals(level)){
                getMerchantExportOut.setLevel("项目");
            }
            getMerchantExportOut.setName(merchantOutData.getName());
            getMerchantExportOut.setContact(merchantOutData.getContact());
            getMerchantExportOut.setContactPhone(merchantOutData.getContactPhone());
            String supplierType = merchantOutData.getSupplierType();
            if(UtilConst.STR_NUMBER_1.equals(supplierType)){
                getMerchantExportOut.setSupplierType("合格");
            }
            if(UtilConst.STR_NUMBER_0.equals(supplierType)){
                getMerchantExportOut.setSupplierType("不合格");
            }
            getMerchantExportOut.setHeTongNum(merchantOutData.getHeTongNum());
            getMerchantExportOut.setAmount(merchantOutData.getAmount());
            outDataList.add(getMerchantExportOut);
        }

        String excelName = "商家信息";
        String[] headers = {"商家等级", "商家名称", "联系人", "联系方式", "是否合格供应商", "合同数量", "合同金额"};
        ExportExcel<GetMerchantExportOutData> excelUtil = new ExportExcel<GetMerchantExportOutData>();
        response.setContentType("application/vnd.ms-excel; charset=utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + new String((excelName).getBytes(), "iso-8859-1") + ".xls");
        response.setCharacterEncoding("UTF-8");
        OutputStream outputStream = response.getOutputStream();
        excelUtil.exportExcel(headers, outDataList, outputStream, null);
    }

    @Override
    @Transactional
    public JsonResult exportIn(List<GetMerchantExportInData> fileDataList) throws Exception {
        if (Util.isEmptyList(fileDataList)) {
            return JsonResult.fail(UtilConst.CODE_403, "需要导入的商家数据为空");
        }
        for(GetMerchantExportInData merchantExportInData:fileDataList){

            if(Util.isEmpty(merchantExportInData.getCode())){
                return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的商家编号不能为空");
            }else{
                if(merchantExportInData.getCode().length()>30){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的商家编号内容过长");
                }
            }
            if(Util.isEmpty(merchantExportInData.getName())){
                return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的商家名称不能为空");
            }else{
                if(merchantExportInData.getName().length()>30){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的商家名称内容过长");
                }
            }
            if(Util.isEmpty(merchantExportInData.getContact())){
                return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的联系人不能为空");
            }else{
                if(merchantExportInData.getContact().length()>15){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的联系人1内容过长");
                }
            }
            if(merchantExportInData.getContact2().length()>15){
                return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的联系人2内容过长");
            }
            if(Util.isEmpty(merchantExportInData.getContactPhone())){
                return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的联系方式1不能为空");
            }else{
                if(merchantExportInData.getContactPhone().length()>15){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的联系方式内容过长");
                }
            }
            if(merchantExportInData.getContactPhone2().length()>15){
                return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的联系方式2内容过长");
            }
            if(Util.isEmpty(merchantExportInData.getConpanyAddress())){
                return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的公司地址不能为空");
            }else{
                if(merchantExportInData.getConpanyAddress().length()>50){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的公司地址内容过长");
                }
            }
            if(!Util.isEmpty(merchantExportInData.getLevel())){
                String level = merchantExportInData.getLevel();
                if(!("全国".equals(level)||"城市".equals(level)||"项目".equals(level))){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的商家等级为(全国丶城市或项目)");
                }
            }else{
                return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的商家等级不能为空");
            }
            if(!Util.isEmpty(merchantExportInData.getSupplierType())){
                String supplierType = merchantExportInData.getSupplierType();
                if(!("否".equals(supplierType)||"是".equals(supplierType))){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的是否合格为(是或否)");
                }
            }else {
                return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的是否合格不能为空");
            }
            if(Util.isEmpty(merchantExportInData.getCity())){
                return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的所在城市不能为空");
            }
        }

        //校验数据的有效性
        boolean flag = true;
        List errorList = new ArrayList();
        int countNum = 0;
        int countCode = 0;
        boolean countNameFlag = true;
        for (GetMerchantExportInData merchantExportInData : fileDataList) {
            //先判断是否存在此商家信息
            Example example = new Example(GMerchant.class);
            example.createCriteria().andEqualTo("code", merchantExportInData.getCode()).andEqualTo("delStatus", UtilConst.STR_NUMBER_1);
            int count  = merchantMapper.selectCountByExample(example);
            if(count>0){
                flag = false;
                String errorMes = "商家编号："+ merchantExportInData.getCode() +"已存在";
                errorList.add(errorMes);
                continue;
            }

            //判读录入的城市是否存在
            example = new Example(GCity.class);
            example.createCriteria().andEqualTo("cityName", merchantExportInData.getCity()).andEqualTo("delStatus", UtilConst.STR_NUMBER_1);
            List<GCity> gCities = cityMapper.selectByExample(example);
            if(gCities.size()==0){
                flag = false;
                String errorMes = "落地城市" + merchantExportInData.getCity() + "不存在";
                errorList.add(errorMes);
                continue;
            }else{
                GCity city = gCities.get(0);
                //先判断此城市下商家名称是否重复
                example = new Example(GMerchant.class);
                example.createCriteria().andEqualTo("name", merchantExportInData.getName())
                        .andEqualTo("city", city.getId())
                        .andEqualTo("delStatus", UtilConst.STR_NUMBER_1);
                int countName  = merchantMapper.selectCountByExample(example);
                if(countName>0){
                    flag = false;
                    String errorMes = "城市："+ city.getCityName()+"下存在相同的商家"+merchantExportInData.getName();
                    errorList.add(errorMes);
                    continue;

                }
            }

            for (GetMerchantExportInData exportInData : fileDataList) {
                if(exportInData.getName().equals(merchantExportInData.getName())&&exportInData.getCity().equals(merchantExportInData.getCity())){
                    countNum ++;
                    if(countNum>1){
                        flag = false;
                        if(countNameFlag){
                            String errorMes =  "同城市下不能存在相同的商家"+merchantExportInData.getName();
                            errorList.add(errorMes);
                            countNameFlag = false;
                            continue;
                        }else {
                            break;
                        }
                    }
                }
            }
            countNum = 0;

            for (GetMerchantExportInData exportInData : fileDataList) {
                if(exportInData.getCode().equals(merchantExportInData.getCode())){
                    countCode ++;
                    if(countCode>1){
                        flag = false;
                        if(countNameFlag){
                            String errorMes =  "导入数据存在相同的商家编号"+merchantExportInData.getName();
                            errorList.add(errorMes);
                            countNameFlag = false;
                            continue;
                        }else {
                            break;
                        }
                    }
                }
            }
            countCode =0;
        }
        if(!flag){
            return JsonResult.fail(UtilConst.CODE_403,errorList.toString());
        }

        for (GetMerchantExportInData merchantExportInData : fileDataList){
            GMerchant gMerchant = new GMerchant();
            gMerchant.setCode(merchantExportInData.getCode());
            gMerchant.setName(merchantExportInData.getName());
            gMerchant.setContact(merchantExportInData.getContact());
            gMerchant.setContactPhone(merchantExportInData.getContactPhone());
            gMerchant.setContact2(merchantExportInData.getContact2());
            gMerchant.setContactPhone2(merchantExportInData.getContactPhone2());
            gMerchant.setDetailedAddress(merchantExportInData.getConpanyAddress());
            gMerchant.setConpanyAddress("");//地址显示在详细地址上，此处不做处理
            //判读录入的城市是否存在
            Example example = new Example(GCity.class);
            example.createCriteria().andEqualTo("cityName", merchantExportInData.getCity()).andEqualTo("delStatus", UtilConst.STR_NUMBER_1);
            List<GCity> gCities = cityMapper.selectByExample(example);
            String cityId = "";
            if(gCities.size()>0){
                GCity city = gCities.get(0);
                Long id = city.getId();
                Long centralCityId = city.getCentralCityId();
                gMerchant.setCentralCityId(centralCityId);
                gMerchant.setCity(id+"");
            }
            gMerchant.setRemarks(merchantExportInData.getRemarks());
            //是否合格供应商（0不合格 1合格）
            String supplierType = merchantExportInData.getSupplierType();
            if("否".equals(supplierType)){
                gMerchant.setSupplierType(UtilConst.STR_NUMBER_0);
            }
            if("是".equals(supplierType)){
                gMerchant.setSupplierType(UtilConst.STR_NUMBER_1);
            }
            //商家等级（1全国 2城市 3项目）
            String level = merchantExportInData.getLevel();
            if("全国".equals(level)){
                gMerchant.setLevel(UtilConst.STR_NUMBER_1);
            }
            if("城市".equals(level)){
                gMerchant.setLevel(UtilConst.STR_NUMBER_2);
            }
            if("项目".equals(level)){
                gMerchant.setLevel(UtilConst.STR_NUMBER_3);
            }
            gMerchant.setDelStatus(UtilConst.STR_NUMBER_1);
            gMerchant.setCreateTime(new Date());
            merchantMapper.insert(gMerchant);
        }
        return JsonResult.success(null,"批量导入成功");
    }

    private boolean validateExcel(String filePath) {
        if (filePath == null || !(filePath.matches("^.+\\.(?i)(xls)$") || filePath.matches("^.+\\.(?i)(xlsx)$"))) {
            return false;
        }
        return true;
    }

}
