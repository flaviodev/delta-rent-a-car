package br.edu.faculdadedelta.rentacar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.faculdadedelta.rentacar.model.Fabricante;
import br.edu.faculdadedelta.rentacar.repository.FabricanteRepository;
import br.edu.faculdadedelta.rentacar.repository.ModeloRepository;

@Controller
@RequestMapping("/" + FabricanteController.NOME_CONTROLADOR)
public class FabricanteController extends CRUDControllerBase<Long, Fabricante, FabricanteRepository> {

	protected static final String NOME_CONTROLADOR = "fabricantes";

	@Autowired
	private ModeloRepository modeloRepository;

	@Autowired
	private FabricanteRepository fabricanteRepository;

	@Override
	public String getNomeControlador() {
		return NOME_CONTROLADOR;
	}

	@Override
	public String getNomeTemplateEdicao() {
		return "fabricante";
	}

	@Override
	public String getNomeEntidade() {
		return "Fabricante";
	}

	@Override
	public String getNomeEntidadePlural() {
		return "Fabricantes";
	}

	@Override
	public String[] getColunasListagem() {
		return new String[] { "ID", "Nome", "Descrição" };
	}

	@Override
	public String[] getAtributosListagem() {
		return new String[] { "id", "nome", "descricao" };
	}

	@Override
	public String validaSeEntidadePodeSerExcluida(Long id) {
		Fabricante fabricante = fabricanteRepository.findOne(id);
		
		if(fabricante==null)
			return null;
		
		int qtdModelosDoFabricante = modeloRepository.countByFabricante(fabricante);
		return qtdModelosDoFabricante>0?"Fabricante '"+fabricante.getTextoApresentacao()+"' não pode ser excluído! Existem modelos castrados que utilizam este fabricante." : null;
	}

}
