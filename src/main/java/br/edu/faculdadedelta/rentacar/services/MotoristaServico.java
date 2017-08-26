package br.edu.faculdadedelta.rentacar.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.faculdadedelta.rentacar.model.Motorista;
import br.edu.faculdadedelta.rentacar.repository.MotoristaRepository;

@Service
public class MotoristaServico extends CRUDServicoBase<Long,Motorista, MotoristaRepository>{

	@Autowired
	private LocacaoServico locacaoServico;  
	
	@Override
	public String validaSeEntidadePodeSerExcluida(Long id) {
		Motorista motorista = obter(id);
		
		if(motorista==null)
			return null;
		
		int qtdLocacoesDoMotorista = locacaoServico.getQuantidadeLocacoesAssociadosAoMotorista(motorista);
		return qtdLocacoesDoMotorista > 0 ? "Motorista '" +motorista.getTextoApresentacao()+ "' não pode ser excluído! Existem locações castrados que utilizam este motorista." : null;
	}
}
