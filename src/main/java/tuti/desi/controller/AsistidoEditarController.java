package tuti.desi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import tuti.desi.DTO.AsistidoDTO;
import tuti.desi.DTO.FamiliaDTO;
import tuti.desi.DTO.PersonaDTO;
import tuti.desi.servicios.AsistidoService;
import tuti.desi.servicios.FamiliaService;

import java.util.List;

@Controller
@RequestMapping("/asistidoEditar")
public class AsistidoEditarController {

	@Autowired
    private AsistidoService asistidoService;
    
    @Autowired
    private FamiliaService familiaService;

    
    //Metodo GET del formulario de Editar Asistido
    //Si mandamos un GET, se busca un objeto asistidoDTO con el ID pasado, si es un asistido, entonces se arma un asistidoForm y se le pasan los datos del DTO y este form se carga al modelo, lo que carga datos, sino arroja errores
    @GetMapping("/{id}")
    public String cargarFormulario(@PathVariable("id") Long id, Model model) {
        AsistidoForm asistidoForm = new AsistidoForm();

    	try {
            PersonaDTO personaDTO = asistidoService.buscarPorId(id);

            if (!"ASISTIDO".equalsIgnoreCase(personaDTO.getTipoPersona())) {
                throw new IllegalArgumentException("La persona con ID " + id + " no es un asistido.");
            }

            AsistidoDTO asistidoDTO = (AsistidoDTO) personaDTO; //castea persona en asistidoDTO
            
            asistidoForm.setId(asistidoDTO.getId());
            asistidoForm.setActiva(asistidoDTO.isActiva());
  			asistidoForm.setDni(asistidoDTO.getDni());
  			asistidoForm.setNombre(asistidoDTO.getNombre());
  			asistidoForm.setApellido(asistidoDTO.getApellido());
  			asistidoForm.setFechaNacimiento(asistidoDTO.getFechaNacimiento());
  			asistidoForm.setDomicilio(asistidoDTO.getDomicilio());
  			asistidoForm.setOcupacion(asistidoDTO.getOcupacion());
  			asistidoForm.setFechaRegistro(asistidoDTO.getFechaRegistro());
  			asistidoForm.setTipoPersona(asistidoDTO.getTipoPersona());
  			asistidoForm.setFamiliaId(asistidoDTO.getFamiliaId());

            List<FamiliaDTO> familias = familiaService.listarTodas();
            model.addAttribute("asistidoForm", asistidoForm);
            model.addAttribute("familias", familias);

            return "asistidoEditar";

        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            List<FamiliaDTO> familias = familiaService.listarTodas();
            model.addAttribute("asistidoForm", asistidoForm);
            model.addAttribute("familias", familias);
            return "errorFamilia";
        }
    }
    
    //Metodo POST del formulario de Editar Asistido del Inicio
  	//Si mandas un POST en un formulario, entonces agarra el modelo del form, arma un objeto asistidoDTO y lo manda al Service.
  	@PostMapping
  	public String guardarFormularioAsistido(@Valid @ModelAttribute("asistidoForm") AsistidoForm asistidoForm, BindingResult result, Model model) {	
  		
  		if (result.hasErrors()) {
  	        model.addAttribute("asistidoForm", asistidoForm);
  	        model.addAttribute("familiaSeleccionada", familiaService.buscarPorId(asistidoForm.getFamiliaId()));
  	        return "asistidoRegistrarFamilia";//ACA
  	    }
  		try {
  			AsistidoDTO asistidoDTO = new AsistidoDTO();
  			
  	        asistidoDTO.setId(asistidoForm.getId());
  	        asistidoDTO.setActiva(asistidoForm.isActiva());
  	        asistidoDTO.setDni(asistidoForm.getDni());
  	        asistidoDTO.setNombre(asistidoForm.getNombre());
  	        asistidoDTO.setApellido(asistidoForm.getApellido());
  	        asistidoDTO.setFechaNacimiento(asistidoForm.getFechaNacimiento());
  	        asistidoDTO.setDomicilio(asistidoForm.getDomicilio());
  	        asistidoDTO.setOcupacion(asistidoForm.getOcupacion());
  	        asistidoDTO.setFechaRegistro(asistidoForm.getFechaRegistro());
  	        asistidoDTO.setTipoPersona(asistidoForm.getTipoPersona());
  	        asistidoDTO.setFamiliaId(asistidoForm.getFamiliaId());
  			
  			asistidoService.guardarAsistido(asistidoDTO);
  	        //Si guarda, pasa a la lista de asistidos
  			if (asistidoDTO.getFamiliaId()!=null) {
  				return "redirect:/familiaListar/"+asistidoDTO.getFamiliaId()+"/miembros";
  			}else {
  				return "redirect:/asistidoListar";
  			}
  	        
  	    } catch (IllegalArgumentException e) {
  	        model.addAttribute("error", e.getMessage());
  	        model.addAttribute("asistidoForm", asistidoForm); 
  	        //Si no guarda, deja los datos cargados y devuelve error que se lo agarra con Thymeleaf
  	        return "asistidoEditar"; 
  	    }
  	}
  	
  	//Metodo GET del formulario de Editar Asistido desde el listado de miembros por familia
    //Si mandamos un GET, se busca un objeto asistidoDTO con el ID pasado, si es un asistido, entonces se arma un asistidoForm y se le pasan los datos del DTO y este form se carga al modelo, lo que carga datos, sino arroja errores
    @GetMapping("/{id}/familia")
    public String cargarFormularioFamilia(@PathVariable("id") Long id, Model model) {
        AsistidoForm asistidoForm = new AsistidoForm();

    	try {
            PersonaDTO personaDTO = asistidoService.buscarPorId(id);// llama al servicio para traer los datos de la persona con ese ID

            if (!"ASISTIDO".equalsIgnoreCase(personaDTO.getTipoPersona())) {
                throw new IllegalArgumentException("La persona con ID " + id + " no es un asistido.");//LANZA UNA EXCEPCION SI NO ES UN ASISTIDO
            }

            AsistidoDTO asistidoDTO = (AsistidoDTO) personaDTO; // castea asistidoDTO
            
            asistidoForm.setId(asistidoDTO.getId()); // hace el casteo de y trasfiere los datos del asistidoDTO al formulario asistidoform
            
            asistidoForm.setActiva(asistidoDTO.isActiva());
  			asistidoForm.setDni(asistidoDTO.getDni());
  			asistidoForm.setNombre(asistidoDTO.getNombre());
  			asistidoForm.setApellido(asistidoDTO.getApellido());
  			asistidoForm.setFechaNacimiento(asistidoDTO.getFechaNacimiento());
  			asistidoForm.setDomicilio(asistidoDTO.getDomicilio());
  			asistidoForm.setOcupacion(asistidoDTO.getOcupacion());
  			asistidoForm.setFechaRegistro(asistidoDTO.getFechaRegistro());
  			asistidoForm.setTipoPersona(asistidoDTO.getTipoPersona());
  			asistidoForm.setFamiliaId(asistidoDTO.getFamiliaId());

            List<FamiliaDTO> familias = familiaService.listarTodas();
            model.addAttribute("asistidoForm", asistidoForm);//agrega los objetos al modelo
            model.addAttribute("familias", familias);

            return "asistidoEditarFamilia";

        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "errorFamilia"; //Si se lanza una excepción, se carga un mensaje de error al modelo y se muestra otra vista
        }
    }
    
    
    //Metodo POST del formulario de Editar Asistido desde el listado de miembros por familia
  	//Si mandas un POST en un formulario, entonces agarra el modelo del form, arma un objeto asistidoDTO y lo manda al Service.
  	@PostMapping("/{id}/familia")
  	public String guardarFormularioAsistidoFamilia(@Valid @ModelAttribute("asistidoForm") AsistidoForm asistidoForm, BindingResult result, Model model) {	
  		
  		if (result.hasErrors()) {
  	        model.addAttribute("asistidoForm", asistidoForm);
  	        model.addAttribute("familiaSeleccionada", familiaService.buscarPorId(asistidoForm.getFamiliaId()));
  	        return "asistidoRegistrarFamilia";//ACA
  	    }
  		try {
  			AsistidoDTO asistidoDTO = new AsistidoDTO();
  			
  	        asistidoDTO.setId(asistidoForm.getId());
  	        asistidoDTO.setActiva(asistidoForm.isActiva());
  	        asistidoDTO.setDni(asistidoForm.getDni());
  	        asistidoDTO.setNombre(asistidoForm.getNombre());
  	        asistidoDTO.setApellido(asistidoForm.getApellido());
  	        asistidoDTO.setFechaNacimiento(asistidoForm.getFechaNacimiento());
  	        asistidoDTO.setDomicilio(asistidoForm.getDomicilio());
  	        asistidoDTO.setOcupacion(asistidoForm.getOcupacion());
  	        asistidoDTO.setFechaRegistro(asistidoForm.getFechaRegistro());
  	        asistidoDTO.setTipoPersona(asistidoForm.getTipoPersona());
  	        asistidoDTO.setFamiliaId(asistidoForm.getFamiliaId());
  			
  			asistidoService.guardarAsistido(asistidoDTO);
  	        //Si guarda, pasa a la lista de asistidos
  			if (asistidoDTO.getFamiliaId()!=null) {
  				return "redirect:/familiaListar/"+asistidoDTO.getFamiliaId()+"/miembros";
  			}else {
  				return "redirect:/familiaListar";
  			}
  	        
  	    } catch (IllegalArgumentException e) {
  	        model.addAttribute("error", e.getMessage());
  	        model.addAttribute("asistidoForm", asistidoForm); 
  	        //Si no guarda, deja los datos cargados y devuelve error que se lo agarra con Thymeleaf
  	        return "asistidoEditar"; 
  	    }
  	}
  	
  	//Metodo GET para la funcion de deshabilitar asistido del formulario de Editar Asistido
  	//Endpoint para deshabilitar asistidos
  	@GetMapping("/{id}/deshabilitar")
	public String deshabilitarAsistido(@PathVariable("id") Long id) {
	    asistidoService.inhabilitar(id);
	    
	    return "redirect:/asistidoListar";
	}
  	//Metodo GET para la funcion de deshabilitar asistido del formulario de Editar Asistido
  	//Endpoint para habilitar asistidos
	@GetMapping("/{id}/habilitar")
	public String habilitarAsistido(@PathVariable("id") Long id) {
		asistidoService.habilitar(id);
	    
		return "redirect:/asistidoListar";
	}
	
	//Metodo GET para la funcion de deshabilitar asistido del formulario de Listar Asistidos por Familia
	//Endpoint para deshabilitar asistidos desde familias, para redireccionar
	@GetMapping("/{id}/familia/deshabilitar")
	public String deshabilitarAsistidoFamilia(@PathVariable("id") Long id) {
		asistidoService.inhabilitar(id);    
	    PersonaDTO personaDTO = asistidoService.buscarPorId(id);
	    if (personaDTO instanceof AsistidoDTO asistidoDTO && asistidoDTO.getFamiliaId() != null) {
	        Long familiaId = asistidoDTO.getFamiliaId();
	        
	        return "redirect:/familiaListar/" + familiaId + "/miembros";
	    }
	    // Si no es un Asistido o no tiene familia asociada
	    return "redirect:/familiaListar";
	}

	//Metodo GET para la funcion de deshabilitar asistido del formulario de Listar Asistidos por Familia
	//Endpoint para habilitar asistidos desde familias, para redireccionar
	@GetMapping("/{id}/familia/habilitar")
	public String habilitarAsistidoFamilia(@PathVariable("id") Long id) {
	    asistidoService.habilitar(id);
	    PersonaDTO personaDTO = asistidoService.buscarPorId(id);
	    if (personaDTO instanceof AsistidoDTO asistidoDTO && asistidoDTO.getFamiliaId() != null) {
	        Long familiaId = asistidoDTO.getFamiliaId();
	        
	        return "redirect:/familiaListar/" + familiaId + "/miembros";
	    }
	    // Si no es un Asistido o no tiene familia asociada
	    return "redirect:/familiaListar";
	}
  	
}
