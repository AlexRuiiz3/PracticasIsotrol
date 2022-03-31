package com.isotrol.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.isotrol.entity.Persona;
import com.isotrol.service.IService;

@Controller
@RequestMapping("/personas")
public class PersonasController {

	@Autowired
	@Qualifier("PersonaService")//Si hay dos clases que implementan la interfaz IService, no se sabria cual inyectar, entonces con @Qualifier("Nombre Servicio") se indica cual es el que se quiere inyectar
	private IService personaService;
	
	@GetMapping("/index")
	public ModelAndView get() {
		ModelMap map = new ModelMap();
		map.put("ListadoPersonas", personaService.obtenerPersonas());
		
		return new ModelAndView("/index",map);
	}
	
	@GetMapping("/create")
	public String create(@ModelAttribute("persona") Persona persona) {
		return "/create";
	}
	
	@GetMapping("/details")
	public ModelAndView details(@RequestParam int id) {
		ModelMap map = new ModelMap();
		map.put("persona",personaService.obtenerPersona(id));
		return new ModelAndView("/details",map);
	}
	
	@GetMapping("/edit")
	public ModelAndView edit(@RequestParam int id) {
		ModelMap map = new ModelMap();
		map.put("persona",personaService.obtenerPersona(id));
		return new ModelAndView("/edit",map);
	}
	
	@GetMapping("/delete")
	public ModelAndView delete(@RequestParam int id) {
		ModelMap map = new ModelMap();
		map.put("persona",personaService.obtenerPersona(id));
		return new ModelAndView("/delete",map);
	}
	
	@PostMapping("/create")
	public String createPost(@Valid @ModelAttribute("persona") Persona persona, BindingResult result) {
		String viewDestino = "/create";
		if(!result.hasErrors()) {
			personaService.insertarPersona(persona);
			viewDestino = "redirect:/personas/index";
		}
		
		return viewDestino; //redirect para que luego en la ruta despues de eliminar no se quede arriba el /personas/delete
	}
	
	@PostMapping("/edit")
	public String edit(Persona persona) { //Si se quiere obtener varios parametros se puede poniendo como parametro HttpServletRequest y luego se hace un get con el nombre del atributo que se haya indicado in thymeleaf
		personaService.insertarPersona(persona);
		return "redirect:/personas/index"; //redirect para que luego en la ruta despues de eliminar no se quede arriba el /personas/delete
	}
	
	@PostMapping("/delete")
	public String deletePost(@RequestParam int id) {//@RequestParam(name = "id") int id es redundante ya que con el nombre de la variable si se llama igual que en el html no hay que poner nada de name
		personaService.eliminarPersona(id);
		return "redirect:/personas/index"; //forward para rutas internas del proyecto y no para paginas externas
	}
	
	@ModelAttribute("listaCadenas") //Esto sirve para pasar datos a la vista, pero de esta forma es mas generica ya que, se puede llamar desde varios metodos, haciendo de esa forma que varios metodos envien los mismos datos gracias a este metodo
	public List<String> poblarLista(){
		//Aqui se rellenaria la lista
		return new ArrayList<>();
	}
}
