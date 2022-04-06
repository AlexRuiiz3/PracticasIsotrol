package es.juntadeandalucia.aacid.comuntramitacion.dto;

/**
 *
 * @author isotrol
 *
 */
public class ValoracionSolicitudDTO {

  private Long idSolicitud;
  private boolean desdeTarea;
  private boolean isValorador;
  private boolean isCoordinador;
  private boolean valoracionIniciada;
  private boolean mostrarPestania3;
  private boolean permiteSumaIncrementos;
  private boolean ongd;
  private boolean generacionInformes;
  private String nifSolicitante;
  private String nombreSolicitante;
  private Long numReintegros;
  private Long causaReintegros;
  private Long magnitudReintegros;
  private String codigoFinalidad;
  private ValoracionTipoCriterioDTO pertinencia = new ValoracionTipoCriterioDTO();
  private ValoracionTipoCriterioDTO viabilidad = new ValoracionTipoCriterioDTO();
  private ValoracionTipoCriterioDTO coherencia = new ValoracionTipoCriterioDTO();
  private ValoracionTipoCriterioDTO sostenibilidad = new ValoracionTipoCriterioDTO();
  private ValoracionTipoCriterioDTO impacto = new ValoracionTipoCriterioDTO();
  private ValoracionTipoCriterioDTO conectividad = new ValoracionTipoCriterioDTO();
  private ValoracionTipoCriterioDTO convergencia = new ValoracionTipoCriterioDTO();
  private ValoracionTipoCriterioDTO capacidadGestion = new ValoracionTipoCriterioDTO();
  private ValoracionTipoCriterioDTO valoracionTotal = new ValoracionTipoCriterioDTO();
  private ValoracionTipoCriterioDTO incrementos = new ValoracionTipoCriterioDTO();

  private Long anio;

  /**
   * Obtiene la propiedad desdeTarea
   *
   * @return el desdeTarea
   */
  public boolean isDesdeTarea() {
    return desdeTarea;
  }

  /**
   * Establece el valor de la propiedad desdeTarea
   *
   * @param desdeTarea
   *          el desdeTarea para establecer
   */
  public void setDesdeTarea(final boolean desdeTarea) {
    this.desdeTarea = desdeTarea;
  }

  /**
   * Obtiene la propiedad isValorador
   *
   * @return el isValorador
   */
  public boolean isValorador() {
    return isValorador;
  }

  /**
   * Establece el valor de la propiedad isValorador
   *
   * @param isValorador
   *          el isValorador para establecer
   */
  public void setValorador(final boolean isValorador) {
    this.isValorador = isValorador;
  }

  /**
   * Obtiene la propiedad isCoordinador
   *
   * @return el isCoordinador
   */
  public boolean isCoordinador() {
    return isCoordinador;
  }

  /**
   * Establece el valor de la propiedad isCoordinador
   *
   * @param isCoordinador
   *          el isCoordinador para establecer
   */
  public void setCoordinador(final boolean isCoordinador) {
    this.isCoordinador = isCoordinador;
  }

  /**
   * Obtiene la propiedad nifSolicitante
   *
   * @return el nifSolicitante
   */
  public String getNifSolicitante() {
    return nifSolicitante;
  }

  /**
   * Establece el valor de la propiedad nifSolicitante
   *
   * @param nifSolicitante
   *          el nifSolicitante para establecer
   */
  public void setNifSolicitante(final String nifSolicitante) {
    this.nifSolicitante = nifSolicitante;
  }

  /**
   * Obtiene la propiedad nombreSolicitante
   *
   * @return el nombreSolicitante
   */
  public String getNombreSolicitante() {
    return nombreSolicitante;
  }

  /**
   * Establece el valor de la propiedad nombreSolicitante
   *
   * @param nombreSolicitante
   *          el nombreSolicitante para establecer
   */
  public void setNombreSolicitante(final String nombreSolicitante) {
    this.nombreSolicitante = nombreSolicitante;
  }

  /**
   * Obtiene la propiedad valoracionIniciada
   *
   * @return el valoracionIniciada
   */
  public boolean isValoracionIniciada() {
    return valoracionIniciada;
  }

  /**
   * Establece el valor de la propiedad valoracionIniciada
   *
   * @param valoracionIniciada
   *          el valoracionIniciada para establecer
   */
  public void setValoracionIniciada(final boolean valoracionIniciada) {
    this.valoracionIniciada = valoracionIniciada;
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
   * Obtiene la propiedad mostrarPestania3
   *
   * @return el mostrarPestania3
   */
  public boolean isMostrarPestania3() {
    return mostrarPestania3;
  }

  /**
   * Establece el valor de la propiedad mostrarPestania3
   *
   * @param mostrarPestania3
   *          el mostrarPestania3 para establecer
   */
  public void setMostrarPestania3(final boolean mostrarPestania3) {
    this.mostrarPestania3 = mostrarPestania3;
  }

  /**
   * Obtiene la propiedad codigoFinalidad
   *
   * @return el codigoFinalidad
   */
  public String getCodigoFinalidad() {
    return codigoFinalidad;
  }

  /**
   * Establece el valor de la propiedad codigoFinalidad
   *
   * @param codigoFinalidad
   *          el codigoFinalidad para establecer
   */
  public void setCodigoFinalidad(final String codigoFinalidad) {
    this.codigoFinalidad = codigoFinalidad;
  }

  /**
   * Obtiene la propiedad pertinencia
   *
   * @return el pertinencia
   */
  public ValoracionTipoCriterioDTO getPertinencia() {
    return pertinencia;
  }

  /**
   * Establece el valor de la propiedad pertinencia
   *
   * @param pertinencia
   *          el pertinencia para establecer
   */
  public void setPertinencia(final ValoracionTipoCriterioDTO pertinencia) {
    this.pertinencia = pertinencia;
  }

  /**
   * Obtiene la propiedad viabilidad
   *
   * @return el viabilidad
   */
  public ValoracionTipoCriterioDTO getViabilidad() {
    return viabilidad;
  }

  /**
   * Establece el valor de la propiedad viabilidad
   *
   * @param viabilidad
   *          el viabilidad para establecer
   */
  public void setViabilidad(final ValoracionTipoCriterioDTO viabilidad) {
    this.viabilidad = viabilidad;
  }

  /**
   * Obtiene la propiedad coherencia
   *
   * @return el coherencia
   */
  public ValoracionTipoCriterioDTO getCoherencia() {
    return coherencia;
  }

  /**
   * Establece el valor de la propiedad coherencia
   *
   * @param coherencia
   *          el coherencia para establecer
   */
  public void setCoherencia(final ValoracionTipoCriterioDTO coherencia) {
    this.coherencia = coherencia;
  }

  /**
   * Obtiene la propiedad impacto
   *
   * @return el impacto
   */
  public ValoracionTipoCriterioDTO getImpacto() {
    return impacto;
  }

  /**
   * Establece el valor de la propiedad impacto
   *
   * @param impacto
   *          el impacto para establecer
   */
  public void setImpacto(final ValoracionTipoCriterioDTO impacto) {
    this.impacto = impacto;
  }

  /**
   * Obtiene la propiedad conectividad
   *
   * @return el conectividad
   */
  public ValoracionTipoCriterioDTO getConectividad() {
    return conectividad;
  }

  /**
   * Establece el valor de la propiedad conectividad
   *
   * @param conectividad
   *          el conectividad para establecer
   */
  public void setConectividad(final ValoracionTipoCriterioDTO conectividad) {
    this.conectividad = conectividad;
  }

  /**
   * Obtiene la propiedad convergencia
   *
   * @return el convergencia
   */
  public ValoracionTipoCriterioDTO getConvergencia() {
    return convergencia;
  }

  /**
   * Establece el valor de la propiedad convergencia
   *
   * @param convergencia
   *          el convergencia para establecer
   */
  public void setConvergencia(final ValoracionTipoCriterioDTO convergencia) {
    this.convergencia = convergencia;
  }

  /**
   * Obtiene la propiedad capacidadGestion
   *
   * @return el capacidadGestion
   */
  public ValoracionTipoCriterioDTO getCapacidadGestion() {
    return capacidadGestion;
  }

  /**
   * Establece el valor de la propiedad capacidadGestion
   *
   * @param capacidadGestion
   *          el capacidadGestion para establecer
   */
  public void setCapacidadGestion(final ValoracionTipoCriterioDTO capacidadGestion) {
    this.capacidadGestion = capacidadGestion;
  }

  /**
   * Obtiene la propiedad valoracionTotal
   *
   * @return el valoracionTotal
   */
  public ValoracionTipoCriterioDTO getValoracionTotal() {
    return valoracionTotal;
  }

  /**
   * Establece el valor de la propiedad valoracionTotal
   *
   * @param valoracionTotal
   *          el valoracionTotal para establecer
   */
  public void setValoracionTotal(final ValoracionTipoCriterioDTO valoracionTotal) {
    this.valoracionTotal = valoracionTotal;
  }

  /**
   * Obtiene la propiedad sostenibilidad
   *
   * @return el sostenibilidad
   */
  public ValoracionTipoCriterioDTO getSostenibilidad() {
    return sostenibilidad;
  }

  /**
   * Establece el valor de la propiedad sostenibilidad
   *
   * @param sostenibilidad
   *          el sostenibilidad para establecer
   */
  public void setSostenibilidad(final ValoracionTipoCriterioDTO sostenibilidad) {
    this.sostenibilidad = sostenibilidad;
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

  public ValoracionTipoCriterioDTO getIncrementos() {
    return incrementos;
  }

  public void setIncrementos(final ValoracionTipoCriterioDTO incrementos) {
    this.incrementos = incrementos;
  }

  /**
   * Obtiene la propiedad permiteSumaIncrementos
   *
   * @return el permiteSumaIncrementos
   */
  public boolean isPermiteSumaIncrementos() {
    return permiteSumaIncrementos;
  }

  /**
   * Establece el valor de la propiedad permiteSumaIncrementos
   *
   * @param permiteSumaIncrementos
   *          el permiteSumaIncrementos para establecer
   */
  public void setPermiteSumaIncrementos(final boolean permiteSumaIncrementos) {
    this.permiteSumaIncrementos = permiteSumaIncrementos;
  }

  /**
   * Obtiene la propiedad causaReintegros
   *
   * @return el causaReintegros
   */
  public Long getCausaReintegros() {
    return causaReintegros;
  }

  /**
   * Establece el valor de la propiedad causaReintegros
   *
   * @param causaReintegros
   *          el causaReintegros para establecer
   */
  public void setCausaReintegros(final Long causaReintegros) {
    this.causaReintegros = causaReintegros;
  }

  /**
   * Obtiene la propiedad magnitudReintegros
   *
   * @return el magnitudReintegros
   */
  public Long getMagnitudReintegros() {
    return magnitudReintegros;
  }

  /**
   * Establece el valor de la propiedad magnitudReintegros
   *
   * @param magnitudReintegros
   *          el magnitudReintegros para establecer
   */
  public void setMagnitudReintegros(final Long magnitudReintegros) {
    this.magnitudReintegros = magnitudReintegros;
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
   * Obtiene la propiedad ongd
   *
   * @return el ongd
   */
  public boolean isOngd() {
    return ongd;
  }

  /**
   * Establece el valor de la propiedad ongd
   *
   * @param ongd
   *          el ongd para establecer
   */
  public void setOngd(final boolean ongd) {
    this.ongd = ongd;
  }

  /**
   * Obtiene la propiedad generacionInformes
   *
   * @return el generacionInformes
   */
  public boolean isGeneracionInformes() {
    return generacionInformes;
  }

  /**
   * Establece el valor de la propiedad generacionInformes
   *
   * @param generacionInformes
   *          el generacionInformes para establecer
   */
  public void setGeneracionInformes(final boolean generacionInformes) {
    this.generacionInformes = generacionInformes;
  }

  /**
   * Obtiene la propiedad anio
   *
   * @return el anio
   */
  public Long getAnio() {
    return anio;
  }

  /**
   * Establece el valor de la propiedad anio
   *
   * @param anio
   *          el anio para establecer
   */
  public void setAnio(final Long anio) {
    this.anio = anio;
  }

}
