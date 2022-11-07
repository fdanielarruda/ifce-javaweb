package br.edu.ifce.academico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.edu.ifce.academico.model.Curso;
import br.edu.ifce.academico.service.CursoService;

@Controller
public class CursoController {
	
	@Autowired
	CursoService cursoService;
	
	@GetMapping({"/cursos"})
	public String index(Model model) {
		List<Curso> cursos = cursoService.listarCursos();
		
		model.addAttribute("cursos", cursos);
		
		return "curso";
	}
	
	@PostMapping({"/cursos"})
	public String store(@RequestBody Curso curso) {
		cursoService.salvarCurso(curso.getNome());
		return "index";
	}
}
