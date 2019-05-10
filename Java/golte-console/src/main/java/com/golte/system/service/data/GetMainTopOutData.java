package com.golte.system.service.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetMainTopOutData implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 资源名称
	 */
	private int sortNum;
	/**
	 * 资源名称
	 */
	private String name;

	/**
	 * 使用的数量
	 */
	private String value;

	/**
	 * 回款值
	 */
	private String payValue;

	/**
	 * 项目名称
	 */
	private String projectName;

	/**
	 * 合同名称
	 */
	private String contractName;

	/**
	 * 合同名称
	 */
	private String paybackTime;

	/**
	 * 收款人
	 */
	private String peceiptMember;

	/**
	 * 开始时间
	 */
	private String startTime;

	/**
	 * 结束时间
	 */
	private String endTime;




}
