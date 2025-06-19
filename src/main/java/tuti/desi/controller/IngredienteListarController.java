package tuti.desi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import tuti.desi.DTO.IngredienteDTO;
import tuti.desi.DTO.RecetaDTO;


import tuti.desi.servicios.RecetaService;

//VER

@Controller
@RequestMapping("/ingredienteListar")
public class IngredienteListarController {

	@Autowired
    private RecetaService recetaService;
	
	//CHECADO X
	
	//Si solicitas un GET, carga un modelo de lista de FamiliaDTO
	@GetMapping
    public String cargarFormulario(Model model) {
		List<RecetaDTO> recetas = recetaService.listarTodos();
        model.addAttribute("recetas", recetas);
        return "ingredienteListar";
    }

	//CHECADO X -- VER COMO LISTADO O VER COMO FILTRAR
	
	//Lista de familias activas
	@GetMapping("/activas")
	public String listarActivas(Model model) {
	    List<RecetaDTO> recetas = recetaService.listarTodos();
	    model.addAttribute("recetas", recetas);
	    return "ingredienteListarActivas";
	}
	
}
