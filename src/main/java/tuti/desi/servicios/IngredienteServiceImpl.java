package tuti.desi.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import tuti.desi.accesoDatos.IngredienteRepository;

import tuti.desi.entidades.Ingrediente;

@Service
public class IngredienteServiceImpl implements IngredienteService {
	
	@Autowired
    private IngredienteRepository ingredienteRepository;

	@Override
	public List<Ingrediente> listarTodos() {
		return ingredienteRepository.findAll();
	}


	@Override
	public void guardar(Ingrediente ingrediente) {
	    ingredienteRepository.findByNombre(ingrediente.getNombre())
	        .ifPresent(existente -> {
	            if (!existente.getId().equals(ingrediente.getId())) {
	                throw new IllegalArgumentException("Ya existe un ingrediente con ese nombre.");
	            }
	        });

	    ingredienteRepository.save(ingrediente);
	}





}
