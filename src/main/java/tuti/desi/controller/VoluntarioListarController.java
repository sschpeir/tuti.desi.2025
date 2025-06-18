package tuti.desi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tuti.desi.DTO.VoluntarioDTO;
import tuti.desi.servicios.VoluntarioService;



@Controller
@RequestMapping("/voluntarioListar")
public class VoluntarioListarController {

	@Autowired
    private VoluntarioService voluntarioService;
	
	//Si solicitas un GET, carga un modelo de lista de FamiliaDTO
	@GetMapping
    public String cargarFormulario(Model model) {
		List<VoluntarioDTO> voluntarios = voluntarioService.listarTodosVoluntarios();
        model.addAttribute("voluntarios", voluntarios);
        return "voluntarioListar";
    }	
	
	//Lista de asistidos activas
	@GetMapping("/activos")
	public String listarActivas(Model model) {
	    List<VoluntarioDTO> voluntarios = voluntarioService.listarVoluntariosActivos();
	    model.addAttribute("voluntarios", voluntarios);
	    return "voluntarioListarActivos";
	}
	
}
