package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.pojo.Persona;
import com.example.repository.PersonaDAOImpl;

@Service
public class PersonaService {
	
	@Autowired
	private PersonaDAOImpl personaDAO;
	
	public int anhadirPersona(Persona persona) {
		return personaDAO.anhadirPersona(persona);
	}
	
	public int editarPersona(Persona persona) {
		return personaDAO.editarPersona(persona);
	}
	
	public int eliminarPersona(int id) {
		return personaDAO.eliminarPersona(id);
	}
	public List<Persona> obtenerPersonas() {
		return personaDAO.obtenerPersonas();
	}
	
	public Persona obtenerPersona(int idPersona) {
		return personaDAO.obtenerPersona(idPersona);
	}

	public boolean comprobarExistenciaPersona(String nombreUsuario, String contrasenha) {
		return personaDAO.comprobarExistenciaPersona(nombreUsuario, contrasenha);
	}

}
