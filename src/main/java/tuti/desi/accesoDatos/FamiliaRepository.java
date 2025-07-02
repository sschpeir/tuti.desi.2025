package tuti.desi.accesoDatos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

import tuti.desi.DTO.FamiliasConMiembrosActivosDTO;
import tuti.desi.DTO.FamiliasConMiembrosDTO;
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
	
	List<Familia> findByNombreLike(String nombre);

	List<Familia> findByIdAndActivaTrue(Long id);

	List<Familia> findByNombreLikeAndActivaTrue(String nombre);
	
	@Query("""
		    SELECT new tuti.desi.DTO.FamiliasConMiembrosDTO(
		        f.id,
		        f.nombre,
		        f.fechaRegistro,
		        f.activa,
		        COUNT(a),
		        COUNT(CASE WHEN a.activa = true THEN 1 ELSE null END)
		    )
		    FROM Familia f
		    LEFT JOIN Asistido a ON a.familia.id = f.id
		    GROUP BY f.id, f.nombre, f.fechaRegistro, f.activa
		""")
		List<FamiliasConMiembrosDTO> listadoFamiliasConAsistidos();

	//Listado de mimbros activos
	
	@Query("""
		    SELECT new tuti.desi.DTO.FamiliasConMiembrosActivosDTO(
		        f.id,
		        f.nombre,
		        f.fechaRegistro,
		        f.activa,
		        COUNT(a),
		        (SELECT MAX(e.fechaEntrega) FROM EntregaAsistencia e WHERE e.familia.id = f.id)
		    )
		    FROM Familia f
		    JOIN Asistido a ON a.familia.id = f.id
		    WHERE f.activa = true AND a.activa = true
		    GROUP BY f.id, f.nombre, f.fechaRegistro, f.activa
		""")
		List<FamiliasConMiembrosActivosDTO> listadoFamiliasConAsistidosActivos();
	
	//Listado de mimbros activos por nombre like
	
	@Query("""
		    SELECT new tuti.desi.DTO.FamiliasConMiembrosActivosDTO(
		        f.id,
		        f.nombre,
		        f.fechaRegistro,
		        f.activa,
		        COUNT(a),
		        (SELECT MAX(e.fechaEntrega) FROM EntregaAsistencia e WHERE e.familia.id = f.id)
		    )
		    FROM Familia f
		    JOIN Asistido a ON a.familia.id = f.id
		    WHERE f.activa = true AND a.activa = true AND f.nombre LIKE %:nombreFiltro%
		    GROUP BY f.id, f.nombre, f.fechaRegistro, f.activa
		""")
		List<FamiliasConMiembrosActivosDTO> listadoFamiliasConAsistidosActivosPorNombre(@Param("nombreFiltro") String nombreFiltro);

	//Listado de mimbros activos por id
	
	@Query("""
		    SELECT new tuti.desi.DTO.FamiliasConMiembrosActivosDTO(
		        f.id,
		        f.nombre,
		        f.fechaRegistro,
		        f.activa,
		        COUNT(a),
		        (SELECT MAX(e.fechaEntrega) FROM EntregaAsistencia e WHERE e.familia.id = f.id)
		    )
		    FROM Familia f
		    JOIN Asistido a ON a.familia.id = f.id
		    WHERE f.activa = true AND a.activa = true AND f.id = :id
		    GROUP BY f.id, f.nombre, f.fechaRegistro, f.activa
		""")
		List<FamiliasConMiembrosActivosDTO> listadoFamiliasConAsistidosActivosPorId(@Param("id") Long id);



    
}
