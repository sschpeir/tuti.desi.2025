package tuti.desi.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Producto extends Ingrediente {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float stockDisponible;
    private Float precioActual;
}
