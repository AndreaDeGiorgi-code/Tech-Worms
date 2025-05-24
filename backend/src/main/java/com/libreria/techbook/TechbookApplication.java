package com.libreria.techbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point dell'applicazione Spring Boot.
 * Avvia il contesto Spring e il server embedded (es. Tomcat).
 */

@SpringBootApplication
public class TechbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechbookApplication.class, args);
	}

}
