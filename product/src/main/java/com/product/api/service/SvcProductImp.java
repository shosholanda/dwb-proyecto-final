package com.product.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.product.api.dto.ApiResponse;
import com.product.api.dto.DtoProductList;
import com.product.api.entity.Category;
import com.product.api.entity.Product;
import com.product.api.repository.RepoCategory;
import com.product.api.repository.RepoProduct;
import com.product.api.repository.RepoProductList;
import com.product.exception.ApiException;

@Service
public class SvcProductImp implements SvcProduct {

	@Autowired
	RepoProduct repo;
	
	@Autowired
	RepoProductList repoList;
	
	@Autowired
	RepoCategory repoCategory;
	
	@Override
	public List<Product> getProducts() {
		return repo.findByStatus(1);
	}

	@Override
	public Product getProduct(String gtin){
		Product product = repo.findByGtinAndStatus(gtin, 1);
		if (product == null)
			throw new ApiException("No se encontró el producto.", HttpStatus.BAD_REQUEST);
		product.setCategory(repoCategory.findByCategoryId(product.getCategory_id()));
		return product;
	}

	@Override
	public ApiResponse createProduct(Product in) {
		Product p = repo.findByGtinAndStatus(in.getGtin(), 0);
		if (p != null) {
			updateProduct(p.getProduct_id(), in);
			return new ApiResponse("El producto se activó");
		} else {
			try {
				in.setStatus(1);
				repo.save(in);
			} catch (DataIntegrityViolationException e) {
				if (e.getLocalizedMessage().contains("gtin"))
					throw new ApiException( "El producto gtin ya existe!", HttpStatus.BAD_REQUEST);
				if (e.getLocalizedMessage().contains("product"))
					throw new ApiException( "El producto ya existe!", HttpStatus.BAD_REQUEST);
			} catch (Exception e) {
				throw new ApiException("Algo salió mal, revisa de nuevo tu peticion :(\n" + e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
			}
			return new ApiResponse("Producto creado.");
		}
		
	}

	@Override
	public ApiResponse updateProduct(Integer product_id, Product in) {
		try {
			repo.updateProduct(in.getGtin(), in.getProduct(), in.getDescription(), in.getPrice(), in.getStock(), product_id);
		} catch (DataIntegrityViolationException e) {
			if (e.getLocalizedMessage().contains("gtin"))
				throw new ApiException( "El producto gtin ya existe!", HttpStatus.BAD_REQUEST);
			if (e.getLocalizedMessage().contains("product"))
				throw new ApiException( "El producto ya existe!", HttpStatus.BAD_REQUEST);
		}
		return new ApiResponse("Producto actualizado!.");
	}

	@Override
	public ApiResponse deleteProduct(Integer product_id) {
		if (repo.deleteProduct(product_id) > 0)
			return new ApiResponse("Producto eliminado!");
		else
			throw new ApiException("El producto no se pudo eliminar", HttpStatus.BAD_REQUEST);
	}

	@Override
	public List<DtoProductList> listProducts(Integer category_id) {
		return repoList.getProductsByCategoryId(category_id);
	}

	@Override
	public ApiResponse updateProductCategory(Integer product_id, Integer category_id) {
		Category c = repoCategory.findByCategoryId(category_id);
		if (c == null) {
			throw new ApiException("La categoría no se encuentra disponible :/", HttpStatus.NOT_FOUND);
		}
		Product p = repo.findByProductId(product_id);
		if (p == null || p.getStatus() == 0) {
			throw new ApiException("No se pudo actualizar la categoría del producto", HttpStatus.BAD_REQUEST);
		}
		try {
			repo.updateProductCategory(category_id, product_id);
		} catch (Exception e) {
			throw new ApiException("Algo salió mal :( más info:\n"+  e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ApiResponse("La categoría del producto fue actualizada!");
	}

}
