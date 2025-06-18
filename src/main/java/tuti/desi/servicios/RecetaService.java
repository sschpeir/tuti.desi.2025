package tuti.desi.servicios;

import java.util.List;

import tuti.desi.DTO.RecetaDTO;

import tuti.desi.entidades.Receta;

public interface RecetaService {
	
	//Metodo de guardado/edicion
	Receta guardar(RecetaDTO recetaDTO);
	
	//Metodo de obtencion de todas las familias
	List<RecetaDTO> listarTodas();
	
	//Metodo de obtencion de todas las familias activas
	List<RecetaDTO> listarFamiliasActivas();
	
	//Metodo para buscar un objeto familia por ID y devuelve DTO con sus integrantes
	RecetaDTO buscarPorId(Long id);

	//Inhabilita la familia
	void inhabilitar(Long id);
	
	//Habilita la familia
	void habilitar(Long id);

}
