package br.edu.ifce.academico.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifce.academico.model.MatrizCurricular;
import br.edu.ifce.academico.model.MatrizCurricularDisciplina;
import br.edu.ifce.academico.repository.MatrizCurricularDisciplinaRepository;

@Service
public class MatrizCurricularDisciplinaService {
	
	@Autowired
	MatrizCurricularDisciplinaRepository matrizCurricularDisciplinaRepository;
	
	public void salvar(MatrizCurricularDisciplina matriz) {
		matrizCurricularDisciplinaRepository.save(matriz);
	}
	
	public List<MatrizCurricularDisciplina> consultarPorMatriz(MatrizCurricular matriz) {
		return matrizCurricularDisciplinaRepository.findByMatrizCurricular(matriz);
	}
	
}
