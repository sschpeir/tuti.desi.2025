package tuti.desi.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import tuti.desi.entidades.Familia;
import tuti.desi.servicios.FamiliaService;

@Controller
@RequestMapping("/familias")
public class FamiliaController {

    @Autowired
    private FamiliaService familiaService;

    // El formulario por defecto que te redirecciona a la lista de familias que te da las opciones
    
    @GetMapping("/form")
    public String cargarFormulario(Model model) {
        model.addAttribute("familia", new Familia());
        return "redirect:/familias/listar"; 
    }
    
    @GetMapping("/alta")
    public String mostrarFormulario(Model model) {
        Familia familia = new Familia();
        familia.setFechaRegistro(LocalDate.now()); // <-- Seteás la fecha de hoy
        model.addAttribute("familia", familia);
        return "familiaAlta";
    }
    
    
    //Guarda la FAMILIA , si da error formatea la pagina de familiaAlta en Thymeleaf para mostrarlo y redirecciona a familiaLista.html cuando se cumple
    
    @PostMapping("/guardar")
    public String guardarFamilia(@ModelAttribute Familia familia, Model model) {
        // Validar si ya existe una familia con el mismo nombre, un rompedero de seso
        List<Familia> existentes = familiaService.listarFamilias();
        boolean nombreDuplicado = existentes.stream()
            .anyMatch(f -> f.getNombre().equalsIgnoreCase(familia.getNombre()) && !f.getId().equals(familia.getId()));

        if (nombreDuplicado) {
            model.addAttribute("familia", familia);
            model.addAttribute("error", "Ya existe una familia con ese nombre.");
            return "familiaAlta";
        }
        familiaService.guardarFamilia(familia); 
        return "redirect:/familias/listar"; 
    }

    //VER MIEMBROS DE FAMILIA
    
    @GetMapping("/{id}/miembros")
    public String listarMiembros(@PathVariable Long id, Model model) {
        Familia familia = familiaService.buscarPorId(id)
            .orElseThrow(() -> new IllegalArgumentException("No se encontró la familia con ID: " + id));

        model.addAttribute("familia", familia);
        model.addAttribute("miembros", familia.getIntegrantes());
        return "asistidosPorFamilia"; 
    }

    // LISTAS TODAS LAS FAMILIAS
    
    @GetMapping("/listar")
    public String listarFamilias(Model model) {
        List<Familia> familias = familiaService.listarFamilias();
        model.addAttribute("familias", familias);
        return "familiaLista";
    }
    
    // LISTAR FAMILIAS HABILITADAS
    
    @GetMapping("/listar/habilitadas")
    public String listarFamiliasHabilitadas(Model model) {
        List<Familia> familias = familiaService.listarFamiliasHabilitadas();
        model.addAttribute("familias", familias);
        return "familiaListaHabilitados";
    }

    
    //EDITAR LAS FAMILIAS POR ID
    
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Familia familia = familiaService.buscarPorId(id)
            .orElseThrow(() -> new IllegalArgumentException("No se encontró la familia con ID: " + id));
        model.addAttribute("familia", familia);
        return "familiaAlta"; 
    }
    
    //ELIMINAR LAS FAMILIAS POR ID
    
    @GetMapping("/eliminar/{id}")
    public String eliminarFamilia(@PathVariable Long id) {
        familiaService.eliminar(id);
        return "redirect:/familias/listar";
    }
}
