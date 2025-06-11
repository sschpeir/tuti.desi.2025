package tuti.desi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tuti.desi.servicios.RecetaService;
import tuti.desi.entidades.Receta;

@RestController
@RequestMapping("/api/clientes")
public class RecetaController {

    @Autowired
    private RecetaService recetaService;

}