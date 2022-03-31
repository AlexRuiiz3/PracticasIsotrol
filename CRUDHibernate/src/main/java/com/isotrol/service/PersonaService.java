package com.isotrol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isotrol.entity.Persona;
import com.isotrol.repository.PersonaDAO;

@Service("PersonaService")
//@Primary para que use este service por defecto ya que en el controller los que hay es una referencia a la interfaz, entonces si hay dos calses que implementan la interfaz, no sabria cual inyectar, pues para eso esta @Primary para asignar una por defecto
public class PersonaService implements IService{ //Hacer que implemente una interfaz asi el codigo esta menos acoplado

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
