package com.example.repository;

import org.springframework.stereotype.Repository;

import com.example.ControllerSpringBoot04.DatosGenerales;
import com.example.pojo.Persona;

@Repository
public class PersonaDAOImpl implements PersonaDAO{

	@Override
	public int anhadirPersona(Persona persona) {
		int filasAfectada = 0;
		int aleatorioSimularFallo = (int)(Math.random() * 2); 
		
		if(aleatorioSimularFallo == 1) { //Simula que se "insertara" con exito la persona en la bbdd
			filasAfectada = 1;
			DatosGenerales.personas.add(persona);
		}
		
		return filasAfectada;
	}

	@Override
	public Persona obtenerPersona(int idPersona) {
		Persona personaSolicitada = null;
		boolean personaEncontrada = false;
		for (int i = 0; i < DatosGenerales.personas.size() && !personaEncontrada; i++) {
			if(DatosGenerales.personas.get(i).getId() == idPersona) {
				personaSolicitada = DatosGenerales.personas.get(i);
				personaEncontrada = true;
			}
		}
		return personaSolicitada;
	}

	@Override
	public boolean comprobarExistenciaPersona(String nombreUsuario, String contrasenha) {
		boolean existe = false;
		for (int i = 0; i < DatosGenerales.personas.size() && !existe; i++) {
			if(DatosGenerales.personas.get(i).getNombreUsuario().equals(nombreUsuario) && DatosGenerales.personas.get(i).getContrasenha().equals(contrasenha)) {
				existe = true;
			}
		}
		return existe;
	}
	
}
