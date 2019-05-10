package com.golte.contract.service.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAnnexOutData implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
     *类型 1合同附件 2商家附件 3商家评估附件 4合同终止附件
	 */
	private String type;

	/**
	 *关联ID
	 */
	private Long associationId;

	/**
	 *附件地址
	 */
	private String url;

	/**
	 *附件名称
	 */
	private String name;

}
