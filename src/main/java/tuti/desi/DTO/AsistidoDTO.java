package tuti.desi.DTO;

import java.time.LocalDate;

public class AsistidoDTO extends PersonaDTO {
	
	
	//Propiedad para referenciarlo
	private Long familiaId;
	
	//Propiedad para referenciar el nombre de las familias desde los listados
	private String familiaNombre;

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
		return super.getFechaRegistro();
	}

	public void setFechaRegistroAsistido(LocalDate fechaRegistroAsistido) {
		super.setFechaRegistro(fechaRegistroAsistido);
	}
	
	@Override
	public String getTipoPersona() {
	    return "ASISTIDO";
	}
	
	//Constructor multiproposito 
		public AsistidoDTO(Long id, boolean activa, String nombre, String apellido, Integer dni, LocalDate fechaNacimiento,String domicilio, String ocupacion, Long familia, LocalDate fechaRegistro) {
			super(id, activa, nombre, apellido, dni, fechaNacimiento, domicilio, ocupacion);
			this.familiaId = familia;
			super.setFechaRegistro(fechaRegistro);
		}

	//Constructor en blanco
	public AsistidoDTO() {
	}
	
}
