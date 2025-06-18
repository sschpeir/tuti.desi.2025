package tuti.desi.DTO;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Min;

public class PersonaDTO {
	
	private Long id;

	@Min(value = 1, message = "El DNI debe ser un número positivo")
	private Integer dni;

    private String domicilio;

    private String nombre;

    private String apellido;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;

    private String ocupacion;

    private boolean activa = true;
    
    private String tipoPersona;
    
    private Integer nroSeguroSocial;
    
    private Long familiaId;

    //Getters y Setters
    
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

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
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

	public String getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}

	public boolean isActiva() {
		return activa;
	}

	public void setActiva(boolean activa) {
		this.activa = activa;
	}


	public Long getFamiliaId() {
		return familiaId;
	}

	public void setFamiliaId(Long familiaId) {
		this.familiaId = familiaId;
	}
	
	public Integer getNroSeguroSocial() {
		return nroSeguroSocial;
	}

	public void setNroSeguroSocial(Integer nroSeguroSocial) {
		this.nroSeguroSocial = nroSeguroSocial;
	}	
	
	//Para saber si es asistido o voluntario
	public String getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}
	

	//Constructor

	public PersonaDTO() {
	}
	
	public PersonaDTO(Long id,boolean activa, String nombre, String apellido, Integer dni, LocalDate fechaNacimiento,String domicilio, String ocupacion) {
		this.id = id;
		this.activa = activa;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.fechaNacimiento = fechaNacimiento;
		this.domicilio = domicilio;
		this.ocupacion = ocupacion;
	}
	
	public PersonaDTO(Long id,boolean activa, String nombre, String apellido, Integer dni, LocalDate fechaNacimiento,String domicilio) {
		this.id = id;
		this.activa = activa;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.fechaNacimiento = fechaNacimiento;
		this.domicilio = domicilio;
	}
	
	//Constructor para DTO Voluntario
	public PersonaDTO(Long id,boolean activa, String nombre, String apellido, Integer dni, LocalDate fechaNacimiento,String domicilio, String ocupacion, Integer nroSeguroSocial) {
		this.id = id;
		this.activa = activa;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.fechaNacimiento = fechaNacimiento;
		this.domicilio = domicilio;
		this.ocupacion = ocupacion;
		this.nroSeguroSocial = nroSeguroSocial;
	}
	
	//Constructor para DTO Asistido
	public PersonaDTO(Long id,boolean activa, String nombre, String apellido, Integer dni, LocalDate fechaNacimiento,String domicilio, String ocupacion, Long familiaId) {
		this.id = id;
		this.activa = activa;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.fechaNacimiento = fechaNacimiento;
		this.domicilio = domicilio;
		this.ocupacion = ocupacion;
		this.familiaId = familiaId;
	}	
	
	//Constructor para DTO ambos
	public PersonaDTO(Long id,boolean activa, String nombre, String apellido, Integer dni, LocalDate fechaNacimiento,String domicilio, String ocupacion, String tipoPersona, Long familiaId, Integer nroSeguroSocial) {
		this.id = id;
		this.activa = activa;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.fechaNacimiento = fechaNacimiento;
		this.domicilio = domicilio;
		this.ocupacion = ocupacion;
		this.tipoPersona = tipoPersona;
		this.familiaId = familiaId;
		this.nroSeguroSocial = nroSeguroSocial;
	}	
	
}
