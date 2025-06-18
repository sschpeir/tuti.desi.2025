package tuti.desi.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tuti.desi.DTO.AsistidoDTO;
import tuti.desi.DTO.PersonaDTO;
import tuti.desi.DTO.VoluntarioDTO;

import tuti.desi.accesoDatos.PersonaRepository;
import tuti.desi.entidades.Asistido;
import tuti.desi.entidades.Persona;
import tuti.desi.entidades.Voluntario;
import tuti.desi.entidades.Familia;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaRepository personaRepository;
    
    //Para guardar una persona como DTO
    @Override
    public Persona guardar(PersonaDTO personaDTO) {
        boolean esEdicion = personaDTO.getId() != null;
        Persona persona;

        if (esEdicion) {
            // Buscar la existente
            persona = personaRepository.findById(personaDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("No se encontró la persona con ID: " + personaDTO.getId()));

            // Validar si el DNI cambió y si ya existe en otra persona
            if (!persona.getDni().equals(personaDTO.getDni()) &&
                personaRepository.existsByDni(personaDTO.getDni())) {
                throw new IllegalArgumentException("Ya existe otra persona con ese DNI");
            }

            // Si es voluntario y se puede castear, actualizar campo extra
            if (persona instanceof Voluntario voluntario) {
                voluntario.setNroSeguroSocial(personaDTO.getNroSeguroSocial());
            }

        } else {
            // Validar que no exista el DNI
            if (personaRepository.existsByDni(personaDTO.getDni())) {
                throw new IllegalArgumentException("Ya existe una persona con ese DNI");
            }

            // Crear nueva instancia según tipo
            if ("Asistido".equalsIgnoreCase(personaDTO.getTipoPersona())) {
                Asistido asistido = new Asistido();
                Familia familia = new Familia();
                familia.setId(personaDTO.getFamiliaId());
                asistido.setFamilia(familia);
                persona = asistido; 
            } else {
                Voluntario voluntario = new Voluntario();
                voluntario.setNroSeguroSocial(personaDTO.getNroSeguroSocial());
                persona = voluntario;
            }
        }

        // Datos comunes
        persona.setDni(personaDTO.getDni());
        persona.setApellido(personaDTO.getApellido());
        persona.setNombre(personaDTO.getNombre());
        persona.setDomicilio(personaDTO.getDomicilio());
        persona.setFechaNacimiento(personaDTO.getFechaNacimiento());
        persona.setOcupacion(personaDTO.getOcupacion());
        persona.setActiva(personaDTO.isActiva());

        return personaRepository.save(persona);
    }
    
    //Listar todos los tipos de persona
    @Override
    public List<PersonaDTO> listarTodos() {
        List<Persona> personas = personaRepository.findAll();

        return personas.stream()
            .map(p -> {
                if (p instanceof Asistido asistido) {
                    return new AsistidoDTO(
                        asistido.getId(),
                        asistido.isActiva(),
                        asistido.getNombre(),
                        asistido.getApellido(),
                        asistido.getDni(),
                        asistido.getFechaNacimiento(),
                        asistido.getDomicilio(),
                        asistido.getOcupacion(),
                        asistido.getFamilia() != null ? asistido.getFamilia().getId() : null,
           	            asistido.getFechaRegistroAsistido()
                    );
                } else if (p instanceof Voluntario voluntario) {
                    return new VoluntarioDTO(
                        voluntario.getId(),
                        voluntario.isActiva(),
                        voluntario.getNombre(),
                        voluntario.getApellido(),
                        voluntario.getDni(),
                        voluntario.getFechaNacimiento(),
                        voluntario.getDomicilio(),
                        voluntario.getOcupacion(),
                        voluntario.getNroSeguroSocial()
                    );
                } else {
                    throw new IllegalStateException("Tipo de persona desconocido: " + p.getClass());
                }
            })
            .collect(Collectors.toList());
    }
    
    //Listar todos los tipos de persona activas
  	@Override
  	public List<PersonaDTO> listarPersonasActivas() {
  		List<Persona> personas = personaRepository.findByActivaTrue();
  		return personas.stream()
  	            .map(p -> {
  	                if (p instanceof Asistido asistido) {
  	                    return new AsistidoDTO(
  	                        asistido.getId(),
  	                        asistido.isActiva(),
  	                        asistido.getNombre(),
  	                        asistido.getApellido(),
  	                        asistido.getDni(),
  	                        asistido.getFechaNacimiento(),
  	                        asistido.getDomicilio(),
  	                        asistido.getOcupacion(),
  	                        asistido.getFamilia() != null ? asistido.getFamilia().getId() : null,
  	       	                asistido.getFechaRegistroAsistido()
  	                    );
  	                } else if (p instanceof Voluntario voluntario) {
  	                    return new VoluntarioDTO(
  	                        voluntario.getId(),
  	                        voluntario.isActiva(),
  	                        voluntario.getNombre(),
  	                        voluntario.getApellido(),
  	                        voluntario.getDni(),
  	                        voluntario.getFechaNacimiento(),
  	                        voluntario.getDomicilio(),
  	                        voluntario.getOcupacion(),
  	                        voluntario.getNroSeguroSocial()
  	                    );
  	                } else {
  	                    throw new IllegalStateException("Tipo de persona desconocido: " + p.getClass());
  	                }
  	            })
  	            .collect(Collectors.toList());
  	}
  	
  	//Listar todos los tipos de persona inactivas
  	@Override
  	public List<PersonaDTO> listarPersonasInactivas() {
  		List<Persona> personas = personaRepository.findByActivaFalse();
  		return personas.stream()
  	            .map(p -> {
  	                if (p instanceof Asistido asistido) {
  	                    return new AsistidoDTO(
  	                        asistido.getId(),
  	                        asistido.isActiva(),
  	                        asistido.getNombre(),
  	                        asistido.getApellido(),
  	                        asistido.getDni(),
  	                        asistido.getFechaNacimiento(),
  	                        asistido.getDomicilio(),
  	                        asistido.getOcupacion(),
  	                        asistido.getFamilia() != null ? asistido.getFamilia().getId() : null,
  	       	                asistido.getFechaRegistroAsistido()
  	                    );
  	                } else if (p instanceof Voluntario voluntario) {
  	                    return new VoluntarioDTO(
  	                        voluntario.getId(),
  	                        voluntario.isActiva(),
  	                        voluntario.getNombre(),
  	                        voluntario.getApellido(),
  	                        voluntario.getDni(),
  	                        voluntario.getFechaNacimiento(),
  	                        voluntario.getDomicilio(),
  	                        voluntario.getOcupacion(),
  	                        voluntario.getNroSeguroSocial()
  	                    );
  	                } else {
  	                    throw new IllegalStateException("Tipo de persona desconocido: " + p.getClass());
  	                }
  	            })
  	            .collect(Collectors.toList());
  	}

    //Busca una persona por su ID, devuelve un PersonaDTO
    @Override
    public PersonaDTO buscarPorId(Long id) {
        Persona persona = personaRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("No se encontró la persona con ID: " + id));

        if (persona instanceof Asistido asistido) {
            Long familiaId = (asistido.getFamilia() != null) ? asistido.getFamilia().getId() : null;

            return new AsistidoDTO(
                asistido.getId(),
                asistido.isActiva(),
                asistido.getNombre(),
                asistido.getApellido(),
                asistido.getDni(),
                asistido.getFechaNacimiento(),
                asistido.getDomicilio(),
                asistido.getOcupacion(),
                asistido.getFamilia() != null ? asistido.getFamilia().getId() : null,
   	            asistido.getFechaRegistroAsistido()
            );
        } else if (persona instanceof Voluntario voluntario) {
            return new VoluntarioDTO(
                voluntario.getId(),
                voluntario.isActiva(),
                voluntario.getNombre(),
                voluntario.getApellido(),
                voluntario.getDni(),
                voluntario.getFechaNacimiento(),
                voluntario.getDomicilio(),
                voluntario.getOcupacion(),
                voluntario.getNroSeguroSocial()
            );
        }

        throw new IllegalArgumentException("Tipo de persona desconocido");
    }


	
	public Optional<Persona> existsByDni(Integer dni) {
		return personaRepository.findByDni(dni);
	}
	
	 @Override
	    public Optional<Persona> buscarPorDni(Integer dni) {
	        return personaRepository.findByDni(dni);
	    }
	 
	    
	    @Override
	    public void eliminar(Long id) {
	        personaRepository.deleteById(id);
	    }
	
}
