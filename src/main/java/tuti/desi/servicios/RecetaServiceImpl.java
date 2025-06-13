package tuti.desi.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tuti.desi.DTO.RecetaDTO;
import tuti.desi.accesoDatos.RecetaRepository;
import tuti.desi.entidades.Receta;

@Service 
public class RecetaServiceImpl implements RecetaService {
	
	@Autowired
    private RecetaRepository recetaRepository;
	
	// Guardar una entidad receta
	@Override
    public Receta guardar(RecetaDTO recetaDTO) {
		Receta receta = new Receta();
		receta.setId(recetaDTO.getId());
		receta.setNombre(recetaDTO.getNombre());
		receta.setDescripcion(recetaDTO.getDescripcion());
		receta.setActiva(recetaDTO.isActiva());
		//Comprueba que no exista una receta con el mismo nombre, si existe tira error
		if (recetaRepository.existsByNombre(receta.getNombre())) {
	        throw new IllegalArgumentException("Ya existe una receta con ese nombre");
	    }
        return recetaRepository.save(receta);
    }
	
	// Lista todas las recetas
	@Override
    public List<Receta> listasTodas() {
        return recetaRepository.findAll();
    }

	
	
	
	
	
	
	

}
