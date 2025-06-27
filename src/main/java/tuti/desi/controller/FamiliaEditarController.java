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
import tuti.desi.DTO.FamiliaDTO;
import tuti.desi.servicios.FamiliaService;


@Controller
@RequestMapping("/familiaEditar")
public class FamiliaEditarController {

	@Autowired
    private FamiliaService familiaService;
	
	//Metodo GET para cargar el formulario de Editar Familia desde Inicio
	//Si mandamos un GET, se busca un objeto familiaDTO con el ID pasado, si es un familia, entonces se arma un familiaForm y se le pasan los datos del DTO y este form se carga al modelo, lo que carga datos, sino arroja errores
	@GetMapping("/{id}")
    public String cargarFormulario(@PathVariable("id") Long id, Model model) {
        try {
			FamiliaDTO familiaDTO = familiaService.buscarPorId(id);
			
			FamiliaForm familiaForm = new FamiliaForm();
			
			familiaForm.setNroFamilia(familiaDTO.getNroFamilia());
			familiaForm.setNombre(familiaDTO.getNombre());
			familiaForm.setFechaRegistro(familiaDTO.getFechaRegistro());
			familiaForm.setActiva(familiaDTO.isActiva());
			
	        model.addAttribute("familiaForm", familiaForm);
	        return "familiaEditar"; 
	        //Sino te devuelve un error lo mandamos a familiaError
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "familiaError"; 
        }
    }
	//Metodo POST para cargar el formulario de Editar Familia desde Inicio
	//Si mandas un POST el formulario crea un objeto Form con los valores de los campos,el objeto form arma un objeto familiaDTO y lo manda al Service.
	@PostMapping
    public String guardarFormulario(@Valid @ModelAttribute("familiaForm") FamiliaForm familiaForm, BindingResult result, Model model) {
		if (result.hasErrors()) {
  	        model.addAttribute("familiaForm", familiaForm);
  	        
  	        return "familiaEditar";
  	    }
		
		try {
        	FamiliaDTO familiaDTO = new FamiliaDTO();
        	
        	familiaDTO.setNroFamilia(familiaForm.getNroFamilia());
        	familiaDTO.setNombre(familiaForm.getNombre());
        	familiaDTO.setFechaRegistro(familiaForm.getFechaRegistro());
        	familiaDTO.setActiva(familiaForm.isActiva());
        	
            familiaService.guardar(familiaDTO);
            
            return "redirect:/familiaListar";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("familiaForm", familiaForm);
            
            return "familiaEditar";
        }
    }
	
	//Metodo GET para la funcion de deshabilitar familia del formulario Editar Familia
	//Endpoint para deshabilitar una familia desde familiarEditar
	@GetMapping("/{id}/deshabilitar")
	public String deshabilitarFamilia(@PathVariable("id") Long id) {
	    familiaService.inhabilitar(id);
	    return "redirect:/familiaListar";
	}

	//Metodo GET para la funcion de habilitar familia del formulario Editar Familia
	//Endpoint para habilitar una familia desde familiarEditar
	@GetMapping("/{id}/habilitar")
	public String habilitarFamilia(@PathVariable("id") Long id) {
	    familiaService.habilitar(id);
	    return "redirect:/familiaListar";
	}
	
}
