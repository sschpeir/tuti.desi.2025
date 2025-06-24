package tuti.desi.entidades;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Preparacion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer totalRacionesPreparadas;
    
    @Column
    private Integer stockRacionesRestantes;
    
    @Column
    private LocalDate fechaCoccion;

    @ManyToOne
    @JoinColumn(name = "receta_id")
    private Receta receta;

    @OneToMany(mappedBy = "preparacion")
    private List<EntregaAsistencia> entregas;
}
