package tuti.desi.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tuti.desi.DTO.ItemRecetaDTO;
import tuti.desi.DTO.RecetaDTO;
import tuti.desi.servicios.RecetaService;

@Controller
@RequestMapping("/recetaRegistrar")
public class RecetaRegistrarController {

    @Autowired
    private RecetaService recetaService;

    @GetMapping
    public String mostrarFormulario(Model model) {
        RecetaDTO recetaDTO = new RecetaDTO();
        recetaDTO.setItems(new ArrayList<>()); 
        model.addAttribute("recetaDTO", recetaDTO);
        return "recetaRegistrar"; // Asegurate de que exista recetaForm.html en templates/
    }

    @PostMapping
    public String procesarFormulario(
            @ModelAttribute("recetaDTO") RecetaDTO recetaDTO,
            @RequestParam(value = "agregarItem", required = false) String agregarItem,
            Model model
    ) {
        if (agregarItem != null) {
            // Se pidió agregar un nuevo item
            recetaDTO.getItems().add(new ItemRecetaDTO());
            model.addAttribute("recetaDTO", recetaDTO);
            return "recetaRegistrar"; // vuelve al mismo form con un item más
        }

        // Guardar la receta
        recetaService.guardar(recetaDTO);

        return "redirect:/recetaListar";
    }
}
