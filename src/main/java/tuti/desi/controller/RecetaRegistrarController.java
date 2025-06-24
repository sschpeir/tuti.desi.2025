package tuti.desi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tuti.desi.DTO.RecetaDTO;

import tuti.desi.servicios.RecetaService;

@Controller
@RequestMapping("/recetaRegistrar")
public class RecetaRegistrarController {

    @Autowired
    private RecetaService recetaService;
  
    @GetMapping
    public String mostrarFormulario(Model model) {
        RecetaDTO recetaDTO = new RecetaDTO();
        model.addAttribute("recetaDTO", recetaDTO);
        return "recetaRegistrar";
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
        return "recetaRegistrar"; 
    }
    }
    
}
