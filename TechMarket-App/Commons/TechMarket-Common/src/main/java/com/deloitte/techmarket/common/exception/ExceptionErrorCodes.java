package com.deloitte.techmarket.common.exception;

/**
 * This ENUM class will be used to define all the error codes.
 *
 */
public enum ExceptionErrorCodes {

	CREATE_EXCEPTION("101" + "Exception while creating a user"),
	
	UPDATE_EXCEPTION("201" + "Exception while updating a user"),

	DELETE_EXCEPTION("301" + "Exception while deleting a user"),

	GET_EXCEPTION("401" + "Exception while getting a user"),

	SIGN_IN_EXCEPTION("100" + "Exception while signing-in a user");

	private String message;

	ExceptionErrorCodes(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
