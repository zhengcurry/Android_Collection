package com.heinqi.curry_base.http;

public class ErrorTO {
	// 字段名称
	private String fieldName;
	// 错误消息
	private String errorMsg;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
