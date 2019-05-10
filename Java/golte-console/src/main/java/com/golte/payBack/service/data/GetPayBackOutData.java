package com.golte.payBack.service.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPayBackOutData implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *主键ID
	 */
	private Long id;

	/**
	 *应收回款
	 */
	private BigDecimal receivablePayback;

	/**
	 *已回款金额
	 */
	private BigDecimal receivedPayback;

	/**
	 *计划回款时间
	 */
	private String paybackTime;

	/**
	 *备注
	 */
	private String remarks;

	/**
	 *是否有效
	 */
	private String status;

	/**
	 * 回款记录列表
	 */
	private List<GetRecordListOutData> recordList;

}
