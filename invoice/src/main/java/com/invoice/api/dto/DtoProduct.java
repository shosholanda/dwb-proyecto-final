package com.invoice.api.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * Sprint 1 - Requerimiento 2
 * Agregar atributos de clase para la validación del producto
 */
@Entity
@Table(name = "product")
public class DtoProduct {

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
	
	/* Atributo de clase que representa la cantidad en stock del producto */
	@Column(name = "stock")
	@NotNull
	@Min(value = 1, message = "there must be at least 1 of the product in stock")
	@JsonProperty("stock")
	private Integer stock;
	
	/* Atributo de clase que representa el valor de status */
	@Column(name = "status")
	@Max(value = 1, message = "El valor debe ser 1 o 0")
	@Min(value = 0, message = "El valor debe ser 1 o 0")
	@JsonIgnore
	private Integer status;

	/**
	 * @return the product_id
	 */
	public Integer getProduct_id() {
		return product_id;
	}

	/**
	 * @param product_id the product_id to set
	 */
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	/**
	 * @return the gtin
	 */
	public String getGtin() {
		return gtin;
	}

	/**
	 * @param gtin the gtin to set
	 */
	public void setGtin(String gtin) {
		this.gtin = gtin;
	}

	/**
	 * @return the stock
	 */
	public Integer getStock() {
		return stock;
	}

	/**
	 * @param stock the stock to set
	 */
	public void setStock(Integer stock) {
		this.stock = stock;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
