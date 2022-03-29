package com.isotrol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.isotrol.service.PersonaService;

@Controller
@RequestMapping
public class PersonasController {

	@Autowired
	private PersonaService personaService;
	
	@GetMapping("/")
	public ModelAndView get() {
		ModelMap map = new ModelMap();
		map.put("ListadoPersonas", personaService.obtenerPersonas());
		
		return new ModelAndView("/index",map);
	}
	@GetMapping("/persona")
	public void get(@RequestParam(name = "idPersona") Long id) {
		
	}
	
	@GetMapping("/create")
	public String create() {
		return "/create";
	}
}
