package tuti.desi.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tuti.desi.DTO.FamiliaDTO;
import tuti.desi.DTO.ItemRecetaDTO;
import tuti.desi.DTO.RecetaDTO;


import tuti.desi.accesoDatos.RecetaRepository;
import tuti.desi.entidades.Familia;
import tuti.desi.entidades.Receta;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecetaServiceImpl implements RecetaService {

    @Autowired
    private RecetaRepository recetaRepository;

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

        receta.setNombre(recetaDTO.getNombre());
        receta.setDescripcion(recetaDTO.getDescripcion());
        receta.setActiva(recetaDTO.isActiva());

        // Mapear ítems si vienen en el DTO
        if (recetaDTO.getItems() != null) {
            List<ItemReceta> items = recetaDTO.getItems().stream()
                    .map(dto -> {
                        ItemReceta item = new ItemReceta();
                        item.setId(dto.getId()); // si viene con ID, lo respeta
                        item.setIngrediente(dto.getIngrediente());
                        item.setCantidad(dto.getCantidad());
                        item.setReceta(receta); // relación bidireccional
                        return item;
                    }).collect(Collectors.toList());

            receta.setItems(items);
        }

        Receta recetaSalvada = recetaRepository.save(receta);

        // Construir el DTO de respuesta
        RecetaDTO resultado = new RecetaDTO();
        resultado.setId(recetaSalvada.getId());
        resultado.setNombre(recetaSalvada.getNombre());
        resultado.setDescripcion(recetaSalvada.getDescripcion());
        resultado.setActiva(recetaSalvada.isActiva());

        if (recetaSalvada.getItems() != null) {
            List<ItemRecetaDTO> itemsDTO = recetaSalvada.getItems().stream()
                    .map(item -> {
                        ItemRecetaDTO dto = new ItemRecetaDTO();
                        dto.setId(item.getId());
                        dto.setIngrediente(item.getIngrediente());
                        dto.setCantidad(item.getCantidad());
                        return dto;
                    }).collect(Collectors.toList());

            resultado.setItems(itemsDTO);
        }

        return resultado;
    }


	@Override
	public List<RecetaDTO> listarTodas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecetaDTO> listarFamiliasActivas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecetaDTO buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void inhabilitar(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void habilitar(Long id) {
		// TODO Auto-generated method stub
		
	}

	
    
    
}
