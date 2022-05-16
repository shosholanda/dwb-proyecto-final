package com.customer.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.api.dto.ApiResponse;
import com.customer.api.dto.DtoCustomerList;
import com.customer.api.entity.Customer;
import com.customer.api.entity.Region;
import com.customer.api.service.SvcCustomer;
import com.customer.exception.ApiException;

/**
 * Clase para poder definir en qué endpoints se van a utilizar los métodos que queremos para la clase customer.
 * de acuerdo al formato de endpoints
 * @author Lenovo
 *
 */

@RestController
@RequestMapping("/customer")
public class CtrCustomer {

	@Autowired
	SvcCustomer svc;
	
	@GetMapping
	public ResponseEntity<List<DtoCustomerList>> getCustomers(){
		return new ResponseEntity<>(svc.getCustomers(), HttpStatus.OK);
	}
	
	@GetMapping("/{rfc}")
	public ResponseEntity<Customer> getCustomer(@PathVariable("rfc") String rfc){
		return new ResponseEntity<>(svc.getCustomer(rfc), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse> createCustomer(@Valid @RequestBody Customer in, BindingResult bindingresult){
		if (bindingresult.hasErrors()) {
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingresult.getAllErrors().get(0).getDefaultMessage());
		}
		return new ResponseEntity<>(svc.createCustomer(in), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> updateCustomer(@Valid @RequestBody Customer in, @PathVariable("id") Integer id, BindingResult bindingResult){
		if (bindingResult.hasErrors()) {
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		}
		return new ResponseEntity<>(svc.updateCustomer(in, id), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteCustomer(@PathVariable("id") Integer id){
		return new ResponseEntity<>(svc.deleteCustomer(id), HttpStatus.OK);
	}
	
	@PutMapping("/{id}/region")
	public ResponseEntity<ApiResponse> updateCustomerRegion(@RequestBody Region region, @PathVariable("id") Integer id){
		return new ResponseEntity<>(svc.updateCustomerRegion(region, id), HttpStatus.OK);
	}
}
