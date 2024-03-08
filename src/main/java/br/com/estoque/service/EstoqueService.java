package br.com.estoque.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estoque.dto.FornecedorDto;
import br.com.estoque.dto.ProdutoDto;
import br.com.estoque.model.Fornecedor;
import br.com.estoque.model.Produto;
import br.com.estoque.repository.FornecedorRepository;
import br.com.estoque.repository.ProdutoRepository;
import jakarta.transaction.Transactional;

@Service
public class EstoqueService {
	
	@Autowired
  private FornecedorRepository fRepository;
	@Autowired
  private ProdutoRepository pRepository;
	
	public FornecedorDto cadastrarFornecedor(FornecedorDto fornecedor) {
		
		var cadastro  = new Fornecedor(fornecedor);
	    var cadFornecedor =	fRepository.save(cadastro);
	    return new FornecedorDto(cadFornecedor);
	}
   
		public Produto cadastrarFornecedorProduto(Produto produto,Long id) {
		Fornecedor fornecedor = fRepository.findById(id).get();
		produto.setFornecedor(fornecedor);
		var salvar = pRepository.save(produto);
		return new Produto(salvar);
	}
	
	@Transactional
    public ProdutoDto atualizarProduto(ProdutoDto produto) {
        var atualiza  = new Produto(produto);
        if(atualiza.getCodigo()== null) {
         throw new IllegalArgumentException("O ID do produto não existe, insira um ID válido.");
        }
        var atualizar =  pRepository.save(atualiza);
        return new ProdutoDto(atualizar);
    }

    @Transactional
    public ProdutoDto adicionarEstoque(Long produtoCodigo, Integer quantidade) {
        Produto produto = pRepository.findById(produtoCodigo)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + quantidade);
      var salve =   pRepository.save(produto);
      return new ProdutoDto(salve);
    }

    @Transactional
    public void venderProduto(Long produtoCodigo, Integer quantidade) {
        Produto produto = pRepository.findById(produtoCodigo)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        if (quantidade <= produto.getQuantidadeEstoque()) {
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - quantidade);
            pRepository.save(produto);
        } else {
            throw new IllegalArgumentException("Quantidade insuficiente em estoque.");
        }
    }
    public Iterable<ProdutoDto> listarProdutos() {
    	var busca = pRepository.findAll().stream().map(ProdutoDto::new).toList();
    	return busca;
			
		
    }
    public ProdutoDto BuscarPorId(Long codigo) {
    	var buscaId = pRepository.findById(codigo).get();
    	return new ProdutoDto(buscaId);
    }
    
    public void excluir(Long codigo) {
    	pRepository.deleteById(codigo);
    	
    }
}
