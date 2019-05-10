package com.golte.system.service.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 操作员工请求
 *
 * @author wj@163.com
 * @date 2018/5/29 10:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetEmployeeInData implements Serializable {
    private static final long serialVersionUID = 1L;

    // 员工id
    private Long id;

    // 员工账号（唯一）
    private String empAccount;

    // 员工名称
    private String empName;

    // 职称
    private String empJobTitle;

    // 排序值
    private Integer empSortValue;

    // 邮箱
    private String empEmail;

    // 状态（0无效 1有效）
    private String empStatus;

    // 角色id集合
    private List<Long> roleIds;

    // 操作人[默认取当前登录人]
    private String loginAccount;

    //删除id集合
    private List<Long> ids;

    //页大小
    private int pageSize;

    //页码
    private int pageNum;
}
