package es.juntadeandalucia.aacid.comuntramitacion.dto;

public class ExclusionesSolicitudDTO {
  private ExclusionDTO exclusion;
  private Long idSolicitud;

  /**
   * Obtiene la propiedad exclusion
   * 
   * @return el exclusion
   */
  public ExclusionDTO getExclusion() {
    return exclusion;
  }

  /**
   * Establece el valor de la propiedad exclusion
   * 
   * @param exclusion
   *          el exclusion para establecer
   */
  public void setExclusion(ExclusionDTO exclusion) {
    this.exclusion = exclusion;
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
  public void setIdSolicitud(Long idSolicitud) {
    this.idSolicitud = idSolicitud;
  }

}
