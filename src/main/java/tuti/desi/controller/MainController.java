package tuti.desi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
public class MainController {
	
	

	 @GetMapping("/inicio")
	    public String mostrarFormularioMain(@RequestParam(required = false) Model model) {
	        return "index"; 
	    }


}
