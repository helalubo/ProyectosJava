/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Entidades.PersonaDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class PersonaJDBC implements PersonaDAO {

    
    
    ////QUERYS
    public static final String SQL_SELECT = "SELECT id_persona, nombre, apellido, email,"
            + " telefono FROM prueba.persona;";

     public static final String SQL_INSERT = "INSERT INTO prueba.persona (nombre, apellido, email, telefono) VALUES (?, ?, ?, ?);";

    public static final String SQL_DELETE = "delete from prueba.persona where id_persona = ?;";

    public static final String SQL_UPDATE = "UPDATE prueba.persona SET nombre =?, apellido =?, "
            + "email =?, telefono =? WHERE id_persona =?;";

    @Override
    public ArrayList<PersonaDTO> Seleccionar() {

        ArrayList<PersonaDTO> listaPersonas = new ArrayList<>();

        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            cn = Conexion.getConnection();
            ps = cn.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();

            while (rs.next()) {
                PersonaDTO personaAux = new PersonaDTO();
                personaAux.setId_persona(rs.getInt("id_persona"));
                personaAux.setNombre(rs.getString("nombre"));
                personaAux.setApellido(rs.getString("apellido"));
                personaAux.setMail(rs.getString("email"));
                personaAux.setTelefono(rs.getString("telefono"));

                listaPersonas.add(personaAux);
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {

            try {
                Conexion.close(rs);
                Conexion.close(ps);
                Conexion.close(cn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }

        }

        return listaPersonas;
    }

    @Override
    public int insertar(PersonaDTO persona) {

        Connection cn = null;
        PreparedStatement ps = null;

        int registros = 0;

        try {
            cn = Conexion.getConnection();
            ps = cn.prepareStatement(SQL_INSERT);

            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getApellido());
            ps.setString(3, persona.getMail());
            ps.setString(4, persona.getTelefono());

            registros = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {

            try {

                Conexion.close(ps);
                Conexion.close(cn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }

        }

        return registros;
    }

    @Override
    public int update(PersonaDTO persona) {
        Connection cn = null;
        PreparedStatement ps = null;

        int registros = 0;

        try {
            cn = Conexion.getConnection();
            ps = cn.prepareStatement(SQL_UPDATE);

            ps.setInt(5, persona.getId_persona());
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getApellido());
            ps.setString(3, persona.getMail());
            ps.setString(4, persona.getTelefono());
          
            registros = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {

            try {

                Conexion.close(ps);
                Conexion.close(cn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }

        }

        return registros;
    }

    @Override
    public int delete(PersonaDTO persona) {
        Connection cn = null;
        PreparedStatement ps = null;

        int registros = 0;

        try {
            cn = Conexion.getConnection();
            ps = cn.prepareStatement(SQL_DELETE);

            ps.setInt(1, persona.getId_persona());

            registros = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {

            try {

                Conexion.close(ps);
                Conexion.close(cn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }

        }

        return registros;
    }

}
