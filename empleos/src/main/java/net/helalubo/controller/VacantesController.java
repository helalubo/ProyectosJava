package net.helalubo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.helalubo.model.Vacante;
import net.helalubo.service.IVacanteService;
import net.helalubo.service.VacantesServiceImpl;

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
		
		return "listCategorias";
	}
	
	@RequestMapping(value ="/create",method=RequestMethod.GET)
	public String Crear(){
		
		return "formVacante";
	}
	
//	utilizo este metodo post para tomar los datos del formulario y guardarlos o manipularlos a antojo. usando requestparam
//	lo impornte es que dentro de los requestparam coincida con el name de los input que toman datos en el formulario
	
	@RequestMapping(value ="/save",method=RequestMethod.POST)
	public String Guardar(@RequestParam("nombre") String nombre, @RequestParam("descripcion")  String descripcion){
		
		//Aca ya podria guardar datos en bases de datos.
		
		System.out.print("Categoria: " + nombre);
		System.out.print("Descripcion: " + descripcion);
		
		return "listCategorias";
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
	
}
