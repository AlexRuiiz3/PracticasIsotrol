package com.inyeccion02.Inyeccion02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.inyeccion02.pojo.Persona;
import com.inyeccion02.services.PersonasService;

@ComponentScan( basePackages = { "com.inyeccion02.repository", "com.inyeccion02.services" } ) //Para que sepa donde buscar el compilador
@SpringBootApplication
public class Inyeccion02Application {

	@Autowired
	private PersonasService personaService;
	
	public static void main(String[] args) {
		SpringApplication.run(Inyeccion02Application.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) 
	{
		return args -> 
		{
			Persona persona = new Persona(1,"Alejandro","Ruiz Campos",21);
			personaService.insertarPersona(persona);
		};
	}
}
