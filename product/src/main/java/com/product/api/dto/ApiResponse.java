package com.product.api.dto;

/**
 * Clase que nos modela el formato de respuesta para mandarlo en Postman
 * @author Lenovo
 *
 */

public class ApiResponse {

	private String message;

	/**
	 * @param message
	 */
	public ApiResponse(String message) {
		super();
		this.message = message;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
