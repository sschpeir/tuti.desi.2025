package tuti.desi.DTO;

public class ItemRecetaDTO {
	
	//Propiedades
	private Long id;
	
	//para mapear el DTO
    private Long recetaId;
    private Long ingredienteId;
    
    
    private Integer cantidad;
    private Integer calorias;
    private IngredienteDTO ingrediente;
    
    //para deshabilitar los ingredientes
    private boolean activa = true;

    // Getters y setters
    public Long getRecetaId() {
    	return recetaId; 
    	}
    
    public void setRecetaId(Long recetaId) {
    	this.recetaId = recetaId;
    	}

    public Long getIngredienteId() {
    	return ingredienteId; 
    	}
    
    public void setIngredienteId(Long ingredienteId) {
    	this.ingredienteId = ingredienteId;
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

    public IngredienteDTO getIngrediente() {
    	return ingrediente;
    	}
    
    public void setIngrediente(IngredienteDTO ingrediente) {
    	this.ingrediente = ingrediente;
    	}

	public boolean isActiva() {
		return activa;
	}

	public void setActiva(boolean activa) {
		this.activa = activa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
    
}
