package net.helalubo.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import net.helalubo.model.Categoria;
import net.helalubo.model.Vacante;
import net.helalubo.repository.CategoriasRepository;
import net.helalubo.service.ICategoriaService;

///Existen 2 formas de dar Informacion sobre cual clase va a implementar la interface para poder ser utilizada, podemos solucionarlo agregando 
//Primary para que determine cual va a ser la implementacion a realizar
//O podemos marcar con 	@Qualifier("categoriasServiceJpa")  en cada una  de las implementaciones donde se utiliza la interface como inyeccion de dependencias 
//@Qualifier("categoriasServiceJpa")  iria arriba de cada uno de las inyecciones que son del tipo de la Interface a utilizar, NO DEL REPOSITORIO

@Service
@Primary

public class CategoriasServiceJpa implements ICategoriaService {

	
	@Autowired
	private CategoriasRepository categoriasRepo;
	
	@Override
	public List<Categoria> buscarTodas() {
		// TODO Auto-generated method stub
		return categoriasRepo.findAll();
	}

	@Override
	public Categoria buscarPorId(int id) {
		// TODO Auto-generated method stub
		
		Optional<Categoria> categoriaOpc = categoriasRepo.findById(id);
		Categoria categoria;
		
		if(categoriaOpc.isPresent())
		{
			return  categoriaOpc.get();
		}
		
		return null;
		
		
	
	}

	@Override
	public void Guardar(Categoria categoria) {
		
		categoriasRepo.save(categoria);

	}

	@Override
	public void eliminar(Integer id) {
		
		
		categoriasRepo.deleteById(id);
		
		
	}



}
