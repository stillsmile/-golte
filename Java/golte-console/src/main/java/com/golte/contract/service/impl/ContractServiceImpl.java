package com.golte.contract.service.impl;

import com.auth0.jwt.internal.org.apache.commons.lang3.StringUtils;
import com.github.pagehelper.PageHelper;
import com.golte.common.data.JsonResult;
import com.golte.common.data.PageInfo;
import com.golte.common.exception.ServiceException;
import com.golte.common.util.*;
import com.golte.contract.service.ContractService;
import com.golte.contract.service.data.*;
import com.golte.mapper.*;
import com.golte.mapper.entity.*;
import com.golte.resource.service.data.GetPointExportInData;
import com.golte.system.service.data.GetResourceProjectInData;
import com.golte.system.service.data.GetResourceProjectOutData;
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
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ContractServiceImpl implements ContractService {

	@Autowired
	private GContractMapper contractMapper;

	@Autowired
	private GContractUpMapper contractUpMapper;

	@Autowired
	private GContractProjectMapper contractProjectMapper;

	@Autowired
	private GContractResourceMapper contractResourceMapper;

	@Autowired
	private GAnnexMapper annexMapper;

	@Autowired
	private GCityMapper cityMapper;

	@Autowired
	private GMerchantMapper merchantMapper;

	@Autowired
	private GResourceProjectMapper resourceProjectMapper;

	@Autowired
	private GContractApprovalMapper contractApprovalMapper;

	@Autowired
	private GContractTerminationMapper contractTerminationMapper;

	@Autowired
	private GPaybackPlanMapper paybackPlanMapper;

	@Autowired
	private GPaybackRecordMapper paybackRecordMapper;

	@Autowired
	private GContractProjectUpMapper contractProjectUpMapper;

	@Autowired
	private GContractResourceUpMapper contractResourceUpMapper;

	@Autowired
	private GResourcePointMapper resourcePointMapper;

	@Autowired
	private GResourceProjectDetailMapper resourceProjectDetailMapper;

    @Autowired
    private GEmployeeMapper employeeMapper;

	@Autowired
	private GRoleRelationshipMapper roleRelationshipMapper;

	@Autowired
	private GCentralCityMapper centralCityMapper;

	@Autowired
	private GContractOldMapper contractOldMapper;

	@Autowired
	private GContractProjectOldMapper contractProjectOldMapper;

	@Autowired
	private GContractResourceOldMapper contractResourceOldMapper;


	@Override
	public JsonResult selectContractList(GetContractInData inData) throws Exception {
		PageInfo<GetContractOutData> pageInfo;
		PageHelper.startPage(inData.getPageNum(), inData.getPageSize());
		List<GetContractOutData> outData = contractMapper.selectListByCondition(inData);
		for(GetContractOutData contractOutData:outData){
		    if(UtilConst.STR_NUMBER_3.equals(contractOutData.getContractStatus())){
		        Example example = new Example(GContractApproval.class);
		        example.createCriteria().andEqualTo("contractId",contractOutData.getId())
                        .andEqualTo("type",UtilConst.STR_NUMBER_2)
                        .andEqualTo("status",UtilConst.STR_NUMBER_1);
		        GContractApproval contractApproval = contractApprovalMapper.selectOneByExample(example);
                contractOutData.setApprovalAccount(contractApproval.getSupperEmployeeId());
            }
            if(UtilConst.STR_NUMBER_4.equals(contractOutData.getContractStatus())){
                Example example = new Example(GContractApproval.class);
                example.createCriteria().andEqualTo("contractId",contractOutData.getId())
                        .andEqualTo("type",UtilConst.STR_NUMBER_1)
                        .andEqualTo("status",UtilConst.STR_NUMBER_1);
                GContractApproval contractApproval = contractApprovalMapper.selectOneByExample(example);
                contractOutData.setApprovalAccount(contractApproval.getSupperEmployeeId());
            }
        }
		if (Util.isEmptyList(outData)) {
			pageInfo = new PageInfo<>(new ArrayList<GetContractOutData>());
		} else {
			pageInfo = new PageInfo<>(outData);
		}
		return JsonResult.success(pageInfo, UtilMessage.SEARCH_MESSAGE_SUCCESS);
	}

	@Override
	public JsonResult detailContract(GetContractInData inData) throws Exception {
		GContract contract = contractMapper.selectByPrimaryKey(inData.getId());
		GetContractOutData outData = new GetContractOutData();
		outData.setId(contract.getId());
		outData.setCode(contract.getCode());
		outData.setName(contract.getName());
		outData.setCityId(contract.getCityId());
		outData.setMerchantId(contract.getMerchantId());
		outData.setMerchantContact(contract.getMerchantContact());
		outData.setMerchantContactPhone(contract.getMerchantContactPhone());
		outData.setSigningContact(contract.getSigningContact());
		outData.setSigningContactPhone(contract.getSigningContactPhone());
		if(!Util.isEmpty(contract.getSigningTime())){
			outData.setSigningTime(Util.date2Str(contract.getSigningTime(),Util.DATE_YYYY_MM_DD_CHN));
		}
		outData.setDeadlineStartTime(Util.date2Str(contract.getDeadlineStartTime(),Util.DATE_YYYY_MM_DD_CHN));
		outData.setDeadlineEndTime(Util.date2Str(contract.getDeadlineEndTime(),Util.DATE_YYYY_MM_DD_CHN));
		outData.setAmount(contract.getAmount());
		outData.setMargin(contract.getMargin());
		outData.setSigningType(contract.getSigningType());
		outData.setContractType(contract.getContractType());
		outData.setDarftStatus(contract.getDarftStatus());
		outData.setPaybackType(contract.getPaybackType());
		outData.setContractStatus(contract.getContractStatus());
		outData.setRemarks(contract.getRemarks());

		Example example = new Example(GContractProject.class);
		example.createCriteria().andEqualTo("contractId",contract.getId()).andEqualTo("status",UtilConst.STR_NUMBER_1);
		List<GContractProject> contractProjects = contractProjectMapper.selectByExample(example);
		List<GetContractProjectOutData> projects = new ArrayList<>();
		for(GContractProject contractProject:contractProjects){
			GetContractProjectOutData contractProjectOutData = new GetContractProjectOutData();
			contractProjectOutData.setId(contractProject.getId());
			contractProjectOutData.setProjectId(contractProject.getProjectId());
			if(!Util.isEmpty(contractProject.getPointId())){
				GResourcePoint resourcePointThree = resourcePointMapper.selectByPrimaryKey(contractProject.getPointId());
				GResourcePoint resourcePointTwo = resourcePointMapper.selectByPrimaryKey(resourcePointThree.getParentId());
				GResourcePoint resourcePointOne = resourcePointMapper.selectByPrimaryKey(resourcePointTwo.getParentId());
				contractProjectOutData.setPointIdThree(resourcePointThree.getId());
				contractProjectOutData.setPointIdTwo(resourcePointTwo.getId());
				contractProjectOutData.setPointIdOne(resourcePointOne.getId());
			}
			contractProjectOutData.setTollMode(contractProject.getTollMode());
			contractProjectOutData.setPaymentCycle(contractProject.getPaymentCycle());
			contractProjectOutData.setUnitPrice(contractProject.getUnitPrice());
			contractProjectOutData.setNumber(contractProject.getNumber());
			contractProjectOutData.setUnitName(contractProject.getUnitName());
			contractProjectOutData.setSubtotal(contractProject.getSubtotal());
			contractProjectOutData.setStatus(contractProject.getStatus());
			Example exampleResource = new Example(GContractResource.class);
			exampleResource.createCriteria().andEqualTo("contractProjectId",contractProject.getId());
			List<GContractResource> contractResources = contractResourceMapper.selectByExample(exampleResource);
			List<GetContractResourceOutData> resources = new ArrayList<>();
			for (GContractResource contractResource:contractResources){
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
			projects.add(contractProjectOutData);
		}
		outData.setProjectList(projects);

		Example exampleAnnex = new Example(GAnnex.class);
		exampleAnnex.createCriteria().andEqualTo("associationId",contract.getId()).andEqualTo("type",UtilConst.STR_NUMBER_1);
		List<GAnnex> annexes = annexMapper.selectByExample(exampleAnnex);
		List<GetAnnexOutData> annexList = new ArrayList<>();
		for(GAnnex annex:annexes){
			GetAnnexOutData annexOutData = new GetAnnexOutData();
			annexOutData.setType(annex.getType());
			annexOutData.setAssociationId(annex.getAssociationId());
			annexOutData.setName(annex.getName());
			annexOutData.setUrl(annex.getUrl());
			annexList.add(annexOutData);
		}
		outData.setAnnexList(annexList);

        //获取审批历史
		List<GetApproveOutData> approveOutDataList = new ArrayList<>();
		List<GContractApproval> contractApprovalList = new ArrayList<>();
		if(UtilConst.STR_NUMBER_1.equals(inData.getFlag())){
			//获取终止信息
			Example exampleApprove = new Example(GContractApproval.class);
			exampleApprove.createCriteria().andEqualTo("contractId",contract.getId())
					.andEqualTo("status",UtilConst.STR_NUMBER_2)
					.andEqualTo("type",UtilConst.STR_NUMBER_1);
			GContractApproval contractApproval = contractApprovalMapper.selectOneByExample(exampleApprove);
			if(!Util.isEmpty(contractApproval)){
				Example exampleStop = new Example(GContractTermination.class);
				exampleStop.createCriteria().andEqualTo("approvalId",contractApproval.getId());
				GContractTermination contractTermination = contractTerminationMapper.selectOneByExample(exampleStop);

				outData.setId(contract.getId());
				outData.setTerminationTime(Util.date2Str(contractTermination.getTerminationTime(),Util.DATE_YYYY_MM_DD_CHN));
				outData.setNotRecoveredAmount(contractTermination.getNotRecoveredAmount());
				outData.setReason(contractTermination.getReason());

				Example exampleStopAnnex = new Example(GAnnex.class);
				exampleStopAnnex.createCriteria().andEqualTo("associationId",contract.getId()).andEqualTo("type",UtilConst.STR_NUMBER_4);
				List<GAnnex> stopAnnexs = annexMapper.selectByExample(exampleStopAnnex);
				List<GetAnnexOutData> stopAnnexList = new ArrayList<>();
				for(GAnnex annex:stopAnnexs){
					GetAnnexOutData annexOutData = new GetAnnexOutData();
					annexOutData.setType(annex.getType());
					annexOutData.setAssociationId(annex.getAssociationId());
					annexOutData.setName(annex.getName());
					annexOutData.setUrl(annex.getUrl());
					stopAnnexList.add(annexOutData);
				}
				outData.setStopAnnexList(stopAnnexList);
			}

			//获取变更信息
			Example exampleChangeApprove = new Example(GContractApproval.class);
			exampleChangeApprove.createCriteria().andEqualTo("contractId",contract.getId())
					.andEqualTo("status",UtilConst.STR_NUMBER_2)
					.andEqualTo("type",UtilConst.STR_NUMBER_2);
			exampleChangeApprove.setOrderByClause("id DESC");
			List<GContractApproval> contractApprovalList1 = contractApprovalMapper.selectByExample(exampleChangeApprove);
			if(!Util.isEmptyList(contractApprovalList1)){
				Example exampleUp = new Example(GContractUp.class);
				exampleUp.createCriteria().andEqualTo("approvalId",contractApprovalList1.get(0).getId());
				GContractUp contractUp = contractUpMapper.selectOneByExample(exampleUp);
				outData.setChangeReason(contractUp.getTerminationReason());

				Example exampleChangeAnnex = new Example(GAnnex.class);
				exampleChangeAnnex.createCriteria().andEqualTo("associationId",contract.getId()).andEqualTo("type",UtilConst.STR_NUMBER_5);
				List<GAnnex> changeAnnexs = annexMapper.selectByExample(exampleChangeAnnex);
				List<GetAnnexOutData> changeAnnexList = new ArrayList<>();
				for(GAnnex annex:changeAnnexs){
					GetAnnexOutData annexOutData = new GetAnnexOutData();
					annexOutData.setType(annex.getType());
					annexOutData.setAssociationId(annex.getAssociationId());
					annexOutData.setName(annex.getName());
					annexOutData.setUrl(annex.getUrl());
					changeAnnexList.add(annexOutData);
				}
				outData.setChangeAnnexList(changeAnnexList);
			}

			Example example1 = new Example(GContractApproval.class);
			example1.createCriteria()
					.andEqualTo("contractId",contract.getId());
			example1.setOrderByClause("id ASC");
			contractApprovalList = contractApprovalMapper.selectByExample(example1);
		}
		if(UtilConst.STR_NUMBER_2.equals(inData.getFlag())){
			Example example1 = new Example(GContractApproval.class);
			example1.createCriteria()
					.andEqualTo("contractId",contract.getId())
					.andEqualTo("type",UtilConst.STR_NUMBER_2);
			example1.setOrderByClause("id ASC");
			contractApprovalList = contractApprovalMapper.selectByExample(example1);
		}
		if(UtilConst.STR_NUMBER_3.equals(inData.getFlag())){
			Example example1 = new Example(GContractApproval.class);
			example1.createCriteria()
					.andEqualTo("contractId",contract.getId())
					.andEqualTo("type",UtilConst.STR_NUMBER_1);
			example1.setOrderByClause("id ASC");
			contractApprovalList = contractApprovalMapper.selectByExample(example1);
		}

        for(GContractApproval data:contractApprovalList){
            GetApproveOutData approveOutData = new GetApproveOutData();
            approveOutData.setName(data.getCreateName());
            approveOutData.setTime(Util.date2Str(data.getCreateTime(),Util.DATE_TIME_PAT_19_));
            if(UtilConst.STR_NUMBER_2.equals(data.getType())){
                approveOutData.setType("申请变更");
            }else {
                approveOutData.setType("申请终止");
            }
            approveOutDataList.add(approveOutData);
            if(!UtilConst.STR_NUMBER_1.equals(data.getStatus())){
				GEmployee employee = employeeMapper.selectByPrimaryKey(data.getApproveEmployeeId());
                GetApproveOutData approveOutData1 = new GetApproveOutData();
                approveOutData1.setName(employee.getEmpName());
                approveOutData1.setTime(Util.date2Str(data.getApprovalTime(),Util.DATE_TIME_PAT_19_));
                if(UtilConst.STR_NUMBER_2.equals(data.getStatus())){
                    approveOutData1.setType("审批通过");
                }
                if(UtilConst.STR_NUMBER_3.equals(data.getStatus())){
                    approveOutData1.setType("审批拒绝");
                }
                approveOutData1.setRemark(data.getOpinion());
                approveOutDataList.add(approveOutData1);
            }
        }
        outData.setApproveList(approveOutDataList);
		return JsonResult.success(outData, UtilMessage.SEARCH_MESSAGE_SUCCESS);
	}

	@Override
	@Transactional
	public JsonResult addContract(GetContractInData inData) throws Exception {
		Example example = new Example(GContract.class);
		example.createCriteria().andEqualTo("code",inData.getCode())
				.andEqualTo("delStatus",UtilConst.STR_NUMBER_1);
		List<GContract> contractList = contractMapper.selectByExample(example);
		if(!Util.isEmptyList(contractList)){
			return JsonResult.fail(UtilConst.CODE_403,"合同编号已存在");
		}

		Example example1 = new Example(GContract.class);
		example1.createCriteria().andEqualTo("name",inData.getName())
				.andEqualTo("delStatus",UtilConst.STR_NUMBER_1);
		List<GContract> contractList1 = contractMapper.selectByExample(example1);
		if(!Util.isEmptyList(contractList1)){
			return JsonResult.fail(UtilConst.CODE_403,"合同名称已存在");
		}

		GCity city = cityMapper.selectByPrimaryKey(inData.getCityId());
		//保存合同信息
		GContract contract = new GContract();
		contract.setCode(inData.getCode());
		contract.setName(inData.getName());
		contract.setCityId(inData.getCityId());
		contract.setCentralCityId(city.getCentralCityId());
		contract.setMerchantId(inData.getMerchantId());
		contract.setMerchantContact(inData.getMerchantContact());
		contract.setMerchantContactPhone(inData.getMerchantContactPhone());
		contract.setSigningContact(inData.getSigningContact());
		contract.setSigningContactPhone(inData.getSigningContactPhone());
		contract.setSigningTime(inData.getSigningTime());
		contract.setDeadlineStartTime(inData.getDeadlineStartTime());
		contract.setDeadlineEndTime(inData.getDeadlineEndTime());
		contract.setAmount(inData.getAmount());
		contract.setMargin(inData.getMargin());
		contract.setSigningType(inData.getSigningType());
		contract.setContractType(inData.getContractType());
		contract.setDarftStatus(inData.getDarftStatus());
		contract.setPaybackType(UtilConst.STR_NUMBER_1);
		if(UtilConst.STR_NUMBER_0.equals(inData.getDarftStatus())){
			contract.setContractStatus(UtilConst.STR_NUMBER_2);
		}else {
			contract.setContractStatus(UtilConst.STR_NUMBER_1);
		}
		contract.setRemarks(inData.getRemarks());
		contract.setCreateTime(new Date());
		contract.setCreateName(inData.getLoginName());
		contractMapper.insertSelective(contract);

		//保存项目列表
		if(!Util.isEmptyList(inData.getProjectList())){
			for(GetContractProjectInData contractProjectInData:inData.getProjectList()){
				GContractProject contractProject = new GContractProject();
				contractProject.setContractId(contract.getId());
				contractProject.setProjectId(contractProjectInData.getProjectId());
				contractProject.setPointId(contractProjectInData.getPointId());
				contractProject.setTollMode(contractProjectInData.getTollMode());
				contractProject.setPaymentCycle(contractProjectInData.getPaymentCycle());
				contractProject.setUnitPrice(contractProjectInData.getUnitPrice());
				contractProject.setNumber(contractProjectInData.getNumber());
				contractProject.setUnitName(contractProjectInData.getUnitName());
				contractProject.setSubtotal(contractProjectInData.getSubtotal());
				contractProject.setStatus(UtilConst.STR_NUMBER_1);
				contractProject.setCreateTime(new Date());
				contractProjectMapper.insertSelective(contractProject);
				//保存资源列表
				if(!Util.isEmptyList(contractProjectInData.getResourceList())){
					for (GetContractResourceInData contractResourceInData:contractProjectInData.getResourceList()){
						GContractResource contractResource = new GContractResource();
						contractResource.setContractId(contract.getId());
						contractResource.setContractProjectId(contractProject.getId());
						contractResource.setResourceId(contractResourceInData.getResourceId());
						contractResourceMapper.insertSelective(contractResource);
					}
				}
				if(UtilConst.STR_NUMBER_0.equals(inData.getDarftStatus())){
					//生成回款计划
					addPayBackPlan(contract,contractProject);
				}
			}
		}

		//保存附件列表
		if(!Util.isEmptyList(inData.getAnnexList())){
			for(GetAnnexInData annexInData:inData.getAnnexList()){
				GAnnex annex = new GAnnex();
				annex.setType(UtilConst.STR_NUMBER_1);
				annex.setAssociationId(contract.getId());
				annex.setName(annexInData.getName());
				annex.setUrl(annexInData.getUrl());
				annex.setCreateTime(new Date());
				annexMapper.insertSelective(annex);
			}
		}
		if(UtilConst.STR_NUMBER_0.equals(inData.getDarftStatus())){
			return JsonResult.success(contract.getId(), UtilMessage.SAVE_MESSAGE_SUCCESS);
		}else {
			return JsonResult.success(null, UtilMessage.SAVE_MESSAGE_SUCCESS);
		}
	}

	@Override
	@Transactional
	public JsonResult updateContract(GetContractInData inData) throws Exception {
		Example exampleCode = new Example(GContract.class);
		exampleCode.createCriteria().andEqualTo("code",inData.getCode())
				.andEqualTo("delStatus",UtilConst.STR_NUMBER_1)
				.andEqualTo("darftStatus",UtilConst.STR_NUMBER_0);
		List<GContract> contractList = contractMapper.selectByExample(exampleCode);
		if(!Util.isEmptyList(contractList)){
			return JsonResult.fail(UtilConst.CODE_403,"合同编号已存在");
		}

		Example exampleName = new Example(GContract.class);
		exampleName.createCriteria().andEqualTo("name",inData.getName())
				.andEqualTo("delStatus",UtilConst.STR_NUMBER_1)
				.andEqualTo("darftStatus",UtilConst.STR_NUMBER_0);
		List<GContract> contractList1 = contractMapper.selectByExample(exampleName);
		if(!Util.isEmptyList(contractList1)){
			return JsonResult.fail(UtilConst.CODE_403,"合同名称已存在");
		}

		//更新合同信息
		GContract contract = contractMapper.selectByPrimaryKey(inData.getId());
		contract.setCode(inData.getCode());
		contract.setName(inData.getName());
		contract.setCityId(inData.getCityId());
		contract.setMerchantId(inData.getMerchantId());
		contract.setMerchantContact(inData.getMerchantContact());
		contract.setMerchantContactPhone(inData.getMerchantContactPhone());
		contract.setSigningContact(inData.getSigningContact());
		contract.setSigningContactPhone(inData.getSigningContactPhone());
		contract.setSigningTime(inData.getSigningTime());
		contract.setDeadlineStartTime(inData.getDeadlineStartTime());
		contract.setDeadlineEndTime(inData.getDeadlineEndTime());
		contract.setAmount(inData.getAmount());
		contract.setMargin(inData.getMargin());
		contract.setSigningType(inData.getSigningType());
		contract.setContractType(inData.getContractType());
		contract.setDarftStatus(inData.getDarftStatus());
		contract.setPaybackType(UtilConst.STR_NUMBER_1);
        if(UtilConst.STR_NUMBER_0.equals(inData.getDarftStatus())){
            contract.setContractStatus(UtilConst.STR_NUMBER_2);
        }else {
            contract.setContractStatus(UtilConst.STR_NUMBER_1);
        }
		contract.setRemarks(inData.getRemarks());
		contract.setCreateTime(new Date());
		contract.setCreateName(inData.getLoginName());
		contractMapper.updateByPrimaryKey(contract);

		//保存项目列表
		if(!Util.isEmptyList(inData.getProjectList())){
			Example example = new Example(GContractProject.class);
			example.createCriteria().andEqualTo("contractId",contract.getId());
			contractProjectMapper.deleteByExample(example);
			Example exampleResource = new Example(GContractResource.class);
			exampleResource.createCriteria().andEqualTo("contractId",contract.getId());
			contractResourceMapper.deleteByExample(exampleResource);
			for(GetContractProjectInData contractProjectInData:inData.getProjectList()){
				GContractProject contractProject = new GContractProject();
				contractProject.setContractId(contract.getId());
				contractProject.setProjectId(contractProjectInData.getProjectId());
				contractProject.setPointId(contractProjectInData.getPointId());
				contractProject.setTollMode(contractProjectInData.getTollMode());
				contractProject.setPaymentCycle(contractProjectInData.getPaymentCycle());
				contractProject.setUnitPrice(contractProjectInData.getUnitPrice());
				contractProject.setNumber(contractProjectInData.getNumber());
				contractProject.setUnitName(contractProjectInData.getUnitName());
				contractProject.setSubtotal(contractProjectInData.getSubtotal());
				contractProject.setStatus(UtilConst.STR_NUMBER_1);
				contractProject.setCreateTime(new Date());
				contractProjectMapper.insertSelective(contractProject);
				//保存资源列表
				if(!Util.isEmptyList(contractProjectInData.getResourceList())){
					for (GetContractResourceInData contractResourceInData:contractProjectInData.getResourceList()){
						GContractResource contractResource = new GContractResource();
						contractResource.setContractId(contract.getId());
						contractResource.setContractProjectId(contractProject.getId());
						contractResource.setResourceId(contractResourceInData.getResourceId());
						contractResourceMapper.insertSelective(contractResource);
					}
				}
				if(UtilConst.STR_NUMBER_0.equals(inData.getDarftStatus())){
					//生成回款计划
					addPayBackPlan(contract,contractProject);
				}
			}
		}

		//保存附件列表
		Example example = new Example(GAnnex.class);
		example.createCriteria().andEqualTo("associationId",contract.getId());
		annexMapper.deleteByExample(example);
		for(GetAnnexInData annexInData:inData.getAnnexList()){
			GAnnex annex = new GAnnex();
			annex.setType(UtilConst.STR_NUMBER_1);
			annex.setAssociationId(contract.getId());
			annex.setName(annexInData.getName());
			annex.setUrl(annexInData.getUrl());
			annex.setCreateTime(new Date());
			annexMapper.insertSelective(annex);
		}
		if(UtilConst.STR_NUMBER_0.equals(inData.getDarftStatus())){
			return JsonResult.success(contract.getId(), UtilMessage.SAVE_MESSAGE_SUCCESS);
		}else {
			return JsonResult.success(null, UtilMessage.SAVE_MESSAGE_SUCCESS);
		}
	}

	@Override
	@Transactional
	public JsonResult stopContract(GetContractInData inData) throws Exception {
		GContract contract = contractMapper.selectByPrimaryKey(inData.getId());
		contract.setContractStatus(UtilConst.STR_NUMBER_4);
		contractMapper.updateByPrimaryKeySelective(contract);

		//保存审批信息
		GContractApproval contractApproval = saveApproveInfo(inData.getEmpId(),contract,UtilConst.STR_NUMBER_1);

		GContractTermination contractTermination = new GContractTermination();
		contractTermination.setApprovalId(contractApproval.getId());
		contractTermination.setContractId(contract.getId());
		contractTermination.setNotRecoveredAmount(inData.getNotRecoveredAmount());
		contractTermination.setReason(inData.getReason());
		contractTermination.setCreateTime(new Date());
		contractTermination.setTerminationTime(inData.getTerminationTime());
		contractTerminationMapper.insertSelective(contractTermination);

		//保存附件列表
		if(!Util.isEmptyList(inData.getAnnexList())){
			Example example = new Example(GAnnex.class);
			example.createCriteria()
					.andEqualTo("associationId",contract.getId())
					.andEqualTo("type",UtilConst.STR_NUMBER_4);
			annexMapper.deleteByExample(example);
			for(GetAnnexInData annexInData:inData.getAnnexList()){
				GAnnex annex = new GAnnex();
				annex.setType(UtilConst.STR_NUMBER_4);
				annex.setAssociationId(contract.getId());
				annex.setName(annexInData.getName());
				annex.setUrl(annexInData.getUrl());
				annex.setCreateTime(new Date());
				annexMapper.insertSelective(annex);
			}
		}
		return JsonResult.success(null, UtilMessage.SAVE_MESSAGE_SUCCESS);
	}

    @Override
    public JsonResult stopContractDetail(GetContractInData inData) throws Exception {
        GContract contract = contractMapper.selectByPrimaryKey(inData.getId());
        Example exampleApprove = new Example(GContractApproval.class);
        exampleApprove.createCriteria().andEqualTo("contractId",contract.getId())
                .andEqualTo("status",UtilConst.STR_NUMBER_1)
                .andEqualTo("type",UtilConst.STR_NUMBER_1);
        GContractApproval contractApproval = contractApprovalMapper.selectOneByExample(exampleApprove);

        Example exampleStop = new Example(GContractTermination.class);
        exampleStop.createCriteria().andEqualTo("approvalId",contractApproval.getId());
        GContractTermination contractTermination = contractTerminationMapper.selectOneByExample(exampleStop);

        GetContractOutData outData = new GetContractOutData();
        outData.setId(contract.getId());
        outData.setTerminationTime(Util.date2Str(contractTermination.getTerminationTime(),Util.DATE_YYYY_MM_DD_CHN));
        outData.setNotRecoveredAmount(contractTermination.getNotRecoveredAmount());
        outData.setReason(contractTermination.getReason());

        Example exampleAnnex = new Example(GAnnex.class);
        exampleAnnex.createCriteria().andEqualTo("associationId",contract.getId()).andEqualTo("type",UtilConst.STR_NUMBER_4);
        List<GAnnex> annexes = annexMapper.selectByExample(exampleAnnex);
        List<GetAnnexOutData> annexList = new ArrayList<>();
        for(GAnnex annex:annexes){
            GetAnnexOutData annexOutData = new GetAnnexOutData();
            annexOutData.setType(annex.getType());
            annexOutData.setAssociationId(annex.getAssociationId());
            annexOutData.setName(annex.getName());
            annexOutData.setUrl(annex.getUrl());
            annexList.add(annexOutData);
        }
        outData.setAnnexList(annexList);

		//获取审批历史
		List<GetApproveOutData> approveOutDataList = new ArrayList<>();
		Example example1 = new Example(GContractApproval.class);
		example1.createCriteria()
				.andEqualTo("contractId",contract.getId())
				.andEqualTo("type",UtilConst.STR_NUMBER_1);
		example1.setOrderByClause("id ASC");
		List<GContractApproval> contractApprovalList = contractApprovalMapper.selectByExample(example1);
		for(GContractApproval data:contractApprovalList){
			GetApproveOutData approveOutData = new GetApproveOutData();
			approveOutData.setName(data.getCreateName());
			approveOutData.setTime(Util.date2Str(data.getCreateTime(),Util.DATE_TIME_PAT_19_));
			approveOutData.setType("提交申请");
			approveOutDataList.add(approveOutData);
			if(!UtilConst.STR_NUMBER_1.equals(data.getStatus())){
				GEmployee employee = employeeMapper.selectByPrimaryKey(data.getApproveEmployeeId());
				GetApproveOutData approveOutData1 = new GetApproveOutData();
				approveOutData1.setName(employee.getEmpName());
				approveOutData1.setTime(Util.date2Str(data.getApprovalTime(),Util.DATE_TIME_PAT_19_));
				if(UtilConst.STR_NUMBER_2.equals(data.getStatus())){
					approveOutData1.setType("审批通过");
				}
				if(UtilConst.STR_NUMBER_3.equals(data.getStatus())){
					approveOutData1.setType("审批拒绝");
				}
				approveOutData1.setRemark(data.getOpinion());
				approveOutDataList.add(approveOutData1);
			}
		}
		outData.setApproveList(approveOutDataList);
        return JsonResult.success(outData, UtilMessage.SEARCH_MESSAGE_SUCCESS);
    }

	@Override
	@Transactional
	public JsonResult approveStopContract(GetContractInData inData) throws Exception {
		//更新合同审批状态
		GContract contract = contractMapper.selectByPrimaryKey(inData.getId());
        if(UtilConst.STR_NUMBER_2.equals(inData.getApprovalStatus())){
            contract.setContractStatus(UtilConst.STR_NUMBER_6);
        }
        if(UtilConst.STR_NUMBER_3.equals(inData.getApprovalStatus())){
            contract.setContractStatus(UtilConst.STR_NUMBER_2);
        }
		contractMapper.updateByPrimaryKeySelective(contract);

		//更新审批表审批状态
		Example example = new Example(GContractApproval.class);
		example.createCriteria().andEqualTo("contractId",contract.getId()).andEqualTo("status",UtilConst.STR_NUMBER_1);
		GContractApproval contractApproval = contractApprovalMapper.selectOneByExample(example);
        contractApproval.setStatus(inData.getApprovalStatus());
		contractApproval.setOpinion(inData.getOpinion());
        contractApproval.setApprovalTime(new Date());
		contractApproval.setApproveEmployeeId(inData.getEmpId());
		contractApprovalMapper.updateByPrimaryKeySelective(contractApproval);

		if(UtilConst.STR_NUMBER_2.equals(inData.getApprovalStatus())){
            //获取终止记录
            Example exampleTermination = new Example(GContractTermination.class);
            exampleTermination.createCriteria().andEqualTo("approvalId",contractApproval.getId());
            GContractTermination contractTermination = contractTerminationMapper.selectOneByExample(exampleTermination);
            //终止更新回款计划
            Example example1 = new Example(GPaybackPlan.class);
            example1.createCriteria().andEqualTo("contractId",contract.getId())
					.andGreaterThan("paybackTime",contractTermination.getTerminationTime());
            List<GPaybackPlan> paybackPlanList = paybackPlanMapper.selectByExample(example1);
            for(GPaybackPlan paybackPlan:paybackPlanList){
                paybackPlan.setStatus(UtilConst.STR_NUMBER_2);
                paybackPlanMapper.updateByPrimaryKeySelective(paybackPlan);
            }
        }
		return JsonResult.success(null, UtilMessage.SAVE_MESSAGE_SUCCESS);
	}

	@Override
	@Transactional
	public JsonResult changeContract(GetContractInData inData) throws Exception {
		List<GetContractProjectInData> projects = inData.getProjectList();
		List<GetContractProjectInData> updateProjects =new ArrayList<>();
		List<Long> ids = new ArrayList<>();
		for(GetContractProjectInData object:projects){
			if(!Util.isEmpty(object.getId())){
				updateProjects.add(object);
				ids.add(object.getId());
			}
		}
		GContract contract = contractMapper.selectByPrimaryKey(inData.getId());
		Example example = new Example(GContractProject.class);
		example.createCriteria().andEqualTo("contractId",contract.getId()).andEqualTo("status",UtilConst.STR_NUMBER_1);
		List<GContractProject> projectList = contractProjectMapper.selectByExample(example);
		//更新合同审核状态
		contract.setContractStatus(UtilConst.STR_NUMBER_3);
		contractMapper.updateByPrimaryKeySelective(contract);

		//保存审批信息
		GContractApproval contractApproval = saveApproveInfo(inData.getEmpId(),contract,UtilConst.STR_NUMBER_2);

		//添加合同变更信息
		GContractUp contractUp = new GContractUp();
		contractUp.setContractId(inData.getId());
		contractUp.setCode(inData.getCode());
		contractUp.setName(inData.getName());
		contractUp.setMerchantId(inData.getMerchantId());
		contractUp.setMerchantContact(inData.getMerchantContact());
		contractUp.setMerchantContactPhone(inData.getMerchantContactPhone());
		contractUp.setSigningContact(inData.getSigningContact());
		contractUp.setSigningContactPhone(inData.getSigningContactPhone());
		contractUp.setSigningTime(inData.getSigningTime());
		contractUp.setDeadlineStartTime(inData.getDeadlineStartTime());
		contractUp.setDeadlineEndTime(inData.getDeadlineEndTime());
		contractUp.setAmount(inData.getAmount());
		contractUp.setMargin(inData.getMargin());
		contractUp.setSigningType(inData.getSigningType());
		contractUp.setContractType(inData.getContractType());
		contractUp.setDarftStatus(inData.getDarftStatus());
		contractUp.setPaybackType(UtilConst.STR_NUMBER_1);
		contractUp.setContractStatus(UtilConst.STR_NUMBER_3);
		contractUp.setDelStatus(UtilConst.STR_NUMBER_1);
		contractUp.setApprovalId(contractApproval.getId());
		contractUp.setRemarks(inData.getRemarks());
		contractUp.setTerminationReason(inData.getReason());
		contractUp.setCreateTime(new Date());
		contractUp.setCreateName(inData.getLoginName());
		contractUpMapper.insertSelective(contractUp);

		//插入审核附件
		if(!Util.isEmptyList(inData.getAnnexList())){
			Example exampleAnnex = new Example(GAnnex.class);
			exampleAnnex.createCriteria()
					.andEqualTo("associationId",contract.getId())
					.andEqualTo("type",UtilConst.STR_NUMBER_5);
			annexMapper.deleteByExample(exampleAnnex);
			for(GetAnnexInData annexInData:inData.getAnnexList()){
				GAnnex annex = new GAnnex();
				annex.setUrl(annexInData.getUrl());
				annex.setName(annexInData.getName());
				annex.setType(UtilConst.STR_NUMBER_5);
				annex.setAssociationId(contract.getId());
				annex.setCreateTime(new Date());
				annexMapper.insertSelective(annex);
			}
		}

		if(updateProjects.size()<projectList.size()){
			//移除项目
			for(GContractProject contractProject:projectList){
				if(!ids.contains(contractProject.getId())){
					//插入移除记录
					GContractProjectUp contractProjectUp = new GContractProjectUp();
					contractProjectUp.setApprovalId(contractApproval.getId());
					contractProjectUp.setContractId(contract.getId());
					contractProjectUp.setContractProjectId(contractProject.getId());
					contractProjectUp.setProjectId(contractProject.getProjectId());
					contractProjectUp.setTollMode(contractProject.getTollMode());
					contractProjectUp.setPaymentCycle(contractProject.getPaymentCycle());
					contractProjectUp.setUnitPrice(contractProject.getUnitPrice());
					contractProjectUp.setNumber(contractProject.getNumber());
					contractProjectUp.setSubtotal(contractProject.getSubtotal());
					contractProjectUp.setType(UtilConst.STR_NUMBER_2);
					contractProjectUp.setReason(inData.getReason());
					contractProjectUp.setEffectiveTime(new Date());
					contractProjectUp.setCreateTime(new Date());
					contractProjectUpMapper.insertSelective(contractProjectUp);
				}
			}
		}

		for(GetContractProjectInData contractProjectInData:projects){
			if(Util.isEmpty(contractProjectInData.getId())){
				//插入新增记录
				GContractProjectUp contractProjectUp = new GContractProjectUp();
				contractProjectUp.setApprovalId(contractApproval.getId());
				contractProjectUp.setContractId(contract.getId());
				contractProjectUp.setProjectId(contractProjectInData.getProjectId());
				contractProjectUp.setPointId(contractProjectInData.getPointId());
				contractProjectUp.setTollMode(contractProjectInData.getTollMode());
				contractProjectUp.setPaymentCycle(contractProjectInData.getPaymentCycle());
				contractProjectUp.setUnitPrice(contractProjectInData.getUnitPrice());
				contractProjectUp.setNumber(contractProjectInData.getNumber());
				contractProjectUp.setUnitName(contractProjectInData.getUnitName());
				contractProjectUp.setSubtotal(contractProjectInData.getSubtotal());
				contractProjectUp.setType(UtilConst.STR_NUMBER_1);
				contractProjectUp.setReason(inData.getReason());
				contractProjectUp.setEffectiveTime(new Date());
				contractProjectUp.setCreateTime(new Date());
				contractProjectUpMapper.insertSelective(contractProjectUp);
				//保存资源列表
				if(!Util.isEmptyList(contractProjectInData.getResourceList())){
					for (GetContractResourceInData contractResourceInData:contractProjectInData.getResourceList()){
						GContractResourceUp contractResourceUp = new GContractResourceUp();
						contractResourceUp.setContractId(contract.getId());
						contractResourceUp.setContractProjectId(contractProjectUp.getId());
						contractResourceUp.setResourceId(contractResourceInData.getResourceId());
						contractResourceUpMapper.insertSelective(contractResourceUp);
					}
				}
			}else {
				GContractProject contractProject = contractProjectMapper.selectByPrimaryKey(contractProjectInData.getId());
				if(Util.isEmpty(contractProjectInData.getUnitPrice())){
					contractProjectInData.setUnitPrice(new BigDecimal(0));
				}
				if(Util.isEmpty(contractProjectInData.getNumber())){
					contractProjectInData.setNumber(0);
				}
				if(Util.isEmpty(contractProject.getUnitPrice())){
					contractProject.setUnitPrice(new BigDecimal(0));
				}
				if(Util.isEmpty(contractProject.getNumber())){
					contractProject.setNumber(0);
				}
				if(!contractProjectInData.getTollMode().equals(contractProject.getTollMode())
						||!contractProjectInData.getPaymentCycle().equals(contractProject.getPaymentCycle())
						||contractProjectInData.getUnitPrice().doubleValue()!=contractProject.getUnitPrice().doubleValue()
						||!contractProjectInData.getNumber().equals(contractProject.getNumber())
						||!Util.date2Str(inData.getDeadlineEndTime(),Util.DATE_YYYY_MM_DD_CHN).equals(Util.date2Str(contract.getDeadlineEndTime(),Util.DATE_YYYY_MM_DD_CHN))){
					//插入变更记录
					GContractProjectUp contractProjectUp = new GContractProjectUp();
					contractProjectUp.setApprovalId(contractApproval.getId());
					contractProjectUp.setContractId(contract.getId());
                    contractProjectUp.setContractProjectId(contractProject.getId());
					contractProjectUp.setProjectId(contractProjectInData.getProjectId());
					contractProjectUp.setPointId(contractProjectInData.getPointId());
					contractProjectUp.setTollMode(contractProjectInData.getTollMode());
					contractProjectUp.setPaymentCycle(contractProjectInData.getPaymentCycle());
					contractProjectUp.setUnitPrice(contractProjectInData.getUnitPrice());
					contractProjectUp.setNumber(contractProjectInData.getNumber());
					contractProjectUp.setUnitName(contractProjectInData.getUnitName());
					contractProjectUp.setSubtotal(contractProjectInData.getSubtotal());
					contractProjectUp.setType(UtilConst.STR_NUMBER_3);
					contractProjectUp.setReason(inData.getReason());
					contractProjectUp.setEffectiveTime(new Date());
					contractProjectUp.setCreateTime(new Date());
					contractProjectUpMapper.insertSelective(contractProjectUp);
					//保存资源列表
					if(!Util.isEmptyList(contractProjectInData.getResourceList())){
						for (GetContractResourceInData contractResourceInData:contractProjectInData.getResourceList()){
							GContractResourceUp contractResourceUp = new GContractResourceUp();
							contractResourceUp.setContractId(contract.getId());
							contractResourceUp.setContractProjectId(contractProjectUp.getId());
							contractResourceUp.setResourceId(contractResourceInData.getResourceId());
							contractResourceUpMapper.insertSelective(contractResourceUp);
						}
					}
				}
			}
		}

		//备份合同原信息
		GContractOld contractOld = new GContractOld();
		contractOld.setContractId(contract.getId());
		contractOld.setApprovalId(contractApproval.getId());
		contractOld.setCode(contract.getCode());
		contractOld.setName(contract.getName());
		contractOld.setMerchantId(contract.getMerchantId());
		contractOld.setMerchantContact(contract.getMerchantContact());
		contractOld.setMerchantContactPhone(contract.getMerchantContactPhone());
		contractOld.setSigningContact(contract.getSigningContact());
		contractOld.setSigningContactPhone(contract.getSigningContactPhone());
		contractOld.setSigningTime(contract.getSigningTime());
		contractOld.setDeadlineStartTime(contract.getDeadlineStartTime());
		contractOld.setDeadlineEndTime(contract.getDeadlineEndTime());
		contractOld.setAmount(contract.getAmount());
		contractOld.setMargin(contract.getMargin());
		contractOld.setSigningType(contract.getSigningType());
		contractOld.setContractType(contract.getContractType());
		contractOld.setRemarks(contract.getRemarks());
		contractOldMapper.insertSelective(contractOld);

		Example exampleOld = new Example(GContractProject.class);
		exampleOld.createCriteria().andEqualTo("contractId",contract.getId()).andEqualTo("status",UtilConst.STR_NUMBER_1);
		List<GContractProject> contractProjects = contractProjectMapper.selectByExample(exampleOld);
		for(GContractProject contractProject:contractProjects){
			GContractProjectOld contractProjectOld = new GContractProjectOld();
			contractProjectOld.setApprovalId(contractApproval.getId());
			contractProjectOld.setContractId(contractOld.getId());
			contractProjectOld.setContractProjectId(contractProject.getId());
			contractProjectOld.setProjectId(contractProject.getProjectId());
			contractProjectOld.setPointId(contractProject.getPointId());
			contractProjectOld.setTollMode(contractProject.getTollMode());
			contractProjectOld.setPaymentCycle(contractProject.getPaymentCycle());
			contractProjectOld.setUnitPrice(contractProject.getUnitPrice());
			contractProjectOld.setNumber(contractProject.getNumber());
			contractProjectOld.setUnitName(contractProject.getUnitName());
			contractProjectOld.setSubtotal(contractProject.getSubtotal());
			contractProjectOldMapper.insertSelective(contractProjectOld);
			Example exampleResource = new Example(GContractResource.class);
			exampleResource.createCriteria().andEqualTo("contractProjectId",contractProject.getId());
			List<GContractResource> contractResources = contractResourceMapper.selectByExample(exampleResource);
			for (GContractResource contractResource:contractResources){
				GContractResourceOld contractResourceOld = new GContractResourceOld();
				contractResourceOld.setContractId(contractOld.getId());
				contractResourceOld.setContractProjectId(contractProjectOld.getId());
				contractResourceOld.setResourceId(contractResource.getResourceId());
				contractResourceOldMapper.insertSelective(contractResourceOld);
			}
		}
		return JsonResult.success(null, UtilMessage.SAVE_MESSAGE_SUCCESS);
	}

    @Override
    public JsonResult changeContractDetail(GetContractInData inData) throws Exception {
        GContract contract = contractMapper.selectByPrimaryKey(inData.getId());
        Example exampleApprove = new Example(GContractApproval.class);
        exampleApprove.createCriteria().andEqualTo("contractId",contract.getId())
                .andEqualTo("status",UtilConst.STR_NUMBER_1)
                .andEqualTo("type",UtilConst.STR_NUMBER_2);
        GContractApproval contractApproval = contractApprovalMapper.selectOneByExample(exampleApprove);

        Example exampleUp = new Example(GContractUp.class);
        exampleUp.createCriteria().andEqualTo("approvalId",contractApproval.getId());
        GContractUp contractUp = contractUpMapper.selectOneByExample(exampleUp);

        GetContractOutData outData = new GetContractOutData();
        outData.setId(contract.getId());
        outData.setCode(contract.getCode());
        outData.setName(contract.getName());
        outData.setCityId(contract.getCityId());
        outData.setMerchantId(contract.getMerchantId());
        outData.setMerchantContact(contractUp.getMerchantContact());
        outData.setMerchantContactPhone(contractUp.getMerchantContactPhone());
        outData.setSigningContact(contractUp.getSigningContact());
        outData.setSigningContactPhone(contractUp.getSigningContactPhone());
        outData.setSigningTime(Util.date2Str(contractUp.getSigningTime(),Util.DATE_YYYY_MM_DD_CHN));
        outData.setDeadlineStartTime(Util.date2Str(contractUp.getDeadlineStartTime(),Util.DATE_YYYY_MM_DD_CHN));
        outData.setDeadlineEndTime(Util.date2Str(contractUp.getDeadlineEndTime(),Util.DATE_YYYY_MM_DD_CHN));
        outData.setAmount(contractUp.getAmount());
        outData.setMargin(contractUp.getMargin());
        outData.setSigningType(contractUp.getSigningType());
        outData.setContractType(contractUp.getContractType());
        outData.setDarftStatus(contractUp.getDarftStatus());
        outData.setPaybackType(contractUp.getPaybackType());
        outData.setContractStatus(contractUp.getContractStatus());
        outData.setRemarks(contractUp.getRemarks());
        outData.setReason(contractUp.getTerminationReason());

        Example example = new Example(GContractProject.class);
        example.createCriteria().andEqualTo("contractId",contract.getId())
				.andEqualTo("status",UtilConst.STR_NUMBER_1);
        List<GContractProject> contractProjects = contractProjectMapper.selectByExample(example);
        List<GetContractProjectOutData> projects = new ArrayList<>();
        for(GContractProject contractProject:contractProjects){
            Example example1 = new Example(GContractProjectUp.class);
            example1.createCriteria().andEqualTo("contractProjectId",contractProject.getId())
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
                    contractProjectOutData.setId(contractProject.getId());
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
					contractProjectOutData.setUnitName(contractProjectUp.getUnitName());
                    contractProjectOutData.setSubtotal(contractProjectUp.getSubtotal());
                    Example exampleResource = new Example(GContractResourceUp.class);
                    exampleResource.createCriteria().andEqualTo("contractProjectId",contractProjectUp.getId());
                    List<GContractResourceUp> contractResources = contractResourceUpMapper.selectByExample(exampleResource);
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
                    projects.add(contractProjectOutData);
                }
            }else {
                //未变更
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
				contractProjectOutData.setUnitName(contractProject.getUnitName());
                contractProjectOutData.setSubtotal(contractProject.getSubtotal());
                Example exampleResource = new Example(GContractResource.class);
                exampleResource.createCriteria().andEqualTo("contractProjectId",contractProject.getId());
                List<GContractResource> contractResources = contractResourceMapper.selectByExample(exampleResource);
                List<GetContractResourceOutData> resources = new ArrayList<>();
                for (GContractResource contractResource:contractResources){
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
                projects.add(contractProjectOutData);
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
			contractProjectOutData.setUnitName(contractProjectUp.getUnitName());
            contractProjectOutData.setSubtotal(contractProjectUp.getSubtotal());
            Example exampleResource = new Example(GContractResourceUp.class);
            exampleResource.createCriteria().andEqualTo("contractProjectId",contractProjectUp.getId());
            List<GContractResourceUp> contractResources = contractResourceUpMapper.selectByExample(exampleResource);
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
            projects.add(contractProjectOutData);
        }

        outData.setProjectList(projects);

        //获取附件列表
        Example exampleAnnex = new Example(GAnnex.class);
        exampleAnnex.createCriteria().andEqualTo("associationId",contract.getId()).andEqualTo("type",UtilConst.STR_NUMBER_5);
        List<GAnnex> annexes = annexMapper.selectByExample(exampleAnnex);
        List<GetAnnexOutData> annexList = new ArrayList<>();
        for(GAnnex annex:annexes){
            GetAnnexOutData annexOutData = new GetAnnexOutData();
            annexOutData.setType(annex.getType());
            annexOutData.setAssociationId(annex.getAssociationId());
            annexOutData.setName(annex.getName());
            annexOutData.setUrl(annex.getUrl());
            annexList.add(annexOutData);
        }
        outData.setAnnexList(annexList);

        //获取审批历史
        List<GetApproveOutData> approveOutDataList = new ArrayList<>();
        Example example1 = new Example(GContractApproval.class);
        example1.createCriteria()
                .andEqualTo("contractId",contract.getId())
                .andEqualTo("type",UtilConst.STR_NUMBER_2);
        example1.setOrderByClause("id ASC");
        List<GContractApproval> contractApprovalList = contractApprovalMapper.selectByExample(example1);
        for(GContractApproval data:contractApprovalList){
            GetApproveOutData approveOutData = new GetApproveOutData();
            approveOutData.setName(data.getCreateName());
            approveOutData.setTime(Util.date2Str(data.getCreateTime(),Util.DATE_TIME_PAT_19_));
            approveOutData.setType("提交申请");
            approveOutDataList.add(approveOutData);
            if(!UtilConst.STR_NUMBER_1.equals(data.getStatus())){
				GEmployee employee = employeeMapper.selectByPrimaryKey(data.getApproveEmployeeId());
                GetApproveOutData approveOutData1 = new GetApproveOutData();
                approveOutData1.setName(employee.getEmpName());
                approveOutData1.setTime(Util.date2Str(data.getApprovalTime(),Util.DATE_TIME_PAT_19_));
                if(UtilConst.STR_NUMBER_2.equals(data.getStatus())){
                    approveOutData1.setType("审批通过");
                }
                if(UtilConst.STR_NUMBER_3.equals(data.getStatus())){
                    approveOutData1.setType("审批拒绝");
                }
				approveOutData1.setRemark(data.getOpinion());
                approveOutDataList.add(approveOutData1);
            }
        }
        outData.setApproveList(approveOutDataList);
        return JsonResult.success(outData, UtilMessage.SEARCH_MESSAGE_SUCCESS);
    }

	@Override
	@Transactional
	public JsonResult approveChangeContract(GetContractInData inData) throws Exception {
	    //更新合同状态
		GContract contract = contractMapper.selectByPrimaryKey(inData.getId());
        contract.setContractStatus(UtilConst.STR_NUMBER_2);
        contractMapper.updateByPrimaryKeySelective(contract);
		//更新审批表审批状态
		Example example = new Example(GContractApproval.class);
		example.createCriteria().andEqualTo("contractId",contract.getId()).andEqualTo("status",UtilConst.STR_NUMBER_1);
		GContractApproval contractApproval = contractApprovalMapper.selectOneByExample(example);
		contractApproval.setStatus(inData.getApprovalStatus());
		contractApproval.setOpinion(inData.getOpinion());
        contractApproval.setApprovalTime(new Date());
		contractApproval.setApproveEmployeeId(inData.getEmpId());
		contractApprovalMapper.updateByPrimaryKeySelective(contractApproval);

		if(UtilConst.STR_NUMBER_2.equals(inData.getApprovalStatus())){
            //获取合同变更信息
            Example exampleContractUp = new Example(GContractUp.class);
            exampleContractUp.createCriteria().andEqualTo("approvalId",contractApproval.getId());
            GContractUp contractUp = contractUpMapper.selectOneByExample(exampleContractUp);
            contract.setCode(contractUp.getCode());
            contract.setName(contractUp.getName());
            contract.setMerchantId(contractUp.getMerchantId());
            contract.setMerchantContact(contractUp.getMerchantContact());
            contract.setMerchantContactPhone(contractUp.getMerchantContactPhone());
            contract.setSigningContact(contractUp.getSigningContact());
            contract.setSigningContactPhone(contractUp.getSigningContactPhone());
            contract.setSigningTime(contractUp.getSigningTime());
            contract.setDeadlineStartTime(contractUp.getDeadlineStartTime());
            contract.setDeadlineEndTime(contractUp.getDeadlineEndTime());
            contract.setAmount(contractUp.getAmount());
            contract.setMargin(contractUp.getMargin());
            contract.setSigningType(contractUp.getSigningType());
            contract.setContractType(contractUp.getContractType());
            contract.setDarftStatus(contractUp.getDarftStatus());
            contract.setRemarks(contractUp.getRemarks());
            contract.setUpdateTime(new Date());
            contract.setContractStatus(UtilConst.STR_NUMBER_2);
            contractMapper.updateByPrimaryKeySelective(contract);

            //获取变更记录
            Example exampleUp = new Example(GContractProjectUp.class);
            exampleUp.createCriteria().andEqualTo("approvalId",contractApproval.getId());
            List<GContractProjectUp> contractProjectUpList = contractProjectUpMapper.selectByExample(exampleUp);
            for(GContractProjectUp contractProjectUp:contractProjectUpList){
                if(UtilConst.STR_NUMBER_1.equals(contractProjectUp.getType())){
                    GContractProject contractProject = new GContractProject();
                    contractProject.setContractId(contract.getId());
                    contractProject.setProjectId(contractProjectUp.getProjectId());
                    contractProject.setPointId(contractProjectUp.getPointId());
                    contractProject.setTollMode(contractProjectUp.getTollMode());
                    contractProject.setPaymentCycle(contractProjectUp.getPaymentCycle());
                    contractProject.setUnitPrice(contractProjectUp.getUnitPrice());
                    contractProject.setNumber(contractProjectUp.getNumber());
					contractProject.setUnitName(contractProjectUp.getUnitName());
                    contractProject.setSubtotal(contractProjectUp.getSubtotal());
                    contractProject.setStatus(UtilConst.STR_NUMBER_1);
                    contractProject.setCreateTime(new Date());
                    contractProjectMapper.insertSelective(contractProject);
                    Example exampleResource = new Example(GContractResourceUp.class);
                    exampleResource.createCriteria().andEqualTo("contractProjectId",contractProjectUp.getId());
                    List<GContractResourceUp> contractResourceUpList = contractResourceUpMapper.selectByExample(exampleResource);
                    //保存资源列表
                    if(!Util.isEmptyList(contractResourceUpList)){
                        for (GContractResourceUp contractResourceUp:contractResourceUpList){
                            GContractResource contractResource = new GContractResource();
                            contractResource.setContractId(contract.getId());
                            contractResource.setContractProjectId(contractProject.getId());
                            contractResource.setResourceId(contractResourceUp.getResourceId());
                            contractResourceMapper.insertSelective(contractResource);
                        }
                    }
                    addPayBackPlan(contract,contractProject);
                }else if(UtilConst.STR_NUMBER_2.equals(contractProjectUp.getType())){
                    removePayBackPlan(contractProjectUp);
                }else if(UtilConst.STR_NUMBER_3.equals(contractProjectUp.getType())){
                    changePayBackPlan(contract,contractProjectUp);
                }
            }
        }
		return JsonResult.success(null, UtilMessage.SAVE_MESSAGE_SUCCESS);
	}

	@Override
	@Transactional
	public JsonResult deleteContract(GetContractInData inData) throws Exception {
		for(Long id:inData.getIds()){
			GContract contract = contractMapper.selectByPrimaryKey(id);
			if(UtilConst.STR_NUMBER_0.equals(contract.getDarftStatus())){
				return JsonResult.fail(UtilConst.CODE_403, "合同已生效，无法删除");
			}
		}
		for(Long id:inData.getIds()){
			GContract contract = contractMapper.selectByPrimaryKey(id);
			contract.setDelStatus(UtilConst.STR_NUMBER_0);
			contractMapper.updateByPrimaryKeySelective(contract);
		}
		return JsonResult.success(null, UtilMessage.DEL_MESSAGE_SUCCESS);
	}

	@Override
	public JsonResult getCityList(GetContractInData inData) throws Exception {
		List<Long> cityIds = inData.getCityIds();
		List<GetCityOutData> outData = new ArrayList<>();
		if((cityIds.size()==1&&cityIds.get(0)==-1L)||Util.isEmptyList(cityIds)){
			Example example = new Example(GCity.class);
			example.createCriteria().andEqualTo("delStatus",UtilConst.STR_NUMBER_1);
			List<GCity> cityList = cityMapper.selectByExample(example);
			for(GCity city:cityList){
				GetCityOutData cityOutData = new GetCityOutData();
				cityOutData.setCityId(city.getId());
				cityOutData.setCityName(city.getCityName());
				outData.add(cityOutData);
			}
		}else {
			for (Long cityId:cityIds){
				GCity city = cityMapper.selectByPrimaryKey(cityId);
				GetCityOutData cityOutData = new GetCityOutData();
				cityOutData.setCityId(city.getId());
				cityOutData.setCityName(city.getCityName());
				outData.add(cityOutData);
			}
		}
		return JsonResult.success(outData, UtilMessage.SEARCH_MESSAGE_SUCCESS);
	}

	@Override
	public JsonResult getCityListByCenterCity(GetContractInData inData) throws Exception {
		Example example = new Example(GCity.class);
		example.createCriteria()
				.andEqualTo("centralCityId",inData.getCentralCityId())
				.andEqualTo("delStatus",UtilConst.STR_NUMBER_1);
		List<GCity> cityList = cityMapper.selectByExample(example);
		return JsonResult.success(cityList, UtilMessage.SEARCH_MESSAGE_SUCCESS);
	}

	@Override
	public JsonResult getCenterCityList(GetContractInData inData) throws Exception {
		List<Long> centerCityIds = inData.getCenterCityIds();
		List<GetCityOutData> outData = new ArrayList<>();
		if((centerCityIds.size()==1&&centerCityIds.get(0)==-1L)||Util.isEmptyList(centerCityIds)){
			Example example = new Example(GCentralCity.class);
			example.createCriteria().andEqualTo("delStatus",UtilConst.STR_NUMBER_1);
			List<GCentralCity> cityList = centralCityMapper.selectByExample(example);
			for(GCentralCity city:cityList){
				GetCityOutData cityOutData = new GetCityOutData();
				cityOutData.setCityId(city.getId());
				cityOutData.setCityName(city.getCentralCityName());
				outData.add(cityOutData);
			}
		}else {
			for (Long cityId:centerCityIds){
				GCentralCity city = centralCityMapper.selectByPrimaryKey(cityId);
				GetCityOutData cityOutData = new GetCityOutData();
				cityOutData.setCityId(city.getId());
				cityOutData.setCityName(city.getCentralCityName());
				outData.add(cityOutData);
			}
		}
		return JsonResult.success(outData, UtilMessage.SEARCH_MESSAGE_SUCCESS);
	}

	@Override
	public JsonResult getMerchantList(GetContractInData inData) throws Exception {
		List<GMerchant> merchantList = new ArrayList<>();
		if(Util.isEmpty(inData.getCityId())){
			Example example = new Example(GMerchant.class);
			example.createCriteria()
					.andEqualTo("delStatus",UtilConst.STR_NUMBER_1);
			merchantList = merchantMapper.selectByExample(example);
		}else {
			Example example = new Example(GMerchant.class);
			example.createCriteria()
					.andEqualTo("delStatus",UtilConst.STR_NUMBER_1)
					.andEqualTo("level",UtilConst.STR_NUMBER_1);
			List<GMerchant> merchants = merchantMapper.selectByExample(example);
			merchantList.addAll(merchants);
			example = new Example(GMerchant.class);
			example.createCriteria()
					.andNotEqualTo("level",UtilConst.STR_NUMBER_1)
					.andEqualTo("delStatus",UtilConst.STR_NUMBER_1)
					.andEqualTo("city",inData.getCityId().toString());
			List<GMerchant> merchants1 = merchantMapper.selectByExample(example);
			merchantList.addAll(merchants1);
		}
		return JsonResult.success(merchantList, UtilMessage.SEARCH_MESSAGE_SUCCESS);
	}

	@Override
	public JsonResult getContractList(GetContractInData inData) throws Exception {
		List<GetContractOutData> outData = contractMapper.selectListByCondition(inData);
		return JsonResult.success(outData, UtilMessage.SEARCH_MESSAGE_SUCCESS);
	}

	@Override
	public JsonResult getProjectList(GetContractInData inData) throws Exception {
		List<Long> cityIds = new ArrayList<>();
		if(!Util.isEmpty(inData.getCityId())){
			cityIds.add(inData.getCityId());
		}
		GetResourceProjectInData resourceProjectInData = new GetResourceProjectInData();
		resourceProjectInData.setCityIds(cityIds);
		List<GetResourceProjectOutData> outData = resourceProjectMapper.selectListByCity(resourceProjectInData);
		return JsonResult.success(outData, UtilMessage.SEARCH_MESSAGE_SUCCESS);
	}

	@Override
	public JsonResult getPointList(GetContractInData inData) throws Exception {
		Example example = new Example(GResourceProjectDetail.class);
		example.createCriteria()
                .andEqualTo("delStatus",1)
                .andEqualTo("projectId",inData.getProjectId());
		List<GResourceProjectDetail> resourceProjectDetails = resourceProjectDetailMapper.selectByExample(example);
		List<GResourcePoint> resourcePoints = new ArrayList<>();
		for(GResourceProjectDetail resourceProjectDetail:resourceProjectDetails){
            GResourcePoint resourcePointThree = resourcePointMapper.selectByPrimaryKey(resourceProjectDetail.getPointId());
            GResourcePoint resourcePointTwo = resourcePointMapper.selectByPrimaryKey(resourcePointThree.getParentId());
            GResourcePoint resourcePointOne = resourcePointMapper.selectByPrimaryKey(resourcePointTwo.getParentId());
            //去除重复点位
            boolean flag = true;
            for(GResourcePoint resourcePoint:resourcePoints){
            	if(resourcePoint.getId().equals(resourcePointOne.getId())){
					flag = false;
				}
			}
			if(flag){
				resourcePoints.add(resourcePointOne);
			}
        }
		return JsonResult.success(resourcePoints, UtilMessage.SEARCH_MESSAGE_SUCCESS);
	}

    @Override
    public JsonResult getPointTwoList(GetContractInData inData) throws Exception {
        Example example = new Example(GResourceProjectDetail.class);
        example.createCriteria()
                .andEqualTo("delStatus",1)
                .andEqualTo("projectId",inData.getProjectId());
        List<GResourceProjectDetail> resourceProjectDetails = resourceProjectDetailMapper.selectByExample(example);
        List<GResourcePoint> resourcePoints = new ArrayList<>();
        for(GResourceProjectDetail resourceProjectDetail:resourceProjectDetails){
            GResourcePoint resourcePointThree = resourcePointMapper.selectByPrimaryKey(resourceProjectDetail.getPointId());
            GResourcePoint resourcePointTwo = resourcePointMapper.selectByPrimaryKey(resourcePointThree.getParentId());
			//去除重复点位
			boolean flag = true;
			for(GResourcePoint resourcePoint:resourcePoints){
				if(resourcePoint.getId().equals(resourcePointTwo.getId())){
					flag = false;
				}
			}
			if(flag){
				if(resourcePointTwo.getParentId().equals(inData.getPointId())){
					resourcePoints.add(resourcePointTwo);
				}
			}
        }
        return JsonResult.success(resourcePoints, UtilMessage.SEARCH_MESSAGE_SUCCESS);
    }

    @Override
    public JsonResult getPointThreeList(GetContractInData inData) throws Exception {
        Example example = new Example(GResourceProjectDetail.class);
        example.createCriteria()
                .andEqualTo("delStatus",1)
                .andEqualTo("projectId",inData.getProjectId());
        List<GResourceProjectDetail> resourceProjectDetails = resourceProjectDetailMapper.selectByExample(example);
        List<GResourcePoint> resourcePoints = new ArrayList<>();
        for(GResourceProjectDetail resourceProjectDetail:resourceProjectDetails){
            GResourcePoint resourcePointThree = resourcePointMapper.selectByPrimaryKey(resourceProjectDetail.getPointId());
			//去除重复点位
			boolean flag = true;
			for(GResourcePoint resourcePoint:resourcePoints){
				if(resourcePoint.getId().equals(resourcePointThree.getId())){
					flag = false;
				}
			}
			if(flag){
				if(resourcePointThree.getParentId().equals(inData.getPointId())){
					resourcePoints.add(resourcePointThree);
				}
			}
        }
        return JsonResult.success(resourcePoints, UtilMessage.SEARCH_MESSAGE_SUCCESS);
    }

	@Override
	public JsonResult getPointChildList(Long pointId) throws Exception {
		Example example = new Example(GResourcePoint.class);
		example.createCriteria().andEqualTo("delStatus",1).andEqualTo("parentId",pointId);
		List<GResourcePoint> resourcePoints = resourcePointMapper.selectByExample(example);
		return JsonResult.success(resourcePoints, UtilMessage.SEARCH_MESSAGE_SUCCESS);
	}

	@Override
	public JsonResult getResourceList(GetContractInData inData) throws Exception {
		Example example = new Example(GResourceProjectDetail.class);
		example.createCriteria()
				.andEqualTo("delStatus",UtilConst.STR_NUMBER_1)
				.andEqualTo("pointId",inData.getPointId())
				.andEqualTo("projectId",inData.getProjectId());
		List<GResourceProjectDetail> resourceProjectDetails = resourceProjectDetailMapper.selectByExample(example);
		return JsonResult.success(resourceProjectDetails, UtilMessage.SEARCH_MESSAGE_SUCCESS);
	}

    @Override
    public void exportOut(HttpServletResponse response, GetContractInData inData) throws Exception{
        List<GetContractOutData> outData = contractMapper.selectListByCondition(inData);
        List<GetContractExportOutData> outDataList = new ArrayList<>();
        for(GetContractOutData contractOutData:outData){
            GetContractExportOutData contractExportOutData = new GetContractExportOutData();
            contractExportOutData.setAmount(contractOutData.getAmount());
            contractExportOutData.setCode(contractOutData.getCode());
            if(UtilConst.STR_NUMBER_1.equals(contractOutData.getContractStatus())){
                contractExportOutData.setContractStatus("未开始");
            }
            if(UtilConst.STR_NUMBER_2.equals(contractOutData.getContractStatus())){
                contractExportOutData.setContractStatus("进行中");
            }
            if(UtilConst.STR_NUMBER_3.equals(contractOutData.getContractStatus())){
                contractExportOutData.setContractStatus("变更审批中");
            }
            if(UtilConst.STR_NUMBER_4.equals(contractOutData.getContractStatus())){
                contractExportOutData.setContractStatus("终止审批中");
            }
            if(UtilConst.STR_NUMBER_5.equals(contractOutData.getContractStatus())){
                contractExportOutData.setContractStatus("已结束");
            }
            if(UtilConst.STR_NUMBER_6.equals(contractOutData.getContractStatus())){
                contractExportOutData.setContractStatus("已终止");
            }
            if(UtilConst.STR_NUMBER_1.equals(contractOutData.getContractType())){
                contractExportOutData.setContractType("广告资源");
            }
            if(UtilConst.STR_NUMBER_2.equals(contractOutData.getContractType())){
                contractExportOutData.setContractType("场地资源");
            }
            if(UtilConst.STR_NUMBER_3.equals(contractOutData.getContractType())){
                contractExportOutData.setContractType("临时摆展");
            }
            if(UtilConst.STR_NUMBER_4.equals(contractOutData.getContractType())){
                contractExportOutData.setContractType("服务产品");
            }
            if(UtilConst.STR_NUMBER_5.equals(contractOutData.getContractType())){
                contractExportOutData.setContractType("其他");
            }
            if(UtilConst.STR_NUMBER_0.equals(contractOutData.getDarftStatus())){
                contractExportOutData.setDarftStatus("正式");
            }
            if(UtilConst.STR_NUMBER_1.equals(contractOutData.getDarftStatus())){
                contractExportOutData.setDarftStatus("草稿");
            }
            contractExportOutData.setDeadlineEndTime(contractOutData.getDeadlineEndTime());
            contractExportOutData.setDeadlineStartTime(contractOutData.getDeadlineStartTime());
            contractExportOutData.setMargin(contractOutData.getMargin());
            contractExportOutData.setMerchantContact(contractOutData.getMerchantContact());
            contractExportOutData.setMerchantContactPhone(contractOutData.getMerchantContactPhone());
            contractExportOutData.setMerchantName(contractOutData.getMerchantName());
            contractExportOutData.setName(contractOutData.getName());
            if(UtilConst.STR_NUMBER_1.equals(contractOutData.getPaybackType())){
                contractExportOutData.setPaybackType("未回款");
            }
            if(UtilConst.STR_NUMBER_2.equals(contractOutData.getPaybackType())){
                contractExportOutData.setPaybackType("部分回款");
            }
            if(UtilConst.STR_NUMBER_3.equals(contractOutData.getPaybackType())){
                contractExportOutData.setPaybackType("已回款");
            }
            contractExportOutData.setSigningContact(contractOutData.getSigningContact());
            contractExportOutData.setSigningContactPhone(contractOutData.getSigningContactPhone());
            contractExportOutData.setSigningTime(contractOutData.getSigningTime());
            if(UtilConst.STR_NUMBER_1.equals(contractOutData.getSigningType())){
                contractExportOutData.setSigningType("新合同");
            }
            if(UtilConst.STR_NUMBER_2.equals(contractOutData.getSigningType())){
                contractExportOutData.setSigningType("续签");
            }
			outDataList.add(contractExportOutData);
        }
        String excelName = "合同报表";
        String[] headers = {"合同编号", "合同名称", "合作商家名称", "商家联系人", "商家联系电话", "签约联系人", "签约联系电话", "签约日期",
                "合同期限开始时间", "合同期限结束时间", "合同总额", "履约保证金", "签约类型", "合同类别", "合同状态", "草稿状态", "回款类型"};
        ExportExcel<GetContractExportOutData> excelUtil = new ExportExcel<GetContractExportOutData>();
        response.setContentType("application/vnd.ms-excel; charset=utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + new String((excelName).getBytes(), "iso-8859-1") + ".xls");
        response.setCharacterEncoding("UTF-8");
        OutputStream outputStream = response.getOutputStream();
        excelUtil.exportExcel(headers, outDataList, outputStream, null);
    }

	@Override
	@Transactional
	public JsonResult exportIn(List<GetContractExportInData> inData, String loginName) {
		if (Util.isEmptyList(inData)) {
			return JsonResult.fail(UtilConst.CODE_403, "需要导入的合同列表为空");
		}
		//校验Excel数据合法性
		for(GetContractExportInData contractExportInData:inData){
			if(Util.isEmpty(contractExportInData.getMerchantName())){
				return JsonResult.fail(UtilConst.CODE_403,"第"+contractExportInData.getIndex()+"行的商家名称不能为空");
			}
			if(Util.isEmpty(contractExportInData.getProjectName())){
				return JsonResult.fail(UtilConst.CODE_403,"第"+contractExportInData.getIndex()+"行的项目名称不能为空");
			}
			if(Util.isEmpty(contractExportInData.getCode())){
				return JsonResult.fail(UtilConst.CODE_403,"第"+contractExportInData.getIndex()+"行的合同编号不能为空");
			}
			if(contractExportInData.getCode().length()>30){
				return JsonResult.fail(UtilConst.CODE_403,"第"+contractExportInData.getIndex()+"行的合同编号内容过长");
			}
			if(!Util.isEmpty(contractExportInData.getName())){
				if(contractExportInData.getName().length()>50){
					return JsonResult.fail(UtilConst.CODE_403,"第"+contractExportInData.getIndex()+"行的合同名称过长");
				}
			}
			//查询商家与项目是否同属一个城市
			Example exampleProject = new Example(GResourceProject.class);
			exampleProject.createCriteria()
					.andEqualTo("projectName",contractExportInData.getProjectName())
					.andEqualTo("delStatus",UtilConst.STR_NUMBER_1);
			List<GResourceProject> projectList = resourceProjectMapper.selectByExample(exampleProject);
			if(Util.isEmptyList(projectList)){
				return JsonResult.fail(UtilConst.CODE_403,"项目名："+contractExportInData.getProjectName()+"不存在");
			}
			GCity city = cityMapper.selectByPrimaryKey(projectList.get(0).getCityId());
			List<GMerchant> merchantList = new ArrayList<>();
			Example example = new Example(GMerchant.class);
			example.createCriteria()
					.andEqualTo("name",contractExportInData.getMerchantName())
					.andEqualTo("delStatus",UtilConst.STR_NUMBER_1)
					.andEqualTo("level",UtilConst.STR_NUMBER_1);
			List<GMerchant> merchants = merchantMapper.selectByExample(example);
			merchantList.addAll(merchants);
			example = new Example(GMerchant.class);
			example.createCriteria()
					.andEqualTo("name",contractExportInData.getMerchantName())
					.andEqualTo("delStatus",UtilConst.STR_NUMBER_1)
					.andEqualTo("city",city.getId().toString());
			List<GMerchant> merchants1 = merchantMapper.selectByExample(example);
			merchantList.addAll(merchants1);
			if(Util.isEmptyList(merchantList)){
				return JsonResult.fail(UtilConst.CODE_403,"商家名："+contractExportInData.getMerchantName()+"不存在或与项目不属于同一个城市");
			}

			String eL= "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
			if(!Util.isEmpty(contractExportInData.getDeadlineStartTime())){
				Pattern p = Pattern.compile(eL);
				Matcher m = p.matcher(contractExportInData.getDeadlineStartTime());
				boolean b = m.matches();
				if(!b){
					return JsonResult.fail(UtilConst.CODE_403,"第"+contractExportInData.getIndex()+"行的开始日期格式不正确");
				}
			}
			if(!Util.isEmpty(contractExportInData.getDeadlineEndTime())){
				Pattern p = Pattern.compile(eL);
				Matcher m = p.matcher(contractExportInData.getDeadlineEndTime());
				boolean b = m.matches();
				if(!b){
					return JsonResult.fail(UtilConst.CODE_403,"第"+contractExportInData.getIndex()+"行的结束日期格式不正确");
				}
			}
			if(!Util.isEmpty(contractExportInData.getAmount())){
				if(!isNumber(contractExportInData.getAmount())){
					return JsonResult.fail(UtilConst.CODE_403,"第"+contractExportInData.getIndex()+"行的合同金额不合法");
				}
				if(new BigDecimal(contractExportInData.getAmount()).compareTo(new BigDecimal(999999999))>0){
					return JsonResult.fail(UtilConst.CODE_403,"第"+contractExportInData.getIndex()+"行的合同金额过大");
				}
			}
			if(!Util.isEmpty(contractExportInData.getMargin())){
				if(!isNumber(contractExportInData.getMargin())){
					return JsonResult.fail(UtilConst.CODE_403,"第"+contractExportInData.getIndex()+"行的履约保证金不合法");
				}
				if(new BigDecimal(contractExportInData.getMargin()).compareTo(new BigDecimal(999999999))>0){
					return JsonResult.fail(UtilConst.CODE_403,"第"+contractExportInData.getIndex()+"行的履约保证金过大");
				}
			}
			if(!Util.isEmpty(contractExportInData.getUnitPrice())){
				if(!isNumber(contractExportInData.getUnitPrice())){
					return JsonResult.fail(UtilConst.CODE_403,"第"+contractExportInData.getIndex()+"行的单价不合法");
				}
				if(new BigDecimal(contractExportInData.getUnitPrice()).compareTo(new BigDecimal(999999999))>0){
					return JsonResult.fail(UtilConst.CODE_403,"第"+contractExportInData.getIndex()+"行的单价过大");
				}
			}
			String integral = "^[0-9]*[1-9][0-9]*$";
			if(!Util.isEmpty(contractExportInData.getNumber())){
				Pattern p = Pattern.compile(integral);
				Matcher m = p.matcher(contractExportInData.getNumber());
				boolean b = m.matches();
				if(!b){
					return JsonResult.fail(UtilConst.CODE_403,"第"+contractExportInData.getIndex()+"行的数量不合法");
				}
				if(contractExportInData.getNumber().length()>10){
					return JsonResult.fail(UtilConst.CODE_403,"第"+contractExportInData.getIndex()+"行的数量过大");
				}
			}
			if(!Util.isEmpty(contractExportInData.getUnitName())){
				if(contractExportInData.getUnitName().length()>11){
					return JsonResult.fail(UtilConst.CODE_403,"第"+contractExportInData.getIndex()+"行的单位内容过长");
				}
			}
			if(!Util.isEmpty(contractExportInData.getSubtotal())){
				if(!isNumber(contractExportInData.getSubtotal())){
					return JsonResult.fail(UtilConst.CODE_403,"第"+contractExportInData.getIndex()+"行的小计不合法");
				}
				if(new BigDecimal(contractExportInData.getSubtotal()).compareTo(new BigDecimal(999999999))>0){
					return JsonResult.fail(UtilConst.CODE_403,"第"+contractExportInData.getIndex()+"行的小计过大");
				}
			}
		}
		for(GetContractExportInData contractExportInData:inData){
			if(!Util.isEmpty(contractExportInData.getCode())){
				Example example = new Example(GContract.class);
				example.createCriteria()
						.andEqualTo("code",contractExportInData.getCode())
						.andEqualTo("delStatus",UtilConst.STR_NUMBER_1)
						.andEqualTo("darftStatus",UtilConst.STR_NUMBER_1);
				List<GContract> contractList = contractMapper.selectByExample(example);

				Example exampleProject = new Example(GResourceProject.class);
				exampleProject.createCriteria().andEqualTo("projectName",contractExportInData.getProjectName())
						.andEqualTo("delStatus",UtilConst.STR_NUMBER_1);
				List<GResourceProject> projectList = resourceProjectMapper.selectByExample(exampleProject);

				Example exampleMerchant = new Example(GMerchant.class);
				exampleMerchant.createCriteria().andEqualTo("name",contractExportInData.getMerchantName())
						.andEqualTo("delStatus",UtilConst.STR_NUMBER_1);
				List<GMerchant> merchantList = merchantMapper.selectByExample(exampleMerchant);
				String paymentCycle = "";
				if("年".equals(contractExportInData.getPaymentCycle())){
					paymentCycle = UtilConst.STR_NUMBER_1;
				}
				if("半年".equals(contractExportInData.getPaymentCycle())){
					paymentCycle = UtilConst.STR_NUMBER_2;
				}
				if("季".equals(contractExportInData.getPaymentCycle())){
					paymentCycle = UtilConst.STR_NUMBER_3;
				}
				if("月".equals(contractExportInData.getPaymentCycle())){
					paymentCycle = UtilConst.STR_NUMBER_4;
				}
				if("周".equals(contractExportInData.getPaymentCycle())){
					paymentCycle = UtilConst.STR_NUMBER_5;
				}
				if("二年".equals(contractExportInData.getPaymentCycle())){
					paymentCycle = UtilConst.STR_NUMBER_6;
				}
				if("三年".equals(contractExportInData.getPaymentCycle())){
					paymentCycle = UtilConst.STR_NUMBER_7;
				}
				if("四年".equals(contractExportInData.getPaymentCycle())){
					paymentCycle = UtilConst.STR_NUMBER_8;
				}
				if("五年".equals(contractExportInData.getPaymentCycle())){
					paymentCycle = UtilConst.STR_NUMBER_9;
				}
				if("一次性".equals(contractExportInData.getPaymentCycle())){
					paymentCycle = UtilConst.STR_NUMBER_10;
				}
				String tollMode = "";
				if("一次性".equals(contractExportInData.getTollMode())){
					tollMode = UtilConst.STR_NUMBER_1;
				}
				if("固定".equals(contractExportInData.getTollMode())){
					tollMode = UtilConst.STR_NUMBER_2;
				}
				if("佣金".equals(contractExportInData.getTollMode())){
					tollMode = UtilConst.STR_NUMBER_3;
				}
				if("其他".equals(contractExportInData.getTollMode())){
					tollMode = UtilConst.STR_NUMBER_4;
				}
				String contractType = "";
				if("广告资源".equals(contractExportInData.getContractType())){
					contractType = UtilConst.STR_NUMBER_1;
				}
				if("场地资源".equals(contractExportInData.getContractType())){
					contractType = UtilConst.STR_NUMBER_2;
				}
				if("临时摆展".equals(contractExportInData.getContractType())){
					contractType = UtilConst.STR_NUMBER_3;
				}
				if("服务产品".equals(contractExportInData.getContractType())){
					contractType = UtilConst.STR_NUMBER_4;
				}
				if("其他".equals(contractExportInData.getContractType())){
					contractType = UtilConst.STR_NUMBER_5;
				}

				if(!Util.isEmptyList(contractList)){
					GContract contract = contractList.get(0);
					contract.setName(contractExportInData.getName());
					contract.setCode(contractExportInData.getCode());
					contract.setMerchantId(merchantList.get(0).getId());
					contract.setDeadlineStartTime(Util.str2Date(contractExportInData.getDeadlineStartTime(),Util.DATE_YYYY_MM_DD_CHN));
					contract.setDeadlineEndTime(Util.str2Date(contractExportInData.getDeadlineEndTime(),Util.DATE_YYYY_MM_DD_CHN));
					if(!Util.isEmpty(contractExportInData.getAmount())){
						contract.setAmount(new BigDecimal(contractExportInData.getAmount()));
					}else {
						contract.setAmount(new BigDecimal(0));
					}
					if(!Util.isEmpty(contractExportInData.getMargin())){
						contract.setMargin(new BigDecimal(contractExportInData.getMargin()));
					}else {
						contract.setMargin(new BigDecimal(0));
					}
					GCity city = cityMapper.selectByPrimaryKey(projectList.get(0).getCityId());
					contract.setCityId(city.getId());
					contract.setCentralCityId(city.getCentralCityId());
					contract.setContractType(contractType);
					contract.setDelStatus(UtilConst.STR_NUMBER_1);
					contract.setPaybackType(UtilConst.STR_NUMBER_1);
					contract.setDarftStatus(UtilConst.STR_NUMBER_1);
					contract.setContractStatus(UtilConst.STR_NUMBER_1);
					contract.setCreateTime(new Date());
					contractMapper.updateByPrimaryKeySelective(contract);

					GContractProject contractProject = new GContractProject();
					contractProject.setContractId(contractList.get(0).getId());
					contractProject.setTollMode(tollMode);
					if(UtilConst.STR_NUMBER_1.equals(tollMode)){
						contractProject.setPaymentCycle(UtilConst.STR_NUMBER_10);
					}else {
						contractProject.setPaymentCycle(paymentCycle);
					}
					if(!Util.isEmpty(contractExportInData.getUnitPrice())){
						contractProject.setUnitPrice(new BigDecimal(contractExportInData.getUnitPrice()));
					}
					contractProject.setUnitName(contractExportInData.getUnitName());
					if(!UtilConst.STR_NUMBER_3.equals(tollMode)){
						if(!Util.isEmpty(contractExportInData.getNumber())){
							contractProject.setNumber(Integer.parseInt(contractExportInData.getNumber()));
						}
					}
					if(!Util.isEmpty(contractExportInData.getSubtotal())){
						contractProject.setSubtotal(new BigDecimal(contractExportInData.getSubtotal()));
					}else {
						contractProject.setSubtotal(new BigDecimal(0));
					}
					contractProject.setProjectId(projectList.get(0).getId());
					contractProject.setStatus(UtilConst.STR_NUMBER_1);
					contractProject.setCreateTime(new Date());
					contractProjectMapper.insertSelective(contractProject);
				}else {
					GContract contract = new GContract();
					contract.setName(contractExportInData.getName());
					contract.setCode(contractExportInData.getCode());
					contract.setMerchantId(merchantList.get(0).getId());
					contract.setDeadlineStartTime(Util.str2Date(contractExportInData.getDeadlineStartTime(),Util.DATE_YYYY_MM_DD_CHN));
					contract.setDeadlineEndTime(Util.str2Date(contractExportInData.getDeadlineEndTime(),Util.DATE_YYYY_MM_DD_CHN));
					if(!Util.isEmpty(contractExportInData.getAmount())){
						contract.setAmount(new BigDecimal(contractExportInData.getAmount()));
					}else {
						contract.setAmount(new BigDecimal(0));
					}
					if(!Util.isEmpty(contractExportInData.getMargin())){
						contract.setMargin(new BigDecimal(contractExportInData.getMargin()));
					}else {
						contract.setMargin(new BigDecimal(0));
					}
					GCity city = cityMapper.selectByPrimaryKey(projectList.get(0).getCityId());
					contract.setCityId(city.getId());
					contract.setCentralCityId(city.getCentralCityId());
					contract.setContractType(contractType);
					contract.setDelStatus(UtilConst.STR_NUMBER_1);
					contract.setPaybackType(UtilConst.STR_NUMBER_1);
					contract.setDarftStatus(UtilConst.STR_NUMBER_1);
					contract.setContractStatus(UtilConst.STR_NUMBER_1);
					contract.setCreateName(loginName);
					contract.setCreateTime(new Date());
					contractMapper.insertSelective(contract);

					GContractProject contractProject = new GContractProject();
					contractProject.setContractId(contract.getId());
					contractProject.setTollMode(tollMode);
					if(UtilConst.STR_NUMBER_1.equals(tollMode)){
						contractProject.setPaymentCycle(UtilConst.STR_NUMBER_10);
					}else {
						contractProject.setPaymentCycle(paymentCycle);
					}
					if(!Util.isEmpty(contractExportInData.getUnitPrice())){
						contractProject.setUnitPrice(new BigDecimal(contractExportInData.getUnitPrice()));
					}
					contractProject.setUnitName(contractExportInData.getUnitName());
					if(!UtilConst.STR_NUMBER_3.equals(tollMode)){
						if(!Util.isEmpty(contractExportInData.getNumber())){
							contractProject.setNumber(Integer.parseInt(contractExportInData.getNumber()));
						}
					}
					if(!Util.isEmpty(contractExportInData.getSubtotal())){
						contractProject.setSubtotal(new BigDecimal(contractExportInData.getSubtotal()));
					}
					contractProject.setProjectId(projectList.get(0).getId());
					contractProject.setStatus(UtilConst.STR_NUMBER_1);
					contractProject.setCreateTime(new Date());
					contractProjectMapper.insertSelective(contractProject);
				}
			}
		}
		return JsonResult.success(null,"批量导入成功");
	}

	@Override
	public JsonResult getAmount(GetContractInData inData) throws Exception {
		GContract contract = contractMapper.selectByPrimaryKey(inData.getId());
		Example example = new Example(GPaybackRecord.class);
		example.createCriteria().andEqualTo("contractId",contract.getId());
		List<GPaybackRecord> paybackRecordList = paybackRecordMapper.selectByExample(example);
		BigDecimal total = new BigDecimal(0);
		for(GPaybackRecord paybackRecord:paybackRecordList){
			total = total.add(paybackRecord.getPaybackAmount());
		}
		return JsonResult.success(total, UtilMessage.SEARCH_MESSAGE_SUCCESS);
	}


	/**
	 * 新增合同项目
	 * @param contract
	 * @param contractProject
	 */
	private void addPayBackPlan(GContract contract,GContractProject contractProject){
		//生成回款计划
		if(UtilConst.STR_NUMBER_1.equals(contractProject.getTollMode())){
			GPaybackPlan paybackPlan = new GPaybackPlan();
			paybackPlan.setContractId(contract.getId());
			paybackPlan.setCentralCityId(contract.getCentralCityId());
			paybackPlan.setCityId(contract.getCityId());
			paybackPlan.setName(contract.getName());
			paybackPlan.setMerchantId(contract.getMerchantId());
			paybackPlan.setContractProjectId(contractProject.getId());
			paybackPlan.setReceivablePayback(contractProject.getSubtotal());
			paybackPlan.setExtensionStatus(UtilConst.STR_NUMBER_0);
			paybackPlan.setBeforeRemind(UtilConst.STR_NUMBER_0);
			paybackPlan.setExtensionRemind(UtilConst.STR_NUMBER_0);
			paybackPlan.setStatus(UtilConst.STR_NUMBER_1);
			paybackPlan.setDelStatus(UtilConst.STR_NUMBER_1);
			paybackPlan.setEditStatus(UtilConst.STR_NUMBER_0);
			paybackPlan.setCreateName(contract.getCreateName());
			Date date = getPayBackTime(contract,0);
			if (date.compareTo(contract.getDeadlineEndTime()) > 0) {
				paybackPlan.setPaybackTime(contract.getDeadlineEndTime());
			}else {
				paybackPlan.setPaybackTime(date);
			}
			String dateStr = Util.date2Str(date,Util.DATE_YYYY_MM_DD_CHN);
			dateStr = dateStr + " 23:59:59";
			if(Util.str2Date(dateStr,Util.DATE_TIME_PAT_19_).compareTo(new Date())<0){
				paybackPlan.setPaybackStatus(UtilConst.STR_NUMBER_2);
			}else {
				paybackPlan.setPaybackStatus(UtilConst.STR_NUMBER_1);
			}
			paybackPlan.setCreateTime(new Date());
			paybackPlanMapper.insertSelective(paybackPlan);
		}else {
			List<Date> dates = getPayBackCount(contract,contractProject);
			BigDecimal sum = new BigDecimal(0);
			for(int i=0;i<dates.size();i++){
				GPaybackPlan paybackPlan = new GPaybackPlan();
				paybackPlan.setContractId(contract.getId());
				paybackPlan.setCentralCityId(contract.getCentralCityId());
				paybackPlan.setCityId(contract.getCityId());
				paybackPlan.setName(contract.getName());
				paybackPlan.setMerchantId(contract.getMerchantId());
				paybackPlan.setContractProjectId(contractProject.getId());
				if(UtilConst.STR_NUMBER_2.equals(contractProject.getTollMode())||(UtilConst.STR_NUMBER_4.equals(contractProject.getTollMode())&&contractProject.getSubtotal().compareTo(new BigDecimal(0))!=0)){
					if(contractProject.getSubtotal().compareTo(new BigDecimal(0))>0){
						sum = sum.add(contractProject.getSubtotal().divide(new BigDecimal(dates.size()),2,BigDecimal.ROUND_UP));
						if(sum.compareTo(contractProject.getSubtotal())>0){
							BigDecimal a = sum.subtract(contractProject.getSubtotal());
							paybackPlan.setReceivablePayback(contractProject.getSubtotal().divide(new BigDecimal(dates.size()),2,BigDecimal.ROUND_UP).subtract(a));
						}else {
							paybackPlan.setReceivablePayback(contractProject.getSubtotal().divide(new BigDecimal(dates.size()),2,BigDecimal.ROUND_UP));
						}
					}else {
						sum = sum.add(contractProject.getSubtotal().divide(new BigDecimal(dates.size()),2,BigDecimal.ROUND_UP));
						if(sum.compareTo(contractProject.getSubtotal())<0){
							BigDecimal a = sum.subtract(contractProject.getSubtotal());
							paybackPlan.setReceivablePayback(contractProject.getSubtotal().divide(new BigDecimal(dates.size()),2,BigDecimal.ROUND_UP).add(a));
						}else {
							paybackPlan.setReceivablePayback(contractProject.getSubtotal().divide(new BigDecimal(dates.size()),2,BigDecimal.ROUND_UP));
						}
					}
				}
				paybackPlan.setExtensionStatus(UtilConst.STR_NUMBER_0);
				paybackPlan.setBeforeRemind(UtilConst.STR_NUMBER_0);
				paybackPlan.setExtensionRemind(UtilConst.STR_NUMBER_0);
				paybackPlan.setStatus(UtilConst.STR_NUMBER_1);
				paybackPlan.setDelStatus(UtilConst.STR_NUMBER_1);
				paybackPlan.setEditStatus(UtilConst.STR_NUMBER_0);
				String dateStr = Util.date2Str(dates.get(i),Util.DATE_YYYY_MM_DD_CHN);
				dateStr = dateStr + " 23:59:59";
				if(Util.str2Date(dateStr,Util.DATE_TIME_PAT_19_).compareTo(new Date())<0){
					paybackPlan.setPaybackStatus(UtilConst.STR_NUMBER_2);
				}else {
					paybackPlan.setPaybackStatus(UtilConst.STR_NUMBER_1);
				}
				paybackPlan.setPaybackTime(dates.get(i));
				paybackPlan.setCreateName(contract.getCreateName());
				paybackPlan.setCreateTime(new Date());
				paybackPlanMapper.insertSelective(paybackPlan);
			}
		}
	}

	/**
	 * 移除合同项目
	 * @param contractProjectUp
	 */
	private void removePayBackPlan(GContractProjectUp contractProjectUp){
		//更改合同项目为失效
		GContractProject contractProject = contractProjectMapper.selectByPrimaryKey(contractProjectUp.getContractProjectId());
		contractProject.setStatus(UtilConst.STR_NUMBER_0);
		contractProjectMapper.updateByPrimaryKeySelective(contractProject);

		//更新回款计划
		Example example1 = new Example(GPaybackPlan.class);
		example1.createCriteria().andEqualTo("contractProjectId",contractProject.getId());
		List<GPaybackPlan> paybackPlanList = paybackPlanMapper.selectByExample(example1);
		for(GPaybackPlan paybackPlan:paybackPlanList){
			paybackPlan.setStatus(UtilConst.STR_NUMBER_0);
			paybackPlanMapper.updateByPrimaryKeySelective(paybackPlan);
		}
	}

	/**
	 * 变更合同项目
	 * @param contract
	 * @param contractProjectUp
	 */
	private void changePayBackPlan(GContract contract,GContractProjectUp contractProjectUp){
		GContractProject contractProject = contractProjectMapper.selectByPrimaryKey(contractProjectUp.getContractProjectId());
		contractProject.setTollMode(contractProjectUp.getTollMode());
		contractProject.setPaymentCycle(contractProjectUp.getPaymentCycle());
		contractProject.setUnitPrice(contractProjectUp.getUnitPrice());
		contractProject.setNumber(contractProjectUp.getNumber());
		contractProject.setUnitName(contractProjectUp.getUnitName());
		contractProject.setSubtotal(contractProjectUp.getSubtotal());
		contractProject.setUpdateTime(new Date());
		contractProjectMapper.updateByPrimaryKeySelective(contractProject);
        Example exampleResource = new Example(GContractResource.class);
        exampleResource.createCriteria().andEqualTo("contractProjectId",contractProject.getId());
        contractResourceMapper.deleteByExample(exampleResource);
        Example exampleResourceUp = new Example(GContractResourceUp.class);
        exampleResourceUp.createCriteria().andEqualTo("contractProjectId",contractProjectUp.getId());
        List<GContractResourceUp> contractResources = contractResourceUpMapper.selectByExample(exampleResourceUp);
        for(GContractResourceUp contractResourceUp:contractResources){
            GContractResource contractResource = new GContractResource();
            contractResource.setContractId(contractProjectUp.getContractId());
            contractResource.setContractProjectId(contractProject.getId());
            contractResource.setResourceId(contractResourceUp.getResourceId());
            contractResourceMapper.insertSelective(contractResource);
        }

		//获取回款记录
		Example exampleRecord = new Example(GPaybackRecord.class);
		exampleRecord.createCriteria().andEqualTo("contractProjectId",contractProject.getId());
		List<GPaybackRecord> paybackRecordList = paybackRecordMapper.selectByExample(exampleRecord);
		BigDecimal backAmount = new BigDecimal(0);
		for(GPaybackRecord paybackRecord:paybackRecordList){
			backAmount = backAmount.add(paybackRecord.getPaybackAmount());
		}
		contractProject.setChangePaybackAmount(backAmount);
		contractProjectMapper.updateByPrimaryKeySelective(contractProject);

		//生成回款计划
		if(UtilConst.STR_NUMBER_1.equals(contractProject.getTollMode())){
			//删除未回款的回款计划
			Example examplePlan = new Example(GPaybackPlan.class);
			examplePlan.createCriteria().andEqualTo("contractProjectId",contractProject.getId());
			List<GPaybackPlan> paybackPlanList = paybackPlanMapper.selectByExample(examplePlan);
			for(GPaybackPlan paybackPlan:paybackPlanList){
				paybackPlan.setStatus(UtilConst.STR_NUMBER_0);
				paybackPlanMapper.updateByPrimaryKeySelective(paybackPlan);
			}

			GPaybackPlan paybackPlan = new GPaybackPlan();
			paybackPlan.setContractId(contract.getId());
			paybackPlan.setCentralCityId(contract.getCentralCityId());
			paybackPlan.setCityId(contract.getCityId());
			paybackPlan.setName(contract.getName());
			paybackPlan.setMerchantId(contract.getMerchantId());
			paybackPlan.setContractProjectId(contractProject.getId());
			paybackPlan.setReceivablePayback(contractProject.getSubtotal().subtract(backAmount));
			paybackPlan.setExtensionStatus(UtilConst.STR_NUMBER_0);
			paybackPlan.setBeforeRemind(UtilConst.STR_NUMBER_0);
			paybackPlan.setExtensionRemind(UtilConst.STR_NUMBER_0);
			paybackPlan.setStatus(UtilConst.STR_NUMBER_1);
			paybackPlan.setDelStatus(UtilConst.STR_NUMBER_1);
			paybackPlan.setEditStatus(UtilConst.STR_NUMBER_0);
			Date date = getPayBackTime(contract,0);
			if (date.compareTo(contract.getDeadlineEndTime()) > 0) {
				paybackPlan.setPaybackTime(contract.getDeadlineEndTime());
			}else {
				paybackPlan.setPaybackTime(date);
			}
			String dateStr = Util.date2Str(date,Util.DATE_YYYY_MM_DD_CHN);
			dateStr = dateStr + " 23:59:59";
			if(Util.str2Date(dateStr,Util.DATE_TIME_PAT_19_).compareTo(new Date())<0){
				paybackPlan.setPaybackStatus(UtilConst.STR_NUMBER_2);
			}else {
				paybackPlan.setPaybackStatus(UtilConst.STR_NUMBER_1);
			}
			paybackPlan.setCreateTime(new Date());
			paybackPlan.setCreateName(contract.getCreateName());
			paybackPlanMapper.insertSelective(paybackPlan);
		}else {
		    //删除未回款的回款计划
            Example examplePlan = new Example(GPaybackPlan.class);
            examplePlan.createCriteria().andEqualTo("contractProjectId",contractProject.getId());
            List<GPaybackPlan> paybackPlanList = paybackPlanMapper.selectByExample(examplePlan);
            for(GPaybackPlan paybackPlan:paybackPlanList){
                paybackPlan.setStatus(UtilConst.STR_NUMBER_0);
                paybackPlanMapper.updateByPrimaryKeySelective(paybackPlan);
            }

            //生成新回款记录
			BigDecimal amount = contractProject.getSubtotal().subtract(backAmount);
			List<Date> dates = getPayBackCount(contract,contractProject);
			BigDecimal sum = new BigDecimal(0);
			for(int i=0;i<dates.size();i++){
				GPaybackPlan paybackPlan = new GPaybackPlan();
				paybackPlan.setContractId(contract.getId());
				paybackPlan.setCentralCityId(contract.getCentralCityId());
				paybackPlan.setCityId(contract.getCityId());
				paybackPlan.setName(contract.getName());
				paybackPlan.setMerchantId(contract.getMerchantId());
				paybackPlan.setContractProjectId(contractProject.getId());
				if(UtilConst.STR_NUMBER_2.equals(contractProject.getTollMode())){
					if(UtilConst.STR_NUMBER_2.equals(contractProject.getTollMode())){
						if(amount.compareTo(new BigDecimal(0))>0){
							sum = sum.add(amount.divide(new BigDecimal(dates.size()),2,BigDecimal.ROUND_UP));
							if(sum.compareTo(amount)>0){
								BigDecimal a = sum.subtract(amount);
								paybackPlan.setReceivablePayback(amount.divide(new BigDecimal(dates.size()),2,BigDecimal.ROUND_UP).subtract(a));
							}else {
								paybackPlan.setReceivablePayback(amount.divide(new BigDecimal(dates.size()),2,BigDecimal.ROUND_UP));
							}
						}else {
							sum = sum.add(amount.divide(new BigDecimal(dates.size()),2,BigDecimal.ROUND_UP));
							if(sum.compareTo(amount)<0){
								BigDecimal a = sum.subtract(amount);
								paybackPlan.setReceivablePayback(amount.divide(new BigDecimal(dates.size()),2,BigDecimal.ROUND_UP).add(a));
							}else {
								paybackPlan.setReceivablePayback(amount.divide(new BigDecimal(dates.size()),2,BigDecimal.ROUND_UP));
							}
						}
					}
				}
				String dateStr = Util.date2Str(dates.get(i),Util.DATE_YYYY_MM_DD_CHN);
				dateStr = dateStr + " 23:59:59";
				if(Util.str2Date(dateStr,Util.DATE_TIME_PAT_19_).compareTo(new Date())<0){
					paybackPlan.setPaybackStatus(UtilConst.STR_NUMBER_2);
				}else {
					paybackPlan.setPaybackStatus(UtilConst.STR_NUMBER_1);
				}
				paybackPlan.setExtensionStatus(UtilConst.STR_NUMBER_0);
				paybackPlan.setBeforeRemind(UtilConst.STR_NUMBER_0);
				paybackPlan.setExtensionRemind(UtilConst.STR_NUMBER_0);
				paybackPlan.setStatus(UtilConst.STR_NUMBER_1);
				paybackPlan.setDelStatus(UtilConst.STR_NUMBER_1);
				paybackPlan.setEditStatus(UtilConst.STR_NUMBER_0);
				paybackPlan.setPaybackTime(dates.get(i));
				paybackPlan.setCreateName(contract.getCreateName());
				paybackPlan.setCreateTime(new Date());
				paybackPlanMapper.insertSelective(paybackPlan);
			}
		}
	}

	private List<Date> getPayBackCount(GContract contract,GContractProject contractProject){
		List<Date> dates = new ArrayList<>();
		int j = 1;
		if (UtilConst.STR_NUMBER_1.equals(contractProject.getPaymentCycle())) {
			j = 12;
		}
		if (UtilConst.STR_NUMBER_2.equals(contractProject.getPaymentCycle())) {
			j = 6;
		}
		if (UtilConst.STR_NUMBER_3.equals(contractProject.getPaymentCycle())) {
			j = 3;
		}
		if (UtilConst.STR_NUMBER_6.equals(contractProject.getPaymentCycle())) {
			j = 24;
		}
		if (UtilConst.STR_NUMBER_7.equals(contractProject.getPaymentCycle())) {
			j = 36;
		}
		if (UtilConst.STR_NUMBER_8.equals(contractProject.getPaymentCycle())) {
			j = 48;
		}
		if (UtilConst.STR_NUMBER_9.equals(contractProject.getPaymentCycle())) {
			j = 60;
		}
		for(int i=0;i<9999;i++) {
			Date date;
			if (UtilConst.STR_NUMBER_5.equals(contractProject.getPaymentCycle())) {
				return getWeekList(contract);
			} else {
				date = getPayBackTime(contract, i * j);
			}
			if (date.compareTo(contract.getDeadlineEndTime()) > 0) {
				if(Util.isEmptyList(dates)){
					dates.add(contract.getDeadlineEndTime());
				}
				return dates;
			}
			dates.add(date);
		}
		return null;
	}

	private List<Date> getWeekList(GContract contract){
		Date startDate = contract.getDeadlineStartTime();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		List<Date> dates = new ArrayList<>();
		for(int i=0;i<9999;i++){
			calendar.add(Calendar.DATE, 1);//DATE=日
			if ((calendar.get(Calendar.DAY_OF_WEEK)) == 2) {
				dates.add(calendar.getTime());
			}
			if(calendar.getTime().compareTo(contract.getDeadlineEndTime()) >= 0){
				if(Util.isEmptyList(dates)){
					dates.add(contract.getDeadlineEndTime());
				}
				return dates;
			}
		}
		return null;
	}

	private Date getPayBackTime(GContract contract,int number){
		Date startDate = contract.getDeadlineStartTime();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
		int start = calendar.get(Calendar.DATE);
		String dateString ="";
		if(start<15){
			dateString=year+"-"+(month+1+number)+"-15";
		}else {
			dateString=year+"-"+(month+2+number)+"-15";
		}
		return Util.str2Date(dateString,Util.DATE_YYYY_MM_DD_CHN);
	}

	private GContractApproval saveApproveInfo(Long empId,GContract contract,String type){
	    GEmployee employee = employeeMapper.selectByPrimaryKey(empId);
		//获取上级审批人
		Example example = new Example(GRoleRelationship.class);
		example.createCriteria().andEqualTo("empId",empId);
		List<GRoleRelationship> roleRelationships = roleRelationshipMapper.selectByExample(example);
		Long i =0L;
		for(GRoleRelationship roleRelationship:roleRelationships){
			if(i<roleRelationship.getRoleId()){
				i = roleRelationship.getRoleId();
			}
		}
		GCity city = cityMapper.selectByPrimaryKey(contract.getCityId());
		GContractApproval contractApproval = new GContractApproval();
		if(i.intValue()==2){
			contractApproval.setSupperEmployeeId(city.getBusinessPrincipal().toString());
		}else if(i.intValue()==3){
			contractApproval.setSupperEmployeeId(city.getCityPrincipal().toString());
		}else if(i.intValue()==4){
			contractApproval.setSupperEmployeeId(city.getCityPrincipal().toString());
		}else if(i.intValue()==6){
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
			contractApproval.setSupperEmployeeId(supperEmpId);
		}else {
			contractApproval.setSupperEmployeeId(city.getBusinessPrincipal().toString());
		}
		contractApproval.setContractId(contract.getId());
		contractApproval.setType(type);
		contractApproval.setStatus(UtilConst.STR_NUMBER_1);
		contractApproval.setCreateTime(new Date());
        contractApproval.setCreateName(employee.getEmpName());
		contractApprovalMapper.insertSelective(contractApproval);
		return contractApproval;
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
}
