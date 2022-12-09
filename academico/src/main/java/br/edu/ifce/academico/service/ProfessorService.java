package br.edu.ifce.academico.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifce.academico.model.Professor;
import br.edu.ifce.academico.repository.ProfessorRepository;

@Service
public class ProfessorService {

	@Autowired
	ProfessorRepository professorRepository;
	
	public List<Professor> listarProfessores() {
		return professorRepository.findAll();
	}
	
	public List<Professor> listarProfessoresAtivos() {
		return professorRepository.findByStatus(true);
	}
	
	public Professor consultar(Long id) {
		return professorRepository.findById(id).get();
	}
	
	public void salvarProfessor(Professor professor) {
		professorRepository.save(professor);
	}
}
