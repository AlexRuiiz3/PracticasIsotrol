package com.isotrol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.isotrol.service.ProductoService;

@Controller
@RequestMapping
public class ProductoController {

	@Autowired
	private ProductoService productoService;
	
	@GetMapping("/")
	public ModelAndView get() {
		ModelMap map = new ModelMap();	
		map.put("ListadoProductos", productoService.obtenerProductos());
		return new ModelAndView("/index",map);
	}
	
	
}
