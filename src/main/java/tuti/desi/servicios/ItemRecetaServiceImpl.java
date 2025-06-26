package tuti.desi.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tuti.desi.DTO.ItemRecetaDTO;

import tuti.desi.accesoDatos.ItemRecetaRepository;

import tuti.desi.entidades.ItemReceta;


@Service
public class ItemRecetaServiceImpl implements ItemRecetaService {

    @Autowired
    private ItemRecetaRepository itemRecetaRepository;
    
    @Autowired
    private IngredienteService ingredienteService;
    
    //Guardamos en funcion aparte para no tener problema con los hijos.
    //Se encontro metodo para guardar todo junto, solamente contempla edicion
    @Override
    public ItemRecetaDTO guardarEdicion(ItemRecetaDTO itemRecetaDTO) {
        if (itemRecetaDTO.getId() == null) {
            throw new IllegalArgumentException("El ID del ítem de receta no puede ser nulo para editar.");
        }

        ItemReceta itemReceta = itemRecetaRepository.findById(itemRecetaDTO.getId())
            .orElseThrow(() -> new RuntimeException("Ítem de receta no encontrado con ID: " + itemRecetaDTO.getId()));

        // Solo actualizamos campos editables
        itemReceta.setCantidad(itemRecetaDTO.getCantidad());
        itemReceta.setCalorias(itemRecetaDTO.getCalorias());
        itemReceta.setActiva(itemRecetaDTO.isActiva());

        itemRecetaRepository.save(itemReceta);
        return itemRecetaDTO;
    }

    //Busca un item de una receta en base al ID
    @Override
    public ItemRecetaDTO buscarPorId(Long id) {
        ItemReceta item = itemRecetaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Ítem de receta no encontrado con ID: " + id));

        ItemRecetaDTO dto = new ItemRecetaDTO();
        dto.setId(item.getId());
        dto.setRecetaId(item.getReceta().getId());
        dto.setIngredienteId(item.getIngrediente().getId());
        dto.setCantidad(item.getCantidad());
        dto.setCalorias(item.getCalorias());
        dto.setActiva(item.isActiva());
        dto.setIngrediente(ingredienteService.ingredienteADTO(item.getIngrediente()));

        return dto;
    }

    //Deshabilita los item de receta
	@Override
	public void deshabilitar(ItemRecetaDTO itemRecetaDTO) {
	    ItemReceta item = itemRecetaRepository.findById(itemRecetaDTO.getId())
	        .orElseThrow(() -> new IllegalArgumentException("ItemReceta no encontrado con ID: " + itemRecetaDTO.getId()));

	    item.setActiva(false);
	    itemRecetaRepository.save(item);
	}

	//Habilita los item de receta
	@Override
	public void habilitar(ItemRecetaDTO itemRecetaDTO) {
	    ItemReceta item = itemRecetaRepository.findById(itemRecetaDTO.getId())
	        .orElseThrow(() -> new IllegalArgumentException("ItemReceta no encontrado con ID: " + itemRecetaDTO.getId()));

	    item.setActiva(true);
	    itemRecetaRepository.save(item);
	}
    
}
