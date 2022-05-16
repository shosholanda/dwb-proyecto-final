/* Esta clase entity debe encontrarse dentro del paquete entity.
 */
package com.customer.api.entity;

//Ctrl shift o = importar bibliotecas automáticamente
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

/*Primero hay que importar las dependencias del repositorio maven las cuales son:
1. Spring boot starter validation
2. Spring boot starter data jpa. 
Al guardar en el archivo pom el código html se descargarán automáticamente las dependencias*/

//Indicar que esta clase es una entidad.
@Entity

//Indicar la referencia que está haciendo a la bdd
@Table(name = "region")
public class Region {

	/* Implementar los atributos que están en el documento de endpoints en la sección de persistencia
	 * Se deben especificar con los @ las anotaciones de entidades a los atributos de la clase como 
	 * correspondan, dependiendo de lo que queramos 
	 */
	
	//Especifica la llave principal de una	entidad.
	@Id
	//Proporciona la especificación de	estrategias de generación de los valores de las llaves primarias.
	//Es como el incremento automático de las llaves primarias.
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//La columna a la que hace referencia de la tabla referenciada.
	@Column(name = "region_id")
	private Integer region_id;
	
	//El elemento anotado no debe ser nulo. Acepta cualquier tipo.
	//El id no es necesario, pues se asigna automaticamente.
	@NotNull
	@Column(name = "region")
	private String region;
	
	//Asigna valores mínimo y máximo para este atributo. Debe ser de tipo numérico. 
	//Podemos agregar un parámetro de mensaje de error, en caso de que haya un valor fuera del rango.
	//En este caso, status solo puede ser 0 o 1
	@Min(value = 0, message = "El status debe ser 0 o 1")
	@Max(value = 1, message = "El status debe ser 0 o 1")
	@Column(name = "status")
	//Como no queremos que en las respuestas del json aparezca status, lo ignnoramos
	@JsonIgnore
	private Integer status;
	
	
	//Constructor vacío porque estamos chiquitos.
	public Region() {
		
	}

	//Agregar métodos get y set con click izquierdo, source:
	public Integer getRegion_id() {
		return region_id;
	}


	public void setRegion_id(Integer region_id) {
		this.region_id = region_id;
	}


	public String getRegion() {
		return region;
	}


	public void setRegion(String region) {
		this.region = region;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}

	//Método toSting() generado automáticamente con source.
	@Override
	public String toString() {
		return "Region [region_id=" + region_id + ", region=" + region + ", status=" + status + "]";
	}
	
	//El siguiente paso se encuentra en el paquete controlador
}
