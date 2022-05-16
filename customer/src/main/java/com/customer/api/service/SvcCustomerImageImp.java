package com.customer.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.customer.api.dto.ApiResponse;
import com.customer.api.entity.CustomerImage;
import com.customer.api.repository.RepoCustomerImage;
import com.customer.exception.ApiException;
/**
 * Clase que implementa cómo vamos a revisar nuestra consulta. En este caso solamente implementamos el 
 * metodo que actualiza una imagen.
 */

@Service
public class SvcCustomerImageImp implements SvcCustomerImage {

	@Autowired
	RepoCustomerImage repo;
	
	@Override
	public ApiResponse setCustomerImage(CustomerImage in) {
		Integer updated = repo.setCustomerImage(in.getCustomer_image(), in.getCustomer_image_id());
		if (updated == 1)
			return new ApiResponse("customer image updated");
		else
			throw new ApiException(HttpStatus.NOT_FOUND, "customer image cannot be updated");
	}

}
