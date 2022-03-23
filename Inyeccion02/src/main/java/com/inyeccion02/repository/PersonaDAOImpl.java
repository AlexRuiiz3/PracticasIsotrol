package com.inyeccion02.repository;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import com.inyeccion02.pojo.Persona;

import lombok.extern.java.Log;

@Log
@Repository("PersonaDAO")
public class PersonaDAOImpl implements PersonaDAO{

	@Override
	public int insertarPersona(Persona persona) {
		log.info("Persona creada: "+persona.toString());
		return 0;
	}
	
	@Override
	public int actualizarPersona(Persona persona) {
		log.info("Persona actualizada: "+persona.toString());
		return 0;
	}
	
	@Override
	public int actualiarEdadPersona(int id,int edad) {
		log.info("Persona "+id+" edad actualizada: "+edad);
		return 0;
	}
	
	@Override
	public int eliminarPersona(int id) {
		log.info("Persona eliminada: "+id);
		return 0;
	}
}
