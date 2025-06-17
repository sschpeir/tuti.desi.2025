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
import tuti.desi.servicios.AsistidoService;
import tuti.desi.servicios.FamiliaService;
import tuti.desi.servicios.PersonaService;

import java.util.List;

@Controller
@RequestMapping("/asistidoRegistrar")
public class AsistidoRegistrarController {
    
    @Autowired
    private AsistidoService asistidoService;
    
    @Autowired
    private FamiliaService familiaService;

    
  	
//CHECADO
    
    //Si solicitas un GET, carga un modelo de PersonaDTO en blanco - Y te Carga un listado de todas las familias para asignar
    @GetMapping
  	public String cargarFormularioAsistido(Model model) {
  	    AsistidoDTO asistidoDTO = new AsistidoDTO();
  	    List<FamiliaDTO> familias = familiaService.listarTodas();

  	    if (familias.isEmpty()) {
	  	    model.addAttribute("error", "Debe registrar una familia primero.");
	  	    model.addAttribute("bloquear", true);
	  	}

  	    model.addAttribute("asistidoDTO", asistidoDTO);
  	    model.addAttribute("familias", familias);
  	    return "asistidoRegistrar";
  	}
    
//CHECADO
    
  	//Si mandas un POST en un formulario, entonces agarra el modelo del form, arma un objeto PersonaDTO y lo manda al Service.
  	@PostMapping
  	public String guardarFormularioAsistido(@ModelAttribute("asistidoDTO") AsistidoDTO asistidoDTO, Model model) {
			asistidoDTO.setTipoPersona("Asistido");
  		try {
  			asistidoService.guardarAsistido(asistidoDTO);
  	        //Si guarda, pasa al index.html
  	        return "redirect:/inicio";
  	    } catch (IllegalArgumentException e) {
  	        model.addAttribute("error", e.getMessage());
  	        model.addAttribute("asistidoDTO", asistidoDTO); 
  	        List<FamiliaDTO> familias = familiaService.listarTodas();
  	        model.addAttribute("familias", familias);
  	        //Si no guarda, deja los datos cargados y devuelve error que se lo agarra con Thymeleaf
  	        return "asistidoRegistrar"; 
  	    }
  	}

  	

  	@GetMapping("/familia/{id}")
  	public String cargarFormularioAsistidoFamilia(@PathVariable("id") Long id, Model model) {
  	    AsistidoDTO asistidoDTO = new AsistidoDTO();
  	    asistidoDTO.setFamiliaId(id);

  	    FamiliaDTO familiaDTO = familiaService.buscarPorId(id);
  	    if (familiaDTO == null) {
  	        model.addAttribute("error", "La familia no existe.");
  	        return "familiaError"; // poné el nombre de tu vista de error
  	    }

  	    model.addAttribute("asistidoDTO", asistidoDTO);
  	    model.addAttribute("familiaSeleccionada", familiaDTO);
  	    return "asistidoRegistrarFamilia";
  	}
    
  	//Si mandas un POST en un formulario, entonces agarra el modelo del form, arma un objeto PersonaDTO y lo manda al Service.
  	@PostMapping("/familia/{id}")
  	public String guardarFormularioAsistidoFamilia(@ModelAttribute("asistidoDTO") AsistidoDTO asistidoDTO, Model model) {
			asistidoDTO.setTipoPersona("Asistido");
  		try {
  			asistidoService.guardarAsistido(asistidoDTO);
  	        return "redirect:/familiaListar/"+asistidoDTO.getFamiliaId()+"/miembros";
  	    } catch (IllegalArgumentException e) {
  	        model.addAttribute("error", e.getMessage());
  	        model.addAttribute("asistidoDTO", asistidoDTO); 
  	        List<FamiliaDTO> familias = familiaService.listarTodas();
  	        model.addAttribute("familias", familias);
  	        //Si no guarda, deja los datos cargados y devuelve error que se lo agarra con Thymeleaf
  	        return "asistidoRegistrarFamilia"; 
  	    }
  	}
  	
}
