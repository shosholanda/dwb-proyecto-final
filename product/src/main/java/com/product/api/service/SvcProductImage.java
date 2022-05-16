package com.product.api.service;

import java.util.List;

import com.product.api.dto.ApiResponse;
import com.product.api.entity.ProductImage;

public interface SvcProductImage {

	/* Método para obtener todas las imagenes relacinadas a un producto */
	public List<ProductImage> getProductImages(Integer product_id);
	
	/* Crear imágenes */
	public ApiResponse createProductImage(ProductImage in);
	
	/* Eliminar imágenes */
	public ApiResponse deleteProductImage(Integer product_id);
}
