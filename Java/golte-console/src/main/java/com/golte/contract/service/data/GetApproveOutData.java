package com.golte.contract.service.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetApproveOutData implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
     *操作人
	 */
	private String name;

	/**
	 *操作时间
	 */
	private String time;

	/**
	 *操作
	 */
	private String type;

	/**
	 *备注
	 */
	private String remark;

}
