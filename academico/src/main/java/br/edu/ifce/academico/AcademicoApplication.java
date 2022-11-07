package br.edu.ifce.academico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.ifce.academico.model.Curso;
import br.edu.ifce.academico.model.Disciplina;
import br.edu.ifce.academico.model.MatrizCurricular;
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
		/*Curso c = new Curso();
		c.setNome("Ciência da Computação");
		
		cursos.save(c);
		
		Disciplina d = new Disciplina();
		d.setNome("Tópicos em Java para Web");
		d.setSigla("tjw");
		
		disciplinas.save(d);
		
		Disciplina d2 = new Disciplina();
		d2.setNome("Cálculo Numérico");
		d2.setSigla("cnum");
		
		disciplinas.save(d2);*/
	}

}
