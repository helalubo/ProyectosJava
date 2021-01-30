package com.helalubo.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nombre;
	private String email;
	private String username;
	private String password;
	private Integer estatus;
	private Date fechaRegistro;
	
	//sE ESPECIFICA LA RELACION ENTRE LOS PARAMETROS QUE SON DE MUCHOS A MUCHOS
	//Que significa fetch => es el indicativo que marca que cada vez que se busque un parametro
	//Usuario me debera traer todos los perfiles de la lista, que se recuperen en la misma consulta todos los perfiles que tenga asociado dicho usuario
	//@JoinTable? es para identificar a la tabla intermedia que esta entre usuarios y perfiles, estas siendo identificadas por su nombre y por sus atributos id
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "UsuarioPerfil",
				joinColumns =  @JoinColumn(name="idUsuario"),  //Esta va primero porque estamos realizando el many to many desde la clase Usuario
				inverseJoinColumns = @JoinColumn(name="idPerfil") //y LIGAMOS LOS PERFILES DE LA OTRA TABLA CON LA QUE SE RELACIONA
				)
	private List<Perfil> perfiles;
	
	
	
	
	public void agregarPerfil(Perfil tempPerfil) {
		
		if(this.perfiles == null) {
			perfiles = new LinkedList<Perfil>();
		}
			
		this.perfiles.add(tempPerfil);
		
	}
	
	
	public List<Perfil> getPerfiles() {
		return perfiles;
	}
	public void setPerfiles(List<Perfil> perfiles) {
		this.perfiles = perfiles;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getEstatus() {
		return estatus;
	}
	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}		


	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", username=" + username + ", fechaRegistro="
				+ fechaRegistro + ", perfiles=" + perfiles + "]";
	}
	
	
	
	

	

	
	
	
	
}
