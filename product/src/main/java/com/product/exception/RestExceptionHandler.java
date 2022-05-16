package com.product.exception;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Controlador global de excepciones APIProduct
 * @author Lenovo
 *
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ApiException.class)
	protected ResponseEntity<ExceptionResponse> handleApiException(ApiException error, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse();
		response.setTimestamp(LocalDateTime.now());
		response.setStatus(error.getStatus().value());
		response.setError(error.getStatus());
		response.setMessage(error.getMessage());
		response.setPath(((ServletWebRequest)request).getRequest().getRequestURI().toString());
		return new ResponseEntity<>(response, response.getError());
	}
}
