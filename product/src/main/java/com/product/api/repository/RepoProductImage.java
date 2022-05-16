package com.product.api.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.product.api.entity.ProductImage;

@Repository
public interface RepoProductImage extends JpaRepository<ProductImage, Integer>{

	/* Seleccionar todas las imágenes que estén asociadas a un solo producto */
	@Query(value = "SELECT * FROM product_image WHERE product_id = :product_id AND status = 1", nativeQuery = true)
	List<ProductImage> findByProductId(@Param ("product_id") Integer product_id);
	
	//Para el create utilizamos el método save de JPA.
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE product_image SET status = 0 WHERE product_image_id = :product_image_id AND status = 1", nativeQuery = true)
	Integer deleteProductImage(@Param("product_image_id") Integer product_image_id);
}
