package com.example.Banking.Application;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
		title = "Banking Application",
		description = "Backend API Services for Banking Application ",
		version = "",
		contact = @Contact(
				name = "Ravindar Reddy",
				email = "ravindarreddy@usf.edu",
				url = " https://github.com/ravindar9121/Banking_Application.git"
		),
		license = @License(
				name = "Apache 2.0",
				url = "https://www.apache.org/licenses/LICENSE-2.0"
		)),
		externalDocs = @ExternalDocumentation(
				description = "Banking Application Documentation",
				url = " https://github.com/ravindar9121/Banking_Application.git"
		)
)
public class BankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingApplication.class, args);
	}

}
