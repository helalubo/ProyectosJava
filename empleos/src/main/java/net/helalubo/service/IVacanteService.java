package net.helalubo.service;

import java.util.List;

import org.springframework.data.domain.Example;

import net.helalubo.model.Vacante;

public interface IVacanteService {

	List<Vacante> buscarTodas();
	
	Vacante buscarPorId(int id);
	
	void Guardar(Vacante vacante);
	
	List<Vacante> buscarDestacadas();
	
	
	
	void eliminar(Integer id);
	
	
	List<Vacante> buscarVacantesPorCategoria(Integer idCategoria);

	void GuardarTodas(List<Vacante> vacantes);
	
	
	/*
	 * Query by example acepta consulta tipo select pero hara que la busqueda se haga 
	 * con condiciones where que seran hechas dinamicamente teniendo en cuenta la clase modelo,
	 * se formaran a base de los que no sean nulos
	 * 
	 * */
	
	List<Vacante> buscarByExample(Example<Vacante> example);
	
	
}
