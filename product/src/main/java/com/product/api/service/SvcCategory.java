package com.product.api.service;

import java.util.List;

import com.product.api.dto.ApiResponse;
import com.product.api.entity.Category;
/**
 * Interfaz para los métodos del controlador
 * @author Lenovo
 *
 */
public interface SvcCategory {

	/* Método del controlador para obtener todas las cateogias disponibles*/
	List<Category> getCategories();
	
	/* Método para obtener una categoría por id */
	Category getCategory(Integer category_id);
	
	/* Método crear una category y saber el estado */
	ApiResponse createCategory(Category category);
	
	/* Método actualizar una category y saber el estado */
	ApiResponse updateCategory(Integer category_id, Category category);
	
	/* Método eliminar una category y saber el estado */
	ApiResponse deleteCategory(Integer category_id);
}
