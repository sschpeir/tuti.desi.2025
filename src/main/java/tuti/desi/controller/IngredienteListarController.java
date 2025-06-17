package tuti.desi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tuti.desi.DTO.IngredienteDTO;
import tuti.desi.DTO.RecetaDTO;
import tuti.desi.entidades.Receta;
import tuti.desi.servicios.IngredienteService;
import tuti.desi.servicios.RecetaService;

@Controller
@RequestMapping("/listarIngrediente.html")
public class IngredienteListarController {

	@Autowired
    private IngredienteService ingredienteService;
	
	//Si solicitas un GET, carga un modelo de recetaDTO en blanco - No te precarga datos, pero es bueno para ediciones.
	@GetMapping
    public String cargarFormulario(Model model) {
		IngredienteDTO ingredienteDTO = new IngredienteDTO();
		List<RecetaDTO> listaRecetas = ingredienteService.listarTodos();
        model.addAttribute("listaRecetas", listaRecetas);
        
        return "listarReceta.html";
    }
	
	//Si mandas un POST en un formulario, entonces agarra el modelo del form, arma un objeto RecetaDTO y lo manda al Service.
	@PostMapping
	public String guardarFormulario(@ModelAttribute("recetaDTO") IngredienteDTO ingredienteDTO, Model model){
		//Le metemos un try-catch por los errores, ejemplo: si hay otra con el mismo nombre
		try {
			ingredienteService.guardar(ingredienteDTO);
	        //Si guarda, pasa al index.html
	        return "index.html";
	    } catch (IllegalArgumentException e) {
	        model.addAttribute("error", e.getMessage());
	        model.addAttribute("ingredienteDTO", ingredienteDTO); 
	        //Si no guarda, deja los datos cargados y devuelve error que se lo agarra con Thymeleaf
	        return "registrarReceta.html"; 
	    }
    }
	
	
	
}
