package com.helalubo.helalubo;

import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/*
 * 
 * la logica de la funcion es sencilla, solo 
 * establece un valor que puede pasarse como 
 * parametro en la URL, esto dentro de la firma 
 * de los parametros de la funcion, estableciendo 
 * el nombre del valor que estara en la url y que hacer 
 * con el, y que en caso de que no exista se mande una 
 * palabra por default en caso de ser necesario para la 
 * logica de dicho metodo. 
 * 
 * 
 * aqui vemos el tipico proyecto hola mundo, podemos ver como 
 * se establecen etiquetas, estas etiquetas estan arriba de 
 * la clase o de un metodo de la clase, este mapeando cual 
 * sera la url para aceder al return que en este caso es un tipo de
 *  dato string, al se string lo muestra directamente,
 */


@SpringBootApplication
@RestController
public class PruebaFuncionamientoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PruebaFuncionamientoApplication.class, args);
	}
	
	@GetMapping("/hola")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
	return String.format("Hello %s!", name);
	}

}
