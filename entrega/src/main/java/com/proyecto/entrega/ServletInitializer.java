package com.proyecto.entrega;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//Hereda de SpringBootServletInitializer, que es una clase de Spring para inicializar apps en servidores externos
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
    //le dice al servidor cuál es la clase principal de la app
    //arranca desde EntregaApplication
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(EntregaApplication.class);
	}
}
/*clase auxiliar que Spring Boot genera cuando quieres desplegar tu aplicación en un servidor externo
* en lugar de java -jar
* sirve para que pueda convertirse en un WAR (Web ARchive)
*/