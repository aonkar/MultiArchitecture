package com.deloitte.techmarket.model;

/**
 * Generic response model for all requests
 *
 * @param <T>
 */

public class ResponseModel<T> {

	private boolean status;
	private T responseObject;
	private String message;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public T getResponseObject() {
		return responseObject;
	}

	public void setResponseObject(T responseObject) {
		this.responseObject = responseObject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ResponseModel [status=" + status + ", responseObject=" + responseObject + ", message=" + message + "]";
	}

	
}
