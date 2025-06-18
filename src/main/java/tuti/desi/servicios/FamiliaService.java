package tuti.desi.servicios;

import java.util.List;

import tuti.desi.DTO.FamiliaDTO;
import tuti.desi.entidades.Familia;

public interface FamiliaService {
	
	//Metodo de guardado/edicion
	Familia guardar(FamiliaDTO familiaDTO);
	
	//Metodo de obtencion de todas las familias
	List<FamiliaDTO> listarTodas();
	
	//Metodo de obtencion de todas las familias activas
	List<FamiliaDTO> listarFamiliasActivas();
	
	//Metodo para buscar un objeto familia por ID y devuelve DTO con sus integrantes
	FamiliaDTO buscarPorId(Long id);

	//Inhabilita la familia
	void inhabilitar(Long nroFamilia);
	
	//Habilita la familia
	void habilitar(Long nroFamilia);

}
