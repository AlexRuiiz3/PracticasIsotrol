package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.example.service.PersonaService;

import lombok.extern.java.Log;

@Controller
@Log
public class PersonaController {

	@Autowired
	private PersonaService personaService;
	
	@PostMapping("/index")
	public ModelAndView index(@RequestParam(name = "txtUsuario") String nombreUsuario,@RequestParam(name = "txtContrasenha") String contrasenha) {
		String viewDestino = "/principal"; 
		ModelMap map = new ModelMap();
		
		log.info("[PersonasController-@PostMapping(/index)-index]Comprobar si existe persona por usuario("+nombreUsuario+") y contrasenha("+contrasenha+")");
		boolean existePersona = personaService.comprobarExistenciaPersona(nombreUsuario, contrasenha);
		if(existePersona) {
			map.put("UsuarioPersona", nombreUsuario);
			log.info("Existe Persona");
		}else {
			map.put("MsgError", "No se encontro ninguna persona con esos valores");
			viewDestino = "/index";
			log.info("No existe Persona");
		}
		
		return new ModelAndView(viewDestino, map);
	}
	
}
