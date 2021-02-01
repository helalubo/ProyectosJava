package net.helalubo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.helalubo.model.Categoria;
import net.helalubo.model.Vacante;
import net.helalubo.service.ICategoriaService;
import net.helalubo.service.IVacanteService;


@Controller
@RequestMapping(value = "/categorias")
//Si quiero hacer subdirectorios en la url debemos poner el requets mapping a niver  de la clase, esta es otra forma de usar request mapping
//LO INTERESANTE DE ESTOS ES PODER CREAR RUTAS CON SUBRUTAS
public class CategoriasController {

	// creo request para acceder a datos de tipo get, post, create o el que necesite
	// y asi traer informacion del frontend

	/// Creo controladores con requestMapping asi poder acceder a datos de por
	/// ejemplo formularios de un frond.

	@Autowired
	//@Qualifier("categoriasServiceJpa")
	private ICategoriaService categoriaService;

	@RequestMapping("/index")
	public String MostrarIndex(Model model) {

		List<Categoria> listaDeCategorias = categoriaService.buscarTodas();
		// System.out.print(vacante);
		model.addAttribute("categorias", listaDeCategorias);
		;

		return "categorias/listCategorias";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String Crear() {

		return "categorias/formCategoria";
	}

//	utilizo este metodo post para tomar los datos del formulario y guardarlos o manipularlos a antojo. usando requestparam
//	lo impornte es que dentro de los requestparam coincida con el name de los input que toman datos en el formulario

	@PostMapping("/save")
	public String Guardar(Categoria categoria, BindingResult result, RedirectAttributes attributes) {

		// Aca ya podria guardar datos en bases de datos.

		for (ObjectError error : result.getAllErrors()) {

			System.out.print("Ocurrio un error: " + error.getDefaultMessage());
		}
		if (result.hasErrors()) {
			return "categorias/formCategoria";
		}

		categoriaService.Guardar(categoria);
		attributes.addFlashAttribute("msg","Registro guardado");

		return "redirect:/categorias/index";
	}
	



}
