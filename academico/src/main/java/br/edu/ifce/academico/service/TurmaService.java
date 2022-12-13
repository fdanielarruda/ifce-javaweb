package br.edu.ifce.academico.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifce.academico.model.Aluno;
import br.edu.ifce.academico.model.Curso;
import br.edu.ifce.academico.model.Disciplina;
import br.edu.ifce.academico.model.Nota;
import br.edu.ifce.academico.model.Turma;
import br.edu.ifce.academico.repository.NotasRepository;
import br.edu.ifce.academico.repository.TurmaRepository;

@Service
public class TurmaService {

	@Autowired
	TurmaRepository turmaRepository;
	
	@Autowired
	NotasService notasService;
	
	@Autowired
	NotasRepository notasRepository;
	
	public List<Turma> listarTurmas() {
		return turmaRepository.findAll();
	}
	
	public Turma consultarTurma(Long id) {
		return turmaRepository.findById(id).get();
	}
	
	public List<Turma> consultarTurmasPorCursoDisciplina(Curso curso, Disciplina disciplina) {
		return turmaRepository.findByCursoAndDisciplina(curso, disciplina);
	}
	
	public void deletarTurma(Turma turma) {
		turmaRepository.delete(turma);
	}
	
	public void salvarTurma(Turma turma) {
		turmaRepository.save(turma);
	}
	
	// VALIDAR
	public void adicionarAluno(Turma turma, Aluno aluno) {
		Nota n = new Nota();
		
		n.setAluno(aluno);
		n.setTurma(turma);
		
		notasService.save(n);
	}

	// VALIDAR
	public void removerAluno(Turma turma, Aluno aluno) {
		List<Nota> turma_aluno = notasRepository.findByTurmaAndAluno(turma, aluno);
		
		for (int i=0; i<turma_aluno.size(); i++) 
			notasRepository.delete(turma_aluno.get(i));
	}
}
