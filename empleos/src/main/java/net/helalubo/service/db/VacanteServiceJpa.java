package net.helalubo.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import net.helalubo.model.Categoria;
import net.helalubo.model.Vacante;
import net.helalubo.repository.VacantesRepository;
import net.helalubo.service.ICategoriaService;
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
	
	
	

}
