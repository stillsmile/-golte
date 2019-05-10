package com.golte.system.service.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetMainInData implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 城市Id
	 */
	private Long cityId;

	/**
	 * 年份 yyyy
	 */
	private String year;

	/**
	 * 类型名称 yyyy
	 */
	private String typeName;


	/**
	 * 城市ID
	 */
	private List<Long> cityIds;

	/**
	 * 页码
	 */
	private Integer pageNum;

	/**
	 * 每页条数
	 */
	private Integer pageSize;

}
