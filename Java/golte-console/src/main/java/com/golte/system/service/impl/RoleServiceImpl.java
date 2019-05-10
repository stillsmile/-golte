package com.golte.system.service.impl;

import com.golte.common.data.JsonResult;
import com.golte.common.data.PageInfo;
import com.golte.common.util.*;
import com.golte.mapper.GResourceRelationshipMapper;
import com.golte.mapper.GRoleMapper;
import com.golte.mapper.GRoleRelationshipMapper;
import com.golte.mapper.entity.GResourceRelationship;
import com.golte.mapper.entity.GRole;
import com.golte.mapper.entity.GRoleRelationship;
import com.golte.system.service.RoleService;
import com.golte.system.service.data.GetAuthorizationRoleInData;
import com.golte.system.service.data.GetRoleInData;
import com.golte.system.service.data.GetRoleOutData;
import com.github.pagehelper.PageHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private GRoleMapper roleMapper;

    @Autowired
    private GRoleRelationshipMapper roleRelationshipMapper;

    @Autowired
    private GResourceRelationshipMapper resourceRelationshipMapper;

    @Override
    public JsonResult selectRole(GetRoleInData inData) throws Exception {
        PageInfo<GetRoleOutData> pageInfo;
        PageHelper.startPage(inData.getPageNum(), inData.getPageSize());
        List<GetRoleOutData> outData = roleMapper.selectListByCondition(inData);
        if(Util.isEmptyList(outData)){
            pageInfo = new PageInfo<GetRoleOutData>(new ArrayList<GetRoleOutData>());
        }else {
            pageInfo = new PageInfo<GetRoleOutData>(outData);
        }
        return JsonResult.success(pageInfo, UtilMessage.GET_MESSAGE_SUCCESS);
    }

    @Override
    @Transactional
    public JsonResult saveRole(GetRoleInData inData) throws Exception {
        Example example = new Example(GRole.class);
        example.createCriteria().andEqualTo("roleName", inData.getRoleName());
        GRole entity = roleMapper.selectOneByExample(example);
        if (!Util.isEmpty(entity)) {
            return JsonResult.fail(UtilConst.CODE_403, UtilMessage.ROLE_NAME_REPEAT);
        }

        GRole role = new GRole();
        role.setRoleName(inData.getRoleName());
        role.setRoleDes(inData.getRoleDes());
        role.setRoleSortValue(inData.getRoleSortValue());
        role.setRoleStatus(UtilConst.STR_NUMBER_1);
        role.setCreateTime(new Date());
        role.setCreateName(inData.getLoginAccount());
        roleMapper.insert(role);
        return JsonResult.success("", UtilMessage.SAVE_MESSAGE_SUCCESS);
    }

    @Override
    @Transactional
    public JsonResult updateRole(GetRoleInData inData) throws Exception {
        GRole role = roleMapper.selectByPrimaryKey(inData.getId());
        if (!role.getRoleName().equals(inData.getRoleName())) {
            Example example = new Example(GRole.class);
            example.createCriteria().andEqualTo("roleName", inData.getRoleName());
            GRole entity = roleMapper.selectOneByExample(example);
            if (!Util.isEmpty(entity) && !entity.getId().equals(inData.getId())) {
                return JsonResult.fail(UtilConst.CODE_403, UtilMessage.ROLE_NAME_REPEAT);
            }
        }
        role.setRoleName(inData.getRoleName());
        role.setRoleDes(inData.getRoleDes());
        role.setRoleSortValue(inData.getRoleSortValue());
        role.setUpdateTime(new Date());
        role.setUpdateName(inData.getLoginAccount());
        roleMapper.updateByPrimaryKeySelective(role);
        return JsonResult.success("", UtilMessage.SAVE_MESSAGE_SUCCESS);
    }

    @Override
    @Transactional
    public JsonResult deleteRole(GetRoleInData inData) throws Exception {
        List<Long> ids = inData.getIds();
        for (Long id :ids) {
            if (Arrays.asList(UtilConst.INIT_ROLE).contains(id)) {
                return JsonResult.fail(UtilConst.CODE_403, UtilMessage.ROLE_INIT_DELETE_ERROR);
            }
        }
        // 查询选中角色下有没有用户
        Example example = new Example(GRoleRelationship.class);
        example.createCriteria().andIn("roleId", ids);
        List<GRoleRelationship> list = roleRelationshipMapper.selectByExample(example);
        if (!Util.isEmptyList(list)) {
            return JsonResult.fail(UtilConst.CODE_403, UtilMessage.ROLE_DELETE_ERROR);
        }
        // 删除角色-权限资源
        example = new Example(GResourceRelationship.class);
        example.createCriteria().andIn("roleId", ids);
        resourceRelationshipMapper.deleteByExample(example);
        // 批量删除角色
        example = new Example(GRole.class);
        example.createCriteria().andIn("id", ids);
        roleMapper.deleteByExample(example);
        return JsonResult.success("" , UtilMessage.DEL_MESSAGE_SUCCESS);
    }

    @Override
    @Transactional
    public JsonResult authorizationRole(GetAuthorizationRoleInData inData) throws Exception {
        // 先删除角色-权限资源
        Example example = new Example(GResourceRelationship.class);
        example.createCriteria().andEqualTo("roleId", inData.getId());
        resourceRelationshipMapper.deleteByExample(example);

        for (Long rescId : inData.getResourceIds()) {
            GResourceRelationship obj = new GResourceRelationship();
            obj.setRoleId(inData.getId());
            obj.setResourceId(rescId);
            resourceRelationshipMapper.insert(obj);
        }
        return JsonResult.success("", UtilMessage.SAVE_MESSAGE_SUCCESS);
    }

    @Override
    public JsonResult selectRoleEnum() throws Exception {
        Example example = new Example(GRole.class);
        example.createCriteria().andEqualTo("roleStatus", UtilConst.STR_NUMBER_1);
        List<GRole> list = roleMapper.selectByExample(example);
        List<GetRoleOutData> roles = new ArrayList<>();
        for (GRole entity : list) {
            GetRoleOutData outData = new GetRoleOutData();
            outData.setId(entity.getId());
            outData.setRoleName(entity.getRoleName());
            roles.add(outData);
        }
        return JsonResult.success(roles, UtilMessage.GET_MESSAGE_SUCCESS);
    }
}
