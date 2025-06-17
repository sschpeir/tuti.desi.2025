package tuti.desi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import tuti.desi.DTO.FamiliaDTO;
import tuti.desi.servicios.FamiliaService;


@Controller
@RequestMapping("/familiaListar")
public class FamiliaListarController {

	@Autowired
    private FamiliaService familiaService;
	
	//CHECADO X
	
	//Si solicitas un GET, carga un modelo de lista de FamiliaDTO
	@GetMapping
    public String cargarFormulario(Model model) {
		List<FamiliaDTO> familias = familiaService.listarTodas();
        model.addAttribute("familias", familias);
        return "familiaListar";
    }

	//CHECADO X -- VER COMO LISTADO O VER COMO FILTRAR
	
	//Lista de familias activas
	@GetMapping("/activas")
	public String listarActivas(Model model) {
	    List<FamiliaDTO> familias = familiaService.listarFamiliasActivas();
	    model.addAttribute("familias", familias);
	    return "familiaListarActivas";
	}
	
	//CHECADO X
	
	@GetMapping("/{id}/miembros")
	public String listarMiembros(@PathVariable Long id, Model model) {
	    FamiliaDTO familiaDTO = familiaService.buscarPorId(id);
	    model.addAttribute("familiaDTO", familiaDTO);
	    model.addAttribute("miembros", familiaDTO.getIntegrantes());
	    return "familiaListarAsistidos";
	}

	
}
