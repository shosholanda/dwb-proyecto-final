package com.product.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.dto.ApiResponse;
import com.product.api.dto.DtoProductList;
import com.product.api.entity.Product;
import com.product.api.service.SvcProduct;
import com.product.exception.ApiException;

@RestController
@RequestMapping("/product")
public class CtrlProduct {
	
	@Autowired
	SvcProduct srv;
	
	
	/*
	 * LIST endpoint
	 */
	@GetMapping
	public ResponseEntity<List<Product>> getProducts(){
		return new ResponseEntity<>(srv.getProducts(), HttpStatus.OK);
	}
	
	/*
	 * List products by category
	 */
	@GetMapping("/category/{category_id}")
	public ResponseEntity<List<DtoProductList>> listProducts(@PathVariable Integer category_id){
		return new ResponseEntity<>(srv.listProducts(category_id), HttpStatus.OK);
	}
	
	/*
	 * READ endpoint
	 */
	@GetMapping("/{gtin}")
	public ResponseEntity<Product> getProduct(@PathVariable("gtin") String gtin) {
		return new ResponseEntity<>(srv.getProduct(gtin), HttpStatus.OK);
	}
	
	
	/*
	 * CREATE endpoint
	 */
	@PostMapping
	public ResponseEntity<ApiResponse> createProduct(@Valid @RequestBody Product product, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			throw new ApiException(bindingResult.getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(srv.createProduct(product), HttpStatus.OK);
	}
	
	/*
	 * UPDATE endpoint
	 */
	@PutMapping("/{product_id}")
	public ResponseEntity<ApiResponse> updateProduct(@PathVariable Integer product_id,@Valid @RequestBody Product product, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new ApiException(bindingResult.getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(srv.updateProduct(product_id, product), HttpStatus.OK);
	}
	
	/*
	 * UPDATE category para un product.
	 */
	@PutMapping("/{product_id}/category")
	public ResponseEntity<ApiResponse> updateProductCategory(@PathVariable Integer product_id, @RequestBody Integer category_id) {
		return new ResponseEntity<>(srv.updateProductCategory(product_id, category_id), HttpStatus.OK);
	}
	
	/*
	 * DELETE endpoint
	 */
	@DeleteMapping("/{product_id}")
	public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Integer product_id) {
		return new ResponseEntity<>(srv.deleteProduct(product_id), HttpStatus.OK);
	}
	
	/*
	 * Requerimiento 2.4. Actualizar el stock de productos.
	 * *CHECK*
	 */
	@PutMapping("/{gtin}")
	public ResponseEntity<ApiResponse> updateProductStock(@PathVariable String gtin, @RequestBody Integer stock){
		return new ResponseEntity<>(srv.updateProductStock(gtin, stock), HttpStatus.OK);
	}
}
