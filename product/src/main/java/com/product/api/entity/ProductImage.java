package com.product.api.entity;

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

@Entity
@Table(name = "product_image")
public class ProductImage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("product_image_id")
	@Column(name = "product_image_id")
	private Integer product_image_id;
	
	@JsonProperty("product_id")
	@Column(name = "product_id")
	@NotNull(message = "product_id is required")
	private Integer product_id;
	
	@JsonProperty("image")
	@Column(name = "image")
	@NotNull(message = "image is required")
	private String image;
	
	@Column(name = "status")
	@Max(value = 1, message = "El valor debe ser 1 o 0")
	@Min(value = 0, message = "El valor debe ser 1 o 0")
	@JsonIgnore
	private Integer status;

	/**
	 * @return the product_image_id
	 */
	public Integer getProduct_image_id() {
		return product_image_id;
	}

	/**
	 * @param product_image_id the product_image_id to set
	 */
	public void setProduct_image_id(Integer product_image_id) {
		this.product_image_id = product_image_id;
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
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
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
