package br.edu.ifce.academico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.edu.ifce.academico.model.Aluno;
import br.edu.ifce.academico.service.AlunoService;

@Controller
public class AlunoController {

	@Autowired
	AlunoService alunoService;
		
	@GetMapping({"/alunos"})
	public String index(Model model) {
		List<Aluno> alunos = alunoService.listarAlunos();
		
		model.addAttribute("alunos", alunos);
		
		return "aluno";
	}
	
}
