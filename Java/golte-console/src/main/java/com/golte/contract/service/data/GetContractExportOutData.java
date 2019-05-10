package com.golte.contract.service.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetContractExportOutData{

	/**
	 *合同编号
	 */
	private String code;

	/**
	 *合同名称
	 */
	private String name;

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
	 *合同期限结束时间
	 */
	private String deadlineEndTime;

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
	 *草稿状态 0正式 1草稿
	 */
	private String darftStatus;

	/**
	 *回款类型 1未回款 2部分回款 3已回款
	 */
	private String paybackType;

}
