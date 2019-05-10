package com.golte.system.service.data;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetLoginOutData implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	private Long empId;

	/**
	 * 登录账户
	 */
	private String loginAccount;

	/**
	 * 用户名
	 */
	private String loginName;

	/**
	 * 令牌
	 */
	private String token;

	/**
	 * 权限
	 */
	private List<String> resourceList;
}
