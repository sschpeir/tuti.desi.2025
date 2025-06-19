package tuti.desi.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tuti.desi.DTO.IngredienteDTO;

import tuti.desi.servicios.IngredienteService;

@Controller
@RequestMapping("/ingredienteRegistrar")
public class IngredienteRegistrarController {

	@Autowired
    private IngredienteService ingredienteService;
	
	//CHECADO X
	
	//Si solicitas un GET, carga un modelo con un FamiliaDTO en blanco
	@GetMapping
    public String cargarFormulario(Model model) {
		IngredienteDTO ingredienteDTO = new IngredienteDTO();
        model.addAttribute("ingredienteDTO", ingredienteDTO);
        return "ingredienteRegistrar";
    }
	
	//CHECADO X
	
	//Si mandas un POST en un formulario, entonces agarra el modelo del form, arma un objeto FamiliaDTO y lo manda al Service.
	@PostMapping
	public String guardarFormulario(@ModelAttribute("ingredienteDTO") IngredienteDTO ingredienteDTO, Model model){
		//Le metemos un try-catch por los errores, ejemplo: si hay otra con el mismo nombre
		try {
			ingredienteService.guardar(ingredienteDTO);
	        //Si guarda, pasa al listado de familias dadas de alta
	        return "redirect:/ingredienteListar";
	    } catch (IllegalArgumentException e) {
	    	//Si no guarda, deja los datos cargados y devuelve error que se lo agarra con Thymeleaf
	    	model.addAttribute("error", e.getMessage());
	        model.addAttribute("ingredienteDTO", ingredienteDTO); 
	        return "ingredienteRegistrar"; 
	    }
    }
	
}
