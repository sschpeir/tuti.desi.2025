package tuti.desi.DTO;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class FamiliasConMiembrosActivosDTO {
	
	//Propiedades

	private Long nroFamilia;
	
	private String nombre;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaRegistro;
	
	private boolean activa;
	
	private Integer cantidadActivos;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaUltimaAsistencia;
	
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

	
	public Integer getCantidadActivos() {
		return cantidadActivos;
	}

	public void setCantidadActivos(Integer cantidadActivos) {
		this.cantidadActivos = cantidadActivos;
	}

	public LocalDate getFechaUltimaAsistencia() {
		return fechaUltimaAsistencia;
	}

	public void setFechaUltimaAsistencia(LocalDate fechaUltimaAsistencia) {
		this.fechaUltimaAsistencia = fechaUltimaAsistencia;
	}
	
	//Constructores

	public FamiliasConMiembrosActivosDTO() {
	}	
	
	public FamiliasConMiembrosActivosDTO(Long nroFamilia, String nombre, LocalDate fechaRegistro, boolean activa, Long cantidadActivos, LocalDate fechaUltimaAsistencia) {
	    this.nroFamilia = nroFamilia;
	    this.nombre = nombre;
	    this.fechaRegistro = fechaRegistro;
	    this.activa = activa;
	    this.cantidadActivos = cantidadActivos != null ? cantidadActivos.intValue() : 0;
	    this.fechaUltimaAsistencia = fechaUltimaAsistencia;
	}

}
