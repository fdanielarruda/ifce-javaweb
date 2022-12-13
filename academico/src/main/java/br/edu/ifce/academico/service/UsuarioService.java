package br.edu.ifce.academico.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifce.academico.model.Usuario;
import br.edu.ifce.academico.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository;

	public Usuario buscarLogin(String email) {
		return usuarioRepository.findLikeByEmail(email);
	}
	
	public void salvar(Usuario usuario) {
		usuarioRepository.save(usuario);
	}
	public Long count() {
		return usuarioRepository.count();
	}
}
