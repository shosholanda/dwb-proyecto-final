package com.product.api.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.product.api.entity.Category;

/**
 * Interfaz de la entidad Category para hacer consultas a la BDD. (Repositorio)
 * @author Lenovo
 *
 */
@Repository
public interface RepoCategory extends JpaRepository<Category, Integer>{

	/*
	 * Método para obtener todos las categorías cuyo status sea 1 o 0.
	 */
	@Query(value = "SELECT * FROM category WHERE status = :status", nativeQuery = true)
	List<Category> findByStatus(@Param ("status") Integer status);
	
	/*
	 * Método para obtener el objeto category que coinicida con category
	 */
	@Query(value = "SELECT * FROM category WHERE category = :category", nativeQuery = true)
	Category findByCategory(@Param ("category") String category);
	
	/*
	 * Método para obtener el objeto category dado su category_id y que el status sea 1 para indicar que está disponible.
	 */
	@Query(value = "SELECT * FROM category WHERE category_id = :category_id AND status = 1", nativeQuery = true)
	Category findByCategoryId(@Param ("category_id") Integer category_id);
	
	/* Método para añadir una nueva category a la BDD */
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO category (category, status) VALUES (:category, 1)", nativeQuery = true)
	Integer createCategory(@Param ("category") String category);
	
	/* Método para actualizar el nombre de una category disponible. */
	@Transactional
	@Modifying
	@Query(value = "UPDATE category SET category = :category WHERE category_id = :category_id", nativeQuery = true)
	Integer updateCategory(@Param ("category") String category, @Param ("category_id") Integer category_id);
	
	/* Método para actualizar el status de 0 a 1 */
	@Transactional
	@Modifying
	@Query(value = "UPDATE category SET status = 1 WHERE category_id = :category_id", nativeQuery = true)
	Integer activateStatus(@Param ("category_id") Integer category_id);
	
	/* Método para "eliminar" una category disponible, haciendola no disponible. */
	@Transactional
	@Modifying
	@Query(value = "UPDATE category SET status = 0 WHERE category_id = :category_id", nativeQuery = true)
	Integer deleteCategory(@Param ("category_id") Integer category_id);
	
}
