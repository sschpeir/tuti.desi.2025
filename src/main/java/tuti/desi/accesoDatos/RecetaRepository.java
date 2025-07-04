package tuti.desi.accesoDatos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

import tuti.desi.DTO.RecetasConItemsYCaloriasDTO;
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
    
    //Listar recetas si activa = true
	List<Receta> findByActivaTrue();
	
	//Listar recetas si activa = false
	List<Receta> findByActivaFalse();
	
	//Listar por recetas parecidas al nombre
	List<Receta> findByNombreLike(String nombre);
	
	//Por ID y si estan activas
	Optional<Receta> findByIdAndActivaTrue(Long id);
	
	//Listar por recetas parecidas al nombre
	List<Receta> findByNombreLikeAndActivaTrue(String nombre);
	
	//Listado solicitado por el TP
	
	@Query("""
		    SELECT new tuti.desi.DTO.RecetasConItemsYCaloriasDTO(
		        r.id,
		        r.nombre,
		        r.activa,
		        SUM(i.calorias)
		    )
		    FROM Receta r
		    JOIN r.items i
		    WHERE r.activa = true
		      AND i.activa = true
		    GROUP BY r.id, r.nombre, r.activa
		""")
		List<RecetasConItemsYCaloriasDTO> listarRecetasConItemsActivosYCalorias();
	
	//Listado solicitado por el TP, pero para el filtro por calorias
	
	@Query("""
		    SELECT new tuti.desi.DTO.RecetasConItemsYCaloriasDTO(
		        r.id,
		        r.nombre,
		        r.activa,
		        SUM(i.calorias),
		        MIN(i.calorias),
		        MAX(i.calorias)
		    )
		    FROM Receta r
		    JOIN r.items i
		    WHERE r.activa = true
		      AND i.activa = true
		    GROUP BY r.id, r.nombre, r.activa
		    HAVING SUM(i.calorias) BETWEEN :minCalorias AND :maxCalorias
		""")
		List<RecetasConItemsYCaloriasDTO> listarRecetasConIngredientesActivosYCaloriasMinYMax(
		    @Param("minCalorias") int minCalorias,
		    @Param("maxCalorias") int maxCalorias
		);
	
	//Listado solicitado por el TP, pero para el filtro por id
	
	@Query("""
		    SELECT new tuti.desi.DTO.RecetasConItemsYCaloriasDTO(
		        r.id,
		        r.nombre,
		        r.activa,
		        SUM(i.calorias),
		        MIN(i.calorias),
		        MAX(i.calorias)
		    )
		    FROM Receta r
		    JOIN r.items i
		    WHERE r.activa = true
		      AND i.activa = true
		      AND r.id = :id
		    GROUP BY r.id, r.nombre, r.activa
		""")
		List<RecetasConItemsYCaloriasDTO> listarRecetasConIngredientesActivosYCaloriasPorId(@Param("id") Long id);
	
	

}
