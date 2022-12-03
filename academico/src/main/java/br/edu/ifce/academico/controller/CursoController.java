package br.edu.ifce.academico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.ifce.academico.model.Curso;
import br.edu.ifce.academico.service.CursoService;

@Controller
public class CursoController {
	
	@Autowired
	CursoService cursoService;
	
	////////////////// LISTAR //////////////////
	@GetMapping("/cursos")
	public String index(Model model) {
		List<Curso> cursos = cursoService.listarCursos();
		
		model.addAttribute("cursos", cursos);
		
		return "cursos/cursos";
	}
	
	////////////////// CADASTRAR //////////////////
	@GetMapping("/cursos/cadastrar")
	public String cadastrar(Curso curso) {
		return "cursos/cadastro";
	}
	
	@PostMapping("/cursos/salvar")
	public String salvar(Curso curso) {
		cursoService.salvarCurso(curso);
		
		return "redirect:/cursos";
	}
	
	////////////////// EDITAR //////////////////
	@GetMapping("/cursos/{id}/editar")
	public String editar(Model model, @PathVariable("id") Long id) {
		Curso curso = cursoService.consultar(id);
		model.addAttribute("curso", curso);
		
		return "cursos/cadastro";
	}
	
	@PostMapping("/cursos/atualizar")
	public String atualizar(Curso curso) {
		cursoService.salvarCurso(curso);
		
		return "redirect:/cursos";
	}
	
	////////////////// DELETAR //////////////////
	@GetMapping("/cursos/{id}/deletar")
	public String deletar(@PathVariable("id") Long id) {
		cursoService.deletarCurso(id);
		
		return "redirect:/cursos";
	}
}
