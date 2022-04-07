package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao;

import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudTipoCriterioDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.SolicitudTipoCriterio;

public interface ISolicitudTipoCriteriosDao {

  /**
   *
   * @param idSolicitud
   * @param nombreTipoCriterio
   * @return
   * @throws TramitacionException
   */
  SolicitudTipoCriterioDTO obtenerSolicitudTipoCriterioDTOByIdSolicitud(Long idSolicitud, String nombreTipoCriterio) throws TramitacionException;

  /**
   *
   * @param solicitudTipoCriterio
   * @return
   * @throws TramitacionException
   */
  int guardarValoracionTipoCriterio(SolicitudTipoCriterio solicitudTipoCriterio) throws TramitacionException;
}
