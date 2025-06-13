package tuti.desi.servicios;

import java.util.List;

import tuti.desi.DTO.RecetaDTO;
import tuti.desi.entidades.Receta;


public interface RecetaService {

	//Receta guardar(Receta receta);


	Receta guardar(RecetaDTO recetaDTO);

	List<RecetaDTO> listarTodas();

	RecetaDTO buscarPorId(Long id);

	

}
