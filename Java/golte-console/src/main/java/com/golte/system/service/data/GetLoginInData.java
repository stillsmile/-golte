package com.golte.system.service.data;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetLoginInData implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 账号
	 */
	private String loginAccount;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 旧密码
	 */
	private String passwordOld;

	/**
	 * 新密码
	 */
	private String passwordNew;
}
