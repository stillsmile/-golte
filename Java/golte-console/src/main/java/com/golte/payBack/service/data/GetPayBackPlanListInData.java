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
public class GetPayBackPlanListInData implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 回款计划列表
	 */
	private List<GetPayBackPlanInData> payBackPlanList;

}
