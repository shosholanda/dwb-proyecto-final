package com.customer.api.dto;

/**
 * Clase para poder regresar al postman solamente la etiqueta de mensaje. En este paquete (DTO) se agrupan los
 * datos en un Json como se requiera. Como para el error solamente mostramos el error en formato JSon
 * entonces creamos un Json personalizado que transforme el ExceptionResponse a JSON.
 */

public class ApiResponse {

	/*Solamente se manda un mensaje.
	 * {
	 * 		"message":"adfasdfasdf error"
	 * }
	 */
	private String message;

	//Constructor generado
	public ApiResponse(String message) {
		super();
		this.message = message;
	}

	//Getters y setters como siempre.
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	/*
	 * Una vez implementado todo esto, es necesario actualizar las clases de controlador y servicio porque
	 * estas clases son las que pueden mandar excepciones. vamos al controlador una vez mas.
	 */
	
	
}
