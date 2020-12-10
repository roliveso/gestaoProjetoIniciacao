package com.iftm.gestaoProjetosIniciacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iftm.gestaoProjetosIniciacao.model.Projeto;

public interface ProjetoDAO extends JpaRepository<Projeto, Long> {

	@Query("select i from Projeto i where i.email = :email")
	public Projeto findByEmail(String email);

	@Query("select k from Projeto k where k.user = :user")
	public Projeto findByUser(String user);

	@Query("select j from Projeto j where j.user = :user and j.senha = :senha")
	public Projeto buscarLogin(String user, String senha);

}
