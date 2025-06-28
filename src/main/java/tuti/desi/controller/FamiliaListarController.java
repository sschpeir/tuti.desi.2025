package tuti.desi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tuti.desi.DTO.FamiliaDTO;
import tuti.desi.DTO.FamiliasConMiembrosActivosDTO;
import tuti.desi.DTO.FamiliasConMiembrosDTO;
import tuti.desi.servicios.FamiliaService;


@Controller
@RequestMapping("/familiaListar")
public class FamiliaListarController {

	@Autowired
    private FamiliaService familiaService;
	
	//CHECADO X
	
	@GetMapping
    public String cargarFormulario(Model model) {
		 
		List<FamiliasConMiembrosDTO> familias = familiaService.listadoFamiliasConAsistidosTotales();
        model.addAttribute("familias", familias);
        return "familiaListar";
    }

	
	//CHECADO X
	
	@GetMapping("/{id}/miembros")
	public String listarMiembros(@PathVariable Long id, Model model) {
	    FamiliaDTO familiaDTO = familiaService.buscarPorId(id);
	    model.addAttribute("familiaDTO", familiaDTO);
	    model.addAttribute("miembros", familiaDTO.getIntegrantes());
	    return "familiaListarAsistidos";
	}
	
	//Filtro del buscador en activas
	
	
	@GetMapping("/solicitado")
    public String cargarFormularioSolicitado(Model model) {
		List<FamiliasConMiembrosActivosDTO> familias = familiaService.listadoFamiliasMiembrosActivos();
        model.addAttribute("familias", familias);
        return "familiaListarSolicitado";
    }
	
	//Listado de 
	@GetMapping("/solicitado/filtro")
	public String filtrarFamiliaSolicitadoFiltro(@RequestParam("tipo") String tipo,@RequestParam("valor") String valor,Model model) {
	    List<FamiliasConMiembrosActivosDTO> familias = familiaService.listadoFamiliasMiembrosActivos();

	    if ("id".equalsIgnoreCase(tipo)) {
	        try {
	        	if (valor.isEmpty()) {
		            familias = familiaService.listadoFamiliasMiembrosActivos();	
	        	}else {
	        		Long id = Long.parseLong(valor);
		            familias = familiaService.listadoFamiliasMiembrosActivosFiltroId(id);
	        	}
	            
	        } catch (NumberFormatException e) {
	            familias = List.of(); // si no es un número válido
	        }
	    } else {
	        familias = familiaService.listadoFamiliasMiembrosActivosFiltroNombre(valor);
	    }

	    model.addAttribute("familias", familias);
	    model.addAttribute("tipo", tipo);
	    model.addAttribute("valor", valor);
	    return "familiaListarSolicitado";
	}
	
}
