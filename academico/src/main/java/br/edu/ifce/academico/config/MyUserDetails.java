package br.edu.ifce.academico.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.edu.ifce.academico.model.Usuario;
import br.edu.ifce.academico.service.UsuarioService;

public class MyUserDetails implements UserDetailsService {

	@Autowired
	UsuarioService usuarioService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario u = usuarioService.buscarLogin(username);
		
		if (u != null) {
			String[] authorities = new String[1];
			authorities[0] = "ROLE_USER";
			
			return new User(u.getNome(), u.getSenha(), AuthorityUtils.createAuthorityList(authorities));	
		} else {
			throw new UsernameNotFoundException("Usuário não encontrado");
		}
	}

}
