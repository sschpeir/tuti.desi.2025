package tuti.desi.servicios;

import java.util.List;
import java.util.Optional;

import tuti.desi.entidades.Familia;
import tuti.desi.entidades.Persona;



public interface FamiliaService {

	List<Familia> listarFamilias();

	Familia guardarFamilia(Familia familia);
	
	Optional<Familia> buscarPorId(Long id);

	void eliminar(Long id);

	List<Familia> listarFamiliasHabilitadas();

}
