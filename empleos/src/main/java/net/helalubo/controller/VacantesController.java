package net.helalubo.controller;


import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.helalubo.model.Vacante;
import net.helalubo.service.ICategoriaService;
import net.helalubo.service.IVacanteService;
import net.helalubo.util.Utileria;

@Controller

@RequestMapping("/vacantes")
public class VacantesController {

    @Value("${empleosapp.ruta.imagenes}")
	private String ruta;
	
	@Autowired
	private IVacanteService vacanteService;
	
	@Autowired
	//@Qualifier("categoriasServiceJpa") //con esto marcamos que la clase que sera implementada por la interface ICategoriaService sera categoriasServiceJpa
	private ICategoriaService categoriaService;
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idVancante,RedirectAttributes attributes) {
		
		System.out.println("Se a eliminado la vacante con id: " + idVancante);
		
		vacanteService.eliminar(idVancante);
		attributes.addFlashAttribute("msg", "La vacante fue eliminada con exito");
	
		return "redirect:/vacantes/index";
	}
	
	
	@RequestMapping(value ="/index",method=RequestMethod.GET)
	public String MostrarIndex(Model model) {
		
		List<Vacante> listaDeVacantes = vacanteService.buscarTodas();
		//System.out.print(vacante);
		model.addAttribute("vacantes",listaDeVacantes);
		
		
	
		return "vacantes/listVacantes";
	}
	
	@GetMapping("/create")
	public String Crear(Vacante vacante, Model model){
		
		
		
		//model.addAttribute("categorias", categoriaService.buscarTodas());
		return "vacantes/formVacante";
	}
	
//	utilizo este metodo post para tomar los datos del formulario y guardarlos o manipularlos a antojo. usando requestparam
//	lo impornte es que dentro de los requestparam coincida con el name de los input que toman datos en el formulario

	
	
	///Gracias a que los atributos name de los input del formulario coinciden con los nombres de los campos de la clase vacante
	//Podemos adquirir sus parametros y obtenerlos directamente en 
	
	
	//BindingResult es un objeto que se utiliza para verificar errores en los datos
	//Esto gracias a su metodo hasErros que nos permite verificar si tiene errores y en el caso de que haya algun error en 
	//spring podremos redirigirlo a otra vista
	
	///agrego el parametro RedirectAttributes para que pueda agregar un dato a la redireccion del template que se necesita en el caso del
	//guardado la redireccion es a la lista cuando ya se muestra como actualizada.
	
	
	@PostMapping("/save")
	public String Guardar(Vacante vacante,BindingResult result , RedirectAttributes attributes, @RequestParam("archivoImagen") MultipartFile multiPart, Model model){
		
		
		//En caso de que el objeto result detecte algun tipo de error con hasErrors y nos retornara denuevo al formulario.
		
		///para ver los errores de consola podemos iterar los object errors de la lista de errores que esta dentro del objeto result de BindingResult
		
		
		//VERIFICACION DE ERRORES EN TOMA DE DATOS INPUT
		for (ObjectError error : result.getAllErrors()) {
			
			System.out.print("Ocurrio un error: " + error.getDefaultMessage());
		}
		if(result.hasErrors()) {
			
			
			//model.addAttribute("categorias",categoriaService.buscarTodas());
			return "vacantes/formVacante";
		}
		
		if(!multiPart.isEmpty())
		{
			//String ruta = "/empleos/img-vacantes/" //Para mac y linux
			//String ruta = "d:/empleos/img-vacantes/"; //Para windows
			String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
			
			if(nombreImagen != null) { //Chequea si la imagen se subio
				//Procesamos la variable nombreImagen para ligarla con el objeto
				vacante.setImagen(nombreImagen);
			}
					
		}
		
		
		
		vacanteService.Guardar(vacante);
		
		//Si quiero puedo agregar al RedirectAttributes un mensaje el cual se podra utilizar en el template donde se a redirigido la app
		//tambien por le hecho de ser un metodo post. el RedirectAttributes se usa cuando queremos mandar
		//alguna informacion a un template al cual se ha sido redirigido, esto usando addFlashAttribute
		attributes.addFlashAttribute("msg","Registro guardado");
		

	
	
		
		return "redirect:/vacantes/index";
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
	
	
	///Metodo para editar vacante
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") int idVacante,Model model) {
		
		
		
		Vacante vacante = vacanteService.buscarPorId(idVacante);
		
//		model.addAttribute("categorias",categoriaService.buscarTodas());
		model.addAttribute("vacante", vacante);
		
		return "/vacantes/formVacante";
		
	}
	
	//Metodo para agregar datos que son comunes para todo el controlador 
	//Esto gracias al decorador ModelAttribute
	@ModelAttribute
	public void setGenericos (Model model) {
		
		model.addAttribute("categorias",categoriaService.buscarTodas());
	}
	
	
	
}
