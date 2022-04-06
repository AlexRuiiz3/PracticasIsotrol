package es.juntadeandalucia.aacid.tramitacionongd.services;

import java.math.BigDecimal;
import java.text.ParseException;

import es.juntadeandalucia.aacid.comuntramitacion.dto.vea.EntregaVeaType;
import es.juntadeandalucia.aacid.comuntramitacion.exception.AlegacionesAceptaReformulaException;
import es.juntadeandalucia.aacid.comuntramitacion.exception.ParseCamposVeaException;

public interface IAnexoVService {

  public void procesamientoEspecifico(BigDecimal idExp, Long idSolicitud, EntregaVeaType ultimaEntrega)
      throws ClassNotFoundException, ParseException, IllegalAccessException, NoSuchFieldException, ParseCamposVeaException, AlegacionesAceptaReformulaException;

}
