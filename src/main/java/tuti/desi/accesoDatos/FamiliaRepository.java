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
    
    Optional<Familia> findBynroFamilia(Integer nroFamilia);
    
	// Buscar por nombre
    Optional<Familia> findByNombre(String nombre);

    // Buscar por nombre
    List<Familia> findAll();

	List<Familia> findByActivaTrue();
    
}
