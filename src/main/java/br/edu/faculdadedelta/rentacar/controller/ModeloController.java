package br.edu.faculdadedelta.rentacar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.faculdadedelta.rentacar.model.Fabricante;
import br.edu.faculdadedelta.rentacar.model.Modelo;
import br.edu.faculdadedelta.rentacar.model.type.Categoria;
import br.edu.faculdadedelta.rentacar.repository.FabricanteRepository;
import br.edu.faculdadedelta.rentacar.repository.ModeloRepository;

@Controller
@RequestMapping("/"+ModeloController.NOME_CONTROLADOR)
public class ModeloController extends CRUDControllerBase<Long, Modelo, ModeloRepository>{

	protected static final String NOME_CONTROLADOR = "modelos";
	
	@Autowired
	private FabricanteRepository fabricanteRepository;
	
	@Override
	public String getNomeControlador() {
		return NOME_CONTROLADOR;
	}
	
	@Override
	public String getNomeTemplateEdicao() {
		return "modelo";
	}

	@Override
	public String getNomeEntidade() {
		return "Modelo";
	}
	
	@Override
	public String getNomeEntidadePlural() {
		return "Modelos";
	}
	
	@Override
	public String[] getColunasListagem() {
		return new String[]{"ID","Descrição", "Categoria", "Fabricante"};
	}
	
	@Override
	public String[] getAtributosListagem() {
		return new String[]{"id","descricao","descricaoDaCategoria", "nomeDoFabricante"};
	}
	
	@ModelAttribute("todasCategorias")
	public Categoria[] todasCategorias() {
		return Categoria.values();
	}
	
	@ModelAttribute("todosFabricantes")
	public List<Fabricante> todosFabricantes() {
		return fabricanteRepository.findAll();
	}
	
}
