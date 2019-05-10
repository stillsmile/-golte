package com.golte.excitation.service.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 操作激励入参
 *
 * @author ws
 * @date 2019/4/9
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcitationInData implements Serializable {
    private static final long serialVersionUID = 1L;

    //Id
    private Long id;

    //激励类型（1月度 2年度）
    private String type;

    //激励类别 -->合同别中的一种
    private String contractType;

    //项目Id
    private Long projectId;

    //项目名称
    private String projectName;

    //物业利润收入
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

    //激励年度
    private String yyear;

    //激励人员
    private String memberName;

    //管理酬金
    private BigDecimal managementAmount;

    //其他成本
    private BigDecimal otherCost;

    //年初利润指标
    private String profitIndex;

    //激励角色
    private Long roleId;

    //到岗折算
    private BigDecimal arrivalConversion;

    //其他扣除
    private BigDecimal otherDeduction;

    //模板状态 0正式 1草稿
    private String status;

    //开始时间
    private String starttime;

    //结束时间
    private String endtime;

    // 操作人[默认取当前登录人]
    private String loginAccount;

    //删除激励ID集合
    private List<Long> ids;

    /**城市ID*/
    private List<Long> cityIds;

    /**
     *token
     */
    private String token;

    //页大小
    private int pageSize;

    //页码
    private int pageNum;

}
