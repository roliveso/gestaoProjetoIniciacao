package com.iftm.gestaoProjetosIniciacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iftm.gestaoProjetosIniciacao.model.Relatorio;
import com.iftm.gestaoProjetosIniciacao.repository.RelatorioDAO;

@Service
public class ServiceRelatorio {

	@Autowired
	private RelatorioDAO relatorioDAO;

	public Relatorio salvarProjeto(final Relatorio projeto) throws Exception {
		return relatorioDAO.save(projeto);
	}
}