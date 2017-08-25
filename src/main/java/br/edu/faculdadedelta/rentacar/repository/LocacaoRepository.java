package br.edu.faculdadedelta.rentacar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.faculdadedelta.rentacar.model.Carro;
import br.edu.faculdadedelta.rentacar.model.Locacao;
import br.edu.faculdadedelta.rentacar.model.Motorista;

public interface LocacaoRepository extends JpaRepository<Locacao, Long> {

	public int countByMotorista(Motorista motorista);
	
	public int countByCarro(Carro carro);
	
}
