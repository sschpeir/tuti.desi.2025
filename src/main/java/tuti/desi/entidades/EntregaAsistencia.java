package tuti.desi.entidades;

import java.time.LocalDate;

import jakarta.persistence.Id;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class EntregaAsistencia {
	
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;
    private Integer cantidadRaciones;

    @ManyToOne
    @JoinColumn(name = "preparacion_id")
    private Preparacion preparacion;

    @ManyToOne
    @JoinColumn(name = "familia_id")
    private Familia familia;
}
