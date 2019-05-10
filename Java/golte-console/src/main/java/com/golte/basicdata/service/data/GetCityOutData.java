package com.golte.basicdata.service.data;

import com.golte.mapper.entity.GCity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 操作员工返回结果
 *
 * @author ws
 * @date 2019/3/22 15:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCityOutData implements Serializable {
    private static final long serialVersionUID = 1L;

    // 城市id
    private Long id;

    // 中心城市Id
    private Long centralCityId;

    // 中心城市名称
    private String centralCityName;

    //城市名称
    private String cityName;

    //城市分公司负责人Id
    private Long cityPrincipal;

    //城市分公司负责人
    private String cityLeaderName;

    //经营部门负责人Id
    private Long businessPrincipal;

    //经营部门负责人
    private String departmentLeaderName;

    //录入人员Id集合
    private List<Long> enterPersonIds;

    //录入人员集合
    private List<String> enterPersonName;

    //年度指标
    private BigDecimal yearTarget;

    //中心城市负责人名称
    private String empName;

    //录入人员列表
    private List<GetEmployeeOutData> employeeList;

    //城市列表
    private List<GCity> cityList;

    //1是全国的显示
    private  String type;

}
