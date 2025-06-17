package tuti.desi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tuti.desi.DTO.FamiliaDTO;
import tuti.desi.DTO.PersonaDTO;
import tuti.desi.servicios.FamiliaService;
import tuti.desi.servicios.PersonaService;


@Controller
@RequestMapping("/personaEditar")
public class PersonaModificarController {

	@Autowired
    private PersonaService personaService;
	
	//Si solicitas un GET, carga un modelo de familiaDTO a partir del ID
	@GetMapping("/{id}")
    public String cargarFormulario(@PathVariable("id") Long id, Model model) {
        try {
			PersonaDTO personaDTO = personaService.buscarPorId(id);
	        model.addAttribute("personaDTO", personaDTO);
	        return "personaEditar"; 
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "personaError"; 
        }
    }
	
	//Cuando al formulario lo submiteas, entonces intenta guardar, sino catchea el error
	@PostMapping
    public String guardarFormulario(@ModelAttribute("personaDTO") PersonaDTO personaDTO, Model model) {
        try {
        	personaService.guardar(personaDTO);
            return "redirect:/index.html";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("personaDTO", personaDTO);
            return "personaEditar";
        }
    }
	
}
