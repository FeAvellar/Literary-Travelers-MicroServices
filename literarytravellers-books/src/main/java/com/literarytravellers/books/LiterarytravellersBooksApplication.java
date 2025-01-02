package com.literarytravellers.books;

import org.flywaydb.core.Flyway;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class LiterarytravellersBooksApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiterarytravellersBooksApplication.class, args);
	}

	@PostConstruct
	public void showEasterEggHint() {
		System.out.println("✨ Dica: Explore o endpoint '/easter-egg' e descubra algo especial! 😉");
	}

	  @Bean
    public CommandLineRunner run(Flyway flyway) {
        return args -> {
            // Inicializar as migrações manualmente
            flyway.migrate();
        };
	}
}
