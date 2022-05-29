package com.product.api.service;

import java.util.List;

import com.product.api.dto.ApiResponse;
import com.product.api.dto.DtoProductList;
import com.product.api.entity.Product;

/**
 * Interfaz para las operaciones CRUD en la api.
 * @author Lenovo
 *
 */
public interface SvcProduct {

	public List<Product> getProducts();
	public List<DtoProductList> listProducts(Integer caterogy_id);
	public Product getProduct(String gtin);
	public ApiResponse createProduct(Product product);
	public ApiResponse updateProduct(Integer product_id, Product product);
	public ApiResponse deleteProduct(Integer product_id);
	public ApiResponse updateProductCategory(Integer product_id, Integer category_id);
	public ApiResponse updateProductStock(String gtin, Integer stock);
}
