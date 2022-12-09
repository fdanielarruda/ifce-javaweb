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
		return alunoRepository.findAll();
	}
	
	public List<Aluno> listarAlunosPorNomeOuMatricula(String pesquisa) { 
		return alunoRepository.findByNomeContainingOrMatriculaContaining(pesquisa, pesquisa);
	}
	
	public Aluno consultarAluno(Long id_aluno) {
		return alunoRepository.findById(id_aluno).get();
	}
	
	public void salvarAluno(Aluno aluno) {
		alunoRepository.save(aluno);
	}
	
	public void removerAluno(Aluno aluno) {
		alunoRepository.delete(aluno);
	}
}
