package tuti.desi.accesoDatos;

import org.springframework.data.jpa.repository.JpaRepository;

import tuti.desi.entidades.Voluntario;

public interface VoluntarioRepository extends JpaRepository<Voluntario, Long> {

}
