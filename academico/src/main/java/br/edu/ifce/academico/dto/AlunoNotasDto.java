package br.edu.ifce.academico.dto;

import javax.persistence.Column;

public class AlunoNotasDto extends AlunoDto {
	
	@Column(nullable = true)
	private Double n1;
	
	@Column(nullable = true)
	private Double n2;
	
	@Column(nullable = true)
	private Double af;

	@Column(nullable = true)
	private Double media;
	
	@Column(nullable = true)
	private Double nf;
	
	private Boolean aprovado;
	
	private Long turma_id;
	
	public AlunoNotasDto(Long id, String nome, String matricula, Double n1, Double n2, Double af, Long turma_id) {
		this.setId(id);
		this.setNome(nome);
		this.setMatricula(matricula);
		this.turma_id = turma_id;
		
		this.n1 = n1;
		this.n2 = n2;
		
		if (this.n1 != null || this.n2 != null) {
			Double media = (this.n1 * 2  + this.n2 * 3) / 5;
			
			this.aprovado = true;
			this.media = media;
			
			if (media >= 7) {
				this.nf = Math.round(media * 100.0)/100.0;
			} else {
				this.af = (af == null ? 0 : af);
				this.af = Math.round(this.af * 100.0)/100.0;

				Double media_recuperacao = (media + af) / 2;
				if (media_recuperacao < 5) this.aprovado = false;

				this.nf = Math.round(media_recuperacao * 100.0)/100.0;
			}
		}
	}

	public Double getN1() {
		return n1;
	}

	public void setN1(Double n1) {
		this.n1 = n1;
	}

	public Double getN2() {
		return n2;
	}

	public void setN2(Double n2) {
		this.n2 = n2;
	}

	public Double getAf() {
		return af;
	}

	public void setAf(Double af) {
		this.af = af;
	}

	public Double getNf() {
		return nf;
	}

	public void setNf(Double nf) {
		this.nf = nf;
	}

	public Boolean getAprovado() {
		return aprovado;
	}

	public void setAprovado(Boolean aprovado) {
		this.aprovado = aprovado;
	}

	public Double getMedia() {
		return media;
	}

	public void setMedia(Double media) {
		this.media = media;
	}

	public Long getTurma_id() {
		return turma_id;
	}

	public void setTurma_id(Long turma_id) {
		this.turma_id = turma_id;
	}
}
