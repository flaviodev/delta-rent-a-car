package br.edu.faculdadedelta.rentacar.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.faculdadedelta.rentacar.model.Carro;
import br.edu.faculdadedelta.rentacar.model.Modelo;
import br.edu.faculdadedelta.rentacar.repository.CarroRepository;

@Service
public class CarroServico extends CRUDServicoBase<Long,Carro, CarroRepository>{

	@Autowired
	private LocacaoServico locacaoServico;  
	
	@Override
	public String validaSeEntidadePodeSerExcluida(Long id) {
		Carro carro = obter(id);
		
		if(carro==null)
			return null;
		
		int qtdLocacoesDoCarro = locacaoServico.getQuantidadeLocacoesAssociadosAoCarro(carro);
		return qtdLocacoesDoCarro > 0 ? "Carro '" +carro.getTextoApresentacaoSimplificado()+ "' não pode ser excluído! Existem locações castrados que utilizam este carro." : null;
	}

	public int getQuantidadeCarrosAssociadosAoModelo(Modelo modelo) {
		
		return getRepositorio().countByModelo(modelo);
	}
}
