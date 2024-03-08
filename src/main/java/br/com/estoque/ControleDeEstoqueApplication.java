package br.com.estoque;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
			title = "API-Controle de Estoque",
			version = "1.0",
			description = "Documentando uma API para controle de estoque de produtos",
			contact = @Contact(name = "Carlos Roberto", email = "crrsj1@gmail.com")
		)
	)
public class ControleDeEstoqueApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControleDeEstoqueApplication.class, args);
	}

}
