package tuti.desi.entidades;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
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

    @Column(name = "fechaEntrega")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaEntrega;
    
    @Column(name = "raciones")
    private Integer cantidadRaciones;

    @ManyToOne
    @JoinColumn(name = "preparacion_id")
    private Preparacion preparacion;

    @ManyToOne
    @JoinColumn(name = "familia_id")
    private Familia familia;
    
    @ManyToOne
    @JoinColumn(name = "voluntario_id")
    private Voluntario voluntario;
}
