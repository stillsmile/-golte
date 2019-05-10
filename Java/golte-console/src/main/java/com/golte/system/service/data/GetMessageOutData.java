package com.golte.system.service.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetMessageOutData implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 未读消息数目
	 */
	private String number;

	//getResByUser 首页左侧栏目树
	List<GetMenuOutData> top;

}
