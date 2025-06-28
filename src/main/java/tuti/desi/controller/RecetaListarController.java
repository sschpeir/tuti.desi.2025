package tuti.desi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tuti.desi.DTO.AsistidoDTO;
import tuti.desi.DTO.IngredienteDTO;
import tuti.desi.DTO.ItemRecetaDTO;
import tuti.desi.DTO.RecetaDTO;
import tuti.desi.DTO.RecetasConItemsYCaloriasDTO;
import tuti.desi.servicios.IngredienteService;
import tuti.desi.servicios.RecetaService;

@Controller
@RequestMapping("/recetaListar")
public class RecetaListarController {

    @Autowired
    private RecetaService recetaService;
    
    @Autowired
    private IngredienteService ingredienteService;

    @GetMapping
    public String mostrarFormulario(Model model) {
        List<RecetaDTO> recetas = recetaService.listarTodas();
        model.addAttribute("recetas", recetas);
        return "recetaListar";
    }

    @PostMapping
    public String procesarFormulario(@ModelAttribute("recetaDTO") RecetaDTO recetaDTO,Model model)
    {
	try {
		recetaService.guardarReceta(recetaDTO);
        //Si guarda, pasa al listado de recetas dadas de alta
        return "redirect:/recetaListar";
    } catch (IllegalArgumentException e) {
    	//Si no guarda, deja los datos cargados y devuelve error que se lo agarra con Thymeleaf
    	model.addAttribute("error", e.getMessage());
        model.addAttribute("recetaDTO", recetaDTO); 
        return "recetaListar"; 
    }
    }
    
	@GetMapping("/{id}/ingredientes")
	public String listarMiembros(@PathVariable Long id, Model model) {
		RecetaDTO recetaDTO = recetaService.buscarPorId(id);
		List<IngredienteDTO> ingredientes = ingredienteService.listarTodos();
		
	    
	    ItemRecetaDTO itemRecetaDTO = new ItemRecetaDTO();
	    itemRecetaDTO.setRecetaId(recetaDTO.getId());
	    
		model.addAttribute("recetaDTO", recetaDTO);
	    model.addAttribute("ingredientes", ingredientes);
	    model.addAttribute("itemRecetaDTO", itemRecetaDTO);
	    

	    return "recetaListarIngredientes";
	}
	
	//Joya..
	@PostMapping("/agregarIngrediente")
	public String agregarIngredienteAReceta(@ModelAttribute ItemRecetaDTO itemRecetaDTO, Model model) {
	    try {
	        recetaService.agregarItemAReceta(itemRecetaDTO);
	        return "redirect:/recetaListar/" + itemRecetaDTO.getRecetaId() + "/ingredientes";
	    } catch (IllegalArgumentException e) {
	        model.addAttribute("error", e.getMessage());

	        // Recargar datos necesarios para reconstruir la pantalla
	        RecetaDTO recetaDTO = recetaService.buscarPorId(itemRecetaDTO.getRecetaId());
	        model.addAttribute("recetaDTO", recetaDTO);

	        List<IngredienteDTO> ingredientes = ingredienteService.listarTodos();
	        model.addAttribute("ingredientes", ingredientes);

	        model.addAttribute("itemRecetaDTO", new ItemRecetaDTO());

	        return "recetaListarIngredientes2"; 
	    }
	}

	//Filtro del buscador 
	@GetMapping("/filtro")
	public String filtrarRecetas(@RequestParam("tipo") String tipo,
	                             @RequestParam("valor") String valor,
	                             Model model) {
	    List<RecetaDTO> recetas;

	    if ("id".equalsIgnoreCase(tipo)) {
	        try {
	            Long id = Long.parseLong(valor);
	            recetas = recetaService.filtrarId(id);
	        } catch (NumberFormatException e) {
	            recetas = List.of(); // si no es un número válido
	        }
	    } else {
	        recetas = recetaService.filtrarNombre(valor);
	    }

	    model.addAttribute("recetas", recetas);
	    model.addAttribute("tipo", tipo);
	    model.addAttribute("valor", valor);
	    return "recetaListar";
	}
	
	@GetMapping("/activas")
    public String mostrarFormularioActivas(Model model) {
        List<RecetaDTO> recetas = recetaService.listarTodasActivas();
        model.addAttribute("recetas", recetas);
        return "recetaListarActivas"; // Asegurate de que exista recetaForm.html en templates/
    }
	
	//Filtro del buscador en activas
	@GetMapping("/solicitado/filtro")
	public String mostrarFormularioSolicitadoFiltro(
	        @RequestParam("tipo") String tipo,
	        @RequestParam(required = false) String valor,
	        @RequestParam(required = false) Integer caloriasMin,
	        @RequestParam(required = false) Integer caloriasMax,
	        Model model) {

	    List<RecetasConItemsYCaloriasDTO> recetas;

	    if ("id".equalsIgnoreCase(tipo)) {
	        try {
	            if (valor == null || valor.isEmpty()) {
	                recetas = recetaService.listarRecetasConIngredientesActivosYCalorias();
	            } else {
	                Long id = Long.parseLong(valor);
	                recetas = recetaService.listarRecetasConIngredientesActivosYCaloriasPorId(id);
	            }
	        } catch (NumberFormatException e) {
	            recetas = List.of(); // ID inválido
	        }
	    } else if ("calorias".equalsIgnoreCase(tipo)) {
	        int min = (caloriasMin != null) ? caloriasMin : 0;
	        int max = (caloriasMax != null) ? caloriasMax : Integer.MAX_VALUE;
	        recetas = recetaService.listarRecetasConIngredientesActivosYCaloriasMinYMax(min, max);
	    } else {
	        recetas = recetaService.listarRecetasConIngredientesActivosYCalorias(); // fallback
	    }

	    model.addAttribute("recetas", recetas);
	    model.addAttribute("tipo", tipo);
	    model.addAttribute("valor", valor);
	    model.addAttribute("caloriasMin", caloriasMin);
	    model.addAttribute("caloriasMax", caloriasMax);
	    return "recetaListarActivasConItemsYCalorias";
	}


	
	@GetMapping("/solicitado")
    public String mostrarFormularioSolicitado(Model model) {
        List<RecetasConItemsYCaloriasDTO> recetas = recetaService.listarRecetasConIngredientesActivosYCalorias();
        model.addAttribute("recetas", recetas);
        return "recetaListarActivasConItemsYCalorias"; // Asegurate de que exista recetaForm.html en templates/
    }


}
