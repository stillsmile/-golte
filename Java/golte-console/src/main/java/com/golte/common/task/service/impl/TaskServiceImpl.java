package com.golte.common.task.service.impl;

import com.golte.common.util.Util;
import com.golte.common.util.UtilConst;
import com.golte.mapper.*;
import com.golte.mapper.entity.*;
import com.golte.system.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.golte.common.task.service.TaskService;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private GEmployeeMapper employeeMapper;

    @Autowired
    private GContractApprovalMapper contractApprovalMapper;

    @Autowired
    private GPaybackApprovalMapper paybackApprovalMapper;

    @Autowired
    private GMessagePushMapper messagePushMapper;

    @Autowired
    private GPaybackPlanMapper paybackPlanMapper;

    @Autowired
    private GRoleRelationshipMapper roleRelationshipMapper;

    @Autowired
    private GCityMapper cityMapper;

    @Autowired
    private CommonService commonService;

    /**
     * 发送合同变更待审批消息
     */
    @Override
    @Transactional
    public void sendChangeMessage() throws Exception {
        //查询用户列表
        Example example = new Example(GEmployee.class);
        example.createCriteria().andEqualTo("empStatus", UtilConst.STR_NUMBER_1)
                .andEqualTo("delStatus",UtilConst.STR_NUMBER_1);
        List<GEmployee> employeeList = employeeMapper.selectByExample(example);
        for(GEmployee employee:employeeList){
            Example exampleApprove = new Example(GContractApproval.class);
            exampleApprove.createCriteria()
                    .andEqualTo("type",UtilConst.STR_NUMBER_2)
                    .andEqualTo("status",   UtilConst.STR_NUMBER_1);
            List<GContractApproval> contractApprovalList = contractApprovalMapper.selectByExample(exampleApprove);
            List<GContractApproval> contractApprovals = new ArrayList<>();
            for(GContractApproval contractApproval:contractApprovals){
                String[] empIds = contractApproval.getSupperEmployeeId().split(",");
                if(Arrays.asList(empIds).contains(employee.getId())){
                    contractApprovals.add(contractApproval);
                }
            }

            if(contractApprovals.size()>0){
                Calendar c = Calendar.getInstance();
                Date date=new Date();
                c.setTime(date);
                int day=c.get(Calendar.DATE);
                c.set(Calendar.DATE,day-1);
                String dayBefore=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
                Example example1 = new Example(GMessagePush.class);
                example1.createCriteria().andLessThan("createTime",dayBefore+" 23:59:59");
                messagePushMapper.deleteByExample(example1);
                //发送站内信
                GMessagePush messagePush = new GMessagePush();
                messagePush.setMesTitle("合同变更待审批消息");
                messagePush.setMesContent("您有"+contractApprovals.size()+"份合同发生变更，请及时进行审批，谢谢！");
                messagePush.setMesReceiveEmpId(employee.getId());
                messagePush.setMesStatus(UtilConst.STR_NUMBER_0);
                messagePush.setCreateTime(new Date());
                messagePushMapper.insertSelective(messagePush);

                //发送邮件
                String msg = employee.getEmpName()+"同学：您的高地物业资源管理系统软件账号内有"+contractApprovals.size()+"份合同发生变更，请及时登录账号进行审批，谢谢！";
                commonService.sendMail(employee.getEmpEmail(),msg, "关于高地物业资源管理系统合同变更审批通知",null);
            }
        }
    }

    /**
     * 发送合同终止待审批消息
     */
    @Override
    @Transactional
    public void sendStopMessage() throws Exception {
        //查询用户列表
        Example example = new Example(GEmployee.class);
        example.createCriteria().andEqualTo("empStatus", UtilConst.STR_NUMBER_1)
                .andEqualTo("delStatus",UtilConst.STR_NUMBER_1);
        List<GEmployee> employeeList = employeeMapper.selectByExample(example);
        for(GEmployee employee:employeeList){
            Example exampleApprove = new Example(GContractApproval.class);
            exampleApprove.createCriteria()
                    .andEqualTo("type",UtilConst.STR_NUMBER_1)
                    .andEqualTo("status",   UtilConst.STR_NUMBER_1);
            List<GContractApproval> contractApprovalList = contractApprovalMapper.selectByExample(exampleApprove);
            List<GContractApproval> contractApprovals = new ArrayList<>();
            for(GContractApproval contractApproval:contractApprovals){
                String[] empIds = contractApproval.getSupperEmployeeId().split(",");
                if(Arrays.asList(empIds).contains(employee.getId())){
                    contractApprovals.add(contractApproval);
                }
            }

            if(contractApprovals.size()>0){
                Calendar c = Calendar.getInstance();
                Date date=new Date();
                c.setTime(date);
                int day=c.get(Calendar.DATE);
                c.set(Calendar.DATE,day-1);
                String dayBefore=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
                Example example1 = new Example(GMessagePush.class);
                example1.createCriteria().andLessThan("createTime",dayBefore+" 23:59:59");
                messagePushMapper.deleteByExample(example1);
                //发送站内信
                GMessagePush messagePush = new GMessagePush();
                messagePush.setMesTitle("合同终止待审批消息");
                messagePush.setMesContent("您有"+contractApprovals.size()+"份合同申请终止，请及时进行审批，谢谢！");
                messagePush.setMesReceiveEmpId(employee.getId());
                messagePush.setMesStatus(UtilConst.STR_NUMBER_0);
                messagePush.setCreateTime(new Date());
                messagePushMapper.insertSelective(messagePush);

                //发送邮件
                String msg = employee.getEmpName()+"同学：您的高地物业资源管理系统软件账号内有"+contractApprovals.size()+"份合同申请终止，请及时登录账户进行审批，谢谢！";
                commonService.sendMail(employee.getEmpEmail(),msg, "关于高地物业资源管理系统合同终止审批通知",null);
            }
        }
    }

    /**
     * 发送回款延期待审批消息
     */
    @Override
    @Transactional
    public void sendDelayMessage() throws Exception {
        //查询用户列表
        Example example = new Example(GEmployee.class);
        example.createCriteria().andEqualTo("empStatus", UtilConst.STR_NUMBER_1)
                .andEqualTo("delStatus",UtilConst.STR_NUMBER_1);
        List<GEmployee> employeeList = employeeMapper.selectByExample(example);
        for(GEmployee employee:employeeList){
            Example exampleApprove = new Example(GPaybackApproval.class);
            exampleApprove.createCriteria()
                    .andEqualTo("status",   UtilConst.STR_NUMBER_1);
            List<GPaybackApproval> paybackApprovalList = paybackApprovalMapper.selectByExample(exampleApprove);
            List<GContractApproval> contractApprovals = new ArrayList<>();
            for(GContractApproval contractApproval:contractApprovals){
                String[] empIds = contractApproval.getSupperEmployeeId().split(",");
                if(Arrays.asList(empIds).contains(employee.getId())){
                    contractApprovals.add(contractApproval);
                }
            }

            if(contractApprovals.size()>0){
                Calendar c = Calendar.getInstance();
                Date date=new Date();
                c.setTime(date);
                int day=c.get(Calendar.DATE);
                c.set(Calendar.DATE,day-1);
                String dayBefore=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
                Example example1 = new Example(GMessagePush.class);
                example1.createCriteria().andLessThan("createTime",dayBefore+" 23:59:59");
                messagePushMapper.deleteByExample(example1);
                //发送站内信
                GMessagePush messagePush = new GMessagePush();
                messagePush.setMesTitle("回款延期待审批消息");
                messagePush.setMesContent("您有"+contractApprovals.size()+"份应收回款申请延期收款，请及时进行审批，谢谢！");
                messagePush.setMesReceiveEmpId(employee.getId());
                messagePush.setMesStatus(UtilConst.STR_NUMBER_0);
                messagePush.setCreateTime(new Date());
                messagePushMapper.insertSelective(messagePush);

                //发送邮件
                String msg = employee.getEmpName()+"同学：您的高地物业资源管理系统软件账号内有"+contractApprovals.size()+"份应收回款申请延期收款，请及时登录账号进行审批，谢谢！";
                commonService.sendMail(employee.getEmpEmail(),msg, "关于高地物业资源管理系统回款延期审批通知",null);
            }
        }
    }

    /**
     * 发送提前7日未回款提醒
     */
    @Override
    @Transactional
    public void sendAdvanceRemind() throws Exception {
        //查询用户列表
        Example example = new Example(GEmployee.class);
        example.createCriteria().andEqualTo("empStatus", UtilConst.STR_NUMBER_1)
                .andEqualTo("delStatus",UtilConst.STR_NUMBER_1);
        List<GEmployee> employeeList = employeeMapper.selectByExample(example);
        for(GEmployee employee:employeeList){
            //查询回款计划列表
            Example examplePlan = new Example(GPaybackPlan.class);
            examplePlan.createCriteria()
                    .andEqualTo("paybackStatus", UtilConst.STR_NUMBER_1)
                    .andEqualTo("delStatus",UtilConst.STR_NUMBER_1)
                    .andEqualTo("beforeRemind",UtilConst.STR_NUMBER_0)
                    .andEqualTo("status",UtilConst.STR_NUMBER_1)
                    .andEqualTo("createName",employee.getEmpName());
            List<GPaybackPlan> paybackPlanList = paybackPlanMapper.selectByExample(examplePlan);
            List<GPaybackPlan> remindList = new ArrayList<>();
            List<Long> supperEmployeeIds = new ArrayList<>();
            for(GPaybackPlan paybackPlan:paybackPlanList){
                Date now = new Date();
                long days = getDaysBetween(paybackPlan.getPaybackTime(),now);
                if(days<=7){
                    //获取上级审批人
                    Example exampleRole = new Example(GRoleRelationship.class);
                    exampleRole.createCriteria().andEqualTo("empId",employee.getId());
                    List<GRoleRelationship> roleRelationships = roleRelationshipMapper.selectByExample(exampleRole);
                    Long i =0L;
                    for(GRoleRelationship roleRelationship:roleRelationships){
                        if(i<roleRelationship.getRoleId()){
                            i = roleRelationship.getRoleId();
                        }
                    }
                    GCity city = cityMapper.selectByPrimaryKey(paybackPlan.getCityId());
                    Long supperEmployeeId = city.getBusinessPrincipal();
                    if(i==2){
                        supperEmployeeId = city.getBusinessPrincipal();
                        supperEmployeeIds.add(supperEmployeeId);
                    }else if(i==3){
                        supperEmployeeId = city.getCityPrincipal();
                        supperEmployeeIds.add(supperEmployeeId);
                    }else if(i==4){
                        supperEmployeeId = city.getCityPrincipal();
                        supperEmployeeIds.add(supperEmployeeId);
                    }else if(i==6){
                        Example example1 = new Example(GRoleRelationship.class);
                        example1.createCriteria().andEqualTo("roleId",UtilConst.STR_NUMBER_7);
                        List<GRoleRelationship> roleRelationshipList = roleRelationshipMapper.selectByExample(example1);
                        for(GRoleRelationship roleRelationship:roleRelationshipList){
                            supperEmployeeIds.add(roleRelationship.getEmpId());
                        }
                    }else {
                        supperEmployeeIds.add(supperEmployeeId);
                    }
                    remindList.add(paybackPlan);
                }
            }

            if(remindList.size()>0){
                //发送站内信
                GMessagePush messagePush = new GMessagePush();
                messagePush.setMesTitle("提前7日未回款提醒");
                messagePush.setMesContent("您本月共有"+remindList.size()+"份应收回款，请及时进行款项的收缴工作，谢谢！");
                messagePush.setMesReceiveEmpId(employee.getId());
                messagePush.setMesStatus(UtilConst.STR_NUMBER_0);
                messagePush.setCreateTime(new Date());
                messagePushMapper.insertSelective(messagePush);
                for(GPaybackPlan paybackPlan:remindList){
                    paybackPlan.setBeforeRemind(UtilConst.STR_NUMBER_1);
                    paybackPlanMapper.updateByPrimaryKeySelective(paybackPlan);
                }

                List<String> cc = new ArrayList<>();
                for(int j = 0;j<supperEmployeeIds.size();j++){
                    GEmployee employee1 = employeeMapper.selectByPrimaryKey(supperEmployeeIds.get(j));
                    if(!cc.contains(employee1.getEmpEmail())){
                        cc.add(employee1.getEmpEmail());
                    }
                }
                String[] array = new String[cc.size()];
                String[] ccArray=cc.toArray(array);
                //发送邮件
                String msg = employee.getEmpName()+"同学：您的高地物业资源管理系统软件账号内本月共有"+remindList.size()+"份应收回款，请及时进行款项的收缴工作，谢谢！";
                commonService.sendMail(employee.getEmpEmail(),msg, "关于高地物业资源管理系统应收回款通知",ccArray);
            }
        }
    }

    /**
     * 发送延迟7日未回款提醒
     */
    @Override
    @Transactional
    public void sendDelayRemind() throws Exception {
        //查询用户列表
        Example example = new Example(GEmployee.class);
        example.createCriteria().andEqualTo("empStatus", UtilConst.STR_NUMBER_1)
                .andEqualTo("delStatus",UtilConst.STR_NUMBER_1);
        List<GEmployee> employeeList = employeeMapper.selectByExample(example);
        for(GEmployee employee:employeeList){
            //查询回款计划列表
            Example examplePlan = new Example(GPaybackPlan.class);
            examplePlan.createCriteria()
                    .andNotEqualTo("paybackStatus", UtilConst.STR_NUMBER_3)
                    .andEqualTo("delStatus",UtilConst.STR_NUMBER_1)
//                    .andEqualTo("extensionRemind",UtilConst.STR_NUMBER_0)
                    .andEqualTo("status",UtilConst.STR_NUMBER_1)
                    .andEqualTo("createName",employee.getEmpName());
            List<GPaybackPlan> paybackPlanList = paybackPlanMapper.selectByExample(examplePlan);
            List<GPaybackPlan> remindList = new ArrayList<>();
            List<Long> supperEmployeeIds = new ArrayList<>();
            for(GPaybackPlan paybackPlan:paybackPlanList){
                Date now = new Date();
                long days = getDaysBetween(paybackPlan.getPaybackTime(),now);
                if(days>0){
                    paybackPlan.setPaybackStatus(UtilConst.STR_NUMBER_2);
                    paybackPlanMapper.updateByPrimaryKeySelective(paybackPlan);
                }
                if(days>=7){
                    //获取上级审批人
                    Example exampleRole = new Example(GRoleRelationship.class);
                    exampleRole.createCriteria().andEqualTo("empId",employee.getId());
                    List<GRoleRelationship> roleRelationships = roleRelationshipMapper.selectByExample(exampleRole);
                    Long i =0L;
                    for(GRoleRelationship roleRelationship:roleRelationships){
                        if(i<roleRelationship.getRoleId()){
                            i = roleRelationship.getRoleId();
                        }
                    }
                    GCity city = cityMapper.selectByPrimaryKey(paybackPlan.getCityId());
                    Long supperEmployeeId = city.getBusinessPrincipal();
                    if(i==2){
                        supperEmployeeId = city.getBusinessPrincipal();
                        supperEmployeeIds.add(supperEmployeeId);
                    }else if(i==3){
                        supperEmployeeId = city.getCityPrincipal();
                        supperEmployeeIds.add(supperEmployeeId);
                    }else if(i==4){
                        supperEmployeeId = city.getCityPrincipal();
                        supperEmployeeIds.add(supperEmployeeId);
                    }else if(i==6){
                        Example example1 = new Example(GRoleRelationship.class);
                        example1.createCriteria().andEqualTo("roleId",UtilConst.STR_NUMBER_7);
                        List<GRoleRelationship> roleRelationshipList = roleRelationshipMapper.selectByExample(example1);
                        for(GRoleRelationship roleRelationship:roleRelationshipList){
                            supperEmployeeIds.add(roleRelationship.getEmpId());
                        }
                    }else {
                        supperEmployeeIds.add(supperEmployeeId);
                    }
                    remindList.add(paybackPlan);
                }
            }

            if(remindList.size()>0){
                //发送站内信
                GMessagePush messagePush = new GMessagePush();
                messagePush.setMesTitle("延迟7日未回款提醒");
                messagePush.setMesContent("您本月共有"+remindList.size()+"份应收回款未按时完成回款，请注意收款时间，及时确认回款，谢谢！");
                messagePush.setMesReceiveEmpId(employee.getId());
                messagePush.setMesStatus(UtilConst.STR_NUMBER_0);
                messagePush.setCreateTime(new Date());
                messagePushMapper.insertSelective(messagePush);
                for(GPaybackPlan paybackPlan:remindList){
                    paybackPlan.setExtensionRemind(UtilConst.STR_NUMBER_1);
                    paybackPlanMapper.updateByPrimaryKeySelective(paybackPlan);
                }

                List<String> cc = new ArrayList<>();
                for(int j = 0;j<supperEmployeeIds.size();j++){
                    GEmployee employee1 = employeeMapper.selectByPrimaryKey(supperEmployeeIds.get(j));
                    if(!cc.contains(employee1.getEmpEmail())){
                        cc.add(employee1.getEmpEmail());
                    }
                }
                String[] array = new String[cc.size()];
                String[] ccArray=cc.toArray(array);
                //发送邮件
                String msg = employee.getEmpName()+"同学：您的高地物业资源管理系统软件账号内本月共有"+remindList.size()+"份应收回款未按时完成回款，请注意收款时间，及时确认回款，谢谢！";
                commonService.sendMail(employee.getEmpEmail(),msg, "关于高地物业资源管理系统回款迟缴通知",ccArray);
            }
        }
    }

    /**
     * 发送延迟7日未回款提醒
     */
    @Override
    @Transactional
    public void updatePaybackStatus() throws Exception {
        //查询回款计划列表
        Example examplePlan = new Example(GPaybackPlan.class);
        examplePlan.createCriteria()
                .andNotEqualTo("paybackStatus", UtilConst.STR_NUMBER_3)
                .andEqualTo("delStatus",UtilConst.STR_NUMBER_1)
                .andEqualTo("status",UtilConst.STR_NUMBER_1);
        List<GPaybackPlan> paybackPlanList = paybackPlanMapper.selectByExample(examplePlan);
        for(GPaybackPlan paybackPlan:paybackPlanList){
            Date now = new Date();
            long days = getDaysBetween(paybackPlan.getPaybackTime(),now);
            if(days>0){
                paybackPlan.setPaybackStatus(UtilConst.STR_NUMBER_2);
                paybackPlanMapper.updateByPrimaryKeySelective(paybackPlan);
            }
        }
    }

    private static Long getDaysBetween(Date startDate, Date endDate) {
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(startDate);
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
        fromCalendar.set(Calendar.MINUTE, 0);
        fromCalendar.set(Calendar.SECOND, 0);
        fromCalendar.set(Calendar.MILLISECOND, 0);

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(endDate);
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);
        toCalendar.set(Calendar.MINUTE, 0);
        toCalendar.set(Calendar.SECOND, 0);
        toCalendar.set(Calendar.MILLISECOND, 0);

        return (toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24);
    }
}
