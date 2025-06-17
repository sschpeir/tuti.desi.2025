package tuti.desi.servicios;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tuti.desi.DTO.IngredienteDTO;
import tuti.desi.DTO.RecetaDTO;
import tuti.desi.accesoDatos.IngredienteRepository;
import tuti.desi.entidades.Condimento;
import tuti.desi.entidades.Ingrediente;
import tuti.desi.entidades.Producto;
import tuti.desi.entidades.Receta;

@Service
public class IngredienteServiceImpl implements IngredienteService {
	
	@Autowired
    private IngredienteRepository ingredienteRepository;

	
	//Salvar un ingrediente
	@Override
	public Ingrediente guardar(IngredienteDTO ingredienteDTO) {
	    Ingrediente ingrediente;

	    if ("Condimento".equalsIgnoreCase(ingredienteDTO.getTipoCondimento())) {
	        ingrediente = new Condimento();
	    } else {
	        Producto producto = new Producto();
	        producto.setStockDisponible(ingredienteDTO.getStockDisponible());
	        producto.setPrecioActual(ingredienteDTO.getPrecioActual());
	        ingrediente = producto; // Producto es un Ingrediente
	    }

	    ingrediente.setId(ingredienteDTO.getId());
	    ingrediente.setNombre(ingredienteDTO.getNombre());
	    ingrediente.setCalorias(ingredienteDTO.getCalorias());
	    ingrediente.setActiva(ingredienteDTO.isActiva());
	    if (ingredienteRepository.existsByNombre(ingrediente.getNombre())) {
	        throw new IllegalArgumentException("Ya existe un ingrediente con ese nombre");
	    }
	    return ingredienteRepository.save(ingrediente);
		}

	//Listar Ingredientes
	/*@Override
	public List<IngredienteDTO> listarTodos() {
	    return ingredienteRepository.findAll().stream()
	        .map(ingrediente -> new IngredienteDTO(
	            ingrediente.getId(),
	            ingrediente.getNombre(),
	            ingrediente.isActiva(),
	            ingrediente.getTipoCondimento(),
	            "Producto".equals(ingrediente.getTipoCondimento()) ? ingrediente.getPrecioActual() : null,
	            "Producto".equals(ingrediente.getTipoCondimento()) ? ingrediente.getStockDisponible() : null
	        ))
	        .collect(Collectors.toList());
	}*/
	


}
