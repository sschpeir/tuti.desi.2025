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

            model.addAttribute("ingredienteDTO", ingredienteDTO);

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
    public String guardarFormulario(@ModelAttribute("ingredienteDTO") IngredienteDTO ingredienteDTO, Model model) {
        try {
            ingredienteService.guardar(ingredienteDTO);
            return "redirect:/ingredienteListar";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("ingredienteDTO", ingredienteDTO);

            // Volver a mostrar el formulario correcto según el tipo
            if ("Producto".equalsIgnoreCase(ingredienteDTO.getTipoCondimento())) {
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
