package com.iftm.gestaoProjetosIniciacao.controller;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iftm.gestaoProjetosIniciacao.exceptions.ExceptionService;
import com.iftm.gestaoProjetosIniciacao.model.Projeto;
import com.iftm.gestaoProjetosIniciacao.model.Relatorio;
import com.iftm.gestaoProjetosIniciacao.repository.ProjetoDAO;
import com.iftm.gestaoProjetosIniciacao.service.ServiceProjeto;
import com.iftm.gestaoProjetosIniciacao.util.Util;

@Controller
public class ProjetoController {

	@Autowired
	ProjetoDAO projetoDAO;

	@Autowired
	ServiceProjeto serviceProjeto;

	@GetMapping("/teste")
	public ModelAndView teste() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/NewFile");
		return mv;
	}

	@GetMapping
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Login/login");
		mv.addObject("projeto", new Projeto());
		return mv;
	}

	@GetMapping("/cadastro")
	public ModelAndView cadastrarProjeto() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("projeto", new Projeto());
		mv.setViewName("Login/cadastro");
		return mv;
	}

	@GetMapping("/preencher-relatorio")
	public ModelAndView finalizaRelatorio() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/FinalizarRelatorio");
		mv.addObject("projeto", new Projeto());
		return mv;
	}

	@GetMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		return login();
	}

	@PostMapping("cadastrar-projeto")
	public ModelAndView cadastrarProjeto(Projeto projeto) throws Exception {
		ModelAndView mv = new ModelAndView();
		serviceProjeto.salvarProjeto(projeto);
		mv.setViewName("redirect:/");
		return mv;
	}

	@PostMapping("/login")
	public ModelAndView login(@Valid Projeto usuarioProjeto, BindingResult br, HttpSession session)
			throws NoSuchAlgorithmException, ExceptionService {
		ModelAndView mv = new ModelAndView();
		mv.addObject("projeto", new Projeto());
		mv.setViewName("Login/preencher-relatorio");
		Projeto projetoLogin = serviceProjeto.loginProjeto(usuarioProjeto.getUser(),
				Util.md5(usuarioProjeto.getSenha()));
		if (projetoLogin == null) {
			mv.addObject("msg", "Usuário não encontrado. Tente novamente");
			return mv;
		} else {
			session.setAttribute("usuarioLogado", projetoLogin);
			mv.setViewName("redirect:preencherRelatorio");
			return mv;
		}
	}
	
	@PostMapping("/preencher-relatorio")
	public ModelAndView preencherRelatorio(@Valid Relatorio entrada, BindingResult br, HttpSession session)
			throws NoSuchAlgorithmException, ExceptionService {
		ModelAndView mv = new ModelAndView();
		mv.addObject("projeto", new Projeto());
		mv.setViewName("Login/preencher-relatorio");
		Relatorio saida = serviceProjeto.loginProjeto(entrada.getUser(),
				Util.md5(entrada.getSenha()));
		if (saida == null) {
			mv.addObject("msg", "Usuário não encontrado. Tente novamente");
			return mv;
		} else {
			session.setAttribute("usuarioLogado", saida);
			mv.setViewName("redirect:preencherRelatorio");
			return mv;
		}
	}
}