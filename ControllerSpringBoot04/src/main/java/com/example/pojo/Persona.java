package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Persona {

	private int id;
	private String nombre;
	private String apellidos;
	private int edad;
	private String nombreUsuario;
	private String contrasenha;
	
}
