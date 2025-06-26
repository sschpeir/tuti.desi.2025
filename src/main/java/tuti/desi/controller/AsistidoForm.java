package tuti.desi.controller;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;


public class AsistidoForm {
	
	private Long id;

	@Min(value = 1, message = "El DNI debe ser un número positivo")
	private Integer dni;

	@NotBlank (message = "El nombre del asistido no puede estar vacio")
    private String nombre;

	@NotBlank (message = "El apellido del asistido no puede estar vacio")
    private String apellido;

	@Past (message = "La fecha de nacimiento no puede ser mayor o igual a hoy")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;
    
    private String domicilio;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaRegistro;

    private String ocupacion;

    private boolean activa = true;
    
    private String tipoPersona = "Asistido";
    
    private Long familiaId;

	//Getters y setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	
	public String getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
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

	public String getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}	

	public Long getFamiliaId() {
		return familiaId;
	}

	public void setFamiliaId(Long familiaId) {
		this.familiaId = familiaId;
	}

	public AsistidoForm() {
	}

}
