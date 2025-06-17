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
import tuti.desi.DTO.FamiliaDTO;
import tuti.desi.DTO.PersonaDTO;
import tuti.desi.servicios.AsistidoService;
import tuti.desi.servicios.FamiliaService;
import tuti.desi.servicios.PersonaService;
import tuti.desi.servicios.VoluntarioService;

import java.util.List;

@Controller
@RequestMapping("/voluntarioEditar")
public class VoluntarioEditarController {

	@Autowired
    private VoluntarioService voluntarioService;
    
    @Autowired
    private FamiliaService familiaService;

    @GetMapping("/{id}")
    public String cargarFormulario(@PathVariable("id") Long id, Model model) {
        try {
            PersonaDTO personaDTO = voluntarioService.buscarPorId(id);

            if (!"VOLUNTARIO".equalsIgnoreCase(personaDTO.getTipoPersona())) {
                throw new IllegalArgumentException("La persona con ID " + id + " no es un asistido.");
            }

            VoluntarioDTO voluntarioDTO = (VoluntarioDTO) personaDTO;

            List<FamiliaDTO> familias = familiaService.listarTodas();
            model.addAttribute("voluntarioDTO", voluntarioDTO);
            model.addAttribute("familias", familias);
            model.addAttribute("bloquear", familias.isEmpty());

            return "voluntarioEditar";

        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "errorFamilia";
        }
    }
    
  	//Si mandas un POST en un formulario, entonces agarra el modelo del form, arma un objeto RecetaDTO y lo manda al Service.
  	@PostMapping
  	public String guardarFormularioAsistido(@ModelAttribute("voluntarioDTO") VoluntarioDTO voluntarioDTO, Model model) {
  		voluntarioDTO.setTipoPersona("Voluntario");
  		try {
  			voluntarioService.guardarVoluntario(voluntarioDTO);
  	        //Si guarda, pasa al index.html
  	        return "redirect:/voluntarioListar";
  	    } catch (IllegalArgumentException e) {
  	        model.addAttribute("error", e.getMessage());
  	        model.addAttribute("voluntarioDTO", voluntarioDTO); 
  	        //Si no guarda, deja los datos cargados y devuelve error que se lo agarra con Thymeleaf
  	        return "voluntarioEditar"; 
  	    }
  	}
  	
  	@GetMapping("/{id}/deshabilitar")
	public String deshabilitarAsistido(@PathVariable("id") Long id) {
  		voluntarioService.inhabilitar(id);
	    return "redirect:/voluntarioListar";
	}

	@GetMapping("/{id}/habilitar")
	public String habilitarAsistido(@PathVariable("id") Long id) {
		voluntarioService.habilitar(id);
	    return "redirect:/voluntarioListar";
	}


  	
}
