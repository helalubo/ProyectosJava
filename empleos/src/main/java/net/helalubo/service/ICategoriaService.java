package net.helalubo.service;

import java.util.List;

import net.helalubo.model.Categoria;


public interface ICategoriaService {

	List<Categoria> buscarTodas();

	Categoria buscarPorId(int id);

	void Guardar(Categoria categoria);

}
