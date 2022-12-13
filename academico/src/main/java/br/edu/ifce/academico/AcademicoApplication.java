package br.edu.ifce.academico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.ifce.academico.model.Usuario;
import br.edu.ifce.academico.service.UsuarioService;

@SpringBootApplication
public class AcademicoApplication implements CommandLineRunner {

	@Autowired
	UsuarioService usuarioService;
	
	public static void main(String[] args) {
		SpringApplication.run(AcademicoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (usuarioService.count() == 0) {
			Usuario u = new Usuario();
			
			u.setNome("admin");
			u.setEmail("admin@gmail.com");
			u.setSenha("$2a$12$M2KxRxp8HqE4CgX63huNaefTZ8RKxuoocAzU9NfGR12jTX.Nnh0s2");
			
			usuarioService.salvar(u);
		}
	}

}
