package com.product.exception;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private HttpStatus status;

	/**
	 * @param status
	 */
	public ApiException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}

	/**
	 * @return the status
	 */
	public HttpStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
	
}
