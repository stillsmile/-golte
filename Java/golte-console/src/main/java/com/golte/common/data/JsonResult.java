package com.golte.common.data;

import com.golte.common.util.UtilConst;

import java.io.Serializable;

/**
 * 
 * @author GXS
 * @date 2018年4月9日
 * @Description:json对象
 */
public class JsonResult implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String ERROR_MESSAGE = "服务器出现了一些小状况，正在修复中！";

	private Integer code;

	private Object data;

	private String message;

	public JsonResult(Integer code, Object data, String message) {
		this.data = data;
		this.message = message;
		this.code = code;
	}

	public static JsonResult success(Object data, String desc) {
		return new JsonResult(UtilConst.CODE_200, data, desc);
	}

	public static JsonResult fail(Integer code,String desc) {
		return new JsonResult(code, null, desc);
	}

	public static JsonResult error() {
		return new JsonResult(UtilConst.CODE_500, null, ERROR_MESSAGE);
	}



	public JsonResult() {

	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
