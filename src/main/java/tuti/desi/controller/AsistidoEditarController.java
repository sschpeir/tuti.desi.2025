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
import tuti.desi.DTO.PersonaDTO;
import tuti.desi.servicios.AsistidoService;
import tuti.desi.servicios.FamiliaService;
import tuti.desi.servicios.PersonaService;

import java.util.List;

@Controller
@RequestMapping("/asistidoEditar")
public class AsistidoEditarController {

	@Autowired
    private AsistidoService asistidoService;
    
    @Autowired
    private FamiliaService familiaService;

    @GetMapping("/{id}")
    public String cargarFormulario(@PathVariable("id") Long id, Model model) {
        try {
            PersonaDTO personaDTO = asistidoService.buscarPorId(id);

            if (!"ASISTIDO".equalsIgnoreCase(personaDTO.getTipoPersona())) {
                throw new IllegalArgumentException("La persona con ID " + id + " no es un asistido.");
            }

            AsistidoDTO asistidoDTO = (AsistidoDTO) personaDTO;

            List<FamiliaDTO> familias = familiaService.listarTodas();
            model.addAttribute("asistidoDTO", asistidoDTO);
            model.addAttribute("familias", familias);
            model.addAttribute("bloquear", familias.isEmpty());

            return "asistidoEditar";

        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "errorFamilia";
        }
    }
    
  	//Si mandas un POST en un formulario, entonces agarra el modelo del form, arma un objeto RecetaDTO y lo manda al Service.
  	@PostMapping
  	public String guardarFormularioAsistido(@ModelAttribute("asistidoDTO") AsistidoDTO asistidoDTO, Model model) {
		asistidoDTO.setTipoPersona("Asistido");
  		try {
  			asistidoService.guardarAsistido(asistidoDTO);
  	        //Si guarda, pasa al index.html
  	        return "redirect:/asistidoListar";
  	    } catch (IllegalArgumentException e) {
  	        model.addAttribute("error", e.getMessage());
  	        model.addAttribute("asistidoDTO", asistidoDTO); 
  	        //Si no guarda, deja los datos cargados y devuelve error que se lo agarra con Thymeleaf
  	        return "asistidoEditar"; 
  	    }
  	}
  	
  	@GetMapping("/{id}/deshabilitar")
	public String deshabilitarAsistido(@PathVariable("id") Long id) {
	    asistidoService.inhabilitar(id);
	    return "redirect:/asistidoListar";
	}

	@GetMapping("/{id}/habilitar")
	public String habilitarAsistido(@PathVariable("id") Long id) {
		asistidoService.habilitar(id);
	    return "redirect:/asistidoListar";
	}
	
	@GetMapping("/{id}/familia/deshabilitar")
	public String deshabilitarAsistidoFamilia(@PathVariable("id") Long id) {
		asistidoService.inhabilitar(id);    
	    PersonaDTO personaDTO = asistidoService.buscarPorId(id);
	    if (personaDTO instanceof AsistidoDTO asistidoDTO && asistidoDTO.getFamiliaId() != null) {
	        Long familiaId = asistidoDTO.getFamiliaId();
	        return "redirect:/familiaListar/" + familiaId + "/miembros";
	    }
	    // Si no es un Asistido o no tiene familia asociada
	    return "redirect:/familiaListar";
	}

	@GetMapping("/{id}/familia/habilitar")
	public String habilitarAsistidoFamilia(@PathVariable("id") Long id) {
	    asistidoService.habilitar(id);
	    PersonaDTO personaDTO = asistidoService.buscarPorId(id);
	    if (personaDTO instanceof AsistidoDTO asistidoDTO && asistidoDTO.getFamiliaId() != null) {
	        Long familiaId = asistidoDTO.getFamiliaId();
	        return "redirect:/familiaListar/" + familiaId + "/miembros";
	    }
	    // Si no es un Asistido o no tiene familia asociada
	    return "redirect:/familiaListar";
	}


  	
}
