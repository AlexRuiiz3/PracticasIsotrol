package es.juntadeandalucia.aacid.comuntramitacion.dto;

import java.io.Serializable;

/**
 * ListadoSubsanacionDTO class.
 * @author isotrol.
 *
 */
public class InformeSubsanacionDTO implements Serializable{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  /**codigosSubsanaciones */
  private String codigosSubsanacion;
  /** numExpTrewa */
  private String numExpTrewa;
  /** solicitud */
  private SolicitudDTO solicitud;
  /**
   * @return the codigosSubsanacion
   */
  public String getCodigosSubsanacion() {
    return codigosSubsanacion;
  }
  /**
   * @param codigosSubsanacion the codigosSubsanacion to set
   */
  public void setCodigosSubsanacion(String codigosSubsanacion) {
    this.codigosSubsanacion = codigosSubsanacion;
  }
  /**
   * @return the numExpTrewa
   */
  public String getNumExpTrewa() {
    return numExpTrewa;
  }
  /**
   * @param numExpTrewa the numExpTrewa to set
   */
  public void setNumExpTrewa(String numExpTrewa) {
    this.numExpTrewa = numExpTrewa;
  }
  /**
   * @return the solicitud
   */
  public SolicitudDTO getSolicitud() {
    return solicitud;
  }
  /**
   * @param solicitud the solicitud to set
   */
  public void setSolicitud(SolicitudDTO solicitud) {
    this.solicitud = solicitud;
  }
  /**
   * @return the serialversionuid
   */
  public static long getSerialversionuid() {
    return serialVersionUID;
  }
  @Override
  public String toString() {
    return "InformeSubsanacionDTO [codigosSubsanacion=" + codigosSubsanacion + ", numExpTrewa=" + numExpTrewa + ", solicitud=" + solicitud + "]";
  }

}
