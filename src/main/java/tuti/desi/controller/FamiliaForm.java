package tuti.desi.controller;

import java.time.LocalDate;


import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;

public class FamiliaForm {
	
	//Propiedades

	private Long nroFamilia;
	
	@NotBlank (message = "El nombre de la familia no puede estar vacio")
	private String nombre;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaRegistro;
	
	private boolean activa = true;
	
	//GETTERS Y SETTERS FORM

	public Long getNroFamilia() {
		return nroFamilia;
	}

	public void setNroFamilia(Long nroFamilia) {
		this.nroFamilia = nroFamilia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDate getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDate fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public boolean isActiva() {
		return activa;
	}

	public void setActiva(boolean activa) {
		this.activa = activa;
	}

	//Constructor vacio

	public FamiliaForm() {
		
	}
	
}
