package tuti.desi.DTO;

import java.util.List;

public class RecetaDTO {

	private Long id;
	
	private String nombre;
	
	private String descripcion;
	
	private boolean activa;
	
	List<Object> items;
	
	List<Object> preparacion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public boolean isActiva() {
		return activa;
	}

	public void setActiva(boolean activa) {
		this.activa = activa;
	}

	public List<Object> getItems() {
		return items;
	}

	public void setItems(List<Object> items) {
		this.items = items;
	}

	public List<Object> getPreparacion() {
		return preparacion;
	}

	public void setPreparacion(List<Object> preparacion) {
		this.preparacion = preparacion;
	}

	public RecetaDTO() {
	}
	
}
