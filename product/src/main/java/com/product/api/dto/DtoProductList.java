package com.product.api.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "product")
public class DtoProductList { 
	/**
	 * Clase que modela una entidad para mostrar solamente los datos para el product list
	 * @author Lenovo
	 *
	 */
	
	
	/* Atributo de clase que representa el id del producto */
	@Id
	@Column(name = "product_id")
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@JsonProperty("product_id")
	private Integer product_id;
	
	/* Atributo de clase que representa el código gtin del producto */
	@Column(name = "gtin")
	@NotNull(message = "gtin is required")
	@JsonProperty("gtin")
	private String gtin;
	
	/* Atributo de clase que representa el nombre del producto */
	@Column(name = "product")
	@NotNull(message = "product is required")
	@JsonProperty("product")
	private String product;
	
	/* Atributo de clase que representa el precio del producto */
	@Column(name = "price")
	@Min(value = 0, message = "price must be positive")
	@NotNull(message = "price is required")
	@JsonProperty("price")
	private Double price;

}
