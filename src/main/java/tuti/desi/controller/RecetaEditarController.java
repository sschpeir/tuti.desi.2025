package tuti.desi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tuti.desi.DTO.RecetaDTO;
import tuti.desi.servicios.RecetaService;

@Controller
@RequestMapping("/editarReceta.html")
public class RecetaEditarController {

	@Autowired
    private RecetaService recetaService;
	
	//Si solicitas un GET, carga un modelo de recetaDTO en blanco - No te precarga datos, pero es bueno para ediciones.
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String cargarFormulario(@PathVariable Long id, Model model) {
	    RecetaDTO recetaDTO = recetaService.buscarPorId(id);
	    model.addAttribute("recetaDTO", recetaDTO);
	    return "editarReceta";
	}

	
	//Si mandas un POST en un formulario, entonces agarra el modelo del form, arma un objeto RecetaDTO y lo manda al Service.
	@RequestMapping(method = RequestMethod.POST)
	public String guardarFormulario(@ModelAttribute("recetaDTO") RecetaDTO recetaDTO, Model model) {
	    try {
	        recetaService.guardar(recetaDTO);
	        return "redirect:/listarReceta.html";
	    } catch (IllegalArgumentException e) {
	        model.addAttribute("error", e.getMessage());
	        model.addAttribute("recetaDTO", recetaDTO);
	        return "editarReceta";
	    }
	}
	
	


	
	
	
}
