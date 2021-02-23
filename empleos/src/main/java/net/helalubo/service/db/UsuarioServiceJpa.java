package net.helalubo.service.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import net.helalubo.model.Categoria;
import net.helalubo.model.Usuario;
import net.helalubo.repository.UsuariosRepository;
import net.helalubo.service.IUsuarioService;

@Service
@Primary
public class UsuarioServiceJpa implements IUsuarioService {

	@Autowired
	private UsuariosRepository repoUsuario;

	@Override
	public void Guardar(Usuario usuario) {
		// TODO Auto-generated method stub
		repoUsuario.save(usuario);

	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		repoUsuario.deleteById(id);
	}

	@Override
	public List<Usuario> BuscarTodos() {
		// TODO Auto-generated method stub
		return repoUsuario.findAll();
	}

}
