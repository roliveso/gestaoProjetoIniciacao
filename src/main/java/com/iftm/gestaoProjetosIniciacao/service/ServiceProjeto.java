package com.iftm.gestaoProjetosIniciacao.service;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iftm.gestaoProjetosIniciacao.exceptions.CriptoExistException;
import com.iftm.gestaoProjetosIniciacao.exceptions.ExceptionService;
import com.iftm.gestaoProjetosIniciacao.exceptions.UserExistsException;
import com.iftm.gestaoProjetosIniciacao.model.Projeto;
import com.iftm.gestaoProjetosIniciacao.repository.ProjetoDAO;
import com.iftm.gestaoProjetosIniciacao.util.Util;

@Service
public class ServiceProjeto {

	@Autowired
	private ProjetoDAO projetoDAO;

	public void salvarProjeto(Projeto projeto) throws Exception {
		try {
			if (projetoDAO.findByUser(projeto.getUser()) != null) {
				throw new UserExistsException("Já existe um usuario cadastrado para: " + projeto.getUser());
			}
			projeto.setSenha(Util.md5(projeto.getSenha()));
		} catch (NoSuchAlgorithmException e) {
			throw new CriptoExistException("Erro na criptografia da senha");
		}
		projetoDAO.save(projeto);
	}

	public Projeto loginProjeto(String user, String senha) throws ExceptionService {
		Projeto projetoLogin = projetoDAO.buscarLogin(user, senha);

		return projetoLogin;
	}
}