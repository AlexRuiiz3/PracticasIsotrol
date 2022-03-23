package com.inyeccion02.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inyeccion02.pojo.Persona;
import com.inyeccion02.repository.PersonaDAO;

@Service
public class PersonasService {

	@Autowired
	private PersonaDAO personaDAO;

	public int insertarPersona(Persona persona) {
		return personaDAO.insertarPersona(persona);
	}
	
	public int actualizarPersona(Persona persona) {
		return personaDAO.actualizarPersona(persona);
	}
	
	public int actualiarEdadPersona(int id, int edad) {
		return personaDAO.actualiarEdadPersona(id, edad);
	}
	
	public int eliminarPersona(int id) {
		return personaDAO.eliminarPersona(id);
	}
}
