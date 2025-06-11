package tuti.desi.accesoDatos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tuti.desi.entidades.Asistido;

public interface AsistidoRepository extends JpaRepository<Asistido, Long> {
	
	
	//Devuelve un listado de personas segun el ID de la familia
	List<Asistido> findByFamiliaId(Long familiaId);
	
	
	//Devuelve si existe una persona segun su DNI
	boolean existsByDni(Integer dni);

	//Devuelve una persona encontrada por su DNI
	Optional<Asistido> findByDni(Integer dni);



}
