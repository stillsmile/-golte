package com.golte.contract.service.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCityOutData implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
     *城市ID
	 */
	private Long cityId;

	/**
	 *城市名称
	 */
	private String cityName;

}
