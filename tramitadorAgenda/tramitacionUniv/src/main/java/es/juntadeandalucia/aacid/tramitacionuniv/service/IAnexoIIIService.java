package es.juntadeandalucia.aacid.tramitacionuniv.service;

import java.math.BigDecimal;
import java.text.ParseException;

import es.juntadeandalucia.aacid.comuntramitacion.dto.vea.EntregaVeaType;
import es.juntadeandalucia.aacid.comuntramitacion.exception.AlegacionesException;
import es.juntadeandalucia.aacid.comuntramitacion.exception.ParseCamposVeaException;

public interface IAnexoIIIService {
  /**
   * Procesamiento especifico para guardar el Anexo III en el sistema.
   * 
   * @param idExp
   *          Identificador del expediente
   * @param idSolicitud
   *          Identificador de la solicitud
   * @param ultimaEntrega
   *          Ultimo Anexo III
   * @throws ClassNotFoundException
   *           Excepcion
   * @throws ParseException
   *           Excepcion
   * @throws IllegalAccessException
   *           Excepcion
   * @throws NoSuchFieldException
   *           Excepcion
   * @throws ParseCamposVeaException
   *           Excepcion
   * @throws AlegacionesException
   *           Excepcion
   */
  public void procesamientoEspecifico(BigDecimal idExp, Long idSolicitud, EntregaVeaType ultimaEntrega)
      throws ClassNotFoundException, ParseException, IllegalAccessException, NoSuchFieldException, ParseCamposVeaException, AlegacionesException;
}
