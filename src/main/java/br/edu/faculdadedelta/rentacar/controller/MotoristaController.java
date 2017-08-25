package br.edu.faculdadedelta.rentacar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.faculdadedelta.rentacar.model.Motorista;
import br.edu.faculdadedelta.rentacar.model.type.Sexo;
import br.edu.faculdadedelta.rentacar.repository.LocacaoRepository;
import br.edu.faculdadedelta.rentacar.repository.MotoristaRepository;

@Controller
@RequestMapping("/motoristas")
public class MotoristaController extends CRUDControllerBase<Long, Motorista, MotoristaRepository>{

	@Autowired
	private MotoristaRepository motoristaRepository;
	
	@Autowired
	private LocacaoRepository locacaoRepository;
	
	@Override
	public String getNomeControlador() {
		return "motoristas";
	}
	
	@Override
	public String getNomeTemplateEdicao() {
		return "motorista";
	}
		
	@Override
	public String getNomeEntidade() {
		return "Motorista";
	}
	
	@Override
	public String getNomeEntidadePlural() {
		return "Motoristas";
	}	
	
	@Override
	public String[] getColunasListagem() {
		return new String[]{"ID","Nome","CPF"};
	}
	
	@Override
	public String[] getAtributosListagem() {
		return new String[]{"id","nome","cpf"};
	}
	
	@ModelAttribute("todosSexos")
	public Sexo[] todosSexos() {
		return Sexo.values();
	}

	
	@Override
	public String validaSeEntidadePodeSerExcluida(Long id) {
		Motorista motorista = motoristaRepository.findOne(id);
		
		if(motorista==null)
			return null;
		
		int qtdLocacoesDoMotorista = locacaoRepository.countByMotorista(motorista);
		return qtdLocacoesDoMotorista > 0 ? "Motorista '" +motorista.getTextoApresentacao()+ "' não pode ser excluído! Existem locações castrados que utilizam este motorista." : null;
	}
}
