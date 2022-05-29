package com.product.api.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.product.api.entity.Product;

/**
 * Interfaz que representa las consultas en SQL. se implementan en el servicio
 * @author Lenovo
 *
 */
@Repository
public interface RepoProduct extends JpaRepository<Product, Integer>{
	
	@Query(value = "SELECT * FROM product WHERE status = :status", nativeQuery = true)
	List<Product> findByStatus(@Param("status") Integer status);
	
	@Query(value = "SELECT * FROM product WHERE category_id = :category_id AND status = :status", nativeQuery = true)
	List<Product> findProductsByStatus(@Param("category_id") Integer category_id, @Param("status") Integer status);
	
	@Query(value = "SELECT * FROM product WHERE product_id = :product_id", nativeQuery = true)
	Product findByProductId(@Param ("product_id") Integer product_id);
	
	@Query(value = "SELECT * FROM product WHERE product = :product", nativeQuery = true)
	Product findByProduct(@Param ("product") String product);
	
	@Transactional
	@Modifying
	@Query( value = "INSERT INTO product (product, status) VALUES (:product, 1)", nativeQuery = true)
	void createProduct(@Param ("product") String product);
	
	/* Método para actualizar el nombre de una category disponible. */
	@Transactional
	@Modifying
	@Query(value = "UPDATE category SET gtin = :gtin, "
									+ " product = :product, "
									+ " description = :description, "
									+ " price = :price, "
									+ " stock = :stock, "
									+ "WHERE product_id = :product_id", nativeQuery = true)
	Integer updateProduct(
			@Param("gtin") String gtin,
			@Param("product") String product,
			@Param("description") String description, 
			@Param("price") Double price, 
			@Param("stock") Integer stock, 
			@Param("product_id") Integer product_id);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE product SET status = 0 WHERE product_id = :product_id AND status = 1", nativeQuery = true)
	Integer deleteProduct(@Param ("product_id") Integer product_id);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE product SET status = 1 WHERE product_id = :product_id", nativeQuery = true)
	void activateProduct(@Param ("product_id") Integer product_id);
	
	/* Implementación para el método que busca código gtin + funciones de JPA */
	Product findByGtinAndStatus(@Param("gtin") String gtin, @Param("status") Integer status);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE product SET category_id = :category_id WHERE product_id = :product_id AND status = 1", nativeQuery = true)
	Integer updateProductCategory(@Param("category_id") Integer category_id, @Param ("product_id") Integer product_id);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE product SET stock = :stock WHERE gtin = :gtin AND status = 1", nativeQuery = true)
	Integer updateProductStock(@Param("gtin") String gtin, @Param("stock") Integer stock);
}
