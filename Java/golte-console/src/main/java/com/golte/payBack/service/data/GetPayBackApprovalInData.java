package com.golte.payBack.service.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPayBackApprovalInData implements Serializable {
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
	 *延时天数
	 */
	private Integer extensionDayNum;

	/**
	 *延时日期
	 */
	private Date extensionTime;

	/**
	 *延期金额
	 */
	private BigDecimal extensionAmount;

	/**
	 *延期原因
	 */
	private String extensionReason;

	/**
	 *上级审批人ID
	 */
	private Long supperEmployeeId;

	/**
	 *审批状态
	 */
	private String status;

	/**
	 *审批意见
	 */
	private String opinion;

	/**
	 *审批时间
	 */
	private Date approvalTime;

	/**
	 *操作人ID
	 */
	private Long empId;

}
