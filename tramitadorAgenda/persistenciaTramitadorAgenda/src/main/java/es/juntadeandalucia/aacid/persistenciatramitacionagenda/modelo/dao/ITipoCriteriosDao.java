package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao;

import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.TiposCriterios;

public interface ITipoCriteriosDao {

  Long obtenerIdTipoCriterioByConvocatoriaFinalidadNombre(String finalidad, String nombre) throws TramitacionException;

  TiposCriterios obtenerTipoCriterioByNombreYFinalidad(String nombreTipoCriterio, String codFinalidad);

  String obtenerNombreTipoCriterioByidCriterio (Long idCriterio) throws TramitacionException;

}
