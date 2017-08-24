package br.edu.faculdadedelta.rentacar.controller;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.faculdadedelta.rentacar.model.EntidadeBase;


public abstract class ControllerBase<ID extends Serializable, E extends EntidadeBase<ID>, R extends JpaRepository<E, ID>> {

	@Autowired
	private R repositorio;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public ModelAndView novo() {
		
		ModelAndView mv = new ModelAndView(getNomeTemplateEdicao());
		
		E entidade = null;
		
		try {
			entidade = ((Class<E>)((ParameterizedType)this.getClass().
				       getGenericSuperclass()).getActualTypeArguments()[1]).newInstance();
			
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException("Erro ao instanciar a entidade");
		}
		
		mv.addObject(getNomeTemplateEdicao(), entidade);
		
		return mv;
	}
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public ModelAndView salvar(@Validated E entidade, Errors errors, RedirectAttributes redirectAttributes) {
		if(errors.hasErrors()) {
			ModelAndView mv = new ModelAndView(getNomeTemplateEdicao());
			
			return mv;
		}
		
		repositorio.save(entidade);
		
		redirectAttributes.addFlashAttribute("mensagem", getMensagemDeSucessoSalvar());
		

		return new ModelAndView("redirect:/"+getNomeControlador()+"/novo");

	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar() {

		ModelAndView mv = new ModelAndView(getNomeTemplateListagem());
		
		List<E> entidades = repositorio.findAll();
		
		mv.addObject("entidades", entidades);
		
		return mv;
	}
	
	protected final R getRepositorio() {
		return repositorio;
	}
	
	protected abstract String getNomeTemplateListagem();
	
	protected abstract String getNomeTemplateEdicao();
	
	protected abstract String getMensagemDeSucessoSalvar();

	@ModelAttribute("controlador")
	public abstract String getNomeControlador();

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
	
	protected abstract String[] getColunasListagem();
	
	protected abstract String[] getAtributosListagem();
	
}
