package br.edu.ifce.academico.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifce.academico.model.Aluno;
import br.edu.ifce.academico.repository.AlunoRepository;

@Service
public class AlunoService {

	@Autowired
	AlunoRepository alunoRepository;
	
	public List<Aluno> listarAlunos() {
		List<Aluno> a = alunoRepository.findAll();
		return a;
	}
	
}
