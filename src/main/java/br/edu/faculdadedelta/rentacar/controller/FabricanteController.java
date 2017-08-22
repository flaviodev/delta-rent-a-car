package br.edu.faculdadedelta.rentacar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.faculdadedelta.rentacar.model.Fabricante;
import br.edu.faculdadedelta.rentacar.repository.FabricanteRepository;

@Controller
@RequestMapping("/fabricantes")
public class FabricanteController {

	@Autowired
	private FabricanteRepository repository;
	
	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public ModelAndView novo() {

		ModelAndView mv = new ModelAndView("fabricante");
		mv.addObject(new Fabricante());
		
		return mv;
	}
	

	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public ModelAndView salvar(@Validated Fabricante produto, Errors errors, RedirectAttributes redirectAttributes) {
		
		if(errors.hasErrors()) {
			return new ModelAndView("fabricante");
		}
		
		repository.save(produto);
		
		redirectAttributes.addFlashAttribute("message", "Fabricante Salvo con sucesso!");
		
		return new ModelAndView("redirect:/fabricantes/novo");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar() {

		ModelAndView mv = new ModelAndView("listaFabricante");
		
		List<Fabricante> fabricantes = repository.findAll();
		
		mv.addObject("fabricantes", fabricantes);
		
		return mv;
	}

	
	@RequestMapping(value="/editar/{id}", method = RequestMethod.GET)
	public ModelAndView editar(@PathVariable("id") Long id) {
		Fabricante fabricante = repository.findOne(id);

		ModelAndView mv = new ModelAndView("fabricante");
		
		mv.addObject("fabricante", fabricante);
		
		return mv;
	}
	
	@RequestMapping(value="/excluir/{id}", method = RequestMethod.GET)
	public ModelAndView excluir(@PathVariable("id") Long id) {
		repository.delete(id);
		
		return new ModelAndView("redirect:/fabricantes");
	}
}
