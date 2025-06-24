package tuti.desi.controller;

public class IngredienteForm {

    private Long id;

    private String nombre;
    
    private Integer calorias;
    
    private boolean activa = true;
    
    //Para discriminar entre "Condimento" y "Producto"
    private String tipoIngrediente;

    // Solo si el tipo es "Producto"
    private Float precioActual;
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
