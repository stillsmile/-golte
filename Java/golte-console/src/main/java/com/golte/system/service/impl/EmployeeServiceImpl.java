package com.golte.system.service.impl;

import com.golte.common.data.JsonResult;
import com.golte.common.data.PageInfo;
import com.golte.common.util.Util;
import com.golte.common.util.UtilConst;
import com.golte.common.util.UtilMessage;
import com.golte.mapper.GEmployeeMapper;
import com.golte.mapper.GRoleMapper;
import com.golte.mapper.GRoleRelationshipMapper;
import com.golte.mapper.entity.GEmployee;
import com.golte.mapper.entity.GRole;
import com.golte.mapper.entity.GRoleRelationship;
import com.golte.system.service.EmployeeService;
import com.golte.system.service.data.GetEmployeeInData;
import com.golte.system.service.data.GetEmployeeOutData;
import com.github.pagehelper.PageHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.entity.Example;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private GEmployeeMapper employeeMapper;

    @Autowired
    private GRoleRelationshipMapper roleRelationshipMapper;

    @Autowired
    private GRoleMapper roleMapper;

    @Override
    public JsonResult selectEmployee(GetEmployeeInData inData) throws Exception {
        PageInfo<GetEmployeeOutData> pageInfo;
        PageHelper.startPage(inData.getPageNum(), inData.getPageSize());

        List<GetEmployeeOutData> outData = employeeMapper.selectListByCondition(inData);
        List<Long> empIds = new ArrayList<>();
        outData.forEach(x -> empIds.add(x.getId()));
        Example exampleRoleR = new Example(GRoleRelationship.class);

        List<GRoleRelationship> roleRelationships = new ArrayList<>();
        if (!Util.isEmpty(empIds)) {
            exampleRoleR.createCriteria().andIn("empId", empIds);
            roleRelationships = roleRelationshipMapper.selectByExample(exampleRoleR);
        }

        List<GRole> list = roleMapper.selectAll();
        HashMap<Long, String> roleMap = new HashMap<Long, String>();
        list.stream().forEach(x -> roleMap.put(x.getId(), x.getRoleName()));

        Map<Long, List<Long>> map = new HashMap<Long, List<Long>>();
        HashMap<Long, List<String>> employRoleMap = new HashMap<>();
        for (GRoleRelationship roleRelationship : roleRelationships) {
            List<Long> roleIds = map.get(roleRelationship.getEmpId());
            if (Util.isEmptyList(roleIds)) {
                roleIds = new ArrayList<>();
            }
            roleIds.add(roleRelationship.getRoleId());
            map.put(roleRelationship.getEmpId(), roleIds);
        }

        for (Map.Entry<Long, List<Long>> entry : map.entrySet()) {
            List<String> roleNames = new ArrayList<>();
            for (Long id : entry.getValue()) {
                String roleName = roleMap.get(id);
                roleNames.add(roleName);
            }
            employRoleMap.put(entry.getKey(), roleNames);
        }

        for (GetEmployeeOutData getEmployeeOutData : outData) {
            List<Long> roleIds = map.get(getEmployeeOutData.getId());
            List<String> roleNames = employRoleMap.get(getEmployeeOutData.getId());
            if (Util.isEmptyList(roleIds)) {
                roleIds = new ArrayList<>();
            }
            if (Util.isEmptyList(roleNames)) {
                roleNames = new ArrayList<>();
            }
            getEmployeeOutData.setRoleIds(roleIds);
            getEmployeeOutData.setRoleNames(roleNames);
        }

        pageInfo = new PageInfo<GetEmployeeOutData>(outData);
        return JsonResult.success(pageInfo, UtilMessage.GET_MESSAGE_SUCCESS);
    }

    @Override
    @Transactional
    public JsonResult saveEmployee(GetEmployeeInData inData) throws Exception {
        Example example = new Example(GEmployee.class);
        example.createCriteria().andEqualTo("empAccountNumber", inData.getEmpAccount()).andEqualTo("delStatus", UtilConst.STR_NUMBER_1);
        GEmployee entity = employeeMapper.selectOneByExample(example);
        if (!Util.isEmpty(entity) && entity.getEmpAccountNumber().equals(inData.getEmpAccount())) {
            return JsonResult.fail(UtilConst.CODE_403, UtilMessage.EMPLOYEE_ACCOUNT_REPEAT);
        }

        // 组装用户
        GEmployee employee = new GEmployee();
        employee.setEmpAccountNumber(inData.getEmpAccount());
        employee.setEmpPassword(Util.encryptMD5(UtilConst.DEFAULT_PASSWORD));
        employee.setEmpName(inData.getEmpName());
        employee.setEmpEmail(inData.getEmpEmail());
        employee.setEmpJobTitle(inData.getEmpJobTitle());
        employee.setEmpSortValue(1);
        employee.setEmpStatus(UtilConst.STR_NUMBER_1);
        employee.setDelStatus(UtilConst.STR_NUMBER_1);
        employee.setCreateTime(new Date());
        employee.setCreateName(inData.getLoginAccount());
        employeeMapper.insert(employee);

        // 用户-角色
        for (Long roleId : inData.getRoleIds()) {
            GRoleRelationship roleRelationship = new GRoleRelationship();
            roleRelationship.setEmpId(employee.getId());
            roleRelationship.setRoleId(roleId);
            roleRelationshipMapper.insert(roleRelationship);
        }
        return JsonResult.success("", UtilMessage.SAVE_MESSAGE_SUCCESS);
    }

    @Override
    @Transactional
    public JsonResult updateEmployee(GetEmployeeInData inData) throws Exception {
        GEmployee employee = employeeMapper.selectByPrimaryKey(inData.getId());

        // 更新用户
        employee.setEmpName(inData.getEmpName());
        employee.setEmpEmail(inData.getEmpEmail());
        employee.setEmpJobTitle(inData.getEmpJobTitle());
        employee.setUpdateTime(new Date());
        employee.setUpdateName(inData.getLoginAccount());
        employeeMapper.updateByPrimaryKeySelective(employee);

        // 用户-角色
        Example exampleRoleR = new Example(GRoleRelationship.class);
        exampleRoleR.createCriteria().andEqualTo("empId", employee.getId());
        roleRelationshipMapper.deleteByExample(exampleRoleR);
        for (Long roleId : inData.getRoleIds()) {
            GRoleRelationship roleRelationship = new GRoleRelationship();
            roleRelationship.setEmpId(employee.getId());
            roleRelationship.setRoleId(roleId);
            roleRelationshipMapper.insert(roleRelationship);
        }
        return JsonResult.success("", UtilMessage.SAVE_MESSAGE_SUCCESS);
    }

    @Override
    @Transactional
    public JsonResult deleteEmployee(GetEmployeeInData inData) throws Exception {
        // 删除账户-角色
        Example exampleRoleR = new Example(GRoleRelationship.class);
        exampleRoleR.createCriteria().andIn("empId", inData.getIds());
        roleRelationshipMapper.deleteByExample(exampleRoleR);
        // 删除员工
        Example example = new Example(GEmployee.class);
        example.createCriteria().andIn("id", inData.getIds());
        GEmployee entity = new GEmployee();
        entity.setDelStatus(UtilConst.STR_NUMBER_0);
        employeeMapper.updateByExampleSelective(entity, example);
        return JsonResult.success("", UtilMessage.DEL_MESSAGE_SUCCESS);
    }

    @Override
    @Transactional
    public JsonResult resetPassword(GetEmployeeInData inData) throws Exception {
        Example example = new Example(GEmployee.class);
        example.createCriteria().andIn("id", inData.getIds());
        GEmployee entity = new GEmployee();
        entity.setEmpPassword(Util.encryptMD5(UtilConst.DEFAULT_PASSWORD));
        employeeMapper.updateByExampleSelective(entity, example);
        return JsonResult.success("", UtilMessage.PASSWORD_MESSAGE_SUCCESS);
    }

    @Override
    @Transactional
    public JsonResult enableEmployee(GetEmployeeInData inData) throws Exception {
        Example example = new Example(GEmployee.class);
        example.createCriteria().andIn("id", inData.getIds());
        GEmployee entity = new GEmployee();
        entity.setEmpStatus(UtilConst.STR_NUMBER_1);
        employeeMapper.updateByExampleSelective(entity, example);
        return JsonResult.success("", UtilMessage.ENABLE_MESSAGE_SUCCESS);
    }

    @Override
    @Transactional
    public JsonResult disableEmployee(GetEmployeeInData inData) throws Exception{
        Example example = new Example(GEmployee.class);
        example.createCriteria().andIn("id", inData.getIds());
        GEmployee entity = new GEmployee();
        entity.setEmpStatus(UtilConst.STR_NUMBER_0);
        employeeMapper.updateByExampleSelective(entity, example);
        return JsonResult.success("", UtilMessage.DISABLE_MESSAGE_SUCCESS);
    }
}
