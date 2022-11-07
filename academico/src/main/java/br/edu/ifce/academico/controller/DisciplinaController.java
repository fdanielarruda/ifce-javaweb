package br.edu.ifce.academico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.edu.ifce.academico.model.Disciplina;
import br.edu.ifce.academico.service.DisciplinaService;

@Controller
public class DisciplinaController {
	
	@Autowired
	DisciplinaService disciplinaService;
	
	@GetMapping({"/disciplinas"})
	public String index(Model model) {
		List<Disciplina> disciplinas = disciplinaService.listarDisciplinas();
		
		model.addAttribute("disciplinas", disciplinas);
		
		return "disciplina";
	}
	
}
