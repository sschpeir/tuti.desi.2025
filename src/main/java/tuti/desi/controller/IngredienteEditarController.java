package tuti.desi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import tuti.desi.DTO.IngredienteDTO;
import tuti.desi.servicios.IngredienteService;

@Controller
@RequestMapping("/ingredienteEditar")
public class IngredienteEditarController {

    @Autowired
    private IngredienteService ingredienteService;

    @GetMapping("/{id}")
    public String cargarFormulario(@PathVariable("id") Long id, Model model) {
        try {
            IngredienteDTO ingredienteDTO = ingredienteService.buscarPorId(id);
            
            IngredienteForm ingredienteForm = new IngredienteForm();

            ingredienteForm.setId(ingredienteDTO.getId());
            ingredienteForm.setActiva(ingredienteDTO.isActiva());
            ingredienteForm.setNombre(ingredienteDTO.getNombre());
            ingredienteForm.setCalorias(ingredienteDTO.getCalorias());
            ingredienteForm.setTipoIngrediente(ingredienteDTO.getTipoCondimento());
            ingredienteForm.setPrecioActual(ingredienteDTO.getPrecioActual());
            ingredienteForm.setStockDisponible(ingredienteDTO.getStockDisponible());
            
            model.addAttribute("ingredienteForm", ingredienteForm);

            // Retornar vista según tipo
            if ("Producto".equalsIgnoreCase(ingredienteDTO.getTipoCondimento())) {
                return "ingredienteEditarProducto";
            } else if ("Condimento".equalsIgnoreCase(ingredienteDTO.getTipoCondimento())) {
                
            	return "ingredienteEditarCondimento";
            } else {
                model.addAttribute("error", "Tipo de ingrediente desconocido");
                
                return "ingredienteError";
            }

        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "ingredienteError";
        }
    }

    @PostMapping
    public String guardarFormulario(@Valid @ModelAttribute("ingredienteForm") IngredienteForm ingredienteForm, BindingResult result, Model model) {
    	if (result.hasErrors()) {
  	        model.addAttribute("ingredienteForm", ingredienteForm);
  	      if ("Producto".equalsIgnoreCase(ingredienteForm.getTipoIngrediente())) {
              return "ingredienteEditarProducto";
          } else {
              return "ingredienteEditarCondimento";
          }
  	    }
    	
    	try {
    		IngredienteDTO ingredienteDTO = new IngredienteDTO();
    		
    		ingredienteDTO.setId(ingredienteForm.getId());
    		ingredienteDTO.setActiva(ingredienteForm.isActiva());
    		ingredienteDTO.setNombre(ingredienteForm.getNombre());
    		ingredienteDTO.setCalorias(ingredienteForm.getCalorias());
    		ingredienteDTO.setTipoCondimento(ingredienteForm.getTipoIngrediente());
    		ingredienteDTO.setPrecioActual(ingredienteForm.getPrecioActual());
    		ingredienteDTO.setStockDisponible(ingredienteForm.getStockDisponible());
    		
            ingredienteService.guardar(ingredienteDTO);
            
            return "redirect:/ingredienteListar";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("ingredienteForm", ingredienteForm);

            // Volver a mostrar el formulario correcto según el tipo
            if ("Producto".equalsIgnoreCase(ingredienteForm.getTipoIngrediente())) {
                return "ingredienteEditarProducto";
            } else {
                return "ingredienteEditarCondimento";
            }
        }
    }
    
	//CHECADO X
	
	@GetMapping("/{id}/deshabilitar")
	public String deshabilitarFamilia(@PathVariable("id") Long id) {
		ingredienteService.inhabilitar(id);
	    return "redirect:/ingredienteListar";
	}

	//CHECADO X
	
	@GetMapping("/{id}/habilitar")
	public String habilitarFamilia(@PathVariable("id") Long id) {
		ingredienteService.habilitar(id);
	    return "redirect:/ingredienteListar";
	}
    
}
