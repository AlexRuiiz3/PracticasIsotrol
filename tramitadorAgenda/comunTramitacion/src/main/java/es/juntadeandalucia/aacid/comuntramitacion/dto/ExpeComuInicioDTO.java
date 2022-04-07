package es.juntadeandalucia.aacid.comuntramitacion.dto;

/**
 * expeComuInicioDTO class.
 *
 * @author isotrol
 *
 */
public class ExpeComuInicioDTO {

  private String numExpediente;
  private String numExpOV;
  private String fechaPagoSubvencion;
  private String fechaPostegracionComunInicio;
  private String fechaFinComuInicio;
  private String diasRestantes;

  /**
   *
   */
  public ExpeComuInicioDTO() {
    // constructor stub
  }

  /**
   * Obtiene la propiedad numExpediente
   *
   * @return el numExpediente
   */
  public String getNumExpediente() {
    return numExpediente;
  }

  /**
   * Establece el valor de la propiedad numExpediente
   *
   * @param numExpediente
   *          el numExpediente para establecer
   */
  public void setNumExpediente(final String numExpediente) {
    this.numExpediente = numExpediente;
  }

  /**
   * Obtiene la propiedad fechaPagoSubvencion
   *
   * @return el fechaPagoSubvencion
   */
  public String getFechaPagoSubvencion() {
    return fechaPagoSubvencion;
  }

  /**
   * Establece el valor de la propiedad fechaPagoSubvencion
   *
   * @param fechaPagoSubvencion
   *          el fechaPagoSubvencion para establecer
   */
  public void setFechaPagoSubvencion(final String fechaPagoSubvencion) {
    this.fechaPagoSubvencion = fechaPagoSubvencion;
  }

  /**
   * Obtiene la propiedad fechaFinComuInicio
   *
   * @return el fechaFinComuInicio
   */
  public String getFechaFinComuInicio() {
    return fechaFinComuInicio;
  }

  /**
   * Establece el valor de la propiedad fechaFinComuInicio
   *
   * @param fechaFinComuInicio
   *          el fechaFinComuInicio para establecer
   */
  public void setFechaFinComuInicio(final String fechaFinComuInicio) {
    this.fechaFinComuInicio = fechaFinComuInicio;
  }

  /**
   * Obtiene la propiedad diasRestantes
   *
   * @return el diasRestantes
   */
  public String getDiasRestantes() {
    return diasRestantes;
  }

  /**
   * Establece el valor de la propiedad diasRestantes
   *
   * @param diasRestantes
   *          el diasRestantes para establecer
   */
  public void setDiasRestantes(final String diasRestantes) {
    this.diasRestantes = diasRestantes;
  }

  /**
   * Obtiene la propiedad numExpOV
   *
   * @return el numExpOV
   */
  public String getNumExpOV() {
    return numExpOV;
  }

  /**
   * Establece el valor de la propiedad numExpOV
   *
   * @param numExpOV
   *          el numExpOV para establecer
   */
  public void setNumExpOV(final String numExpOV) {
    this.numExpOV = numExpOV;
  }

  /**
   * Obtiene la propiedad fechaPostegracionComunInicio
   *
   * @return el fechaPostegracionComunInicio
   */
  public String getFechaPostegracionComunInicio() {
    return fechaPostegracionComunInicio;
  }

  /**
   * Establece el valor de la propiedad fechaPostegracionComunInicio
   *
   * @param fechaPostegracionComunInicio
   *          el fechaPostegracionComunInicio para establecer
   */
  public void setFechaPostegracionComunInicio(final String fechaPostegracionComunInicio) {
    this.fechaPostegracionComunInicio = fechaPostegracionComunInicio;
  }
}