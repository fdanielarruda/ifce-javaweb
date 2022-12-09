package br.edu.ifce.academico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.ifce.academico.model.Professor;
import br.edu.ifce.academico.service.DisciplinaService;
import br.edu.ifce.academico.service.ProfessorService;

@Controller
public class ProfessorController {
	
	@Autowired
	ProfessorService professorService;
	
	@Autowired
	DisciplinaService disciplinaService;
	
	@GetMapping("/professores")
	public String index(Model model) {
		List<Professor> professores = professorService.listarProfessores();
		
		model.addAttribute("professores", professores);
		
		return "professores/professor";
	}
	
	////////////////// CADASTRAR //////////////////
	@GetMapping("/professores/cadastrar")
	public String cadastrarProfessor(Model model) {
		return "professores/cadastro";
	}
	
	@PostMapping("/professores/salvar")
	public String salvar(Professor professor) {
		professorService.salvarProfessor(professor);
	
		return "redirect:/professores";
	}
	
	//////////////////EDITAR //////////////////
	@GetMapping("/professores/{id}/editar")
	public String editar(Model model, @PathVariable("id") Long id) {
		Professor professor = professorService.consultar(id);
		
		model.addAttribute("professor", professor);
		
		return "professores/cadastro";
	}
	
	@PostMapping("/professores/atualizar")
	public String atualizar(Professor professor) {
		professorService.salvarProfessor(professor);
		
		return "redirect:/professores";
	}
	
	@GetMapping("/professores/{professor_id}/status")
	public String atualizarStatus(@PathVariable("professor_id") Long id, @RequestParam Boolean s) {
		Professor professor = professorService.consultar(id);
		
		if (s instanceof Boolean) {
			professor.setStatus(s);
			professorService.salvarProfessor(professor);
		}
		
		return "redirect:/professores";
	}
	
	//////////////////ROTAS APENAS PARA CONSULTA FORA DO TH //////////////////
	@GetMapping("app/professores")
	public ResponseEntity<List<Professor>> appDisciplinas() {
		List<Professor> professor = professorService.listarProfessoresAtivos();
		
		if (professor == null)
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(professor);
	}
}
