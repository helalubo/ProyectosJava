package net.helalubo.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
	 * Query by example acepta consulta tipo select pero tomara 
	 * el objeto que proviene del model (generalmente desde un formulario)
	 * y utilizara sus campos en la parte del where para hacer la busqueda.
	 * 
	 * */
	
	List<Vacante> buscarByExample(Example<Vacante> example);
	
	
	//paginacion
	
	public Page<Vacante> buscarTodas(Pageable page);
	
	
	
	
}
