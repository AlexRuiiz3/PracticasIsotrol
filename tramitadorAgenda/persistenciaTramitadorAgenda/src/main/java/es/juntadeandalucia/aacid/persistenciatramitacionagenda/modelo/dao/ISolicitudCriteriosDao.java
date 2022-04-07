package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao;

import java.math.BigDecimal;

import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudCriterioDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;

public interface ISolicitudCriteriosDao {

  /**
   * Obtener valoración del criterio
   *
   * @param nExp
   * @param idCriterio
   * @return
   * @throws TramitacionException
   */
  SolicitudCriterioDTO obtenerValoracionCriterios(Long nExp, Long idCriterio);

  /**
   * Obtenemos valoración a partir del idSolicitud y criterio
   *
   * @param idSolicitud
   * @param idCriterio
   * @return
   */
  SolicitudCriterioDTO obtenerValoracionCriteriosByIdSolIdCrit(long idSolicitud, Long idCriterio);

  /**
   * Guarda la valoración de un criterio
   *
   * @param solCrit
   * @return
   * @throws TramitacionException
   */
  int guardarValoracionCriterio(SolicitudCriterioDTO solCrit) throws TramitacionException;

  /**
   *    Obtenemos valoracion a partir del idSolicitud y criterio
   *
   * @param solCrit
   * @return
   * @throws TramitacionException
   */
SolicitudCriterioDTO obtenerValoracionCriteriosValorMaximo(Long nExp, Long idCriterio);

BigDecimal obtenerPuntuacionTotalSolicitud(Long idSolicitud);

}
