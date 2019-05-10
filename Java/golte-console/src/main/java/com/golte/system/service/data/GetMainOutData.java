package com.golte.system.service.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetMainOutData implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 年度指标
	 */
	private BigDecimal yearTarget;

	/**
	 * 签约金额
	 */
	private BigDecimal amount;

	/**
	 * 签约金额
	 */
	private BigDecimal rate;

}
