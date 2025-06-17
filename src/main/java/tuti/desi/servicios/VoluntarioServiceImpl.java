package tuti.desi.servicios;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tuti.desi.DTO.AsistidoDTO;
import tuti.desi.DTO.PersonaDTO;
import tuti.desi.DTO.VoluntarioDTO;
import tuti.desi.accesoDatos.AsistidoRepository;
import tuti.desi.accesoDatos.FamiliaRepository;
import tuti.desi.accesoDatos.PersonaRepository;
import tuti.desi.accesoDatos.VoluntarioRepository;
import tuti.desi.entidades.Asistido;
import tuti.desi.entidades.Familia;
import tuti.desi.entidades.Persona;
import tuti.desi.entidades.Voluntario;



@Service
public class VoluntarioServiceImpl implements VoluntarioService{

	@Autowired
    private VoluntarioRepository voluntarioRepository;
	
	@Autowired
    private PersonaRepository personaRepository;

	
	//Guardamos asistidos por otro lado:
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

            voluntarioDTO = new Voluntario();
        }

        // Datos comunes
        voluntario.setDni(voluntarioDTO.getDni());
        voluntario.setApellido(voluntarioDTO.getApellido());
        voluntario.setNombre(voluntarioDTO.getNombre());
        voluntario.setDomicilio(voluntarioDTO.getDomicilio());
        voluntario.setFechaNacimiento(voluntarioDTO.getFechaNacimiento());
        voluntario.setOcupacion(voluntarioDTO.getOcupacion());
        voluntario.setActiva(voluntarioDTO.isActiva());

        if (voluntarioDTO.getFamiliaId() != null) {
            Familia familia = familiaRepository.findById(voluntarioDTO.getFamiliaId())
                .orElseThrow(() -> new IllegalArgumentException("No se encontró la familia con ID: " + asistidoDTO.getFamiliaId()));
            voluntario.setFamilia(familia);
        }

        return personaRepository.save(voluntario);
    }
	
	//Listar todos los asistidos
		 @Override
		 public List<VoluntarioDTO> listarTodosAsistidos() {
		     List<Voluntario> voluntarios = voluntarioRepository.findAll();

		     return voluntarios.stream()
		         .map(voluntario -> {
		        	 VoluntarioDTO voluntarioDTO = new VoluntarioDTO(
		        			 voluntario.getId(),
		        			 voluntario.isActiva(),
		        			 voluntario.getNombre(),
		        			 voluntario.getApellido(),
		        			 voluntario.getDni(),
		        			 voluntario.getFechaNacimiento(),
		        			 voluntario.getDomicilio(),
		        			 voluntario.getOcupacion(),
		        			 voluntario.getNroSeguridadSocial()
		             );

		             return voluntarioDTO;
		         })
		         .collect(Collectors.toList());
		 }

		 //Listar todos los asistidos activos
		 @Override
		 public List<AsistidoDTO> listarAsistidosActivos() {
		     List<Persona> personas = personaRepository.findByActivaTrue();

		     return personas.stream()
		         .filter(p -> p instanceof Asistido)
		         .map(p -> {
		             Asistido asistido = (Asistido) p;
		             AsistidoDTO dto = new AsistidoDTO(
		                 asistido.getId(),
		                 asistido.isActiva(),
		                 asistido.getNombre(),
		                 asistido.getApellido(),
		                 asistido.getDni(),
		                 asistido.getFechaNacimiento(),
		                 asistido.getDomicilio(),
		                 asistido.getOcupacion(),
		                 asistido.getFamilia() != null ? asistido.getFamilia().getId() : null
		             );

		             if (asistido.getFamilia() != null) {
		                 dto.setFamiliaNombre(asistido.getFamilia().getNombre());
		             }

		             return dto;
		         })
		         .collect(Collectors.toList());
		 }


		//Listar asistidos inactivos
		@Override
		 public List<AsistidoDTO> listarAsistidosInactivos() {
		     List<Persona> personas = personaRepository.findByActivaFalse();

		     return personas.stream()
		         .filter(p -> p instanceof Asistido)
		         .map(p -> {
		             Asistido asistido = (Asistido) p;
		             AsistidoDTO dto = new AsistidoDTO(
		                 asistido.getId(),
		                 asistido.isActiva(),
		                 asistido.getNombre(),
		                 asistido.getApellido(),
		                 asistido.getDni(),
		                 asistido.getFechaNacimiento(),
		                 asistido.getDomicilio(),
		                 asistido.getOcupacion(),
		                 asistido.getFamilia() != null ? asistido.getFamilia().getId() : null
		             );

		             if (asistido.getFamilia() != null) {
		                 dto.setFamiliaNombre(asistido.getFamilia().getNombre());
		             }

		             return dto;
		         })
		         .collect(Collectors.toList());
		 }
		
		
		 //Funcion para inhabilitar asistidos
		 @Override
	    public void inhabilitar(Long id) {
		 		personaRepository.findById(id).ifPresent(asistido -> {
		 		asistido.setActiva(false);
	            personaRepository.save(asistido);
	        });
	    }

		//Funcion para habilitar asistidos
		@Override
		public void habilitar(Long id) {
				personaRepository.findById(id).ifPresent(asistido -> {
				asistido.setActiva(true);
		        personaRepository.save(asistido);
		    });
		}
		
		//Metodo para abstraer a personaService del controlador de AsistidoController
		@Override
		public PersonaDTO buscarPorId(Long id) {
		    Persona persona = personaRepository.findById(id)
		        .orElseThrow(() -> new IllegalArgumentException("No existe el asistido"));

		    if (!(persona instanceof Asistido asistido)) {
		        throw new IllegalArgumentException("La persona no es un asistido.");
		    }

		    return new AsistidoDTO(
		    	    asistido.getId(),
		    	    asistido.isActiva(),
		    	    asistido.getNombre(),
		    	    asistido.getApellido(),
		    	    asistido.getDni(),
		    	    asistido.getFechaNacimiento(),
		    	    asistido.getDomicilio(),
		    	    asistido.getOcupacion(),
		    	    asistido.getFamilia() != null ? asistido.getFamilia().getId() : null // acá extraés solo el ID
		    );

		}

		@Override
		public Voluntario guardarVoluntario(VoluntarioDTO voluntarioDTO) {
			// TODO Auto-generated method stub
			return null;
		}


	
		
	
}
