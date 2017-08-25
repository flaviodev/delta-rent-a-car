package br.edu.faculdadedelta.rentacar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.faculdadedelta.rentacar.model.Fabricante;
import br.edu.faculdadedelta.rentacar.model.Modelo;

public interface ModeloRepository extends JpaRepository<Modelo, Long> {
	
	public int countByFabricante(Fabricante fabricante);
}
