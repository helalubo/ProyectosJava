package net.helalubo.controller;


import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.helalubo.model.Vacante;
import net.helalubo.service.IVacanteService;

@Controller

@RequestMapping("/vacantes")
public class VacantesController {

	@Autowired
	private IVacanteService vacanteService;
	
	@GetMapping("/delete")
	public String eliminar(@RequestParam("id") int idVancante, Model model) {
		
		
		model.addAttribute("id",idVancante);
		System.out.print("Borrando id " + idVancante);
		
		return "mensaje";
	}
	
	
	@RequestMapping(value ="/index",method=RequestMethod.GET)
	public String MostrarIndex(Model model) {
		
		List<Vacante> listaDeVacantes = vacanteService.buscarTodas();
		//System.out.print(vacante);
		model.addAttribute("vacantes",listaDeVacantes);
		return "vacantes/listVacantes";
	}
	
	@RequestMapping(value ="/create",method=RequestMethod.GET)
	public String Crear(){
		
		return "vacantes/formVacante";
	}
	
//	utilizo este metodo post para tomar los datos del formulario y guardarlos o manipularlos a antojo. usando requestparam
//	lo impornte es que dentro de los requestparam coincida con el name de los input que toman datos en el formulario

	
	
	///Gracias a que los atributos name de los input del formulario coinciden con los nombres de los campos de la clase vacante
	//Podemos adquirir sus parametros y obtenerlos directamente en 
	
	@PostMapping("/save")
	public String Guardar(Vacante vacante,Model model){
		
		//Aca ya podria guardar datos en bases de datos.
		
		vacanteService.Guardar(vacante);
		List<Vacante> listaDeVacantes = vacanteService.buscarTodas();
		//System.out.print(vacante);
		model.addAttribute("vacantes",listaDeVacantes);
	
		
		return "vacantes/listVacantes";
	}
	
	
	
	
	
	/*
	 * Uso variable dinamica para poder acceder a cada uno de los empleos, esto es por id. 
	 * Busca la vacante que sea igual al id y devuelve el objeto agregandolo al model para luego ser usada en la vista devuelta como string en la funccion.
	 * @see detalle.html
	 * 
	 * 
	 * */
	
	@GetMapping("/view/{id}")
	public String verDetalle(@PathVariable("id") int  idVacante, Model model)
	{
		
	
		Vacante vacante =vacanteService.buscarPorId(idVacante);
		
		//agrego la variable al modelo para usar en frontend
		
		model.addAttribute("vacante", vacante);
		
		
		//retorno la vista de frontend donde debera utilizarse la vacante
		return "detalle";
		
	}
	
	//Metodo que pareca implicitamente en el momento de que spring trabaja con los formularios input,
	//en este caso toma una fecha en string y la transforma en el dato deseado, sirve para varios tipos de datos.
	
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		webDataBinder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
	}
}
