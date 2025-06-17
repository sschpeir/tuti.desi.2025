package tuti.desi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tuti.desi.DTO.AsistidoDTO;

import tuti.desi.servicios.AsistidoService;



@Controller
@RequestMapping("/asistidoListar")
public class AsistidoListarController {

	@Autowired
    private AsistidoService asistidoService;
	
	//Si solicitas un GET, carga un modelo de lista de FamiliaDTO
	@GetMapping
    public String cargarFormulario(Model model) {
		List<AsistidoDTO> personas = asistidoService.listarTodosAsistidos();
        model.addAttribute("personas", personas);
        return "asistidoListar";
    }	
	
	//Lista de asistidos activas
	@GetMapping("/activos")
	public String listarActivas(Model model) {
	    List<AsistidoDTO> personas = asistidoService.listarAsistidosActivos();
	    model.addAttribute("personas", personas);
	    return "asistidoListarActivos";
	}
	
}
