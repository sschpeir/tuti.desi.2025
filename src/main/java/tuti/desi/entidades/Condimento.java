package tuti.desi.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Condimento extends Ingrediente {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
