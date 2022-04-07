package es.juntadeandalucia.aacid.comuntramitacion.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * SolicitudAuxiliarDTO
 *
 * @author Isotrol
 */
public class SolicitudAuxiliarDTO implements Serializable {

  /** Serial id */
  private static final long serialVersionUID = 4896822284751542365L;

  private Long id;
  private String observaciones;
  private Long numReintegros;
  private Long causaReintegro;
  private Long magnitudReintegro;
  private BigDecimal puntuacionTotal;
  private Long idSolicitud;

  public SolicitudAuxiliarDTO(final Long id, final String observaciones, final Long numReintegros, final Long causaReintegro, final Long magnitudReintegro, final BigDecimal puntuacionTotal) {
    this.id = id;
    this.observaciones = observaciones;
    this.numReintegros = numReintegros;
    this.causaReintegro = causaReintegro;
    this.magnitudReintegro = magnitudReintegro;
    this.puntuacionTotal = puntuacionTotal;
  }

  /**
   * Obtiene la propiedad id
   *
   * @return el id
   */
  public Long getId() {
    return id;
  }

  /**
   * Establece el valor de la propiedad id
   *
   * @param id
   *          el id para establecer
   */
  public void setId(final Long id) {
    this.id = id;
  }

  /**
   * Obtiene la propiedad observaciones
   *
   * @return el observaciones
   */
  public String getObservaciones() {
    return observaciones;
  }

  /**
   * Establece el valor de la propiedad observaciones
   *
   * @param observaciones
   *          el observaciones para establecer
   */
  public void setObservaciones(final String observaciones) {
    this.observaciones = observaciones;
  }

  /**
   * Obtiene la propiedad numReintegros
   *
   * @return el numReintegros
   */
  public Long getNumReintegros() {
    return numReintegros;
  }

  /**
   * Establece el valor de la propiedad numReintegros
   *
   * @param numReintegros
   *          el numReintegros para establecer
   */
  public void setNumReintegros(final Long numReintegros) {
    this.numReintegros = numReintegros;
  }

  /**
   * Obtiene la propiedad causaReintegro
   *
   * @return el causaReintegro
   */
  public Long getCausaReintegro() {
    return causaReintegro;
  }

  /**
   * Establece el valor de la propiedad causaReintegro
   *
   * @param causaReintegro
   *          el causaReintegro para establecer
   */
  public void setCausaReintegro(final Long causaReintegro) {
    this.causaReintegro = causaReintegro;
  }

  /**
   * Obtiene la propiedad magnitudReintegro
   *
   * @return el magnitudReintegro
   */
  public Long getMagnitudReintegro() {
    return magnitudReintegro;
  }

  /**
   * Establece el valor de la propiedad magnitudReintegro
   *
   * @param magnitudReintegro
   *          el magnitudReintegro para establecer
   */
  public void setMagnitudReintegro(final Long magnitudReintegro) {
    this.magnitudReintegro = magnitudReintegro;
  }

  /**
   *
   */
  public SolicitudAuxiliarDTO() {
    // TODO Auto-generated constructor stub
  }

  /**
   * Obtiene la propiedad idSolicitud
   *
   * @return el idSolicitud
   */
  public Long getIdSolicitud() {
    return idSolicitud;
  }

  /**
   * Establece el valor de la propiedad idSolicitud
   *
   * @param idSolicitud
   *          el idSolicitud para establecer
   */
  public void setIdSolicitud(final Long idSolicitud) {
    this.idSolicitud = idSolicitud;
  }

  public BigDecimal getPuntuacionTotal() {
    return puntuacionTotal;
  }

  public void setPuntuacionTotal(final BigDecimal puntuacionTotal) {
    this.puntuacionTotal = puntuacionTotal;
  }
}
