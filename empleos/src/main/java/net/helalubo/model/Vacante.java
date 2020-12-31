package net.helalubo.model;

import java.util.Date;

//Los modelos deben ser java beans


public class Vacante {

	
	private int id;
	private String nombre;
	private String descripcion;
	private Date fecha;
	private Double salario;
	
	
	
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
	
	
	
	
	
	
}
