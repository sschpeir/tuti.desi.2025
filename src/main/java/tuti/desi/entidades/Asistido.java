package tuti.desi.entidades;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;


@Entity
@DiscriminatorValue("ASISTIDO")
public class Asistido extends Persona {

    @ManyToOne
    @JoinColumn(name = "familia_id")
    private Familia familia;
    
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaRegistroAsistido;

    // Getters y Setters

    public Familia getFamilia() {
        return familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

	public LocalDate getFechaRegistroAsistido() {
		return fechaRegistroAsistido;
	}

	public void setFechaRegistroAsistido(LocalDate fechaRegistroAsistido) {
		this.fechaRegistroAsistido = fechaRegistroAsistido;
	}
    
    
}

