package br.edu.ifce.academico.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NotaController {

	@GetMapping({"/notas"})
	public String index() {
		return "nota";
	}
	
}
