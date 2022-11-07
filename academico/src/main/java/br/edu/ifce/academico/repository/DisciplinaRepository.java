package br.edu.ifce.academico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifce.academico.model.Disciplina;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {

}
