package com.hcl.bankingapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(BankException.class)
	public ResponseEntity<ErrorResponse> ecomorseExceptionHandler(BankException ex, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(ex.getMessage());

		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> globalExceptionHandler(Exception exception, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(exception.getMessage());

		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(UserNotFound.class)
	public ResponseEntity<ExceptionPojo> globalExceptionHandler(UserNotFound ex, WebRequest request) {

		ExceptionPojo user = new ExceptionPojo(ex.getMessage());

		return new ResponseEntity<ExceptionPojo>(user, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(EnterValidCredentials.class)
	public ResponseEntity<ExceptionPojo> globalExceptionHandler(EnterValidCredentials ex, WebRequest request) {

		ExceptionPojo user = new ExceptionPojo(ex.getMessage());

		return new ResponseEntity<ExceptionPojo>(user, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoTransactions.class)
	public ResponseEntity<ExceptionPojo> globalExceptionHandler(NoTransactions ex, WebRequest request) {

		ExceptionPojo user = new ExceptionPojo(ex.getMessage());

		return new ResponseEntity<ExceptionPojo>(user, HttpStatus.NOT_FOUND);
	}

}