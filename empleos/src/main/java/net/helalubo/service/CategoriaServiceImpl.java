package net.helalubo.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import net.helalubo.model.Categoria;
import net.helalubo.model.Vacante;

@Service
public class CategoriaServiceImpl implements ICategoriaService {

	
	private List<Categoria> lista = null;
	
	
	
	
	
	
	
	
	public CategoriaServiceImpl() {
	
		
     List<Categoria> listaDeCategorias = new LinkedList<Categoria>();
		
		listaDeCategorias.add(new Categoria(1,"Recursos Humanos", "Trabajos relacionados con el area de RH."));
		listaDeCategorias.add(new Categoria(2,"Ventas", "Ofertas de trabajo relacionado con ventas."));
		listaDeCategorias.add(new Categoria(3,"Arquitectura", "Diseño de planos en general y trabajos relacionados."));
		listaDeCategorias.add(new Categoria(4,"Programador", "Crear proyectos en base a codigo y logica."));
		
		this.lista = listaDeCategorias;
	}

	@Override
	public List<Categoria> buscarTodas() {
	
		
		return this.lista;
	}

	@Override
	public Categoria buscarPorId(int id) {
		
		List<Categoria> lista = this.lista;
		Categoria categoria = new Categoria();
		
		for (Categoria categoriaAux : lista) {
			
			if(id == categoriaAux.getId())
			{
				categoria = categoriaAux;
				break;
			}
		}
		return categoria;
		
	}

	@Override
	public void Guardar(Categoria categoria) {
	
		
		this.lista.add(categoria);
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		
	}



}
