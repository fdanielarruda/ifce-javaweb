package br.edu.ifce.academico.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifce.academico.model.Disciplina;
import br.edu.ifce.academico.repository.DisciplinaRepository;

@Service
public class DisciplinaService {

	@Autowired
	DisciplinaRepository disciplinaRepository;
	
	public List<Disciplina> listarDisciplinas() {
		List<Disciplina> d = disciplinaRepository.findAll();
		return d;
	}
	
}
