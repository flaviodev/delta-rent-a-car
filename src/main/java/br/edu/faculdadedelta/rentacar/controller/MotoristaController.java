package br.edu.faculdadedelta.rentacar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.faculdadedelta.rentacar.model.Motorista;
import br.edu.faculdadedelta.rentacar.repository.MotoristaRepository;

@Controller
@RequestMapping("/motoristas")
public class MotoristaController extends CRUDControllerBase<Long, Motorista, MotoristaRepository>{

	@Override
	public String getNomeControlador() {
		return "motoristas";
	}
	
	@Override
	public String getNomeTemplateEdicao() {
		return "motorista";
	}
	
	@Override
	public String getNomeTemplateListagem() {
		return "listaMotorista";
	}
}
