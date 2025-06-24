package tuti.desi.entidades;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@DiscriminatorValue("Condimento")
public class Condimento extends Ingrediente {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
}
