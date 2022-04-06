package es.juntadeandalucia.aacid.comuntramitacion.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * SolicitudCriterioDTO
 *
 * @author Isotrol
 */
public class SolicitudCriterioDTO implements Serializable {

  /** Serial id */
  private static final long serialVersionUID = 4896822284751542365L;

  private Long id;
  private BigDecimal valor;
  private BigDecimal puntuacion;
  private Long idSolicitud;
  private Long idCriterio;
  private BigDecimal valorMaximo;

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
   * Obtiene la propiedad valor
   *
   * @return el valor
   */
  public BigDecimal getValor() {
    return valor;
  }

  /**
   * Establece el valor de la propiedad valor
   *
   * @param valor
   *          el valor para establecer
   */
  public void setValor(final BigDecimal valor) {
    this.valor = valor;
  }

  /**
   * Obtiene la propiedad puntuacion
   *
   * @return el puntuacion
   */
  public BigDecimal getPuntuacion() {
    return puntuacion;
  }

  /**
   * Establece el valor de la propiedad puntuacion
   *
   * @param puntuacion
   *          el puntuacion para establecer
   */
  public void setPuntuacion(final BigDecimal puntuacion) {
    this.puntuacion = puntuacion;
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
   * Obtiene la propiedad idCriterio
   *
   * @return el idCriterio
   */
  public Long getIdCriterio() {
    return idCriterio;
  }

  /**
   * Establece el valor de la propiedad idCriterio
   *
   * @param idCriterio
   *          el idCriterio para establecer
   */
  public void setIdCriterio(final Long idCriterio) {
    this.idCriterio = idCriterio;
  }

  /**
   *
   */
  public SolicitudCriterioDTO() {
  }

  /**
   * @param id
   * @param valor
   * @param puntuacion
   */
  public SolicitudCriterioDTO(final Long id, final BigDecimal valor, final BigDecimal puntuacion) {
    this.id = id;
    this.valor = valor;
    this.puntuacion = puntuacion;
  }
  
  
  /**
   * @param id
   * @param valor
   * @param puntuacion
   * @param valorMaximo
   */
  public SolicitudCriterioDTO(final Long id, final BigDecimal valor,  final BigDecimal puntuacion,final BigDecimal valorMaximo) {
    this.id = id;
    this.valor = valor;
    this.puntuacion = puntuacion;
    this.valorMaximo=valorMaximo;
  }

public BigDecimal getValorMaximo() {
	return valorMaximo;
}

public void setValorMaximo(BigDecimal valorMaximo) {
	this.valorMaximo = valorMaximo;
}
}
