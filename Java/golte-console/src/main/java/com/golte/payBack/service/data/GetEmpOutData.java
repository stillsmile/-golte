package com.golte.payBack.service.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetEmpOutData implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
     *人员ID
	 */
	private Long empId;

	/**
	 *人员名称
	 */
	private String empName;

}
