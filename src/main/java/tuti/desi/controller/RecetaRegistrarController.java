package tuti.desi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import tuti.desi.DTO.RecetaDTO;

import tuti.desi.servicios.RecetaService;

@Controller
@RequestMapping("/recetaRegistrar")
public class RecetaRegistrarController {

    @Autowired
    private RecetaService recetaService;
  
    @GetMapping
    public String mostrarFormulario(Model model) {
    	RecetaForm recetaForm = new RecetaForm();
        model.addAttribute("recetaForm", recetaForm);
        return "recetaRegistrar";
    }

    @PostMapping
    public String procesarFormulario(@Valid @ModelAttribute("recetaForm") RecetaForm recetaForm,BindingResult result, Model model){
    
    if (result.hasErrors()) {
  	        model.addAttribute("recetaForm", recetaForm);
  	        return "asistidoRegistrar";
  	    }
	try {
		RecetaDTO recetaDTO = new RecetaDTO();
		
		recetaDTO.setId(recetaForm.getId());
		recetaDTO.setNombre(recetaForm.getNombre());
		recetaDTO.setDescripcion(recetaForm.getDescripcion());
		recetaDTO.setActiva(recetaForm.isActiva());
		
		recetaService.guardarReceta(recetaDTO);
        //Si guarda, pasa al listado de recetas dadas de alta
        return "redirect:/recetaListar";
	    } catch (IllegalArgumentException e) {
	    	//Si no guarda, deja los datos cargados y devuelve error que se lo agarra con Thymeleaf
	    	model.addAttribute("error", e.getMessage());
	        model.addAttribute("recetaForm", recetaForm); 
	        return "recetaRegistrar"; 
	    }
    }
    
}
