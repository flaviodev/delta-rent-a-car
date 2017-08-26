package br.edu.faculdadedelta.rentacar.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.faculdadedelta.rentacar.model.Fabricante;
import br.edu.faculdadedelta.rentacar.repository.FabricanteRepository;

@Service
public class FabricanteServico extends CRUDServicoBase<Long,Fabricante, FabricanteRepository> {

	@Autowired
	private ModeloServico modeloServico;
	
	@Override
	public String validaSeEntidadePodeSerExcluida(Long id) {
		Fabricante fabricante = obter(id);
		
		if(fabricante==null)
			return null;
		
		int qtdModelosDoFabricante = modeloServico.getQuantidadeModelosAssociadosAoFabricante(fabricante);
		
		return qtdModelosDoFabricante>0?"Fabricante '"+fabricante.getTextoApresentacao()+"' não pode ser excluído! Existem modelos castrados que utilizam este fabricante." : null;
	}
}
