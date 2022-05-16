package com.customer.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.customer.api.dto.DtoCustomerList;

/**
 * Interfaz para la entidad DToCusomterList
 * Es necesario porque como extendemos a JPA debemos mapear una entidad exactamente con los datos que se requieren
 * Como tenemos dos entidades que apuntan a la misma tabla, pero una tiene menos datos que la otra, entonces
 * nos mandaría un error porque se debe llenar todos los datos de la entidad. 
 * 
 * Esta interfaz es solamente para mapear la entidad DtoCustomerList.
 * @author Lenovo
 *
 */

@Repository
public interface RepoCustomerList extends JpaRepository<DtoCustomerList, Integer>{

	@Query(value = "SELECT * FROM customer WHERE status = :status", nativeQuery = true)
	List<DtoCustomerList> getCustomers(@Param ("status") Integer status);
	
	/*
	 * método equivalemte utilizando las funcionalidades de JPA
	 */
	List<DtoCustomerList> findByStatus(Integer id);
}
