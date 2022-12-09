package br.edu.ifce.academico.dto;

import java.util.List;

import br.edu.ifce.academico.model.Disciplina;

public class MatrizCurricularDto {
	
	private Long id;
	
	private String codigo;
	
	private String dataMatriz;
	
	private String disciplinas;
	
	private List<Disciplina> todasDisciplinas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDataMatriz() {
		return dataMatriz;
	}

	public void setDataMatriz(String dataMatriz) {
		this.dataMatriz = dataMatriz;
	}

	public String getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(String disciplinas) {
		this.disciplinas = disciplinas;
	}

	public List<Disciplina> getTodasDisciplinas() {
		return todasDisciplinas;
	}

	public void setTodasDisciplinas(List<Disciplina> todasDisciplinas) {
		this.todasDisciplinas = todasDisciplinas;
	}
}
