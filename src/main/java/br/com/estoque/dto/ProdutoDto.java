package br.com.estoque.dto;

import java.io.Serializable;

import br.com.estoque.model.Fornecedor;
import br.com.estoque.model.Produto;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "codigo")
public class ProdutoDto implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	@NotBlank
	private String marca;
	@NotBlank
	private String nomeProduto;
	@NotNull
	private Integer quantidadeEstoque;
	private Double precoUnitario;
	private Fornecedor fornecedor;

	public ProdutoDto(Produto cadProduto) {
		this.codigo = cadProduto.getCodigo();
		this.marca = cadProduto.getMarca();
		this.nomeProduto  = cadProduto.getNomeProduto();
		this.quantidadeEstoque = cadProduto.getQuantidadeEstoque();
		this.precoUnitario  = cadProduto.getPrecoUnitario();
		this.fornecedor = cadProduto.getFornecedor();
	}

}
