package tuti.desi.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tuti.desi.DTO.FamiliaDTO;
import tuti.desi.DTO.IngredienteDTO;
import tuti.desi.DTO.ItemRecetaDTO;
import tuti.desi.DTO.RecetaDTO;
import tuti.desi.accesoDatos.IngredienteRepository;
import tuti.desi.accesoDatos.RecetaRepository;
import tuti.desi.entidades.Condimento;
import tuti.desi.entidades.Ingrediente;
import tuti.desi.entidades.ItemReceta;
import tuti.desi.entidades.Producto;
import tuti.desi.entidades.Receta;
import tuti.desi.servicios.IngredienteService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecetaServiceImpl implements RecetaService {

    @Autowired
    private RecetaRepository recetaRepository;
    
    @Autowired
    private IngredienteRepository ingredienteRepository;
    
    @Autowired
    private IngredienteService ingredienteService;


	//Metodo de guardado/edicion
    @Override
    public RecetaDTO guardar(RecetaDTO recetaDTO) {
        boolean esEdicion = recetaDTO.getId() != null;
        Receta receta;

        if (esEdicion) {
            receta = recetaRepository.findById(recetaDTO.getId())
                    .orElseThrow(() -> new IllegalArgumentException("No se encontró la receta con ID: " + recetaDTO.getId()));

            if (!receta.getNombre().equals(recetaDTO.getNombre()) &&
                recetaRepository.existsByNombre(recetaDTO.getNombre())) {
                throw new IllegalArgumentException("Ya existe otra receta con ese nombre");
            }

            // Limpiar ítems previos (gracias a orphanRemoval)
            receta.getItems().clear();

        } else {
            if (recetaRepository.existsByNombre(recetaDTO.getNombre())) {
                throw new IllegalArgumentException("Ya existe una receta con ese nombre");
            }

            receta = new Receta();
        }


        Receta recetaSalvada = recetaRepository.save(receta);
		return recetaDTO;

    }
     // Construir el DTO de respuesta

    @Override
    public RecetaDTO guardarReceta(RecetaDTO recetaDTO) {
        boolean esEdicion = recetaDTO.getId() != null;
        Receta receta;

        if (esEdicion) {
            receta = recetaRepository.findById(recetaDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("No se encontró la receta con ID: " + recetaDTO.getId()));

            if (!receta.getNombre().equals(recetaDTO.getNombre()) &&
                recetaRepository.existsByNombre(recetaDTO.getNombre())) {
                throw new IllegalArgumentException("Ya existe otra receta con ese nombre");
            }

            // Eliminar los items anteriores si se actualizan completamente
            receta.getItems().clear();

        } else {
            if (recetaRepository.existsByNombre(recetaDTO.getNombre())) {
                throw new IllegalArgumentException("Ya existe una receta con ese nombre");
            }

            receta = new Receta();
        }

        receta.setNombre(recetaDTO.getNombre());
        receta.setActiva(recetaDTO.isActiva());
        receta.setDescripcion(recetaDTO.getDescripcion());

        // Mapear items del DTO a entidad
        if (recetaDTO.getItems() != null) {
            List<ItemReceta> items = recetaDTO.getItems().stream().map(dto -> {
                Ingrediente ingrediente = ingredienteRepository.findById(dto.getIngrediente().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Ingrediente no encontrado con ID: " + dto.getIngrediente().getId()));

                ItemReceta item = new ItemReceta();
                item.setCantidad(dto.getCantidad());
                item.setIngrediente(ingrediente);
                item.setReceta(receta); // vínculo bidireccional si aplica
                return item;
            }).collect(Collectors.toList());

            receta.setItems(items);
        }

        Receta guardada = recetaRepository.save(receta);
        return recetaADTO(guardada); // ← si querés devolver DTO actualizado
    }




    @Override
    public List<RecetaDTO> listarTodos() {
        List<Receta> recetas = recetaRepository.findAll();

        return recetas.stream()
                .map(this::recetaADTO)
                .collect(Collectors.toList());
    }


	@Override
	public List<RecetaDTO> listarFamiliasActivas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecetaDTO buscarPorId(Long id) {
	    Receta receta = recetaRepository.findById(id)
	        .orElseThrow(() -> new IllegalArgumentException("No se encontró la receta con ID: " + id));

	    return recetaADTO(receta);
	}


	@Override
	public void inhabilitar(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void habilitar(Long id) {
		// TODO Auto-generated method stub
		
	}
	
	
	  //Transforma una receta a DTO (Ahorra mucho laburo)
	public RecetaDTO recetaADTO(Receta receta) {
	    if (receta == null) {
	        return null;
	    }

	    RecetaDTO recetaDTO = new RecetaDTO();
	    recetaDTO.setId(receta.getId());
	    recetaDTO.setActiva(receta.isActiva());
	    recetaDTO.setNombre(receta.getNombre());
	    recetaDTO.setDescripcion(receta.getDescripcion());

	    // Agregar items si existen
	    if (receta.getItems() != null && !receta.getItems().isEmpty()) {
	        List<ItemRecetaDTO> itemsDTO = receta.getItems().stream()
	            .map(item -> {
	                ItemRecetaDTO itemDTO = new ItemRecetaDTO();
	                itemDTO.setId(item.getId());
	                itemDTO.setCantidad(item.getCantidad());
	                itemDTO.setCalorias(item.getCalorias());
	                itemDTO.setIngrediente(ingredienteService.ingredienteADTO(item.getIngrediente()));
	                return itemDTO;
	            }).collect(Collectors.toList());

	        recetaDTO.setItems(itemsDTO);
	    }

	    return recetaDTO;
	}

	//
    
    
}
