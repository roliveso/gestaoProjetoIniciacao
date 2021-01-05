package com.iftm.gestaoProjetosIniciacao.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iftm.gestaoProjetosIniciacao.exceptions.ExceptionService;
import com.iftm.gestaoProjetosIniciacao.model.Projeto;
import com.iftm.gestaoProjetosIniciacao.model.Relatorio;
import com.iftm.gestaoProjetosIniciacao.repository.RelatorioDAO;
import com.iftm.gestaoProjetosIniciacao.service.ServiceProjeto;
import com.iftm.gestaoProjetosIniciacao.service.ServiceRelatorio;
import com.iftm.gestaoProjetosIniciacao.util.Util;

@Controller
public class ProjetoController {

	@Autowired
	private RelatorioDAO relatorioDAO;

	@Autowired
	ServiceProjeto serviceProjeto;

	@Autowired
	ServiceRelatorio serviceRelatorio;

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
		mv.setViewName("/preencher-relatorio");
		mv.addObject("projeto", new Projeto());
		mv.addObject("entrada", new Relatorio());

		return mv;
	}

	@GetMapping("/visualizar-relatorio")
	public ModelAndView visualizarRelatorio(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		List<Relatorio> listRelatorio;
		Projeto projeto = ((Projeto) session.getAttribute("usuarioLogado"));
		listRelatorio = relatorioDAO.findAllByIdProjeto(projeto.getId());
		mv.addObject("relatorios", listRelatorio);
		mv.setViewName("/visualizar-relatorios");
		return mv;
	}

	@GetMapping("/editar-relatorio/{id}")
	public ModelAndView editarRelatorio(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView();
		Relatorio relatorio = relatorioDAO.findById(id).get();
		System.out.println(relatorio.getComentarios());
		mv.addObject("relatorio", relatorio);
		mv.setViewName("/editar-relatorio");
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
		Projeto projetoLogin = serviceProjeto.loginProjeto(usuarioProjeto.getUser(),
				Util.md5(usuarioProjeto.getSenha()));
		if (projetoLogin == null) {
			mv.addObject("msg", "Usuário não encontrado. Tente novamente");
			mv.setViewName("Login/login");
			return mv;
		} else {
			session.setAttribute("usuarioLogado", projetoLogin);
			mv.setViewName("redirect:preencher-relatorio");
			return mv;
		}
	}

	@PostMapping("/preencher-relatorio")
	public ModelAndView preencherRelatorio(@Valid Relatorio entrada, BindingResult br, HttpSession session)
			throws NoSuchAlgorithmException, ExceptionService {
		ModelAndView mv = new ModelAndView();
		mv.addObject("projeto", new Projeto());
		mv.setViewName("Login/preencher-relatorio");
		entrada.setProjeto((Projeto) session.getAttribute("usuarioLogado"));
		Relatorio saida = serviceRelatorio.salvar(entrada);
		if (saida == null) {
			mv.addObject("msg", "Erro ao salvar o relatório");
			return mv;
		} else {
			session.setAttribute("relatorio", saida);
			mv.setViewName("redirect:visualizar-relatorio");
			return mv;
		}
	}
}