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
import tuti.desi.DTO.VoluntarioDTO;

import tuti.desi.servicios.VoluntarioService;

import java.time.LocalDate;

@Controller
@RequestMapping("/voluntarioRegistrar")
public class VoluntarioRegistrarController {
    
    @Autowired
    private VoluntarioService voluntarioService;
    
    
    //Metodo GET del formulario de Registrar Voluntario que se ve en el inicio
    //Si solicitas un GET, crea un objeto VoluntarioForm en blanco y lo pasa al modelo.
    @GetMapping
  	public String cargarFormularioVoluntario(Model model) {
    	VoluntarioForm voluntarioForm = new VoluntarioForm();
    	
    	voluntarioForm.setFechaRegistro(LocalDate.now());
  	    model.addAttribute("voluntarioForm", voluntarioForm);
  	    
  	    return "voluntarioRegistrar";
  	}
    
    //Metodo POST del formulario de Registrar Voluntario que se ve en el inicio
  	//Si mandas un POST el formulario crea un objeto Form con los valores de los campos,el objeto form, arma un objeto VoluntarioDTO y lo manda al Service.
  	@PostMapping
  	public String guardarFormularioVoluntario(@Valid @ModelAttribute("voluntarioForm") VoluntarioForm voluntarioForm, BindingResult result, Model model) {
  		if (result.hasErrors()) {
  	        model.addAttribute("voluntarioForm", voluntarioForm);
  	        return "voluntarioRegistrar";
  	    }
  		try {
  			VoluntarioDTO voluntarioDTO = new VoluntarioDTO();
  			
  			voluntarioDTO.setId(voluntarioForm.getId());
  			voluntarioDTO.setActiva(voluntarioForm.isActiva());
  			voluntarioDTO.setDni(voluntarioForm.getDni());
  			voluntarioDTO.setNombre(voluntarioForm.getNombre());
  			voluntarioDTO.setApellido(voluntarioForm.getApellido());
  			voluntarioDTO.setFechaNacimiento(voluntarioForm.getFechaNacimiento());
  			voluntarioDTO.setDomicilio(voluntarioForm.getDomicilio());
  			voluntarioDTO.setOcupacion(voluntarioForm.getOcupacion());
  			voluntarioDTO.setFechaRegistro(voluntarioForm.getFechaRegistro());
  			voluntarioDTO.setNroSeguroSocial(voluntarioForm.getNroSeguroSocial());
  			voluntarioDTO.setTipoPersona(voluntarioForm.getTipoPersona());
  			
  			voluntarioService.guardarVoluntario(voluntarioDTO);
  	        //Si guarda, pasa a la lista de voluntarios
  	        return "redirect:/voluntarioListar";
  	    } catch (IllegalArgumentException e) {
  	        model.addAttribute("error", e.getMessage());
  	        model.addAttribute("voluntarioForm", voluntarioForm); 
  	        //Si no guarda, deja los datos cargados y devuelve error que se muestra con Thymeleaf
  	        return "voluntarioRegistrar"; 
  	    }
  	}
  	
}
