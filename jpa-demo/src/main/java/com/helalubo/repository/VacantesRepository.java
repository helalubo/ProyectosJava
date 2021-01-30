package com.helalubo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.helalubo.model.Vacante;

public interface VacantesRepository extends JpaRepository<Vacante, Integer> {

	List<Vacante> findByEstatus(String estatus);
	
	
	//IMPORTANTE: Para saber que tipo de datos pasar como parametros debemos mirar los tipos de datos
	//del model, por ejemplo si es un ID se debera pasar o como Long o como Integer
	
	List<Vacante> findByEstatusAndDestacadoOrderByIdDesc(String estatus, Integer destacado);
	
	
	List<Vacante>  findBySalarioBetweenOrderBySalarioDesc(double min, double max );
	
	
	List<Vacante> findByEstatusIn(String[] estatus);
	
}
