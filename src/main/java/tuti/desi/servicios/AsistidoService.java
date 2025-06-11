package tuti.desi.servicios;

import java.util.List;
import java.util.Optional;

import tuti.desi.entidades.Asistido;

public interface AsistidoService {

	void guardar(Asistido asistido);

	List<Asistido> listarPorFamiliaId(Long id);

	List<Asistido> listarTodos();

	void eliminar(Long id);

	Optional<Asistido> obtenerPorId(Long id);
	
}
