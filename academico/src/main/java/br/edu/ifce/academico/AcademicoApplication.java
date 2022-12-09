package br.edu.ifce.academico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.ifce.academico.repository.CursoRepository;
import br.edu.ifce.academico.repository.DisciplinaRepository;
import br.edu.ifce.academico.repository.MatrizCurricularRepository;

@SpringBootApplication
public class AcademicoApplication implements CommandLineRunner {

	@Autowired
	CursoRepository cursos;
	
	@Autowired
	DisciplinaRepository disciplinas;

	@Autowired
	MatrizCurricularRepository matrizes;
	
	public static void main(String[] args) {
		SpringApplication.run(AcademicoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}

}
