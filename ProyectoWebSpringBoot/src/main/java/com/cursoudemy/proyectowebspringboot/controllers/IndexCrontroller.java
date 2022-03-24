package com.cursoudemy.proyectowebspringboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexCrontroller {

	//Como en asp.net 
	@RequestMapping() //Para relacionar este metodo con una ruta url. Cuando se escriba index, se ejecute este metodo
	public String index() {
		
		return "index";
	}
}
