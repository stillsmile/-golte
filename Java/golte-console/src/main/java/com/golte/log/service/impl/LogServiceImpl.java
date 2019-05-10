package com.golte.log.service.impl;

import com.github.pagehelper.PageHelper;
import com.golte.common.data.JsonResult;
import com.golte.common.data.PageInfo;
import com.golte.common.util.Util;
import com.golte.common.util.UtilConst;
import com.golte.common.util.UtilMessage;
import com.golte.contract.service.data.GetContractProjectOutData;
import com.golte.contract.service.data.GetContractResourceOutData;
import com.golte.log.service.LogService;
import com.golte.log.service.data.GetLogInData;
import com.golte.log.service.data.GetLogOutData;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {

	@Autowired
	private GContractApprovalMapper contractApprovalMapper;

	@Autowired
	private GContractOldMapper contractOldMapper;

	@Autowired
	private GContractUpMapper contractUpMapper;

	@Autowired
	private GContractProjectMapper contractProjectMapper;

	@Autowired
	private GContractProjectOldMapper contractProjectOldMapper;

	@Autowired
	private GContractProjectUpMapper contractProjectUpMapper;

	@Autowired
	private GResourcePointMapper resourcePointMapper;

	@Autowired
	private GContractResourceOldMapper contractResourceOldMapper;

	@Autowired
	private GContractResourceUpMapper contractResourceUpMapper;

	@Autowired
	private GResourceProjectDetailMapper resourceProjectDetailMapper;

	@Autowired
	private GEmployeeMapper employeeMapper;



	@Override
	public JsonResult selectLogList(GetLogInData inData) throws Exception {
		PageInfo<GetLogOutData> pageInfo;
		PageHelper.startPage(inData.getPageNum(), inData.getPageSize());
		List<GetLogOutData> outData = contractApprovalMapper.selectListByCondition(inData);
		for(GetLogOutData data:outData){
			String[] empIds = data.getSupperEmpId().split(",");
			String name = "";
			for (int i = 0;i<empIds.length;i++){
				GEmployee employee = employeeMapper.selectByPrimaryKey(Long.parseLong(empIds[i]));
				if(Util.isEmpty(name)){
					name = employee.getEmpName();
				}else {
					name = name+"，"+employee.getEmpName();
				}
			}
			data.setApproveName(name);
		}
		if (Util.isEmptyList(outData)) {
			pageInfo = new PageInfo<>(new ArrayList<GetLogOutData>());
		} else {
			pageInfo = new PageInfo<>(outData);
		}
		return JsonResult.success(pageInfo, UtilMessage.SEARCH_MESSAGE_SUCCESS);
	}


	@Override
	public JsonResult getLogDetail(GetLogInData inData) throws Exception {
		GContractApproval contractApproval = contractApprovalMapper.selectByPrimaryKey(inData.getId());
		//获取变更前信息
		Example exampleOld = new Example(GContractOld.class);
		exampleOld.createCriteria().andEqualTo("approvalId",contractApproval.getId());
		GContractOld contractOld = contractOldMapper.selectOneByExample(exampleOld);
		GetLogOutData outData = new GetLogOutData();
		outData.setMerchantContact(contractOld.getMerchantContact());
		outData.setMerchantContactPhone(contractOld.getMerchantContactPhone());
		outData.setSigningContact(contractOld.getSigningContact());
		outData.setSigningContactPhone(contractOld.getSigningContactPhone());
		outData.setSigningTime(Util.date2Str(contractOld.getSigningTime(),Util.DATE_YYYY_MM_DD_CHN));
		outData.setDeadlineStartTime(Util.date2Str(contractOld.getDeadlineStartTime(),Util.DATE_YYYY_MM_DD_CHN));
		outData.setDeadlineEndTime(Util.date2Str(contractOld.getDeadlineEndTime(),Util.DATE_YYYY_MM_DD_CHN));
		outData.setAmount(contractOld.getAmount());
		outData.setMargin(contractOld.getMargin());
		outData.setSigningType(contractOld.getSigningType());
		outData.setContractType(contractOld.getContractType());

		Example example = new Example(GContractProjectOld.class);
		example.createCriteria().andEqualTo("contractId",contractOld.getId());
		List<GContractProjectOld> contractProjectOlds = contractProjectOldMapper.selectByExample(example);
		List<GetContractProjectOutData> projects = new ArrayList<>();
		for(GContractProjectOld contractProjectOld:contractProjectOlds){
			GetContractProjectOutData contractProjectOutData = new GetContractProjectOutData();
			contractProjectOutData.setId(contractProjectOld.getId());
			contractProjectOutData.setProjectId(contractProjectOld.getProjectId());
			GResourcePoint resourcePointThree = resourcePointMapper.selectByPrimaryKey(contractProjectOld.getPointId());
			GResourcePoint resourcePointTwo = resourcePointMapper.selectByPrimaryKey(resourcePointThree.getParentId());
			GResourcePoint resourcePointOne = resourcePointMapper.selectByPrimaryKey(resourcePointTwo.getParentId());
			contractProjectOutData.setPointIdThree(resourcePointThree.getId());
			contractProjectOutData.setPointIdTwo(resourcePointTwo.getId());
			contractProjectOutData.setPointIdOne(resourcePointOne.getId());
			contractProjectOutData.setTollMode(contractProjectOld.getTollMode());
			contractProjectOutData.setPaymentCycle(contractProjectOld.getPaymentCycle());
			contractProjectOutData.setUnitPrice(contractProjectOld.getUnitPrice());
			contractProjectOutData.setNumber(contractProjectOld.getNumber());
			contractProjectOutData.setSubtotal(contractProjectOld.getSubtotal());
			Example exampleResource = new Example(GContractResourceOld.class);
			exampleResource.createCriteria().andEqualTo("contractProjectId",contractProjectOld.getId());
			List<GContractResourceOld> contractResourceOlds = contractResourceOldMapper.selectByExample(exampleResource);
			List<GetContractResourceOutData> resources = new ArrayList<>();
			for (GContractResourceOld contractResourceOld:contractResourceOlds){
				GetContractResourceOutData contractResourceOutData = new GetContractResourceOutData();
				contractResourceOutData.setId(contractResourceOld.getId());
				contractResourceOutData.setContractId(contractResourceOld.getContractId());
				contractResourceOutData.setContractProjectId(contractResourceOld.getContractProjectId());
				contractResourceOutData.setResourceId(contractResourceOld.getResourceId());
				GResourceProjectDetail resourceProjectDetail = resourceProjectDetailMapper.selectByPrimaryKey(contractResourceOld.getResourceId());
				contractResourceOutData.setResourceName(resourceProjectDetail.getAssetsName());
				resources.add(contractResourceOutData);
			}
			contractProjectOutData.setResourceList(resources);
			projects.add(contractProjectOutData);
		}
		outData.setProjectList(projects);


		//获取变更后信息
		Example exampleUp = new Example(GContractUp.class);
		exampleUp.createCriteria().andEqualTo("approvalId",contractApproval.getId());
		GContractUp contractUp = contractUpMapper.selectOneByExample(exampleUp);

		outData.setMerchantContactUp(contractUp.getMerchantContact());
		outData.setMerchantContactPhoneUp(contractUp.getMerchantContactPhone());
		outData.setSigningContactUp(contractUp.getSigningContact());
		outData.setSigningContactPhoneUp(contractUp.getSigningContactPhone());
		outData.setSigningTimeUp(Util.date2Str(contractUp.getSigningTime(),Util.DATE_YYYY_MM_DD_CHN));
		outData.setDeadlineStartTimeUp(Util.date2Str(contractUp.getDeadlineStartTime(),Util.DATE_YYYY_MM_DD_CHN));
		outData.setDeadlineEndTimeUp(Util.date2Str(contractUp.getDeadlineEndTime(),Util.DATE_YYYY_MM_DD_CHN));
		outData.setAmountUp(contractUp.getAmount());
		outData.setMarginUp(contractUp.getMargin());
		outData.setSigningTypeUp(contractUp.getSigningType());
		outData.setContractTypeUp(contractUp.getContractType());

		List<GetContractProjectOutData> projectUps = new ArrayList<>();
		for(GContractProjectOld contractProjectOld:contractProjectOlds){
			Example example1 = new Example(GContractProjectUp.class);
			example1.createCriteria().andEqualTo("contractProjectId",contractProjectOld.getContractProjectId())
					.andEqualTo("approvalId",contractApproval.getId());
			GContractProjectUp contractProjectUp = contractProjectUpMapper.selectOneByExample(example1);
			if(!Util.isEmpty(contractProjectUp)){
				if(UtilConst.STR_NUMBER_2.equals(contractProjectUp.getType())){
					//移除
					continue;
				}
				if(UtilConst.STR_NUMBER_3.equals(contractProjectUp.getType())){
					//变更
					GetContractProjectOutData contractProjectOutData = new GetContractProjectOutData();
					contractProjectOutData.setId(contractProjectUp.getId());
					contractProjectOutData.setProjectId(contractProjectUp.getProjectId());
					GResourcePoint resourcePointThree = resourcePointMapper.selectByPrimaryKey(contractProjectUp.getPointId());
					GResourcePoint resourcePointTwo = resourcePointMapper.selectByPrimaryKey(resourcePointThree.getParentId());
					GResourcePoint resourcePointOne = resourcePointMapper.selectByPrimaryKey(resourcePointTwo.getParentId());
					contractProjectOutData.setPointIdThree(resourcePointThree.getId());
					contractProjectOutData.setPointIdTwo(resourcePointTwo.getId());
					contractProjectOutData.setPointIdOne(resourcePointOne.getId());
					contractProjectOutData.setTollMode(contractProjectUp.getTollMode());
					contractProjectOutData.setPaymentCycle(contractProjectUp.getPaymentCycle());
					contractProjectOutData.setUnitPrice(contractProjectUp.getUnitPrice());
					contractProjectOutData.setNumber(contractProjectUp.getNumber());
					contractProjectOutData.setSubtotal(contractProjectUp.getSubtotal());
					Example exampleResourceUp = new Example(GContractResourceUp.class);
					exampleResourceUp.createCriteria().andEqualTo("contractProjectId",contractProjectUp.getId());
					List<GContractResourceUp> contractResourceUps = contractResourceUpMapper.selectByExample(exampleResourceUp);
					List<GetContractResourceOutData> resourceUps = new ArrayList<>();
					for (GContractResourceUp contractResource:contractResourceUps){
						GetContractResourceOutData contractResourceOutData = new GetContractResourceOutData();
						contractResourceOutData.setId(contractResource.getId());
						contractResourceOutData.setContractId(contractResource.getContractId());
						contractResourceOutData.setContractProjectId(contractResource.getContractProjectId());
						contractResourceOutData.setResourceId(contractResource.getResourceId());
						GResourceProjectDetail resourceProjectDetail = resourceProjectDetailMapper.selectByPrimaryKey(contractResource.getResourceId());
						contractResourceOutData.setResourceName(resourceProjectDetail.getAssetsName());
						resourceUps.add(contractResourceOutData);
					}
					contractProjectOutData.setResourceList(resourceUps);
					projectUps.add(contractProjectOutData);
				}
			}else {
				//未变更
				GetContractProjectOutData contractProjectOutData = new GetContractProjectOutData();
				contractProjectOutData.setId(contractProjectOld.getId());
				contractProjectOutData.setProjectId(contractProjectOld.getProjectId());
				GResourcePoint resourcePointThree = resourcePointMapper.selectByPrimaryKey(contractProjectOld.getPointId());
				GResourcePoint resourcePointTwo = resourcePointMapper.selectByPrimaryKey(resourcePointThree.getParentId());
				GResourcePoint resourcePointOne = resourcePointMapper.selectByPrimaryKey(resourcePointTwo.getParentId());
				contractProjectOutData.setPointIdThree(resourcePointThree.getId());
				contractProjectOutData.setPointIdTwo(resourcePointTwo.getId());
				contractProjectOutData.setPointIdOne(resourcePointOne.getId());
				contractProjectOutData.setTollMode(contractProjectOld.getTollMode());
				contractProjectOutData.setPaymentCycle(contractProjectOld.getPaymentCycle());
				contractProjectOutData.setUnitPrice(contractProjectOld.getUnitPrice());
				contractProjectOutData.setNumber(contractProjectOld.getNumber());
				contractProjectOutData.setSubtotal(contractProjectOld.getSubtotal());
				Example exampleResource = new Example(GContractResourceOld.class);
				exampleResource.createCriteria().andEqualTo("contractProjectId",contractProjectOld.getId());
				List<GContractResourceOld> contractResourceOlds = contractResourceOldMapper.selectByExample(exampleResource);
				List<GetContractResourceOutData> resourceUps = new ArrayList<>();
				for (GContractResourceOld contractResourceOld:contractResourceOlds){
					GetContractResourceOutData contractResourceOutData = new GetContractResourceOutData();
					contractResourceOutData.setId(contractResourceOld.getId());
					contractResourceOutData.setContractId(contractResourceOld.getContractId());
					contractResourceOutData.setContractProjectId(contractResourceOld.getContractProjectId());
					contractResourceOutData.setResourceId(contractResourceOld.getResourceId());
					GResourceProjectDetail resourceProjectDetail = resourceProjectDetailMapper.selectByPrimaryKey(contractResourceOld.getResourceId());
					contractResourceOutData.setResourceName(resourceProjectDetail.getAssetsName());
					resourceUps.add(contractResourceOutData);
				}
				contractProjectOutData.setResourceList(resourceUps);
				projectUps.add(contractProjectOutData);
			}
		}
		Example exampleProjectUp = new Example(GContractProjectUp.class);
		exampleProjectUp.createCriteria().andEqualTo("approvalId",contractApproval.getId()).andEqualTo("type",UtilConst.STR_NUMBER_1);
		List<GContractProjectUp> contractProjectUpList = contractProjectUpMapper.selectByExample(exampleProjectUp);
		for(GContractProjectUp contractProjectUp:contractProjectUpList){
			//新增
			GetContractProjectOutData contractProjectOutData = new GetContractProjectOutData();
			contractProjectOutData.setId(contractProjectUp.getId());
			contractProjectOutData.setProjectId(contractProjectUp.getProjectId());
			GResourcePoint resourcePointThree = resourcePointMapper.selectByPrimaryKey(contractProjectUp.getPointId());
			GResourcePoint resourcePointTwo = resourcePointMapper.selectByPrimaryKey(resourcePointThree.getParentId());
			GResourcePoint resourcePointOne = resourcePointMapper.selectByPrimaryKey(resourcePointTwo.getParentId());
			contractProjectOutData.setPointIdThree(resourcePointThree.getId());
			contractProjectOutData.setPointIdTwo(resourcePointTwo.getId());
			contractProjectOutData.setPointIdOne(resourcePointOne.getId());
			contractProjectOutData.setTollMode(contractProjectUp.getTollMode());
			contractProjectOutData.setPaymentCycle(contractProjectUp.getPaymentCycle());
			contractProjectOutData.setUnitPrice(contractProjectUp.getUnitPrice());
			contractProjectOutData.setNumber(contractProjectUp.getNumber());
			contractProjectOutData.setSubtotal(contractProjectUp.getSubtotal());
			Example exampleResource1 = new Example(GContractResourceUp.class);
			exampleResource1.createCriteria().andEqualTo("contractProjectId",contractProjectUp.getId());
			List<GContractResourceUp> contractResources = contractResourceUpMapper.selectByExample(exampleResource1);
			List<GetContractResourceOutData> resources = new ArrayList<>();
			for (GContractResourceUp contractResource:contractResources){
				GetContractResourceOutData contractResourceOutData = new GetContractResourceOutData();
				contractResourceOutData.setId(contractResource.getId());
				contractResourceOutData.setContractId(contractResource.getContractId());
				contractResourceOutData.setContractProjectId(contractResource.getContractProjectId());
				contractResourceOutData.setResourceId(contractResource.getResourceId());
				GResourceProjectDetail resourceProjectDetail = resourceProjectDetailMapper.selectByPrimaryKey(contractResource.getResourceId());
				contractResourceOutData.setResourceName(resourceProjectDetail.getAssetsName());
				resources.add(contractResourceOutData);
			}
			contractProjectOutData.setResourceList(resources);
			projectUps.add(contractProjectOutData);
		}

		outData.setProjectList(projects);
		outData.setProjectListUp(projectUps);
		return JsonResult.success(outData, UtilMessage.SEARCH_MESSAGE_SUCCESS);
	}
}
