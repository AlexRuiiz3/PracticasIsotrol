package com.isotrol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isotrol.entity.Persona;
import com.isotrol.repository.PersonaDAO;

@Service
public class PersonaService {

	@Autowired
	private PersonaDAO personaDao;
	
	public void insertarPersona(Persona persona) {
		personaDao.save(persona);
	}
	
	public void eliminarPersona(int id) {
		personaDao.deleteById(id);
	}
	
	public Persona obtenerPersona(int id) {
		return personaDao.getById(id);
	}
	
	public List<Persona> obtenerPersonas() {
		return personaDao.findAll();
	}
}
