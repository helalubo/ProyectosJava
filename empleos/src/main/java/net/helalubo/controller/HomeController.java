package net.helalubo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.helalubo.model.Perfil;
import net.helalubo.model.Usuario;
import net.helalubo.model.Vacante;
import net.helalubo.service.IUsuarioService;
import net.helalubo.service.IVacanteService;

/*Establecemos que la clase es controller*/

@Controller
public class HomeController {

//Cuando quiero usar un servicio (previamente marcado con la etiqueta @Service ) tengo que usar la etiqueta  @Autowired, esto indicara
	// Que estoy utilizando un servicio con metodos ya declarados

	@Autowired
	private IVacanteService vacanteService;

	@Autowired
	private IUsuarioService usuarioService;

	@GetMapping("/signup")
	public String Crear(Vacante vacante, Model model){
		
		
		
		
		return "/usuarios/formRegistro"; 
	}
	
	
	
	@PostMapping("/signup")
	public String GuardaRegistro(Usuario usuario, BindingResult result, RedirectAttributes attributes, Model model) {
		for (ObjectError error : result.getAllErrors()) {

			System.out.print("Ocurrio un error: " + error.getDefaultMessage());
		}
		if (result.hasErrors()) {

			// model.addAttribute("categorias",categoriaService.buscarTodas());
			return "/usuarios/formRegistro";
		}
		
		
		
		usuario.setPerfiles( Arrays.asList(new Perfil(3)) );
		usuario.setFechaRegistro( new Date() );
		usuario.setEstatus(1);
		
		
		usuarioService.Guardar(usuario);
		
		attributes.addFlashAttribute("msg",new StringBuilder().append("Bienvenido ").append(usuario.getNombre()).toString());
		
		return "redirect:/";
		
	}

	@GetMapping("/vacantes")
	public String mostrarTabla(Model model) {
		List<Vacante> lista = vacanteService.buscarTodas();
		model.addAttribute("vacantes", lista);

		return "tabla";

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

		return "home";
	}

	// ModelAttribute sirve para agregar todos los atributos que quedamos, y todos
	// los atributos que instanciemos aqui van a estar habilitados para
	// Todos los metodos de este controlador

	@ModelAttribute
	public void setGenericos(Model model) {
		model.addAttribute("vacantes", vacanteService.buscarDestacadas());
	}

}
