package com.helalubo;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.helalubo.model.Categoria;
import com.helalubo.model.Perfil;
import com.helalubo.model.Usuario;
import com.helalubo.model.Vacante;
import com.helalubo.repository.CategoriasRepository;
import com.helalubo.repository.PerfilesRepository;
import com.helalubo.repository.UsuariosRepository;
import com.helalubo.repository.VacantesRepository;

///Agregamos CommandLineRunner para poder hacer aplicacion de consola y hago override al metodo run, dentro de este metodo sera ejecutado al iniciar la aplicacion y terminara la app cuando temina el metodo

@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner {

	// inyectamos repositorio con @Autowired y con esto ya tenemos los metodos para
	// ser usados, el objeto es de clase de la interface
	@Autowired
	private CategoriasRepository repoCategorias;
	@Autowired
	private VacantesRepository repoVacantes;
	@Autowired
	private UsuariosRepository repoUsuarios;
	@Autowired
	private PerfilesRepository repoPerfiles;

	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stu
		//System.out.println(repoCategorias);

		/// CRUD REPOSITORY

		// actualizar();
		// buscarPorId();
		// guardar();
		// eliminar();
		// conteo();
		// eliminarTodos();
		// EncontrarPorIds();
		// buscarTodos();
		// existe();
		// guardarTodos();
		
		
	

		//// JPA REPOSITORY

//		buscarTodosJpa();
//		borrarTodoEnBloque();
		// buscarTodosOrdenados();
		//buscarTodosPaginacion();
		
		//buscarTodosPaginacionOrdenados();
		
		
		
		
		///repoVacantes
		
		
		//buscarVacantes();
		 //guardarVacante();
		
		//Utilizando repoPerfiles
		
		//crearPerfilesAplicacion();
		
		
		///RepoUsuario
		
//		crearUsuarioConUnPerfil();
//		
//		for (Usuario user : repoUsuarios.findAll()) {
//			
//			System.out.println(user);
//			
//			System.out.println("*********************************\n");
	//	}
		    
		
		
		/*
		 * 
		 * Buscando un usuario por id y desplegando sus Perfiles
		 * */
		
		//buscarUsuario(1);
		
//      buscarVacantesPorEstatus();

	
	
	
		buscarVacantesPorDestacadoEstatus();
	
	
	}
	
	/**
	 * Query Method: buscar Vacantes por Destacado y estatus ordenado por id Desc
	 * 
	 * 
	 * 
	 */
	
	
	public void buscarVacantesPorDestacadoEstatus() {
		
		 List<Vacante> vacantesConEstatusCreada = repoVacantes.findByEstatusAndDestacadoOrderByIdDesc("Aprobada", 1);
	        vacantesConEstatusCreada.forEach( (Vacante vacante) ->  System.out.println(vacante.getId()));
		
	}
	
	
	
	/**
	 * Qyery Method Busca vacantes por estatus y los muestra
	 * 
	 */
	
	public void buscarVacantesPorEstatus() {
		 List<Vacante> vacantesConEstatusCreada = repoVacantes.findByEstatus("Creada");
	        vacantesConEstatusCreada.forEach( (Vacante vacante) ->  System.out.println(vacante.getId()));
	}
	
	
	/**
	 * Metodo para buscar un usuario y desplegar sus perfiles asociados
	 * 
	 * 
	 */
	
	public void buscarUsuario(int idUsuario) {
		
	Optional<Usuario> userOpc =	repoUsuarios.findById(idUsuario);
	
	if(userOpc.isPresent()) {
		
		Usuario user = userOpc.get();
		List<Perfil> perfiles =   user.getPerfiles();
		
		System.out.println("********************");
		System.out.println(user);
		System.out.println("********************");
//		for (Perfil perfil : perfiles ) {
//			
//			
//			System.out.println(perfil);
//			System.out.println("********************");
//			
//		}
		
		perfiles.forEach((Perfil perfil ) -> System.out.println(perfil));
	}else {
		System.out.println("Usuario no encontrado");
	}
	
	
		
		
		
		
	}
	
	
	
	
	
	//Usando crear usuario de la clase Usuario  con dos perfiles
	
	
	private void crearUsuarioConUnPerfil() {
		
		Usuario user = new Usuario();
		
		user.setNombre("Daiana De Moraiz");
		user.setEmail("DaianaDeMoraiz@gmail.com");
		user.setFechaRegistro(new Date());
		user.setUsername("DaianaDM");
		user.setPassword("758865");
		user.setEstatus(1);
		
		Perfil perfil1 = new Perfil();
		perfil1.setId(2);
		
		Perfil perfil2 = new Perfil();
		perfil2.setId(3);
		
		user.agregarPerfil(perfil1);
		user.agregarPerfil(perfil2);
		
		repoUsuarios.save(user);
		
		
		
	}
	
	
	
	
	///RepoVacantes

		private void buscarVacantes() {
			List<Vacante> lista = repoVacantes.findAll();
			
			for (Vacante vacante : lista) {
				System.out.println("id: " + vacante.getId() + " nombre: " + vacante.getNombre() + "categoria: " + vacante.getCategoria().getNombre());
			}
		}
		
		
		private void guardarVacante() {
			
			Vacante vacante = new Vacante();
			vacante.setNombre("Profesor de Matematicas");
			vacante.setDescripcion("Escuela primaria solicita profesor para curso de Matematicas");
			vacante.setFecha (new Date());
			vacante.setSalario(8500.0);
			vacante.setEstatus("Aprobada");
			vacante.setDestacado(0);
			vacante.setImagen("escuela.png");
			vacante.setDetalles("<h1>Los requisitos para profesor de Matematicas</h1>");
			
			Categoria cat = new Categoria();
			cat.setId(15); //Con establecer el id alcanza
			vacante.setCategoria(cat);
			
			repoVacantes.save(vacante);
			
		}
	
	
	
	
	///RepoCategorias

	private void guardar() {

		Categoria cat = new Categoria();
		cat.setNombre("Finanzas");
		cat.setDescripcion("Trabajos relacionados con finanzas y contabilidad");

		repoCategorias.save(cat);

	}

	private void buscarPorId() {

		// Para recuperar datos de la base de datos por id vamos a usar el metodo
		// findById el cual devuelve (siempre mirar que devuelven las funciones)
		// un objeto de tipo optional, dentro de <> debemos colocar como parametro el
		// tipado del objeto que se espera obtener en la consulta realizada con el
		// CrudRepository

		// ¿Que es opcional? opcional es un contenedor de objeto que puede contener o no
		// un valor nulo, este se encuentra en la clase java.util en caso de que de
		// diferente de null
		// podemos usar isPresent() para verificarlo y podemos usar get() para obtenerlo
		// desde el contenedor

		Optional<Categoria> optional = repoCategorias.findById(1);

		if (optional.isPresent()) {
			System.out.println(optional.get());

		} else {
			System.out.println("No se encuentran registros");
		}

	}

	private void actualizar() {

		Optional<Categoria> optional = repoCategorias.findById(1);

		if (optional.isPresent()) {
			Categoria catTmp = optional.get();

			catTmp.setNombre("Ingenieria de software");
			catTmp.setDescripcion("Desarrollo de sistemas");

			repoCategorias.save(catTmp);

		} else {
			System.out.println("No se encuentran registros");
		}

	}

	private void eliminar() {

		int idCategoria = 2;
		repoCategorias.deleteById(idCategoria);

	}

	private void conteo() {
		long count = repoCategorias.count();

		System.out.println("Total de categorias: " + count);

	}

	private void eliminarTodos() {
		repoCategorias.deleteAll();
	}

	private void EncontrarPorIds() {

		List<Integer> ids = new LinkedList<Integer>();
		Iterable<Categoria> categoriasEncontradas = new LinkedList<Categoria>();

		ids.add(2);
		ids.add(15);
		ids.add(5);
		ids.add(4);
		ids.add(1);

		categoriasEncontradas = repoCategorias.findAllById(ids);

		for (Categoria categoria : categoriasEncontradas) {

			System.out.println(categoria);
			System.out.println();
		}

	}

	private void buscarTodos() {

		Iterable<Categoria> categoriasEncontradas = repoCategorias.findAll();

		for (Categoria categoria : categoriasEncontradas) {

			System.out.println(categoria);
			System.out.println();
		}

	}

	private void existe() {

		boolean rta = false;

		rta = repoCategorias.existsById(15);

		if (rta) {
			System.out.println("Existe el registro");
		}
	}

	private void guardarTodos() {

		repoCategorias.saveAll(getListaCategorias());
	}

	private List<Categoria> getListaCategorias() {
		List<Categoria> lista = new LinkedList<Categoria>();
		// Categoria 1
		Categoria cat1 = new Categoria();
		cat1.setNombre("Programador de Blockchain");
		cat1.setDescripcion("Trabajos relacionados con Bitcoin y Criptomonedas");
		// Categoria 2
		Categoria cat2 = new Categoria();
		cat2.setNombre("Soldador/Pintura");
		cat2.setDescripcion("Trabajos relacionados con soldadura, pintura y enderezado");
		// Categoria 3
		Categoria cat3 = new Categoria();
		cat3.setNombre("Ingeniero Industrial");
		cat3.setDescripcion("Trabajos relacionados con Ingenieria industrial.");

		lista.add(cat1);
		lista.add(cat2);
		lista.add(cat3);

		return lista;
	}

	////////////////////////////////////////////////////////// JPAREPOSITORY

	private void buscarTodosJpa() {

		List<Categoria> categorias = repoCategorias.findAll();

		for (Categoria categoria : categorias) {

			System.out.println(categoria.getId() + "" + categoria.getNombre());
		}

	}

	private void borrarTodoEnBloque() {

		repoCategorias.deleteAllInBatch();
	}

	/**
	 * 
	 * Metodo findAll [Ordena por un campo] - Interfaz PagingAndSortingRepositorty
	 * (Esta es extiende a jpaRepository asi que al usar esta directamente ya
	 * podremos usar los metodos de dicha interface)
	 * 
	 * 
	 * Se debera usar La clase Sort como parametro del metodo findAll para poder
	 * indicarle el criterio de ordenamiento, en este caso es el nombre que es el
	 * tipo de la clase MODELO
	 *
	 * Por defecto es por manera ascendente, para hacerlo de forma descendente
	 * debemos llamar al metodo descending() con el operador .
	 *
	 *
	 */

	private void buscarTodosOrdenados() {

		List<Categoria> categorias = repoCategorias.findAll(Sort.by("nombre").descending());

		for (Categoria categoria : categorias) {

			System.out.println(categoria.getId() + " " + categoria.getNombre());
		}
	}

	/**
	 * Buscar todos con paginacion
	 * 
	 * la idea de paginacion es muy sencilla, es determinar de cuanto en cuanto
	 * quiero obtener los datos
	 * 
	 * Este metodo devolvera en este caso un objeto de tipo page, del cual luego
	 * podemos extraer la lista con getContent()
	 * 
	 * El objeto page podemos tambien obtener informacion con respecto a la cantidad
	 * de registros y la cantidad de paginas que pueden generarse dependiendo de
	 * cuanto en cuanto pediste la lista y la cantidad de registros que puedan
	 * acerce con ese numero requerido
	 * 
	 * 
	 * IMPORTANTE El primer parametro de PageRequest.of indica que pagina se va a
	 * ver y el segundo parametro indica la cantidad de registros por pagina, para
	 * adquirir nuevas paginas basta con sumarle 1 a la pagina por ejemplo.
	 */

	private void buscarTodosPaginacion() {

		Page<Categoria> page = repoCategorias.findAll(PageRequest.of(2, 5));

		System.out.println("Contar registros " + page.getTotalElements());
		System.out.println("Total de paginas " + page.getTotalPages());

		for (Categoria categoria : page.getContent()) {

			System.out.println(categoria.getId() + " " + categoria.getNombre());
		}

	}

	/**
	 * Usamos metodo de paginacion con ordenamiento,
	 * EL MISMO METODO BY de pageRequest nos ofrece poner como tercer parametro un ordenamiento.
	 * Es mezclar el de paginacion con el de ordenamiento
	 * 
	 */
	
	private void buscarTodosPaginacionOrdenados() {

		Page<Categoria> page = repoCategorias.findAll(PageRequest.of(0, 5,Sort.by("nombre")));

		System.out.println("Contar registros " + page.getTotalElements());
		System.out.println("Total de paginas " + page.getTotalPages());

		for (Categoria categoria : page.getContent()) {

			System.out.println(categoria.getId() + " " + categoria.getNombre());
		}

	}
	
	
	///Creacion de perfiles en la aplicacion, usando PerfilesRepository
	
	
	private void crearPerfilesAplicacion() {
		
		repoPerfiles.saveAll(getPerfilesAplicacion());
		
	}
	
	
	///Metodo que hardcodea los perfiles para ser usado dentro de crearPerfilesAplicacion

	
	private List<Perfil> getPerfilesAplicacion (){
		List<Perfil> lista = new LinkedList<Perfil>();
		Perfil per1 = new Perfil();
		per1.setPerfil("SUPERVISOR");
		Perfil per2 = new Perfil();
		per2.setPerfil("ADMINISTRADOR");
		Perfil per3 = new Perfil();
		per3.setPerfil("USUARIO");
		lista.add(per1);
		lista.add(per2);
		lista.add(per3);
		return lista;
				
				
		}
}
