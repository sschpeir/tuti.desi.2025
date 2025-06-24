package tuti.desi.servicios;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import tuti.desi.DTO.PersonaDTO;
import tuti.desi.DTO.VoluntarioDTO;


import tuti.desi.accesoDatos.PersonaRepository;
import tuti.desi.accesoDatos.VoluntarioRepository;

import tuti.desi.entidades.Persona;
import tuti.desi.entidades.Voluntario;



@Service
public class VoluntarioServiceImpl implements VoluntarioService{

	@Autowired
    private VoluntarioRepository voluntarioRepository;
	
	@Autowired
    private PersonaRepository personaRepository;

	
	//Guardamos Voluntarios:
    public Voluntario guardarVoluntario(VoluntarioDTO voluntarioDTO) {
        boolean esEdicion = voluntarioDTO.getId() != null;
        Voluntario voluntario;

        if (esEdicion) {
        	voluntario = (Voluntario) personaRepository.findById(voluntarioDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el asistido con ID: " + voluntarioDTO.getId()));

            if (!voluntario.getDni().equals(voluntarioDTO.getDni()) &&
                personaRepository.existsByDni(voluntarioDTO.getDni())) {
                throw new IllegalArgumentException("Ya existe otro persona con ese DNI");
            }

        } else {
            if (personaRepository.existsByDni(voluntarioDTO.getDni())) {
                throw new IllegalArgumentException("Ya existe una persona con ese DNI");
            }

            voluntario = new Voluntario();
        }

        // Datos comunes
        voluntario.setDni(voluntarioDTO.getDni());
        voluntario.setApellido(voluntarioDTO.getApellido());
        voluntario.setNombre(voluntarioDTO.getNombre());
        voluntario.setDomicilio(voluntarioDTO.getDomicilio());
        voluntario.setFechaNacimiento(voluntarioDTO.getFechaNacimiento());
        voluntario.setOcupacion(voluntarioDTO.getOcupacion());
        voluntario.setActiva(voluntarioDTO.isActiva());
        voluntario.setFechaRegistro(voluntarioDTO.getFechaRegistro());
        voluntario.setNroSeguroSocial(voluntarioDTO.getNroSeguroSocial());

        return personaRepository.save(voluntario);
    }

		 //Listar todos los asistidos activos
		 @Override
		 public List<VoluntarioDTO> listarVoluntariosActivos() {
			 //Toma un listado de personas donde disponen el estado de activa=true
		     List<Persona> personas = personaRepository.findByActivaTrue();
		     //Filtra para que los voluntarios, se vayan mapeando en un listado como coleccion.
		     return personas.stream()
		         .filter(persona -> persona instanceof Voluntario)
		         .map(persona -> {
		        	 Voluntario voluntario = (Voluntario) persona;
		             VoluntarioDTO voluntarioDTO = new VoluntarioDTO(
		            		 voluntario.getId(),
		            		 voluntario.isActiva(),
		            		 voluntario.getNombre(),
		            		 voluntario.getApellido(),
		            		 voluntario.getDni(),
		            		 voluntario.getFechaNacimiento(),
		            		 voluntario.getDomicilio(),
		            		 voluntario.getOcupacion(),
		            		 voluntario.getFechaRegistro(),
		            		 voluntario.getNroSeguroSocial()
		            		 );
		             return voluntarioDTO;
		         })
		         .collect(Collectors.toList());
		 }
		
		
		 //Funcion para inhabilitar voluntarios
		 @Override
	    public void inhabilitar(Long id) {
		 		personaRepository.findById(id).ifPresent(voluntario -> {
		 		voluntario.setActiva(false);
	            personaRepository.save(voluntario);
	        });
	    }

		//Funcion para habilitar voluntarios
		@Override
		public void habilitar(Long id) {
				personaRepository.findById(id).ifPresent(voluntario -> {
				voluntario.setActiva(true);
		        personaRepository.save(voluntario);
		    });
		}
		
		//Metodo para abstraer a personaService del controlador de VoluntarioController
		@Override
		public PersonaDTO buscarPorId(Long id) {
		    Persona persona = personaRepository.findById(id)
		        .orElseThrow(() -> new IllegalArgumentException("No existe el voluntario"));

		    if (!(persona instanceof Voluntario voluntario)) {
		        throw new IllegalArgumentException("La persona no es un voluntario.");
		    }

		    return new VoluntarioDTO(
		    		voluntario.getId(),
		    		voluntario.isActiva(),
		    		voluntario.getNombre(),
		    		voluntario.getApellido(),
		    		voluntario.getDni(),
		    		voluntario.getFechaNacimiento(),
		    		voluntario.getDomicilio(),
		    		voluntario.getOcupacion(),
		    		voluntario.getFechaRegistro(),
		    		voluntario.getNroSeguroSocial()
		    );

		}

		//Metodo para listar todos los voluntarios.
		@Override
		public List<VoluntarioDTO> listarTodosVoluntarios() {
			//Invoca una lista de voluntarios
		    List<Voluntario> voluntarios = voluntarioRepository.findAll();
		    //Los transforma en una lista de VoluntariosDTO y los devuelve como coleccion
		    return voluntarios.stream()
		            .map(voluntario -> {
		                VoluntarioDTO voluntarioDTO = new VoluntarioDTO();
		                
		                voluntarioDTO.setId(voluntario.getId());
		                voluntarioDTO.setFechaRegistro(voluntario.getFechaRegistro());
		                voluntarioDTO.setDni(voluntario.getDni());
		                voluntarioDTO.setNombre(voluntario.getNombre());
		                voluntarioDTO.setApellido(voluntario.getApellido());
		                voluntarioDTO.setFechaNacimiento(voluntario.getFechaNacimiento());
		                voluntarioDTO.setDomicilio(voluntario.getDomicilio());
		                voluntarioDTO.setOcupacion(voluntario.getOcupacion());
		                voluntarioDTO.setActiva(voluntario.isActiva());
		                voluntarioDTO.setTipoPersona("Voluntario");
		                voluntarioDTO.setNroSeguroSocial(voluntario.getNroSeguroSocial());
		                
		                return voluntarioDTO;
		            })
		            .collect(Collectors.toList());
		}
	
}
