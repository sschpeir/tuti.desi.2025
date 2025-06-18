package tuti.desi.DTO;

import java.time.LocalDate;

public class VoluntarioDTO extends PersonaDTO{
	
private Integer nroSeguroSocial;

	//Getters y setters
	
	@Override
	public String getTipoPersona() {
	    return "VOLUNTARIO";
	}
	
	public Integer getNroSeguroSocial() {
		return nroSeguroSocial;
	}

	public void setNroSeguroSocial(Integer nroSeguroSocial) {
		this.nroSeguroSocial = nroSeguroSocial;
	}

	//Constructor para mapearlo
	public VoluntarioDTO(Long id,boolean activa, String nombre, String apellido, Integer dni, LocalDate fechaNacimiento, String domicilio, String ocupacion, Integer nroSeguroSocial) {
		//Llama al constructor super por herencia
		super(id, activa, nombre, apellido, dni, fechaNacimiento, domicilio, ocupacion);
		this.nroSeguroSocial = nroSeguroSocial;
	}


	public VoluntarioDTO() {
	}

}
