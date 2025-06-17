package tuti.desi.servicios;

import java.util.List;

import tuti.desi.DTO.PersonaDTO;
import tuti.desi.DTO.VoluntarioDTO;
import tuti.desi.entidades.Voluntario;

public interface VoluntarioService {
	
	//NUEVOS METODOS
	
	List<VoluntarioDTO> listarAsistidosActivos();
	
	List<VoluntarioDTO> listarAsistidosInactivos();

	Voluntario guardarVoluntario(VoluntarioDTO voluntarioDTO);

	List<VoluntarioDTO> listarTodosAsistidos();

	void habilitar(Long id);
	
	void inhabilitar(Long id);

	PersonaDTO buscarPorId(Long id);
		
}
