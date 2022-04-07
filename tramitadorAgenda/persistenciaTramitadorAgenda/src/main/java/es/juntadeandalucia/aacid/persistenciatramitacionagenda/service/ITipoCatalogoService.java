package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service;

import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.dto.TipoCatalogoDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;

public interface ITipoCatalogoService {
	List<TipoCatalogoDTO> obtenerCatalogosPorAnio (Integer anio, String tipoConvocatoria) throws TramitacionException;
}
