package br.edu.ifce.academico.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifce.academico.model.Aluno;
import br.edu.ifce.academico.model.Curso;
import br.edu.ifce.academico.model.Disciplina;
import br.edu.ifce.academico.model.Turma;
import br.edu.ifce.academico.repository.TurmaRepository;

@Service
public class TurmaService {

	@Autowired
	TurmaRepository turmaRepository;
	
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
	
	public void adicionarAluno(Turma turma, Aluno aluno) {
		List<Aluno> alunos_atuais = turma.getAlunos();
		alunos_atuais.add(aluno);
		
		turma.setAlunos(alunos_atuais);
		
		turmaRepository.save(turma);
	}
	
	public void removerAluno(Turma turma, Aluno aluno) {
		List<Aluno> alunos_atuais = turma.getAlunos();

		for (Aluno a: alunos_atuais) {
			if (a.getId().equals(aluno.getId())) {
				alunos_atuais.remove(a);
				break;
			}
		}
		
		turma.setAlunos(alunos_atuais);
		
		turmaRepository.save(turma);
	}
}
