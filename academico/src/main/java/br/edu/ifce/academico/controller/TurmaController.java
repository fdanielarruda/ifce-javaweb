package br.edu.ifce.academico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.ifce.academico.model.Turma;
import br.edu.ifce.academico.service.TurmaService;

@Controller
public class TurmaController {

	@Autowired
	TurmaService turmaService;
	
	@GetMapping({"/turmas"})
	public String index(Model model) {
		List<Turma> turmas = turmaService.listarTurmas();
		
		model.addAttribute("turmas", turmas);
		
		return "turma";
	}
	
	@GetMapping({"/turmas/{id_turma}/alunos"})
	public String alunosByTurmas(Model model, @PathVariable(value = "id_turma") Long id_turma) {
		Turma turma = turmaService.consultarTurma(id_turma);
		
		model.addAttribute("turma", turma);
		
		return "turma_aluno";
	}
	
}
