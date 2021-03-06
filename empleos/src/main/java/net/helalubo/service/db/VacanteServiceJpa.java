package net.helalubo.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.helalubo.model.Categoria;
import net.helalubo.model.Vacante;
import net.helalubo.repository.VacantesRepository;
import net.helalubo.service.IVacanteService;

@Service
@Primary
public class VacanteServiceJpa implements IVacanteService {

	
	@Autowired
	VacantesRepository vacantesRepo;

	@Override
	public List<Vacante> buscarTodas() {
		// TODO Auto-generated method stub
		return vacantesRepo.findAll();
	}

	@Override
	public Vacante buscarPorId(int id) {
		// TODO Auto-generated method stubç
		
		Optional<Vacante> vacanteOpc = vacantesRepo.findById(id);
		
		if(vacanteOpc.isPresent()) {
			return vacanteOpc.get();
		}
		return null;
	}

	@Override
	public void Guardar(Vacante vacante) {
		
		
		vacantesRepo.save(vacante);
		
	}

	@Override
	public List<Vacante> buscarDestacadas() {
		// TODO Auto-generated method stub
		return vacantesRepo.findByEstatusAndDestacadoOrderByIdDesc("aprobada", 1);
	}

	@Override
	public void eliminar(Integer id) {

		
		vacantesRepo.deleteById(id);
		
	}

	@Override
	public List<Vacante> buscarVacantesPorCategoria(Integer idCategoria) {
		// TODO Auto-generated method stub
		Categoria categoria = new Categoria();
		categoria.setId(idCategoria);
		
		return vacantesRepo.findByCategoria(categoria);
	}
	

	@Override
	public void GuardarTodas(List<Vacante> vacantes)
	{
		vacantesRepo.saveAll(vacantes);
	}

	@Override
	public List<Vacante> buscarByExample(Example<Vacante> example) {
		// TODO Auto-generated method stub
		return vacantesRepo.findAll(example);
	}

	@Override
	public Page<Vacante> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return vacantesRepo.findAll(page);
		
	}

	
	

}
