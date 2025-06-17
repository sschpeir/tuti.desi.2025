package tuti.desi.servicios;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tuti.desi.DTO.AsistidoDTO;
import tuti.desi.DTO.PersonaDTO;
import tuti.desi.accesoDatos.AsistidoRepository;
import tuti.desi.accesoDatos.FamiliaRepository;
import tuti.desi.accesoDatos.PersonaRepository;
import tuti.desi.entidades.Asistido;
import tuti.desi.entidades.Familia;
import tuti.desi.entidades.Persona;



@Service
public class AsistidoServiceImpl implements AsistidoService{

	@Autowired
    private AsistidoRepository asistidoRepository;
	
	@Autowired
    private PersonaRepository personaRepository;
	
	@Autowired
    private FamiliaRepository familiaRepository;

	
	//Guardamos asistidos por otro lado:
    public Asistido guardarAsistido(AsistidoDTO asistidoDTO) {
	    boolean esEdicion = asistidoDTO.getId() != null;
	    Asistido asistido;
	
	    if (esEdicion) {
	        asistido = (Asistido) personaRepository.findById(asistidoDTO.getId())
	            .orElseThrow(() -> new IllegalArgumentException("No se encontró el asistido con ID: " + asistidoDTO.getId()));
	
	        if (!asistido.getDni().equals(asistidoDTO.getDni()) &&
	            personaRepository.existsByDni(asistidoDTO.getDni())) {
	            throw new IllegalArgumentException("Ya existe otro persona con ese DNI");
	        }
	
	    } else {
	        if (personaRepository.existsByDni(asistidoDTO.getDni())) {
	            throw new IllegalArgumentException("Ya existe una persona con ese DNI");
	        }
	
	        asistido = new Asistido();
	    }
	
	    // Datos comunes
	    asistido.setDni(asistidoDTO.getDni());
	    asistido.setApellido(asistidoDTO.getApellido());
	    asistido.setNombre(asistidoDTO.getNombre());
	    asistido.setDomicilio(asistidoDTO.getDomicilio());
	    asistido.setFechaNacimiento(asistidoDTO.getFechaNacimiento());
	    asistido.setOcupacion(asistidoDTO.getOcupacion());
	    asistido.setActiva(asistidoDTO.isActiva());
	
	    if (asistidoDTO.getFamiliaId() != null) {
	        Familia familia = familiaRepository.findById(asistidoDTO.getFamiliaId())
	            .orElseThrow(() -> new IllegalArgumentException("No se encontró la familia con ID: " + asistidoDTO.getFamiliaId()));
	        asistido.setFamilia(familia);
	    }
	
	    return personaRepository.save(asistido);
	}
	
		 //Listar todos los asistidos
	 @Override
	 public List<AsistidoDTO> listarTodosAsistidos() {
	     List<Asistido> asistidos = asistidoRepository.findAll();

	     return asistidos.stream()
	         .map(asistido -> {
	             AsistidoDTO asitidoDTO = new AsistidoDTO(
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

	             // Agrega el nombre de la familia al DTO
	             if (asistido.getFamilia() != null) {
	            	 asitidoDTO.setFamiliaNombre(asistido.getFamilia().getNombre());
	             }

	             return asitidoDTO;
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
	             AsistidoDTO asistidoDTO = new AsistidoDTO(
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
	            	 asistidoDTO.setFamiliaNombre(asistido.getFamilia().getNombre());
	             }

	             return asistidoDTO;
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


	
		
	
}
