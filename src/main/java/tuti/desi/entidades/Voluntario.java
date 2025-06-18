package tuti.desi.entidades;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@DiscriminatorValue("VOLUNTARIO")
public class Voluntario extends Persona {
	
	//Propiedades
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer nroSeguroSocial;

    //Getters y Setters
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNroSeguroSocial() {
		return nroSeguroSocial;
	}

	public void setNroSeguroSocial(Integer nroSeguroSocial) {
		this.nroSeguroSocial = nroSeguroSocial;
	}

	//Constructor vacio
	public Voluntario() {
		super();
	}
	
}
