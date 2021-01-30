package com.helalubo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.helalubo.model.Categoria;

//Creo interface que se extienda de un CrudRepository, este reposutorio tiene metodos ya implementados por spring, vamos a poder usarlos para interactuar con base de datos

//Con jpa repository tendremos mas metodos para poder interactuar con base de datos, en este caso otorgara la posibilidad de obtener datos
//con ordenamiento o con paginacion, tambien metodos mas elaborados

//public interface CategoriasRepository extends CrudRepository<Categoria, Integer>
public interface CategoriasRepository extends JpaRepository<Categoria, Integer> {
	
	

}
