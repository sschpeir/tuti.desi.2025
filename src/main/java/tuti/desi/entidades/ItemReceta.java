package tuti.desi.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ItemReceta {
	    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private Integer cantidad;
	    private Integer calorias;

	    @ManyToOne
	    @JoinColumn(name = "receta_id")
	    private Receta receta;

	    @ManyToOne
	    @JoinColumn(name = "ingrediente_id")
	    private Ingrediente ingrediente;
	}


