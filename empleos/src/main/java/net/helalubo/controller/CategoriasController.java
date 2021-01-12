package net.helalubo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CategoriasController {

	
	///Creo controladores con requestMapping asi poder acceder a datos de por ejemplo formularios de un frond. 
	
	@RequestMapping(value ="/index",method=RequestMethod.GET)
	public String MostrarIndex(Model model) {
		
		return "Categorias/listCategorias";
	}
	
	@RequestMapping(value ="/create",method=RequestMethod.GET)
	public String Crear(){
		
		return "Categorias/formCategoria";
	}
	
	@RequestMapping(value ="/save",method=RequestMethod.GET)
	public String Guardar(){
		
		return "Categorias/listCategorias";
	}
}
