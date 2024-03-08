package br.com.estoque.model;

import java.io.Serializable;

import br.com.estoque.dto.ProdutoDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "codigo")
public class Produto implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	private String marca;
	private String nomeProduto;
	private Integer quantidadeEstoque;
	private Double precoUnitario;
	@ManyToOne
	@JoinColumn(name = "fornecedor_id")
	private Fornecedor fornecedor;
	
	public Produto(ProdutoDto produto) {
		this.codigo = produto.getCodigo();
		this.marca = produto.getMarca();
		this.nomeProduto = produto.getNomeProduto();
		this.quantidadeEstoque = produto.getQuantidadeEstoque();
		this.precoUnitario = produto.getPrecoUnitario();
	}

	public Produto(Produto salvar) {
		this.codigo = salvar.codigo;
		this.marca = salvar.marca;
		this.nomeProduto = salvar.nomeProduto;
		this.quantidadeEstoque = salvar.quantidadeEstoque;
		this.precoUnitario = salvar.precoUnitario;
	}

}
