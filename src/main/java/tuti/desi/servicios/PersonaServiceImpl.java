package tuti.desi.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tuti.desi.accesoDatos.PersonaRepository;
import tuti.desi.entidades.Persona;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public Persona guardar(Persona persona) {
        return personaRepository.save(persona);
    }

    @Override
    public Optional<Persona> buscarPorDni(Integer dni) {
        return personaRepository.findByDni(dni);
    }

    @Override
    public List<Persona> listarTodos() {
        return personaRepository.findAll();
    }
    
    @Override
    public void eliminar(Long id) {
        personaRepository.deleteById(id);
    }

	@Override
	public Optional<Persona> buscarPorId(Long id) {
		return personaRepository.findById(id);
	}
	
	public Optional<Persona> existsByDni(Integer dni) {
		return personaRepository.findByDni(dni);
	}
}
