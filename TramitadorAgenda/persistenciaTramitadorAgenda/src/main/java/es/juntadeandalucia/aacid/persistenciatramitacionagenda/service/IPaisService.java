package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service;

import es.juntadeandalucia.aacid.comuntramitacion.dto.ValoracionCriterioDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;

public interface IPaisService {

  /**
   * Obtener valoracion con la convergencia de paises
   *
   * @param idSolicitud
   * @param valoracionConvergencia
   * @throws TramitacionException
   */
  void obtenerValoracionConvergencia(Long idSolicitud, ValoracionCriterioDTO valoracionConvergencia) throws TramitacionException;
}
