package com.iftm.gestaoProjetosIniciacao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iftm.gestaoProjetosIniciacao.model.Relatorio;

public interface RelatorioDAO extends JpaRepository<Relatorio, Long> {
	
	@Query("select k from Relatorio k where k.projeto.id = :id")
	public List<Relatorio> findAllByIdProjeto(Long id);

}
