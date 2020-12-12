/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author User
 */
public class PersonaDTO {

    private int id_persona;
    private String nombre;
    private String apellido;
    private String mail;
    private String telefono;

    public PersonaDTO() {

    }

    public PersonaDTO(int id_persona) {
        this.id_persona = id_persona;
    }

    public PersonaDTO(int id_persona, String nombre, String apellido, String mail, String telefono) {
        this.id_persona = id_persona;
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.telefono = telefono;
    }

    public PersonaDTO(String nombre, String apellido, String mail, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.telefono = telefono;
    }

    public int getId_persona() {
        return id_persona;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getMail() {
        return mail;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        
        
        sb.append("id: ").append(this.id_persona);
        sb.append(" nombre: ").append(this.nombre);
        sb.append(" apellido: ").append(this.apellido);
        sb.append(" email: ").append(this.mail);
        sb.append(" telefono: ").append(this.telefono);
        
        return  sb.toString();

    }

}
