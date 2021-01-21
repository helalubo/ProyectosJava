package com.helalubo.repository;

import org.springframework.data.repository.CrudRepository;

import com.helalubo.model.Categoria;

//Creo interface que se extienda de un CrudRepository, este reposutorio tiene metodos ya implementados por spring, vamos a poder usarlos para interactuar con base de datos

public interface CategoriasRepository extends CrudRepository<Categoria, Integer> {
	
	

}
