package br.edu.faculdadedelta.rentacar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.faculdadedelta.rentacar.model.Fabricante;
import br.edu.faculdadedelta.rentacar.repository.FabricanteRepository;

@Controller
@RequestMapping("/fabricantes")
public class FabricanteController extends AbstractCRUDController<Long, Fabricante, FabricanteRepository>{

	@Override
	public String getNomeControlador() {
		return "fabricantes";
	}
	
	@Override
	public String getNomeEntidade() {
		return "fabricante";
	}
	
	@Override
	public String getNomeListaEntidade() {
		return "listaFabricante";
	}

	@Override
	public String getNomeMensagemEntidade() {
		return "Fabricante";
	}
	
	@Override
	public Fabricante getInstanciaEntidade() {
		return new Fabricante();
	}
}
