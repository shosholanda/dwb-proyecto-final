package com.customer.api.repository;
/*
 * Después de crear las clases entidad y controlador, que una es la tabla de la bdd y el otro 
 * la conexión con los endpoints, entonces podemos conectar a la bdd utilizando la api de java
 * JPA que nos devuelve las consultas a la bdd como objetos de java y poder manipularlos más facilmente.
 * Básicamente el repository se encarca de toda la conexión con la bdd.
 * En los repositorys se utiliza una interfaz, porque vamos a extender los métodos de la clase abstracta
 * de JPA para poder implementarlos en otra clase. Es decir, aquí solamente estarán las firmas de los métodos
 * que utilizaremos para hacer consultas a la bdd. Los nombres de los métodos son las consultas.
 */


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.customer.api.entity.Region;

/*
 * Interfaz que define las consultas a la bdd.
 * el @Repository es para indicar que esta interfaz es de tipo repo, así como @entity y @restcontroller
 * implementamos la clase JPA. Además, dentro de <> van 2 parámetros
 * 1. El tipo de dato al que se mapeará nuestra consulta (transformación de bdd a <T>)
 * 2. El tipo de dato que es la llave primaria de la bdd a consultar.
 */
@Repository
public interface RepoRegion extends JpaRepository<Region, Integer> {

	/**
	 * Consulta para obtener todas las regiones de la bdd en una lsita cuyo status cumpla el parámetro indicado.
	 * @Query la sentencia de SQL para obtener las regiones con status = 0/1. nativequery para decir que es SQL el lenguaje
	 * :param el valor a sustituir por el parametro para la consulta.
	 * @param("status") el nombre del parámetro que le pasamos y después el parámetro normal en java. 
	 */
	@Query(value = "SELECT * FROM region WHERE status = :status", nativeQuery = true)
	List<Region> findByStatus(@Param ("status") Integer status);
	
	/**
	 * Consulta para obtener todas las regiones filtradas por @param region
	 * El tipo de parámetro cambia por string con nombre region.
	 */
	@Query(value = "SELECT * FROM region WHERE region = :region", nativeQuery = true)
	Region findByRegion(@Param ("region") String region);
	
	/**
	 * Consulta para obtener la región por id y que el status sea 1 para indicar que está disponible.
	 */
	@Query(value = "SELECT * FROM region WHERE region_id = :region_id AND status = 1", nativeQuery = true)
	Region findByID(@Param ("region_id") Integer region_id);
	
	/**
	 * Añadir una nueva región a la bdd. No regresamos nada.
	 * Insertar en la tabla región, en las columnas region, status , los valores :region, 1 porque status = 1.
	 * Al insertar, esta actualizando por lo que se usan las otras 2 etiquetas
	 */
	@Modifying
	@Transactional
	@Query (value = "INSERT INTO region (region, status) VALUES( :region, 1)", nativeQuery = true)
	void createRegion(@Param ("region") String region);
	
	/**
	 * Actualizar el nombre de una región de la BDD.No regresamos nada
	 * Necesitaremos el nombre de la nueva región y el id de la región que se va a actualizar (por el endpoint)
	 * @Modyfing etiqueta para actualizar
	 * @Transactional otra etiqueta necesaria.
	 */
	@Modifying
	@Transactional
	@Query (value = "UPDATE region SET region = :region WHERE region_id = :region_id", nativeQuery = true)
	void updateRegion(@Param ("region_id") Integer region_id, @Param ("region") String region);
	
	/**
	 * Actualizar el status de una región dado su Id
	 */
	@Modifying
	@Transactional
	@Query (value = "UPDATE region SET status = 1 WHERE region_id = :region_id", nativeQuery = true)
	void activateRegion(@Param ("region_id") Integer region_id);
	
	/**
	 * Consulta para eliminar una región dado su id. Eliminar significa desactivar su status para no borrarla para siempre.
	 * Esta vez no regresa nada porque se borra para siempre
	 */
	@Modifying
	@Transactional
	@Query (value = "UPDATE region SET status = 0 WHERE region_id = :region_id", nativeQuery = true)
	void deleteByID(@Param ("region_id") Integer region_id);
	
	/*
	 * * Después de este sigue la interfaz servicio.
 */
}

