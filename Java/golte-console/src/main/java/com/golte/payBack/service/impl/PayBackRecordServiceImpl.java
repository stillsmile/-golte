package com.golte.payBack.service.impl;

import com.golte.common.data.JsonResult;
import com.golte.common.util.Util;
import com.golte.common.util.UtilConst;
import com.golte.common.util.UtilMessage;
import com.golte.contract.service.data.GetContractProjectOutData;
import com.golte.mapper.*;
import com.golte.mapper.entity.*;
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
public class PayBackRecordServiceImpl implements PayBackRecordService {

	@Autowired
	private GPaybackRecordMapper paybackRecordMapper;

	@Autowired
	private GPaybackPlanMapper paybackPlanMapper;

	@Autowired
	private GContractMapper contractMapper;

	@Autowired
	private GContractProjectMapper contractProjectMapper;

	@Autowired
	private GContractResourceMapper contractResourceMapper;

	@Autowired
	private GResourceProjectDetailMapper resourceProjectDetailMapper;

	@Autowired
	private GEmployeeMapper employeeMapper;

	@Override
	public JsonResult detailPayBackRecord(GetPayBackRecordInData inData) throws Exception {
		GPaybackPlan paybackPlan = paybackPlanMapper.selectByPrimaryKey(inData.getId());
		GContract contract = contractMapper.selectByPrimaryKey(paybackPlan.getContractId());
		GetPayBackRecordOutData outData = new GetPayBackRecordOutData();
		outData.setContractId(contract.getId());
		outData.setMerchantId(contract.getMerchantId());
		outData.setName(contract.getName());
		outData.setContractAmount(contract.getAmount());
		if(!Util.isEmpty(contract.getSigningTime())){
			outData.setSigningTime(Util.date2Str(contract.getSigningTime(),Util.DATE_YYYY_MM_DD_CHN));
		}
		outData.setDeadlineStartTime(Util.date2Str(contract.getDeadlineStartTime(),Util.DATE_YYYY_MM_DD_CHN));
		outData.setDeadlineEndTime(Util.date2Str(contract.getDeadlineEndTime(),Util.DATE_YYYY_MM_DD_CHN));

		GContractProject contractProject = contractProjectMapper.selectByPrimaryKey(paybackPlan.getContractProjectId());
		outData.setContractProjectId(contractProject.getId());
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
		outData.setPaybackPlanId(paybackPlan.getId());
		outData.setPlanPaybackTime(Util.date2Str(paybackPlan.getPaybackTime(),Util.DATE_YYYY_MM_DD_CHN));
		outData.setReceivablePayback(paybackPlan.getReceivablePayback());
		outData.setRemarks(paybackPlan.getRemarks());
		Example exampleRecord = new Example(GPaybackRecord.class);
		exampleRecord.createCriteria().andEqualTo("paybackPlanId",paybackPlan.getId());
		List<GPaybackRecord> paybackRecordList = paybackRecordMapper.selectByExample(exampleRecord);
		List<GetRecordListOutData> recordList = new ArrayList<>();
		for(GPaybackRecord paybackRecord:paybackRecordList){
			GetRecordListOutData recordListOutData = new GetRecordListOutData();
			recordListOutData.setId(paybackRecord.getId());
			recordListOutData.setContractId(paybackRecord.getContractId());
			recordListOutData.setContractProjectId(paybackRecord.getContractProjectId());
			recordListOutData.setLastPayment(paybackRecord.getLastPayment());
			recordListOutData.setPaybackAmount(paybackRecord.getPaybackAmount());
			recordListOutData.setPaybackInvoiceCode(paybackRecord.getPaybackInvoiceCode());
			recordListOutData.setPaybackPlanId(paybackRecord.getPaybackPlanId());
			recordListOutData.setPaybackTime(Util.date2Str(paybackRecord.getPaybackTime(),Util.DATE_YYYY_MM_DD_CHN));
			recordListOutData.setReceiptMember(paybackRecord.getReceiptMember());
			recordList.add(recordListOutData);
		}
		outData.setRecordList(recordList);
		return JsonResult.success(outData, UtilMessage.SEARCH_MESSAGE_SUCCESS);
	}

	@Override
	public JsonResult detailPayBackRecordList(GetPayBackRecordInData inData) throws Exception {
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
			contractProjectOutData.setTollMode(contractProject.getTollMode());
			contractProjectOutData.setPaymentCycle(contractProject.getPaymentCycle());
			contractProjectOutData.setUnitPrice(contractProject.getUnitPrice());
			contractProjectOutData.setNumber(contractProject.getNumber());
			contractProjectOutData.setSubtotal(contractProject.getSubtotal());
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
			examplePlan.createCriteria().andEqualTo("contractProjectId",contractProject.getId())
					.andEqualTo("delStatus",UtilConst.STR_NUMBER_1);
			List<GPaybackPlan> paybackPlanList = paybackPlanMapper.selectByExample(examplePlan);
			List<GetPayBackOutData> payBackList = new ArrayList<>();
			for (GPaybackPlan plan:paybackPlanList){
				GetPayBackOutData payBackOutData = new GetPayBackOutData();
				payBackOutData.setId(plan.getId());
				payBackOutData.setPaybackTime(Util.date2Str(plan.getPaybackTime(),Util.DATE_YYYY_MM_DD_CHN));
				payBackOutData.setReceivablePayback(plan.getReceivablePayback());
				payBackOutData.setStatus(plan.getStatus());
				payBackOutData.setRemarks(plan.getRemarks());
				Example exampleRecord = new Example(GPaybackRecord.class);
				exampleRecord.createCriteria().andEqualTo("paybackPlanId",plan.getId());
				List<GPaybackRecord> paybackRecordList = paybackRecordMapper.selectByExample(exampleRecord);
				List<GetRecordListOutData> recordList = new ArrayList<>();
				for(GPaybackRecord paybackRecord:paybackRecordList){
					GetRecordListOutData recordListOutData = new GetRecordListOutData();
					recordListOutData.setId(paybackRecord.getId());
					recordListOutData.setContractId(paybackRecord.getContractId());
					recordListOutData.setContractProjectId(paybackRecord.getContractProjectId());
					recordListOutData.setLastPayment(paybackRecord.getLastPayment());
					recordListOutData.setPaybackAmount(paybackRecord.getPaybackAmount());
					recordListOutData.setPaybackInvoiceCode(paybackRecord.getPaybackInvoiceCode());
					recordListOutData.setPaybackPlanId(paybackRecord.getPaybackPlanId());
					recordListOutData.setPaybackTime(Util.date2Str(paybackRecord.getPaybackTime(),Util.DATE_YYYY_MM_DD_CHN));
					GEmployee employee = employeeMapper.selectByPrimaryKey(Long.parseLong(paybackRecord.getReceiptMember()));
					if(Util.isEmpty(employee)){
						recordListOutData.setReceiptMember("");
					}else {
						recordListOutData.setReceiptMember(employee.getEmpName());
					}
					recordList.add(recordListOutData);
				}
				payBackOutData.setRecordList(recordList);
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
	public JsonResult updatePayBackRecord(GetPayBackRecordListInData inData) throws Exception {
		BigDecimal amount = new BigDecimal(0);
		for(GetPayBackRecordInData payBackRecordInData:inData.getRecordList()){
			if(Util.isEmpty(payBackRecordInData.getId())){
				GPaybackRecord paybackRecord = new GPaybackRecord();
				paybackRecord.setContractId(payBackRecordInData.getContractId());
				paybackRecord.setContractProjectId(payBackRecordInData.getContractProjectId());
				paybackRecord.setLastPayment(payBackRecordInData.getLastPayment());
				paybackRecord.setPaybackAmount(payBackRecordInData.getPaybackAmount());
				paybackRecord.setPaybackInvoiceCode(payBackRecordInData.getPaybackInvoiceCode());
				paybackRecord.setPaybackPlanId(payBackRecordInData.getPaybackPlanId());
				paybackRecord.setPaybackTime(payBackRecordInData.getPaybackTime());
				paybackRecord.setReceiptMember(payBackRecordInData.getReceiptMember());
				paybackRecord.setCreateTime(new Date());
				paybackRecordMapper.insertSelective(paybackRecord);
			}else {
				GPaybackRecord paybackRecord = paybackRecordMapper.selectByPrimaryKey(payBackRecordInData.getId());
				paybackRecord.setPaybackAmount(payBackRecordInData.getPaybackAmount());
				paybackRecord.setPaybackInvoiceCode(payBackRecordInData.getPaybackInvoiceCode());
				paybackRecord.setPaybackTime(payBackRecordInData.getPaybackTime());
				paybackRecord.setReceiptMember(payBackRecordInData.getReceiptMember());
				paybackRecord.setLastPayment(payBackRecordInData.getLastPayment());
				paybackRecordMapper.updateByPrimaryKeySelective(paybackRecord);
			}
			amount=amount.add(payBackRecordInData.getPaybackAmount());
		}
		GPaybackPlan paybackPlan = paybackPlanMapper.selectByPrimaryKey(inData.getRecordList().get(0).getPaybackPlanId());
		if(paybackPlan.getReceivablePayback().compareTo(new BigDecimal(0))>0){
			if(amount.compareTo(paybackPlan.getReceivablePayback())>=0){
				paybackPlan.setPaybackStatus(UtilConst.STR_NUMBER_3);
				paybackPlanMapper.updateByPrimaryKeySelective(paybackPlan);
			}
		}else {
			if(amount.compareTo(paybackPlan.getReceivablePayback())<=0){
				paybackPlan.setPaybackStatus(UtilConst.STR_NUMBER_3);
				paybackPlanMapper.updateByPrimaryKeySelective(paybackPlan);
			}
		}

		if(!Util.isEmptyList(inData.getRecordList())){
			GContract contract = contractMapper.selectByPrimaryKey(inData.getRecordList().get(0).getContractId());
			Example example = new Example(GPaybackPlan.class);
			example.createCriteria()
					.andEqualTo("contractId",contract.getId())
					.andEqualTo("status",UtilConst.STR_NUMBER_1)
					.andEqualTo("delStatus",UtilConst.STR_NUMBER_1);
			List<GPaybackPlan> paybackPlanList = paybackPlanMapper.selectByExample(example);
			boolean flag = true;
			for(GPaybackPlan plan:paybackPlanList){
				if(!UtilConst.STR_NUMBER_3.equals(plan.getPaybackStatus())){
					flag = false;
				}
			}
			if(flag){
				contract.setPaybackType(UtilConst.STR_NUMBER_3);
				contract.setContractStatus(UtilConst.STR_NUMBER_5);
			} else {
				contract.setPaybackType(UtilConst.STR_NUMBER_2);
			}
			contractMapper.updateByPrimaryKeySelective(contract);
		}

		return JsonResult.success(null, UtilMessage.SAVE_MESSAGE_SUCCESS);
	}

	@Override
	@Transactional
	public JsonResult getEmpList() throws Exception {
		Example example = new Example(GEmployee.class);
		example.createCriteria()
				.andEqualTo("empStatus",UtilConst.STR_NUMBER_1)
				.andEqualTo("delStatus",UtilConst.STR_NUMBER_1);
		List<GEmployee> employees = employeeMapper.selectByExample(example);
		List<GetEmpOutData> outData = new ArrayList<>();
		for(GEmployee employee:employees){
			GetEmpOutData empOutData = new GetEmpOutData();
			empOutData.setEmpId(employee.getId());
			empOutData.setEmpName(employee.getEmpName());
			outData.add(empOutData);
		}
		return JsonResult.success(outData, UtilMessage.SAVE_MESSAGE_SUCCESS);
	}

}
