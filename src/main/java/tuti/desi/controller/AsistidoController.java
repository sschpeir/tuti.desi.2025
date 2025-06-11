package tuti.desi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import tuti.desi.entidades.Asistido;
import tuti.desi.entidades.Familia;
import tuti.desi.entidades.Persona;
import tuti.desi.servicios.AsistidoService;
import tuti.desi.servicios.FamiliaService;
import org.springframework.dao.DataIntegrityViolationException;


import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/asistidos")
public class AsistidoController {
	
	//Tengo que invocar el service de Asistido y Familia porque tengo reglas de negocio de ambos
	
    @Autowired
    private AsistidoService asistidoService;
    
    @Autowired
    private FamiliaService familiaService;

    
    


    
    @GetMapping("/form")
    public String mostrarFormularioAsistido(@RequestParam(required = false) Long familiaId, Model model) {
        Asistido asistido = new Asistido();
        if (familiaId != null) {
        	familiaService.buscarPorId(familiaId).ifPresent(asistido::setFamilia);
        }
        model.addAttribute("asistido", asistido);
        model.addAttribute("familias", familiaService.listarFamilias());
        return "asistidoAlta"; 
    }
    
    @GetMapping("/alta")
    public String altarFormularioAsistido(@RequestParam(required = false) Long familiaId, Model model) {
        Asistido asistido = new Asistido();
        if (familiaId != null) {
        	familiaService.buscarPorId(familiaId).ifPresent(asistido::setFamilia);

        }
        model.addAttribute("asistido", asistido);
        model.addAttribute("familias", familiaService.listarFamilias());
        return "asistidoAlta"; 
    }

    //El Endpoint para guardar una persona dentro de una familia, si tiene error lo tira mediante cartel a Thymeleaf 
    //sino te manda a la pagina de miembros dados de alta
    
    @PostMapping("/guardar")
    public String guardarAsistido(@Valid @ModelAttribute Asistido asistido,
                                  BindingResult result,
                                  Model model) {
    	
        if (result.hasErrors()) {
            model.addAttribute("familias", familiaService.listarFamilias());
            return "asistidoAlta";
        }
        
        
        
        try {
            asistidoService.guardar(asistido);
            return "redirect:/familias/" + asistido.getFamilia().getId() + "/miembros";
        } catch (IllegalArgumentException e) {
            model.addAttribute("familias", familiaService.listarFamilias());
            model.addAttribute("error", e.getMessage());
            return "asistidoAlta";
        }
    }
    
    //consultar miembros de la familia
    @GetMapping("/familias/{id}/miembros")
    public String listarMiembrosFamilia(@PathVariable Long id, Model model) {
        Familia familia = familiaService.buscarPorId(id)
            .orElseThrow(() -> new IllegalArgumentException("Familia no encontrada con ID: " + id));
        List<Asistido> miembros = asistidoService.listarPorFamiliaId(id);

        model.addAttribute("familia", familia);
        model.addAttribute("miembros", miembros);
        return "asistidosPorFamilia"; 
    }
    
    
    //Un Endpoint de lista
    
    @GetMapping("/lista")
    public String listarAsistidos(Model model) {
        List<Asistido> asistidos = asistidoService.listarTodos();
        model.addAttribute("asistidos", asistidos);
        return "asistidoLista"; // vista HTML
    }
    
    //Endpoint que simula eliminar pero solo cambia el estado del objeto persona.
    
    @GetMapping("/eliminar/{id}")
    public String inhabilitarAsistido(@PathVariable Long id) {
        Optional<Asistido> asistidoOpt = asistidoService.obtenerPorId(id);
        Asistido asistido = asistidoOpt.get();
        Long familiaId = asistido.getFamilia().getId();
        if (asistidoOpt.isPresent()) {
            
            System.out.println(asistido.isActiva());
            if (asistido.isActiva()) {
            	asistido.setActiva(false);
            	}else{
            	asistido.setActiva(true);
            	}
            asistidoService.guardar(asistido);
            return "redirect:/familias/" + familiaId + "/miembros";
            }
        	return "redirect:/familias/" + familiaId + "/miembros";
        }
  
    
    //Endpoint para editar
    
    @GetMapping("/editar/{id}")
    public String editarAsistido(@PathVariable Long id, Model model) {
        Asistido asistido = asistidoService.obtenerPorId(id)
            .orElseThrow(() -> new IllegalArgumentException("Asistido no encontrado con ID: " + id));
        
        model.addAttribute("asistido", asistido);
        model.addAttribute("familias", familiaService.listarFamilias()); // Asegurate de incluir las familias si las usás en el form

        return "asistidoEditar";
    }


}
