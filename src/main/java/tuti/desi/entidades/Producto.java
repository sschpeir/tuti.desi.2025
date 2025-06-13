package tuti.desi.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@DiscriminatorValue("Producto")
public class Producto extends Ingrediente {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column
    private Float stockDisponible;
	
	@Column
    private Float precioActual;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Float getStockDisponible() {
		return stockDisponible;
	}

	public void setStockDisponible(Float stockDisponible) {
		this.stockDisponible = stockDisponible;
	}

	public Float getPrecioActual() {
		return precioActual;
	}

	public void setPrecioActual(Float precioActual) {
		this.precioActual = precioActual;
	}
	
	
	
}
