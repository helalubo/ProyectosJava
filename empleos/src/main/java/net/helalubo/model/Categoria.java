package net.helalubo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Categorias")
public class Categoria {

	//Se agregan anotaciones para mappear
	
	//agrego id autoincrementable marcandolo como estrategia
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String descripcion;
	
	
	
	
	
	public Integer getId() {
		return id;
	}
	
	
	
	public Categoria() {
		
		
	}
	


	public Categoria(Integer id, String nombre, String descripcion) {
	
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}
	
	
		
		

	
}
