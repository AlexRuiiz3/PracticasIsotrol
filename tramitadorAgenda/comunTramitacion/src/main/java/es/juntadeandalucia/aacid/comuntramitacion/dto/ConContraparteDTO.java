package es.juntadeandalucia.aacid.comuntramitacion.dto;

import java.math.BigDecimal;

public class ConContraparteDTO {
  private Long idConContra;
  private BigDecimal contribucion;
  private String localidad;
  private String pais;
  private BigDecimal contribucionValidada;
  private BigDecimal contribucionNoValidada;
  private BigDecimal contraparteEmergencia;
  private ContribucionDTO aaciContribucionesByFkContribucion;
  private ContraparteDTO aaciContrapartesByFkEntidad;

  /**
   * Obtiene la propiedad idConContra
   *
   * @return el idConContra
   */
  public Long getIdConContra() {
    return idConContra;
  }

  /**
   * Establece el valor de la propiedad idConContra
   *
   * @param idConContra
   *          el idConContra para establecer
   */
  public void setIdConContra(final Long idConContra) {
    this.idConContra = idConContra;
  }

  /**
   * Obtiene la propiedad contribucion
   *
   * @return el contribucion
   */
  public BigDecimal getContribucion() {
    return contribucion;
  }

  /**
   * Establece el valor de la propiedad contribucion
   *
   * @param contribucion
   *          el contribucion para establecer
   */
  public void setContribucion(final BigDecimal contribucion) {
    this.contribucion = contribucion;
  }

  /**
   * Obtiene la propiedad localidad
   *
   * @return el localidad
   */
  public String getLocalidad() {
    return localidad;
  }

  /**
   * Establece el valor de la propiedad localidad
   *
   * @param localidad
   *          el localidad para establecer
   */
  public void setLocalidad(final String localidad) {
    this.localidad = localidad;
  }

  /**
   * Obtiene la propiedad pais
   *
   * @return el pais
   */
  public String getPais() {
    return pais;
  }

  /**
   * Establece el valor de la propiedad pais
   *
   * @param pais
   *          el pais para establecer
   */
  public void setPais(final String pais) {
    this.pais = pais;
  }

  /**
   * Obtiene la propiedad contribucionValidada
   *
   * @return el contribucionValidada
   */
  public BigDecimal getContribucionValidada() {
    return contribucionValidada;
  }

  /**
   * Establece el valor de la propiedad contribucionValidada
   *
   * @param contribucionValidada
   *          el contribucionValidada para establecer
   */
  public void setContribucionValidada(final BigDecimal contribucionValidada) {
    this.contribucionValidada = contribucionValidada;
  }

  /**
   * Obtiene la propiedad contribucionNoValidada
   *
   * @return el contribucionNoValidada
   */
  public BigDecimal getContribucionNoValidada() {
    return contribucionNoValidada;
  }

  /**
   * Establece el valor de la propiedad contribucionNoValidada
   *
   * @param contribucionNoValidada
   *          el contribucionNoValidada para establecer
   */
  public void setContribucionNoValidada(final BigDecimal contribucionNoValidada) {
    this.contribucionNoValidada = contribucionNoValidada;
  }

  /**
   * Obtiene la propiedad contraparteEmergencia
   *
   * @return el contraparteEmergencia
   */
  public BigDecimal getContraparteEmergencia() {
    return contraparteEmergencia;
  }

  /**
   * Establece el valor de la propiedad contraparteEmergencia
   *
   * @param contraparteEmergencia
   *          el contraparteEmergencia para establecer
   */
  public void setContraparteEmergencia(final BigDecimal contraparteEmergencia) {
    this.contraparteEmergencia = contraparteEmergencia;
  }

  /**
   * Obtiene la propiedad aaciContribucionesByFkContribucion
   *
   * @return el aaciContribucionesByFkContribucion
   */
  public ContribucionDTO getAaciContribucionesByFkContribucion() {
    return aaciContribucionesByFkContribucion;
  }

  /**
   * Establece el valor de la propiedad aaciContribucionesByFkContribucion
   *
   * @param aaciContribucionesByFkContribucion
   *          el aaciContribucionesByFkContribucion para establecer
   */
  public void setAaciContribucionesByFkContribucion(final ContribucionDTO aaciContribucionesByFkContribucion) {
    this.aaciContribucionesByFkContribucion = aaciContribucionesByFkContribucion;
  }

  /**
   * Obtiene la propiedad aaciContrapartesByFkEntidad
   *
   * @return el aaciContrapartesByFkEntidad
   */
  public ContraparteDTO getAaciContrapartesByFkEntidad() {
    return aaciContrapartesByFkEntidad;
  }

  /**
   * Establece el valor de la propiedad aaciContrapartesByFkEntidad
   *
   * @param aaciContrapartesByFkEntidad
   *          el aaciContrapartesByFkEntidad para establecer
   */
  public void setAaciContrapartesByFkEntidad(final ContraparteDTO aaciContrapartesByFkEntidad) {
    this.aaciContrapartesByFkEntidad = aaciContrapartesByFkEntidad;
  }

}
