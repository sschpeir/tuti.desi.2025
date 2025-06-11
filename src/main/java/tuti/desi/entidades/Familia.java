package tuti.desi.entidades;

import java.time.LocalDate;

import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Id;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;

@Entity
public class Familia {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nroFamilia;

    @Column(unique = true, nullable = false, length = 150)
    private String nombre;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaRegistro;
    

    @OneToMany(mappedBy = "familia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Asistido> integrantes;

    @OneToMany(mappedBy = "familia")
    private List<EntregaAsistencia> entregas;
    
    private boolean activa = true;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDate getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDate fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

    public boolean isActiva() { 
    	return activa; 
    	}
    public void setActiva(boolean activa) { 
    	this.activa = activa; 
    	}
    public List<Asistido> getIntegrantes() {
    	return integrantes; }
    
    public void setIntegrantes(List<Asistido> integrantes) { 
    	this.integrantes = integrantes; }

    public List<EntregaAsistencia> getEntregas() {
    	return entregas; }
    public void setEntregas(List<EntregaAsistencia> entregas) {
    	this.entregas = entregas; }

    public Long getId() {
    	return nroFamilia;
    }
    
    public void setId(Long id) {
    	this.nroFamilia = id;
    }


}
