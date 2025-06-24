package tuti.desi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tuti.desi.DTO.IngredienteDTO;
import tuti.desi.servicios.IngredienteService;

@Controller
@RequestMapping("/ingredienteListar")
public class IngredienteListarController {

	@Autowired
    private IngredienteService ingredienteService;
	
	//CHECADO X
	
	//Si solicitas un GET, carga un modelo de lista de IngredienteDTO
	@GetMapping
    public String cargarFormulario(Model model) {
		List<IngredienteDTO> ingredientes = ingredienteService.listarTodos();
        model.addAttribute("ingredientes", ingredientes);
        return "ingredienteListar";
    }

	//CHECADO X -- VER COMO LISTADO O VER COMO FILTRAR
	
	//Lista de Ingrediente activas
	@GetMapping("/activos")
	public String listarActivas(Model model) {
	    List<IngredienteDTO> ingredientes = ingredienteService.listarTodos();
	    model.addAttribute("ingredientes", ingredientes);
	    return "ingredienteListarActivos";
	}
	
}
