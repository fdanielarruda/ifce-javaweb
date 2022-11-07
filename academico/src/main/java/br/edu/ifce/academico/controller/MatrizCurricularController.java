package br.edu.ifce.academico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.ifce.academico.model.Curso;
import br.edu.ifce.academico.model.MatrizCurricular;
import br.edu.ifce.academico.service.CursoService;
import br.edu.ifce.academico.service.MatrizCurricularService;

@Controller
public class MatrizCurricularController {
	
	@Autowired
	MatrizCurricularService matrizService;
	
	@Autowired
	CursoService cursoService;
	
	@GetMapping({"/matriz/{curso_id}"})
	public String listMatrizByCursos(Model model, @PathVariable(value = "curso_id") Long curso_id) {
		List<MatrizCurricular> matrizes = matrizService.listarMatrizDoCurso(curso_id);
		Curso curso = cursoService.consultar(curso_id); 
		
		model.addAttribute("matrizes", matrizes);
		model.addAttribute("curso", curso);
		
		return "matriz";
	}
	
}
