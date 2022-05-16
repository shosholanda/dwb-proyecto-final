package com.customer.exception;

import org.springframework.http.HttpStatus;

/**
 * Resumen: para la api: try { ... } catch (new ApiException(Httpstatus, message) {... } 
 * Clase que está entre la clase throwable y nuestra clase exception response, por lo que 
 * cada excepción que se genere en lugar de ir a throwable como eException o Runtimeexception 
 * la vamos a lanzar en esta clase, es decir vamos a crear excepciones con new ApliException pero 
 * el resultado mostrado en postman va a ser un new ExceptionREsponse.
 * 
 * Extendemos de la interfaz RunTimeException porque nuestros "errores" salen en tiempo de ejecución.
 * @author Lenovo
 *
 * Esta clase también nos ayuda a que, cada vez que lancemos una excepción de timpo ExceptionResponse nos 
 * ahorremos definir todos los atributos de la clase sino solo algunos de ellos, pues ya existe esa información.
 * dentro de la excepcion. Solo necesitamos el status para saber qué mensaje regresar.
 */

public class ApiException extends RuntimeException {

	/**
	 * Como runtimeexception es serializable, debemos de darle un id al error.
	 * Solo para que no ande molestando eclipse.
	 */
	private static final long serialVersionUID = 1L;
	
	/* Atributo para saber qué tipo de error es: */
	private HttpStatus status;
	
	/*
	 * Constructor para definir el status. y un mensaje para ya construirlo de una vez.
	 */
	public ApiException (HttpStatus status, String message) {
		//asignamos message como atributo del objeto runtimeexception.
		super(message);
		this.status = status; 
	}

	//Añadir los getters y setters.
	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
	/*
	 * Ahora solo falta cachar estos objetos en otra clase llamada RestExceptionHandler.
	 */
}
