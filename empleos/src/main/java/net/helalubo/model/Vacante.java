package net.helalubo.model;

import java.util.Date;

//Los modelos deben ser java beans


public class Vacante {

	
	private int id;
	private String nombre;
	private String descripcion;
	private Date fecha;
	private Double salario;
	private Integer destacado;
	private String imagen="no-image.png";
	
	
	
	public Vacante() {
		
	}
			
	
	public Vacante(int id, String nombre, String descripcion, Date fecha, Double salario, Integer destacado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.salario = salario;
		this.destacado = destacado;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Double getSalario() {
		return salario;
	}
	public void setSalario(Double salario) {
		this.salario = salario;
	}
	public Integer getDestacado() {
		return destacado;
	}
	
	
	public void setDestacado(Integer destacado) {
		this.destacado = destacado;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
	 StringBuilder sb = new StringBuilder();
	 
	 
	 sb.append("Vacante:");
	 sb.append(" id: ").append(getId());
	 sb.append(" nombre: ").append(getNombre());
	 sb.append(" Descripcion: ").append(getDescripcion());
	 sb.append(" fecha: ").append(getFecha());
	 sb.append(" Salario: ").append(getSalario());
	 
	 return sb.toString();
	 
	}


	public String getImagen() {
		return imagen;
	}


	public void setImagen(String imagen) {
		this.imagen = imagen;
	}


	
	
	
	
	
	
}
