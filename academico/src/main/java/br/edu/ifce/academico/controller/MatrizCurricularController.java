package br.edu.ifce.academico.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;

import br.edu.ifce.academico.dto.MatrizCurricularDto;
import br.edu.ifce.academico.model.Curso;
import br.edu.ifce.academico.model.Disciplina;
import br.edu.ifce.academico.model.MatrizCurricular;
import br.edu.ifce.academico.model.MatrizCurricularDisciplina;
import br.edu.ifce.academico.service.CursoService;
import br.edu.ifce.academico.service.DisciplinaService;
import br.edu.ifce.academico.service.MatrizCurricularDisciplinaService;
import br.edu.ifce.academico.service.MatrizCurricularService;

@Controller
public class MatrizCurricularController {
	
	@Autowired
	MatrizCurricularService matrizService;
	
	@Autowired
	CursoService cursoService;
	
	@Autowired
	DisciplinaService disciplinaService;
	
	@Autowired
	MatrizCurricularService matrizCurricularService;
	
	@Autowired
	MatrizCurricularDisciplinaService matrizCurricularDisciplinaService;
	
	@GetMapping("/cursos/{curso_id}/matrizes")
	public String listMatrizByCursos(Model model, @PathVariable(value = "curso_id") Long curso_id) {
		List<MatrizCurricular> matrizes = matrizService.listarMatrizDoCurso(curso_id);
		Curso curso = cursoService.consultar(curso_id); 
		
		model.addAttribute("matrizes", matrizes);
		model.addAttribute("curso", curso);
		
		return "matrizes/matriz";
	}
	
	@GetMapping("/cursos/{curso_id}/matrizes/cadastrar")
	public String cadastrarMatriz(Model model, @PathVariable(value = "curso_id") Long curso_id) {
		Curso curso = cursoService.consultar(curso_id);
		
		List<Disciplina> disciplinas = disciplinaService.consultarStatus(true);
		
		model.addAttribute("curso", curso);
		model.addAttribute("disciplinas", disciplinas);
		
		return "matrizes/cadastro";
	}

	@PostMapping("/cursos/{curso_id}/matrizes/salvar")
	public String salvarMatriz(@PathVariable(value = "curso_id") Long curso_id, MatrizCurricularDto matriz) {
		try {
			// Converter String Data
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
			Date dataFormatada = formato.parse(matriz.getDataMatriz()); 
			
			// Curso
			Curso curso = cursoService.consultar(curso_id);
			
			// Matriz Curricular
			MatrizCurricular matriz_curricular = new MatrizCurricular();
			
			matriz_curricular.setCodigo(matriz.getCodigo());
			matriz_curricular.setDataMatriz(dataFormatada);
			matriz_curricular.setCurso(curso);
			
			// Disciplinas
			Gson gson = new Gson();
			
			@SuppressWarnings("unchecked")
			List<String> disciplinas_json = gson.fromJson(matriz.getDisciplinas(), List.class);
			
			if (disciplinas_json != null) {
				List<Disciplina> disciplinas_escolhidas = new ArrayList<Disciplina>();

				for (int i=0; i<disciplinas_json.size(); i+=2) {
					// get(i) = id_discplina; get(i + 1) = semestre
					Disciplina d = disciplinaService.consultar(Long.parseLong(disciplinas_json.get(i)));
					
					MatrizCurricularDisciplina mcd = new MatrizCurricularDisciplina();
					
					mcd.setDisciplina(d);
					mcd.setMatrizCurricular(matriz_curricular);
					mcd.setSemestre(Integer.parseInt(disciplinas_json.get(i+1)));
					
					matrizCurricularDisciplinaService.salvar(mcd);
				}
				
				matriz_curricular.setDisciplinas(disciplinas_escolhidas);
			}
			
			matrizService.salvarMatriz(matriz_curricular);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return "redirect:/cursos/" + curso_id + "/matrizes";
	}
	
	////////////////// EDITAR //////////////////
	@GetMapping("/matrizes/{matriz_id}/status")
	public String atualizarStatus(@PathVariable("matriz_id") Long id, @RequestParam Boolean s) {
		MatrizCurricular matriz = matrizService.consultarMatriz(id);
		
		if (s instanceof Boolean) {
			matriz.setStatus(s);
			matrizService.salvarMatriz(matriz);
		}
		
		Curso curso = matriz.getCurso();
		Long curso_id = curso.getId();
		
		return "redirect:/cursos/" + curso_id + "/matrizes";
	}
	
	@GetMapping("/matrizes/{matriz_id}")
	public String verMatrizes(Model model, @PathVariable(value = "matriz_id") Long matriz_id) {
		MatrizCurricular matriz = matrizService.consultarMatriz(matriz_id);
		Curso curso = matriz.getCurso();
		
		List<MatrizCurricularDisciplina> matriz_disciplinas = matrizCurricularDisciplinaService.consultarPorMatriz(matriz);
		
		model.addAttribute("matriz", matriz);
		model.addAttribute("curso", curso);
		model.addAttribute("matriz_disciplinas", matriz_disciplinas);
		
		return "matrizes/detalhes";
	}
	
	//////////////////ROTAS APENAS PARA CONSULTA FORA DO TH //////////////////
	@GetMapping("/app/matrizes")
	public ResponseEntity<List<MatrizCurricularDto>> appMatrizes(@RequestParam("curso_id") Long curso_id) {
		Curso c = cursoService.consultar(curso_id);
		List<MatrizCurricularDto> matrizes = new ArrayList<MatrizCurricularDto>();
		
		for (int i=0; i<c.getMatrizCurricular().size(); i++) {
			MatrizCurricular mc = c.getMatrizCurricular().get(i);
			
			if (mc.getStatus()) {	
				MatrizCurricularDto mcd = new MatrizCurricularDto();
				
				mcd.setId(mc.getId());
				mcd.setCodigo(mc.getCodigo());
				mcd.setTodasDisciplinas(mc.getDisciplinas());
				
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		        String dataFormatada = dateFormat.format(mc.getDataMatriz());
		        
				mcd.setDataMatriz(dataFormatada);
				
				matrizes.add(mcd);
			}
		}
		
		return ResponseEntity.ok(matrizes);
	}
}
