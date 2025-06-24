package tuti.desi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tuti.desi.DTO.IngredienteDTO;
import tuti.desi.DTO.ItemRecetaDTO;
import tuti.desi.servicios.ItemRecetaService;
import tuti.desi.servicios.IngredienteService;


@Controller
@RequestMapping("/itemRecetaEditar")
public class ItemRecetaEditarController {

	@Autowired
    private ItemRecetaService itemRecetaService;
	
	@Autowired
    private IngredienteService ingredienteService;
	
	//CHECADO X
	
	//Si solicitas un GET, carga un modelo de familiaDTO a partir del ID
	@GetMapping("/{id}")
    public String cargarFormulario(@PathVariable("id") Long id, Model model) {
        try {
			ItemRecetaDTO itemRecetaDTO = itemRecetaService.buscarPorId(id);
			IngredienteDTO ingrediente = ingredienteService.buscarPorId(itemRecetaDTO.getIngredienteId());
			model.addAttribute("itemRecetaDTO", itemRecetaDTO);
			model.addAttribute("ingrediente", ingrediente);
		        
	        return "itemRecetaEditar"; 
	        //Sino te devuelve un error lo mandamos a familiaError
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "itemRecetaEditar"; 
        }
    }
	
	//CHECADO X
	
	//Cuando al formulario lo submiteas, entonces intenta guardar, sino catchea el error
	@PostMapping
    public String guardarFormulario(@ModelAttribute("itemRecetaDTO") ItemRecetaDTO itemRecetaDTO, Model model) {
        try {
        	itemRecetaService.guardarEdicion(itemRecetaDTO);
            return "redirect:/recetaListar/"+itemRecetaDTO.getRecetaId()+"/ingredientes";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("itemRecetaDTO", itemRecetaDTO);
            return "itemRecetaEditar";
        }
    }
	
	//CHECADO X
	
	@GetMapping("/{id}/deshabilitar")
	public String deshabilitarItem(@PathVariable("id") Long id) {
	    ItemRecetaDTO itemRecetaDTO = itemRecetaService.buscarPorId(id);
	    itemRecetaService.deshabilitar(itemRecetaDTO);
	    
	    return "redirect:/recetaListar/" + itemRecetaDTO.getRecetaId() + "/ingredientes";
	}


	//CHECADO X
	
	@GetMapping("/{id}/habilitar")
	public String habilitarItem(@PathVariable("id") Long id) {
	    ItemRecetaDTO itemRecetaDTO = itemRecetaService.buscarPorId(id);
	    itemRecetaService.habilitar(itemRecetaDTO);
	    
	    return "redirect:/recetaListar/" + itemRecetaDTO.getRecetaId() + "/ingredientes";
	}

	
}
