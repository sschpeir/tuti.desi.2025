package tuti.desi.servicios;

import java.util.List;

import tuti.desi.DTO.IngredienteDTO;

import tuti.desi.entidades.Ingrediente;

public interface IngredienteService {
	
	//Metodo de guardado/edicion
	Ingrediente guardar(IngredienteDTO ingredienteDTO);
	
	//Metodo de obtencion de todas las familias
	List<IngredienteDTO> listarTodos();
	
	//Metodo de obtencion de todas las familias activas
	List<IngredienteDTO> listarIngredientesActivas();
	
	//Metodo para buscar un objeto familia por ID y devuelve DTO con sus integrantes
	IngredienteDTO buscarPorId(Long id);
	
	//Metodo para devolucion de un ingrediente a DTO
	IngredienteDTO ingredienteADTO(Ingrediente ingrediente);

	//Inhabilita la familia
	void inhabilitar(Long id);
	
	//Habilita la familia
	void habilitar(Long id);

}
