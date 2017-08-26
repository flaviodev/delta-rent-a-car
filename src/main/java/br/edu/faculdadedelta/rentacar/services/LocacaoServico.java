package br.edu.faculdadedelta.rentacar.services;

import org.springframework.stereotype.Service;

import br.edu.faculdadedelta.rentacar.model.Carro;
import br.edu.faculdadedelta.rentacar.model.Locacao;
import br.edu.faculdadedelta.rentacar.model.Motorista;
import br.edu.faculdadedelta.rentacar.repository.LocacaoRepository;

@Service
public class LocacaoServico extends CRUDServicoBase<Long,Locacao, LocacaoRepository>{

	@Override
	public String validaSeEntidadePodeSerExcluida(Long id) {
		return null;
	}

	public int getQuantidadeLocacoesAssociadosAoCarro(Carro carro) {
		
		return getRepositorio().countByCarro(carro);
	}
	
	public int getQuantidadeLocacoesAssociadosAoMotorista(Motorista motorista) {
		
		return getRepositorio().countByMotorista(motorista);
	}
}
