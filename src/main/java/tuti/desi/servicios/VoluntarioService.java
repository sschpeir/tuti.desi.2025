package tuti.desi.servicios;

import java.util.List;

import tuti.desi.DTO.PersonaDTO;
import tuti.desi.DTO.VoluntarioDTO;
import tuti.desi.entidades.Voluntario;

public interface VoluntarioService {
	
	//NUEVOS METODOS

	Voluntario guardarVoluntario(VoluntarioDTO voluntarioDTO);

	void habilitar(Long id);
	
	void inhabilitar(Long id);

	PersonaDTO buscarPorId(Long id);

	List<VoluntarioDTO> listarTodosVoluntarios();

	List<VoluntarioDTO> listarVoluntariosActivos();
		
}
