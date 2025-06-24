package tuti.desi.entidades;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("ASISTIDO")
public class Asistido extends Persona {

    @ManyToOne
    @JoinColumn(name = "familia_id")
    private Familia familia;

    // Getters y Setters

    public Familia getFamilia() {
        return familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

	public LocalDate getFechaRegistroAsistido() {
		return super.getFechaRegistro();
	}

	public void setFechaRegistroAsistido(LocalDate fechaRegistroAsistido) {
		super.setFechaRegistro(fechaRegistroAsistido);
	}
	
	//Constructor vacio
	public Asistido() {
	}
    
    
}

