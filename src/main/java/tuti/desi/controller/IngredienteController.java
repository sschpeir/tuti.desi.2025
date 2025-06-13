package tuti.desi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import tuti.desi.DTO.IngredienteDTO;
import tuti.desi.entidades.Ingrediente;
import tuti.desi.entidades.Producto;
import tuti.desi.servicios.IngredienteService;


import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ingredientes")
public class IngredienteController {

    @Autowired
    private IngredienteService ingredienteService;

    // Mostrar formulario para crear nueva persona
    @GetMapping("/alta")
    public String mostrarFormulario(Model model) {
        model.addAttribute("ingredienteDTO", new IngredienteDTO());
        return "ingredienteAlta";
    }
    

    
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("ingredienteForm") IngredienteDTO dto) {
        if ("Producto".equals(dto.getTipoCondimento())) {
            Producto p = new Producto();
            p.setNombre(dto.getNombre());
            p.setCalorias(dto.getCalorias());
            p.setPrecioActual(dto.getPrecioActual());
            p.setStockDisponible(dto.getStockDisponible());
            
            ingredienteService.guardar(p); // 💾 guardar producto
        } else {
            Ingrediente i = new Ingrediente();
            i.setNombre(dto.getNombre());
            i.setCalorias(dto.getCalorias());
            
            ingredienteService.guardar(i); // 💾 guardar ingrediente
        }

        return "redirect:/ingredientes/listar";
    }

    
    @GetMapping("/listar")
    public String listarIngredientes(Model model) {
        List<Ingrediente> ingredientes = ingredienteService.listarTodos(); // Este método debería traer tanto Ingrediente como Producto
        model.addAttribute("ingredientes", ingredientes);
        return "ingredienteLista";
    }




}
