package com.aquilesdias.challengebossabox;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title = "Tools API",
				version = "1",
				description = "API development to resolving challenge")
		)
@SpringBootApplication
public class ChallengeBossaboxApplication {

	public static void main(String[] args) {

		SpringApplication.run(ChallengeBossaboxApplication.class, args);
	}

}
