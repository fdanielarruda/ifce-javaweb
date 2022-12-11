package br.edu.ifce.academico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifce.academico.model.Aluno;
import br.edu.ifce.academico.model.Nota;
import br.edu.ifce.academico.model.Turma;

@Repository
public interface NotasRepository extends JpaRepository<Nota, Long> {
	
	List<Nota> findByTurma(Turma turma);
	
	Nota findFirstByAlunoAndTurma(Aluno aluno, Turma turma);
}
