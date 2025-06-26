package tuti.desi.controller;

import jakarta.validation.constraints.NotBlank;

public class RecetaForm {

	private Long id;
	
	@NotBlank (message = "El nombre de la receta no puede estar vacio")
	private String nombre;
	
	@NotBlank (message = "Agregue una descripcion")
	private String descripcion;
	
	private boolean activa = true;
	

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

	public RecetaForm() {
	}

}
