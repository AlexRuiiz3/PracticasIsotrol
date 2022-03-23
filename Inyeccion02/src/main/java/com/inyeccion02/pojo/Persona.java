package com.inyeccion02.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Persona {

	private int id;
	private String nombre;
	private String apellidos;
	private int edad;	
}
