package tuti.desi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tuti.desi.DTO.PersonaDTO;

import tuti.desi.servicios.PersonaService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/personaRegistrar")
public class PersonaRegistrarController {

    @Autowired
    private PersonaService personaService;

    //Si solicitas un GET, carga un modelo de recetaDTO en blanco - No te precarga datos, pero es bueno para ediciones.
  	@GetMapping
      public String cargarFormulario(Model model) {
  		PersonaDTO personaDTO = new PersonaDTO();
          model.addAttribute("personaDTO", personaDTO);
          return "personaRegistrar";
      }
    
  	//Si mandas un POST en un formulario, entonces agarra el modelo del form, arma un objeto RecetaDTO y lo manda al Service.
  	@PostMapping
  	public String guardarFormulario(@ModelAttribute("personaDTO") PersonaDTO personaDTO, Model model){
  		//Le metemos un try-catch por los errores, ejemplo: si hay otra con el mismo nombre
  		try {
  			personaService.guardar(personaDTO);
  	        //Si guarda, pasa al index.html
  	        return "redirect:/inicio";
  	    } catch (IllegalArgumentException e) {
  	        model.addAttribute("error", e.getMessage());
  	        model.addAttribute("personaDTO", personaDTO); 
  	        //Si no guarda, deja los datos cargados y devuelve error que se lo agarra con Thymeleaf
  	        return "personaRegistrar"; 
  	    }
     }

}
