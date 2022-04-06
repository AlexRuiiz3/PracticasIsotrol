package es.juntadeandalucia.aacid.comuntramitacion.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * SolicitudTipoCriterioDTO
 *
 * @author Isotrol
 */
public class SolicitudTipoCriterioDTO implements Serializable {

  /** Serial id */
  private static final long serialVersionUID = 4896822284751542365L;

  private Long id;
  private String observaciones;
  private BigDecimal puntuacionTotal;
  private Long idSolicitud;
  private Long idTipoCriterio;

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
   * Obtiene la propiedad puntuacionTotal
   *
   * @return el puntuacionTotal
   */
  public BigDecimal getPuntuacionTotal() {
    return puntuacionTotal;
  }

  /**
   * Establece el valor de la propiedad puntuacionTotal
   *
   * @param puntuacionTotal
   *          el puntuacionTotal para establecer
   */
  public void setPuntuacionTotal(final BigDecimal puntuacionTotal) {
    this.puntuacionTotal = puntuacionTotal;
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

  /**
   * Obtiene la propiedad idTipoCriterio
   *
   * @return el idTipoCriterio
   */
  public Long getIdTipoCriterio() {
    return idTipoCriterio;
  }

  /**
   * Establece el valor de la propiedad idTipoCriterio
   *
   * @param idTipoCriterio
   *          el idTipoCriterio para establecer
   */
  public void setIdTipoCriterio(final Long idTipoCriterio) {
    this.idTipoCriterio = idTipoCriterio;
  }

  /**
   *
   */
  public SolicitudTipoCriterioDTO() {
  }

  /**
   * @param id
   * @param observaciones
   * @param puntuacionTotal
   * @param idSolicitud
   * @param idTipoCriterio
   */
  public SolicitudTipoCriterioDTO(final Long id, final String observaciones, final BigDecimal puntuacionTotal) {
    this.id = id;
    this.observaciones = observaciones;
    this.puntuacionTotal = puntuacionTotal;
  }
}
