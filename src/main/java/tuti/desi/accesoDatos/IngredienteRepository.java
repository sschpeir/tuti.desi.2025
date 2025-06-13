package tuti.desi.accesoDatos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import tuti.desi.entidades.Ingrediente;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {
	
	
	//Devuelve un listado de personas segun el ID de la familia
	Optional<Ingrediente> findByNombre(String nombre);
	
	
	//Devuelve si existe una persona segun su DNI
	boolean existsByNombre(String nombre);

	//Devuelve una persona encontrada por su DNI
	Optional<Ingrediente> findById(Integer id);
	
	






}
