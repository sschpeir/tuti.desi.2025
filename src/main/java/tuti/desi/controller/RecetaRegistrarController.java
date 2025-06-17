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
@RequestMapping("/registrarReceta")
public class RecetaRegistrarController {

	@Autowired
    private RecetaService recetaService;
	
	//Si solicitas un GET, carga un modelo de recetaDTO en blanco - No te precarga datos, pero es bueno para ediciones.
	@GetMapping
    public String cargarFormulario(Model model) {
		RecetaDTO recetaDTO = new RecetaDTO();
        model.addAttribute("recetaDTO", recetaDTO);
        return "registrarReceta";
    }
	
	//Si mandas un POST en un formulario, entonces agarra el modelo del form, arma un objeto RecetaDTO y lo manda al Service.
	@PostMapping
	public String guardarFormulario(@ModelAttribute("recetaDTO") RecetaDTO recetaDTO, Model model){
		//Le metemos un try-catch por los errores, ejemplo: si hay otra con el mismo nombre
		try {
	        recetaService.guardar(recetaDTO);
	        //Si guarda, pasa al index.html
	        return "redirect:/index.html";
	    } catch (IllegalArgumentException e) {
	        model.addAttribute("error", e.getMessage());
	        model.addAttribute("recetaDTO", recetaDTO); 
	        //Si no guarda, deja los datos cargados y devuelve error que se lo agarra con Thymeleaf
	        return "registrarReceta"; 
	    }
    }
	
	
	
}
