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
public class GetPayBackPlanInData implements Serializable {
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
	 *合同负责人
	 */
	private String contractPerson;

	/**
	 *商家ID
	 */
	private Long merchantId;

	/**
	 *商家名
	 */
	private String merchantName;

	/**
	 *合同项目ID
	 */
	private Long contractProjectId;

	/**
	 *应收回款
	 */
	private BigDecimal receivablePayback;

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
	private Date extensionTime;

	/**
	 *计划回款时间
	 */
	private Date paybackTime;

	/**
	 *计划回款开始时间
	 */
	private String paybackStartTime;

	/**
	 *计划回款结束时间
	 */
	private String paybackEndTime;

	/**
	 *汇款日期
	 */
	private Date remittanceTime;

	/**
	 *备注
	 */
	private String remarks;

	/**
	 * 中心城市ID
	 */
	private Long centralCityId;

	/**
     * 城市ID
	 */
	private Long cityId;

	/**
	 * 城市ID
	 */
	private List<Long> cityIds;


	/**
	 *操作人
	 */
	private String loginName;

	/**
	 *操作人ID
	 */
	private Long empId;

	/**
	 * 页码
	 */
	private Integer pageNum;

	/**
	 * 每页条数
	 */
	private Integer pageSize;

	/**
	 * 删除id集合
	 */
	private List<Long> ids;

}
