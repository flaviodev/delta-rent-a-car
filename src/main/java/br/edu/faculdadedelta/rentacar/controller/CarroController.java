package br.edu.faculdadedelta.rentacar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.faculdadedelta.rentacar.model.Carro;
import br.edu.faculdadedelta.rentacar.model.Modelo;
import br.edu.faculdadedelta.rentacar.repository.CarroRepository;
import br.edu.faculdadedelta.rentacar.repository.ModeloRepository;

@Controller
@RequestMapping("/"+CarroController.NOME_CONTROLADOR)
public class CarroController extends CRUDControllerBase<Long, Carro, CarroRepository>{

	protected static final String NOME_CONTROLADOR = "carros";
	
	@Autowired
	private ModeloRepository modeloRepository;
	
	@Override
	public String getNomeControlador() {
		return NOME_CONTROLADOR;
	}
	
	@Override
	public String getNomeTemplateEdicao() {
		return "carro";
	}

	@Override
	public String getNomeEntidade() {
		return "Carro";
	}
	
	@Override
	public String getNomeEntidadePlural() {
		return "Carros";
	}
	
	@Override
	public String[] getColunasListagem() {
		return new String[]{"ID","Placa", "Chassi","Valor da Diária", "Modelo"};
	}
	
	@Override
	public String[] getAtributosListagem() {
		return new String[]{"id","placa","chassi", "valorDaDiaria", "descricaoModelo"};
	}
	
	@ModelAttribute("todosModelos")
	public List<Modelo> todosMOdelos() {
		return modeloRepository.findAll();
	}
	
}
