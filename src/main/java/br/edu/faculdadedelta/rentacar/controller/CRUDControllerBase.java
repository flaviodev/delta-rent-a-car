package br.edu.faculdadedelta.rentacar.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.faculdadedelta.rentacar.model.Model;


public abstract class AbstractCRUDController<ID extends Serializable, M extends Model<ID>, R extends JpaRepository<M, ID>> {

	@Autowired
	private R repository;
	
	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public ModelAndView novo() {

		ModelAndView mv = new ModelAndView(getNomeEntidade());
		mv.addObject(getInstanciaEntidade());
		
		return mv;
	}
	

	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public ModelAndView salvar(@Validated M entidade, Errors errors, RedirectAttributes redirectAttributes) {
		
		if(errors.hasErrors()) {
			return new ModelAndView(getNomeEntidade());
		}
		
		repository.save(entidade);
		
		redirectAttributes.addFlashAttribute("message", getNomeMensagemEntidade() + " Salvo com sucesso!");
		
		return new ModelAndView("redirect:/"+getNomeControlador()+"/novo");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar() {

		ModelAndView mv = new ModelAndView(getNomeListaEntidade());
		
		List<M> entidades = repository.findAll();
		
		mv.addObject(getNomeControlador(), entidades);
		
		return mv;
	}

	
	@RequestMapping(value="/editar/{id}", method = RequestMethod.GET)
	public ModelAndView editar(@PathVariable("id") ID id) {
		M entidade = repository.findOne(id);

		ModelAndView mv = new ModelAndView(getNomeEntidade());
		
		mv.addObject(getNomeEntidade(), entidade);
		
		return mv;
	}
	
	@RequestMapping(value="/excluir/{id}", method = RequestMethod.GET)
	public ModelAndView excluir(@PathVariable("id") ID id) {
		repository.delete(id);
		
		return new ModelAndView("redirect:/"+getNomeControlador());
	}
	
	public abstract String getNomeControlador();
	
	public abstract String getNomeEntidade();
	
	public abstract String getNomeListaEntidade();
	
	public abstract String getNomeMensagemEntidade();
	
	public abstract M getInstanciaEntidade();
}
