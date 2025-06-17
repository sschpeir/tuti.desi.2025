package tuti.desi.entidades;

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
}

