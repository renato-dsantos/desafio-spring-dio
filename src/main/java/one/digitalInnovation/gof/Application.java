package one.digitalInnovation.gof;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * Desafio da DIO
 * 
 * 
 * Projeto Spring Boot gerado via Spring Initializar
 * Os seguintes módlos fora selecionado
 * Spring data JPA
 * Spring Web
 * Hs Database
 * OpenFeign
 * 
 * @author Renato-dsantos
 */


@EnableFeignClients
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}