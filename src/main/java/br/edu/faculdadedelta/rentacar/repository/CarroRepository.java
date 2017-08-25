package br.edu.faculdadedelta.rentacar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.faculdadedelta.rentacar.model.Carro;
import br.edu.faculdadedelta.rentacar.model.Modelo;
import br.edu.faculdadedelta.rentacar.model.type.SituacaoDoCarro;

public interface CarroRepository extends JpaRepository<Carro, Long> {

	public List<Carro> findAllBySituacao(SituacaoDoCarro situacao);

	public int countByModelo(Modelo modelo);
}
