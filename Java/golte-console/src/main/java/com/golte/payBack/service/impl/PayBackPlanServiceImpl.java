package com.golte.payBack.service.impl;

import com.github.pagehelper.PageHelper;
import com.golte.common.data.JsonResult;
import com.golte.common.data.PageInfo;
import com.golte.common.util.Util;
import com.golte.common.util.UtilConst;
import com.golte.common.util.UtilMessage;
import com.golte.contract.service.ContractService;
import com.golte.contract.service.data.*;
import com.golte.mapper.*;
import com.golte.mapper.entity.*;
import com.golte.payBack.service.PayBackPlanService;
import com.golte.payBack.service.data.GetPayBackOutData;
import com.golte.payBack.service.data.GetPayBackPlanInData;
import com.golte.payBack.service.data.GetPayBackPlanListInData;
import com.golte.payBack.service.data.GetPayBackPlanOutData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class PayBackPlanServiceImpl implements PayBackPlanService {

	@Autowired
	private GPaybackPlanMapper paybackPlanMapper;

	@Autowired
	private GPaybackRecordMapper paybackRecordMapper;

	@Autowired
	private GPaybackApprovalMapper paybackApprovalMapper;

	@Autowired
	private GContractMapper contractMapper;

	@Autowired
	private GContractProjectMapper contractProjectMapper;

	@Autowired
	private GContractResourceMapper contractResourceMapper;

	@Autowired
	private GResourceProjectDetailMapper resourceProjectDetailMapper;

	@Autowired
	private GResourcePointMapper resourcePointMapper;

	@Autowired
	private GEmployeeMapper employeeMapper;

	@Override
	public JsonResult selectPayBackList(GetPayBackPlanInData inData) throws Exception {
		PageInfo<GetPayBackPlanOutData> pageInfo;
		PageHelper.startPage(inData.getPageNum(), inData.getPageSize());
		List<GetPayBackPlanOutData> outData = paybackPlanMapper.selectListByCondition(inData);
		for(GetPayBackPlanOutData payBackPlanOutData:outData){
			Example example = new Example(GPaybackApproval.class);
			example.createCriteria()
					.andEqualTo("paybackPlanId",payBackPlanOutData.getId())
					.andEqualTo("status",UtilConst.STR_NUMBER_1);
			GPaybackApproval paybackApproval = paybackApprovalMapper.selectOneByExample(example);
			if(!Util.isEmpty(paybackApproval)){
				payBackPlanOutData.setIsApprove(paybackApproval.getStatus());
				payBackPlanOutData.setApprovalAccount(paybackApproval.getSupperEmployeeId());
			}else {
				payBackPlanOutData.setIsApprove(UtilConst.STR_NUMBER_0);
			}
		}
		if (Util.isEmptyList(outData)) {
			pageInfo = new PageInfo<>(new ArrayList<GetPayBackPlanOutData>());
		} else {
			pageInfo = new PageInfo<>(outData);
		}
		return JsonResult.success(pageInfo, UtilMessage.SEARCH_MESSAGE_SUCCESS);
	}

	@Override
	public JsonResult detailPayBack(GetPayBackPlanInData inData) throws Exception {
		GContract contract = contractMapper.selectByPrimaryKey(inData.getContractId());
		GetPayBackPlanOutData outData = new GetPayBackPlanOutData();
		outData.setContractId(contract.getId());
		outData.setMerchantId(contract.getMerchantId());
		outData.setName(contract.getName());
		outData.setContractAmount(contract.getAmount());
		if(!Util.isEmpty(contract.getSigningTime())){
			outData.setSigningTime(Util.date2Str(contract.getSigningTime(),Util.DATE_YYYY_MM_DD_CHN));
		}
		outData.setDeadlineStartTime(Util.date2Str(contract.getDeadlineStartTime(),Util.DATE_YYYY_MM_DD_CHN));
		outData.setDeadlineEndTime(Util.date2Str(contract.getDeadlineEndTime(),Util.DATE_YYYY_MM_DD_CHN));

		Example example = new Example(GContractProject.class);
		example.createCriteria().andEqualTo("contractId",contract.getId())
				.andEqualTo("status",UtilConst.STR_NUMBER_1);
		List<GContractProject> contractProjects = contractProjectMapper.selectByExample(example);
		List<GetContractProjectOutData> projects = new ArrayList<>();
		for(GContractProject contractProject:contractProjects){
			GetContractProjectOutData contractProjectOutData = new GetContractProjectOutData();
			contractProjectOutData.setId(contractProject.getId());
			contractProjectOutData.setProjectId(contractProject.getProjectId());
			GResourcePoint resourcePointThree = resourcePointMapper.selectByPrimaryKey(contractProject.getPointId());
			GResourcePoint resourcePointTwo = resourcePointMapper.selectByPrimaryKey(resourcePointThree.getParentId());
			GResourcePoint resourcePointOne = resourcePointMapper.selectByPrimaryKey(resourcePointTwo.getParentId());
			contractProjectOutData.setPointIdThree(resourcePointThree.getId());
			contractProjectOutData.setPointIdTwo(resourcePointTwo.getId());
			contractProjectOutData.setPointIdOne(resourcePointOne.getId());
			contractProjectOutData.setTollMode(contractProject.getTollMode());
			contractProjectOutData.setPaymentCycle(contractProject.getPaymentCycle());
			contractProjectOutData.setUnitPrice(contractProject.getUnitPrice());
			contractProjectOutData.setNumber(contractProject.getNumber());
			contractProjectOutData.setSubtotal(contractProject.getSubtotal());
			contractProjectOutData.setBackAmount(contractProject.getChangePaybackAmount());
			contractProjectOutData.setStatus(contractProject.getStatus());
			Example exampleResource = new Example(GContractResource.class);
			exampleResource.createCriteria().andEqualTo("contractProjectId",contractProject.getId());
			List<GContractResource> contractResources = contractResourceMapper.selectByExample(exampleResource);
			String resources = "";
			for (GContractResource contractResource:contractResources){
				GResourceProjectDetail resourceProjectDetail = resourceProjectDetailMapper.selectByPrimaryKey(contractResource.getResourceId());
				if(Util.isEmpty(resources)){
					resources = resources+resourceProjectDetail.getAssetsName();
				}else {
					resources = resources+","+resourceProjectDetail.getAssetsName();
				}
			}
			contractProjectOutData.setResourceName(resources);

			Example examplePlan = new Example(GPaybackPlan.class);
			examplePlan.createCriteria()
					.andEqualTo("contractProjectId",contractProject.getId())
					.andNotEqualTo("status",UtilConst.STR_NUMBER_0)
					.andEqualTo("delStatus",UtilConst.STR_NUMBER_1);
			List<GPaybackPlan> paybackPlanList = paybackPlanMapper.selectByExample(examplePlan);
			List<GetPayBackOutData> payBackList = new ArrayList<>();
			for (GPaybackPlan plan:paybackPlanList){
				Example example1 = new Example(GPaybackRecord.class);
				example1.createCriteria().andEqualTo("paybackPlanId",plan.getId());
				List<GPaybackRecord> paybackRecordList = paybackRecordMapper.selectByExample(example1);
				BigDecimal receivedPayBack = new BigDecimal(0);
				for(GPaybackRecord paybackRecord:paybackRecordList){
					receivedPayBack = receivedPayBack.add(paybackRecord.getPaybackAmount());
				}
				GetPayBackOutData payBackOutData = new GetPayBackOutData();
				payBackOutData.setId(plan.getId());
				payBackOutData.setPaybackTime(Util.date2Str(plan.getPaybackTime(),Util.DATE_YYYY_MM_DD_CHN));
				payBackOutData.setReceivablePayback(plan.getReceivablePayback());
				payBackOutData.setRemarks(plan.getRemarks());
				payBackOutData.setStatus(plan.getStatus());
				payBackOutData.setReceivedPayback(receivedPayBack);
				payBackList.add(payBackOutData);
			}
			contractProjectOutData.setPayBackList(payBackList);
			projects.add(contractProjectOutData);
		}
		outData.setProjectList(projects);

		return JsonResult.success(outData, UtilMessage.SEARCH_MESSAGE_SUCCESS);
	}

	@Override
	@Transactional
	public JsonResult updatePayBack(GetPayBackPlanListInData inData) throws Exception {
		if(!Util.isEmptyList(inData.getPayBackPlanList())){
			Example example = new Example(GPaybackPlan.class);
			example.createCriteria().andEqualTo("contractId",inData.getPayBackPlanList().get(0).getContractId());
			List<GPaybackPlan> paybackPlanList = paybackPlanMapper.selectByExample(example);
			List<Long> ids = new ArrayList<>();
			for(GetPayBackPlanInData payBackPlanInData:inData.getPayBackPlanList()){
				if(!Util.isEmpty(payBackPlanInData.getId())){
					ids.add(payBackPlanInData.getId());
				}
			}
			for(GPaybackPlan paybackPlan:paybackPlanList){
				if(!ids.contains(paybackPlan.getId())){
					if(UtilConst.STR_NUMBER_1.equals(paybackPlan.getStatus())){
						//删除关联的审批
						Example example1 = new Example(GPaybackApproval.class);
						example1.createCriteria().andEqualTo("paybackPlanId",paybackPlan.getId());
						paybackApprovalMapper.deleteByExample(example1);
						GPaybackPlan paybackPlan1 = paybackPlanMapper.selectByPrimaryKey(paybackPlan.getId());
						paybackPlan1.setDelStatus(UtilConst.STR_NUMBER_0);
						paybackPlanMapper.updateByPrimaryKeySelective(paybackPlan1);
					}
				}
			}
		}
		for(GetPayBackPlanInData payBackPlanInData:inData.getPayBackPlanList()){
			GContract contract = contractMapper.selectByPrimaryKey(payBackPlanInData.getContractId());
			if(Util.isEmpty(payBackPlanInData.getId())){
				GPaybackPlan paybackPlan = new GPaybackPlan();
				paybackPlan.setContractId(payBackPlanInData.getContractId());
				paybackPlan.setCentralCityId(contract.getCentralCityId());
				paybackPlan.setCityId(contract.getCityId());
				paybackPlan.setName(contract.getName());
				paybackPlan.setMerchantId(contract.getMerchantId());
				paybackPlan.setContractProjectId(payBackPlanInData.getContractProjectId());
				paybackPlan.setExtensionStatus(UtilConst.STR_NUMBER_0);
				paybackPlan.setBeforeRemind(UtilConst.STR_NUMBER_0);
				paybackPlan.setExtensionRemind(UtilConst.STR_NUMBER_0);
				paybackPlan.setStatus(UtilConst.STR_NUMBER_1);
				paybackPlan.setDelStatus(UtilConst.STR_NUMBER_1);
				paybackPlan.setEditStatus(UtilConst.STR_NUMBER_1);
				paybackPlan.setCreateTime(new Date());
				paybackPlan.setPaybackTime(payBackPlanInData.getPaybackTime());
				String dateStr = Util.date2Str(payBackPlanInData.getPaybackTime(),Util.DATE_YYYY_MM_DD_CHN);
				dateStr = dateStr + " 23:59:59";
				if(Util.str2Date(dateStr,Util.DATE_TIME_PAT_19_).compareTo(new Date())<0){
					paybackPlan.setPaybackStatus(UtilConst.STR_NUMBER_2);
				}else {
					paybackPlan.setPaybackStatus(UtilConst.STR_NUMBER_1);
				}
				paybackPlan.setReceivablePayback(payBackPlanInData.getReceivablePayback());
				paybackPlan.setRemarks(payBackPlanInData.getRemarks());
				paybackPlan.setCreateName(contract.getCreateName());
				paybackPlanMapper.insertSelective(paybackPlan);
			}else {
				GPaybackPlan paybackPlan = paybackPlanMapper.selectByPrimaryKey(payBackPlanInData.getId());
				paybackPlan.setPaybackTime(payBackPlanInData.getPaybackTime());
				String dateStr = Util.date2Str(payBackPlanInData.getPaybackTime(),Util.DATE_YYYY_MM_DD_CHN);
				dateStr = dateStr + " 23:59:59";
				if(Util.str2Date(dateStr,Util.DATE_TIME_PAT_19_).compareTo(new Date())<0){
					paybackPlan.setPaybackStatus(UtilConst.STR_NUMBER_2);
				}else {
					paybackPlan.setPaybackStatus(UtilConst.STR_NUMBER_1);
				}
				paybackPlan.setReceivablePayback(payBackPlanInData.getReceivablePayback());
				paybackPlan.setRemarks(payBackPlanInData.getRemarks());
				paybackPlan.setEditStatus(UtilConst.STR_NUMBER_1);
				paybackPlan.setCreateName(contract.getCreateName());
				paybackPlanMapper.updateByPrimaryKeySelective(paybackPlan);
			}
		}
		return JsonResult.success(null, UtilMessage.SAVE_MESSAGE_SUCCESS);
	}

}
