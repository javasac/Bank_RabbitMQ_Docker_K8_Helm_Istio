package com.sachin.Loans;
import com.sachin.Loans.dto.LoansContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableConfigurationProperties(value={LoansContactInfoDto.class})
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@SpringBootApplication
@OpenAPIDefinition(info =
	@Info(title = "Accounts Microservice REST API Documentation",
			description = "Bank Accounts Microservice REST API Documentation", version = "v1"),
			externalDocs = @ExternalDocumentation(description = "Bank Accounts Microservice REST API Documentation")
)
public class LoansApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(LoansApplication.class, args);
	}
}
