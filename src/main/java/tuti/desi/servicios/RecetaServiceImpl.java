package tuti.desi.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tuti.desi.DTO.ItemRecetaDTO;
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
	
	//Listar todas las recetas
	@Override
	public List<RecetaDTO> listarTodas() {
	    List<Receta> recetas = recetaRepository.findAll();
	    System.out.println("Cantidad de recetas: " + recetas.size());

	    return recetas.stream()
	        .map(receta -> new RecetaDTO(
	            receta.getId(),
	            receta.getNombre(),
	            receta.getDescripcion(),
	            receta.isActiva(),
	            (receta.getItems() != null)
	                ? receta.getItems().stream()
	                    .map(item -> new ItemRecetaDTO(
	                        item.getId(),
	                        item.getCantidad(),
	                        item.getCalorias()))
	                    .collect(Collectors.toList())
	                : new ArrayList<>()
	        ))
	        .collect(Collectors.toList());
	}
	
	//Editar una receta segun ID
	@Override
	public RecetaDTO buscarPorId(Long id) {
	    Receta receta = recetaRepository.findById(id)
	        .orElseThrow(() -> new IllegalArgumentException("Receta no encontrada con id: " + id));

	    return new RecetaDTO(
	        receta.getId(),
	        receta.getNombre(),
	        receta.getDescripcion(),
	        receta.isActiva(),
	        receta.getItems() != null
	            ? receta.getItems().stream()
	                .map(item -> new ItemRecetaDTO(
	                    item.getId(),
	                    item.getCantidad(),
	                    item.getCalorias()))
	                .collect(Collectors.toList())
	            : new ArrayList<>()
	    );
	}

}
