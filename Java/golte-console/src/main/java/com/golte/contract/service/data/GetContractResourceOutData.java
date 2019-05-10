package com.golte.contract.service.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetContractResourceOutData implements Serializable {
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
	 *合同项目ID
	 */
	private Long contractProjectId;

	/**
	 *资源ID
	 */
	private Long resourceId;

	/**
	 *资源名称
	 */
	private String resourceName;

}
