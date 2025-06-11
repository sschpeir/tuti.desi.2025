package tuti.desi.servicios;

import tuti.desi.entidades.Persona;
import java.util.Optional;
import java.util.List;

public interface PersonaService {

    Persona guardar(Persona persona);

    Optional<Persona> buscarPorDni(Integer dni);
    
    Optional<Persona> buscarPorId(Long id);

    List<Persona> listarTodos();

	void eliminar(Long id);
}
