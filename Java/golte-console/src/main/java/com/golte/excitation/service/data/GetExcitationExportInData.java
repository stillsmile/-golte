package com.golte.excitation.service.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 激励导入参数
 *
 * @author ws
 * @date 2019/3/22 15:52
 */
@Data
@NoArgsConstructor
public class GetExcitationExportInData implements Serializable {

    //类别-->激励类别 -->合同类别中的一种
    private String contractType;

    //项目名称
    private String projectName;

    //物业利润收入
    private String propertyProfit;

    //税金百分比
    private String taxPercentage;

    // 税金==物业利润收入/税金百分比
    private String tax;

    //激励基数
    private String cardinalNumber;

    //激励百分比
    private String excitationPercentage;

    //激励金额 == 实际激励金额（月度对应的就是实际激励金额，年度的除此还有一个应激励金额）
    private String actualAmount;

    //激励人员
    private String memberName;

    //激励月度
    private String month;

    /**
     * 序号
     */
    private String index;


}
