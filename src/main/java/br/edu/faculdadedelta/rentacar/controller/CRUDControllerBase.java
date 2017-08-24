package br.edu.faculdadedelta.rentacar.controller;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.edu.faculdadedelta.rentacar.model.EntidadeBase;


public abstract class CRUDControllerBase<ID extends Serializable, E extends EntidadeBase<ID>, R extends JpaRepository<E, ID>> extends ControllerBase<ID, E, R> {

	
	@RequestMapping(value="/editar/{id}", method = RequestMethod.GET)
	public ModelAndView editar(@PathVariable("id") ID id) {
		E entidade = getRepositorio().findOne(id);

		ModelAndView mv = new ModelAndView(getNomeTemplateEdicao());
		
		mv.addObject(getNomeTemplateEdicao(), entidade);
		
		return mv;
	}
	
	@RequestMapping(value="/excluir/{id}", method = RequestMethod.GET)
	public ModelAndView excluir(@PathVariable("id") ID id) {
		getRepositorio().delete(id);
		
		return new ModelAndView("redirect:/"+getNomeControlador());
	}
	
	public String getNomeTemplateListagem() {
		return "listaRegistros";
	}
	
	protected String getMensagemDeSucessoSalvar() {
		return "Cadastro de " + getNomeEntidade() + " salvo com sucesso!";
	}
	
}
