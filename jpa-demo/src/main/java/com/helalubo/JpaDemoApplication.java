package com.helalubo;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.helalubo.model.Categoria;
import com.helalubo.repository.CategoriasRepository;

///Agregamos CommandLineRunner para poder hacer aplicacion de consola y hago override al metodo run, dentro de este metodo sera ejecutado al iniciar la aplicacion y terminara la app cuando temina el metodo

@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner {

	
	//inyectamos repositorio con @Autowired y con esto ya tenemos los metodos para ser usados, el objeto es de clase de la interface 
	@Autowired
	CategoriasRepository repo;
	
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stu
		System.out.println(repo);
		//actualizar();
		//buscarPorId();
		//guardar();
		//eliminar();
		//conteo();
		 //eliminarTodos();
		//EncontrarPorIds();
		//buscarTodos();
		//existe();
		//guardarTodos();
	}

	private void guardar() {

		Categoria cat = new Categoria();
		cat.setNombre("Finanzas");
		cat.setDescripcion("Trabajos relacionados con finanzas y contabilidad");
		
		repo.save(cat);
		

	}
	
	private void buscarPorId() {
		
		//Para recuperar datos de la base de datos por id vamos a usar el metodo findById el cual devuelve (siempre mirar que devuelven las funciones)
		//un objeto de tipo optional, dentro de <> debemos colocar como parametro el tipado del objeto que se espera obtener en la consulta realizada con el CrudRepository
		
		
		//¿Que es opcional? opcional es un contenedor de objeto que puede contener o no un valor nulo, este se encuentra en la clase java.util en caso de que de diferente de null
		//podemos usar isPresent() para verificarlo y podemos usar get() para obtenerlo desde el contenedor
		
		Optional<Categoria> optional =repo.findById(1);
		
		
		if(optional.isPresent())
		{
			System.out.println(optional.get());
			
		}else {
			System.out.println("No se encuentran registros");
		}
		
		
		
		
	}
	
	private void actualizar(){
		
	   
		Optional<Categoria> optional =repo.findById(1);
		
		if(optional.isPresent())
		{
		Categoria catTmp =	optional.get();
		
		catTmp.setNombre("Ingenieria de software");
		catTmp.setDescripcion("Desarrollo de sistemas");
		
		repo.save(catTmp);
		
			
		}else {
			System.out.println("No se encuentran registros");
		}
		
		
	}
	

	private void eliminar() {

		int idCategoria = 2;
		repo.deleteById(idCategoria);

	}
	
	
	private void conteo() {
	long count =	repo.count();
	
	System.out.println("Total de categorias: " + count);
	
	}
	
	private void eliminarTodos() {
		repo.deleteAll();
	}
	private void EncontrarPorIds() {
		
		List<Integer> ids = new LinkedList<Integer>();	
		Iterable<Categoria> categoriasEncontradas = new LinkedList<Categoria>();
		
		ids.add(2);
		ids.add(15);
		ids.add(5);
		ids.add(4);
		ids.add(1);
		
		
		
		categoriasEncontradas=	repo.findAllById(ids);
		
		for (Categoria categoria : categoriasEncontradas) {
			
			System.out.println(categoria);
			System.out.println();
		}
		
	}
	
	private void buscarTodos() {
		
		Iterable<Categoria> categoriasEncontradas = repo.findAll();
		
		
	for (Categoria categoria : categoriasEncontradas) {
			
			System.out.println(categoria);
			System.out.println();
		}
		
	}
	
	private void existe()
	{
		
	boolean rta= false;
		
		rta =repo.existsById(15);
		
		if(rta) {
			System.out.println("Existe el registro");
		}
	}
	
	
	private void guardarTodos() {
		
		
		repo.saveAll(getListaCategorias());
	}
	
	private List<Categoria> getListaCategorias ( ){
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
}
