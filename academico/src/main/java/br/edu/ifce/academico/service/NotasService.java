package br.edu.ifce.academico.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifce.academico.model.Aluno;
import br.edu.ifce.academico.model.Nota;
import br.edu.ifce.academico.model.Turma;
import br.edu.ifce.academico.repository.NotasRepository;

@Service
public class NotasService {

	@Autowired
	NotasRepository notasRepository;
	
	public List<Nota> consultarPorTurma(Turma turma) {
		return notasRepository.findByTurma(turma);
	}
	
	public Nota consultarPorAlunoTurma(Aluno aluno, Turma turma) {
		return notasRepository.findFirstByAlunoAndTurma(aluno, turma);
	}
	
	public void save(Nota nota) {
		notasRepository.save(nota);
	}
}
