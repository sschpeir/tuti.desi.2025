package tuti.desi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import tuti.desi.DTO.RecetaDTO;
import tuti.desi.entidades.Receta;
import tuti.desi.servicios.RecetaService;

@Controller
@RequestMapping("/listarReceta.html")
public class RecetaListarController {

	@Autowired
    private RecetaService recetaService;
	
	//Si solicitas un GET, carga un modelo de recetaDTO en blanco - No te precarga datos, pero es bueno para ediciones.
	@RequestMapping(method=RequestMethod.GET)
    public String cargarFormulario(Model model) {
		RecetaDTO recetaDTO = new RecetaDTO();
		List<RecetaDTO> listaRecetas = recetaService.listarTodas();
		System.out.println(listaRecetas.size()+"Tamaño de lista");
        //model.addAttribute("recetaDTO", recetaDTO);
        model.addAttribute("listaRecetas", listaRecetas);
        
        return "listarReceta.html";
    }
	
	//Si mandas un POST en un formulario, entonces agarra el modelo del form, arma un objeto RecetaDTO y lo manda al Service.
	@RequestMapping(method=RequestMethod.POST)
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
	        return "registrarReceta.html"; 
	    }
    }
	
	
	
}
