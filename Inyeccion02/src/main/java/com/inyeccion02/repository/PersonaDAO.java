package com.inyeccion02.repository;

import com.inyeccion02.pojo.Persona;

public interface PersonaDAO {

	int insertarPersona(Persona persona);
	
	int actualizarPersona(Persona persona);
	
	int actualiarEdadPersona(int id,int edad);
	
	int eliminarPersona(int id);
}
