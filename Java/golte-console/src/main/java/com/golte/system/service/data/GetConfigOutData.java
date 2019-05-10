package com.golte.system.service.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetConfigOutData implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 激励类型Id
	 */
	private String typeId;

	/**
	 * 激励类型
	 */
	private String type;

	/**
	 * 激励类型
	 */
	private String typeValue;

	/**
	 * 激励的角色Id
	 */
	private String roleNameId;

	/**
	 * 激励的角色
	 */
	private String roleName;

	/**
	 * 激励对应的百分比
	 */
	private String perCent;

}
