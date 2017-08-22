package br.edu.faculdadedelta.rentacar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.faculdadedelta.rentacar.model.Motorista;
import br.edu.faculdadedelta.rentacar.repository.MotoristaRepository;

@Controller
@RequestMapping("/motoristas")
public class MotoristaController extends AbstractCRUDController<Long, Motorista, MotoristaRepository>{

	@Override
	public String getNomeControlador() {
		return "motoristas";
	}
	
	@Override
	public String getNomeEntidade() {
		return "motorista";
	}
	
	@Override
	public String getNomeListaEntidade() {
		return "listaMotorista";
	}

	@Override
	public String getNomeMensagemEntidade() {
		return "Motorista";
	}
	
	@Override
	public Motorista getInstanciaEntidade() {
		return new Motorista();
	}
}
