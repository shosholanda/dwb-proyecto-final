package com.invoice.api.dto;

/*
 * Sprint 1 - Requerimiento 2
 * Agregar atributos de clase para la validación del producto
 * *CHECK*
 */
public class DtoProduct {


	/* Atributo de clase que representa el código gtin del producto */
	private String gtin;
	
	/* Atributo de clase que representa la cantidad en stock del producto */
	private Integer stock;
	
	/* Atributo de clase que representa el valor de status */
	
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
	
}
