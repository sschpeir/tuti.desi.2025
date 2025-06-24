package tuti.desi.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import tuti.desi.DTO.ItemRecetaDTO;
import tuti.desi.DTO.RecetaDTO;

import tuti.desi.accesoDatos.IngredienteRepository;
import tuti.desi.accesoDatos.ItemRecetaRepository;
import tuti.desi.accesoDatos.RecetaRepository;

import tuti.desi.entidades.Ingrediente;
import tuti.desi.entidades.ItemReceta;
import tuti.desi.entidades.Receta;

import java.util.List;

import java.util.stream.Collectors;

@Service
public class RecetaServiceImpl implements RecetaService {

    @Autowired
    private RecetaRepository recetaRepository;
    
    @Autowired
    private IngredienteRepository ingredienteRepository;
    
    @Autowired
    private ItemRecetaRepository itemRecetaRepository;
    
    @Autowired
    private IngredienteService ingredienteService;
    
    //Guardamos en funcion aparte para no tener problema con los hijos.
    @Override
    public RecetaDTO guardarEdicion(RecetaDTO recetaDTO) {
        if (recetaDTO.getId() == null) {
            throw new IllegalArgumentException("La receta debe tener un ID para poder editarse.");
        }

        Receta receta = recetaRepository.findById(recetaDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("No se encontró la receta con ID: " + recetaDTO.getId()));

        // Verificamos si se quiere cambiar el nombre (sin duplicados)
        if (!receta.getNombre().equals(recetaDTO.getNombre()) &&
            recetaRepository.existsByNombre(recetaDTO.getNombre())) {
            throw new IllegalArgumentException("Ya existe otra receta con ese nombre.");
        }

        // Actualizamos solo los campos básicos, sin tocar los items
        receta.setNombre(recetaDTO.getNombre());
        receta.setDescripcion(recetaDTO.getDescripcion());
        receta.setActiva(recetaDTO.isActiva());

        // NO tocar: receta.setItems(...) ni receta.getItems().clear()

        recetaRepository.save(receta);
        return recetaDTO;
    }

     
    //Metodo para guardar la receta
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

    //Listado de recetasDTO
    @Override
    public List<RecetaDTO> listarTodas() {
        List<Receta> recetas = recetaRepository.findAll();

        return recetas.stream()
                .map(this::recetaADTO)
                .collect(Collectors.toList());
    }

    //buscar una receta por ID, la pasa por el mapper y la devuelve como DTO.
	@Override
	public RecetaDTO buscarPorId(Long id) {
	    Receta receta = recetaRepository.findById(id)
	        .orElseThrow(() -> new IllegalArgumentException("No se encontró la receta con ID: " + id));

	    return recetaADTO(receta);
	}

    @Override
    public void inhabilitar(Long id) {
    	recetaRepository.findById(id).ifPresent(receta -> {
        	receta.setActiva(false);
        	recetaRepository.save(receta);
        });
    }

	@Override
	public void habilitar(Long id) {
		recetaRepository.findById(id).ifPresent(receta -> {
	        receta.setActiva(true);
	        recetaRepository.save(receta);
	    });
	}
	
	@Override
	public RecetaDTO recetaADTO(Receta receta) {
	    if (receta == null) {
	        return null;
	    }

	    RecetaDTO recetaDTO = new RecetaDTO();
	    recetaDTO.setId(receta.getId());
	    recetaDTO.setActiva(receta.isActiva());
	    recetaDTO.setNombre(receta.getNombre());
	    recetaDTO.setDescripcion(receta.getDescripcion());

	    // Convertimos los items si existen
	    if (receta.getItems() != null && !receta.getItems().isEmpty()) {
	        List<ItemRecetaDTO> itemsDTO = receta.getItems().stream()
	            .map(item -> {
	                ItemRecetaDTO itemDTO = new ItemRecetaDTO();
	                itemDTO.setId(item.getId());
	                itemDTO.setRecetaId(item.getReceta().getId());
	                itemDTO.setIngredienteId(item.getIngrediente().getId());
	                itemDTO.setCantidad(item.getCantidad());
	                itemDTO.setCalorias(item.getCalorias());
	                itemDTO.setActiva(item.isActiva());
	                itemDTO.setIngrediente(ingredienteService.ingredienteADTO(item.getIngrediente()));

	                return itemDTO;
	            })
	            .collect(Collectors.toList());

	        recetaDTO.setItems(itemsDTO);
	    }

	    return recetaDTO;
	}

	//Metodo para agregar un item a una receta a partir de el formulario de recetaListarIngredientes
	@Transactional
	public void agregarItemAReceta(ItemRecetaDTO itemRecetaDTO) {
	    Receta receta = recetaRepository.findById(itemRecetaDTO.getRecetaId())
	        .orElseThrow(() -> new RuntimeException("Receta no encontrada"));

	    if (itemRecetaDTO.getIngredienteId() == null) {
	        throw new IllegalArgumentException("El ID del ingrediente no puede ser null");
	    }

	    Ingrediente ingrediente = ingredienteRepository.findById(itemRecetaDTO.getIngredienteId())
	        .orElseThrow(() -> new RuntimeException("Ingrediente no encontrado"));

	    ItemReceta item = new ItemReceta();
	    item.setReceta(receta);
	    item.setIngrediente(ingrediente);
	    item.setCantidad(itemRecetaDTO.getCantidad());
	    item.setCalorias(itemRecetaDTO.getCalorias());
	    
	    item.setActiva(itemRecetaDTO.isActiva());

	    item = itemRecetaRepository.save(item); 

	    itemRecetaDTO.setId(item.getId()); 
	}

	@Override
	public List<RecetaDTO> filtrarNombre(String nombre) {
	    List<Receta> recetas = recetaRepository.findByNombreLike("%"+nombre+"%");
	    return recetas.stream()
	                  .map(this::recetaADTO)
	                  .collect(Collectors.toList());
	}
	
	@Override
	public List<RecetaDTO> filtrarNombreAndActivaTrue(String nombre) {
	    List<Receta> recetas = recetaRepository.findByNombreLikeAndActivaTrue("%"+nombre+"%");
	    return recetas.stream()
	                  .map(this::recetaADTO)
	                  .collect(Collectors.toList());
	}
	
	@Override
	public List<RecetaDTO> filtrarId(Long id) {
	    return recetaRepository.findById(id)
	            .map(r -> List.of(recetaADTO(r)))
	            .orElse(List.of());
	}
	
	@Override
	public List<RecetaDTO> filtrarIdActivas(Long id) {
	    return recetaRepository.findByIdAndActivaTrue(id)
	            .map(r -> List.of(recetaADTO(r)))
	            .orElse(List.of());
	}

	@Override
	public List<RecetaDTO> listarTodasActivas() {
	    List<Receta> recetas = recetaRepository.findByActivaTrue();

	    return recetas.stream()
	            .filter(Receta::isActiva) // <-- solo las activas
	            .map(this::recetaADTO)
	            .collect(Collectors.toList());
	}

    
}
