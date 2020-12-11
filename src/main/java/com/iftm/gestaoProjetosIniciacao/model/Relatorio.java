package com.iftm.gestaoProjetosIniciacao.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Relatorio {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name="projeto", nullable=false)
	private Projeto projeto;
	
	private String situacaoProjeto;
	
	private String situacaoProjetoJustificativa;
	
	private String cumprimentoCargaHoraria;
	
	private String interesseAtividades;
	
	private String habilidadesDesenvolvidas;
	
	private String outrasInformacoes;
	
	private String resumoAtividades;

	private String comentarios;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public String getSituacaoProjeto() {
		return situacaoProjeto;
	}

	public void setSituacaoProjeto(String situacaoProjeto) {
		this.situacaoProjeto = situacaoProjeto;
	}

	public String getSituacaoProjetoJustificativa() {
		return situacaoProjetoJustificativa;
	}

	public void setSituacaoProjetoJustificativa(String situacaoProjetoJustificativa) {
		this.situacaoProjetoJustificativa = situacaoProjetoJustificativa;
	}

	public String getCumprimentoCargaHoraria() {
		return cumprimentoCargaHoraria;
	}

	public void setCumprimentoCargaHoraria(String cumprimentoCargaHoraria) {
		this.cumprimentoCargaHoraria = cumprimentoCargaHoraria;
	}

	public String getInteresseAtividades() {
		return interesseAtividades;
	}

	public void setInteresseAtividades(String interesseAtividades) {
		this.interesseAtividades = interesseAtividades;
	}

	public String getHabilidadesDesenvolvidas() {
		return habilidadesDesenvolvidas;
	}

	public void setHabilidadesDesenvolvidas(String habilidadesDesenvolvidas) {
		this.habilidadesDesenvolvidas = habilidadesDesenvolvidas;
	}

	public String getOutrasInformacoes() {
		return outrasInformacoes;
	}

	public void setOutrasInformacoes(String outrasInformacoes) {
		this.outrasInformacoes = outrasInformacoes;
	}

	public String getResumoAtividades() {
		return resumoAtividades;
	}

	public void setResumoAtividades(String resumoAtividades) {
		this.resumoAtividades = resumoAtividades;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	
	
	
}