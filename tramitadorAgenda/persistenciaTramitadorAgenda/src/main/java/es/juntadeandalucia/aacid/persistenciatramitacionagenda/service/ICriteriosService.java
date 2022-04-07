package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service;

import es.juntadeandalucia.aacid.comuntramitacion.dto.ValoracionTipoCriterioDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;

public interface ICriteriosService {

  /**
   * Obtener listado de criterios por el tipo de criterio, la finalidad asociada y la convocatoria a la que pertenece
   * 
   * @param finalidad
   * @param tipoCriterio
   * @param isUniv
   * @return
   * @throws TramitacionException
   */
  ValoracionTipoCriterioDTO obtenerListaCriteriosByFinalidadAndConvocatoria(final String finalidad, final String tipoCriterio, final boolean isUniv, Long anyo,
      boolean comprobarSubFinalidad) throws TramitacionException;

}
