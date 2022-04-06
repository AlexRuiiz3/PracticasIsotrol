package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao;

import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.TipoCatalogo;

public interface ITipoCatalogoDao {

	List<TipoCatalogo> obtenerTiposCatalogoPorAnio (Integer anio, String tipoConvocatoria) throws TramitacionException;
}
