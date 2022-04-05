package com.isotrol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductoController {

	@RequestMapping("/")
	public String index() {
		return "/index";
	}
	
	@PostMapping("/indexPost")
	public String indexPost() {
		return "redirect:/";
	}
}
