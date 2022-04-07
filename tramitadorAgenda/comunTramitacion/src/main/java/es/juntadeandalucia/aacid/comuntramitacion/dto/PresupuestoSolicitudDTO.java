package es.juntadeandalucia.aacid.comuntramitacion.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * PresupuestoSolicitudDTO
 *
 * @author Isotrol
 */
public class PresupuestoSolicitudDTO implements Serializable {

  /** Serial id */
  private static final long serialVersionUID = 4896822284751542365L;

  private Long idPresupuesto;
  private transient GastoDTO gasto;
  private String tipoUnidad;
  private Long numUnidades;
  private BigDecimal costeUnitario;
  private BigDecimal costeTotal;
  private BigDecimal contribucion;
  private String concepto;
  private transient EntidadParticipanteDTO entidadFinanciadora;

  /**
   * Obtiene la propiedad idPresupuesto
   *
   * @return el idPresupuesto
   */
  public Long getIdPresupuesto() {
    return idPresupuesto;
  }

  /**
   * Establece el valor de la propiedad idPresupuesto
   *
   * @param idPresupuesto
   *          el idPresupuesto para establecer
   */
  public void setIdPresupuesto(final Long idPresupuesto) {
    this.idPresupuesto = idPresupuesto;
  }

  /**
   * Obtiene la propiedad gasto
   *
   * @return el gasto
   */
  public GastoDTO getGasto() {
    return gasto;
  }

  /**
   * Establece el valor de la propiedad gasto
   *
   * @param gasto
   *          el gasto para establecer
   */
  public void setGasto(final GastoDTO gasto) {
    this.gasto = gasto;
  }

  /**
   * Obtiene la propiedad tipoUnidad
   *
   * @return el tipoUnidad
   */
  public String getTipoUnidad() {
    return tipoUnidad;
  }

  /**
   * Establece el valor de la propiedad tipoUnidad
   *
   * @param tipoUnidad
   *          el tipoUnidad para establecer
   */
  public void setTipoUnidad(final String tipoUnidad) {
    this.tipoUnidad = tipoUnidad;
  }

  /**
   * Obtiene la propiedad numUnidades
   *
   * @return el numUnidades
   */
  public Long getNumUnidades() {
    return numUnidades;
  }

  /**
   * Establece el valor de la propiedad numUnidades
   *
   * @param numUnidades
   *          el numUnidades para establecer
   */
  public void setNumUnidades(final Long numUnidades) {
    this.numUnidades = numUnidades;
  }

  /**
   * Obtiene la propiedad costeUnitario
   *
   * @return el costeUnitario
   */
  public BigDecimal getCosteUnitario() {
    return costeUnitario;
  }

  /**
   * Establece el valor de la propiedad costeUnitario
   *
   * @param costeUnitario
   *          el costeUnitario para establecer
   */
  public void setCosteUnitario(final BigDecimal costeUnitario) {
    this.costeUnitario = costeUnitario;
  }

  /**
   * Obtiene la propiedad costeTotal
   *
   * @return el costeTotal
   */
  public BigDecimal getCosteTotal() {
    return costeTotal;
  }

  /**
   * Establece el valor de la propiedad costeTotal
   *
   * @param costeTotal
   *          el costeTotal para establecer
   */
  public void setCosteTotal(final BigDecimal costeTotal) {
    this.costeTotal = costeTotal;
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
   * Obtiene la propiedad concepto
   *
   * @return el concepto
   */
  public String getConcepto() {
    return concepto;
  }

  /**
   * Establece el valor de la propiedad concepto
   *
   * @param concepto
   *          el concepto para establecer
   */
  public void setConcepto(final String concepto) {
    this.concepto = concepto;
  }

  /**
   * Obtiene la propiedad entidadFinanciadora
   *
   * @return el entidadFinanciadora
   */
  public EntidadParticipanteDTO getEntidadFinanciadora() {
    return entidadFinanciadora;
  }

  /**
   * Establece el valor de la propiedad entidadFinanciadora
   *
   * @param entidadFinanciadora
   *          el entidadFinanciadora para establecer
   */
  public void setEntidadFinanciadora(final EntidadParticipanteDTO entidadFinanciadora) {
    this.entidadFinanciadora = entidadFinanciadora;
  }

}
