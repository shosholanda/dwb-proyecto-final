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
import com.product.api.entity.Category;
import com.product.api.service.SvcCategory;
import com.product.exception.ApiException;

@RestController
@RequestMapping("/category")
public class CtrlCategory {
	
	/* Instancia de la interfaz para utilizar las consultas */
	@Autowired
	SvcCategory svc;
	
	/*
	 * Endpoint LIST. 
	 * No recibe nada, regresa una lista de categorias disponibles.
	 * @return una lista de todas las categorias.
	 */
	@GetMapping
	public ResponseEntity<List<Category>> getCategories(){
		return new ResponseEntity<>(svc.getCategories(), HttpStatus.OK);
	}
	
	/*
	 * Endpoint READ
	 * @param category_id el id de la categoria que se quiere consultar.
	 * @return la región que tenga el mismo id del parámetro.
	 */
	@GetMapping("/{category_id}")
	public ResponseEntity<Category> getCategory(@PathVariable int category_id) throws Exception{
		Category c = svc.getCategory(category_id);
		if (c == null)
			throw new ApiException("category does not exists", HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(c, HttpStatus.OK);
	}
	
	/*
	 * Endpoint CREATE
	 * @param category la nueva categoria a añadir en la bdd. La categoria debe ser diferente a todas las que se encuentren en la bdd
	 * @return un mensaje del estado de la operación
	 */
	@PostMapping
	public ResponseEntity<ApiResponse> createCategory(@Valid @RequestBody Category category, BindingResult bindingResult ) {
		if (bindingResult.hasErrors())
			throw new ApiException(bindingResult.getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(svc.createCategory(category), HttpStatus.OK);
	}
	
	/*
	 * Endpoint UPDATE
	 * @param category_id el id de la catgoría que queremos actualizar
	 * @param category el objeto por el cual vamos a reemplazar.
	 * @return un mensaje indicando el estado de la operación.
	 */
	@PutMapping("/{category_id}")
	public ResponseEntity<ApiResponse> updateCategory(@PathVariable int category_id, @Valid @RequestBody Category category, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new ApiException(bindingResult.getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(svc.updateCategory(category_id, category), HttpStatus.OK);
	}
	
	/*
	 * Endpoint DELETE (final)
	 * @param category_id el id de la categoria que desea ser eliminado.
	 * @return un mensaje con el estado de la operación
	 */
	@DeleteMapping("/{category_id}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable int category_id) {
		return new ResponseEntity<>(svc.deleteCategory(category_id), HttpStatus.OK);
	}
}
