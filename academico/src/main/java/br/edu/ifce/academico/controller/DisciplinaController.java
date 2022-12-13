package br.edu.ifce.academico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.ifce.academico.model.Disciplina;
import br.edu.ifce.academico.service.CursoService;
import br.edu.ifce.academico.service.DisciplinaService;
import br.edu.ifce.academico.service.MatrizCurricularService;

@Controller
public class DisciplinaController {
	
	@Autowired
	DisciplinaService disciplinaService;
	
	@Autowired
	MatrizCurricularService matrizCurricularService;
	
	@Autowired
	CursoService cursoService;
	
	@GetMapping("/disciplinas")
	public String index(Model model) {
		List<Disciplina> disciplinas = disciplinaService.listarDisciplinas();
		
		model.addAttribute("disciplinas", disciplinas);
		
		return "disciplinas/disciplina";
	}
	
	////////////////// CADASTRAR //////////////////
	@GetMapping("/disciplinas/cadastrar")
	public String cadastrar(Disciplina disciplina) {
		return "disciplinas/cadastro";
	}
	
	@PostMapping("/disciplinas/salvar")
	public String salvar(Disciplina disciplina) {
		disciplinaService.salvarDisciplina(disciplina);
	
		return "redirect:/disciplinas";
	}
	
	////////////////// EDITAR //////////////////
	@GetMapping("/disciplinas/{disciplina_id}/status")
	public String atualizarStatus(@PathVariable("disciplina_id") Long id, @RequestParam Boolean s) {
		Disciplina disciplina = disciplinaService.consultar(id);
		
		if (s instanceof Boolean) {
			disciplina.setStatus(s);
			disciplinaService.salvarDisciplina(disciplina);
		}
		
		return "redirect:/disciplinas";
	}
	
	//////////////////EDITAR //////////////////
	@GetMapping("/disciplinas/{disciplina_id}/editar")
	public String editarDisciplina(Model model, @PathVariable("disciplina_id") Long id) {
		Disciplina disciplina = disciplinaService.consultar(id);
		
		model.addAttribute("disciplina", disciplina);
		
		return "disciplinas/cadastro";
	}	
}
