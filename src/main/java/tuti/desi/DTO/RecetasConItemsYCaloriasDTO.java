package tuti.desi.DTO;

public class RecetasConItemsYCaloriasDTO {

    private Long id;
    private String nombre;
    private Boolean activa;
    private Double totalCalorias;

    public RecetasConItemsYCaloriasDTO(Long id, String nombre, Boolean activa, Number totalCalorias) {
        this.id = id;
        this.nombre = nombre;
        this.activa = activa;
        this.totalCalorias = totalCalorias.doubleValue();
    }


    // getters y setters (requerido para que Thymeleaf acceda a los campos)

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Boolean getActiva() {
        return activa;
    }

    public Double getTotalCalorias() {
        return totalCalorias;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setActiva(Boolean activa) {
        this.activa = activa;
    }

    public void setTotalCalorias(Double totalCalorias) {
        this.totalCalorias = totalCalorias;
    }
}
