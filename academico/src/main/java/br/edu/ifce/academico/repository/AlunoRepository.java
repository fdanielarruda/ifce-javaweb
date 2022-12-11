package br.edu.ifce.academico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifce.academico.dto.AlunoNotas;
import br.edu.ifce.academico.model.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
	
	List<Aluno> findByNomeContainingOrMatriculaContaining(String nome, String matricula);

	@Query(value = "SELECT a.id as id, a.matricula as matricula, a.nome as nome, n.n1 as n1, n.n2 as n2, n.af as af "
			+ "FROM aluno a "
			+ "INNER JOIN turma_alunos ta ON ta.alunos_id = a.id "
			+ "LEFT JOIN nota n ON n.aluno_id = ta.alunos_id AND n.turma_id = ta.turmas_id "
			+ "WHERE ta.turmas_id = :turma_id", nativeQuery = true)
	List<AlunoNotas> getAlunosNotasDaTurma(Long turma_id);
}
