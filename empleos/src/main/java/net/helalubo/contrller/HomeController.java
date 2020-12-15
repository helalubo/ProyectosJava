package net.helalubo.contrller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/*Establecemos que la clase es controller*/

@Controller
public class HomeController {

	
/*Mapeo  la metodo controlador que en este caso devuelve un string en formato por defecto para html 
 * entre parentesis del getMapping pondemos la URL, en este caso ponemos solo la barra debido a que se 
 * trata del home*/
	
	
	@GetMapping("/")
	public String mostrarHome() {
		
		
		return "home";
	}
}
