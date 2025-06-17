package tuti.desi.accesoDatos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tuti.desi.entidades.Voluntario;

public interface VoluntarioRepository extends JpaRepository<Voluntario, Long> {
	
	//Devuelve si existe una persona segun su DNI
	boolean existsByDni(Integer dni);

	//Devuelve una persona encontrada por su DNI
	Optional<Voluntario> findByDni(Integer dni);



}
