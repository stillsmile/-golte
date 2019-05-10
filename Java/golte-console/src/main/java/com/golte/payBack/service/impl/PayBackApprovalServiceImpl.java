package com.golte.payBack.service.impl;

import com.golte.common.data.JsonResult;
import com.golte.common.util.Util;
import com.golte.common.util.UtilConst;
import com.golte.common.util.UtilMessage;
import com.golte.mapper.*;
import com.golte.mapper.entity.*;
import com.golte.payBack.service.PayBackApprovalService;
import com.golte.payBack.service.PayBackRecordService;
import com.golte.payBack.service.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PayBackApprovalServiceImpl implements PayBackApprovalService {

	@Autowired
	private GPaybackRecordMapper paybackRecordMapper;

	@Autowired
	private GPaybackPlanMapper paybackPlanMapper;

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
	private GCityMapper cityMapper;

	@Autowired
	private GRoleRelationshipMapper roleRelationshipMapper;

	@Override
	public JsonResult detailPayBackApproval(GetPayBackApprovalInData inData) throws Exception {
		GPaybackPlan paybackPlan = paybackPlanMapper.selectByPrimaryKey(inData.getId());
		GContract contract = contractMapper.selectByPrimaryKey(paybackPlan.getContractId());
		GetPayBackApprovalOutData outData = new GetPayBackApprovalOutData();
		outData.setContractId(contract.getId());
		outData.setMerchantId(contract.getMerchantId());
		outData.setName(contract.getName());
		outData.setContractAmount(contract.getAmount());
		outData.setSigningTime(Util.date2Str(contract.getSigningTime(),Util.DATE_YYYY_MM_DD_CHN));
		outData.setDeadlineStartTime(Util.date2Str(contract.getDeadlineStartTime(),Util.DATE_YYYY_MM_DD_CHN));
		outData.setDeadlineEndTime(Util.date2Str(contract.getDeadlineEndTime(),Util.DATE_YYYY_MM_DD_CHN));

		GContractProject contractProject = contractProjectMapper.selectByPrimaryKey(paybackPlan.getContractProjectId());
		outData.setTollMode(contractProject.getTollMode());
		outData.setPaymentCycle(contractProject.getPaymentCycle());
		outData.setUnitPrice(contractProject.getUnitPrice());
		outData.setNumber(contractProject.getNumber());
		outData.setSubtotal(contractProject.getSubtotal());
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
		outData.setResourceName(resources);
		outData.setPlanPaybackTime(Util.date2Str(paybackPlan.getPaybackTime(),Util.DATE_YYYY_MM_DD_CHN));
		outData.setReceivablePayback(paybackPlan.getReceivablePayback());
		outData.setPaybackPlanId(paybackPlan.getId());

		//获取回款记录
		Example exampleRecord = new Example(GPaybackRecord.class);
		exampleRecord.createCriteria().andEqualTo("paybackPlanId",paybackPlan.getId());
		List<GPaybackRecord> paybackRecordList = paybackRecordMapper.selectByExample(exampleRecord);
		BigDecimal backAmount = new BigDecimal(0);
		for(GPaybackRecord paybackRecord:paybackRecordList){
			backAmount = backAmount.add(paybackRecord.getPaybackAmount());
		}
		outData.setReceivedAmount(backAmount);

		Example exampleApproval = new Example(GPaybackApproval.class);
		exampleApproval.createCriteria()
				.andEqualTo("paybackPlanId",paybackPlan.getId())
				.andEqualTo("status",UtilConst.STR_NUMBER_1);
		GPaybackApproval paybackApproval = paybackApprovalMapper.selectOneByExample(exampleApproval);

		if(!Util.isEmpty(paybackApproval)){
			outData.setId(paybackApproval.getId());
			outData.setExtensionAmount(paybackApproval.getExtensionAmount());
			outData.setExtensionTime(Util.date2Str(paybackApproval.getExtensionTime(),Util.DATE_YYYY_MM_DD_CHN));
			outData.setExtensionReason(paybackApproval.getExtensionReason());
		}
		return JsonResult.success(outData, UtilMessage.SEARCH_MESSAGE_SUCCESS);
	}

	@Override
	@Transactional
	public JsonResult delayPayBackApproval(GetPayBackApprovalInData inData) throws Exception {
		GContract contract = contractMapper.selectByPrimaryKey(inData.getContractId());
		GCity city = cityMapper.selectByPrimaryKey(contract.getCityId());
		if(Util.isEmpty(inData.getId())){

			//获取上级审批人
			Example example = new Example(GRoleRelationship.class);
			example.createCriteria().andEqualTo("empId",inData.getEmpId());
			List<GRoleRelationship> roleRelationships = roleRelationshipMapper.selectByExample(example);
			Long i =0L;
			for(GRoleRelationship roleRelationship:roleRelationships){
				if(i<roleRelationship.getRoleId()){
					i = roleRelationship.getRoleId();
				}
			}
			GPaybackApproval paybackApproval = new GPaybackApproval();
			if(i==2){
				paybackApproval.setSupperEmployeeId(city.getBusinessPrincipal().toString());
			}else if(i==3){
				paybackApproval.setSupperEmployeeId(city.getCityPrincipal().toString());
			}else if(i==4){
				paybackApproval.setSupperEmployeeId(city.getCityPrincipal().toString());
			}else if(i==6){
				Example example1 = new Example(GRoleRelationship.class);
				example1.createCriteria().andEqualTo("roleId",UtilConst.STR_NUMBER_7);
				List<GRoleRelationship> roleRelationshipList = roleRelationshipMapper.selectByExample(example1);
				String supperEmpId = "";
				for(GRoleRelationship roleRelationship:roleRelationshipList){
					if(Util.isEmpty(supperEmpId)){
						supperEmpId = roleRelationship.getEmpId().toString();
					}else {
						supperEmpId = supperEmpId+","+roleRelationship.getEmpId();
					}
				}
				paybackApproval.setSupperEmployeeId(supperEmpId);
			}else {
				paybackApproval.setSupperEmployeeId(city.getBusinessPrincipal().toString());
			}

			paybackApproval.setContractId(inData.getContractId());
			paybackApproval.setPaybackPlanId(inData.getPaybackPlanId());
			paybackApproval.setExtensionTime(inData.getExtensionTime());
			paybackApproval.setExtensionAmount(inData.getExtensionAmount());
			paybackApproval.setExtensionReason(inData.getExtensionReason());
			paybackApproval.setStatus(UtilConst.STR_NUMBER_1);
			paybackApproval.setCreateTime(new Date());
			paybackApprovalMapper.insertSelective(paybackApproval);
		}else {
			GPaybackApproval paybackApproval = paybackApprovalMapper.selectByPrimaryKey(inData.getId());
			paybackApproval.setExtensionTime(inData.getExtensionTime());
			paybackApproval.setExtensionAmount(inData.getExtensionAmount());
			paybackApproval.setExtensionReason(inData.getExtensionReason());
			paybackApproval.setStatus(UtilConst.STR_NUMBER_1);
			paybackApprovalMapper.updateByPrimaryKeySelective(paybackApproval);
		}
		//更新
		GPaybackPlan paybackPlan = paybackPlanMapper.selectByPrimaryKey(inData.getPaybackPlanId());
		paybackPlan.setExtensionStatus(UtilConst.STR_NUMBER_1);
		paybackPlanMapper.updateByPrimaryKeySelective(paybackPlan);
		return JsonResult.success(null, UtilMessage.SAVE_MESSAGE_SUCCESS);
	}

	@Override
	@Transactional
	public JsonResult approvePayBackApproval(GetPayBackApprovalInData inData) throws Exception {
		GPaybackApproval paybackApproval = paybackApprovalMapper.selectByPrimaryKey(inData.getId());
		paybackApproval.setStatus(inData.getStatus());
		paybackApproval.setOpinion(inData.getOpinion());
		paybackApproval.setApprovalTime(new Date());
		paybackApproval.setApproveEmployeeId(inData.getEmpId());
		paybackApprovalMapper.updateByPrimaryKeySelective(paybackApproval);

		//审批通过更新回款计划
		if(UtilConst.STR_NUMBER_2.equals(inData.getStatus())){
			GPaybackPlan paybackPlan = paybackPlanMapper.selectByPrimaryKey(paybackApproval.getPaybackPlanId());
			paybackPlan.setPaybackTime(paybackApproval.getExtensionTime());
			String dateStr = Util.date2Str(paybackApproval.getExtensionTime(),Util.DATE_YYYY_MM_DD_CHN);
			dateStr = dateStr + " 23:59:59";
			if(Util.str2Date(dateStr,Util.DATE_TIME_PAT_19_).compareTo(new Date())<0){
				paybackPlan.setPaybackStatus(UtilConst.STR_NUMBER_2);
			}else {
				paybackPlan.setPaybackStatus(UtilConst.STR_NUMBER_1);
			}
			paybackPlan.setExtensionStatus(UtilConst.STR_NUMBER_1);
			paybackPlan.setExtensionAmount(paybackApproval.getExtensionAmount());
			paybackPlan.setExtensionTime(paybackApproval.getExtensionTime());
			paybackPlanMapper.updateByPrimaryKeySelective(paybackPlan);
		}
		return JsonResult.success(null, UtilMessage.SAVE_MESSAGE_SUCCESS);
	}
}
