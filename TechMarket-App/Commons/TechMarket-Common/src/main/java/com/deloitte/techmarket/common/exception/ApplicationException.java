package com.deloitte.techmarket.common.exception;

import com.deloitte.techmarket.common.CommonConstants;

/**
 * This class will be used to show error message incase of any exception.
 *
 */
public class ApplicationException extends Exception {

	private static final long serialVersionUID = 1L;
	public static final String CUSTOM_EXCEPTION_MESSAGE = "CustomException [errorMessage=";

	public ApplicationException(String errorMessage) {
		super(errorMessage);
	}

	@Override
	public String toString() {
		return CUSTOM_EXCEPTION_MESSAGE + getMessage() + CommonConstants.END_OF_MESSAGE;
	}

}
