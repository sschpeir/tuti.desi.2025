package tuti.desi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tuti.desi.DTO.AsistidoDTO;
import tuti.desi.DTO.FamiliaDTO;
import tuti.desi.DTO.VoluntarioDTO;
import tuti.desi.servicios.AsistidoService;
import tuti.desi.servicios.FamiliaService;
import tuti.desi.servicios.PersonaService;
import tuti.desi.servicios.VoluntarioService;

import java.util.List;

@Controller
@RequestMapping("/voluntarioRegistrar")
public class VoluntarioRegistrarController {
    
    @Autowired
    private VoluntarioService voluntarioService;
    
    @Autowired
    private FamiliaService familiaService;

    
  	

    
    //Si solicitas un GET, carga un modelo de PersonaDTO en blanco - Y te Carga un listado de todas las familias para asignar
    @GetMapping
  	public String cargarFormularioVoluntario(Model model) {
    	VoluntarioDTO voluntarioDTO = new VoluntarioDTO();
  	    List<FamiliaDTO> familias = familiaService.listarTodas();

  	    if (familias.isEmpty()) {
	  	    model.addAttribute("error", "Debe registrar una familia primero.");
	  	    model.addAttribute("bloquear", true);
	  	}

  	    model.addAttribute("voluntarioDTO", voluntarioDTO);
  	    model.addAttribute("familias", familias);
  	    return "voluntarioRegistrar";
  	}
    

    
  	//Si mandas un POST en un formulario, entonces agarra el modelo del form, arma un objeto PersonaDTO y lo manda al Service.
  	@PostMapping
  	public String guardarFormularioVoluntario(@ModelAttribute("voluntarioDTO") VoluntarioDTO voluntarioDTO, Model model) {
  		voluntarioDTO.setTipoPersona("Voluntario");
  		try {
  			voluntarioService.guardarVoluntario(voluntarioDTO);
  	        //Si guarda, pasa al index.html
  	        return "redirect:/inicio";
  	    } catch (IllegalArgumentException e) {
  	        model.addAttribute("error", e.getMessage());
  	        model.addAttribute("voluntarioDTO", voluntarioDTO); 
  	        List<FamiliaDTO> familias = familiaService.listarTodas();
  	        model.addAttribute("familias", familias);
  	        //Si no guarda, deja los datos cargados y devuelve error que se lo agarra con Thymeleaf
  	        return "voluntarioRegistrar"; 
  	    }
  	}
  	
}
