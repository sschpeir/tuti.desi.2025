package tuti.desi.accesoDatos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

import tuti.desi.entidades.Familia;


@Repository
public interface FamiliaRepository extends JpaRepository<Familia, Long> {


	// Buscar por ID    
    Optional<Familia> findById(Integer id);
    
    //Buscar por numero de familia
    Optional<Familia> findBynroFamilia(Integer nroFamilia);
    
	// Buscar por nombre
    Optional<Familia> findByNombre(String nombre);

    // Buscar todas las familias
    List<Familia> findAll();

    //Devuelve un listado de familias activa=true
	List<Familia> findByActivaTrue();
	
	//Devuelve un listado de familias activa=false
	List<Familia> findByActivaFalse();
	
	//
	boolean existsByNombre(String nombre);
    
}
