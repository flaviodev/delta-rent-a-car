package br.edu.faculdadedelta.rentacar.controller;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.faculdadedelta.rentacar.model.EntidadeBase;


public abstract class CRUDControllerBase<ID extends Serializable, E extends EntidadeBase<ID>, R extends JpaRepository<E, ID>> {

	@Autowired
	private R repository;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public ModelAndView novo() {
		
		ModelAndView mv = new ModelAndView(getNomeTemplateEdicao());
		
		try {
			mv.addObject("entidade", ((Class<E>)((ParameterizedType)this.getClass().
				       getGenericSuperclass()).getActualTypeArguments()[1]).newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException("Erro ao instanciar a entidade");
		}
		
		return mv;
	}

	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public ModelAndView salvar(@Validated E entidade, Errors errors, RedirectAttributes redirectAttributes) {
		
		if(errors.hasErrors()) {
			return new ModelAndView(getNomeTemplateEdicao());
		}
		
		repository.save(entidade);
		
		redirectAttributes.addFlashAttribute("mensagem", "Cadastro de " + getNomeEntidade() + " salvo com sucesso!");
		
		return new ModelAndView("redirect:/"+getNomeControlador()+"/novo");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar() {

		ModelAndView mv = new ModelAndView("listaRegistros");
		
		List<E> entidades = repository.findAll();
		
		mv.addObject("entidades", entidades);
		
		return mv;
	}
	
	@RequestMapping(value="/editar/{id}", method = RequestMethod.GET)
	public ModelAndView editar(@PathVariable("id") ID id) {
		E entidade = repository.findOne(id);

		ModelAndView mv = new ModelAndView(getNomeTemplateEdicao());
		
		mv.addObject("entidade", entidade);
		
		return mv;
	}
	
	@RequestMapping(value="/excluir/{id}", method = RequestMethod.GET)
	public ModelAndView excluir(@PathVariable("id") ID id) {
		repository.delete(id);
		
		return new ModelAndView("redirect:/"+getNomeControlador());
	}
	
	@ModelAttribute("controlador")
	public abstract String getNomeControlador();
	
	public abstract String getNomeTemplateEdicao();

	@ModelAttribute("nomeEntidade")
	public abstract String getNomeEntidade();
		
	@ModelAttribute("nomeEntidadePlural")
	public abstract String getNomeEntidadePlural();

	
	@ModelAttribute("colunas")
	public final String[] colunas() {
		String[] colunasListagem = getColunasListagem();
		
		if(colunasListagem == null) {
			throw new RuntimeException("As colunas para listagem do devem ser informadas");
		}
		
		String[] colunas = new String[colunasListagem.length+1];
		
		for(int i = 0; i<colunasListagem.length; i++) {
			colunas[i] = colunasListagem[i];
		}
		
		colunas[colunasListagem.length] = "Operações";
		
		return colunas;
	}

	@ModelAttribute("atributos")
	public final String[] atributos() {
		return getAtributosListagem();
	}
	
	public abstract String[] getColunasListagem();
	
	public abstract String[] getAtributosListagem();
}
