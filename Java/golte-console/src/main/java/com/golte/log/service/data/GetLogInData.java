package com.golte.log.service.data;

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
public class GetLogInData implements Serializable {
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
	 *审批状态
	 */
	private String status;

	/**
	 * 页码
	 */
	private Integer pageNum;

	/**
	 * 每页条数
	 */
	private Integer pageSize;

}
