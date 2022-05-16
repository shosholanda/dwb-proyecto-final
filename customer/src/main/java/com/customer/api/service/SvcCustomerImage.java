package com.customer.api.service;

import com.customer.api.dto.ApiResponse;
import com.customer.api.entity.CustomerImage;

/**
 * Interface que solo tiene un método por ahora, porque así se especifica en el endopoint
 * Solo actualiza una imagen.
 * @param c
 * @return
 */
public interface SvcCustomerImage {
	
	/**
	 * ¨Método para actualizar una imágen en el servidor utilizando un JSON.
	 * @param in el JSON (siempre para JSON de entrada) out para salida.
	 * @return una ApiResponse mostrando el estado del proceso.
	 * Ahora vamos al controlador a actualizar el método
	 */
	ApiResponse setCustomerImage(CustomerImage in);
}
