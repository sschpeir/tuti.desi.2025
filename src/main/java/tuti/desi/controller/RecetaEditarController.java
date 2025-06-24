package tuti.desi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tuti.desi.DTO.RecetaDTO;

import tuti.desi.servicios.RecetaService;

@Controller
@RequestMapping("/recetaEditar")
public class RecetaEditarController {

    @Autowired
    private RecetaService recetaService;

    //Si solicitas un GET, carga un modelo de recetaDTO a partir del ID
  	@GetMapping("/{id}")
      public String cargarFormulario(@PathVariable("id") Long id, Model model) {
          try {
  			RecetaDTO recetaDTO = recetaService.buscarPorId(id);
  	        model.addAttribute("recetaDTO", recetaDTO);
  	        return "recetaEditar"; 
  	        //Sino te devuelve un error lo mandamos a familiaError
          } catch (IllegalArgumentException e) {
              model.addAttribute("error", e.getMessage());
              return "recetaListar"; 
          }
      }
  	
  	//Metodo POST de recetaEditar, agarra el template de recetaDTO y lo manda a guardar
  	@PostMapping
    public String guardarFormulario(@ModelAttribute("recetaDTO") RecetaDTO recetaDTO, Model model) {
        try {
        	recetaService.guardarEdicion(recetaDTO);
            return "redirect:/recetaListar";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("recetaDTO", recetaDTO);
            return "recetaEditar";
        }
    }	
  	
    //Endpoint para deshabilitar una receta desde recetaListar
	@GetMapping("/{id}/deshabilitar")
	public String deshabilitarReceta(@PathVariable("id") Long id) {
		recetaService.inhabilitar(id);
	    return "redirect:/recetaListar";
	}


	//Endpoint para habilitar una receta desde recetaListar
	@GetMapping("/{id}/habilitar")
	public String habilitarReceta(@PathVariable("id") Long id) {
		recetaService.habilitar(id);
	    return "redirect:/recetaListar";
	}

}
