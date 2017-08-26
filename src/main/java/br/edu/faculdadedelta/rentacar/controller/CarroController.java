package br.edu.faculdadedelta.rentacar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.faculdadedelta.rentacar.model.Carro;
import br.edu.faculdadedelta.rentacar.model.Modelo;
import br.edu.faculdadedelta.rentacar.repository.CarroRepository;
import br.edu.faculdadedelta.rentacar.repository.LocacaoRepository;
import br.edu.faculdadedelta.rentacar.repository.ModeloRepository;

@Controller
@RequestMapping("/"+CarroController.NOME_CONTROLADOR)
public class CarroController extends CRUDControllerBase<Long, Carro, CarroRepository>{

	protected static final String NOME_CONTROLADOR = "carros";
	
	@Autowired
	private CarroRepository carroRepository;

	@Autowired
	private LocacaoRepository locacaoRepository;

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
		return new String[]{"ID", "Modelo","Placa","Valor da Diária", "Situação"};
	}
	
	@Override
	public String[] getAtributosListagem() {
		return new String[]{"id", "descricaoDoModelo", "placa", "valorDaDiaria", "descricaoDaSituacao"};
	}
	
	@ModelAttribute("todosModelos")
	public List<Modelo> todosModelos() {
		return modeloRepository.findAll();
	}
	
	@Override
	public String validaSeEntidadePodeSerExcluida(Long id) {
		Carro carro = carroRepository.findOne(id);
		
		if(carro==null)
			return null;
		
		int qtdLocacoesDoCarro = locacaoRepository.countByCarro(carro);
		return qtdLocacoesDoCarro > 0 ? "Carro '" +carro.getTextoApresentacaoSimplificado()+ "' não pode ser excluído! Existem locações castrados que utilizam este carro." : null;
	}
	

}
