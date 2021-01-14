package net.helalubo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import net.helalubo.model.Vacante;

@Controller
@RequestMapping("/vacantes")
public class VacantesController {

	
	
	/*
	 * Uso variable dinamica para poder acceder a cada uno de los empleos, esto es por id. 
	 * Busca la vacante que sea igual al id y devuelve el objeto agregandolo al model para luego ser usada en la vista devuelta como string en la funccion.
	 * @see detalle.html
	 * 
	 * 
	 * */
	
	@GetMapping("/view/{id}")
	public String verDetalle(@PathVariable("id") int  idVacante, Model model)
	{
		
	
		Vacante vacante = Vacante.getVacante(idVacante);
		
		//agrego la variable al modelo para usar en frontend
		
		model.addAttribute("vacante", vacante);
		
		
		//retorno la vista de frontend donde debera utilizarse la vacante
		return "vacantes/detalle";
		
	}
	
}
