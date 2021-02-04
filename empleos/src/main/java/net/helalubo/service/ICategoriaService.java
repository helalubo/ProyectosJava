package net.helalubo.service;

import java.util.List;

import net.helalubo.model.Categoria;
import net.helalubo.model.Vacante;


public interface ICategoriaService {

	List<Categoria> buscarTodas();

	Categoria buscarPorId(int id);

	void Guardar(Categoria categoria);
	
	
	void eliminar(Integer id);
	
	

}
