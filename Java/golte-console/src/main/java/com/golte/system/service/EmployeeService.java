package com.golte.system.service;

import com.golte.common.data.JsonResult;
import com.golte.system.service.data.GetEmployeeInData;

public interface EmployeeService {
    /**
     * 分页查询
     * @param inData
     * @return
     * @throws Exception
     */
    JsonResult selectEmployee(GetEmployeeInData inData) throws Exception;

    /**
     * 保存用户
     * @param inData
     * @return
     * @throws Exception
     */
    JsonResult saveEmployee(GetEmployeeInData inData) throws Exception;

    /**
     * 更新用户
     * @param inData
     * @return
     * @throws Exception
     */
    JsonResult updateEmployee(GetEmployeeInData inData) throws Exception;

    /**
     * 删除用户
     * @param inData
     * @return
     * @throws Exception
     */
    JsonResult deleteEmployee(GetEmployeeInData inData) throws Exception;

    /**
     * 重置密码
     * @author yq
     * @param inData
     * @return
     * @throws Exception
     */
    JsonResult resetPassword(GetEmployeeInData inData) throws Exception;

    /**
     * 启用账号
     * @author yq
     * @param inData
     * @return
     * @throws Exception
     */
    JsonResult enableEmployee(GetEmployeeInData inData) throws Exception;

    /**
     * 禁用账号
     * @author yq
     * @param inData
     * @return
     * @throws Exception
     */
    JsonResult disableEmployee(GetEmployeeInData inData) throws Exception;;
}
