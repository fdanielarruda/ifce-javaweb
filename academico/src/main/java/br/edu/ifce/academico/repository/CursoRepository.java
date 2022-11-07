package br.edu.ifce.academico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifce.academico.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

}
