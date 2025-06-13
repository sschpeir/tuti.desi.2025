package tuti.desi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tuti.desi.servicios.RecetaService;
import tuti.desi.DTO.RecetaDTO;
import tuti.desi.entidades.Receta;

@Controller
@RequestMapping("/recetas")
public class RecetaController {

    @Autowired
    private RecetaService recetaService;
    
    @GetMapping("/alta")
    public String mostrarFormularioReceta(Model model) {
        model.addAttribute("receta", new Receta());
        return "recetaAlta";
    }
    
    //Controlador para la lista
    @GetMapping("/listar")
    public String listarRecetas(Model model) {
        List<RecetaDTO> recetas = recetaService.listarTodas();
        model.addAttribute("recetas", recetas);
        return "recetaListar"; // necesitas crear persona-lista.html
    }

}