package br.edu.ifce.academico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifce.academico.model.Curso;
import br.edu.ifce.academico.model.Disciplina;
import br.edu.ifce.academico.model.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {

	List<Turma> findByCursoAndDisciplina(Curso curso, Disciplina disciplina);
}
