package com.golte.payBack.service.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPayBackRecordInData implements Serializable {
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
	private Date paybackTime;

}
