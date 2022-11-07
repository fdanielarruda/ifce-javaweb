package br.edu.ifce.academico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifce.academico.model.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {

}
