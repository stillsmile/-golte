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
public class GetContractInData implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *主键ID
	 */
	private Long id;

	/**
	 *点位ID
	 */
	private Long pointId;

	/**
	 *项目ID
	 */
	private Long projectId;

	/**
	 *商家编号
	 */
	private String merchantCode;

	/**
	 *商家名称
	 */
	private String merchantName;

	/**
	 *资源编号
	 */
	private String resourceCode;

	/**
	 *资源名称
	 */
	private String resourceName;

	/**
	 *项目名称
	 */
	private String projectName;

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
	private Date signingTime;

	/**
	 *合同期限开始时间
	 */
	private Date deadlineStartTime;

	/**
	 *合同生效开始时间
	 */
	private String deadlineStartTimeStr;
	/**
	 *合同生效结束时间
	 */
	private String deadlineStartTimeEnd;

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
	 *审批状态 1进行中 2通过 3未通过 4变更审批中
	 */
	private String approvalStatus;

	/**
	 *终止状态 0未终止 1已终止
	 */
	private String terminationStatus;

	/**
	 *期限状态 0未到期 1已到期
	 */
	private String deadlineStatus;

	/**
	 *过审时间
	 */
	private Date approvalTime;

	/**
	 *合同期限结束时间
	 */
	private Date deadlineEndTime;

	/**
	 *合同结束开始时间
	 */
	private String deadlineEndTimeStr;
	/**
	 *合同结束结束时间
	 */
	private String deadlineEndTimeEnd;

	/**
	 *草稿状态 0正式 1草稿
	 */
	private String darftStatus;

	/**
	 *备注
	 */
	private String remarks;

	/**
	 *flag 1查看 2变更
	 */
	private String flag;

	/**
	 *回款类型 1未回款 2部分回款 3已回款
	 */
	private String paybackType;

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
	 * 中心城市ID
	 */
	private List<Long> centerCityIds;

	/**
	 * 合同项目
	 */
	private List<GetContractProjectInData> projectList;

	/**
	 * 附件列表
	 */
	private List<GetAnnexInData> annexList;

	/**
	 * 未回款金额
	 */
	private BigDecimal notRecoveredAmount;

	/**
	 * 原因
	 */
	private String reason;

    /**
     * 审批意见
     */
    private String opinion;

	/**
	 * 终止日期
	 */
	private Date terminationTime;

	/**
	 *操作人
	 */
	private String loginName;

	/**
	 *操作人ID
	 */
	private Long empId;

	/**
	 *token
	 */
	private String token;

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
