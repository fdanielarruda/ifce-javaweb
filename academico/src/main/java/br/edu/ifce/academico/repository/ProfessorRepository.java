package br.edu.ifce.academico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifce.academico.model.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long>{
	
	List<Professor> findByStatus(Boolean status);
}
