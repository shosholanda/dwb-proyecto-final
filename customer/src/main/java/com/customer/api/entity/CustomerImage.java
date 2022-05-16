package com.customer.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Versión de implementación para poder enviar y recibir imágenes al servidor.
 * Básicamente añadiremos el objeto imagen que recibiremos cuando consultemos por imágenes.
 * Solo tiene 2 atributos. ID y nombre o url de la imagen. Todo lo que tenga Image es parte de la implementación
 * de api con imágenes. (funcionalidad extra)
 * @author Lenovo
 *
 */

@Entity
@Table(name = "customer_image")
public class CustomerImage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_image_id")
	private Integer customer_image_id;
	
	@NotNull
	@Column(name = "customer_image")
	private String customer_image;
	
	public CustomerImage() {
		this.customer_image = "";
	}

	/**
	 * @return the customer_image_id
	 */
	public Integer getCustomer_image_id() {
		return customer_image_id;
	}

	/**
	 * @param customer_image_id the customer_image_id to set
	 */
	public void setCustomer_image_id(Integer customer_image_id) {
		this.customer_image_id = customer_image_id;
	}

	/**
	 * @return the customer_image
	 */
	public String getCustomer_image() {
		return customer_image;
	}

	/**
	 * @param customer_image the customer_image to set
	 * Nos vamos a crear los servicios (clase abstracta) de las funciones que tengamos.
	 */
	public void setCustomer_image(String customer_image) {
		this.customer_image = customer_image;
	}
	
	
}
