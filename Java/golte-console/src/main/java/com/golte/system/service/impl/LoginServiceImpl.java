package com.golte.system.service.impl;

import com.golte.common.data.JsonResult;
import com.golte.common.util.*;
import com.golte.mapper.*;
import com.golte.mapper.entity.*;
import com.golte.system.service.data.GetMenuOutData;

import com.golte.system.service.data.GetMessageOutData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.golte.system.service.LoginService;
import com.golte.system.service.data.GetLoginInData;
import com.golte.system.service.data.GetLoginOutData;

import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private GEmployeeMapper employeeMapper;

	@Autowired
	private GResourceMapper resourceMapper;

	@Autowired
	private GRoleRelationshipMapper roleRelationshipMapper;

	@Autowired
	private GResourceRelationshipMapper resourceRelationshipMapper;

	@Autowired
	private GMessagePushMapper messagePushMapper;
	
	@Override
	public JsonResult checkUserInfo(GetLoginInData indata) throws Exception {
		GetLoginOutData outData = new GetLoginOutData();
		Example example = new Example(GEmployee.class);
		example.createCriteria().andEqualTo("empAccountNumber", indata.getLoginAccount()).andEqualTo("delStatus", UtilConst.STR_NUMBER_1);
		GEmployee employee = employeeMapper.selectOneByExample(example);
		if (Util.isEmpty(employee) || !UtilConst.STR_NUMBER_1.equals(employee.getDelStatus())) {
			return JsonResult.fail(UtilConst.CODE_403, UtilMessage.LOGIN_NO_ACCOUNT);
		}
		if (UtilConst.STR_NUMBER_0.equals(employee.getEmpStatus())) {
			return JsonResult.fail(UtilConst.CODE_403, UtilMessage.LOGIN_ACCOUNT_INVALID);
		}
		if (!employee.getEmpPassword().equals(Util.encryptMD5(indata.getPassword()))) {
			return JsonResult.fail(UtilConst.CODE_403, UtilMessage.LOGIN_PASSWORD_ERROR);
		}
		// 查询用户权限
		List<GResource> resources = resourceMapper.getListByEmp(employee.getId());
		List<String> resourceList = new ArrayList<>();
		for (GResource resource : resources) {
			if (UtilConst.STR_NUMBER_3.equals(resource.getResType())) {
				resourceList.add(resource.getResUrl());
			}
		}
		outData.setEmpId(employee.getId());
		outData.setLoginAccount(employee.getEmpAccountNumber());
		outData.setLoginName(employee.getEmpName());
		outData.setResourceList(resourceList);
		String token = Jwt.sign(outData, 0);
		outData.setToken(token);
		CacheUtils.put(token, outData);
		return JsonResult.success(outData, UtilMessage.LOGIN_SUCCESS);
	}

	@Override
	public JsonResult getResByUser(Long empId) throws Exception {
		List<GetMenuOutData> top = new ArrayList<>();
		GetMessageOutData getMessageOutData = new GetMessageOutData();
		//获取用户的角色
		Example example = new Example(GRoleRelationship.class);
		example.createCriteria().andEqualTo("empId", empId);
		List<GRoleRelationship> roleRelationships = roleRelationshipMapper.selectByExample(example);
		if (Util.isEmpty(roleRelationships)) {
			return JsonResult.success(top, UtilMessage.GET_MESSAGE_SUCCESS);
		}
		List<Long> roleList = new ArrayList<Long>();
		for (GRoleRelationship roleRelationship : roleRelationships) {
			roleList.add(roleRelationship.getRoleId());
		}

		//获取角色对应的权限
		example = new Example(GResourceRelationship.class);
		example.createCriteria().andIn("roleId", roleList);
		List<GResourceRelationship> resourceRelationships = resourceRelationshipMapper.selectByExample(example);
		if (Util.isEmpty(resourceRelationships)) {
			return JsonResult.success(top, UtilMessage.GET_MESSAGE_SUCCESS);
		}
		List<Long> resourceList = new ArrayList<>();
		for (GResourceRelationship resourceRelationship : resourceRelationships) {
			resourceList.add(resourceRelationship.getResourceId());
		}

		// 查询权限
		example = new Example(GResource.class);
		example.setOrderByClause("RES_SEQ ASC");
		example.createCriteria().andIn("id", resourceList);
		List<GResource> resources = resourceMapper.selectByExample(example);

		Map<Long, GetMenuOutData> treeMap = new HashMap<>();

		for (GResource resource : resources) {
			if (UtilConst.STR_NUMBER_3.equals(resource.getResType())) {
				continue;
			}
			Long id = resource.getId();
			Long pId = resource.getResPartenId();

			GetMenuOutData tree = new GetMenuOutData();
			tree.setId(id);
			tree.setName(resource.getResName());
			tree.setIcon(resource.getResIcon());
			tree.setUrl(resource.getResUrl());
			tree.setPid(pId);
			treeMap.put(id, tree);
			if (pId == 0L) {
				top.add(tree);
			}
		}
		for (GResource resource : resources) {
			Long id = resource.getId();
			Long pId = resource.getResPartenId();
			GetMenuOutData idData = treeMap.get(id);
			GetMenuOutData pIdData = treeMap.get(pId);
			if (!Util.isEmpty(pIdData) && !Util.isEmpty(idData)) {
				pIdData.addChild(idData);
			}
		}

		//查询未读消息数量  //信息状态 0未读 1已读
		example = new Example(GMessagePush.class);
		example.createCriteria().andEqualTo("mesReceiveEmpId", empId)
		.andEqualTo("mesStatus", '0');
		List<GMessagePush> gMessagePushes = messagePushMapper.selectByExample(example);
		int number = gMessagePushes.size();
		//封装输出数据
		getMessageOutData.setTop(top);
		getMessageOutData.setNumber( number + "");
		return JsonResult.success(getMessageOutData, UtilMessage.GET_MESSAGE_SUCCESS);
	}

	@Override
	@Transactional
	public JsonResult updatePwd(GetLoginInData indata) throws Exception {
		if (6 > indata.getPasswordNew().length() || 20 < indata.getPasswordNew().length()) {
			return JsonResult.fail(UtilConst.CODE_403, UtilMessage.UPDATE_PWD_LENGTH);
		}
		if (indata.getPasswordOld().equals(indata.getPasswordNew())) {
			return JsonResult.fail(UtilConst.CODE_403, UtilMessage.UPDATE_PWD_SAME_PASSWORD);
		}

		Example example = new Example(GEmployee.class);
		example.createCriteria().andEqualTo("empAccountNumber", indata.getLoginAccount()).andEqualTo("delStatus", UtilConst.STR_NUMBER_1);
		GEmployee employee = employeeMapper.selectOneByExample(example);

		// 验证账号
		if (Util.isEmpty(employee) || !UtilConst.STR_NUMBER_1.equals(employee.getDelStatus())) {
			return JsonResult.fail(UtilConst.CODE_403, UtilMessage.LOGIN_NO_ACCOUNT);
		}
		// 验证账号是否失效
		if (UtilConst.STR_NUMBER_0.equals(employee.getEmpStatus())) {
			return JsonResult.fail(UtilConst.CODE_403, UtilMessage.LOGIN_ACCOUNT_INVALID);
		}
		// 验证原密码是否正确
		if (!employee.getEmpPassword().equals(Util.encryptMD5(indata.getPasswordOld()))) {
			return JsonResult.fail(UtilConst.CODE_403, UtilMessage.UPDATE_PWD_OLD_PASSWORD_ERROR);
		}

		// 修改密码
		employee.setEmpPassword(Util.encryptMD5(indata.getPasswordNew()));
		employeeMapper.updateByPrimaryKeySelective(employee);

		return JsonResult.success("", UtilMessage.UPDATE_PASSWORD_SUCCESS);
	}

}
