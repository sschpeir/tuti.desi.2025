package tuti.desi.servicios;

import java.util.List;

import tuti.desi.DTO.IngredienteDTO;
import tuti.desi.entidades.Ingrediente;

public interface IngredienteService {
	
	//USADO - Metodo de guardado/edicion
	IngredienteDTO guardar(IngredienteDTO ingredienteDTO);
	
	//USADO - Metodo de obtencion de todas las familias
	List<IngredienteDTO> listarTodos();
	
	//Metodo de obtencion de todas las familias activas
	List<IngredienteDTO> listarIngredientesActivas();
	
	//USADO - Metodo para buscar un objeto familia por ID y devuelve DTO con sus integrantes
	IngredienteDTO buscarPorId(Long id);
	
	//USADO - Metodo para devolucion de un ingrediente a DTO
	IngredienteDTO ingredienteADTO(Ingrediente ingrediente);

	//USADO - Inhabilita un ingrediente
	void inhabilitar(Long id);
	
	//USADO - Habilita un ingrediente
	void habilitar(Long id);

	//NO USADO AUN - Lista ingredientes activos
	List<IngredienteDTO> listarIngredientesActivos();

}
