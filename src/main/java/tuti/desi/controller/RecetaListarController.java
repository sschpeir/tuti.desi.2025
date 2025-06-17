package tuti.desi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tuti.desi.DTO.FamiliaDTO;
import tuti.desi.DTO.RecetaDTO;
import tuti.desi.entidades.Receta;
import tuti.desi.servicios.RecetaService;

@Controller
@RequestMapping("/listarReceta")
public class RecetaListarController {

	@Autowired
    private RecetaService recetaService;
	
	//Si solicitas un GET, el se arma una lista de RecetaDTO llamando al service y luego las incrusta como atributos al modelo.
	@GetMapping
    public String cargarFormulario(Model model) {
		RecetaDTO recetaDTO = new RecetaDTO();
		List<RecetaDTO> listaRecetas = recetaService.listarTodas();
        model.addAttribute("listaRecetas", listaRecetas);
        
        return "listarReceta";
    }
	
	
	//Lista de familias activas
	@GetMapping("/activas")
	public String listarActivas(Model model) {
	    List<RecetaDTO> listaRecetas = recetaService.listarRecetasActivas();
	    model.addAttribute("listaRecetas", listaRecetas);
	    return "listarReceta.html";
	}
	
	//Lista de familias inactivas
	@GetMapping("/inactivas")
	public String listarInactivas(Model model) {
	    List<RecetaDTO> listaRecetas = recetaService.listarRecetasInactivas();
	    model.addAttribute("listaRecetas", listaRecetas);
	    return "listarReceta";
	}
	
	
	//Si mandas un POST en un formulario, entonces agarra el modelo del form, arma un objeto RecetaDTO y lo manda al Service.
	@PostMapping
	public String guardarFormulario(@ModelAttribute("recetaDTO") RecetaDTO recetaDTO, Model model){
		//Le metemos un try-catch por los errores, ejemplo: si hay otra con el mismo nombre
		try {
	        recetaService.guardar(recetaDTO);
	        //Si guarda, pasa al index.html
	        return "index.html";
	    } catch (IllegalArgumentException e) {
	        model.addAttribute("error", e.getMessage());
	        model.addAttribute("recetaDTO", recetaDTO); 
	        //Si no guarda, deja los datos cargados y devuelve error que se lo agarra con Thymeleaf
	        return "registrarReceta"; 
	    }
    }
	
	
	
}
