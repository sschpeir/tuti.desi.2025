package tuti.desi.DTO;

import java.time.LocalDate;

public class VoluntarioDTO extends PersonaDTO{
	
private String nroSeguridadSocial;

	
	//Getters y setters
	
	@Override
	public String getTipoPersona() {
	    return "VOLUNTARIO";
	}


	public String getNroSeguridadSocial() {
		return nroSeguridadSocial;
	}

	public void setNroSeguridadSocial(String nroSeguridadSocial) {
		this.nroSeguridadSocial = nroSeguridadSocial;
	}
	
	//Constructor para mapearlo
	public VoluntarioDTO(Long id,boolean activa, String nombre, String apellido, Integer dni, LocalDate fechaNacimiento, String domicilio, String ocupacion, String nroSeguridadSocial) {
		//Llama al constructor super por herencia
		super(id, activa, nombre, apellido, dni, fechaNacimiento, domicilio, ocupacion);
		this.nroSeguridadSocial = nroSeguridadSocial;
	}

}
