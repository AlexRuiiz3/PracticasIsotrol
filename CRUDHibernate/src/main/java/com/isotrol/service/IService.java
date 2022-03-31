package com.isotrol.service;

import java.util.List;

import com.isotrol.entity.Persona;

public interface IService {

	public void insertarPersona(Persona persona);
	
	public void eliminarPersona(int id);
	
	public Persona obtenerPersona(int id);
	
	public List<Persona> obtenerPersonas();
	
}
