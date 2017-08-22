package br.edu.faculdadedelta.posexemplofdsdev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.faculdadedelta.posexemplofdsdev.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
