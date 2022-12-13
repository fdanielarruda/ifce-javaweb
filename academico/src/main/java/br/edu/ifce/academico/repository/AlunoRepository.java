package br.edu.ifce.academico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifce.academico.dto.AlunoNotas;
import br.edu.ifce.academico.model.Aluno;
import br.edu.ifce.academico.model.Curso;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
	
	List<Aluno> findByCursoAndNomeContainingOrCursoAndMatriculaContaining(Curso curso, String nome, Curso curso2, String matricula);

	@Query(value = "SELECT a.id as id, a.matricula as matricula, a.nome as nome, ta.n1 as n1, ta.n2 as n2, ta.af as af "
			+ "FROM aluno a "
			+ "INNER JOIN turma_alunos ta ON ta.aluno_id = a.id "
			+ "WHERE ta.turma_id = :turma_id", nativeQuery = true)
	List<AlunoNotas> getAlunosNotasDaTurma(Long turma_id);
}
