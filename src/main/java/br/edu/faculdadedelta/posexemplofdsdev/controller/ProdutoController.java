package br.edu.faculdadedelta.posexemplofdsdev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.faculdadedelta.posexemplofdsdev.model.Produto;
import br.edu.faculdadedelta.posexemplofdsdev.model.type.StatusDoProduto;
import br.edu.faculdadedelta.posexemplofdsdev.repository.ProdutoRepository;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository repository;
	
	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public ModelAndView novo() {

		ModelAndView mv = new ModelAndView("produto");
		mv.addObject(new Produto());
		
		return mv;
	}
	

	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public ModelAndView salvar(@Validated Produto produto, Errors errors, RedirectAttributes redirectAttributes) {
		
		if(errors.hasErrors()) {
			return new ModelAndView("produto");
		}
		
		repository.save(produto);
		
		redirectAttributes.addFlashAttribute("message", "Produto Salvo con sucesso!");
		
		return new ModelAndView("redirect:/produtos/novo");
	}

	@ModelAttribute("todosStatusDoProtudo")
	public StatusDoProduto[] todosStatusDoProtudo() {
		return StatusDoProduto.values();
	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar() {

		ModelAndView mv = new ModelAndView("listaProduto");
		
		List<Produto> produtos = repository.findAll();
		
		mv.addObject("produtos", produtos);
		
		return mv;
	}

	
	@RequestMapping(value="/editar/{id}", method = RequestMethod.GET)
	public ModelAndView editar(@PathVariable("id") Long id) {
		Produto produto = repository.findOne(id);

		ModelAndView mv = new ModelAndView("produto");
		
		mv.addObject("produto", produto);
		
		return mv;
	}
	
	@RequestMapping(value="/excluir/{id}", method = RequestMethod.GET)
	public ModelAndView excluir(@PathVariable("id") Long id) {
		repository.delete(id);
		
		return new ModelAndView("redirect:/produtos");
	}
}
