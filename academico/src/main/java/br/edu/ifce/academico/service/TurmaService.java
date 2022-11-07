package br.edu.ifce.academico.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifce.academico.model.Turma;
import br.edu.ifce.academico.repository.TurmaRepository;

@Service
public class TurmaService {

	@Autowired
	TurmaRepository turmaRepository;
	
	public List<Turma> listarTurmas() {
		List<Turma> t = turmaRepository.findAll();
		return t;
	}
	
	public Turma consultarTurma(Long id) {
		Turma t = turmaRepository.findById(id).get();
		return t;
	}
	
}
