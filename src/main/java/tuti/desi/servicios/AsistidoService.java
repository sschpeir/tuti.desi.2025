package tuti.desi.servicios;

import java.util.List;

import tuti.desi.DTO.AsistidoDTO;
import tuti.desi.DTO.PersonaDTO;
import tuti.desi.entidades.Asistido;

public interface AsistidoService {
	
	//NUEVOS METODOS
	
	List<AsistidoDTO> listarAsistidosActivos();

	Asistido guardarAsistido(AsistidoDTO asistidoDTO);

	List<AsistidoDTO> listarTodosAsistidos();

	void habilitar(Long id);
	
	void inhabilitar(Long id);

	PersonaDTO buscarPorId(Long id);

	List<AsistidoDTO> listarAsistidosSinFamilia();

	List<AsistidoDTO> listarFiltrado(boolean soloActivos, boolean sinFamilia);

	AsistidoDTO asistidoADTO(Asistido asistido);
		
}
