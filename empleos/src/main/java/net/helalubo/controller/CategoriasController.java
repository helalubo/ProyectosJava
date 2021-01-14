package net.helalubo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value ="/categorias")
//Si quiero hacer subdirectorios en la url debemos poner el requets mapping a niver  de la clase, esta es otra forma de usar request mapping
//LO INTERESANTE DE ESTOS ES PODER CREAR RUTAS CON SUBRUTAS
public class CategoriasController {

	
	//creo request para acceder a datos de tipo get, post, create o el que necesite y asi traer informacion del frontend
	
	///Creo controladores con requestMapping asi poder acceder a datos de por ejemplo formularios de un frond. 
	
	
	
	
	@RequestMapping(value ="/index",method=RequestMethod.GET)
	public String MostrarIndex(Model model) {
		
		return "listCategorias";
	}
	
	@RequestMapping(value ="/create",method=RequestMethod.GET)
	public String Crear(){
		
		return "formCategoria";
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
	
	
	
	
	
	
	
}
