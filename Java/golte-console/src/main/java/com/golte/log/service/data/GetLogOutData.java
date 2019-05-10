package com.golte.log.service.data;

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
public class GetLogOutData implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
     *主键ID
	 */
	private Long id;

	/**
	 *合同名称
	 */
	private String contractName;

	/**
	 *操作时间
	 */
	private String createTime;

	/**
	 *提交人
	 */
	private String createName;

	/**
	 *上级审批人ID
	 */
	private String supperEmpId;

	/**
	 *上级审批人
	 */
	private String approveName;

	/**
	 *审批操作人
	 */
	private String approvedName;

	/**
	 *操作类型 1终止合同 2变更合同
	 */
	private String type;

	/**
	 *审批状态 1审批中 2通过
	 */
	private String status;


	//变更详情

	/**
	 *商家联系人
	 */
	private String merchantContact;

	/**
	 *商家联系人(变更后)
	 */
	private String merchantContactUp;

	/**
	 *商家联系电话
	 */
	private String merchantContactPhone;

	/**
	 *商家联系电话(变更后)
	 */
	private String merchantContactPhoneUp;

	/**
	 *签约联系人
	 */
	private String signingContact;

	/**
	 *签约联系人(变更后)
	 */
	private String signingContactUp;

	/**
	 *签约联系电话
	 */
	private String signingContactPhone;

	/**
	 *签约联系电话(变更后)
	 */
	private String signingContactPhoneUp;

	/**
	 *签约日期
	 */
	private String signingTime;

	/**
	 *签约日期(变更后)
	 */
	private String signingTimeUp;

	/**
	 *合同期限开始时间
	 */
	private String deadlineStartTime;

	/**
	 *合同期限开始时间(变更后)
	 */
	private String deadlineStartTimeUp;

	/**
	 *合同期限结束时间
	 */
	private String deadlineEndTime;

	/**
	 *合同期限结束时间(变更后)
	 */
	private String deadlineEndTimeUp;

	/**
	 *合同总额
	 */
	private BigDecimal amount;

	/**
	 *合同总额(变更后)
	 */
	private BigDecimal amountUp;

	/**
	 *履约保证金
	 */
	private BigDecimal margin;

	/**
	 *履约保证金(变更后)
	 */
	private BigDecimal marginUp;

	/**
	 *签约类型 1新合同 2续签
	 */
	private String signingType;

	/**
	 *签约类型 1新合同 2续签(变更后)
	 */
	private String signingTypeUp;

	/**
	 *合同类别 1临时摆展
	 */
	private String contractType;

	/**
	 *合同类别 1临时摆展(变更后)
	 */
	private String contractTypeUp;

	/**
	 * 合同项目
	 */
	private List<GetContractProjectOutData> projectList;

	/**
	 * 合同项目(变更后)
	 */
	private List<GetContractProjectOutData> projectListUp;

}
