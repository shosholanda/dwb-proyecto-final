package com.product.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.product.api.dto.ApiResponse;
import com.product.api.entity.Category;
import com.product.api.repository.RepoCategory;
import com.product.exception.ApiException;

@Service
public class SvcCategoryImp implements SvcCategory {

	/* Instancia de la interfaz para usar las consultas. */
	@Autowired
	RepoCategory repo;
	
	@Override
	public List<Category> getCategories() {
		// TODO Auto-generated method stub
		return repo.findByStatus(1);
	}

	@Override
	public Category getCategory(Integer category_id) {
		// TODO Auto-generated method stub
		Category c = repo.findByCategoryId(category_id);
		if (c == null)
			throw new ApiException("category does not exist!", HttpStatus.NOT_FOUND);
		return c;
	}

	@Override
	public ApiResponse createCategory(Category category) {
		// TODO Auto-generated method stub
		Category categorySaved = repo.findByCategory(category.getCategory());
		if (categorySaved == null) {
			repo.createCategory(category.getCategory());
			return new ApiResponse("category created");
		} else {
			if (categorySaved.getStatus() == 0) {
				repo.activateStatus(categorySaved.getCategory_id());
				return new ApiResponse("category has been activated");
			} else {
				throw new ApiException("category already exists", HttpStatus.BAD_REQUEST);
			}
		}
	}

	@Override
	public ApiResponse updateCategory(Integer category_id, Category category) {
		// TODO Auto-generated method stub
		Category categorySaved = repo.findByCategoryId(category_id);
		if (categorySaved == null) 
			throw new ApiException("category does not exists", HttpStatus.NOT_FOUND);
		if (categorySaved.getStatus() == 0) 
			throw new ApiException("category is not active", HttpStatus.BAD_REQUEST);
		categorySaved = repo.findByCategory(category.getCategory());
		if (categorySaved != null)
			throw new ApiException("category already exists", HttpStatus.BAD_REQUEST);
		repo.updateCategory(category.getCategory(), category_id);
		return new ApiResponse("category updated");
		
	}

	@Override
	public ApiResponse deleteCategory(Integer category_id) {
		// TODO Auto-generated method stub
		Category categorySaved = repo.findByCategoryId(category_id);
		if (categorySaved == null)
			throw new ApiException("category does not exist", HttpStatus.NOT_FOUND);
		if (categorySaved.getStatus() == 0)
			throw new ApiException("category cannot be removed if it has products", HttpStatus.BAD_REQUEST);
		repo.deleteCategory(category_id);
		return new ApiResponse("category removed");
	}
}
