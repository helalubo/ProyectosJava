/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import Datos.PersonaJDBC;
import Entidades.PersonaDTO;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Helalubo
 */
public class Tests {

    public static void main(String[] args) {

        PersonaJDBC personaDao = new PersonaJDBC();

        List<PersonaDTO> listaPersonas;

        listaPersonas = personaDao.Seleccionar();

        Tests.mostrarLista(listaPersonas);

        // int consulta = personaDao.delete(new Persona(4));
        // System.out.println(consulta);
        System.out.println("***************************************");

        personaDao.update(new PersonaDTO(3, "MAITE", "RODRIGUEZ", "MAITEVR@GMAIL.COM", "911"));

        listaPersonas = personaDao.Seleccionar();
        Tests.mostrarLista(listaPersonas);

        personaDao.insertar(new PersonaDTO("Magali", "Rodriguez", "mica@gmail.com", "101"));

        listaPersonas = personaDao.Seleccionar();
        Tests.mostrarLista(listaPersonas);
    }

    public static void mostrarLista(List<PersonaDTO> personas) {
        personas.forEach(persona -> {
            System.out.println(persona);
        });
    }
}
