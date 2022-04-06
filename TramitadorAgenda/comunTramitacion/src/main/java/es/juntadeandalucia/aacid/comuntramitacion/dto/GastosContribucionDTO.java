package es.juntadeandalucia.aacid.comuntramitacion.dto;

import java.math.BigDecimal;

public class GastosContribucionDTO {

  private Long idGastoContribucion;
  private GastoDTO gasto;
  private BigDecimal valor;
  private String valorTitle;
  private BigDecimal valorValidado;
  private String valorValidadoTitle;
  private BigDecimal valorNoValidado;
  private String valorNoValidadoTitle;
  private boolean disabled;

  public GastosContribucionDTO() {

  }

  public GastosContribucionDTO(final Long idGastoContribucion, final GastoDTO gasto, final BigDecimal valor, final String valorTitle,
      final String valorNoValidadoTitle, final BigDecimal valorNoValidado, final BigDecimal valorValidado, final boolean disabled) {
    this.idGastoContribucion = idGastoContribucion;
    this.gasto = gasto;
    this.valor = valor;
    this.valorTitle = valorTitle;
    this.valorNoValidadoTitle = valorNoValidadoTitle;
    this.valorNoValidado = valorNoValidado;
    this.valorValidado = valorValidado;
    this.disabled = disabled;
  }

  public String getValorNoValidadoTitle() {
    return valorNoValidadoTitle;
  }

  public void setValorNoValidadoTitle(final String valorNoValidadoTitle) {
    this.valorNoValidadoTitle = valorNoValidadoTitle;
  }

  public BigDecimal getValorNoValidado() {
    return valorNoValidado;
  }

  public void setValorNoValidado(final BigDecimal valorNoValidado) {
    this.valorNoValidado = valorNoValidado;
  }

  public BigDecimal getValorValidado() {
    return valorValidado;
  }

  public void setValorValidado(final BigDecimal valorValidado) {
    this.valorValidado = valorValidado;
  }

  public BigDecimal getValor() {
    return valor;
  }

  public void setValor(final BigDecimal valor) {
    this.valor = valor;
  }

  public String getValorTitle() {
    return valorTitle;
  }

  public void setValorTitle(final String valorTitle) {
    this.valorTitle = valorTitle;
  }

  public boolean isDisabled() {
    return disabled;
  }

  public void setDisabled(final boolean disabled) {
    this.disabled = disabled;
  }

  public GastoDTO getGasto() {
    return gasto;
  }

  public void setGasto(final GastoDTO gasto) {
    this.gasto = gasto;
  }

  /**
   * Obtiene la propiedad idGastoContribucion
   *
   * @return el idGastoContribucion
   */
  public Long getIdGastoContribucion() {
    return idGastoContribucion;
  }

  /**
   * Establece el valor de la propiedad idGastoContribucion
   *
   * @param idGastoContribucion
   *          el idGastoContribucion para establecer
   */
  public void setIdGastoContribucion(final Long idGastoContribucion) {
    this.idGastoContribucion = idGastoContribucion;
  }

  /**
   * Obtiene la propiedad valorValidadoTitle
   *
   * @return el valorValidadoTitle
   */
  public String getValorValidadoTitle() {
    return valorValidadoTitle;
  }

  /**
   * Establece el valor de la propiedad valorValidadoTitle
   *
   * @param valorValidadoTitle
   *          el valorValidadoTitle para establecer
   */
  public void setValorValidadoTitle(final String valorValidadoTitle) {
    this.valorValidadoTitle = valorValidadoTitle;
  }
}