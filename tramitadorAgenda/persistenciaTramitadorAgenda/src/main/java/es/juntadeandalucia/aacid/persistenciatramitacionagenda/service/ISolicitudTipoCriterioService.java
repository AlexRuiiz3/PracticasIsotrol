package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service;

import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudTipoCriterioDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.ValoracionSolicitudDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.ValoracionTipoCriterioDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.SolicitudTipoCriterio;
import es.juntadeandalucia.plataforma.visibilidad.perfil.Perfil;

public interface ISolicitudTipoCriterioService {

  /**
   * Obtenemos los datos del tipo del criterio para esa solicitud
   *
   * @param idSolicitud
   * @param idTipoCriteriom
   * @param observaciones
   * @param totalPuntuacion
   * @return
   * @throws TramitacionException
   */
  SolicitudTipoCriterioDTO obtenerSolicitudTipoCriterioByIdSolicitud(Long idSolicitud, String nombreTipoCriterio) throws TramitacionException;

  /**
   * Guarda los campos relacionados con el tipo criterio y la solicitud
   *
   * @param pertinencia
   * @return
   * @throws TramitacionException
   */

  int guardarValoracionTipoCriterio(SolicitudTipoCriterio pertinencia) throws TramitacionException;

  /**
   * Realizaremos las comprobaciones pertinentes para poder mostrar las distinats pest�as.
   *
   * @param valoracionSolicitudDTO
   */
  void realizarComprobaciones(ValoracionSolicitudDTO valoracionSolicitudDTO, List<Perfil> perfiles);

  /**
   * Obtenemos los datos de un tipo de criterio.
   *
   * @param idSolicitud
   * @param nombreTipoCritero
   * @param valoracionTipoCriterioDTO
   */

  void obtenerDatosTipoCriterio(Long idSolicitud, String nombreTipoCritero, ValoracionTipoCriterioDTO valoracionTipoCriterioDTO) throws TramitacionException;
}
