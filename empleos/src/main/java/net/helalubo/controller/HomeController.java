package net.helalubo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import net.helalubo.model.Vacante;
import net.helalubo.service.IVacanteService;
import net.helalubo.service.VacantesServiceImpl;

/*Establecemos que la clase es controller*/

@Controller
public class HomeController {

//Cuando quiero usar un servicio (previamente marcado con la etiqueta @Service ) tengo que usar la etiqueta  @Autowired, esto indicara
	//Que estoy utilizando un servicio con metodos ya declarados
	
	@Autowired
	private IVacanteService vacanteService;
	
	
	@GetMapping("/vacantes")
	public String mostrarTabla(Model model)
	{
		List<Vacante> lista = vacanteService.buscarTodas();
		model.addAttribute("vacantes", lista);
		
		
		return "tabla";
		
	}
	


	


	@GetMapping("/detalle")
	public String mostrarDetalle(Model model) {

		Vacante vacante = new Vacante();
		vacante.setNombre("Ingeniero de comunicaciones");
		vacante.setDescripcion("Se solicita ingeniero para dar soporte a intranet");
		vacante.setFecha(new Date());
		vacante.setSalario(9700.0);
		model.addAttribute("vacante", vacante);

		return "detalle";

	}

	/*
	 * Mapeo la metodo controlador que en este caso devuelve un string en formato
	 * por defecto para html entre parentesis del getMapping pondemos la URL, en
	 * este caso ponemos solo la barra debido a que se trata del home
	 */

	/*
	 * Usamos el tipo de dato Model de forma generar para crear atributos y
	 * utilizarlos en el html*
	 */

	@GetMapping("/acerca")
	public String mostrarAcerca(Model model) {

		StringBuilder sb = new StringBuilder();

		sb.append("Miguel Alejandro De Moraiz 2020");

		model.addAttribute("detalle", sb.toString());
		return "acerca";

	}

	@GetMapping("/listado")
	public String mostrarListado(Model model) {

		List<String> lista = new LinkedList<String>();
		lista.add("Ingeniero de Sistemas");
		lista.add("Auxiliar de Contabilidad");
		lista.add("Vendedor");
		lista.add("Arquitecto");
		lista.add("Programador");

		model.addAttribute("empleos", lista);

		return "listado";

	}

	@GetMapping("/")
	public String mostrarHome(Model model) {

		// StringBuilder sb = new StringBuilder();
		// sb.append("Bienvenido").append("Alejandro De Moraiz");

		/*
		 * sb.append("Bienvenidos a Empleos App"); model.addAttribute("user",
		 * sb.toString());
		 */
		String nombre = "Auxiliar de Contabilidad";
		Date fechaPub = new Date();
		double salario = 9000.0;
		boolean vigente = true;

		model.addAttribute("nombre", nombre);
		model.addAttribute("fechaPub", fechaPub);
		model.addAttribute("salario", salario);
		model.addAttribute("vigente", vigente);

		model.addAttribute("fecha", new Date());

		return "home";
	}
}
