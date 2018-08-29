package com.deloitte.techmarket.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.deloitte.techmarket.common.exception.ApplicationException;
import com.deloitte.techmarket.model.ResponseModel;

/**
 * Exception class to handle all the exceptions.
 *
 */
@ControllerAdvice
public class SpringbootExceptionHandler {

	public static final String MESSAGE_EXCEPTION = "Exception in the flow";
	
	/**
	 * Handles all the exceptions thrown of type ApplicationException
	 * 
	 * @param exception
	 */
	@ExceptionHandler(ApplicationException.class)
	public ResponseEntity<ResponseModel<ApplicationException>> exceptionHandler(ApplicationException exception) {
		boolean status = false;
		ResponseModel<ApplicationException> responseJson = new ResponseModel<>();
		responseJson.setStatus(status);
		responseJson.setMessage(MESSAGE_EXCEPTION);
		responseJson.setResponseObject(exception);
		return new ResponseEntity<>(responseJson, HttpStatus.ACCEPTED);
	}

	@ExceptionHandler(Exception.class)
	public void exceptionTypeHandler() {
		// Any generic exception code
	}

}
