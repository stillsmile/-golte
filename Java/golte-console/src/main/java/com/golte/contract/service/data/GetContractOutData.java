package com.golte.contract.service.data;

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
public class GetContractOutData implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 序号
	 */
	private int sortNum;

	/**
	 *主键ID
	 */
	private Long id;

	/**
	 *合同编号
	 */
	private String code;

	/**
	 *合同名称
	 */
	private String name;

	/**
	 *合作商家ID
	 */
	private Long merchantId;

	/**
	 *合作商家名称
	 */
	private String merchantName;

	/**
	 *商家联系人
	 */
	private String merchantContact;

	/**
	 *商家联系电话
	 */
	private String merchantContactPhone;

	/**
	 *签约联系人
	 */
	private String signingContact;

	/**
	 *签约联系电话
	 */
	private String signingContactPhone;

	/**
	 *签约日期
	 */
	private String signingTime;

	/**
	 *合同期限开始时间
	 */
	private String deadlineStartTime;

	/**
	 *合同总额
	 */
	private BigDecimal amount;

	/**
	 *履约保证金
	 */
	private BigDecimal margin;

	/**
	 *签约类型 1新合同 2续签
	 */
	private String signingType;

	/**
	 *合同类别 1临时摆展
	 */
	private String contractType;

	/**
	 *合同状态 1进行中 2通过 3未通过 4变更审批中
	 */
	private String contractStatus;

	/**
	 *过审时间
	 */
	private String approvalTime;

	/**
	 *审批人账号
	 */
	private String approvalAccount;

	/**
	 *终止时间
	 */
	private String terminationTime;

	/**
	 * 未回款金额
	 */
	private BigDecimal notRecoveredAmount;

	/**
	 * 原因
	 */
	private String reason;

	/**
	 * 变更原因
	 */
	private String changeReason;

	/**
	 *合同期限结束时间
	 */
	private String deadlineEndTime;

	/**
	 *草稿状态 0正式 1草稿
	 */
	private String darftStatus;

	/**
	 *备注
	 */
	private String remarks;

	/**
	 *回款类型 1未回款 2部分回款 3已回款
	 */
	private String paybackType;

	/**
	 * 城市ID
	 */
	private Long cityId;

	/**
	 * 城市ID
	 */
	private List<Long> cityIds;

	/**
	 * 合同项目
	 */
	private List<GetContractProjectOutData> projectList;

	/**
	 * 附件列表
	 */
	private List<GetAnnexOutData> annexList;

	/**
	 * 终止附件列表
	 */
	private List<GetAnnexOutData> stopAnnexList;

	/**
	 * 变更附件列表
	 */
	private List<GetAnnexOutData> changeAnnexList;

	/**
	 * 审批历史列表
	 */
	private List<GetApproveOutData> approveList;

}
