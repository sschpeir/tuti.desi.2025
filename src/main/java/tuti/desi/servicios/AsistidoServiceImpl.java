package tuti.desi.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tuti.desi.accesoDatos.AsistidoRepository;
import tuti.desi.entidades.Asistido;



@Service
public class AsistidoServiceImpl implements AsistidoService{

	@Autowired
    private AsistidoRepository asistidoRepository;
	

	@Override
	public void guardar(Asistido asistido) {
	    Optional<Asistido> existente = asistidoRepository.findByDni(asistido.getDni());

	    if (existente.isPresent() && !existente.get().getId().equals(asistido.getId())) {
	        throw new IllegalArgumentException("Ya existe un asistido con ese DNI.");
	    }

	    asistidoRepository.save(asistido);
	}

	@Override
	public List<Asistido> listarPorFamiliaId(Long idFamilia) {
	    return asistidoRepository.findByFamiliaId(idFamilia);
	}

	@Override
	public List<Asistido> listarTodos() {
		return asistidoRepository.findAll();
	}

	@Override
    public void eliminar(Long id) {
        asistidoRepository.deleteById(id);
    }

	@Override
	public Optional<Asistido> obtenerPorId(Long id) {
		return asistidoRepository.findById(id);
	}

	
	
}
