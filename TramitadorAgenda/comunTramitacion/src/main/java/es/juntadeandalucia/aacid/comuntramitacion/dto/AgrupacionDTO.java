package es.juntadeandalucia.aacid.comuntramitacion.dto;

import java.io.Serializable;

/**
 * Agrupacion
 *
 * @author Isotrol
 */
public class AgrupacionDTO implements Serializable {

  /** Serial id */
  private static final long serialVersionUID = 4896822284751542365L;

  private Long id;
  private String participacion;
  private Double total;

  /** Campos para Subsanación */
  private String entParticipante;
  private String siglas;
  private String racda;
  private String cif;

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
   * Obtiene la propiedad participacion
   *
   * @return el participacion
   */
  public String getParticipacion() {
    return participacion;
  }

  /**
   * Establece el valor de la propiedad participacion
   *
   * @param participacion
   *          el participacion para establecer
   */
  public void setParticipacion(final String participacion) {
    this.participacion = participacion;
  }

  /**
   * Obtiene la propiedad total
   *
   * @return el total
   */
  public Double getTotal() {
    return total;
  }

  /**
   * Establece el valor de la propiedad total
   *
   * @param total
   *          el total para establecer
   */
  public void setTotal(final Double total) {
    this.total = total;
  }

  /**
   * Obtiene la propiedad entParticipante
   *
   * @return el entParticipante
   */
  public String getEntParticipante() {
    return entParticipante;
  }

  /**
   * Establece el valor de la propiedad entParticipante
   *
   * @param entParticipante
   *          el entParticipante para establecer
   */
  public void setEntParticipante(final String entParticipante) {
    this.entParticipante = entParticipante;
  }

  /**
   * Obtiene la propiedad siglas
   *
   * @return el siglas
   */
  public String getSiglas() {
    return siglas;
  }

  /**
   * Establece el valor de la propiedad siglas
   *
   * @param siglas
   *          el siglas para establecer
   */
  public void setSiglas(final String siglas) {
    this.siglas = siglas;
  }

  /**
   * Obtiene la propiedad racda
   *
   * @return el racda
   */
  public String getRacda() {
    return racda;
  }

  /**
   * Establece el valor de la propiedad racda
   *
   * @param racda
   *          el racda para establecer
   */
  public void setRacda(final String racda) {
    this.racda = racda;
  }

  /**
   * Obtiene la propiedad cif
   *
   * @return el cif
   */
  public String getCif() {
    return cif;
  }

  /**
   * Establece el valor de la propiedad cif
   *
   * @param cif
   *          el cif para establecer
   */
  public void setCif(final String cif) {
    this.cif = cif;
  }

}
