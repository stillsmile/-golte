package com.golte.system.service.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetMessagePushInData implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 人员Id
	 */
	private Long id;

	//信息状态 0未读 1已读
	private String mesStatus;

}
