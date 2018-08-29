package com.deloitte.techmarket.common.exception;

import com.deloitte.techmarket.common.CommonConstants;

public class ErrorResponseJson {

	public static final String ERROR_CODE = "ErrorResponseJson [errorCode=";
	public static final String ERROR_MESSAGE = ", errorMsg=";
	
	private int errorCode;
	private String errorMsg;

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	@Override
	public String toString() {
		return ERROR_CODE + errorCode + ERROR_MESSAGE + errorMsg + CommonConstants.END_OF_MESSAGE;
	}

	
}
