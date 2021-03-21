package net.helalubo.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.helalubo.model.Perfil;
import net.helalubo.model.Usuario;
import net.helalubo.model.Vacante;
import net.helalubo.service.ICategoriaService;
import net.helalubo.service.IUsuarioService;
import net.helalubo.service.IVacanteService;

/*Establecemos que la clase es controller*/

@Controller
public class HomeController {

	// Cuando quiero usar un servicio (previamente marcado con la etiqueta @Service
	// ) tengo que usar la etiqueta @Autowired, esto indicara
	// Que estoy utilizando un servicio con metodos ya declarados

	@Autowired
	private IVacanteService vacanteService;

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private ICategoriaService categoriaService;

	// USAREMOS EL METODO .encoder(passwordstring) para poder hacer la encriptacion.

	@Autowired
	private PasswordEncoder PasswordEncoder;

	@GetMapping("/login")
	public String mostrarLogin(Vacante vacante, Model model) {

		return "formLogin";
	}

	@GetMapping("/signup")
	public String Crear(Vacante vacante, Model model) {

		return "/usuarios/formRegistro";
	}

	// recordar configurar la seguridad del password con encriptacion

	@PostMapping("/signup")
	public String GuardaRegistro(Usuario usuario, BindingResult result, RedirectAttributes attributes, Model model) {

		for (ObjectError error : result.getAllErrors()) {

			System.out.print("Ocurrio un error: " + error.getDefaultMessage());
		}
		if (result.hasErrors()) {

			// model.addAttribute("categorias",categoriaService.buscarTodas());
			return "/usuarios/formRegistro";
		}

		// configuracion para encriptar contraseña

		String pwdplano = usuario.getPassword();
		String pwdEncriptado = PasswordEncoder.encode(pwdplano);

		usuario.setPassword(pwdEncriptado);
		usuario.setPerfiles(Arrays.asList(new Perfil(3)));
		usuario.setFechaRegistro(new Date());
		usuario.setEstatus(1);

		usuarioService.Guardar(usuario);

		attributes.addFlashAttribute("msg",
				new StringBuilder().append("Bienvenido ").append(usuario.getNombre()).toString());

		return "redirect:/";

	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

		// este es el metodo que destruye la sesion
		logoutHandler.logout(request, null, null);

		return "redirect:/login";
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

	// asegurarme que el import sea de
	// org.springframework.security.core.Authentication

	// ESTA ES LA FORMA DE recuperar A LOS DATOS DEL USUARIO QUE INICIO SESION
	// SE HACE CON UNA VARIABLE Authentication

	// una vez que tenemos el nombre del usuario podemos usarlo para crear
	// funcionabilidades accediendo a la DB con su nombre de usuario
	// tambien desde el dato Authentication podemos acceder a los roles y a partir
	// de los roles crear mas logica de programacion

	// NOTA: A PARTIR DE OBTENER EL USUARIO AUTENTIFICADO PUEDO USAR ESTE DATO PARA
	// OBTENER TODOS LOS DATOS DEL USUARIO, ESTO DESDE SU REPOSITORY USANDO JPA

	// Recuperamos el usuario y añadimos el usuario a la sesion, esto usando el tipo
	// de dato como parametro que es
	// HTTP SESSION. Esto para que podemas tener en cuenta los datos del usuario en
	// la sesion.

	@GetMapping("/index")
	public String mostrarIndex(Authentication auth, HttpSession session) {

		String username = auth.getName();
		System.out.println("Nombre de usuario: " + username);

		for (GrantedAuthority rol : auth.getAuthorities()) {
			System.out.println("ROL: " + rol.getAuthority());
		}
		if (session.getAttribute("usuario") == null) {

			Usuario usuario = usuarioService.buscarPorUsername(username);
			usuario.setPassword(null);
			System.out.println(usuario);

			session.setAttribute("usuario", usuario);
		}

		return "redirect:/";
	}

	@GetMapping("/")
	public String mostrarHome(Model model) {

		return "home";
	}

	@GetMapping("/search")
	public String buscar(@ModelAttribute("search") Vacante vacante, Model model) {

		// model.addAttribute("vacantes",
		// vacanteService.buscarVacantesPorCategoriaYDescripcion(vacante.getCategoria().getId(),
		// vacante.getDescripcion()));

		System.out.println(vacante);

		// Esta seria la configuracion para poder usar el LIKE en los querys, usando un
		// objeto
		// ExampleMarcher y pasandole como primer parametro del WithMartcher el nombre
		// de la propiedad
		// y luego el metodo contains para que verifique si esta siendo contenido
		// Where descripcion like '%?%'
		ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("descripcion",
				ExampleMatcher.GenericPropertyMatchers.contains());

		// agregamos el matcher a el example como segundo parametro en Example.of.
		// IMPORTANTE*****

		Example<Vacante> example = Example.of(vacante, matcher);
		List<Vacante> lista = vacanteService.buscarByExample(example);

		model.addAttribute("vacantes", lista);

		return "home";
	}

	/**
	 * InitBinder para Strings si los detecta vacios en el Data Bingding los settea
	 * a null
	 * 
	 * @param Binder
	 * 
	 * 
	 * 
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	// ModelAttribute sirve para agregar todos los atributos que quedamos, y todos
	// los atributos que instanciemos aqui van a estar habilitados para
	// Todos los metodos de este controlador

	@ModelAttribute
	public void setGenericos(Model model) {

		Vacante vacanteSearch = new Vacante();

		System.out.println("CREO VACANTE SEARCH");
		System.out.println(vacanteSearch);
		System.out.println("CREO VACANTE SEARCH");

		vacanteSearch.reset();

		model.addAttribute("vacantes", vacanteService.buscarDestacadas());
		model.addAttribute("categorias", categoriaService.buscarTodas());
		model.addAttribute("search", vacanteSearch);
	}

	@GetMapping("/bcrypt/{texto}")
	// para que devuelva texto plano directamente al nav de internet en vez de una
	// vista
	@ResponseBody
	public String encriptar(@PathVariable("texto") String texto) {

		return texto + " Encriptado: " + PasswordEncoder.encode(texto);

	}

}
