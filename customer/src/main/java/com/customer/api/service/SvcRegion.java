package com.customer.api.service;

import java.util.List;

import com.customer.api.dto.ApiResponse;
import com.customer.api.entity.Region;

/**
 * Interaz que va a gestionar el orden de las cosas, entre los controladores, repos etc.
 * Por ejemplo que primero va el endpoint y luego la consulta, y luego el resutlado de la bdd.
 * En teoría, después de crear nuestras entidades, se crea esta interfaz con los métodos que implementará el controlador.
 * Adicionalmente, implementaremos una clase con los mismos métodos en servicio para que se ejecute primero.
 * @author Lenovo
 *
 *Básicamente son los métodos que tenemos en el Controlador.
 */
public interface SvcRegion {
	
	/* Método del controlador para obtener todas las regiones disponibles*/
	List<Region> getRegions();
	
	/* Método para obtener una región por id */
	Region getRegion(Integer region_id);
	
	/* Método crear una región y ssaber el estado */
	/* Actualización: cambiar de String->ApiResponse para poder lanzar las excepciones.
	 * También cambiar la implementación del servicio, lanzando un apiexception cuando se requiera
	 * Y regresando un apiresponse cada vez que todo salga bien.*/
	ApiResponse createRegion(Region region);
	
	/* Método actualizar una región y saber el estado */
	ApiResponse updateRegion(Integer region_id, Region region);
	
	/* Método eliminar una región y saber el estado */
	ApiResponse deleteRegion(Integer region_id);
	
	/*
	 * Una vez definidos los tenemos que implementar en una clase llamada SVcRegionImp de implementacion.java
	 */
	
}
