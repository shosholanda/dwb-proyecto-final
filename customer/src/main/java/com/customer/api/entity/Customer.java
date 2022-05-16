package com.customer.api.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Clase que es como la tabla que relaciona customer_image con region, adicionalmente contiene otros atributos.
 * @author Lenovo
 *
 */
@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("customer_id")
	@Column(name  = "customer_id")
	private Integer customer_id;
	
	@Column(name = "name")
	@NotNull(message = "name is required")
	@JsonProperty("name")
	private String name;
	
	@Column(name = "surname")
	@NotNull(message = "name is required")
	@JsonProperty("surname")
	private String surname;
	
	@Column(name = "date_birth")
	@NotNull(message = "date birth is required")
	@JsonProperty("date_birth")
	private LocalDate date_birth;
	
	@Column(name = "rfc")
	@NotNull(message = "rfc is required")
	@JsonProperty("rfc")
	private String rfc;
	
	@Column(name = "mail")
	@NotNull(message = "mail is required")
	@JsonProperty("mail")
	private String mail;
	
	@Column(name = "address")
	@JsonProperty("address")
	private String address;
	
	@Min(value = 0, message = "status must be 0 or 1")
	@Max(value = 1, message = "status must be 0 or 1")
	@Column(name = "status")
	@JsonIgnore
	private Integer status;
	
	/*
	 * Onetoone para decir que la relación de las tablas es de 1 a 1
	 * cascade type .merge = actualiza los datos de una
	 * @Joincolumn = Junta las columnas que son iguales. (columna region_id de customer con region_id de Region)
	 */
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "region_id", referencedColumnName = "region_id")
	private Region region;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_image_id", referencedColumnName = "customer_image_id")
	private CustomerImage customerImage;

	/**
	 * @return the customer_id
	 */
	public Integer getCustomer_id() {
		return customer_id;
	}

	/**
	 * @param customer_id the customer_id to set
	 */
	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return the date_birth
	 */
	public LocalDate getDate_birth() {
		return date_birth;
	}

	/**
	 * @param date_birth the date_birth to set
	 */
	public void setDate_birth(LocalDate date_birth) {
		this.date_birth = date_birth;
	}

	/**
	 * @return the rfc
	 */
	public String getRfc() {
		return rfc;
	}

	/**
	 * @param rfc the rfc to set
	 */
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @return the adress
	 */
	public String getAdress() {
		return address;
	}

	/**
	 * @param adress the adress to set
	 */
	public void setAdress(String adress) {
		this.address = adress;
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

	/**
	 * @return the region
	 */
	public Region getRegion() {
		return region;
	}

	/**
	 * @param region the region to set
	 */
	public void setRegion(Region region) {
		this.region = region;
	}

	/**
	 * @return the customer_image
	 */
	public CustomerImage getCustomer_image() {
		return customerImage;
	}

	/**
	 * @param customer_image the customer_image to set
	 */
	public void setCustomer_image(CustomerImage customer_image) {
		this.customerImage = customer_image;
	}
}
