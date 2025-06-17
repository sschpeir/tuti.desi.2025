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
import tuti.desi.servicios.FamiliaService;


@Controller
@RequestMapping("/familiaEditar")
public class FamiliaEditarController {

	@Autowired
    private FamiliaService familiaService;
	
	//CHECADO X
	
	//Si solicitas un GET, carga un modelo de familiaDTO a partir del ID
	@GetMapping("/{id}")
    public String cargarFormulario(@PathVariable("id") Long id, Model model) {
        try {
			FamiliaDTO familiaDTO = familiaService.buscarPorId(id);
	        model.addAttribute("familiaDTO", familiaDTO);
	        return "familiaEditar"; 
	        //Sino te devuelve un error lo mandamos a familiaError
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "familiaError"; 
        }
    }
	
	//CHECADO X
	
	//Cuando al formulario lo submiteas, entonces intenta guardar, sino catchea el error
	@PostMapping
    public String guardarFormulario(@ModelAttribute("familiaDTO") FamiliaDTO familiaDTO, Model model) {
        try {
            familiaService.guardar(familiaDTO);
            return "redirect:/familiaListar";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("familiaDTO", familiaDTO);
            return "familiaEditar";
        }
    }
	
	//CHECADO X
	
	@GetMapping("/{id}/deshabilitar")
	public String deshabilitarFamilia(@PathVariable("id") Long id) {
	    familiaService.inhabilitar(id);
	    return "redirect:/familiaListar";
	}

	//CHECADO X
	
	@GetMapping("/{id}/habilitar")
	public String habilitarFamilia(@PathVariable("id") Long id) {
	    familiaService.habilitar(id);
	    return "redirect:/familiaListar";
	}
	
}
