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
import tuti.desi.DTO.AsistidoDTO;
import tuti.desi.DTO.FamiliaDTO;

import tuti.desi.servicios.AsistidoService;
import tuti.desi.servicios.FamiliaService;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/asistidoRegistrar")
public class AsistidoRegistrarController {
    
    @Autowired
    private AsistidoService asistidoService;
    
    @Autowired
    private FamiliaService familiaService; 
  	
//CHECADO 24-06-2025
    
    //Si solicitas un GET, carga un modelo de PersonaDTO en blanco - Y te Carga un listado de todas las familias para asignar
    @GetMapping
  	public String cargarFormularioAsistido(Model model) {
  	    AsistidoForm asistidoForm = new AsistidoForm();
  	    asistidoForm.setFechaRegistro(LocalDate.now());
  	    List<FamiliaDTO> familias = familiaService.listarTodas();
  	    model.addAttribute("asistidoForm", asistidoForm);
  	    model.addAttribute("familias", familias);
  	    return "asistidoRegistrar";
  	}
    
//CHECADO 24-06-2025 - FALTA COMPROBACIONES
    
  	//Si mandas un POST en un formulario, entonces agarra el modelo del form, arma un objeto PersonaDTO y lo manda al Service.
  	@PostMapping
  	public String guardarFormularioAsistido(@Valid @ModelAttribute("asistidoForm") AsistidoForm asistidoForm, BindingResult result, Model model) {
  		if (result.hasErrors()) {
  	        model.addAttribute("asistidoForm", asistidoForm);
  	        return "asistidoRegistrar";
  	    	}
  		try {
  			AsistidoDTO asistidoDTO = new AsistidoDTO();
  			
  			asistidoDTO.setId(asistidoForm.getId());
  			asistidoDTO.setActiva(asistidoForm.isActiva());
  			asistidoDTO.setDni(asistidoForm.getDni());
  			asistidoDTO.setNombre(asistidoForm.getNombre());
  			asistidoDTO.setApellido(asistidoForm.getApellido());
  			asistidoDTO.setFechaNacimiento(asistidoForm.getFechaNacimiento());
  			asistidoDTO.setDomicilio(asistidoForm.getDomicilio());
  			asistidoDTO.setOcupacion(asistidoForm.getOcupacion());
  			asistidoDTO.setFechaRegistro(asistidoForm.getFechaRegistro());
  			asistidoDTO.setTipoPersona(asistidoForm.getTipoPersona());
  			asistidoDTO.setFamiliaId(asistidoForm.getFamiliaId());
  			
  			asistidoService.guardarAsistido(asistidoDTO);
  	        //Si guarda, pasa al index.html
  	        return "redirect:/inicio";
	  	    } catch (IllegalArgumentException e) {
	  	        model.addAttribute("error", e.getMessage());
	  	        model.addAttribute("asistidoForm", asistidoForm); 
	  	        List<FamiliaDTO> familias = familiaService.listarTodas();
	  	        model.addAttribute("familias", familias);
	  	        //Si no guarda, deja los datos cargados y devuelve error que se lo agarra con Thymeleaf
	  	        return "asistidoRegistrar"; 
	  	    }
  	}	

//CHECADO 24-06-2025 	
  	//
  	@GetMapping("/familia/{id}")
  	public String cargarFormularioAsistidoFamilia(@PathVariable("id") Long id, Model model) {
  	    try {
  	        FamiliaDTO familiaDTO = familiaService.buscarPorId(id); // lanza excepción si no existe

  	        AsistidoForm asistidoForm = new AsistidoForm();
  	        asistidoForm.setFamiliaId(id);
  	        asistidoForm.setFechaRegistro(LocalDate.now());
  	        model.addAttribute("asistidoForm", asistidoForm);
  	        model.addAttribute("familiaSeleccionada", familiaDTO);
  	        return "asistidoRegistrarFamilia";

  	    } catch (IllegalArgumentException e) {
  	        model.addAttribute("error", e.getMessage());
  	        return "asistidoError";
  	    }
  	}

//CHECADO 24-06-2025 - FALTA COMPROBACIONES
  	
  	//Si mandas un POST en un formulario, entonces agarra el modelo del form, arma un objeto PersonaDTO y lo manda al Service.
  	@PostMapping("/familia/{id}")
  	public String guardarFormularioAsistidoFamilia(@Valid @ModelAttribute("asistidoForm") AsistidoForm asistidoForm, BindingResult result, Model model) {
  	    if (result.hasErrors()) {
  	        model.addAttribute("asistidoForm", asistidoForm);
  	        model.addAttribute("familiaSeleccionada", familiaService.buscarPorId(asistidoForm.getFamiliaId()));
  	        return "asistidoRegistrarFamilia";
  	    }

  	    try {
  	        AsistidoDTO asistidoDTO = new AsistidoDTO();
  	        
  	        asistidoDTO.setId(asistidoForm.getId());
  	        asistidoDTO.setActiva(asistidoForm.isActiva());
  	        asistidoDTO.setDni(asistidoForm.getDni());
  	        asistidoDTO.setNombre(asistidoForm.getNombre());
  	        asistidoDTO.setApellido(asistidoForm.getApellido());
  	        asistidoDTO.setFechaNacimiento(asistidoForm.getFechaNacimiento());
  	        asistidoDTO.setDomicilio(asistidoForm.getDomicilio());
  	        asistidoDTO.setOcupacion(asistidoForm.getOcupacion());
  	        asistidoDTO.setFechaRegistro(asistidoForm.getFechaRegistro());
  	        asistidoDTO.setTipoPersona(asistidoForm.getTipoPersona());
  	        asistidoDTO.setFamiliaId(asistidoForm.getFamiliaId());

  	        asistidoService.guardarAsistido(asistidoDTO);

  	        return "redirect:/familiaListar/" + asistidoDTO.getFamiliaId() + "/miembros";

  	    } catch (IllegalArgumentException e) {
  	        model.addAttribute("error", e.getMessage());
  	        model.addAttribute("asistidoForm", asistidoForm);
  	        model.addAttribute("familiaSeleccionada", familiaService.buscarPorId(asistidoForm.getFamiliaId()));
  	        return "asistidoRegistrarFamilia";
  	    }
  	}

  	
}
