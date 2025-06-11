package tuti.desi.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tuti.desi.accesoDatos.FamiliaRepository;
import tuti.desi.entidades.Familia;


@Service
public class FamiliaServiceImpl implements FamiliaService{

	@Autowired
    private FamiliaRepository familiaRepository;
    
    
    @Override
    public List<Familia> listarFamilias() {
        return familiaRepository.findAll();
    }
    
    @Override
    public Familia guardarFamilia(Familia familia) {
    	return familiaRepository.save(familia);
    }
    
	@Override
	public Optional<Familia> buscarPorId(Long id){
		return familiaRepository.findById(id);
	}
	
    @Override
    public void eliminar(Long id) {
        familiaRepository.deleteById(id);
    }

    public List<Familia> listarFamiliasHabilitadas() {
        return familiaRepository.findByActivaTrue();
    }

    



}
