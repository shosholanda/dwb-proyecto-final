package com.customer.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.api.dto.ApiResponse;
import com.customer.api.entity.CustomerImage;
import com.customer.api.service.SvcCustomerImage;
import com.customer.exception.ApiException;

/**
 * Clase que nos da la comunicación entre el servidor y el repo o bdd
 * @author Lenovo
 *
 */
@RestController
@RequestMapping("/customer-image")
public class CtrlCustomerImage {

	@Autowired
	SvcCustomerImage svc; 
	
	@PutMapping
	/*
	 * similar al de ctrlregion pero ahora utilizaremos el servicio que acabamos de crear de customer image.
	 * Después de este vamos a implementar el repo o la query. en una nueva clase.
	 */
	public ResponseEntity<ApiResponse> setCustomerImage(@RequestBody CustomerImage in, BindingResult bindingResult){
		if (bindingResult.hasErrors()) {
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		}
		return new ResponseEntity<>(svc.setCustomerImage(in), HttpStatus.OK);
	}
}
