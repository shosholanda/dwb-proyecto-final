package com.customer.api.service;

/**
 * Trucazo, crear nueva interfaz > add > svcRegion y automáticamente tendremos todos los métodos a implementar.
 * Indicar que es una clase de service con el @Service
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.customer.api.dto.ApiResponse;
import com.customer.api.entity.Region;
import com.customer.api.repository.RepoRegion;
import com.customer.exception.ApiException;

@Service
public class SvcRegionImp implements SvcRegion {

	/*
	 * Lo primero que tenemos que hacer es crear una instancia de nuestro repositorio (interfaz)
	 * Para no crear un objeto de la clase usamos la etiqueta autowired para que cree todo lo que 
	 * necesite para que el objeto pueda funcionar con todos los métodos que define.
	 */
	@Autowired
	RepoRegion repo;
	
	/* ¿Cómo hacer que el controlador tenga la consulta?
	 * usamos las queries del objeto repo y los regresamos.
	 */
	@Override
	public List<Region> getRegions() {
		return repo.findByStatus(1);
	}
	
	/*
	 * Método para obtener la región por id.
	 * No validamos nada porque solo hacemos una consulta.
	 */
	@Override
	public Region getRegion(Integer region_id) {
		//Si no existe la region, lanzar una exception.
		Region region = repo.findByID(region_id);
		if (region == null)
			throw new ApiException(HttpStatus.BAD_REQUEST, "region does not exist");
		return region;
	}

	/*
	 * Método para poder crear una region usando al servicio.
	 * Debemos validar aquí si existe la region en la bdd o no y 
	 * dependiendo de eso damos una respuesta.
	 * Ver si Region regionsaved no es null. Hacer un cast para que no ande molestando el IDE
	 */
	@Override
	public ApiResponse createRegion(Region region) {
		Region regionSaved = (Region) repo.findByRegion(region.getRegion());
		//Si no es null, entonces existe ´pero puede tener valor de 0 o 1. si es 1 decimos que ya existe  
		//(especificación del documento), si no activamos el status. (Cosas de implementación).
		//por lo que utilizamos la query para cambiar el status.
		if (regionSaved != null) {
			if (regionSaved.getStatus() == 0) {
				repo.activateRegion(regionSaved.getRegion_id());
				return new ApiResponse("region has been activated");
			} else
				throw new ApiException(HttpStatus.BAD_REQUEST, "region already exists!");
		} else {
			//Si es null no existe y creamos una nueva region.
			repo.createRegion(region.getRegion());	
			return new ApiResponse("region created");
		}
	}

	/*
	 * Método para actualizar una región
	 * Primero hay que validar que exista, si no mandamos error
	 * Solo podemos actualizar cuando la región está activa.
	 * Además, no podemos repetir regiones, por lo que hay que ver que no exista el nombre de la región.
	 * No es óptimo porque hacemos varias consultas en una sola.
	 */
	@Override
	public ApiResponse updateRegion(Integer region_id, Region region) {
		Region regionSaved = repo.findByID(region_id);
		if (regionSaved != null) {
			if (regionSaved.getStatus() == 0) 
				return new ApiResponse("region is not active");
			else {
				regionSaved = (Region) repo.findByRegion(region.getRegion());
				if (regionSaved == null) {
					repo.updateRegion(region_id, region.getRegion());
					return new ApiResponse("region updated");
				} else
					return new ApiResponse("region already exists");
			}
		} else 
			return new ApiResponse("región does not exist");
	}

	/*
	 * Método para eliminar una region (camibar su status)
	 */
	@Override
	public ApiResponse deleteRegion(Integer region_id) {
		Region regionSaved = (Region) repo.findByID(region_id);
		if (regionSaved == null)
			return new ApiResponse("region does not exists, cannot be removed if it has clients");
		repo.deleteByID(region_id);
		return new ApiResponse("region removed");
	}
	
	/*
	 * Una vez implementados los métodos debemos probarlos con postman y ver que funcione como debería.
	 * La siguiente parte son el manejo de excepciones
	 * https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc
	 * 
	 * Creamos una nueva clase en el paquete exception llamada ExceptionResponse.
	 */

}
