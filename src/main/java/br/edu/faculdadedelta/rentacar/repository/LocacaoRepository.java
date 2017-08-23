package br.edu.faculdadedelta.rentacar.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import br.edu.faculdadedelta.rentacar.model.Locacao;

public interface LocacaoRepository extends JpaRepository<Locacao, Long> {

}
