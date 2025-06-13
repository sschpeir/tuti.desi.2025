package tuti.desi.accesoDatos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

import tuti.desi.DTO.RecetaDTO;
import tuti.desi.entidades.Receta;

@Repository
public interface RecetaRepository extends JpaRepository<Receta, Long> {

    // Buscar por ID
    Optional<Receta> findById(Long id);

    // Buscar por nombre
    List<Receta> findByNombreContainingIgnoreCase(String nombre);
    
    // Buscar todas las recetas
    List<Receta> findAll();

    // Buscar si existe la receta por ID
    boolean existsById(Long id);
    
    // Buscar si existe la receta por nombre
    boolean existsByNombre(String nombre);

    

}
