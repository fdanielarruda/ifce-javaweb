package br.edu.ifce.academico.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifce.academico.model.Curso;
import br.edu.ifce.academico.model.MatrizCurricular;
import br.edu.ifce.academico.repository.CursoRepository;

@Service
public class CursoService {
	@Autowired
	CursoRepository cursoRepository;
	
	@Autowired
	MatrizCurricularDisciplinaService matrizCurricularDisciplinaService;
	
	@Autowired
	MatrizCurricularService matrizCurricularService;

	public Curso consultar(Long id) {
		return cursoRepository.findById(id).get();
	}
	
	public List<Curso> listarCursos() {
		return cursoRepository.findAll();
	}

	public void salvarCurso(Curso curso) {
		cursoRepository.save(curso);
	}
	
	public void deletarCurso(Long id) {
		Curso c = cursoRepository.findById(id).get();
		
		// DELETANDO AS MATRIZES CURRICULARES DO CURSO
		List<MatrizCurricular> matrizes = c.getMatrizCurricular();
		
		for (int i=0; i<matrizes.size(); i++) {
			matrizCurricularService.remover(matrizes.get(i));			
		}

		cursoRepository.delete(c);
	}
}
