package tuti.desi.servicios;

import tuti.desi.DTO.ItemRecetaDTO;


public interface ItemRecetaService {

	ItemRecetaDTO guardarEdicion(ItemRecetaDTO itemRecetaDTO);

	ItemRecetaDTO buscarPorId(Long id);

	void deshabilitar(ItemRecetaDTO itemRecetaDTO);

	void habilitar(ItemRecetaDTO itemRecetaDTO);
	

}
