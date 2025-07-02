package tuti.desi.servicios;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tuti.desi.DTO.IngredienteDTO;
import tuti.desi.accesoDatos.IngredienteRepository;
import tuti.desi.entidades.Condimento;
import tuti.desi.entidades.Ingrediente;
import tuti.desi.entidades.Producto;

@Service
public class IngredienteServiceImpl implements IngredienteService{

	@Autowired
    private IngredienteRepository ingredienteRepository;

	
	//Guarda un ingrediente a partir de un DTO y devuelve un DTO o errores
	@Override
	public IngredienteDTO guardar(IngredienteDTO ingredienteDTO) {
	    if (ingredienteDTO == null) {
	        throw new IllegalArgumentException("El ingrediente no puede ser nulo");
	    }

	    Optional<Ingrediente> existente = ingredienteRepository.findByNombre(ingredienteDTO.getNombre());

	    if (existente.isPresent() &&
	        (ingredienteDTO.getId() == null || !existente.get().getId().equals(ingredienteDTO.getId()))) {
	        throw new IllegalArgumentException("Ya existe un ingrediente con el nombre: " + ingredienteDTO.getNombre());
	    }

	    Ingrediente ingrediente;

	    switch (ingredienteDTO.getTipoCondimento()) {
	        case "Producto":
	            Producto producto = new Producto();
	            producto.setId(ingredienteDTO.getId()); // importante para actualizar
	            producto.setNombre(ingredienteDTO.getNombre());
	            producto.setCalorias(ingredienteDTO.getCalorias());
	            producto.setActiva(ingredienteDTO.isActiva());
	            producto.setStockDisponible(ingredienteDTO.getStockDisponible());
	            producto.setPrecioActual(ingredienteDTO.getPrecioActual());
	            ingrediente = producto;
	            break;

	        case "Condimento":
	            Condimento condimento = new Condimento();
	            condimento.setId(ingredienteDTO.getId());
	            condimento.setNombre(ingredienteDTO.getNombre());
	            condimento.setCalorias(ingredienteDTO.getCalorias());
	            condimento.setActiva(ingredienteDTO.isActiva());
	            ingrediente = condimento;
	            break;

	        default:
	            throw new IllegalArgumentException("Tipo de ingrediente desconocido: " + ingredienteDTO.getTipoCondimento());
	    }

	    Ingrediente guardado = ingredienteRepository.save(ingrediente);
	    return ingredienteADTO(guardado);
	}


	//Lista todos los ingredientes

	@Override
	public List<IngredienteDTO> listarTodos() {
	    List<Ingrediente> ingredientes = ingredienteRepository.findAll();

	    return ingredientes.stream()
	            .map(this::ingredienteADTO)
	            .collect(Collectors.toList());
	}


	//Busca un ingrediente en la BBDD en base al ID y devuelve DTO si encuentra sino error
	@Override
	public IngredienteDTO buscarPorId(Long id) {
	    Ingrediente ingrediente = ingredienteRepository.findById(id)
	            .orElseThrow(() -> new IllegalArgumentException("Ingrediente no encontrado con ID: " + id));

	    return ingredienteADTO(ingrediente);
	}

	//Transforma un ingrediente a DTO (Ahorra mucho laburo)
	public IngredienteDTO ingredienteADTO(Ingrediente ingrediente) {
	    if (ingrediente == null) {
	        return null;
	    }

	    IngredienteDTO ingredienteDTO = new IngredienteDTO();
	    ingredienteDTO.setId(ingrediente.getId());
	    ingredienteDTO.setNombre(ingrediente.getNombre());
	    ingredienteDTO.setCalorias(ingrediente.getCalorias());
	    ingredienteDTO.setActiva(ingrediente.isActiva());

	    if (ingrediente instanceof Producto producto) {
	        ingredienteDTO.setTipoCondimento("Producto");
	        ingredienteDTO.setStockDisponible(producto.getStockDisponible());
	        ingredienteDTO.setPrecioActual(producto.getPrecioActual());
	    } else if (ingrediente instanceof Condimento) {
	        ingredienteDTO.setTipoCondimento("Condimento");
	    } else {
	        ingredienteDTO.setTipoCondimento("Ingrediente");
	    }

	    return ingredienteDTO;
	}

	 //Funcion para inhabilitar ingredientes
	 @Override
	   public void inhabilitar(Long id) {
			 	ingredienteRepository.findById(id).ifPresent(ingrediente -> {
			 		ingrediente.setActiva(false);
		 		ingredienteRepository.save(ingrediente);
	       });
	   }
	
	//Funcion para habilitar ingredientes
	@Override
	public void habilitar(Long id) {
			ingredienteRepository.findById(id).ifPresent(ingrediente -> {
				ingrediente.setActiva(true);
			ingredienteRepository.save(ingrediente);
	    });
	}
	
	//EN DESUSO -- Lista ingredientes activos (ideal para filtros o ahorrar laburo)
	/*@Override
	public List<IngredienteDTO> listarIngredientesActivos() {
	    List<Ingrediente> ingredientes = ingredienteRepository.findAll();

	    return ingredientes.stream()
	            .filter(Ingrediente::isActiva)
	            .map(this::ingredienteADTO)
	            .collect(Collectors.toList());
	}*/
	

	//EN DESUSO - Lista ingredientes con estado activo
	/*@Override
	public List<IngredienteDTO> listarIngredientesActivas() {
		// TODO Auto-generated method stub
		return null;
	}*/
	
}
