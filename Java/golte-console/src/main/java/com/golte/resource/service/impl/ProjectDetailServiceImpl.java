package com.golte.resource.service.impl;

import com.auth0.jwt.internal.org.apache.commons.lang3.StringUtils;
import com.github.pagehelper.PageHelper;
import com.golte.common.data.JsonResult;
import com.golte.common.data.PageInfo;
import com.golte.common.exception.ServiceException;
import com.golte.common.util.*;
import com.golte.mapper.*;
import com.golte.mapper.entity.GCity;
import com.golte.mapper.entity.GResourcePoint;
import com.golte.mapper.entity.GResourceProject;
import com.golte.mapper.entity.GResourceProjectDetail;
import com.golte.resource.service.ProjectDetailService;
import com.golte.resource.service.data.*;
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
public class ProjectDetailServiceImpl implements ProjectDetailService{

    @Autowired
    private GResourceProjectDetailMapper resourceProjectDetailMapper;

    @Autowired
    private GResourceProjectMapper resourceProjectMapper;

    @Autowired
    private GResourcePointMapper resourcePointMapper;

    @Autowired
    private GCityMapper cityMapper;

    @Override
    public JsonResult selectResourceProjectDetailList(GetProjectDetailInData inData) throws Exception {
        PageInfo<GetProjectDetailOutData> pageInfo;
        PageHelper.startPage(inData.getPageNum(), inData.getPageSize());
        List<GetProjectDetailOutData> getProjectDetailOutData = resourceProjectDetailMapper.selectProjectDetailListByCondition(inData);
        if (Util.isEmptyList(getProjectDetailOutData)) {
            pageInfo = new PageInfo<>(new ArrayList<GetProjectDetailOutData>());
        } else {
            pageInfo = new PageInfo<>(getProjectDetailOutData);
        }
        return JsonResult.success(pageInfo, UtilMessage.GET_MESSAGE_SUCCESS);

    }

    @Override
    @Transactional
    public JsonResult saveResourceProjectDetail(GetProjectDetailInData inData) throws Exception {

        List<GetProjectDetailInData> rProjectDetailList = inData.getRProjectDetailList();
        for (int i=0;i<rProjectDetailList.size();i++){
            //判断插入数据的有效性
            //判断是否存在相同的资源项目详细
            //资产编号唯一判断
            GetProjectDetailInData projectDetailInData = rProjectDetailList.get(i);
            Example example = new Example(GResourceProjectDetail.class);
            example.createCriteria().andEqualTo("assetsCode", projectDetailInData.getAssetsCode());
            List<GResourceProjectDetail> gResourceProjectDetails = resourceProjectDetailMapper.selectByExample(example);
            if(gResourceProjectDetails.size()>0){
                String existMes = "资产编号:"+ projectDetailInData.getAssetsCode()+"已存在！";
                return JsonResult.fail(UtilConst.CODE_500, existMes);
            }
            //资产名称唯一判断
            example = new Example(GResourceProjectDetail.class);
            example.createCriteria().andEqualTo("assetsName", projectDetailInData.getAssetsName());
            List<GResourceProjectDetail> resourceProjectDetails = resourceProjectDetailMapper.selectByExample(example);
            if(resourceProjectDetails.size()>0){
                String existMes = "资产名称:"+ projectDetailInData.getAssetsName()+"已存在！";
                return JsonResult.fail(UtilConst.CODE_500, existMes);
            }
            int count =0;
            int countName =0;
            for (GetProjectDetailInData projectDetailInData1: rProjectDetailList){
                if (projectDetailInData.getAssetsCode().equals(projectDetailInData1.getAssetsCode())){
                    count++;
                }
                if (projectDetailInData.getAssetsName().equals(projectDetailInData1.getAssetsName())){
                    countName++;
                }
                if (count>1){
                    String existMes = "资产编号:"+ projectDetailInData.getAssetsCode() + "重复！";
                    return JsonResult.fail(UtilConst.CODE_500, existMes);
                }
                if (countName>1){
                    String existMes = "重复数据，资产名称:"+ projectDetailInData.getAssetsName() +"！";
                    return JsonResult.fail(UtilConst.CODE_500, existMes);
                }
            }
        }

        for (int i=0;i<rProjectDetailList.size();i++){
            GResourceProjectDetail gResourceProjectDetail = new GResourceProjectDetail();
            GetProjectDetailInData projectDetailInData = rProjectDetailList.get(i);
            gResourceProjectDetail.setProjectId(projectDetailInData.getProjectId());
            gResourceProjectDetail.setPointId(projectDetailInData.getPointId());
            gResourceProjectDetail.setAssetsCode(projectDetailInData.getAssetsCode());
            gResourceProjectDetail.setAssetsName(projectDetailInData.getAssetsName());
            gResourceProjectDetail.setAssetsUnit(projectDetailInData.getAssetsUnit());
            gResourceProjectDetail.setDelStatus(UtilConst.STR_NUMBER_1);
            gResourceProjectDetail.setCreateName(inData.getLoginAccount());
            gResourceProjectDetail.setCreateTime(new Date());
            resourceProjectDetailMapper.insert(gResourceProjectDetail);
        }
        return JsonResult.success("", UtilMessage.SAVE_MESSAGE_SUCCESS);
    }

    @Override
    @Transactional
    public JsonResult deleteResourceProjectDetail(GetProjectDetailInData inData) throws Exception {
        for (Long id : inData.getIds()){
            List<GetProjectDetailOutData> getProjectDetailOutData = resourceProjectDetailMapper.selectExitsNum(id);
            int number = getProjectDetailOutData.size();
            if(number>0){
                String existMes = "资源编号:"+ getProjectDetailOutData.get(0).getAssetsCode() + "" +",资源名称:"+ getProjectDetailOutData.get(0).getAssetsName() + "存在未到期的合约，不可删除！";
                return JsonResult.fail(UtilConst.CODE_500, existMes);
            }
        }
        Example example = new Example(GResourceProjectDetail.class);
        example.createCriteria().andIn("id", inData.getIds());
        GResourceProjectDetail gResourceProjectDetail = new GResourceProjectDetail();
        gResourceProjectDetail.setDelStatus(UtilConst.STR_NUMBER_0);
        resourceProjectDetailMapper.updateByExampleSelective(gResourceProjectDetail, example);
        return JsonResult.success("", UtilMessage.DEL_MESSAGE_SUCCESS);
    }

    @Override
    @Transactional
    public JsonResult updateResourceProjectDetail(GetProjectDetailInData inData) throws Exception {
        GResourceProjectDetail gResourceProjectDetail = resourceProjectDetailMapper.selectByPrimaryKey(inData.getId());

        //判断插入数据的有效性
        //判断是否存在相同的资源项目详细
        Example example = new Example(GResourceProjectDetail.class);
        example.createCriteria().andEqualTo("assetsCode", inData.getAssetsCode());
        List<GResourceProjectDetail> gResourceProjectDetails = resourceProjectDetailMapper.selectByExample(example);
        if(gResourceProjectDetails.size()>0){
            GResourceProjectDetail gResourceProjectDetail1 = gResourceProjectDetails.get(0);
            //如果Id相同，则为同一个，不同则说明重复
            if(!gResourceProjectDetail1.getId().equals(inData.getId())||gResourceProjectDetails.size()>1){
                String existMes = "提示 ：资产编号:"+ inData.getAssetsCode() + "已存在！";
                return JsonResult.fail(UtilConst.CODE_500, existMes);
            }
        }
        //资产名称唯一判断
        example = new Example(GResourceProjectDetail.class);
        example.createCriteria().andEqualTo("assetsName", inData.getAssetsName());
        List<GResourceProjectDetail> resourceProjectDetails = resourceProjectDetailMapper.selectByExample(example);
        if(resourceProjectDetails.size()>0){
            GResourceProjectDetail gResourceProjectDetail1 = gResourceProjectDetails.get(0);
            //如果Id相同，则为同一个，不同则说明重复
            if(!gResourceProjectDetail1.getId().equals(inData.getId())||resourceProjectDetails.size()>1){
                String existMes = "提示 ：资产名称:"+ inData.getAssetsName()+"已存在！";
                return JsonResult.fail(UtilConst.CODE_500, existMes);
            }
        }

        //更新资源项目 点位项目禁止更新
        gResourceProjectDetail.setAssetsCode(inData.getAssetsCode());
        gResourceProjectDetail.setAssetsName(inData.getAssetsName());
        gResourceProjectDetail.setAssetsUnit(inData.getAssetsUnit());
        gResourceProjectDetail.setUpdateName(inData.getLoginAccount());
        gResourceProjectDetail.setUpdateTime(new Date());
        resourceProjectDetailMapper.updateByPrimaryKeySelective(gResourceProjectDetail);
        return JsonResult.success("", UtilMessage.SAVE_MESSAGE_SUCCESS);
    }

    @Override
    public void exportOut(HttpServletResponse response, GetProjectDetailInData inData) throws Exception {
        List<GetProjectDetailOutData> getProjectDetailOutData = resourceProjectDetailMapper.selectProjectDetailListByCondition(inData);
        List<GetPointExportOutData> outDataList = new ArrayList<>();

        for (GetProjectDetailOutData projectDetailOutData : getProjectDetailOutData){
            GetPointExportOutData pointExportOutData = new GetPointExportOutData();
            pointExportOutData.setCityName(projectDetailOutData.getCityName());
            pointExportOutData.setProjectName(projectDetailOutData.getProjectName());
            pointExportOutData.setZcName(projectDetailOutData.getZcName());
            pointExportOutData.setAssetsCode(projectDetailOutData.getAssetsCode());
            pointExportOutData.setAssetsName(projectDetailOutData.getAssetsName());
            pointExportOutData.setMerchantName(projectDetailOutData.getMerchantName());
            pointExportOutData.setContractName(projectDetailOutData.getContractName());
            pointExportOutData.setAmount(projectDetailOutData.getAmount());
            outDataList.add(pointExportOutData);
        }

        String excelName = "资源项目详情";
        String[] headers = {"城市", "项目", "资产类型", "资源编号", "资源名称", "合作商", "合同名称", "合同单价(元)"};
        ExportExcel<GetPointExportOutData> excelUtil = new ExportExcel<GetPointExportOutData>();
        response.setContentType("application/vnd.ms-excel; charset=utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + new String((excelName).getBytes(), "iso-8859-1") + ".xls");
        response.setCharacterEncoding("UTF-8");
        OutputStream outputStream = response.getOutputStream();
        excelUtil.exportExcel(headers, outDataList, outputStream, null);

    }

    @Override
    @Transactional
    public JsonResult exportIn(List<GetPointExportInData> inData,String loginName) throws Exception {
        if (Util.isEmptyList(inData)) {
            return JsonResult.fail(UtilConst.CODE_403, "需要导入的资源项目明细为空");
        }
        for (GetPointExportInData pointExportInData:inData) {
            if(Util.isEmpty(pointExportInData.getCityName())){
                return JsonResult.fail(UtilConst.CODE_403,"第"+pointExportInData.getIndex()+"行的城市不能为空");
            }
            if(Util.isEmpty(pointExportInData.getProjectName())){
                return JsonResult.fail(UtilConst.CODE_403,"第"+pointExportInData.getIndex()+"行的项目不能为空");
            }
            if(Util.isEmpty(pointExportInData.getFirstPointName())){
                return JsonResult.fail(UtilConst.CODE_403,"第"+pointExportInData.getIndex()+"行的一级目录不能为空");
            }else {
                if(pointExportInData.getFirstPointName().length()>30){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+pointExportInData.getIndex()+"行的一级目录内容过长");
                }
            }
            if(Util.isEmpty(pointExportInData.getSecondPointName())){
                return JsonResult.fail(UtilConst.CODE_403,"第"+pointExportInData.getIndex()+"行的二级目录不能为空");
            }else {
                if(pointExportInData.getSecondPointName().length()>30){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+pointExportInData.getIndex()+"行的二级目录内容过长");
                }
            }
            if(Util.isEmpty(pointExportInData.getThirdPointName())){
                return JsonResult.fail(UtilConst.CODE_403,"第"+pointExportInData.getIndex()+"行的三级目录不能为空");
            }else {
                if(pointExportInData.getThirdPointName().length()>30){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+pointExportInData.getIndex()+"行的三级目录内容过长");
                }
            }
            if(Util.isEmpty(pointExportInData.getAssetsName())){
                return JsonResult.fail(UtilConst.CODE_403,"第"+pointExportInData.getIndex()+"行的资产名称不能为空");
            }else {
                if(pointExportInData.getAssetsName().length()>30){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+pointExportInData.getIndex()+"行的资产名称内容过长");
                }
            }
            if(Util.isEmpty(pointExportInData.getAssetsCode())){
                return JsonResult.fail(UtilConst.CODE_403,"第"+pointExportInData.getIndex()+"行的资产编号不能为空");
            }else {
                if(pointExportInData.getAssetsCode().length()>30){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+pointExportInData.getIndex()+"行的资产编号内容过长");
                }
            }
            if(Util.isEmpty(pointExportInData.getAssetsUnit())){
                return JsonResult.fail(UtilConst.CODE_403,"第"+pointExportInData.getIndex()+"行的规格不能为空");
            }else{
                if(pointExportInData.getAssetsUnit().length()>30){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+pointExportInData.getIndex()+"行的规格内容过长");
                }
            }
        }

        for (int i=0;i<inData.size();i++){
            //判断插入数据的有效性
            //判断是否存在相同的资源项目详细
            GetPointExportInData pointExportInData = inData.get(i);
            Example example = new Example(GResourceProjectDetail.class);
            example.createCriteria().andEqualTo("assetsCode", pointExportInData.getAssetsCode()).andEqualTo("delStatus", UtilConst.STR_NUMBER_1);
            List<GResourceProjectDetail> gResourceProjectDetails = resourceProjectDetailMapper.selectByExample(example);
            if(gResourceProjectDetails.size()>0){
                String existMes = "资产编号:"+ pointExportInData.getAssetsCode() + "已存在！";
                return JsonResult.fail(UtilConst.CODE_500, existMes);
            }

            //资产名称唯一判断
            example = new Example(GResourceProjectDetail.class);
            example.createCriteria().andEqualTo("assetsName", pointExportInData.getAssetsName()).andEqualTo("delStatus", UtilConst.STR_NUMBER_1);
            List<GResourceProjectDetail> resourceProjectDetails = resourceProjectDetailMapper.selectByExample(example);
            if(resourceProjectDetails.size()>0){
                String existMes = "资产名称:"+ pointExportInData.getAssetsName()+"已存在！";
                return JsonResult.fail(UtilConst.CODE_500, existMes);
            }

            int countCode =0;
            int countName =0;
            for (GetPointExportInData pointExportIn: inData){
                if (pointExportInData.getAssetsCode().equals(pointExportIn.getAssetsCode())){
                    countCode++;
                }
                if (pointExportInData.getAssetsName().equals(pointExportIn.getAssetsName())){
                    countName++;
                }
                if (countCode>1){
                    String existMes = "重复数据，资产编号:"+ pointExportInData.getAssetsCode() +"！";
                    return JsonResult.fail(UtilConst.CODE_500, existMes);
                }
                if (countName>1){
                    String existMes = "重复数据，资产名称:"+ pointExportInData.getAssetsName() +"！";
                    return JsonResult.fail(UtilConst.CODE_500, existMes);
                }
            }
            example = new Example(GCity.class);
            example.createCriteria().andEqualTo("cityName",pointExportInData.getCityName()).andEqualTo("delStatus", UtilConst.STR_NUMBER_1);
            List<GCity> gCities = cityMapper.selectByExample(example);
            if(gCities.size()<=0){
                String existCityMes = "城市名称:"+ pointExportInData.getCityName() +"不存在！";
                return JsonResult.fail(UtilConst.CODE_500, existCityMes);
            }
        }

        for(GetPointExportInData pointExportInData:inData){

            //查询对应城市的项目是否存在
            GResourceProject projectOutData = resourceProjectMapper.selectExistProjectList(pointExportInData);
            //项目，如果项目表不存在改项目，则新增该项目
            if(Util.isEmpty(projectOutData)){
                Example example = new Example(GCity.class);
                example.createCriteria().andEqualTo("cityName",pointExportInData.getCityName()).andEqualTo("delStatus", UtilConst.STR_NUMBER_1);
                List<GCity> gCities = cityMapper.selectByExample(example);
                GCity city = gCities.get(0);
                projectOutData = new GResourceProject();
                projectOutData.setCentralCityId(city.getCentralCityId());
                projectOutData.setCityId(city.getId());
                projectOutData.setProjectName(pointExportInData.getProjectName());
                projectOutData.setDelStatus(UtilConst.STR_NUMBER_1);
                projectOutData.setCreateTime(new Date());
                projectOutData.setCreateName(loginName);
                resourceProjectMapper.insert(projectOutData);
            }

            //组装一级资源点位信息
            Example example = new Example(GResourcePoint.class);
            example.createCriteria().andEqualTo("name",pointExportInData.getFirstPointName())
                    .andEqualTo("delStatus",UtilConst.STR_NUMBER_1)
                    .andEqualTo("level","1");
            List<GResourcePoint> gResourcePoints = resourcePointMapper.selectByExample(example);
            GResourcePoint pointData = null;
            if(gResourcePoints.size()>0){
                pointData = gResourcePoints.get(0);
            }else{
                pointData = getPointData(pointExportInData.getFirstPointName(), 0L, 1);
                resourcePointMapper.insert(pointData);
            }

            //组装二级资源点位信息
            example = getExampleData(pointExportInData.getSecondPointName(), UtilConst.STR_NUMBER_2,pointData.getId());
            List<GResourcePoint> secondResourcePoints = resourcePointMapper.selectByExample(example);
            GResourcePoint secondResource = null;
            if(secondResourcePoints.size()>0){
                secondResource = secondResourcePoints.get(0);
            }else{
                secondResource = getPointData(pointExportInData.getSecondPointName(), pointData.getId(), 2);
                resourcePointMapper.insert(secondResource);
            }

            //组装三级资源点位信息
            example = getExampleData(pointExportInData.getThirdPointName(), UtilConst.STR_NUMBER_3,secondResource.getId());
            List<GResourcePoint> thirdResourcePoints = resourcePointMapper.selectByExample(example);
            GResourcePoint thirdPoint = null;
            if(thirdResourcePoints.size()>0){
                thirdPoint = thirdResourcePoints.get(0);
            }else{
                thirdPoint = getPointData(pointExportInData.getThirdPointName(), secondResource.getId(), 3);
                resourcePointMapper.insert(thirdPoint);
            }

            //判断是否存在相同的资源名称和资源编号

            GResourceProjectDetail gResourceProjectDetail = new GResourceProjectDetail();
            gResourceProjectDetail.setProjectId(projectOutData.getId());
            gResourceProjectDetail.setPointId(thirdPoint.getId());
            gResourceProjectDetail.setAssetsCode(pointExportInData.getAssetsCode());
            gResourceProjectDetail.setAssetsName(pointExportInData.getAssetsName());
            gResourceProjectDetail.setAssetsUnit(pointExportInData.getAssetsUnit());
            gResourceProjectDetail.setDelStatus(UtilConst.STR_NUMBER_1);
            gResourceProjectDetail.setCreateTime(new Date());
            resourceProjectDetailMapper.insert(gResourceProjectDetail);

        }

        return JsonResult.success(null,"批量导入成功");
    }

    public GResourcePoint getPointData(String pointName,Long parentId,int level){
        GResourcePoint gResourcePoint = new GResourcePoint();
        gResourcePoint.setName(pointName);
        gResourcePoint.setParentId(parentId);
        gResourcePoint.setDelStatus(UtilConst.STR_NUMBER_1);
        gResourcePoint.setLevel(level);
        gResourcePoint.setCreateTime(new Date());
        return  gResourcePoint;
    }

    public Example getExampleData(String pointName,String level,Long parentId){
        Example example = new Example(GResourcePoint.class);
        example.createCriteria().andEqualTo("name",pointName)
                .andEqualTo("delStatus",UtilConst.STR_NUMBER_1)
                .andEqualTo("parentId",parentId)
                .andEqualTo("level",level);

        return  example;
    }


}
