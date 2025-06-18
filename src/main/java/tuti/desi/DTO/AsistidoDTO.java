package tuti.desi.DTO;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class AsistidoDTO extends PersonaDTO {
	
	
	//Propiedad para referenciarlo
	private Long familiaId;
	
	//Propiedad para referenciar el nombre de las familias desde los listados
	private String familiaNombre;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaRegistroAsistido;

	//Getters y setters
	
	public Long getFamiliaId() {
		return familiaId;
	}

	public void setFamiliaId(Long familiaId) {
		this.familiaId = familiaId;
	}
	
	public String getFamiliaNombre() {
		return familiaNombre;
	}

	public void setFamiliaNombre(String familiaNombre) {
		this.familiaNombre = familiaNombre;
	}
	
	public LocalDate getFechaRegistroAsistido() {
		return fechaRegistroAsistido;
	}

	public void setFechaRegistroAsistido(LocalDate fechaRegistroAsistido) {
		this.fechaRegistroAsistido = fechaRegistroAsistido;
	}
	
	@Override
	public String getTipoPersona() {
	    return "ASISTIDO";
	}
	
	//Constructor multiproposito 
		public AsistidoDTO(Long id, boolean activa, String nombre, String apellido, Integer dni, LocalDate fechaNacimiento,String domicilio, String ocupacion, Long familia, LocalDate fechaRegistroAsistido) {
			super(id, activa, nombre, apellido, dni, fechaNacimiento, domicilio, ocupacion);
			this.familiaId = familia;
			this.fechaRegistroAsistido = fechaRegistroAsistido;
		}

	//Constructor en blanco
	public AsistidoDTO() {
	}





	
}
