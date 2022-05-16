package com.customer.exception;


import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Como es la clase que va a cachar todos los errores de la api, debe tener la etiqueta @ControllerAdvice
 * Además, como transforma el error ApiException a un formato Json ExceptionResponse. debe extender a ResponseEntityExceptionHandler
 */

@ControllerAdvice 
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	/*
	 * Para poder interceptar excepciones debemos utilizar ExceptionHandler y en sus parámetros
	 * indicar la clase de la que interceptará excepciones.
	 * protectes porque es medio publico y privado.
	 * Regresa un ResponseEntity de tipo <ExceptionResponse> porque es la clase donde tenemos el formato.
	 * Recibe como parámetros
	 * 1.  El objeto que se está lanzando como excepcion (en este caso como es de apiexception, usamos una referencia)
	 * 2. Webrequest que nos permite tener acceso a los metadatos de la solicitudes.
	 */
	@ExceptionHandler(ApiException.class)
	protected ResponseEntity<ExceptionResponse> handleApiException(ApiException apiException, WebRequest request){
		//Creamos un objeto que vamos a regresar, ie un objeto de nuestra clase formato.
		ExceptionResponse response = new ExceptionResponse();
		//le asignamos los parámetros necesarios:
		response.setTimestamp(LocalDateTime.now());
		//Este status es el código de status de HttpStatus.
		response.setStatus(apiException.getStatus().value());
		response.setError(apiException.getStatus());
		//obtener el error de mensaje.
		response.setMessage(apiException.getMessage());
		//Literalmente copypaste porque esta bien pinche largo y no tiene sentido.
		response.setPath(((ServletWebRequest)request).getRequest().getRequestURI().toString());
		
		//Finalmente devolvemos nuestra respuesta json con su error generado:
		return new ResponseEntity<>(response, response.getError());
	}
	
	/*
	 * Una vez terminado de implementar el manejador global de la api, debemos de crear una clase en dto llamado apiresponse
	 */
}
