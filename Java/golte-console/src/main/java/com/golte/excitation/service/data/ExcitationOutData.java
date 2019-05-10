package com.golte.excitation.service.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 操作激励出参
 *
 * @author ws
 * @date 2019/4/9
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcitationOutData implements Serializable {
    private static final long serialVersionUID = 1L;

    //Id
    private Long id;

    /**合同编号*/
    private String code;

    //已收回款
    private BigDecimal paybackAmount;

    //奖励类型（1月度 2年度）--->激励类型
    private String type;

    //激励类别 -->合同别中的一种-->合同类别
    private String contractType;

    //项目Id
    private Long projectId;

    //项目名称
    private String projectName;

    //所在城市
    private String cityName;

    //物业利润收入 ---->利润金额
    private BigDecimal propertyProfit;

    //税金百分比
    private Double taxPercentage;

    // 税金==物业利润收入/税金百分比
    private BigDecimal tax;

    //激励基数
    private String cardinalNumber;

    //激励百分比
    private Double excitationPercentage;

    //激励金额 == 实际激励金额（月度对应的就是实际激励金额，年度的除此还有一个应激励金额）
    private BigDecimal actualAmount;

    //应激励金额
    private BigDecimal shouldAmount;

    //激励月度
    private String month;

    //激励人员
    private String memberName;

    //管理酬金
    private BigDecimal managementAmount;

    //其他成本
    private BigDecimal otherCost;

    //激励年份

    //年初利润指标
    private String profitIndex;

    //激励角色
    private Long roleId;

    //到岗折算
    private BigDecimal arrivalConversion;

    //其他扣除
    private BigDecimal otherDeduction;

    //模板状态
    private String status;

}
