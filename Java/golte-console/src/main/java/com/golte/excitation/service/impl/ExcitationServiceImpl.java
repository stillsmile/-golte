package com.golte.excitation.service.impl;

import com.auth0.jwt.internal.org.apache.commons.lang3.StringUtils;
import com.github.pagehelper.PageHelper;
import com.golte.common.data.JsonResult;
import com.golte.common.data.PageInfo;
import com.golte.common.exception.ServiceException;
import com.golte.common.util.*;
import com.golte.excitation.service.ExcitationService;
import com.golte.excitation.service.data.*;
import com.golte.mapper.*;
import com.golte.mapper.entity.GConfigure;
import com.golte.mapper.entity.GContractProject;
import com.golte.mapper.entity.GExcitation;
import com.golte.mapper.entity.GResourceProject;
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
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ExcitationServiceImpl implements ExcitationService {

    @Autowired
    private GExcitationMapper excitationMapper;
    
    @Autowired
    private GContractProjectMapper contractProjectMapper;

    @Autowired
    private GResourceProjectMapper resourceProjectMapper;

    @Autowired
    private GConfigureMapper gConfigureMapper;


    @Override
    public JsonResult selectListByCondition(ExcitationInData inData) throws Exception {
        PageInfo<ExcitationOutData> pageInfo;
        PageHelper.startPage(inData.getPageNum(), inData.getPageSize());

        List<ExcitationOutData> excitationOutData = excitationMapper.selectListByCondition(inData);

        if (Util.isEmptyList(excitationOutData)) {
            pageInfo = new PageInfo<>(new ArrayList<ExcitationOutData>());
        } else {
            pageInfo = new PageInfo<>(excitationOutData);
        }
        return JsonResult.success(pageInfo, UtilMessage.GET_MESSAGE_SUCCESS);
    }

    @Override
    public JsonResult selectRoleList(ExcitationInData inData) throws Exception {
        Example example = new Example(GConfigure.class);
        example.createCriteria().andEqualTo("cogKey","TYPE")
                .andEqualTo("cogValue",inData.getContractType());
        List<String> roleIds = new ArrayList<>();
        List<GConfigure> gConfigures = gConfigureMapper.selectByExample(example);
        if(gConfigures.size()==0){
            return JsonResult.fail(UtilConst.CODE_500, UtilMessage.ROLE_NAME_ERROR);
        }
        GConfigure gConfigure = gConfigures.get(0);
        String[] ids = gConfigure.getCogName().split(",");
        String[] rates = gConfigure.getCogExtendOne().split(",");
        //组装Id集合
        for (String id : ids){
            roleIds.add(id);
        }

        example = new Example(GConfigure.class);
        example.createCriteria().andEqualTo("cogKey","ROLENAME").andIn("cogValue",roleIds);
        List<GConfigure> gConfigures1 = gConfigureMapper.selectByExample(example);
        for(GConfigure configure:gConfigures1){
            for (int i=0;i<ids.length;i++){
                if(configure.getCogValue().equals(ids[i])){
                    configure.setCogExtendOne(rates[i]);
                }
            }
        }
        return JsonResult.success(gConfigures1, UtilMessage.GET_MESSAGE_SUCCESS);
    }

    @Override
    @Transactional
    public JsonResult saveExcitation(ExcitationInData inData) throws Exception {

        GExcitation excitation = new GExcitation();
        excitation.setStatus(inData.getStatus());
        excitation.setType(inData.getType());
        excitation.setContractType(inData.getContractType());
        excitation.setProjectId(inData.getProjectId());
        excitation.setPropertyProfit(inData.getPropertyProfit());
        excitation.setTaxPercentage(inData.getTaxPercentage()==null?Double.parseDouble("0"):inData.getTaxPercentage());
        excitation.setTax(inData.getTax()==null?new BigDecimal("0"):inData.getTax());
        excitation.setMonth(inData.getMonth());//可以为年度也可为月度
        excitation.setMemberName(inData.getMemberName());
        //激励金额 == 实际激励金额（月度对应的就是实际激励金额，年度的除此还有一个应激励金额）
        excitation.setActualAmount(inData.getActualAmount()==null?new BigDecimal("0"):inData.getActualAmount());
        excitation.setExcitationPercentage(inData.getExcitationPercentage()==null?Double.parseDouble("0"):inData.getExcitationPercentage());
        excitation.setCardinalNumber(inData.getCardinalNumber());
        //激励类型（1月度 2年度）
        if("2".equals(inData.getType())){
            excitation.setManagementAmount(inData.getManagementAmount()==null?new BigDecimal("0"):inData.getManagementAmount());
            excitation.setOtherCost(inData.getOtherCost());
            excitation.setProfitIndex(inData.getProfitIndex());
            excitation.setRoleId(inData.getRoleId());
            excitation.setShouldAmount(inData.getShouldAmount());
            excitation.setArrivalConversion(inData.getArrivalConversion());
            excitation.setOtherDeduction(inData.getOtherDeduction());
        }
        excitation.setStatus(inData.getStatus());
        excitation.setCreateName(inData.getLoginAccount());
        excitation.setCreateTime(new Date());
        excitation.setDelStatus(UtilConst.STR_NUMBER_1);
        excitationMapper.insert(excitation);
        return JsonResult.success("", UtilMessage.SAVE_MESSAGE_SUCCESS);
    }


    @Override
    @Transactional
    public JsonResult deleteExcitation(ExcitationInData inData) throws Exception {
        //判断数据是否可删除
        List<Long> ids = inData.getIds();
        for (Long id : ids) {
            Example example = new Example(GExcitation.class);
            example.createCriteria().andEqualTo("id",id);
            List<GExcitation> gExcitations = excitationMapper.selectByExample(example);
            if (gExcitations.size()>0){
                GExcitation excitation = gExcitations.get(0);
                String status = excitation.getStatus();
                //模板状态 0正式 1草稿
                if("0".equals(status)){
                    return JsonResult.fail(UtilConst.CODE_500, UtilMessage.EXIXATION_DELETE_ERROR);
                }
            }
        }
        Example example = new Example(GExcitation.class);
        example.createCriteria().andIn("id", inData.getIds());
        GExcitation excitation = new GExcitation();
        excitation.setDelStatus(UtilConst.STR_NUMBER_0);
        excitationMapper.updateByExampleSelective(excitation, example);
        return JsonResult.success("", UtilMessage.DEL_MESSAGE_SUCCESS);
    }

    @Override
    @Transactional
    public JsonResult updateExcitation(ExcitationInData inData) throws Exception {
        GExcitation excitation = excitationMapper.selectByPrimaryKey(inData.getId());
        excitation.setType(inData.getType());
        excitation.setContractType(inData.getContractType());
        excitation.setProjectId(inData.getProjectId());
        excitation.setPropertyProfit(inData.getPropertyProfit());
        excitation.setTaxPercentage(inData.getTaxPercentage());
        excitation.setTax(inData.getTax());
        excitation.setMonth(inData.getMonth());//可以为年度也可为月度
        excitation.setMemberName(inData.getMemberName());
        //激励金额 == 实际激励金额（月度对应的就是实际激励金额，年度的除此还有一个应激励金额）
        excitation.setActualAmount(inData.getActualAmount());
        excitation.setExcitationPercentage(inData.getExcitationPercentage());
        excitation.setCardinalNumber(inData.getCardinalNumber());
        if("2".equals(inData.getType())){//1是月度   2--年度
            excitation.setManagementAmount(inData.getManagementAmount());
            excitation.setOtherCost(inData.getOtherCost());
            excitation.setProfitIndex(inData.getProfitIndex());
            excitation.setRoleId(inData.getRoleId());
            excitation.setShouldAmount(inData.getShouldAmount());
            excitation.setArrivalConversion(inData.getArrivalConversion());
            excitation.setOtherDeduction(inData.getOtherDeduction());
        }
        excitation.setStatus(inData.getStatus());
        excitation.setUpdateName(inData.getLoginAccount());
        excitation.setUpdateTime(new Date());
        excitationMapper.updateByPrimaryKeySelective(excitation);
        return JsonResult.success("", UtilMessage.SAVE_MESSAGE_SUCCESS);
    }


    @Override
    public void exportOut(HttpServletResponse response, ExcitationInData inData) throws Exception {
        List<ExcitationOutData> excitationOutData = excitationMapper.selectListByCondition(inData);
        List<GetExcitationExportOutData> outDataList = new ArrayList<>();

        for (ExcitationOutData outData:excitationOutData){
            GetExcitationExportOutData excitationExportOutData = new GetExcitationExportOutData();
            excitationExportOutData.setPaybackamount(outData.getPaybackAmount());
            if("1".equals(outData.getContractType())){
                excitationExportOutData.setContractType("广告资源");
            }
            if("2".equals(outData.getContractType())){
                excitationExportOutData.setContractType("场地资源");
            }
            if("3".equals(outData.getContractType())){
                excitationExportOutData.setContractType("临时摆展");
            }
            if("4".equals(outData.getContractType())){
                excitationExportOutData.setContractType("服务产品");
            }
            if("5".equals(outData.getContractType())){
                excitationExportOutData.setContractType("其他");
            }
            if("2,5".equals(outData.getContractType())){
                excitationExportOutData.setContractType("管理收入");
            }
            excitationExportOutData.setProjectName(outData.getProjectName());
            excitationExportOutData.setCityName(outData.getCityName());

            //奖励类型（1月度 2年度）--->激励类型
            if ("1".equals(outData.getType())){
                excitationExportOutData.setType("月度");
            }
            if ("2".equals(outData.getType())){
                excitationExportOutData.setType("年度");
            }
            excitationExportOutData.setMemberName(outData.getMemberName());
            excitationExportOutData.setPropertyProfit(outData.getPropertyProfit());
            excitationExportOutData.setActualAmount(outData.getActualAmount());
            outDataList.add(excitationExportOutData);
        }
        String excelName = "激励";
        String[] headers = {"已收回款", "激励类别", "所在项目", "所在城市", "激励类型", "激励人员", "利润金额", "激励金额"};
        ExportExcel<GetExcitationExportOutData> excelUtil = new ExportExcel<GetExcitationExportOutData>();
        response.setContentType("application/vnd.ms-excel; charset=utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + new String((excelName).getBytes(), "iso-8859-1") + ".xls");
        response.setCharacterEncoding("UTF-8");
        OutputStream outputStream = response.getOutputStream();
        excelUtil.exportExcel(headers, outDataList, outputStream, null);
    }

    @Override
    @Transactional
    public JsonResult exportYearIn(List<GetExcitationYearExportInData> fileYearDataList) throws Exception {
        if (Util.isEmptyList(fileYearDataList)) {
            return JsonResult.fail(UtilConst.CODE_403, "需要导入的年度激励为空");
        }
        for(GetExcitationYearExportInData merchantExportInData:fileYearDataList){
            if(Util.isEmpty(merchantExportInData.getContractType())){
                return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的激励类别不能为空");
            }
            if(Util.isEmpty(merchantExportInData.getProjectName())){
                return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的项目名称不能为空");
            }
            if(!Util.isEmpty(merchantExportInData.getPropertyProfit())){
                boolean numeric = isNumeric(merchantExportInData.getPropertyProfit());
                if(!numeric){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的物业利润收入不合法");
                }
                if(new BigDecimal(merchantExportInData.getPropertyProfit()).compareTo(new BigDecimal(99999999))>0){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的物业利润收入过大");
                }
                BigDecimal bigDecimal = new BigDecimal(merchantExportInData.getPropertyProfit()).setScale(2, BigDecimal.ROUND_HALF_UP);
                if(!isNumber(bigDecimal.toString())){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的物业利润收入不合法");
                }
            }else{
                return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的物业利润收入不能为空");
            }
            if(!Util.isEmpty(merchantExportInData.getTaxPercentage())){
                boolean numeric = isNumeric(merchantExportInData.getTaxPercentage());
                if(!numeric){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的税金百分比不合法");
                }
                if(new BigDecimal(merchantExportInData.getTaxPercentage()).compareTo(new BigDecimal(1))>0){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的税金百分比为0到1");
                }
            }else{
                return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的税金百分比不能为空");
            }
            if(!Util.isEmpty(merchantExportInData.getTax())){
                boolean numeric = isNumeric(merchantExportInData.getTax());
                if(!numeric){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的税金不合法");
                }
                if(new BigDecimal(merchantExportInData.getTax()).compareTo(new BigDecimal(99999999))>0){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的税金过大");
                }
                BigDecimal bigDecimal = new BigDecimal(merchantExportInData.getTax()).setScale(2, BigDecimal.ROUND_HALF_UP);
                if(!isNumber(bigDecimal.toString())){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的税金不合法");
                }
            }else{
                return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的税金不能为空");
            }
            if(!Util.isEmpty(merchantExportInData.getManagementAmount())){
                boolean numeric = isNumeric(merchantExportInData.getManagementAmount());
                if(!numeric){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的管理酬金不合法");
                }
                if(new BigDecimal(merchantExportInData.getManagementAmount()).compareTo(new BigDecimal(99999999))>0){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的管理酬金过大");
                }
                BigDecimal bigDecimal = new BigDecimal(merchantExportInData.getManagementAmount()).setScale(2, BigDecimal.ROUND_HALF_UP);
                if(!isNumber(bigDecimal.toString())){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的管理酬金不合法");
                }
            }else{
                return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的管理酬金不能为空");
            }
            if(!Util.isEmpty(merchantExportInData.getOtherCost())){
                boolean numeric = isNumeric(merchantExportInData.getOtherCost());
                if(!numeric){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的其他成本不合法");
                }
                if(new BigDecimal(merchantExportInData.getOtherCost()).abs().compareTo(new BigDecimal(9999999))>0){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的其他成本过大");
                }
                BigDecimal bigDecimal = new BigDecimal(merchantExportInData.getOtherCost()).setScale(2, BigDecimal.ROUND_HALF_UP);
                if(!isNumber(bigDecimal.toString())){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的其他成本不合法");
                }
            }else{
                return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的其他成本不能为空");
            }
            if(!Util.isEmpty(merchantExportInData.getProfitIndex())){
                boolean numeric = isNumeric(merchantExportInData.getProfitIndex());
                if(!numeric){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的年初利润指标不合法");
                }
                if(new BigDecimal(merchantExportInData.getProfitIndex()).compareTo(new BigDecimal(99999999))>0){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的年初利润指标过大");
                }
                BigDecimal bigDecimal = new BigDecimal(merchantExportInData.getProfitIndex()).setScale(2, BigDecimal.ROUND_HALF_UP);
                if(!isNumber(bigDecimal.toString())){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的年初利润指标不合法");
                }
            }else{
                return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的年初利润指标不能为空");
            }
            if(!Util.isEmpty(merchantExportInData.getCardinalNumber())){
                boolean numeric = isNumeric(merchantExportInData.getCardinalNumber());
                if(!numeric){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的激励基数不合法");
                }
                if(new BigDecimal(merchantExportInData.getCardinalNumber()).compareTo(new BigDecimal(99999999))>0){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的激励基数过大");
                }
                BigDecimal bigDecimal = new BigDecimal(merchantExportInData.getCardinalNumber()).setScale(2, BigDecimal.ROUND_HALF_UP);
                if(!isNumber(bigDecimal.abs().toString())){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的激励基数不合法");
                }
            }else{
                return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的激励基数不能为空");
            }
            if(Util.isEmpty(merchantExportInData.getRoleName())){
                return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的激励角色不能为空");
            }
            if(!Util.isEmpty(merchantExportInData.getExcitationPercentage())){
                boolean numeric = isNumeric(merchantExportInData.getExcitationPercentage());
                if(!numeric){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的激励百分比不合法");
                }
                if(new BigDecimal(merchantExportInData.getExcitationPercentage()).compareTo(new BigDecimal(1))>0){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的激励百分比为0到1之间");
                }
            }else {
                return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的激励百分比不能为空");
            }
            if(!Util.isEmpty(merchantExportInData.getShouldAmount())){
                boolean numeric = isNumeric(merchantExportInData.getShouldAmount());
                if(!numeric){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的应激励金额不合法");
                }
                if(new BigDecimal(merchantExportInData.getShouldAmount()).compareTo(new BigDecimal(99999999))>0){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的应激励金额过大");
                }
                BigDecimal bigDecimal = new BigDecimal(merchantExportInData.getShouldAmount()).setScale(2, BigDecimal.ROUND_HALF_UP);
                if(!isNumber(bigDecimal.abs().toString())){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的应激励金额不合法");
                }
            }else{
                return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的应激励金额不能为空");
            }
            if(!Util.isEmpty(merchantExportInData.getArrivalConversion())){
                boolean numeric = isNumeric(merchantExportInData.getArrivalConversion());
                if(!numeric){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的到岗折算不合法");
                }
                if(new BigDecimal(merchantExportInData.getArrivalConversion()).compareTo(new BigDecimal(999))>0){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的到岗折算过大");
                }
                BigDecimal bigDecimal = new BigDecimal(merchantExportInData.getArrivalConversion()).setScale(2, BigDecimal.ROUND_HALF_UP);
                if(!isNumber(bigDecimal.abs().toString())){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的到岗折算不合法");
                }
            }else{
                return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的到岗折算不能为空");
            }
            if(!Util.isEmpty(merchantExportInData.getOtherDeduction())){
                boolean numeric = isNumeric(merchantExportInData.getOtherDeduction());
                if(!numeric){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的其他扣除不合法");
                }
                if(new BigDecimal(merchantExportInData.getOtherDeduction()).compareTo(new BigDecimal(9999999))>0){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的其他扣除过大");
                }
                BigDecimal bigDecimal = new BigDecimal(merchantExportInData.getOtherDeduction()).setScale(2, BigDecimal.ROUND_HALF_UP);
                if(!isNumber(bigDecimal.toString())){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的其他扣除不合法");
                }
            }else{
                return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的其他扣除不能为空");
            }
            if(!Util.isEmpty(merchantExportInData.getActualAmount())){
                boolean numeric = isNumeric(merchantExportInData.getActualAmount());
                if(!numeric){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的实际激励金额不合法");
                }
                if(new BigDecimal(merchantExportInData.getActualAmount()).compareTo(new BigDecimal(999999999))>0){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的实际激励金额过大");
                }
                BigDecimal bigDecimal = new BigDecimal(merchantExportInData.getActualAmount()).setScale(2, BigDecimal.ROUND_HALF_UP);
                if(!isNumber(bigDecimal.abs().toString())){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的实际激励金额不合法");
                }
            }else{
                return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的实际激励金额不能为空");
            }
            if(Util.isEmpty(merchantExportInData.getMemberName())){
                return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的激励人员不能为空");
            }else {
                if(merchantExportInData.getMemberName().length()>30){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的激励人员长度过大");
                }
            }
            if(Util.isEmpty(merchantExportInData.getYmonth())){
                return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的激励年份不能为空");
            }
        }

        boolean flag = true;
        String projectNames = "";
        String contractNames = "";
        List errorList = new ArrayList();
        //验证数据有效性
        //组装导入数据
        for(GetExcitationYearExportInData exportInData:fileYearDataList){
            //查询资源项目是否存在
            Example exampleProject = new Example(GResourceProject.class);
            exampleProject.createCriteria().andEqualTo("projectName",exportInData.getProjectName())
                    .andEqualTo("delStatus",UtilConst.STR_NUMBER_1);
            List<GResourceProject> projectList = resourceProjectMapper.selectByExample(exampleProject);
            if(projectList.size()<=0){
                flag = false;
                String errorMes = "项目名称："+ exportInData.getProjectName() +"不存在";
                errorList.add(errorMes);
                continue;
            }

            //查询对应的激励角色是否存在
            Example example = new Example(GConfigure.class);
            example.createCriteria().andEqualTo("cogKey","ROLENAME")
                    .andEqualTo("cogName",exportInData.getRoleName());
            List<GConfigure> gConfigures = gConfigureMapper.selectByExample(example);
            if(gConfigures.size()<=0){
                flag = false;
                String errorMes = "激励角色{" + exportInData.getRoleName() + "}不存在";
                errorList.add(errorMes);
                continue;
            }

            boolean excitationType = true;
            String contractType = exportInData.getContractType();
            //管理收入类--》对应合同类别里是场地类和其他类
            //1广告资源 2场地资源 3临时摆展 4服务产 5其他 2,5管理收入
            if("广告资源类".equals(contractType)){
                excitationType =false;
            }
            if("管理收入类".equals(contractType)){
                excitationType =false;
            }
            if("临时摆展类".equals(contractType)){
                excitationType =false;
            }
            if("服务产品类".equals(contractType)){
                excitationType =false;
            }
            if("管理收入类".equals(contractType)){
                excitationType =false;
            }
            if(excitationType){
                flag = false;
                String errorMes = "不存在的激励类型 ：" + contractType;
                errorList.add(errorMes);
                continue;
            }
        }
        if(!flag){
            return JsonResult.fail(UtilConst.CODE_403,errorList.toString());
        }

        //组装导入数据
        for(GetExcitationYearExportInData exportInData:fileYearDataList){

            //查询资源项目是否存在
            Example exampleProject = new Example(GResourceProject.class);
            exampleProject.createCriteria().andEqualTo("projectName",exportInData.getProjectName())
                    .andEqualTo("delStatus",UtilConst.STR_NUMBER_1);
            List<GResourceProject> projectList = resourceProjectMapper.selectByExample(exampleProject);
            GResourceProject resourceProject = projectList.get(0);

            //查询对应的激励角色是否存在
            Example example = new Example(GConfigure.class);
            example.createCriteria().andEqualTo("cogKey","ROLENAME")
                    .andEqualTo("cogName",exportInData.getRoleName());
            List<GConfigure> gConfigures = gConfigureMapper.selectByExample(example);
            GConfigure configure = gConfigures.get(0);

            GExcitation excitation = new GExcitation();
            excitation.setType(UtilConst.STR_NUMBER_2);//1--月度   2---年度
            //1广告资源 2场地资源 3临时摆展 4服务产 5其他
            //管理收入类--》对应合同类别里是场地类和其他类
            String contractType = exportInData.getContractType();
            if("广告资源类".equals(contractType)){
                excitation.setContractType("1");
            }
            if("场地资源类".equals(contractType)){
                excitation.setContractType("2");
            }
            if("临时摆展类".equals(contractType)){
                excitation.setContractType("3");
            }
            if("服务产品类".equals(contractType)){
                excitation.setContractType("4");
            }

            if("管理收入类".equals(contractType)){
                excitation.setContractType("2,5");
            }
            excitation.setProjectId(resourceProject.getId());
            excitation.setPropertyProfit(new BigDecimal(exportInData.getPropertyProfit()).setScale(2, BigDecimal.ROUND_HALF_UP));
            excitation.setTax(new BigDecimal(exportInData.getTax()).setScale(2, BigDecimal.ROUND_HALF_UP));
            excitation.setManagementAmount(new BigDecimal(exportInData.getManagementAmount()).setScale(2, BigDecimal.ROUND_HALF_UP));
            excitation.setOtherCost(new BigDecimal(exportInData.getOtherCost()));
            DecimalFormat format = new DecimalFormat("0.00");
            String cardinalNumber = format.format(new BigDecimal(exportInData.getCardinalNumber()));
            excitation.setCardinalNumber(cardinalNumber);
            excitation.setRoleId(Long.parseLong(configure.getCogValue()));
            excitation.setProfitIndex(new BigDecimal(exportInData.getProfitIndex()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
            BigDecimal multiply = new BigDecimal(exportInData.getExcitationPercentage()).multiply(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_UP);
            excitation.setExcitationPercentage(Double.parseDouble(multiply.toString()));
            excitation.setShouldAmount(new BigDecimal(exportInData.getShouldAmount()).setScale(2, BigDecimal.ROUND_HALF_UP));
            excitation.setArrivalConversion(new BigDecimal(exportInData.getArrivalConversion()).setScale(2, BigDecimal.ROUND_HALF_UP));
            excitation.setOtherDeduction(new BigDecimal(exportInData.getOtherDeduction()));
            excitation.setActualAmount(new BigDecimal(exportInData.getActualAmount()).setScale(2, BigDecimal.ROUND_HALF_UP));
            excitation.setMemberName(exportInData.getMemberName().replace("/",""));
            excitation.setMonth(exportInData.getYmonth());
            String taxPercentage = exportInData.getTaxPercentage();
            BigDecimal bigDecimal = new BigDecimal(taxPercentage).multiply(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_UP);
            excitation.setTaxPercentage(Double.parseDouble(bigDecimal.toString()));
            excitation.setCreateTime(new Date());
            excitation.setDelStatus(UtilConst.STR_NUMBER_1);
            excitation.setStatus("1");
            excitationMapper.insert(excitation);
        }

        if(!flag){
            return JsonResult.fail(UtilConst.CODE_403,"项目："+projectNames +"不存在！ "+contractNames+" 对应的合同不存在！");
        }else {
            return JsonResult.success(null,"批量导入成功");
        }
    }

    @Override
    @Transactional
    public JsonResult exportIn(List<GetExcitationExportInData> fileDataList) throws Exception {
        if (Util.isEmptyList(fileDataList)) {
            return JsonResult.fail(UtilConst.CODE_403, "需要导入的月度激励为空");
        }

        for(GetExcitationExportInData merchantExportInData:fileDataList){
            if(Util.isEmpty(merchantExportInData.getContractType())){
                return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的激励类别不能为空");
            }
            if(Util.isEmpty(merchantExportInData.getProjectName())){
                return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的项目名称不能为空");
            }
            if(!Util.isEmpty(merchantExportInData.getPropertyProfit())){
                boolean numeric = isNumeric(merchantExportInData.getPropertyProfit());
                if(!numeric){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的物业利润收入不合法");
                }
                if(new BigDecimal(merchantExportInData.getPropertyProfit()).compareTo(new BigDecimal(999999999))>0){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的物业利润收入过大");
                }
                BigDecimal bigDecimal = new BigDecimal(merchantExportInData.getPropertyProfit()).setScale(2, BigDecimal.ROUND_HALF_UP);
                if(!isNumber(bigDecimal.toString())){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的物业利润收入不合法");
                }
            }else{
                return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的物业利润收入不能为空");
            }

            if(!Util.isEmpty(merchantExportInData.getTaxPercentage())){
                boolean numeric = isNumeric(merchantExportInData.getTaxPercentage());
                if(!numeric){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的税金百分比不合法");
                }
                if(new BigDecimal(merchantExportInData.getTaxPercentage()).compareTo(new BigDecimal(1))>0){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的税金百分比为0到1之间");
                }
            }else{
                return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的税金百分比不能为空");
            }
            if(!Util.isEmpty(merchantExportInData.getTax())){
                boolean numeric = isNumeric(merchantExportInData.getTax());
                if(!numeric){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的税金不合法");
                }
                if(new BigDecimal(merchantExportInData.getTax()).compareTo(new BigDecimal(999999999))>0){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的税金过大");
                }
                BigDecimal bigDecimal = new BigDecimal(merchantExportInData.getTax()).setScale(2, BigDecimal.ROUND_HALF_UP);
                if(!isNumber(bigDecimal.toString())){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的税金不合法");
                }
            }else{
                return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的税金不能为空");
            }
            if(!Util.isEmpty(merchantExportInData.getCardinalNumber())){
                boolean numeric = isNumeric(merchantExportInData.getCardinalNumber());
                if(!numeric){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的激励基数不合法");
                }
                if(new BigDecimal(merchantExportInData.getCardinalNumber()).compareTo(new BigDecimal(999999999))>0){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的激励基数过大");
                }
                BigDecimal bigDecimal = new BigDecimal(merchantExportInData.getCardinalNumber()).setScale(2, BigDecimal.ROUND_HALF_UP);
                if(!isNumber(bigDecimal.toString())){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的激励基数不合法");
                }
            }else{
                return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的激励基数不能为空");
            }
            if(!Util.isEmpty(merchantExportInData.getExcitationPercentage())){
                boolean numeric = isNumeric(merchantExportInData.getExcitationPercentage());
                if(!numeric){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的激励百分比不合法");
                }
                if(new BigDecimal(merchantExportInData.getExcitationPercentage()).compareTo(new BigDecimal(1))>0){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的激励百分比过大");
                }
            }else {
                return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的激励百分比不能为空");
            }
            if(!Util.isEmpty(merchantExportInData.getActualAmount())){
                boolean numeric = isNumeric(merchantExportInData.getActualAmount());
                if(!numeric){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的激励金额不合法");
                }
                if(new BigDecimal(merchantExportInData.getActualAmount()).compareTo(new BigDecimal(999999999))>0){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的激励金额过大");
                }
                BigDecimal bigDecimal = new BigDecimal(merchantExportInData.getActualAmount()).setScale(2, BigDecimal.ROUND_HALF_UP);
                if(!isNumber(bigDecimal.toString())){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的激励金额不合法");
                }
            }else{
                return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的激励金额不能为空");
            }
            if(Util.isEmpty(merchantExportInData.getMemberName())){
                return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的激励人员不能为空");
            }else{
                if(merchantExportInData.getMemberName().length()>30){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的激励人员长度过大");
                }
            }
            if(Util.isEmpty(merchantExportInData.getMonth())){
                return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的激励月度不能为空");
            }else {
                String[] split = merchantExportInData.getMonth().split("-");
                if(split.length!=2){
                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的激励月度格式yyyy-mm");
                }

//                Pattern pattern=Pattern.compile("/^\\d{4}-((0([1-9]))|(1(0|1|2)))$/");
//                Matcher match=pattern.matcher(merchantExportInData.getMonth());
//                if(match.matches()==false){
//                    return JsonResult.fail(UtilConst.CODE_403,"第"+merchantExportInData.getIndex()+"行的激励月度格式yyyy-mm");
//                }
            }
        }

        boolean flag = true;
        String projectNames = "";
        String contractNames = "";
        List errorList = new ArrayList();
        //校验数据有效性
        for (GetExcitationExportInData exportInData : fileDataList) {
            //查询资源项目是否存在
            Example exampleProject = new Example(GResourceProject.class);
            exampleProject.createCriteria().andEqualTo("projectName",exportInData.getProjectName())
                    .andEqualTo("delStatus",UtilConst.STR_NUMBER_1);
            List<GResourceProject> projectList = resourceProjectMapper.selectByExample(exampleProject);
            if(projectList.size()<=0){
                flag = false;
                String errorMes = "项目名称："+ exportInData.getProjectName() +"不存在";
                errorList.add(errorMes);
                continue;
            }

            boolean excitationType = true;
            String contractType = exportInData.getContractType();
            //1广告资源 2场地资源 3临时摆展 4服务产 5其他
            if("临时摆展类".equals(contractType)){
                excitationType =false;
            }
            if("服务产品类".equals(contractType)){
                excitationType =false;
            }
            if(excitationType){
                flag = false;
                String errorMes = "不存在的激励类型 ：" + contractType;
                errorList.add(errorMes);
                continue;
            }
        }
        if(!flag){
            return JsonResult.fail(UtilConst.CODE_403,errorList.toString());
        }

        //组装导入数据
        for(GetExcitationExportInData exportInData:fileDataList){

            //查询资源项目
            Example exampleProject = new Example(GResourceProject.class);
            exampleProject.createCriteria().andEqualTo("projectName",exportInData.getProjectName())
                    .andEqualTo("delStatus",UtilConst.STR_NUMBER_1);
            List<GResourceProject> projectList = resourceProjectMapper.selectByExample(exampleProject);
            GResourceProject resourceProject = projectList.get(0);

            GExcitation excitation = new GExcitation();
            excitation.setType(UtilConst.STR_NUMBER_1);//1--月度   2---年度
            //1临时摆展 2服务产
            String contractType = exportInData.getContractType();
            //1广告资源 2场地资源 3临时摆展 4服务产 5其他
            if("临时摆展类".equals(contractType)){
                excitation.setContractType("3");
            }
            if("服务产品类".equals(contractType)){
                excitation.setContractType("4");
            }
            excitation.setProjectId(resourceProject.getId());
            excitation.setPropertyProfit(new BigDecimal(exportInData.getPropertyProfit()).setScale(2, BigDecimal.ROUND_HALF_UP));
            excitation.setTax(new BigDecimal(exportInData.getTax()).setScale(2, BigDecimal.ROUND_HALF_UP));
            excitation.setCardinalNumber(new BigDecimal(exportInData.getCardinalNumber()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
            excitation.setRoleId(Long.parseLong("0"));
            BigDecimal bigDecimal = new BigDecimal(exportInData.getTaxPercentage()).multiply(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_UP);
            excitation.setTaxPercentage(Double.parseDouble(bigDecimal.toString()));
            excitation.setExcitationPercentage(Double.parseDouble(exportInData.getExcitationPercentage()));
            excitation.setActualAmount(new BigDecimal(exportInData.getActualAmount()).setScale(2, BigDecimal.ROUND_HALF_UP));
            excitation.setMemberName(exportInData.getMemberName());
            excitation.setMonth(exportInData.getMonth());
            excitation.setCreateTime(new Date());
            excitation.setDelStatus(UtilConst.STR_NUMBER_1);
            excitation.setStatus("1");
            excitationMapper.insert(excitation);
        }

        if(!flag){
            return JsonResult.fail(UtilConst.CODE_403,"项目："+projectNames +"不存在！ "+contractNames+" 对应的合同不存在！");
        }else {
            return JsonResult.success(null,"批量导入成功");
        }
    }

    //金额验证
    public static boolean isNumber(String str){
        Pattern pattern=Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后2位的数字的正则表达式
        Matcher match=pattern.matcher(str);
        if(match.matches()==false){
            return false;
        }else{
            return true;
        }
    }

    /**
     * 利用正则表达式判断字符串是否是数字
     * @param str
     * @return
     */
    public static boolean isNumeric00(String str){
//        Pattern pattern = Pattern.compile("[0-9]*");
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        if(str.indexOf(".")>0){//判断是否有小数点
            if(str.indexOf(".")==str.lastIndexOf(".") && str.split("\\.").length==2){ //判断是否只有一个小数点
                return pattern.matcher(str.replace(".","")).matches();
            }else {
                return false;
            }
        }else {
            return pattern.matcher(str).matches();
        }
    }

    public static boolean isNumeric(String str){
        try {
            BigDecimal bigDecimal = new BigDecimal(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
