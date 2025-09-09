// Define el paquete al que pertenece esta clase. Ayuda a organizar el código.
package com.proyecto.entrega;

// Importa las clases necesarias de Spring Boot.
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Clase principal de la aplicación.
@SpringBootApplication //Prepara y configura todo automaticamente.
public class EntregaApplication {

	public static void main(String[] args) {
		// SpringApplication arrancará la aplicación, creando un ApplicationContext de Spring.
		SpringApplication.run(EntregaApplication.class, args);
	}
}
