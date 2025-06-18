package tuti.desi.DTO;

public class ItemRecetaDTO {
	
    private Long id;
    
    private Integer cantidad;
    
    private Integer calorias;

    public ItemRecetaDTO() {
    }
    
    public ItemRecetaDTO(Long id, Integer cantidad, Integer calorias) {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.calorias = calorias;
	}
    

    // Getters y setters

    



	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Integer getCalorias() {
		return calorias;
	}

	public void setCalorias(Integer calorias) {
		this.calorias = calorias;
	}
    
	private String tipoIngrediente; // o un enum si lo estás usando

    public String getTipoIngrediente() {
        return tipoIngrediente;
    }

    public void setTipoIngrediente(String tipoIngrediente) {
        this.tipoIngrediente = tipoIngrediente;
    }
    

   
}
