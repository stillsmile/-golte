package com.golte.excitation.service.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 激励月度导出
 *
 * @author ws
 * @date 2019/4/11 15:52
 */
@Data
@NoArgsConstructor
public class GetExcitationExportOutData implements Serializable {

    //已收回款
    private BigDecimal paybackamount;

    //激励类别 -->合同别中的一种-->合同类别
    private String contractType;

    //项目名称
    private String projectName;

    //所在城市
    private String cityName;

    //奖励类型（1月度 2年度）--->激励类型
    private String type;

    //激励人员
    private String memberName;

    //物业利润收入 ---->利润金额
    private BigDecimal propertyProfit;

    //激励金额 == 实际激励金额（月度对应的就是实际激励金额，年度的除此还有一个应激励金额）
    private BigDecimal actualAmount;

}
