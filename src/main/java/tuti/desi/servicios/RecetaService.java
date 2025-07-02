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

	//Lista todas las recetas activas
	List<RecetaDTO> listarTodasActivas();

	//Listado de recetas activas con ingredientes activos y sumatoria de calorias
	List<RecetasConItemsYCaloriasDTO> listarRecetasConIngredientesActivosYCalorias();

	//Listado de recetas activas con ingredientes activos y sumatoria de calorias con filtro por calorias
	List<RecetasConItemsYCaloriasDTO> listarRecetasConIngredientesActivosYCaloriasMinYMax(Integer caloriasMin, Integer caloriasMax);

	//Listado de recetas activas con ingredientes activos y sumatoria de calorias con filtro por id
	List<RecetasConItemsYCaloriasDTO> listarRecetasConIngredientesActivosYCaloriasPorId(Long id);
	
	//EN DESUSO - Lista todas las recetas por nombre y que esten activas - Intento de filtro
	//List<RecetaDTO> filtrarNombreAndActivaTrue(String nombre);

	//EN DESUSO - Lista todas las recetas con id activas
	//List<RecetaDTO> filtrarIdActivas(Long id);
}
