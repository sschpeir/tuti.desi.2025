package tuti.desi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tuti.desi.DTO.IngredienteDTO;
import tuti.desi.DTO.RecetaDTO;
import tuti.desi.servicios.IngredienteService;
import tuti.desi.servicios.RecetaService;

@Controller
@RequestMapping("/recetaListar")
public class RecetaListarController {

    @Autowired
    private RecetaService recetaService;
    
    @Autowired
    private IngredienteService ingredienteService;

    @GetMapping
    public String mostrarFormulario(Model model) {
        RecetaDTO recetaDTO = new RecetaDTO();
        model.addAttribute("recetaDTO", recetaDTO);
        return "recetaListar"; // Asegurate de que exista recetaForm.html en templates/
    }

    @PostMapping
    public String procesarFormulario(@ModelAttribute("recetaDTO") RecetaDTO recetaDTO,Model model)
    {
	try {
		recetaService.guardarReceta(recetaDTO);
        //Si guarda, pasa al listado de recetas dadas de alta
        return "redirect:/recetaListar";
    } catch (IllegalArgumentException e) {
    	//Si no guarda, deja los datos cargados y devuelve error que se lo agarra con Thymeleaf
    	model.addAttribute("error", e.getMessage());
        model.addAttribute("recetaDTO", recetaDTO); 
        return "recetaListar"; 
    }
    }
    
	@GetMapping("/{id}/Items")
	public String listarMiembros(@PathVariable Long id, Model model) {
		RecetaDTO recetaDTO = recetaService.buscarPorId(id);
		List<IngredienteDTO> ingredientes = ingredienteService.listarIngredientesActivas();
	    model.addAttribute("recetaDTO", recetaDTO);
	    model.addAttribute("ingredientes", ingredientes);
	    return "recetaListarItems";
	}
	
	@GetMapping("/{id}/Items/activos")
	public String listarMiembrosActivos(@PathVariable Long id, Model model) {
		RecetaDTO recetaDTO = recetaService.buscarPorId(id);
		List<IngredienteDTO> ingredientesActivos = ingredienteService.listarIngredientesActivas();
		IngredienteDTO ingredienteDTO = ingredienteService.buscarPorId(id);
	    model.addAttribute("recetaDTO", recetaDTO);
	    model.addAttribute("ingredientes", ingredientesActivos);
	    return "recetaListarItems";
	}
}
