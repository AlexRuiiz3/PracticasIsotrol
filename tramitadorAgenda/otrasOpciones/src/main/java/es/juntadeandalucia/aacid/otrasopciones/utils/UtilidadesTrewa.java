package es.juntadeandalucia.aacid.otrasopciones.utils;

import trewa.bd.trapi.trapiui.tpo.TrDatosContacto;
import trewa.bd.trapi.trapiui.tpo.TrUsuarioAsignado;

/**
 * ConvocatoriasUtils class.
 * 
 * @author isotrol
 *
 */
public final class UtilidadesTrewa {

  private UtilidadesTrewa() {
    super();
  }

  /**
   * @param usuariosAsignadosExpediente
   * @return obtiene nombre y apellidos del usuario asignado
   */
  public static String obtenerNombreApellidoTrUsuarioAsignado(TrUsuarioAsignado usuarioAsignado) {
    return usuarioAsignado.getUSUARIO().getNOMBRE() + " " + usuarioAsignado.getUSUARIO().getAPELLIDO1() + " " + usuarioAsignado.getUSUARIO().getAPELLIDO2();
  }

  public static String construirDireccion(TrDatosContacto datosContacto) {
    return datosContacto.getTIPOVIA().getCODTIPOVIA() + " " + datosContacto.getNOMBREVIA() + " "
        + (datosContacto.getNUMERO() != null ? datosContacto.getNUMERO() : "") + " " + (datosContacto.getLETRA() != null ? datosContacto.getLETRA() : "") + " "
        + (datosContacto.getESCALERA() != null ? datosContacto.getESCALERA() : "\b") + " "
        + (datosContacto.getPISO() != null ? datosContacto.getPISO() + "º" : "") + " " + (datosContacto.getPUERTA() != null ? datosContacto.getPUERTA() : "")
        + (datosContacto.getCODPOSTAL() != null ? datosContacto.getCODPOSTAL() : "") + " "
        + (datosContacto.getMUNICIPIO().getPROVINCIA().getNOMBRE() != null ? datosContacto.getMUNICIPIO().getPROVINCIA().getNOMBRE() : "");
  }
}
