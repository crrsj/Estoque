package br.com.estoque.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.estoque.dto.FornecedorDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Fornecedor implements Serializable {	
	
	private static final long serialVersionUID = 1L;
	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	private String nome;
	private String telefone;
	private String email;
	@JsonIgnore
	@OneToMany(mappedBy = "fornecedor",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Produto>produtos = new ArrayList<>();
	
	public Fornecedor(FornecedorDto fornecedor) {
		this.id = fornecedor.getId();
		this.nome = fornecedor.getNome();
		this.telefone = fornecedor.getTelefone();
		this.email = fornecedor.getEmail();
		
	}

}
