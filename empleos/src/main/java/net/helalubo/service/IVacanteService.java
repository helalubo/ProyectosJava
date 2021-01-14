package net.helalubo.service;

import java.util.List;

import net.helalubo.model.Vacante;

public interface IVacanteService {

	List<Vacante> buscarTodas();
	
	Vacante buscarPorId(int id);
}