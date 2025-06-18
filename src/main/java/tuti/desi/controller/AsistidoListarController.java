package tuti.desi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tuti.desi.DTO.AsistidoDTO;

import tuti.desi.servicios.AsistidoService;



@Controller
@RequestMapping("/asistidoListar")
public class AsistidoListarController {

	@Autowired
    private AsistidoService asistidoService;
	
	//Si solicitas un GET, carga un modelo de lista de FamiliaDTO
	@GetMapping
	public String listarAsistidos(
	        @RequestParam(name = "soloActivos", required = false, defaultValue = "false") boolean soloActivos,
	        @RequestParam(name = "sinFamilia", required = false, defaultValue = "false") boolean sinFamilia,
	        Model model) {

	    List<AsistidoDTO> personas = asistidoService.listarFiltrado(soloActivos, sinFamilia);
	    model.addAttribute("personas", personas);
	    model.addAttribute("soloActivos", soloActivos);
	    model.addAttribute("sinFamilia", sinFamilia);
	    return "asistidoListar";
	}



	
	//Lista de asistidos activas
	@GetMapping("/activos")
	public String listarActivas(Model model) {
	    List<AsistidoDTO> personas = asistidoService.listarAsistidosActivos();
	    model.addAttribute("personas", personas);
	    return "asistidoListarActivos";
	}
	
	//Lista de asistidos activas
	@GetMapping("/sinFamilia")
	public String listarAsistidosSinFamilia(Model model) {
	    List<AsistidoDTO> personas = asistidoService.listarAsistidosSinFamilia();
	    model.addAttribute("personas", personas);
	    return "asistidoListarSinFamilia";
	}
	
}
