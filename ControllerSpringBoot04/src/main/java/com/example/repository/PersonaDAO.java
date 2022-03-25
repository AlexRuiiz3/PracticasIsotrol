package com.example.repository;

import com.example.pojo.Persona;

public interface PersonaDAO {

	int anhadirPersona(Persona persona);
	
	Persona obtenerPersona(int idPersona);
	
	boolean comprobarExistenciaPersona(String nombreUsuario, String contrasenha);
}
