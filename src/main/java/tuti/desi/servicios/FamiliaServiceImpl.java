package tuti.desi.servicios;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tuti.desi.DTO.AsistidoDTO;
import tuti.desi.DTO.FamiliaDTO;
import tuti.desi.DTO.FamiliasConMiembrosActivosDTO;
import tuti.desi.DTO.FamiliasConMiembrosDTO;
import tuti.desi.accesoDatos.FamiliaRepository;
import tuti.desi.entidades.Familia;

@Service
public class FamiliaServiceImpl implements FamiliaService{

	@Autowired
    private FamiliaRepository familiaRepository;
	
	@Autowired
	private AsistidoService asistidoService;
    
	//Metodo de guardado/edicion
    @Override
    public Familia guardar(FamiliaDTO familiaDTO) {
        boolean esEdicion = familiaDTO.getNroFamilia() != null;
        Familia familia;

        if (esEdicion) {
            // Buscar la entidad existente
            familia = familiaRepository.findById(familiaDTO.getNroFamilia())
                .orElseThrow(() -> new IllegalArgumentException("No se encontró la familia con ID: " + familiaDTO.getNroFamilia()));

            // Si cambió el nombre, validar duplicado
            if (!familia.getNombre().equals(familiaDTO.getNombre()) &&
                familiaRepository.existsByNombre(familiaDTO.getNombre())) {
                throw new IllegalArgumentException("Ya existe otra familia con ese nombre");
            }

        } else {
            // Alta nueva
            if (familiaRepository.existsByNombre(familiaDTO.getNombre())) {
                throw new IllegalArgumentException("Ya existe una familia con ese nombre");
            }

            familia = new Familia();
        }

        familia.setNombre(familiaDTO.getNombre());
        familia.setActiva(familiaDTO.isActiva());
        familia.setFechaRegistro(familiaDTO.getFechaRegistro());

        return familiaRepository.save(familia);
    }
    
    //Metodo de obtencion de todas las familias
  	@Override
  	public List<FamiliaDTO> listarTodas() {
  	    List<Familia> familias = familiaRepository.findAll();
  	    return familias.stream()
  	        .map(f -> new FamiliaDTO(
  	            f.getId(),
  	            f.getNombre(),
  	            f.getFechaRegistro(),
  	            f.isActiva()
  	        ))
  	        .collect(Collectors.toList());
  	}
    
    //Metodo para buscar un objeto familia por ID y devolverlo como DTO con sus integrantes
    @Override
    public FamiliaDTO buscarPorId(Long id) {
        return familiaRepository.findById(id)
            .map(familia -> {
                FamiliaDTO familiaDTO = new FamiliaDTO(
                    familia.getId(),
                    familia.getNombre(),
                    familia.getFechaRegistro(),
                    familia.isActiva()
                );

                // Mapear los integrantes si existen
                if (familia.getIntegrantes() != null) {
                	familiaDTO.setIntegrantes(
                        familia.getIntegrantes().stream()
                            .map(asistido -> new AsistidoDTO(
                                asistido.getId(),
                                asistido.isActiva(),
                                asistido.getNombre(),
                                asistido.getApellido(),
                                asistido.getDni(),
                                asistido.getFechaNacimiento(),
                                asistido.getDomicilio(),
                                asistido.getOcupacion(),
                                asistido.getFamilia() != null ? asistido.getFamilia().getId() : null,
                           	    asistido.getFechaRegistroAsistido()
                            ))
                            .collect(Collectors.toList())
                    );
                }

                return familiaDTO;
            })
            .orElseThrow(() -> new IllegalArgumentException("No se encontró una familia con ID: " + id));
    }

    //Cambiar el estado a habilitado
	@Override
	public void habilitar(Long nroFamilia) {
	    familiaRepository.findById(nroFamilia).ifPresent(familia -> {
	        familia.setActiva(true);
	        familiaRepository.save(familia);
	    });
	}
	
	//Cambiar el estado a habilitado
    @Override
    public void inhabilitar(Long nroFamilia) {
        familiaRepository.findById(nroFamilia).ifPresent(familia -> {
            familia.setActiva(false);
            familiaRepository.save(familia);
        });
    }
    
	//Metodo para transformar 
	@Override
	public FamiliaDTO familiaADTO(Familia familia) {
	    if (familia == null) return null;

	    FamiliaDTO familiaDTO = new FamiliaDTO();
	    familiaDTO.setNroFamilia(familia.getId());
	    familiaDTO.setNombre(familia.getNombre());
	    familiaDTO.setFechaRegistro(familia.getFechaRegistro());

	    // Si tiene integrantes (Asistido), los convertís también:
	    if (familia.getIntegrantes() != null) {
	        List<AsistidoDTO> integrantesDTO = familia.getIntegrantes()
	            .stream()
	            .map(asistidoService::asistidoADTO)
	            .collect(Collectors.toList());
	        familiaDTO.setIntegrantes(integrantesDTO);
	    }

	    return familiaDTO;
	}
	
	//Metodo general para buscador en blanco
	@Override
	public List<FamiliasConMiembrosActivosDTO> listadoFamiliasMiembrosActivos() {
	    return familiaRepository.listadoFamiliasConAsistidosActivos();
	}
	
	//Metodo para buscador por parametro nombre
	@Override
	public List<FamiliasConMiembrosActivosDTO> listadoFamiliasMiembrosActivosFiltroNombre(String nombre) {
	return familiaRepository.listadoFamiliasConAsistidosActivosPorNombre(nombre);
	}
	
	//Metodo para buscador por parametro id
	@Override
	public List<FamiliasConMiembrosActivosDTO> listadoFamiliasMiembrosActivosFiltroId(Long id) {
	return familiaRepository.listadoFamiliasConAsistidosActivosPorId(id);
	}
	
	
	@Override
	public List<FamiliasConMiembrosDTO> listadoFamiliasConAsistidosTotales() {
		return familiaRepository.listadoFamiliasConAsistidos();
	}
	
	//EN DESUSO - Metodo para buscador a traves de familiaListar
	@Override
	public List<FamiliaDTO> filtrarId(Long id) {
	    return familiaRepository.findById(id)
	            .map(f -> List.of(familiaADTO(f)))
	            .orElse(List.of());
	}
	
	//EN DESUSO - Metodo para buscador a traves de familiaListar
	@Override
	public List<FamiliaDTO> filtrarNombre(String nombre) {
	    List<Familia> familias = familiaRepository.findByNombreLike("%" + nombre + "%");
	    return familias.stream()
	                   .map(this::familiaADTO)
	                   .collect(Collectors.toList());
	}

	
	//EN DESUSO - Metodo para buscador a traves de familiaListar/activas
	@Override
	public List<FamiliaDTO> filtrarIdActivas(Long id) {
		List<Familia> familias = familiaRepository.findByIdAndActivaTrue(id);
		return familias.stream()
                .map(this::familiaADTO)
                .collect(Collectors.toList());
	}

	//EN DESUSO - Metodo para buscador a traves de familiaListar/activas
	@Override
	public List<FamiliaDTO> filtrarNombreAndActivaTrue(String nombre) {
		List<Familia> familias = familiaRepository.findByNombreLikeAndActivaTrue("%" + nombre + "%");
		return familias.stream()
                .map(this::familiaADTO)
                .collect(Collectors.toList());
	}
	
    //EN DESUSO - Metodo de obtencion de todas las familias activas
    public List<FamiliaDTO> listarFamiliasActivas() {
    	List<Familia> familias = familiaRepository.findByActivaTrue();
        return familias.stream()
    	        .map(f -> new FamiliaDTO(
    	            f.getId(),
    	            f.getNombre(),
    	            f.getFechaRegistro(),
    	            f.isActiva()
    	        ))
    	        .collect(Collectors.toList());
	}


	
}

