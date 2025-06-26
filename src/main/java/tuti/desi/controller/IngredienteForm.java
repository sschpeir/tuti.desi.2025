package tuti.desi.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public class IngredienteForm {

    private Long id;
    
    @NotBlank (message = "El nombre del ingrediente no puede estar vacio")
    private String nombre;
    
    @PositiveOrZero (message = "El valor de las calorias no puede ser negativo")
    private Integer calorias;
    
    private boolean activa = true;
    
    //Para discriminar entre "Condimento" y "Producto"
    private String tipoIngrediente;

    // Solo si el tipo es "Producto"
    @PositiveOrZero (message = "El precio del producto no puede ser negativo")
    private Float precioActual;
    
    @PositiveOrZero (message = "El stock del producto no puede ser negativo")
    private Float stockDisponible;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCalorias() {
        return calorias;
    }

    public void setCalorias(Integer calorias) {
        this.calorias = calorias;
    }

    public String getTipoIngrediente() {
		return tipoIngrediente;
	}

	public void setTipoIngrediente(String tipoIngrediente) {
		this.tipoIngrediente = tipoIngrediente;
	}

	public Float getPrecioActual() {
        return precioActual;
    }

    public void setPrecioActual(Float precioActual) {
        this.precioActual = precioActual;
    }

    public Float getStockDisponible() {
        return stockDisponible;
    }

    public void setStockDisponible(Float stockDisponible) {
        this.stockDisponible = stockDisponible;
    }

	public boolean isActiva() {
		return activa;
	}
	public void setActiva(boolean activa) {
		this.activa = activa;
	}

	public IngredienteForm() {
		
	}
	
}
