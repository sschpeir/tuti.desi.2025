package tuti.desi.entidades;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@DiscriminatorValue("VOLUNTARIO")
public class Voluntario extends Persona {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nroSeguridadSocial;

	public String getNroSeguridadSocial() {
		return nroSeguridadSocial;
	}

	public void setNroSeguridadSocial(String nroSeguridadSocial) {
		this.nroSeguridadSocial = nroSeguridadSocial;
	}
}
