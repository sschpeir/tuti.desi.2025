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
  			
  			RecetaForm recetaForm = new RecetaForm();
  			
  			recetaForm.setId(recetaDTO.getId());
  			recetaForm.setNombre(recetaDTO.getNombre());
  			recetaForm.setDescripcion(recetaDTO.getDescripcion());
  			recetaForm.setActiva(recetaDTO.isActiva());
  			
  	        model.addAttribute("recetaForm", recetaForm);
  	        return "recetaEditar"; 
  	        //Sino te devuelve un error lo mandamos a familiaError
          } catch (IllegalArgumentException e) {
              model.addAttribute("error", e.getMessage());
              return "recetaListar"; 
          }
      }
  	
  	//Metodo POST de recetaEditar, agarra el template de recetaDTO y lo manda a guardar
  	@PostMapping
    public String guardarFormulario(@Valid @ModelAttribute("recetaForm") RecetaForm recetaForm,BindingResult result, Model model) {
        try {
        	RecetaDTO recetaDTO = new RecetaDTO();
        	
        	recetaDTO.setId(recetaForm.getId());
        	recetaDTO.setNombre(recetaForm.getNombre());
        	recetaDTO.setDescripcion(recetaForm.getDescripcion());
        	recetaDTO.setActiva(recetaForm.isActiva());
        	
        	recetaService.guardarEdicion(recetaDTO);
        	
            return "redirect:/recetaListar";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("recetaForm", recetaForm);
            
            return "recetaEditar";
        }
    }
  	
    //Si solicitas un GET, carga un modelo de recetaDTO a partir del ID
  	@GetMapping("/solicitado/{id}")
      public String cargarFormularioSolicitado(@PathVariable("id") Long id, Model model) {
          try {
  			RecetaDTO recetaDTO = recetaService.buscarPorId(id);
  			
  			RecetaForm recetaForm = new RecetaForm();
  			
  			recetaForm.setId(recetaDTO.getId());
  			recetaForm.setNombre(recetaDTO.getNombre());
  			recetaForm.setDescripcion(recetaDTO.getDescripcion());
  			recetaForm.setActiva(recetaDTO.isActiva());
  			
  	        model.addAttribute("recetaForm", recetaForm);
  	        return "recetaEditarSolicitado"; 
  	        //Sino te devuelve un error lo mandamos a familiaError
          } catch (IllegalArgumentException e) {
              model.addAttribute("error", e.getMessage());
              return "recetaListarActivasConItemsYCalorias"; 
          }
      }
  	
  	//Metodo POST de recetaEditar, agarra el template de recetaDTO y lo manda a guardar
  	@PostMapping("/solicitado")
    public String guardarFormularioSolicitado(@Valid @ModelAttribute("recetaForm") RecetaForm recetaForm,BindingResult result, Model model) {
        try {
        	RecetaDTO recetaDTO = new RecetaDTO();
        	
        	recetaDTO.setId(recetaForm.getId());
        	recetaDTO.setNombre(recetaForm.getNombre());
        	recetaDTO.setDescripcion(recetaForm.getDescripcion());
        	recetaDTO.setActiva(recetaForm.isActiva());
        	
        	recetaService.guardarEdicion(recetaDTO);
        	
            return "redirect:/recetaListar/solicitado";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("recetaForm", recetaForm);
            
            return "recetaListarActivasConItemsYCalorias";
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
	
    //Endpoint para deshabilitar una receta desde recetaListar
	@GetMapping("/solicitado/{id}/deshabilitar")
	public String deshabilitarRecetaSolicitado(@PathVariable("id") Long id) {
		recetaService.inhabilitar(id);
	    return "redirect:/recetaListar/solicitado";
	}


	//Endpoint para habilitar una receta desde recetaListar
	@GetMapping("/solicitado/{id}/habilitar")
	public String habilitarRecetaSolicitado(@PathVariable("id") Long id) {
		recetaService.habilitar(id);
	    return "redirect:/recetaListar/solicitado";
	}

}
