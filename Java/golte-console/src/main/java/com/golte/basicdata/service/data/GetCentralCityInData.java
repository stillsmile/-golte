package com.golte.basicdata.service.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
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
public class GetCentralCityInData implements Serializable {
    private static final long serialVersionUID = 1L;


    // 中心城市id
    private Long id;

    // 中心城市（唯一）
    private String centralCityName;

    //中心城市负责人
    private Long empId;

    // 操作人[默认取当前登录人]
    private String loginAccount;

    //删除中心城市ID集合
    private List<Long> ids;

    //角色id
    private Long roleId;

    /**
     * 有权限的城市ID集合
     */
    private List<Long> cityIds;

    //页大小
    private int pageSize;

    //页码
    private int pageNum;
}
