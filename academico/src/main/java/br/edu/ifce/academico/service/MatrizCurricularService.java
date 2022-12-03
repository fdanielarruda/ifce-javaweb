package br.edu.ifce.academico.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifce.academico.model.Curso;
import br.edu.ifce.academico.model.MatrizCurricular;
import br.edu.ifce.academico.repository.CursoRepository;
import br.edu.ifce.academico.repository.MatrizCurricularRepository;

@Service
public class MatrizCurricularService {

	@Autowired
	CursoRepository cursoRepository;
	
	@Autowired
	MatrizCurricularRepository matrizRepository;
	
	public List<MatrizCurricular> listarMatrizDoCurso (Long curso_id) {
		Curso _c = cursoRepository.findById(curso_id).get();
		return _c.getMatrizCurricular();
	}
	
	public MatrizCurricular consultarMatriz(Long matriz_id) {
		return matrizRepository.findById(matriz_id).get();
	}
	
	public void salvarMatriz(MatrizCurricular matriz) {
		matrizRepository.save(matriz);
	}
	
}
