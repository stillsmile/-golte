package com.golte.system.service.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetConfigInData implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 激励类型Id
	 */
	private String typeId;

	/**
	 * 激励类型Value
	 */
	private String typeValue;


	/**
	 * 激励类型
	 */
	private String type;

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

	//删除id集合
	private List<String> ids;

	//删除id对应的激励类型Id
	private List<Long> typeIds;

	//页大小
	private int pageSize;

	//页码
	private int pageNum;

}
