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
		IngredienteForm ingredienteForm = new IngredienteForm();
        model.addAttribute("ingredienteForm", ingredienteForm);
        return "ingredienteRegistrar";
    }
	
	//CHECADO X
	
	//Si mandas un POST en un formulario, entonces agarra el modelo del form, arma un objeto FamiliaDTO y lo manda al Service.
	@PostMapping
	public String guardarFormulario(@Valid @ModelAttribute("ingredienteForm") IngredienteForm ingredienteForm, BindingResult result, Model model){
		//Le metemos un try-catch por los errores, ejemplo: si hay otra con el mismo nombre
		
		if (result.hasErrors()) {
  	        model.addAttribute("ingredienteForm", ingredienteForm);
  	        return "ingredienteRegistrar";
  	    }
		try {
			IngredienteDTO ingredienteDTO = new IngredienteDTO();
			
			ingredienteDTO.setId(ingredienteForm.getId());
			ingredienteDTO.setActiva(ingredienteForm.isActiva());
			ingredienteDTO.setNombre(ingredienteForm.getNombre());
			ingredienteDTO.setCalorias(ingredienteForm.getCalorias());
			ingredienteDTO.setPrecioActual(ingredienteForm.getPrecioActual());
			ingredienteDTO.setStockDisponible(ingredienteForm.getStockDisponible());
			ingredienteDTO.setTipoCondimento(ingredienteForm.getTipoIngrediente());
  			
			ingredienteService.guardar(ingredienteDTO);
  	        //Si guarda, pasa al ingredienteListar
  	        return "redirect:/ingredienteListar";
  	    } catch (IllegalArgumentException e) {
  	        model.addAttribute("error", e.getMessage());
  	        model.addAttribute("ingredienteForm", ingredienteForm); 
  	        //Si no guarda, deja los datos cargados y devuelve error que se lo agarra con Thymeleaf
  	        return "ingredienteRegistrar"; 
  	    }

	}	
}
