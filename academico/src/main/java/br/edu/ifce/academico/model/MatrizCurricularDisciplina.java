package br.edu.ifce.academico.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "matriz_curricular_disciplina")
public class MatrizCurricularDisciplina {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "matriz_curricular_id")
	private MatrizCurricular matrizCurricular;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "disciplina_id")
	private Disciplina disciplina;
	
	private Integer semestre;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MatrizCurricular getMatrizCurricular() {
		return matrizCurricular;
	}

	public void setMatrizCurricular(MatrizCurricular matrizCurricular) {
		this.matrizCurricular = matrizCurricular;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Integer getSemestre() {
		return semestre;
	}

	public void setSemestre(Integer semestre) {
		this.semestre = semestre;
	}
}
