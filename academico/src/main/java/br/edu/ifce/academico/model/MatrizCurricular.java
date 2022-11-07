package br.edu.ifce.academico.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class MatrizCurricular {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String codigo;
	
	@Temporal(TemporalType.DATE)
	private Date dataMatriz;
	
	private boolean status;
	
	@OneToOne
	private Curso curso;
	
	@ManyToMany
	@JoinTable(name = "matriz_curricular_disciplina",
			joinColumns = @JoinColumn(name = "matriz_curricular_id"),
			inverseJoinColumns = @JoinColumn(name = "disciplina_id"))
	private List<Disciplina> disciplinas;
	
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

	public Date getDataMatriz() {
		return dataMatriz;
	}

	public void setDataMatriz(Date dataMatriz) {
		this.dataMatriz = dataMatriz;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
}
