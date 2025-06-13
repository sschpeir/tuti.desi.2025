package tuti.desi.servicios;

import java.util.List;

import tuti.desi.entidades.Ingrediente;

public interface IngredienteService {

	List<Ingrediente> listarTodos();

	void guardar(Ingrediente ingrediente);


}
