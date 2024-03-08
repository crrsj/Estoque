package br.com.estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.estoque.model.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

}
