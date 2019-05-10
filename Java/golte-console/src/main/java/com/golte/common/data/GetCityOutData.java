package com.golte.common.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCityOutData implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 城市ID
	 */
	private List<Long> cityIds;

	/**
	 * 中心城市ID
	 */
	private List<Long> centralCityIds;

}
