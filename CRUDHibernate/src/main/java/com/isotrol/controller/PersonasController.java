package com.isotrol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.isotrol.entity.Persona;
import com.isotrol.service.PersonaService;

@Controller
@RequestMapping("/personas")
public class PersonasController {

	@Autowired
	private PersonaService personaService;
	
	@GetMapping("/index")
	public ModelAndView get() {
		ModelMap map = new ModelMap();
		map.put("ListadoPersonas", personaService.obtenerPersonas());
		
		return new ModelAndView("/index",map);
	}
	
	@GetMapping("/create")
	public String create() {
		return "/create";
	}
	
	@GetMapping("/details")
	public ModelAndView details(@RequestParam(name = "id") int id) {
		ModelMap map = new ModelMap();
		map.put("persona",personaService.obtenerPersona(id));
		return new ModelAndView("/details",map);
	}
	
	@GetMapping("/edit")
	public ModelAndView edit(@RequestParam(name = "id") int id) {
		ModelMap map = new ModelMap();
		map.put("persona",personaService.obtenerPersona(id));
		return new ModelAndView("/edit",map);
	}
	
	@GetMapping("/delete")
	public ModelAndView delete(@RequestParam(name = "id") int id) {
		ModelMap map = new ModelMap();
		map.put("persona",personaService.obtenerPersona(id));
		return new ModelAndView("/delete",map);
	}
	
	@PostMapping("/create")
	public String createPost(Persona persona) {
		personaService.insertarPersona(persona);
		return "/index";
	}
	
	@PostMapping("/edit")
	public String edit(Persona persona) {
		personaService.insertarPersona(persona);
		return "/index";
	}
	
	@PostMapping("/delete")
	public String deletePost(@RequestParam(name = "id") int id) {
		personaService.eliminarPersona(id);
		return "/index";
	}
}
