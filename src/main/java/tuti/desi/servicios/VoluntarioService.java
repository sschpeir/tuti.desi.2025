package tuti.desi.servicios;

import java.util.List;

import tuti.desi.DTO.PersonaDTO;
import tuti.desi.DTO.VoluntarioDTO;
import tuti.desi.entidades.Voluntario;

public interface VoluntarioService {
	
    //Guarda un voluntario
	Voluntario guardarVoluntario(VoluntarioDTO voluntarioDTO);

	//Habilita voluntarios
	void habilitar(Long id);
	
	//Deshabilita voluntarios
	void inhabilitar(Long id);

	//Busca una persona por su ID
	PersonaDTO buscarPorId(Long id);

	//Lista todos los voluntarios
	List<VoluntarioDTO> listarTodosVoluntarios();

	//Lista todos los voluntarios activos
	List<VoluntarioDTO> listarVoluntariosActivos();
		
}
