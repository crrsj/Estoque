package br.com.estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.estoque.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
