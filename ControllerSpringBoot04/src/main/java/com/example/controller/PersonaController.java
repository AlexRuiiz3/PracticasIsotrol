package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.ControllerSpringBoot04.DatosGenerales;
import com.example.pojo.Persona;
import com.example.service.PersonaService;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class PersonaController {

	@Autowired
	private PersonaService personaService;
	
	@GetMapping("/")
	public ModelAndView get() {
		ModelMap map = new ModelMap();
		map.put("ListadoPersonas", personaService.obtenerPersonas());
		return new ModelAndView("/index",map);
	}
	
	@GetMapping("/login")
	public String login() {
		log.info("Accediendo a /login");
		return "/login";
	}
	
	@PostMapping("/login")
	public ModelAndView login(@RequestParam(name = "txtUsuario") String nombreUsuario,@RequestParam(name = "txtContrasenha") String contrasenha) {
		String viewDestino = "/principal"; 
		ModelMap map = new ModelMap();
		
		log.info("[login] Comprobar si existe persona por usuario("+nombreUsuario+") y contrasenha("+contrasenha+")");
		boolean existePersona = personaService.comprobarExistenciaPersona(nombreUsuario, contrasenha);
		if(existePersona) {
			map.put("NombreUsuario", nombreUsuario);
			log.info("Existe Persona");
		}else {
			map.put("MsgError", "No se encontro ninguna persona con esos valores");
			viewDestino = "/login";
			log.info("No existe Persona");
		}
		
		return new ModelAndView(viewDestino, map);
	}
	
	@GetMapping("/registro")
	public String create() {
		log.info("Accediendo a /registro");
		return "/registro";
	}
	
	@PostMapping("/registro")
	public ModelAndView create(Persona persona) {
		String viewDestino = "/index";
		ModelMap map = new ModelMap();
		
		log.info("[create] POST, Añadiendo persona");
		int filasAfectadas = personaService.anhadirPersona(persona);
		
		if(filasAfectadas != 1) {
			log.info("Error al insertar la persona");
			viewDestino = "/registro";
			map.put("MsgErrorCrearPersona", "Error al crear el usuario, vuelva a intentarlo");
		}
		
		return new ModelAndView(viewDestino,map);
	}
	
	@GetMapping("/editar")
	public ModelAndView edit(@RequestParam(name = "idPersona") int id) {
		ModelMap map = new ModelMap();
		log.info("[edit] obteniendo persona por id");
		map.put("persona", personaService.obtenerPersona(id));
		return new ModelAndView("/editar",map);
	}
	
	@PostMapping("/editar")
	public String edit(Persona persona) {
		log.info("[edit] POST, editando la persona");
		personaService.editarPersona(persona);
		return "/index";
	}
	
	@GetMapping("/eliminar")
	public ModelAndView delete(@RequestParam(name = "idPersona") int id) {
		ModelMap map = new ModelMap();
		log.info("[delete] obteniendo persona por id");
		map.put("persona", personaService.obtenerPersona(id));
		return new ModelAndView("/eliminar",map);
	}
	
	@PostMapping("/eliminar")
	public String deletePost(@RequestParam(name = "idPersona") int id) {
		log.info("[delete] POST, eliminando la persona");
		personaService.eliminarPersona(id);
		return "/index";
	}
}
