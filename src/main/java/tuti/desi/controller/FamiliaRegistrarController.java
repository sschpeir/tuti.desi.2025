
package tuti.desi.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import tuti.desi.DTO.FamiliaDTO;
import tuti.desi.servicios.FamiliaService;

@Controller
@RequestMapping("/familiaRegistrar")
public class FamiliaRegistrarController {

	@Autowired
    private FamiliaService familiaService;
	
	//Metodo GET para el formulario de Registrar Familia desde el Inicio
	//Si solicitas un GET, carga un modelo con un FamiliaForm en blanco y lo pasa al modelo
	@GetMapping
    public String cargarFormulario(Model model) {
		FamiliaForm familiaForm = new FamiliaForm();
		familiaForm.setFechaRegistro(LocalDate.now());
        model.addAttribute("familiaForm", familiaForm);
        return "familiaRegistrar";
    }
	
	//Metodo POST para el formulario de Registrar Familia desde el Inicio
	//Si mandas un POST en el formulario, toma los datos el modelo del form, arma un objeto FamiliaDTO y lo manda al Service.
	@PostMapping
	public String guardarFormulario(@Valid @ModelAttribute("familiaForm") FamiliaForm familiaForm, BindingResult result, Model model){
	//Le metemos un try-catch por los errores, ejemplo: si hay otra con el mismo nombre
	if (result.hasErrors()) {
  	        model.addAttribute("familiaForm", familiaForm);
  	        return "familiaRegistrar";
  	    }
	try {
		FamiliaDTO familiaDTO = new FamiliaDTO();
		
		familiaDTO.setNroFamilia(familiaForm.getNroFamilia());
		familiaDTO.setNombre(familiaForm.getNombre());
		familiaDTO.setFechaRegistro(familiaForm.getFechaRegistro());
		familiaDTO.setActiva(familiaForm.isActiva());
		
		familiaService.guardar(familiaDTO);
	    //Si guarda, pasa al listado de familias dadas de alta
	    return "redirect:/familiaListar";
	    } catch (IllegalArgumentException e) {
	    	//Si no guarda, deja los datos cargados y devuelve error que se lo agarra con Thymeleaf
	    	model.addAttribute("error", e.getMessage());
	        model.addAttribute("familiaForm", familiaForm); 
	        return "familiaRegistrar"; 
	    }
    }
	
}
