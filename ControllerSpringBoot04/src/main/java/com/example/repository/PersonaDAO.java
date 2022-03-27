package com.example.repository;

import java.util.List;

import com.example.pojo.Persona;

public interface PersonaDAO {

	int anhadirPersona(Persona persona);
	
	List<Persona> obtenerPersonas();
	
	Persona obtenerPersona(int idPersona);
	
	int editarPersona(Persona persona);
	
	int eliminarPersona(int id);
	
	boolean comprobarExistenciaPersona(String nombreUsuario, String contrasenha);
}
