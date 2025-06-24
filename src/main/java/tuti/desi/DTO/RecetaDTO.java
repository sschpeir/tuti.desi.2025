package tuti.desi.DTO;

import java.util.ArrayList;
import java.util.List;

public class RecetaDTO {

	private Long id;
	
	private String nombre;
	
	private String descripcion;
	
	private boolean activa;
	
	
	//Evita problemas de nulidad cuando iteramos
	private List<ItemRecetaDTO> items = new ArrayList<>();

	
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

	public List<ItemRecetaDTO> getItems() {
		return items;
	}

	public void setItems(List<ItemRecetaDTO> items) {
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

	//Constructor para las listas complejas
	
	public RecetaDTO(Long id, String nombre, String descripcion, boolean activa, List<ItemRecetaDTO> items) {
	    this.id = id;
	    this.nombre = nombre;
	    this.descripcion = descripcion;
	    this.activa = activa;
	    this.items = (items != null) ? items : new ArrayList<>();
	}
	
	//Constructor para las listas simples

	public RecetaDTO(Long id, String nombre, String descripcion, boolean activa) {
		this.id = id;
	    this.nombre = nombre;
	    this.descripcion = descripcion;
	    this.activa = activa;
	}

}
