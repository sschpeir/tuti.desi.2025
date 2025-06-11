package tuti.desi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tuti.desi.entidades.Persona;
import tuti.desi.servicios.PersonaService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    // Mostrar formulario para crear nueva persona
    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("persona", new Persona());
        return "persona-alta";
    }
    
    @PostMapping("/guardar")
    public String guardarPersona(@ModelAttribute("persona") Persona persona) {
        System.out.println("Persona recibida: " + persona);
        return "redirect:/personas/lista";
    }




    // Listar todas las personas (opcional)
    @GetMapping("/lista")
    public String listarPersonas(Model model) {
        List<Persona> personas = personaService.listarTodos();
        model.addAttribute("personas", personas);
        return "persona-lista"; // necesitas crear persona-lista.html
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarPersona(@PathVariable Long id) {
        personaService.eliminar(id); // suponiendo que lo tenés implementado
        return "redirect:/personas/lista";
    }
    
    @GetMapping("/editar/{id}")
    public String editarPersona(@PathVariable Long id, Model model) {
        Persona persona = personaService.buscarPorId(id)
            .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        model.addAttribute("persona", persona);
        return "persona-alta"; // usás el mismo formulario de alta
    }

}
