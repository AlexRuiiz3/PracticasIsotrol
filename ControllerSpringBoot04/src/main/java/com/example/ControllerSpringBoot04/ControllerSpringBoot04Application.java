package com.example.ControllerSpringBoot04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.example.controller", "com.example.service", "com.example.repository", "com.example.pojo"})
@SpringBootApplication
public class ControllerSpringBoot04Application {

	public static void main(String[] args) {
		DatosGenerales.llenarListaPersonas();
		SpringApplication.run(ControllerSpringBoot04Application.class, args);
	}
}
