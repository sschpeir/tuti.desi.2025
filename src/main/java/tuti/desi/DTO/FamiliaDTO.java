package tuti.desi.DTO;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class FamiliaDTO {
	
	//Propiedades

	private Long nroFamilia;
	
	private String nombre;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaRegistro;
	
	private boolean activa = true;
	
	private List<AsistidoDTO> integrantes;
	
	
	//GETTERS Y SETTERS DTO
	
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
	
	public List<AsistidoDTO> getIntegrantes() {
		return integrantes;
	}

	public void setIntegrantes(List<AsistidoDTO> integrantes) {
		this.integrantes = integrantes;
	}

	//Constructor vacio

	public FamiliaDTO() {
		
	}
	
	
	//Constructor para editar
	public FamiliaDTO(Long nroFamilia, String nombre) {
		this.nroFamilia = nroFamilia;
		this.nombre = nombre;
	}

	//Constructor para listar
	public FamiliaDTO(Long id, String nombre, LocalDate fechaRegistro, boolean activa) {
		this.activa = activa;
		this.nroFamilia = id;
		this.nombre = nombre;
		this.fechaRegistro = fechaRegistro;
		
	}
	
	//Constructor para listar con miembros
	public FamiliaDTO(Long id, String nombre, LocalDate fechaRegistro, boolean activa,List<AsistidoDTO> integrantes) {
		this.activa = activa;
		this.nroFamilia = id;
		this.nombre = nombre;
		this.fechaRegistro = fechaRegistro;
		this.integrantes = integrantes;
		
	}
	
}
