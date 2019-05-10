package com.golte.payBack.service.data;

import com.golte.contract.service.data.GetAnnexOutData;
import com.golte.contract.service.data.GetContractProjectOutData;
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
public class GetPayBackPlanOutData implements Serializable {
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
	 *商家ID
	 */
	private Long merchantId;

	/**
	 *商家名称
	 */
	private String merchantName;

	/**
	 *负责人
	 */
	private String createName;

	/**
	 *合同项目ID
	 */
	private Long contractProjectId;

	/**
	 *应收回款
	 */
	private BigDecimal receivablePayback;

	/**
	 *已回款金额
	 */
	private BigDecimal receivedAmount;

	/**
	 *回款状态 1未到期 2逾期未回款 3已完成
	 */
	private String paybackStatus;

	/**
	 *延期状态 0不延期 1延期
	 */
	private String extensionStatus;

	/**
	 *回款提前提醒 0未提醒 1已提醒
	 */
	private String beforeRemind;

	/**
	 *回款延时提醒 0未提醒 1已提醒
	 */
	private String extensionRemind;

	/**
	 *回款计划状态 0失效 1有效 2终止
	 */
	private String status;

	/**
	 *删除标志 0删除 1未删除
	 */
	private String delStatus;

	/**
	 *是否可编辑 0可编辑 1不可编辑
	 */
	private String editStatus;

	/**
	 *是否延期审批 0未审批 1审批中 2审批通过 3审批失败
	 */
	private String isApprove;

	/**
	 *回款延时天数
	 */
	private Integer extensionDayNum;

	/**
	 *延期金额
	 */
	private BigDecimal extensionAmount;

	/**
	 *延期回款时间
	 */
	private String extensionTime;

	/**
	 *计划回款时间
	 */
	private String paybackTime;

	/**
	 *汇款日期
	 */
	private String remittanceTime;

	/**
	 *备注
	 */
	private String remarks;

	/**
	 *审批人账号
	 */
	private String approvalAccount;

	/**
	 * 中心城市ID
	 */
	private Long centralCityId;

	/**
	 * 城市ID
	 */
	private Long cityId;

	/**
	 * 项目列表
	 */
	private List<GetContractProjectOutData> projectList;

}
