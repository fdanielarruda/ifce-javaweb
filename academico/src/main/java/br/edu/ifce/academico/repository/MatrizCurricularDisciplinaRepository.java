package br.edu.ifce.academico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifce.academico.model.MatrizCurricular;
import br.edu.ifce.academico.model.MatrizCurricularDisciplina;

@Repository
public interface MatrizCurricularDisciplinaRepository extends JpaRepository<MatrizCurricularDisciplina, Long> {

	List<MatrizCurricularDisciplina> findByMatrizCurricular(MatrizCurricular matrizCurricular);
}
