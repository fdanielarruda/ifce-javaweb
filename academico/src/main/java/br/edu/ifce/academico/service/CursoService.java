package br.edu.ifce.academico.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifce.academico.model.Curso;
import br.edu.ifce.academico.repository.CursoRepository;

@Service
public class CursoService {
	@Autowired
	CursoRepository cursoRepository;

	public Curso consultar(Long id) {
		Curso c = cursoRepository.findById(id).get();
		return c;
	}
	
	public List<Curso> listarCursos() {
		List<Curso> cursos = cursoRepository.findAll();
		return cursos;
	}
	
	public void salvarCurso(String nome) {
		Curso c = new Curso();
		c.setNome(nome);
		cursoRepository.save(c);
		
		System.out.println("Curso Cadastrado");
	}
}
