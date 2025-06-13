package tuti.desi.servicios;

import java.util.List;

import tuti.desi.DTO.RecetaDTO;
import tuti.desi.entidades.Receta;


public interface RecetaService {

	//Receta guardar(Receta receta);

	List<Receta> listasTodas();

	Receta guardar(RecetaDTO recetaDTO);

}
