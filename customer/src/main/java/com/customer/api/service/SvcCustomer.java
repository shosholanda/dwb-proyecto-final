package com.customer.api.service;

import java.util.List;

import com.customer.api.dto.ApiResponse;
import com.customer.api.dto.DtoCustomerList;
import com.customer.api.entity.Customer;
import com.customer.api.entity.Region;

/**
 * Debemos implementar nuevos métodos para la tabla customer, como se especifica en el documento
 * Estos son independientes de los métodos para region y customerimg
 * @author Lenovo
 *
 */

public interface SvcCustomer {

	/* Obtiene la lista de todos los customers de acuerdo al formato JSON especificado ¨*/
	public List<DtoCustomerList> getCustomers();
	/* Obtiene un customer de la tabla utilizando el rfc*/
	public Customer getCustomer(String rfc);
	/* Las operaciones crud para customer */
	public ApiResponse createCustomer(Customer in);
	public ApiResponse updateCustomer(Customer in, Integer id);
	public ApiResponse deleteCustomer(Integer id);
	
	/* Operación adicional para poder actualizar /customer /{customer_id}/region*/
	public ApiResponse updateCustomerRegion(Region region, Integer id);
}
