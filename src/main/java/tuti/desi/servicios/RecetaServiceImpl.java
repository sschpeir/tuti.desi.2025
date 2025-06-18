package tuti.desi.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tuti.desi.DTO.RecetaDTO;


import tuti.desi.accesoDatos.RecetaRepository;

import tuti.desi.entidades.Receta;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecetaServiceImpl implements RecetaService {

    @Autowired
    private RecetaRepository recetaRepository;

	@Override
	public Receta guardar(RecetaDTO familiaDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecetaDTO> listarTodas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecetaDTO> listarFamiliasActivas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecetaDTO buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void inhabilitar(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void habilitar(Long id) {
		// TODO Auto-generated method stub
		
	}

	
    
    
}
