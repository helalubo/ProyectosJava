/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Entidades.PersonaDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Helalubo
 */
public interface PersonaDAO {

    ArrayList<PersonaDTO> Seleccionar();

    int insertar(PersonaDTO persona);

    int update(PersonaDTO persona);
    
    int delete(PersonaDTO persona);

}
