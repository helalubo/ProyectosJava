package net.helalubo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import net.helalubo.model.Vacante;

/*Establecemos que la clase es controller*/

@Controller
public class HomeController {

	
	@GetMapping("/tabla")
	public String mostrarTabla(Model model)
	{
		List<Vacante> lista = getVacantes();
		model.addAttribute("vacantes", lista);
		
		
		return "tabla";
		
	}
	
	
	/*
	 * Uso variable dinamica para poder acceder a cada uno de los empleos, esto es por id. 
	 * Busca la vacante que sea igual al id y devuelve el objeto agregandolo al model para luego ser usada en la vista devuelta como string en la funccion.
	 * @see detalle.html
	 * 
	 * 
	 * */
	@GetMapping("/tabla/view/{id}")
	public String verDetalle(@PathVariable("id") int  idVacante, Model model)
	{
		
		List<Vacante> lista = getVacantes();
		Vacante vacante = new Vacante();
		
		for (Vacante vacanteAux : lista) {
			
			if(idVacante == vacanteAux.getId())
			{
				vacante = vacanteAux;
				break;
			}
		}
		
		
		model.addAttribute("vacante", vacante);
		
		return "detalle";
		
	}
	
	/**
	 * 
	 * Metodo para obtener una lista de objetos de tipo Vacante
	 * @return Lista de vacantes
	 * 
	 * 
	 * */
	
	private List<Vacante> getVacantes() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		List<Vacante> lista = new LinkedList<Vacante>();

		try {
			Vacante vacante1 = new Vacante(1, "Ingreniero Civil", "Solicitamos ing Civil para diseñar puente peatonal",
					sdf.parse("08-02-2019"), 8500.0,1);
			
			
			Vacante vacante2 = new Vacante(2, "Contador publico",
					"Empresa importante solicita contador con 5 años de experiencia titulado", sdf.parse("09-02-2019"),
					12000.0,0);
			Vacante vacante3 = new Vacante(3, "Ingeriero electronico",
					"Empresa internacional solicita ingeniero electronico para matenimiento de instalacion electrica",
					sdf.parse("10-02-2019"), 10500.0,0);
			Vacante vacante4 = new Vacante(4, "Diseñador grafico",
					"Solicitamos diseñador grafico titulado para diseñar publicidad de la empresa",
					sdf.parse("11-02-2019"), 7500.0,1);
			
			
//			agrego imagenes
			
			vacante1.setImagen("empresa1.png");
			vacante2.setImagen("empresa2.png");
			vacante4.setImagen("empresa3.png");
			
			lista.add(vacante1);
			lista.add(vacante2);
			lista.add(vacante3);
			lista.add(vacante4);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			
			System.out.print("Error: "+e.getMessage());
	
		}

		return lista;

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
