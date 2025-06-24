package tuti.desi.servicios;

import tuti.desi.DTO.PersonaDTO;
import tuti.desi.entidades.Persona;
import java.util.Optional;
import java.util.List;

public interface PersonaService {

	//Metodo de guardado/edicion
    Persona guardar(PersonaDTO personaDTO);

    //Metodo de obtencion de todas las personas
    List<PersonaDTO> listarTodos();
    
    Optional<Persona> buscarPorDni(Integer dni);
    
    PersonaDTO buscarPorId(Long id);

	void eliminar(Long id);

	List<PersonaDTO> listarPersonasInactivas();

	List<PersonaDTO> listarPersonasActivas();

}
