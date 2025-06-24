package tuti.desi.DTO;

public class CondimentoDTO {

    private Long id;

    private String nombre;
    
    private Integer calorias;
    
    //
    private boolean activa;
    
    //Para discriminar entre "Condimento" y "Producto"
    private String tipoCondimento = "Condimento";

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

    public String getTipoCondimento() {
        return tipoCondimento;
    }

    public void setTipoCondimento(String tipoCondimento) {
        this.tipoCondimento = tipoCondimento;
    }

	public boolean isActiva() {
		return activa;
	}
	public void setActiva(boolean activa) {
		this.activa = activa;
	}

	public CondimentoDTO() {
		
	}
	
}
