package br.com.estoque.erros;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TratandoErros {
   
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?>tratarErro404(){
		return ResponseEntity.notFound().build();
		
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?>tratarErro400(MethodArgumentNotValidException ex){		
		var erros = ex.getFieldErrors();
		return ResponseEntity.badRequest().body(erros.stream().map(TratarErros::new).toList());
	}
	public record TratarErros(String campo,String mensagem) {
		public TratarErros(FieldError erro) {
			this(erro.getField(),erro.getDefaultMessage());
		}
	}
}
