package br.edu.faculdadedelta.rentacar.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.faculdadedelta.rentacar.model.Carro;
import br.edu.faculdadedelta.rentacar.model.Locacao;
import br.edu.faculdadedelta.rentacar.model.Motorista;
import br.edu.faculdadedelta.rentacar.repository.CarroRepository;
import br.edu.faculdadedelta.rentacar.repository.LocacaoRepository;
import br.edu.faculdadedelta.rentacar.repository.MotoristaRepository;

@Controller
@RequestMapping("/"+LocacaoController.NOME_CONTROLADOR)
public class LocacaoController extends CRUDControllerBase<Long, Locacao, LocacaoRepository>{

	protected static final String NOME_CONTROLADOR = "locacoes";
	
	@Autowired
	private MotoristaRepository motoristaRepository;
	
	@Autowired
	private CarroRepository carroRepository;

	@Override
	public String getNomeControlador() {
		return NOME_CONTROLADOR;
	}
	
	@Override
	public String getNomeTemplateEdicao() {
		return "locacao";
	}

	@Override
	public String getNomeEntidade() {
		return "Locação";
	}
	
	@Override
	public String getNomeEntidadePlural() {
		return "Locações";
	}
	
	@Override
	public String[] getColunasListagem() {
		return new String[]{"ID","Motorista", "Carro", "Valor da Diária", "Data de Locação"};
	}
	
	@Override
	public String[] getAtributosListagem() {
		return new String[]{"id","nomeDoMotorista","descricaoDoCarro", "valorDaDiaria", "dataDeLocacao"};
	}
	
	@ModelAttribute("todosMotoristas")
	public List<Motorista> todosMotoristas() {
		return motoristaRepository.findAll();
	}

	@ModelAttribute("todosCarros")
	public List<Carro> todosCarros() {
		return carroRepository.findAll();
	}
}
