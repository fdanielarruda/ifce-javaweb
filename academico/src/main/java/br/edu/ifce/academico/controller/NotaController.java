package br.edu.ifce.academico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.ifce.academico.dto.AlunoNotasDto;
import br.edu.ifce.academico.model.Aluno;
import br.edu.ifce.academico.model.Curso;
import br.edu.ifce.academico.model.Nota;
import br.edu.ifce.academico.model.Turma;
import br.edu.ifce.academico.service.AlunoService;
import br.edu.ifce.academico.service.CursoService;
import br.edu.ifce.academico.service.NotasService;
import br.edu.ifce.academico.service.TurmaService;

@Controller
public class NotaController {
	
	@Autowired
	NotasService notasService;
	
	@Autowired
	CursoService cursoService;
	
	@Autowired
	AlunoService alunoService;

	@Autowired
	TurmaService turmaService;
	
	@GetMapping("/notas")
	public String index(Model model) {
		List<Curso> cursos = cursoService.listarCursos();
		
		model.addAttribute("cursos", cursos);
		
		return "notas/nota";
	}
	
	//////////////////ROTAS APENAS PARA CONSULTA FORA DO TH //////////////////
	@GetMapping("/app/notas/salvar")
	public ResponseEntity<AlunoNotasDto> appAlunos(@RequestParam Long aluno_id, @RequestParam Long turma_id, @RequestParam Double n1, @RequestParam Double n2, Double af) {
		System.out.println("salvar");
		
		Aluno a = alunoService.consultarAluno(aluno_id);
		Turma t = turmaService.consultarTurma(turma_id);
		
		Nota n = notasService.consultarPorAlunoTurma(a, t);
		
		if (n != null) {
			n.setN1(n1);
			n.setN2(n2);
			n.setAf(af);
			
			notasService.save(n);
			AlunoNotasDto an_dto = new AlunoNotasDto(aluno_id, a.getNome(), a.getMatricula(), n.getN1(), n.getN2(), n.getAf(), turma_id);
			return ResponseEntity.ok(an_dto);
		} else {
			Nota no = new Nota();
			
			no.setAluno(a);
			no.setTurma(t);
			no.setN1(n1);
			no.setN2(n2);
			no.setAf(af);
			
			notasService.save(no);
			AlunoNotasDto an_dto = new AlunoNotasDto(aluno_id, a.getNome(), a.getMatricula(), no.getN1(), no.getN2(), no.getAf(), turma_id);
			return ResponseEntity.ok(an_dto);
		}
	}
}
