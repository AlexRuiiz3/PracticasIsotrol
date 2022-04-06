package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import es.juntadeandalucia.aacid.comuntramitacion.dto.ValoracionCriterioDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.ValoracionSolicitudDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.ValoracionTipoCriterioDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;

public interface ISolicitudCriteriosService {

  /**
   * Guarda la valroacion de la solicitud
   *
   * @param listaValoracionesEditadas
   * @param valoracionSolicitudDTO
   * @throws TramitacionException
   */
  void guardarValoracionSolicitud(List<ValoracionCriterioDTO> listaValoracionesEditadas, ValoracionSolicitudDTO valoracionSolicitudDTO)
      throws TramitacionException;

  /**
   * Obtenemos la valoración de cada criterio para la solicitud
   *
   * @param nExp
   * @param valoraciones
   */
  void obtenerValoracionCriterios(Long nExp, ValoracionTipoCriterioDTO valoraciones);

  /**
   * validar valoración
   *
   * @param listaValoracionesEditadas
   * @param valoracionSolicitudDTO
   * @param esONGD
   * @return
   */
  Collection<String> validarValoracionSolicitud(List<ValoracionCriterioDTO> listaValoracionesEditadas, ValoracionSolicitudDTO valoracionSolicitudDTO,
      boolean esONGD) throws TramitacionException;

  /**
   * Obtenemos los datos para el informe de valoración
   *
   * @param idExpTrewa
   * @param idSolicitud
   * @param valoracionSolicitudDTO
   * @param anyo
   * @return
   * @throws TramitacionException
   */
  Map<String, Object> obtenerDatosInformeValoracion(Long idExpTrewa, Long idSolicitud, ValoracionSolicitudDTO valoracionSolicitudDTO, Long anyo, boolean filtrarSubtipo)
      throws TramitacionException;

  BigDecimal obtenerPuntuacionTotalSolicitud(Long idSolicitud);

}
