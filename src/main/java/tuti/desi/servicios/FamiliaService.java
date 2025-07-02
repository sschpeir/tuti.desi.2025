package tuti.desi.servicios;

import java.util.List;

import tuti.desi.DTO.FamiliaDTO;
import tuti.desi.DTO.FamiliasConMiembrosActivosDTO;
import tuti.desi.DTO.FamiliasConMiembrosDTO;
import tuti.desi.entidades.Familia;

public interface FamiliaService {
	
	//Metodo de guardado/edicion
	Familia guardar(FamiliaDTO familiaDTO);
	
	//Metodo de obtencion de todas las familias
	List<FamiliaDTO> listarTodas();
	
	//Metodo para buscar un objeto familia por ID y devuelve DTO con sus integrantes
	FamiliaDTO buscarPorId(Long id);

	//Inhabilita la familia
	void inhabilitar(Long nroFamilia);
	
	//Habilita la familia
	void habilitar(Long nroFamilia);

	//Transforma una entity a DTO,
	FamiliaDTO familiaADTO(Familia familia);
	
	//Funcion para obtener el listado solicitado de familias activas con miembros activos
	List<FamiliasConMiembrosActivosDTO> listadoFamiliasMiembrosActivos();

	//Funcion para obtener el listado solicitado de familias activas con miembros activos para filtro por nombre
	List<FamiliasConMiembrosActivosDTO> listadoFamiliasMiembrosActivosFiltroNombre(String nombre);

	//Funcion para obtener el listado solicitado de familias activas con miembros activos para filtro por id
	List<FamiliasConMiembrosActivosDTO> listadoFamiliasMiembrosActivosFiltroId(Long id);

	//Listado de familias con miembros totales y activos para familiarListar
	List<FamiliasConMiembrosDTO> listadoFamiliasConAsistidosTotales();
	
	//EN DESUSO - Metodo de obtencion de todas las familias activas
	//List<FamiliaDTO> listarFamiliasActivas();
	
	//EN DESUSO - Busca un listado de nombres por id
	//List<FamiliaDTO> filtrarId(Long id);
	
	//EN DESUSO - Busca un listado de nombres con la expresion "like"
	//List<FamiliaDTO> filtrarNombre(String valor);

	//EN DESUSO - Busca un listado de nombres por id con la expresion activa
	//List<FamiliaDTO> filtrarIdActivas(Long id);

	//EN DESUSO - Busca un listado de nombres con la expresion "like" y activa = true
	//List<FamiliaDTO> filtrarNombreAndActivaTrue(String nombre);

}
