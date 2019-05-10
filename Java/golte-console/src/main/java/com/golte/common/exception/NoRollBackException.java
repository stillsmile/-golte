package com.golte.common.exception;

/**
 * 不需要回滚的自定义异常
 * 
 * @author yq
 * @date 2018/9/25
 */
public class NoRollBackException extends Exception {

	private static final long serialVersionUID = 1L;

	public NoRollBackException(String message) {
		super(message);
	}
}
