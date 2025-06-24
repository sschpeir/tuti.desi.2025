package tuti.desi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tuti.desi.DTO.VoluntarioDTO;
import tuti.desi.DTO.PersonaDTO;

import tuti.desi.servicios.VoluntarioService;


@Controller
@RequestMapping("/voluntarioEditar")
public class VoluntarioEditarController {

	@Autowired
    private VoluntarioService voluntarioService;

    @GetMapping("/{id}")
    public String cargarFormulario(@PathVariable("id") Long id, Model model) {
        try {
            PersonaDTO personaDTO = voluntarioService.buscarPorId(id);

            if (!"VOLUNTARIO".equalsIgnoreCase(personaDTO.getTipoPersona())) {
                throw new IllegalArgumentException("La persona con ID " + id + " no es un voluntario.");
            }
            VoluntarioDTO voluntarioDTO = (VoluntarioDTO) personaDTO;
            
            VoluntarioForm voluntarioForm = new VoluntarioForm();
            
            voluntarioForm.setId(voluntarioDTO.getId());
            voluntarioForm.setActiva(voluntarioDTO.isActiva());
            voluntarioForm.setDni(voluntarioDTO.getDni());
            voluntarioForm.setNombre(voluntarioDTO.getNombre());
            voluntarioForm.setApellido(voluntarioDTO.getApellido());
            voluntarioForm.setFechaNacimiento(voluntarioDTO.getFechaNacimiento());
            voluntarioForm.setDomicilio(voluntarioDTO.getDomicilio());
            voluntarioForm.setOcupacion(voluntarioDTO.getOcupacion());
            voluntarioForm.setFechaRegistro(voluntarioDTO.getFechaRegistro());
            voluntarioForm.setNroSeguroSocial(voluntarioDTO.getNroSeguroSocial());

            model.addAttribute("voluntarioForm", voluntarioForm);

            return "voluntarioEditar";

        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "voluntarioError";
        }
    }
    
  	//Si mandas un POST en un formulario, entonces agarra el modelo del form, arma un objeto RecetaDTO y lo manda al Service.
  	@PostMapping
  	public String guardarFormularioVoluntario(@ModelAttribute("voluntarioDTO") VoluntarioForm voluntarioForm, Model model) {
  		
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
  	        //Si guarda, pasa al index.html
  	        return "redirect:/voluntarioListar";
  	    } catch (IllegalArgumentException e) {
  	        model.addAttribute("error", e.getMessage());
  	        model.addAttribute("voluntarioForm", voluntarioForm); 
  	        //Si no guarda, deja los datos cargados y devuelve error que se lo agarra con Thymeleaf
  	        return "voluntarioEditar"; 
  	    }
  	}
  	
  	@GetMapping("/{id}/deshabilitar")
	public String deshabilitarVoluntario(@PathVariable("id") Long id) {
  		voluntarioService.inhabilitar(id);
	    return "redirect:/voluntarioListar";
	}

	@GetMapping("/{id}/habilitar")
	public String habilitarVoluntario(@PathVariable("id") Long id) {
		voluntarioService.habilitar(id);
	    return "redirect:/voluntarioListar";
	}


  	
}
