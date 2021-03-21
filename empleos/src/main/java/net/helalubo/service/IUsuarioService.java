package net.helalubo.service;

import java.util.List;

import net.helalubo.model.Usuario;

public interface IUsuarioService {

	List<Usuario> BuscarTodos();

	void Guardar(Usuario Usuario);

	void eliminar(Integer id);

	Usuario buscarPorUsername(String username);

}
