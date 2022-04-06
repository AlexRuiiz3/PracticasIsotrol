package es.juntadeandalucia.aacid.comuntramitacion.dto;

public class SolicitudConcesionDTO {

  private Long idTrewa;
  private Long idSolConce;
  private Long idSolicitud;
  private String codIdentificativo;
  private String numExpTrewa;
  private String presupuestoValidado;
  private String subvencionConceder;
  private String subvencionSolicitada;
  private String entidad;
  private String puntuacion;
  private Boolean beneficiaria;

  /**
   * @param idSolConce
   * @param idSolicitud
   * @param tipoConcesion
   * @param presupuestoValidado
   * @param subvencionConceder
   * @param subvencionSolicitada
   * @param tipoConvocatoria
   */
  public SolicitudConcesionDTO(final Long idTrewa, final Long idSolConce, final Long idSolicitud, final String codIdentificativo, final Boolean beneficiaria,
      final String presupuestoValidado, final String subvencionConceder, final String subvencionSolicitada) {
    this.idSolConce = idSolConce;
    this.idSolicitud = idSolicitud;
    this.beneficiaria = beneficiaria;
    this.presupuestoValidado = presupuestoValidado;
    this.subvencionConceder = subvencionConceder;
    this.subvencionSolicitada = subvencionSolicitada;
    this.idTrewa = idTrewa;
    this.codIdentificativo = codIdentificativo;
  }

  /**
   *
   */
  public SolicitudConcesionDTO() {
    // constructor stub
  }

  public SolicitudConcesionDTO(final Long idSolicitud, final Boolean beneficiaria) {
    this.idSolicitud = idSolicitud;
    this.beneficiaria = beneficiaria;
  }

  /**
   * Obtiene la propiedad idSolConce
   *
   * @return el idSolConce
   */
  public Long getIdSolConce() {
    return idSolConce;
  }

  /**
   * Establece el valor de la propiedad idSolConce
   *
   * @param idSolConce
   *          el idSolConce para establecer
   */
  public void setIdSolConce(final Long idSolConce) {
    this.idSolConce = idSolConce;
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
   * Obtiene la propiedad presupuestoValidado
   *
   * @return el presupuestoValidado
   */
  public String getPresupuestoValidado() {
    return presupuestoValidado;
  }

  /**
   * Establece el valor de la propiedad presupuestoValidado
   *
   * @param presupuestoValidado
   *          el presupuestoValidado para establecer
   */
  public void setPresupuestoValidado(final String presupuestoValidado) {
    this.presupuestoValidado = presupuestoValidado;
  }

  /**
   * Obtiene la propiedad subvencionConceder
   *
   * @return el subvencionConceder
   */
  public String getSubvencionConceder() {
    return subvencionConceder;
  }

  /**
   * Establece el valor de la propiedad subvencionConceder
   *
   * @param subvencionConceder
   *          el subvencionConceder para establecer
   */
  public void setSubvencionConceder(final String subvencionConceder) {
    this.subvencionConceder = subvencionConceder;
  }

  /**
   * Obtiene la propiedad subvencionSolicitada
   *
   * @return el subvencionSolicitada
   */
  public String getSubvencionSolicitada() {
    return subvencionSolicitada;
  }

  /**
   * Establece el valor de la propiedad subvencionSolicitada
   *
   * @param subvencionSolicitada
   *          el subvencionSolicitada para establecer
   */
  public void setSubvencionSolicitada(final String subvencionSolicitada) {
    this.subvencionSolicitada = subvencionSolicitada;
  }

  /**
   * Obtiene la propiedad idTrewa
   *
   * @return el idTrewa
   */
  public Long getIdTrewa() {
    return idTrewa;
  }

  /**
   * Establece el valor de la propiedad idTrewa
   *
   * @param idTrewa
   *          el idTrewa para establecer
   */
  public void setIdTrewa(final Long idTrewa) {
    this.idTrewa = idTrewa;
  }

  /**
   * Obtiene la propiedad codIdentificativo
   *
   * @return el codIdentificativo
   */
  public String getCodIdentificativo() {
    return codIdentificativo;
  }

  /**
   * Establece el valor de la propiedad codIdentificativo
   *
   * @param codIdentificativo
   *          el codIdentificativo para establecer
   */
  public void setCodIdentificativo(final String codIdentificativo) {
    this.codIdentificativo = codIdentificativo;
  }

  /**
   * Obtiene la propiedad numExpTrewa
   *
   * @return el numExpTrewa
   */
  public String getNumExpTrewa() {
    return numExpTrewa;
  }

  /**
   * Establece el valor de la propiedad numExpTrewa
   *
   * @param numExpTrewa
   *          el numExpTrewa para establecer
   */
  public void setNumExpTrewa(final String numExpTrewa) {
    this.numExpTrewa = numExpTrewa;
  }

  /**
   * Obtiene la propiedad entidad
   *
   * @return el entidad
   */
  public String getEntidad() {
    return entidad;
  }

  /**
   * Establece el valor de la propiedad entidad
   *
   * @param entidad
   *          el entidad para establecer
   */
  public void setEntidad(final String entidad) {
    this.entidad = entidad;
  }

  /**
   * Obtiene la propiedad puntuacion
   *
   * @return el puntuacion
   */
  public String getPuntuacion() {
    return puntuacion;
  }

  /**
   * Establece el valor de la propiedad puntuacion
   *
   * @param puntuacion
   *          el puntuacion para establecer
   */
  public void setPuntuacion(final String puntuacion) {
    this.puntuacion = puntuacion;
  }

  /**
   * Obtiene la propiedad beneficiaria
   *
   * @return el beneficiaria
   */
  public Boolean getBeneficiaria() {
    return beneficiaria;
  }

  /**
   * Establece el valor de la propiedad beneficiaria
   *
   * @param beneficiaria
   *          el beneficiaria para establecer
   */
  public void setBeneficiaria(final Boolean beneficiaria) {
    this.beneficiaria = beneficiaria;
  }
}
