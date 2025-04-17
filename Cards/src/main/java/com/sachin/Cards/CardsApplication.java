package com.sachin.Cards;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@SpringBootApplication
@OpenAPIDefinition(info =
	@Info(title = "Accounts Microservice REST API Documentation",
			description = "Bank Accounts Microservice REST API Documentation", version = "v1"),
			externalDocs = @ExternalDocumentation(description = "Bank Accounts Microservice REST API Documentation")
)
public class CardsApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(CardsApplication.class, args);
	}
}
