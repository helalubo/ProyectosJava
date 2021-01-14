package net.helalubo.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import net.helalubo.model.Vacante;

//Creamos el servicio poniendo la etiqueta  @service arriva de la clase que implementa la interface IVacanteService. 
@Service
public class VacantesServiceImpl implements IVacanteService {

	private List<Vacante> lista = null;

	public VacantesServiceImpl() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		lista = new LinkedList<Vacante>();

		try {
			Vacante vacante1 = new Vacante(1, "Ingreniero Civil", "Solicitamos ing Civil para diseñar puente peatonal",
					sdf.parse("08-02-2019"), 14000.0, 1);

			Vacante vacante2 = new Vacante(2, "Contador publico",
					"Empresa importante solicita contador con 5 años de experiencia titulado", sdf.parse("09-02-2019"),
					12000.0, 0);
			Vacante vacante3 = new Vacante(3, "Ingeriero electronico",
					"Empresa internacional solicita ingeniero electronico para matenimiento de instalacion electrica",
					sdf.parse("10-02-2019"), 10500.0, 0);
			Vacante vacante4 = new Vacante(4, "Diseñador grafico",
					"Solicitamos diseñador grafico titulado para diseñar publicidad de la empresa",
					sdf.parse("11-02-2019"), 7500.0, 1);

//				agrego imagenes

			vacante1.setImagen("logo7.png");
			vacante2.setImagen("empresa2.png");
			vacante4.setImagen("empresa3.png");

			lista.add(vacante1);
			lista.add(vacante2);
			lista.add(vacante3);
			lista.add(vacante4);

		} catch (ParseException e) {
			// TODO Auto-generated catch block

			System.out.print("Error: " + e.getMessage());

		}

	}

	@Override
	public List<Vacante> buscarTodas() {
		// TODO Auto-generated method stub
		return this.lista;
	}

	@Override
	public Vacante buscarPorId(int id) {
		// TODO Auto-generated method stub

	{
			
			List<Vacante> lista = this.lista;
			Vacante vacante = new Vacante();
			
			for (Vacante vacanteAux : lista) {
				
				if(id == vacanteAux.getId())
				{
					vacante = vacanteAux;
					break;
				}
			}
			return vacante;
			
		}
	}

}
