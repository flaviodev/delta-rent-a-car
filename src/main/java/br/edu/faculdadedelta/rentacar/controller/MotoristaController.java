package br.edu.faculdadedelta.rentacar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.faculdadedelta.rentacar.model.Motorista;
import br.edu.faculdadedelta.rentacar.model.type.Sexo;
import br.edu.faculdadedelta.rentacar.repository.MotoristaRepository;
import br.edu.faculdadedelta.rentacar.services.MotoristaServico;

@Controller
@RequestMapping("/motoristas")
public class MotoristaController extends CRUDControllerBase<Long, Motorista, MotoristaRepository, MotoristaServico>{

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
}
