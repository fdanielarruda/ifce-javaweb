package br.edu.ifce.academico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifce.academico.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findLikeByEmail(String name);
	
}
