package com.golte.basicdata.service.data;

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
public class GetCityInData implements Serializable {
    private static final long serialVersionUID = 1L;

    // 城市id
    private Long id;

    // 中心城市Id
    private Long centralCityId;

    //城市名称
    private String cityName;

    //城市分公司负责人
    private Long cityPrincipal;

    //经营部门负责人
    private Long businessPrincipal;

    //录入人员
    private Long enterPerson;

    // 录入人员id集合
    private List<Long> enterPersonIds;

    //年度指标
    private BigDecimal yearTarget;

    // 操作人[默认取当前登录人]
    private String loginAccount;

    //删除中心城市ID集合
    private List<Long> ids;

    /**
     * 城市ID集合
     */
    private List<Long> cityIds;

    //页大小
    private int pageSize;

    //页码
    private int pageNum;

}
