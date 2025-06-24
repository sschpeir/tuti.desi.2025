package tuti.desi.accesoDatos;

import org.springframework.data.jpa.repository.JpaRepository;

import tuti.desi.entidades.ItemReceta;

public interface ItemRecetaRepository extends JpaRepository<ItemReceta, Long> {

}
