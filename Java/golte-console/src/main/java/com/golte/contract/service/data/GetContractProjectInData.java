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
public class GetContractProjectInData implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
     *主键ID
	 */
	private Long id;

	/**
	 *项目ID
	 */
	private Long projectId;

	/**
	 *点位ID
	 */
	private Long pointId;

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
	 *资源单位
	 */
	private String unitName;

	/**
	 *小计
	 */
	private BigDecimal subtotal;

	/**
	 *合同项目状态 0失效 1有效
	 */
	private String status;

	/**
	 * 项目资源列表
	 */
	private List<GetContractResourceInData> resourceList;

}
