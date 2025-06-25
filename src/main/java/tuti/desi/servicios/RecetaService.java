package tuti.desi.servicios;

import java.util.List;

import tuti.desi.DTO.ItemRecetaDTO;
import tuti.desi.DTO.RecetaDTO;
import tuti.desi.DTO.RecetasConItemsYCaloriasDTO;
import tuti.desi.entidades.Receta;

public interface RecetaService {
	
	//Prueba de guardarReceta con items
	RecetaDTO guardarReceta(RecetaDTO recetaDTO);
	
	//Guardado de edicion
	RecetaDTO guardarEdicion(RecetaDTO recetaDTO);
	
	//Metodo de obtencion de todas las recetas
	List<RecetaDTO> listarTodas();
	
	//Metodo para buscar un objeto receta por ID y devuelve DTO con sus integrantes
	RecetaDTO buscarPorId(Long id);

	//Inhabilita una receta
	void inhabilitar(Long id);
	
	//Habilita una receta
	void habilitar(Long id);

	//Convierte los entity en DTO, ahorra laburo
	RecetaDTO recetaADTO(Receta receta);

	//Agrega items a una receta puntual
	void agregarItemAReceta(ItemRecetaDTO itemRecetaDTO);
	
	//Filtrar por nombre
	List<RecetaDTO> filtrarNombre(String nombre);

	//Filtrar por ID
	List<RecetaDTO> filtrarId(Long id);

	List<RecetaDTO> listarTodasActivas();

	List<RecetaDTO> filtrarIdActivas(Long id);

	List<RecetaDTO> filtrarNombreAndActivaTrue(String nombre);

	List<RecetasConItemsYCaloriasDTO> listarRecetasConIngredientesActivosYCalorias();

	
	

}
