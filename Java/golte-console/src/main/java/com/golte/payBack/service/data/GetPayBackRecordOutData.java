package com.golte.payBack.service.data;

import com.golte.contract.service.data.GetContractProjectOutData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPayBackRecordOutData implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *主键ID
	 */
	private Long id;

	/**
	 *合同ID
	 */
	private Long contractId;

	/**
	 *商家ID
	 */
	private Long merchantId;

	/**
	 *合同名称
	 */
	private String name;

	/**
	 *合同总额
	 */
	private BigDecimal contractAmount;

	/**
	 *合同生效日期
	 */
	private String deadlineStartTime;

	/**
	 *合同结束日期
	 */
	private String deadlineEndTime;

	/**
	 *签约日期
	 */
	private String signingTime;

	/**
	 *回款计划ID
	 */
	private Long paybackPlanId;

	/**
	 *合同项目ID
	 */
	private Long contractProjectId;

	/**
	 *回款金额
	 */
	private BigDecimal paybackAmount;

	/**
	 *回款发票号
	 */
	private String paybackInvoiceCode;

	/**
	 *滞纳金
	 */
	private BigDecimal lastPayment;

	/**
	 *收款人
	 */
	private String receiptMember;

	/**
	 *回款时间
	 */
	private String paybackTime;

	/**
	 *收费模式 1一次性 2固定 3佣金 4其他
	 */
	private String tollMode;

	/**
	 *付款周期 1年 2半年 3季度 4月
	 */
	private String paymentCycle;

	/**
	 *资源单价
	 */
	private BigDecimal unitPrice;

	/**
	 *资源数量
	 */
	private Integer number;

	/**
	 *小计
	 */
	private BigDecimal subtotal;

	/**
	 *资源名称
	 */
	private String resourceName;

	/**
	 *应收回款
	 */
	private BigDecimal receivablePayback;

	/**
	 *计划回款时间
	 */
	private String planPaybackTime;

	/**
	 *备注
	 */
	private String remarks;

	/**
	 * 回款记录列表
	 */
	private List<GetRecordListOutData> recordList;

	/**
	 * 合同项目
	 */
	private List<GetContractProjectOutData> projectList;

}
