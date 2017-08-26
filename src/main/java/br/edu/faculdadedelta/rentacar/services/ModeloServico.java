package br.edu.faculdadedelta.rentacar.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.faculdadedelta.rentacar.model.Fabricante;
import br.edu.faculdadedelta.rentacar.model.Modelo;
import br.edu.faculdadedelta.rentacar.repository.ModeloRepository;

@Service
public class ModeloServico extends CRUDServicoBase<Long,Modelo, ModeloRepository>{

	@Autowired
	private CarroServico carroServico;
	
	@Override
	public String validaSeEntidadePodeSerExcluida(Long id) {
		Modelo modelo = obter(id);
		
		if(modelo==null)
			return null;
		
		int qtdCarrosDoModelo = carroServico.getQuantidadeCarrosAssociadosAoModelo(modelo);
		return qtdCarrosDoModelo > 0 ? "Modelo '" + modelo.getTextoApresentacao() + "' não pode ser excluído! Existem carros castrados que utilizam este modelo." : null;
	}
	
	public int getQuantidadeModelosAssociadosAoFabricante(Fabricante fabricante) {
		return getRepositorio().countByFabricante(fabricante);
	}
}
