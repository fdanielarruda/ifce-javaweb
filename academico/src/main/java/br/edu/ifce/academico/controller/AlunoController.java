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

import br.edu.ifce.academico.dto.AlunoDto;
import br.edu.ifce.academico.dto.AlunoNotas;
import br.edu.ifce.academico.dto.AlunoNotasDto;
import br.edu.ifce.academico.model.Aluno;
import br.edu.ifce.academico.service.AlunoService;
import br.edu.ifce.academico.service.NotasService;
import br.edu.ifce.academico.service.TurmaService;

@Controller
public class AlunoController {

	@Autowired
	AlunoService alunoService;

	@Autowired
	TurmaService turmaService;
	
	@Autowired
	NotasService notasService;
		
	@GetMapping("/alunos")
	public String index(Model model) {
		List<Aluno> alunos = alunoService.listarAlunos();
		
		model.addAttribute("alunos", alunos);
		
		return "alunos/aluno";
	}
	
	@GetMapping("/alunos/cadastrar")
	public String cadastrar() {
		return "alunos/cadastro";
	}
	
	@PostMapping("/alunos/salvar")
	public String salvar(Aluno aluno) {
		alunoService.salvarAluno(aluno);
	
		return "redirect:/alunos";
	}
	
	@GetMapping("/alunos/{aluno_id}/editar")
	public String editar(Model model, @PathVariable("aluno_id") Long aluno_id) {
		Aluno aluno = alunoService.consultarAluno(aluno_id);
	
		model.addAttribute("aluno", aluno);
		
		return "alunos/cadastro";
	}
	
	@GetMapping("/alunos/{aluno_id}/remover")
	public String remover(@PathVariable("aluno_id") Long aluno_id) {
		Aluno aluno = alunoService.consultarAluno(aluno_id);
		alunoService.removerAluno(aluno);
		
		return "redirect:/alunos";
	}
	
	//////////////////ROTAS APENAS PARA CONSULTA FORA DO TH //////////////////
	@GetMapping("app/alunos/pesquisa")
	public ResponseEntity<List<AlunoDto>> appAlunos(@RequestParam String pesquisa) {
		List<Aluno> aluno = alunoService.listarAlunosPorNomeOuMatricula(pesquisa);
		List<AlunoDto> ad = new ArrayList<AlunoDto>();
		
		for (int i=0; i<aluno.size(); i++) {
			AlunoDto a = new AlunoDto();
			Aluno a_selecionado = aluno.get(i);
			
			a.setId(a_selecionado.getId());
			a.setMatricula(a_selecionado.getMatricula());
			a.setNome(a_selecionado.getNome());
			
			ad.add(a);
		}

		return ResponseEntity.ok(ad);
	}
	
	@GetMapping("/app/alunos/notas")
	public ResponseEntity<List<AlunoNotasDto>> listarTurmasPorCursoDisciplina(@RequestParam("turma_id") Long turma_id) {
		List<AlunoNotas> notas_alunos = alunoService.consultarAlunosNotasDaTurma(turma_id);
		List<AlunoNotasDto> alunos = new ArrayList<AlunoNotasDto>();
		
		for (int i=0; i<notas_alunos.size(); i++) {
			AlunoNotas an = notas_alunos.get(i);
			
			AlunoNotasDto an_dto = new AlunoNotasDto(
				an.getId(),
				an.getNome(),
				an.getMatricula(),
				an.getN1() == null ? 0 : an.getN1(),
				an.getN2() == null ? 0 : an.getN2(),
				an.getAf() == null ? 0 : an.getAf(),
				turma_id
			);
			
			alunos.add(an_dto);
		}
		
		return ResponseEntity.ok(alunos);
	}
}
