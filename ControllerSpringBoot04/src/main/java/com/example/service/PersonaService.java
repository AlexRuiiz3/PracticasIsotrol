package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.pojo.Persona;
import com.example.repository.PersonaDAOImpl;

@Component
@Service
public class PersonaService {
	
	@Autowired
	private PersonaDAOImpl personaDAO;
	
	public int anhadirPersona(Persona persona) {
		return personaDAO.anhadirPersona(persona);
	}
	
	public Persona obtenerPersona(int idPersona) {
		return personaDAO.obtenerPersona(idPersona);
	}

	public boolean comprobarExistenciaPersona(String nombreUsuario, String contrasenha) {
		return personaDAO.comprobarExistenciaPersona(nombreUsuario, contrasenha);
	}

}
