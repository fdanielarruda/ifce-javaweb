package br.edu.ifce.academico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifce.academico.model.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
	
	List<Aluno> findByNomeContainingOrMatriculaContaining(String nome, String matricula);
	
}
