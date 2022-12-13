package br.edu.ifce.academico.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GeralController {
	
	@GetMapping("/")
	public String index() {
		return "geral/index";
	}
	
	@GetMapping("/sobre")
	public String sobre() {
		return "geral/sobre";
	}
	
	@GetMapping("/403")
	public String deny() {
		return "geral/acesso_negado";
	}
}
