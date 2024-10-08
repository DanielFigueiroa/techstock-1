package br.com.techhub.techstock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenAPIConfig {

	@Bean
	public OpenAPI registrationOpenAPI() {
		return new OpenAPI().info(
			new Info().title("TechStock")
				.description("Especificação da API do TechStock")
				.version("1.0")
		);
	}

	//    @Bean

}
