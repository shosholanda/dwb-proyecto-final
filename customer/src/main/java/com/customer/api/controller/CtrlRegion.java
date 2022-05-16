package com.customer.api.controller;

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

import com.customer.api.dto.ApiResponse;
import com.customer.api.entity.Region;
import com.customer.api.service.SvcRegion;
import com.customer.exception.ApiException;

//Al igual que @Entity, sirve para indicar que esta clase es un controlador
@RestController

//Esta etiqueta necesita una ubicacion para poder acceder a los métodos aquí implementados.
//Es decir, cada vez que en el "navegador" estemos en la sección region podemos acceder a estos métodos.
@RequestMapping("/region")

/* En esta clase se implementan los métodos de endpoints especificados en el documento endpoints
 * Al final deberemos implementar 5 enpoints que son los de Lista, Read, Delete, Get, Update.
 */
public class CtrlRegion {
	
	/**
	 * UPDATE: Actualizar el controlador para que no tenga objetos creados, sino para 
	 * procesar los datos reales de la bdd utilizando un objeto de la clase srvrepoimp.
	 * @Autowired para lo mismo que en srv.
	 */
	@Autowired
	SvcRegion svc;
	
	/* Endpoint de LIST
	 * Regresa un objeto de tipo ResponseEntity que tiene un formato JSON.
	 * Podemos especificar el tipo que regresa usando los <>. Adicionalmente, el método debe llamarse getRegions
	 * No recibe ningún parámetro de entrada el método puesto que solo obtendremos todas las regiones disponibles.
	 * Cuando tengamos un método GET Http se ejecutará este método.
	 */
	@GetMapping
	public ResponseEntity<List<Region>> getRegions(){
		
		//Aquí debería estar el código de la consulta a la bdd para obtener todas las regiones.
		//Sin embargo, como es un primer acercamiento, creamos 2 objetos Region y los añadimos a una lista.
		
		/* Objetos de prueba para los priemros videos, ahora se implementará con consultas reales a la bdd
		Region region1 = new Region();
		region1.setRegion_id(1);
		region1.setRegion("Norte");
		region1.setStatus(1);
		
		Region region2 = new Region();
		region2.setRegion_id(2);
		region2.setRegion("Sur");
		region2.setStatus(1);
		
		List regiones = new ArrayList();
		
		regiones.add(region1);
		regiones.add(region2);
		*/
		
		/*
		 * Regresamos un objeto de tipo Response entity.
		 * Ya no es necesario especificar el tipo <T> de nuevo, simpelemente lo dejamos vacíó.
		 * Recibe dos parámetros den entrada. 
		 * 1. El objeto que vamos a regresar (en este caso es regiones)
		 * 2. El código http que dice en que estado fue devuelto el objeto.
		 * HttpStatus es una Enumeración (200 = OK)
		 *
		return new ResponseEntity<>(regiones, HttpStatus.OK);
		*/
		
		
		
		//Regresamos el método que hicimos en SvcRegionImp.java
		//return new ResponseEntity<>(svc.getRegions(), HttpStatus.OK);
		
		//Ultima modificación, implementado con exceptions. (igual, pues no hay errores porque #listavacia)
		return new ResponseEntity<>(svc.getRegions(), HttpStatus.OK);
	}
	
	/*
	 * Endpoing READ.
	 * Es similar al endpoing List, con la diferencia de que en lugar de devolver todas las regiones
	 * solo se regresa la región con el id que se especifica en /regiones/{region_id}
	 * Cambia el recurso donde se llama a este endpoint especificando el id.
	 * Se manda llamar cuando se utiliza el metodo GET HTTP. (para obtener cosas)
	 * Recibe un parámetro de entrada que es el id de la region a buscar para regresarlo si existe o error en otro caso.
	 * @PathVariable para especificar que ese argumento viene de la URL.
	 */
	@GetMapping("/{region_id}")
	public ResponseEntity<Region> getRegion(@PathVariable int region_id){
		/*Ejemplo de prueba
		Region region1 = new Region();
		region1.setRegion_id(1);
		region1.setRegion("Norte");
		region1.setStatus(1);
		
		return new ResponseEntity<>(region1, HttpStatus.OK);
		*/
		//Implemntación chida.
		return new ResponseEntity<>(svc.getRegion(region_id), HttpStatus.OK);
		
		/*
		 * Ultima modificación; la excepcion se lanza desde el servicio porque ahí podeomos verificar más facilmente.
		 */
	}
	
	/* Endpoint CREATE
	 * A diferencia de LIST y READ, en este metodo no necesitamos obtener algo de la web, sino que utiliza al método
	 * crear POST de HTTP.
	 * En lugar de regresar un objeto region, regresamos un String que es el mensaje de que se creo correctamente la region nueva
	 * Recibe como parámetro la region que se añadira a la bdd. 
	 * @Valid al parámetro porque se debe ver que cumpla con las etiquetas de la entidad region (NOT NULL)
	 * @RequestBody al parámetro porque este objeto es obligatorio para ejecutar este método.
	 * Recibe como parámetro un objeto de la interfaz BindingResult, que lo que hace es obtener los errores en caso
	 * de que haya un error.
	 * @BindingResult Interfaz que amplía las capacidades de registro de errores, permite aplicar un validador, agrega análisis y
creación de modelos específicos. Sirve como contenedor de resultados y se puede usar directamente para invocar un
validador.
	 */
	@PostMapping
	public ResponseEntity<ApiResponse> createRegion(@Valid @RequestBody Region region, BindingResult bindingResult){
		//mensaje que mostraremos en cualquier caso.
		//Verificar que el objeto region que recibimos cumpla con las validaciones que especificamos
		/*if (bindingResult.hasErrors()) {
			//Como hay errores de validacion, Obtenemos toda la pila, y nos quedamos con el primer error y lo convertimos a string.
			message = bindingResult.getAllErrors().get(0).getDefaultMessage();
			//Regresar el mensaje de error:
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
		*/
		//Si no hubo errores, entonces añadimos region a la bdd con su código correspondiente.
		/*En este caso, solo ponemos que todo salio bien porque es de prueba.
		message = "region created";
		return new ResponseEntity<>(message, HttpStatus.OK);
		*/
		
		//IMplementación chida.
		//return new ResponseEntity<>(svc.createRegion(region), HttpStatus.OK);
		
		/*
		 * Implementación final:
		 * Cambiar String por ApiResponse (regresar ya sea una Region o el json dto.
		 */
		if (bindingResult.hasErrors()) {
			//Lanzamos la excepción dentro de nuestra api que recibe el codigo http y el mensaje.
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		}
		
		//Como regresa un apiresponse el <T> y en la interfaz de servicio api regresa un string debemos cambiar 
		//los servicios (al menos por ahora) la firma de los métodos en la interfaz de servicio.
		return new ResponseEntity<>(svc.createRegion(region), HttpStatus.OK);
	}
	
	/* Endpoint UPDATE
	 * Similar a Post, solo que se utiliza el método Put de HTTP
	 * Además, recibe 2 parámetros, el id de la región que se quiere actualizar y el objeto con el se actualizara
	 */
	@PutMapping("/{region_id}")
	public ResponseEntity<ApiResponse> updateRegion(@PathVariable int region_id, @Valid @RequestBody Region region, BindingResult bindingResult){
		//Se hace lo mismo que en update. 
		//Se verifica que el objeto region sea correcto.
		/*if (bindingResult.hasErrors()) {
			message = bindingResult.getAllErrors().get(0).getDefaultMessage();
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
		*/
		//Aquí deberíamos poner el código para hacer la consulta a la bdd y ver si existe el id, si sí lo añadimos
		/*Si no regresamos otro error.
		message = "region updated";
		return new ResponseEntity<>(message, HttpStatus.OK);
		*/
		
		//IMplementación chida.
		//return new ResponseEntity<>(svc.updateRegion(region_id, region), HttpStatus.OK);
		
		/*
		 * Ultima implementación chida: Cambiar los métodos para que en lugar que devuelvan un string devuelvan un apiresponse
		 */
		if (bindingResult.hasErrors()) {
			throw new ApiException (HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		}
		return new ResponseEntity<>(svc.updateRegion(region_id, region), HttpStatus.OK);
	}
	
	/*
	 * Endpoint DELETE (final)
	 * Al igual que eliminar en listas, debemos recibir el id de la region que queremos eliminar, por lo que recibimos el id.
	 * El método al cual es mapeado es Delete de http
	 */
	@DeleteMapping("/{region_id}")
	public ResponseEntity<ApiResponse> deleteRegion(@PathVariable int region_id) {
		/*Aquí debe estar el código para poder eliminar en la bdd y ver si existe primero y luego eliminar exitosamente
		message = "region deleted";
		return new ResponseEntity<>(message, HttpStatus.OK);
		*/
		
		//Implementación chida:
		//Lo que sigue es probar de nuevo las peticiones creadas en postman
		return new ResponseEntity<>(svc.deleteRegion(region_id), HttpStatus.OK);
		
		/*
		 * De acuerdo al formato de endpoints, solamente lanzamos las apiexception que puedan 
		 * Salir de aquí, como por ejemplo los bindingResults. Al final las excepciones más 
		 * apegadas al funcionamiento se lanzan desde el servicio, por lo que no hay que poner en
		 * este método un throw.
		 */
	}
	
	/*	EJECUTAR NUESTRO PROYECTO EN UN SERVIDOR LOCAL CON UN PUERTO PARTICULAR.
	 * Después de definir los endpoints, vamos a probar nuestros endpoints con postman.
	 * Primero, debemos conectar esto con una bdd, creada en mysql con el siguiente esquema:
	 * 
	 * CREATE TABLE region (
region_id INT NOT NULL AUTO_INCREMENT,
region VARCHAR(100) UNIQUE NOT NULL,
status TINYINT NOT NULL,
PRIMARY KEY (region_id)
);
	 * 
	 * Click derecho al proyecto > Run As > Spring Boot App (Dejar que falle la aplicación)
	 * Despues
	 * 
	 * 1. Ir al archivo customer/src/main/resources/aplication.properties y abrirlo.
	 * 2. Cambiar el puerto por default dado por apache (8080) a otro ej. (8081) con: server.port = 8081
	 * 3. Añadir el siguiente código:
	 * 
	 * spring.datasource.url = jdbc:mysql://localhost:<puerto de la bdd>/<nombre de la bdd>
	 * spring.datasource.username = root
	 * spring.datasource.password = ${db_password}
	 * spring.datasource.driver-class = com.mysql.jdbc.Driver
	 * 
	 * En url se debe indicar el puerto y el nombre de la bdd que se requiera construida en mysql workbench. Debe ya tener el esquema de la bdd.
	 * . localhost porque está en la misma computadora
	 * en username es el usuario root
	 * en password debe ser añadida como una variable de entorno, o simplemente poner la contraseña ahí
	 * Para crear una variable de entorno, vamos a Run > Run configuration > Spring Boot App > Enviroments > Add
	 * Agregamos el nombre y el valor de la variable. Aplicamos y cerramos.
	 * 
	 * Para driver-class, debeemos ir al maven repository y copiar la dependencia mysql conector /J
	 * La dependencia la pegamos en el archivo pom del proyecto.
	 * 
	 * FINALMENTE, DEBEMOS EJECUTAR EL PROYECTO CON
	 * Click derecho al proyecto > Run As > Spring Boot App
	 * 
	 * La continuación para probar nuestros controladores es en customer/postman.txt
	 */
}
