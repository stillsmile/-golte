package com.golte.contract.service.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class GetContractExportInData implements Serializable {
	/**
	 *合同编号
	 */
	private String code;

	/**
	 *合同名称
	 */
	private String name;

	/**
	 *商家名称
	 */
	private String merchantName;

	/**
	 *合同期限开始时间
	 */
	private String deadlineStartTime;

	/**
	 *合同期限结束时间
	 */
	private String deadlineEndTime;

	/**
	 *合同总额
	 */
	private String amount;

	/**
	 *付款周期 1年 2半年 3季度 4月
	 */
	private String paymentCycle;

	/**
	 *履约保证金
	 */
	private String margin;

	/**
	 *合同类别 1临时摆展
	 */
	private String contractType;

	/**
	 *收费模式 1一次性 2固定 3佣金 4其他
	 */
	private String tollMode;

	/**
	 *资源单价
	 */
	private String unitPrice;

	/**
	 *单位
	 */
	private String unitName;

	/**
	 *资源数量
	 */
	private String number;

	/**
	 *小计
	 */
	private String subtotal;

	/**
	 *所在项目
	 */
	private String projectName;

	/**
	 * 序号
	 */
	private String index;
}
