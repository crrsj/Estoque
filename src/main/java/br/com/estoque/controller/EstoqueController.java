package br.com.estoque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.estoque.dto.FornecedorDto;
import br.com.estoque.dto.ProdutoDto;
import br.com.estoque.model.Produto;
import br.com.estoque.service.EstoqueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("estoque")
public class EstoqueController {
	@Autowired
	private EstoqueService service;
	
	@PostMapping("fornecedor")
	 @Operation(summary = "Rota responsável por cadastrar o fornecedor ao banco de dados")  
	 @ApiResponse(responseCode = "201",description = " fornecedor  cadastrado com sucesso",content = {
	    		@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
	    })           
	public ResponseEntity<FornecedorDto>cadastrar(@RequestBody @Valid FornecedorDto fornecedor,UriComponentsBuilder uriBuilder ){	 
		var cadastro = service.cadastrarFornecedor(fornecedor);
	    return new ResponseEntity<FornecedorDto>(cadastro,HttpStatus.CREATED);
		 
	}
    @PostMapping("addProduto/{id}")
    @Operation(summary = "Rota responsável por vincular um fornecedor ao produto e cadastrar no banco de dados")  
	 @ApiResponse(responseCode = "201",description = "produto vinculado ao fornecedor e cadastrado com sucesso",content = {
	    		@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
	    })           
	public ResponseEntity<Produto>cadastrarFornecedorProduto(@RequestBody Produto produto, @PathVariable Long id){
		var salvar = service.cadastrarFornecedorProduto(produto, id);
		return new ResponseEntity<Produto>(salvar,HttpStatus.CREATED);
	}
    @PutMapping
    @Operation(summary = "Rota responsável por atualizar produto")  
	 @ApiResponse(responseCode = "200",description = "produto atualizado com sucesso",content = {
	    		@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
	    })           
    public ResponseEntity<ProdutoDto> atualizaProduto(@RequestBody @Valid ProdutoDto produto) {
	   var atualizar = service.atualizarProduto(produto);
	   return new ResponseEntity<ProdutoDto>(atualizar,HttpStatus.OK);
   }
    @PostMapping("/{produtoCodigo}/{quantidade}")
    @Operation(summary = "Rota responsável por atualizar a quantidade do estoque")  
	 @ApiResponse(responseCode = "200",description = "quantidade atualizada com sucesso",content = {
	    		@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
	    })           
    public ResponseEntity<ProdutoDto>adicionarAoEstoque(@PathVariable Long produtoCodigo,@PathVariable Integer quantidade){
    	var add =  service.adicionarEstoque(produtoCodigo, quantidade);
    	return new ResponseEntity<ProdutoDto>(add,HttpStatus.OK);
    	
    }
    @PostMapping("/{produtoId}/vender/{quantidade}")
    @Operation(summary = "Rota responsável pela atualização do estoque após a venda")  
	 @ApiResponse(responseCode = "200",description = "estoque atualizado com sucesso",content = {
	    		@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
	    })           
    public ResponseEntity<Void> venderProduto(@PathVariable Long produtoCodigo, @PathVariable Integer quantidade) {
        service.venderProduto(produtoCodigo, quantidade);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping
    @Operation(summary = "Rota responsável pela listagem dos produtos")  
	 @ApiResponse(responseCode = "200",description = " sucesso",content = {
	    		@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
	    })           
    public ResponseEntity<Iterable<ProdutoDto>>buscarProdutos(){
    	var lista = service.listarProdutos();
    	return new ResponseEntity<Iterable<ProdutoDto>>(lista,HttpStatus.OK);
    }
    @GetMapping("/{codigo}")
    @Operation(summary = "Rota responsável busca do produto específico")  
	 @ApiResponse(responseCode = "200",description = "sucesso",content = {
	    		@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
	    })           
    public ResponseEntity<ProdutoDto>buscarPorId(@PathVariable Long codigo){
    	var buscarId = service.BuscarPorId(codigo);
    	return new ResponseEntity<ProdutoDto>(buscarId,HttpStatus.OK);
    }
    @Operation(summary = "Rota responsável pela deleção de um produto específico")  
	 @ApiResponse(responseCode = "204",description = " sucesso",content = {
	    		@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
	    })           
    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void>excluir(@PathVariable Long codigo){
    	service.excluir(codigo);
    	return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
