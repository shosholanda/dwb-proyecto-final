package com.customer.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Todas las excepciones en Spring tienen un formato json que da información acerca del error.
 * Tiene algunos atributos como timestamp, status, error, path etc en formato JSON.
 * 
 * Esta clase es la que le va a dar un formato personalizado a las excepciones que se generen durante
 * la ejecución de la api. es decir los errores van a caer aquí y vamos a seleccionar solo algunas cosas
 * para mostrar el error como se especifica en los endpoints.
 * 
 * Lo primero que vamos a hacer es definir una variable para cada atributo del JSON cuando hay una 
 * excepcion en Spring
 * @author Lenovo
 *
 */

public class ExceptionResponse {
	
	/* atributos que contiene el JSON generamos un constructor vacío y sus getter y setters
	 * @JsonFormat para cambiar la forma en la que se representa el dato, necesita 2 parámetros
	 * 1. pattern: cómo mostrar el tipo de dato (formato)
	 * 2. shape: al tipo de dato que queremos formatear nuestro objeto. 
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime timestamp;
	private Integer status;
	private HttpStatus error;
	private String message;
	private String path;
	
	public ExceptionResponse () {
		
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public HttpStatus getError() {
		return error;
	}

	public void setError(HttpStatus error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	/*
	 * Si implementamos desde una clase Throwable como extendemos la clase no tendríamos solamente nuestros atributos
	 * que definimos en esta clase. Por lo que hay que hacer una clase intermedia "vacía" que extianda a Throwable y 
	 * que nos intercepte la exception para que nos pueda devolver un objeto de esta clase con nuestros atributos definidos
	 * 
	 * Creamos la clase APIexception en el mismo paquete para poder interceptar las excepcioens.
	 */
	
	
}
