package tuti.desi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import tuti.desi.DTO.VoluntarioDTO;
import tuti.desi.DTO.PersonaDTO;

import tuti.desi.servicios.VoluntarioService;


@Controller
@RequestMapping("/voluntarioEditar")
public class VoluntarioEditarController {

	@Autowired
    private VoluntarioService voluntarioService;

	//Metodo GET para cargar el formulario de Editar Voluntario
	//Si mandamos un GET, se busca un objeto VoluntarioDTO con el ID pasado, si es un voluntario, entonces se arma un voluntarioForm y se le pasan los datos del DTO y este form se carga al modelo, lo que carga datos, sino arroja errores
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
    
    //Metodo POST para el formulario Editar Voluntario
    //Si mandas un POST el formulario crea un objeto Form con los valores de los campos,el objeto form arma un objeto VoluntarioDTO y lo manda al Service.
  	@PostMapping
  	public String guardarFormularioVoluntario(@Valid @ModelAttribute("voluntarioDTO") VoluntarioForm voluntarioForm, BindingResult result, Model model) {
  		if (result.hasErrors()) {
  	        model.addAttribute("voluntarioForm", voluntarioForm);
  	        
  	        return "voluntarioEditar";
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
  	        //Si guarda, pasa al inicio
  	        return "redirect:/voluntarioListar";
  	    } catch (IllegalArgumentException e) {
  	        model.addAttribute("error", e.getMessage());
  	        model.addAttribute("voluntarioForm", voluntarioForm); 
  	        //Si no guarda, deja los datos cargados y devuelve error que lo muestra con Thymeleaf
  	        return "voluntarioEditar"; 
  	    }
  	}
  	
  	//Metodo GET para el metodo deshabilitar que se accede desde el Formulario de Listar Voluntarios.
    //Si mando un GET, cambia el estado del campo ACTIVA del asistido
  	@GetMapping("/{id}/deshabilitar")
	public String deshabilitarVoluntario(@PathVariable("id") Long id) {
  		voluntarioService.inhabilitar(id);
	    return "redirect:/voluntarioListar";
	}

  	//Metodo GET para el metodo habilitar que se accede desde el Formulario de Listar Voluntarios.
  	//Si mando un GET, cambia el estado del campo ACTIVA del asistido
	@GetMapping("/{id}/habilitar")
	public String habilitarVoluntario(@PathVariable("id") Long id) {
		voluntarioService.habilitar(id);
	    return "redirect:/voluntarioListar";
	}


  	
}
