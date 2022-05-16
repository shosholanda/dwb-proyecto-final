package com.product.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Clase que modela una entidad tabla en la api
 * @author Lenovo
 *
 */
@Entity
@Table(name = "product")
public class Product {
	
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
	
	/* Atributo de clase que representa la descripción del producto*/
	@Column(name = "description")
	@JsonProperty("description")
	private String description;
	
	/* Atributo de clase que representa el precio del producto */
	@Column(name = "price")
	@Min(value = 0, message = "price must be positive")
	@NotNull(message = "price is required")
	private Double price;
	
	/* Atributo de clase que representa la cantidad en stock del producto */
	@Column(name = "stock")
	@NotNull
	@Min(value = 1, message = "there must be at least 1 of the product in stock")
	@JsonProperty("stock")
	private Integer stock;
	
	/* Atributo de clase que representa la referencia a la categoria asociada ¨*/
	@Column(name = "category_id")
	@NotNull(message = "category_id is required")
	@JsonProperty("category_id")
	private Integer category_id;
	
	/* Atributo de clase que representa el valor de status */
	@Column(name = "status")
	@Max(value = 1, message = "El valor debe ser 1 o 0")
	@Min(value = 0, message = "El valor debe ser 1 o 0")
	@JsonIgnore
	private Integer status;
	
	/* Atributo de clase que representa el objeto de categoria. Se asigna después con su category_id */
	@Transient
	@JsonProperty("category")
	private Category category;

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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
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
	 * @return the category_id
	 */
	public Integer getCategory_id() {
		return category_id;
	}

	/**
	 * @param category_id the category_id to set
	 */
	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}

	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

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
	 * @return the product
	 */
	public String getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(String product) {
		this.product = product;
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

	@Override
	public String toString() {
		return "Product [product_id=" + product_id + ", gtin=" + gtin + ", product=" + product + ", description="
				+ description + ", price=" + price + ", stock=" + stock + ", category_id=" + category_id + ", status="
				+ status + ", category=" + category + "]";
	}
	
	
}
