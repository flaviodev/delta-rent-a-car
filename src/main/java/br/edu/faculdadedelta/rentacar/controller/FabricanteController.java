package br.edu.faculdadedelta.rentacar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.faculdadedelta.rentacar.model.Fabricante;
import br.edu.faculdadedelta.rentacar.repository.FabricanteRepository;

@Controller
@RequestMapping("/"+FabricanteController.NOME_CONTROLADOR)
public class FabricanteController extends CRUDControllerBase<Long, Fabricante, FabricanteRepository>{

	protected static final String NOME_CONTROLADOR = "fabricantes";
	
	@Override
	public String getNomeControlador() {
		return NOME_CONTROLADOR;
	}
	
	@Override
	public String getNomeTemplateEdicao() {
		return "fabricante";
	}
	
	@Override
	public String getNomeTemplateListagem() {
		return "listaFabricante";
	}
}
