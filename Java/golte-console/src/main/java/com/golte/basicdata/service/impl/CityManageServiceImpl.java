package com.golte.basicdata.service.impl;

import com.github.pagehelper.PageHelper;
import com.golte.basicdata.service.CityManageService;
import com.golte.basicdata.service.data.GetCityInData;
import com.golte.basicdata.service.data.GetCityOutData;
import com.golte.basicdata.service.data.GetEmployeeOutData;
import com.golte.common.data.JsonResult;
import com.golte.common.data.PageInfo;
import com.golte.common.util.Util;
import com.golte.common.util.UtilConst;
import com.golte.common.util.UtilMessage;
import com.golte.mapper.*;
import com.golte.mapper.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CityManageServiceImpl implements CityManageService {

    @Autowired
    private GCityMapper cityMapper;

    @Autowired
    private GCityEntryMapper cityEntryMapper;

    @Autowired
    private GCityYearTargetMapper cityYearTargetMapper;

    @Autowired
    private GContractApprovalMapper contractApprovalMapper;

    @Autowired
    private GPaybackApprovalMapper paybackApprovalMapper;

    @Autowired
    private GContractMapper contractMapper;

    @Override
    public JsonResult selectCitys(GetCityInData inData) throws Exception {
        GetCityOutData getCityOutData = new GetCityOutData();
        List<GCity> gCities = cityMapper.selectCitys(inData);
        if(Util.isEmptyList(inData.getCityIds())){
            getCityOutData.setType("1");
        }else{
            getCityOutData.setType("");
        }
        getCityOutData.setCityList(gCities);
        return JsonResult.success(getCityOutData, UtilMessage.GET_MESSAGE_SUCCESS);
    }

    @Override
    public JsonResult selectCityList(GetCityInData inData) throws Exception {

        PageInfo<GetCityOutData> pageInfo;
        PageHelper.startPage(inData.getPageNum(), inData.getPageSize());
        List<GetCityOutData> cityOutData = cityMapper.selectListByCondition(inData);
        if(cityOutData.size()>0){
            for(int i =0;i<cityOutData.size();i++){
                GetCityOutData getCityOutData1 = cityOutData.get(i);
                //如存在录入人员信息
                if(getCityOutData1.getEmployeeList().size()>0){
                    List<String> enterNames = new ArrayList<>();
                    List<Long> enterIds = new ArrayList<>();
                    for(GetEmployeeOutData employee : getCityOutData1.getEmployeeList()){
                        enterNames.add(employee.getEmpName());
                        enterIds.add(employee.getId());
                    }
                    cityOutData.get(i).setEnterPersonName(enterNames);
                    cityOutData.get(i).setEnterPersonIds(enterIds);
                }
            }
        }
        if (Util.isEmptyList(cityOutData)) {
            pageInfo = new PageInfo<>(new ArrayList<GetCityOutData>());
        } else {
            pageInfo = new PageInfo<>(cityOutData);
        }
        return JsonResult.success(pageInfo, UtilMessage.GET_MESSAGE_SUCCESS);
    }

    @Override
    @Transactional
    public JsonResult saveCity(GetCityInData inData) throws Exception {
        //先判断是否存在此城市信息
        Example example = new Example(GCity.class);
        example.createCriteria().andEqualTo("cityName", inData.getCityName()).andEqualTo("delStatus", UtilConst.STR_NUMBER_1);;
        int count  = cityMapper.selectCountByExample(example);
        if(count>0){
            return JsonResult.fail(UtilConst.CODE_403, UtilMessage.CITY_NAME_REPEAT);
        }
        //组装城市信息
        GCity city = new GCity();
        city.setCentralCityId(inData.getCentralCityId());
        city.setCityName(inData.getCityName());
        city.setCityPrincipal(inData.getCityPrincipal());
        city.setBusinessPrincipal(inData.getBusinessPrincipal());
        city.setYearTarget(inData.getYearTarget());
        city.setDelStatus(UtilConst.STR_NUMBER_1);
        city.setCreateTime(new Date());
        city.setCreateName(inData.getLoginAccount());
        cityMapper.insert(city);
        //组装年度信息
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        GCityYearTarget gCityYearTarget = new GCityYearTarget();
        gCityYearTarget.setCityId(city.getId());
        gCityYearTarget.setYear(sdf.format(date));
        gCityYearTarget.setYearTarget(inData.getYearTarget());
        cityYearTargetMapper.insert(gCityYearTarget);
        // 组装对应的录入人员
        for (Long enterPersonId : inData.getEnterPersonIds()) {
            GCityEntry gCityEntry = new GCityEntry();
            gCityEntry.setCentralCityId(inData.getCentralCityId());
            gCityEntry.setCityId(city.getId());
            gCityEntry.setEnterPerson(enterPersonId);
            cityEntryMapper.insert(gCityEntry);
        }
        return JsonResult.success("", UtilMessage.SAVE_MESSAGE_SUCCESS);
    }

    @Override
    @Transactional
    public JsonResult deleteCity(GetCityInData inData) throws Exception {
       //删除员工
        Example example = new Example(GCity.class);
        example.createCriteria().andIn("id", inData.getIds());
        GCity city = new GCity();
        city.setDelStatus(UtilConst.STR_NUMBER_0);
        cityMapper.updateByExampleSelective(city,example);
        return JsonResult.success("", UtilMessage.DEL_MESSAGE_SUCCESS);
    }

    @Override
    @Transactional
    public JsonResult updateCity(GetCityInData inData) throws Exception {
        //先判断是否存在此城市信息
        Example example = new Example(GCity.class);
        example.createCriteria().andEqualTo("cityName", inData.getCityName()).andEqualTo("delStatus", UtilConst.STR_NUMBER_1);
        List<GCity> gCities = cityMapper.selectByExample(example);
        if(gCities.size()>0){
            GCity city = gCities.get(0);
            //如果Id相同，则为同一个，不同则说明重复
            if(!city.getId().equals(inData.getId())){
                return JsonResult.fail(UtilConst.CODE_403, UtilMessage.CITY_NAME_REPEAT);
            }
        }

        GCity city2 = cityMapper.selectByPrimaryKey(inData.getId());
        //若城市分公司负责人变更，顺带更新合同审批表未审批的的负责人
        if(!city2.getCityPrincipal().equals(inData.getCityPrincipal())){
            example = new Example(GContractApproval.class);
            example.createCriteria().andEqualTo("supperEmployeeId", city2.getCityPrincipal()).andEqualTo("status", UtilConst.STR_NUMBER_1);
            List<GContractApproval> gContractApprovals = contractApprovalMapper.selectByExample(example);
            for (GContractApproval contractApprovals : gContractApprovals) {
                //查询合同信息
                GContract contract = contractMapper.selectByPrimaryKey(contractApprovals.getContractId());
                //如果是当前城市的审批，则更新审批人
                if(city2.getId().equals(contract.getCityId())){
                    contractApprovals.setSupperEmployeeId(inData.getCityPrincipal().toString());
                    contractApprovalMapper.updateByPrimaryKeySelective(contractApprovals);
                }
            }

            example = new Example(GPaybackApproval.class);
            example.createCriteria().andEqualTo("supperEmployeeId", city2.getCityPrincipal()).andEqualTo("status", UtilConst.STR_NUMBER_1);
            List<GPaybackApproval> gPaybackApprovals = paybackApprovalMapper.selectByExample(example);
            for (GPaybackApproval paybackApproval : gPaybackApprovals){
                //查询合同信息
                GContract contract = contractMapper.selectByPrimaryKey(paybackApproval.getContractId());
                //如果是当前城市的审批，则更新审批人
                if(city2.getId().equals(contract.getCityId())){
                    paybackApproval.setSupperEmployeeId(inData.getCityPrincipal().toString());
                    paybackApprovalMapper.updateByPrimaryKeySelective(paybackApproval);
                }
            }
        }
        //若经营部分负责人变更，顺带更新合同审批表未审批的的负责人
        if(!city2.getBusinessPrincipal().equals(inData.getBusinessPrincipal())){
            example = new Example(GContractApproval.class);
            example.createCriteria().andEqualTo("supperEmployeeId", city2.getBusinessPrincipal()).andEqualTo("status", UtilConst.STR_NUMBER_1);
            List<GContractApproval> gContractApprovals = contractApprovalMapper.selectByExample(example);
            for (GContractApproval contractApprovals : gContractApprovals) {
                //查询合同信息
                GContract contract = contractMapper.selectByPrimaryKey(contractApprovals.getContractId());
                //如果是当前城市的审批，则更新审批人
                if(city2.getId().equals(contract.getCityId())){
                    contractApprovals.setSupperEmployeeId(inData.getBusinessPrincipal().toString());
                    contractApprovalMapper.updateByPrimaryKeySelective(contractApprovals);
                }
            }

            example = new Example(GPaybackApproval.class);
            example.createCriteria().andEqualTo("supperEmployeeId", city2.getBusinessPrincipal()).andEqualTo("status", UtilConst.STR_NUMBER_1);
            List<GPaybackApproval> gPaybackApprovals = paybackApprovalMapper.selectByExample(example);
            for (GPaybackApproval paybackApproval : gPaybackApprovals){
                //查询合同信息
                GContract contract = contractMapper.selectByPrimaryKey(paybackApproval.getContractId());
                //如果是当前城市的审批，则更新审批人
                if(city2.getId().equals(contract.getCityId())){
                    paybackApproval.setSupperEmployeeId(inData.getBusinessPrincipal().toString());
                    paybackApprovalMapper.updateByPrimaryKeySelective(paybackApproval);
                }
            }
        }

        GCity city = cityMapper.selectByPrimaryKey(inData.getId());
        city.setCentralCityId(inData.getCentralCityId());
        city.setCityName(inData.getCityName());
        city.setCityPrincipal(inData.getCityPrincipal());
        city.setBusinessPrincipal(inData.getBusinessPrincipal());
        city.setYearTarget(inData.getYearTarget());
        city.setUpdateName(inData.getLoginAccount());
        city.setUpdateTime(new Date());
        cityMapper.updateByPrimaryKeySelective(city);

        //更新城市年度表的指标
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        example = new Example(GCityYearTarget.class);
        example.createCriteria().andEqualTo("cityId", inData.getId()).andEqualTo("year", sdf.format(date));
        List<GCityYearTarget> gCityYearTargets = cityYearTargetMapper.selectByExample(example);
        //若当年的年度指标变更则更新
        if(gCityYearTargets.size()>0){
            GCityYearTarget gCityYearTarget = gCityYearTargets.get(0);
            BigDecimal yearTarget = gCityYearTarget.getYearTarget();
            if(yearTarget.compareTo(inData.getYearTarget())==0){
                //相同不做处理
            }else{
                gCityYearTarget.setYearTarget(inData.getYearTarget());
                cityYearTargetMapper.updateByPrimaryKeySelective(gCityYearTarget);
            }

        }
        //若不存在当前年度的信息，则保存
        if(gCityYearTargets.size()==0){
            //组装年度信息
            GCityYearTarget gCityYearTarget = new GCityYearTarget();
            gCityYearTarget.setCityId(city.getId());
            gCityYearTarget.setYear(sdf.format(date));
            gCityYearTarget.setYearTarget(inData.getYearTarget());
            cityYearTargetMapper.insert(gCityYearTarget);
        }

        example = new Example(GCityEntry.class);
        example.createCriteria().andEqualTo("centralCityId", inData.getCentralCityId())
                .andEqualTo("cityId", inData.getId());
        //删除存在的录入员
        List<GCityEntry> gCityEntries = cityEntryMapper.selectByExample(example);
        for (GCityEntry city1 :gCityEntries) {
            cityEntryMapper.delete(city1);
        }
        // 组装对应的录入人员
        for (Long enterPersonId : inData.getEnterPersonIds()) {
            GCityEntry gCityEntry = new GCityEntry();
            gCityEntry.setCentralCityId(inData.getCentralCityId());
            gCityEntry.setCityId(city.getId());
            gCityEntry.setEnterPerson(enterPersonId);
            cityEntryMapper.insert(gCityEntry);
        }
        return JsonResult.success("", UtilMessage.SAVE_MESSAGE_SUCCESS);
    }
}
