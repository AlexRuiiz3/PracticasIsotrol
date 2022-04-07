package es.juntadeandalucia.aacid.tramitacionuniv.service;

import java.math.BigDecimal;
import java.text.ParseException;

import es.juntadeandalucia.aacid.comuntramitacion.dto.vea.EntregaVeaType;
import es.juntadeandalucia.aacid.comuntramitacion.exception.AlegacionesAceptaReformulaException;
import es.juntadeandalucia.aacid.comuntramitacion.exception.ParseCamposVeaException;

public interface IAnexoIVService {

  /**
   * Procesamiento específico para guardar el Anexo IV en el sistema.
   * 
   * @param idExp
   *          Identificador del expediente
   * @param idSolicitud
   *          Identificador de la solicitud
   * @param ultimaEntrega
   *          Último Anexo IV
   * @throws ClassNotFoundException
   *           Excepción
   * @throws ParseException
   *           Excepción
   * @throws IllegalAccessException
   *           Excepción
   * @throws NoSuchFieldException
   *           Excepción
   * @throws ParseCamposVeaException
   *           Excepción
   * @throws AlegacionesAceptaReformulaException
   *           Excepción
   */
  public void procesamientoEspecifico(BigDecimal idExp, Long idSolicitud, EntregaVeaType ultimaEntrega)
      throws ClassNotFoundException, ParseException, IllegalAccessException, NoSuchFieldException, ParseCamposVeaException, AlegacionesAceptaReformulaException;

}
