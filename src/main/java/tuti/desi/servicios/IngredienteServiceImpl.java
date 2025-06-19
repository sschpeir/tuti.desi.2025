package tuti.desi.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tuti.desi.DTO.IngredienteDTO;
import tuti.desi.DTO.ProductoDTO;
import tuti.desi.accesoDatos.IngredienteRepository;
import tuti.desi.entidades.Ingrediente;
import tuti.desi.entidades.Producto;




@Service
public class IngredienteServiceImpl implements IngredienteService{

	@Autowired
    private IngredienteRepository ingredienteRepository;

	@Override
	public Ingrediente guardar(IngredienteDTO ingredienteDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IngredienteDTO> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IngredienteDTO> listarIngredientesActivas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IngredienteDTO buscarPorId(Long id) {
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

	@Override
	public IngredienteDTO ingredienteADTO(Ingrediente ingrediente) {
		IngredienteDTO ingredienteDevuelto = new IngredienteDTO();
		Ingrediente ingredienteEnviado;
		if (ingredienteEnviado instanceof Producto) {
			ProductoDTO productoDTO = new ProductoDTO();
			productoDTO.setPrecioActual();
		}
		ingredienteDevuelto.setId(ingredienteEnviado.getId());
		ingredienteDevuelto.setNombre(ingredienteEnviado.getNombre());
		ingredienteDevuelto.setCalorias(ingredienteEnviado.getCalorias());
		ingredienteDevuelto
		ingredienteDevuelto
		return null;
	}

	
	
}
