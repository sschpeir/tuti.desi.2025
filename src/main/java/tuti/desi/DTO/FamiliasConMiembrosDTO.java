package tuti.desi.DTO;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class FamiliasConMiembrosDTO {
	
	//Propiedades

	private Long nroFamilia;
	
	private String nombre;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaRegistro;
	
	private boolean activa;
	
	private Long cantidad;
	
	private Long cantidadActivos;
	
	//GETTERS Y SETTERS DTO

	public Long getNroFamilia() {
		return nroFamilia;
	}

	public void setNroFamilia(Long nroFamilia) {
		this.nroFamilia = nroFamilia;
	}
	
	public boolean isActiva() {
		return activa;
	}

	public void setActiva(boolean activa) {
		this.activa = activa;
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

	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public Long getCantidadActivos() {
		return cantidadActivos;
	}

	public void setCantidadActivos(Long cantidadActivos) {
		this.cantidadActivos = cantidadActivos;
	}

	
	//Constructores



	public FamiliasConMiembrosDTO() {
	}	
	
	
	public FamiliasConMiembrosDTO(Long id, String nombre, LocalDate fechaRegistro, boolean activa, Long cantidad, Long cantidadActivos) {
	    this.nroFamilia = id;
	    this.nombre = nombre;
	    this.fechaRegistro = fechaRegistro;
	    this.activa = activa;
	    this.cantidad = cantidad != null ? cantidad : 0L;
	    this.cantidadActivos = cantidadActivos != null ? cantidadActivos : 0L;
	}



}
