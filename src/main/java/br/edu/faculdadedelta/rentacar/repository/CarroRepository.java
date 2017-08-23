package br.edu.faculdadedelta.rentacar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.faculdadedelta.rentacar.model.Carro;

public interface CarroRepository extends JpaRepository<Carro, Long> {

}
