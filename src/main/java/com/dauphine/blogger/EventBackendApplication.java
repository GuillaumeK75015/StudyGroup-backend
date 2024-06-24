package com.dauphine.blogger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "",
				description = "Blogger box endpoints and Api",
				contact = @Contact(name = "Guillaume", email="guillaume.karaouane@gmail.com"),
				version = "1.0.0"
				)
)

public class EventBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventBackendApplication.class, args);
	}

}
