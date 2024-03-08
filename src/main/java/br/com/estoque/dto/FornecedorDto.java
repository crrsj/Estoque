package br.com.estoque.dto;

import java.io.Serializable;

import br.com.estoque.model.Fornecedor;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class FornecedorDto implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	private Long id;
	@NotBlank
	private String nome;
	@NotBlank
	private String telefone;
	private String email;
	
	public FornecedorDto(Fornecedor cadFornecedor) {
		this.id = cadFornecedor.getId();
		this.nome = cadFornecedor.getNome();
		this.telefone = cadFornecedor.getTelefone();
		this.email = cadFornecedor.getEmail();
	}

	
}
