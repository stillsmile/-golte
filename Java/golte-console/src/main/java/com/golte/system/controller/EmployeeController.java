package com.golte.system.controller;

import com.golte.common.exception.ErrorException;
import com.golte.common.util.BaseController;
import com.golte.common.data.JsonResult;
import com.golte.system.service.EmployeeService;
import com.golte.system.service.data.GetEmployeeInData;
import com.golte.system.service.data.GetLoginOutData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/pc/employee/")
public class EmployeeController extends BaseController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 分页查询
     * @param request
     * @param inData
     * @return
     */
    @PostMapping("select")
    public JsonResult selectEmployee(HttpServletRequest request, @RequestBody GetEmployeeInData inData) {
        try {
            return employeeService.selectEmployee(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 保存用户
     * @param request
     * @param inData
     * @return
     */
    @PostMapping("save")
    public JsonResult saveEmployee(HttpServletRequest request, @RequestBody GetEmployeeInData inData) {
        try {
            GetLoginOutData outData = getLoginUser(request);
            inData.setLoginAccount(outData.getLoginName());
            return employeeService.saveEmployee(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 更新用户
     * @param request
     * @param inData
     * @return
     */
    @PostMapping("update")
    public JsonResult updateEmployee(HttpServletRequest request, @RequestBody GetEmployeeInData inData) {
        try {
            GetLoginOutData outData = getLoginUser(request);
            inData.setLoginAccount(outData.getLoginName());
            return employeeService.updateEmployee(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 删除用户
     * @param request
     * @param inData
     * @return
     */
    @PostMapping("delete")
    public JsonResult deleteEmployee(HttpServletRequest request, @RequestBody GetEmployeeInData inData) {
        try {
            return employeeService.deleteEmployee(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 重置密码
     * @author yq
     * @param request
     * @param inData
     * @return
     */
    @PostMapping("resetPassword")
    public JsonResult resetPassword(HttpServletRequest request, @RequestBody GetEmployeeInData inData) {
        try {
            return employeeService.resetPassword(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 启用账号
     * @author yq
     * @param request
     * @param inData
     * @return
     */
    @PostMapping("enable")
    public JsonResult enableEmployee(HttpServletRequest request, @RequestBody GetEmployeeInData inData) {
        try {
            return employeeService.enableEmployee(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }

    /**
     * 禁用账号
     * @author yq
     * @param request
     * @param inData
     * @return
     */
    @PostMapping("disable")
    public JsonResult disableEmployee(HttpServletRequest request, @RequestBody GetEmployeeInData inData) {
        try {
            return employeeService.disableEmployee(inData);
        } catch (Exception e) {
            throw new ErrorException(e);
        }
    }
}
