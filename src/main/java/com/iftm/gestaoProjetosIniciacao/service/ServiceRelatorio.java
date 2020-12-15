package com.iftm.gestaoProjetosIniciacao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iftm.gestaoProjetosIniciacao.model.Relatorio;
import com.iftm.gestaoProjetosIniciacao.repository.RelatorioDAO;

@Service
public class ServiceRelatorio {

	@Autowired
	private RelatorioDAO relatorioDAO;

	public Relatorio salvar(final Relatorio projeto) {
		return relatorioDAO.save(projeto);
	}
}