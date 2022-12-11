package br.edu.ifce.academico.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.ifce.academico.dto.TurmaDto;
import br.edu.ifce.academico.model.Aluno;
import br.edu.ifce.academico.model.Curso;
import br.edu.ifce.academico.model.Disciplina;
import br.edu.ifce.academico.model.Turma;
import br.edu.ifce.academico.service.AlunoService;
import br.edu.ifce.academico.service.CursoService;
import br.edu.ifce.academico.service.DisciplinaService;
import br.edu.ifce.academico.service.TurmaService;

@Controller
public class TurmaController {

	@Autowired
	TurmaService turmaService;
	
	@Autowired
	DisciplinaService disciplinaService;
	
	@Autowired
	CursoService cursoService;
	
	@Autowired
	AlunoService alunoService;
	
	@GetMapping("/turmas")
	public String index(Model model) {
		List<Turma> turmas = turmaService.listarTurmas();
		
		model.addAttribute("turmas", turmas);
		
		return "turmas/turma";
	}
	
	@GetMapping("/turmas/{id_turma}/alunos")
	public String alunosByTurmas(Model model, @PathVariable(value = "id_turma") Long id_turma) {
		Turma turma = turmaService.consultarTurma(id_turma);
		
		model.addAttribute("turma", turma);
		
		return "turmas/alunos";
	}
	
	@GetMapping("/turmas/{id_turma}/deletar")
	public String deletarTurma(Model model, @PathVariable("id_turma") Long id_turma) {
		turmaService.deletarTurma(turmaService.consultarTurma(id_turma));
		
		return "redirect:/turmas";
	}
	
	@GetMapping("/turmas/cadastrar")
	public String cadastrarTurma(Model model) {
		List<Curso> cursos = cursoService.listarCursos();
		
		model.addAttribute("cursos", cursos);
		
		return "turmas/cadastro";
	}
	
	@PostMapping("/turmas/salvar")
	public String salvarTurma(Turma turma) {		
		turmaService.salvarTurma(turma);
		return "redirect:/turmas";
	}
	
	@PostMapping("/turmas/{id_turma}/alunos/salvar")
	public String salvarAlunoNaTurma(@PathVariable("id_turma") Long id_turma, Aluno aluno) {		
		Turma turma = turmaService.consultarTurma(id_turma);
		turmaService.adicionarAluno(turma, aluno);
		
		return "redirect:/turmas/" + id_turma + "/alunos";
	}
	
	@GetMapping("/turmas/{id_turma}/alunos/{id_aluno}/remover")
	public String salvarAlunoNaTurma(@PathVariable("id_turma") Long id_turma, @PathVariable("id_aluno") Long id_aluno) {		
		Turma turma = turmaService.consultarTurma(id_turma);
		Aluno aluno = alunoService.consultarAluno(id_aluno);
		
		turmaService.removerAluno(turma, aluno);
		
		return "redirect:/turmas/" + id_turma + "/alunos";
	}
	
	//////////////////ROTAS APENAS PARA CONSULTA FORA DO TH //////////////////
	@GetMapping("/app/turmas")
	public ResponseEntity<List<TurmaDto>> listarTurmasPorCursoDisciplina(@RequestParam("curso_id") Long curso_id, @RequestParam("disciplina_id") Long disciplina_id) {
		Curso curso = cursoService.consultar(curso_id);
		Disciplina disciplina = disciplinaService.consultar(disciplina_id);
		
		List<Turma> turmas = turmaService.consultarTurmasPorCursoDisciplina(curso, disciplina);
		List<TurmaDto> td = new ArrayList<TurmaDto>();
		
		for (int i=0; i<turmas.size(); i++) {
			TurmaDto t = new TurmaDto();
			Turma t_ = turmas.get(i);
			
			t.setId(t_.getId());
			t.setAnoLetivo(t_.getAnoLetivo());
			t.setSemestre(t_.getSemestre());
			
			td.add(t);
		}
		
		return ResponseEntity.ok(td);
	}
}
