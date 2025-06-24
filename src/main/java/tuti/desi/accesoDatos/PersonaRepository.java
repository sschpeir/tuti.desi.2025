package tuti.desi.accesoDatos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

import tuti.desi.entidades.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

    // Buscar por DNI
    Optional<Persona> findByDni(Integer dni);

    // Buscar por nombre
    List<Persona> findByNombreContainingIgnoreCase(String nombre);

    // Buscar por nombre y DNI
    Optional<Persona> findByNombreAndDni(String nombre, Integer dni);

    // Contar personas por nombre
    long countByNombreContaining(String nombre);
    
    //Buscar persona por ID
    Optional<Persona> findById(Integer id);

    //Comprobar si una persona existe por su DNI
	boolean existsByDni(Integer dni);
	
	//Devuelve un listado de personas activa=true
	List<Persona> findByActivaTrue();
	
	//Devuelve un listado de personas activa=false
	List<Persona> findByActivaFalse();

}
