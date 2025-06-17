package tuti.desi.servicios;

import java.util.List;

import tuti.desi.DTO.IngredienteDTO;
import tuti.desi.DTO.RecetaDTO;
import tuti.desi.entidades.Ingrediente;
import tuti.desi.entidades.Receta;

public interface IngredienteService {

	
	//Firma para listar todos los ingredientes
	List<RecetaDTO> listarTodos();

	//Firma para guardar un ingrediente
	Ingrediente guardar(IngredienteDTO ingredienteDTO);


}
