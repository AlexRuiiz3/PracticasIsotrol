package com.example.inicio01;

//import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.pojo.Persona;
import com.example.pojo.Producto;

import lombok.extern.log4j.Log4j2;
//@Repository(Es un estereotipo relacionado con algo de acceso a datos) se indica en una clase que se quiera que sea una clase manejada por spring para que pueda tener una instancia en su memoria
//@Service sirve para indicar que algo representa una logica de negocios
//@AutoWired(Objetivo traer una instancia definida por un estereotipo) para indicar que variable cogera la instancia que tiene spring en memoria

//classptah ruuta donde la aplicacion sabe buscar por defecto conoce src main java y resources
@SpringBootApplication
@Log4j2
public class Inicio01Application {
	
	public static void main(String[] args) {
	//SpringApplication.run(Inicio01Application.class, args);
		Persona p = new Persona();
		
		p.setNombre("Alejandro");
		p.setApellidos("Ruiz");
		p.setEdad(21);
		/*
		Persona p2 = new Persona("Alejandro","Ruiz",21);
		
		if(p2.equals(p)) {
			log.info("Iguales");
		}else {
			log.debug("No son iguales");
		}*/
		
		log.info("Nivel info");
		log.debug("Nivel debug");
		//log.debug(p);
		//log.info(p.getNombre());
		/*
		Producto producto1 = new Producto();
		
		producto1.setId(1);
		producto1.setDescripcion("Descripcion de el productoa");
		producto1.setPrecio(19.99);
		
		log.info(producto1);
		log.error(producto1);
		log.debug(producto1);*/

	}
}
