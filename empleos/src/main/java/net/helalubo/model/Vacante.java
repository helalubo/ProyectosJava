package net.helalubo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;



//Los modelos deben ser java beans
@Entity
@Table(name= "vacantes")
public class Vacante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String descripcion;
	private Date fecha;

	private Integer destacado;
	private Double salario;
	private String imagen = "no-image.png";
	private String estatus;
	private String detalles;
	
	//Transient es para poder ignorar un atributo con respecto a su mapeo
	//@Transient
	/**
	 *Para hacer relaciones de uno a uno se utiliza el decorador OneToOne,
	 *Con el podemos unir por id de FK entre tablas
	 *Es importante que dentro del JoinColumn pongamos la FK la cual es el nombre de la fk en la base de datos 
	 * 
	 */
	
	@OneToOne
	@JoinColumn(name ="idCategoria")
	private Categoria categoria;

//	public Vacante() {
//
//	}

//	public Vacante(int id, String nombre, String descripcion, Date fecha, Double salario, Integer destacado,
//			String imagen, String estatus, String detalles) {
//		super();
//		this.id = id;
//		this.nombre = nombre;
//		this.descripcion = descripcion;
//		this.fecha = fecha;
//		this.salario = salario;
//		this.destacado = destacado;
//		this.imagen = imagen;
//		this.estatus = estatus;
//		this.detalles = detalles;
//	}
//
//	public Vacante(int id, String nombre, String descripcion, Date fecha, Double salario, Integer destacado) {
//		super();
//		this.id = id;
//		this.nombre = nombre;
//		this.descripcion = descripcion;
//		this.fecha = fecha;
//		this.salario = salario;
//		this.destacado = destacado;
//	}

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

//	@Override
//	public String toString() {
//		// TODO Auto-generated method stub
//		StringBuilder sb = new StringBuilder();
//
//		sb.append("Vacante:");
//		sb.append(" id: ").append(getId());
//		sb.append(" nombre: ").append(getNombre());
//		sb.append(" Descripcion: ").append(getDescripcion());
//		sb.append(" fecha: ").append(getFecha());
//		sb.append(" Salario: ").append(getSalario());
//
//		return sb.toString();
//
//	}
	
	

	public String getImagen() {
		return imagen;
	}

	

	@Override
	public String toString() {
		return "Vacante [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", fecha=" + fecha
				+ ", destacado=" + destacado + ", salario=" + salario + ", imagen=" + imagen + ", estatus=" + estatus
				+ ", detalles=" + detalles + ", categoria=" + categoria + "]";
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getDetalles() {
		return detalles;
	}

	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

//
//	public static Vacante getVacante(int idVacante) {
//		
//		List<Vacante> lista = Vacante.getVacantes();
//		Vacante vacante = new Vacante();
//		
//		for (Vacante vacanteAux : lista) {
//			
//			if(idVacante == vacanteAux.getId())
//			{
//				vacante = vacanteAux;
//				break;
//			}
//		}
//		return vacante;
//		
//	}

//	public static List<Vacante> getVacantes() {
//		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//		List<Vacante> lista = new LinkedList<Vacante>();
//
//		try {
//			Vacante vacante1 = new Vacante(1, "Ingreniero Civil", "Solicitamos ing Civil para diseñar puente peatonal",
//					sdf.parse("08-02-2019"), 8500.0,1);
//			
//			
//			Vacante vacante2 = new Vacante(2, "Contador publico",
//					"Empresa importante solicita contador con 5 años de experiencia titulado", sdf.parse("09-02-2019"),
//					12000.0,0);
//			Vacante vacante3 = new Vacante(3, "Ingeriero electronico",
//					"Empresa internacional solicita ingeniero electronico para matenimiento de instalacion electrica",
//					sdf.parse("10-02-2019"), 10500.0,0);
//			Vacante vacante4 = new Vacante(4, "Diseñador grafico",
//					"Solicitamos diseñador grafico titulado para diseñar publicidad de la empresa",
//					sdf.parse("11-02-2019"), 7500.0,1);
//			
//			
////			agrego imagenes
//			
//			vacante1.setImagen("empresa1.png");
//			vacante2.setImagen("empresa2.png");
//			vacante4.setImagen("empresa3.png");
//			
//			lista.add(vacante1);
//			lista.add(vacante2);
//			lista.add(vacante3);
//			lista.add(vacante4);
//
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			
//			System.out.print("Error: "+e.getMessage());
//	
//		}
//
//		return lista;
//
//	}
	
	public void reset() {
		this.imagen = null;
		
	}
	

}
