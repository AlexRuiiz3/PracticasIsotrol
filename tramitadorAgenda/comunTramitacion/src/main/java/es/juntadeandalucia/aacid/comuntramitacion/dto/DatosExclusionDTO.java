package es.juntadeandalucia.aacid.comuntramitacion.dto;

import java.util.ArrayList;
import java.util.List;

public class DatosExclusionDTO implements java.io.Serializable {

  /**
  *
  */
  private static final long serialVersionUID = -5962741538535114305L;

  private Long tipoExclusion;
  private List<String> exclusionesComunesSeleccionadas = new ArrayList<>();
  private List<String> exclusionesCooperacionSeleccionadas = new ArrayList<>();
  private List<String> exclusionesHumanitariaSeleccionadas = new ArrayList<>();
  private List<String> exclusionesEducacionSeleccionadas = new ArrayList<>();
  private List<String> exclusionesFormacionSeleccionadas = new ArrayList<>();
  private List<String> exclusionesPaisesPriorizadosSeleccionadas = new ArrayList<>();
  private List<String> exclusionesAndaluciaSeleccionadas = new ArrayList<>();
  private List<String> exclusionesInadmisionSeleccionadas = new ArrayList<>();
  private List<String> exclusionesProyCooperacionSeleccionadas = new ArrayList<>();
  private List<String> exclusionesProyAccHumanitariaSeleccionadas = new ArrayList<>();
  private List<String> exclusionesProyDesarrolloSeleccionadas = new ArrayList<>();
  // Tipo 2 excepciones exclusiones
  private List<String> exclusionesExcepcionTipo2 = new ArrayList<>();
  private List<String> exclusionesExcepcionTipo2Seleccionadas = new ArrayList<>();

  private Long idSolicitud;
  private boolean noTieneExclusiones;

  /**
   * Obtiene la propiedad tipoExclusion
   *
   * @return el tipoExclusion
   */
  public Long getTipoExclusion() {
    return tipoExclusion;
  }

  /**
   * Establece el valor de la propiedad tipoExclusion
   *
   * @param tipoExclusion
   *          el tipoExclusion para establecer
   */
  public void setTipoExclusion(final Long tipoExclusion) {
    this.tipoExclusion = tipoExclusion;
  }

  /**
   * Obtiene la propiedad exclusionesComunesSeleccionadas
   *
   * @return el exclusionesComunesSeleccionadas
   */
  public List<String> getExclusionesComunesSeleccionadas() {
    return exclusionesComunesSeleccionadas;
  }

  /**
   * Establece el valor de la propiedad exclusionesComunesSeleccionadas
   *
   * @param exclusionesComunesSeleccionadas
   *          el exclusionesComunesSeleccionadas para establecer
   */
  public void setExclusionesComunesSeleccionadas(final List<String> exclusionesComunesSeleccionadas) {
    this.exclusionesComunesSeleccionadas = exclusionesComunesSeleccionadas;
  }

  /**
   * Obtiene la propiedad exclusionesCooperacionSeleccionadas
   *
   * @return el exclusionesCooperacionSeleccionadas
   */
  public List<String> getExclusionesCooperacionSeleccionadas() {
    return exclusionesCooperacionSeleccionadas;
  }

  /**
   * Establece el valor de la propiedad exclusionesCooperacionSeleccionadas
   *
   * @param exclusionesCooperacionSeleccionadas
   *          el exclusionesCooperacionSeleccionadas para establecer
   */
  public void setExclusionesCooperacionSeleccionadas(final List<String> exclusionesCooperacionSeleccionadas) {
    this.exclusionesCooperacionSeleccionadas = exclusionesCooperacionSeleccionadas;
  }

  /**
   * Obtiene la propiedad exclusionesHumanitariaSeleccionadas
   *
   * @return el exclusionesHumanitariaSeleccionadas
   */
  public List<String> getExclusionesHumanitariaSeleccionadas() {
    return exclusionesHumanitariaSeleccionadas;
  }

  /**
   * Establece el valor de la propiedad exclusionesHumanitariaSeleccionadas
   *
   * @param exclusionesHumanitariaSeleccionadas
   *          el exclusionesHumanitariaSeleccionadas para establecer
   */
  public void setExclusionesHumanitariaSeleccionadas(final List<String> exclusionesHumanitariaSeleccionadas) {
    this.exclusionesHumanitariaSeleccionadas = exclusionesHumanitariaSeleccionadas;
  }

  /**
   * Obtiene la propiedad exclusionesEducacionSeleccionadas
   *
   * @return el exclusionesEducacionSeleccionadas
   */
  public List<String> getExclusionesEducacionSeleccionadas() {
    return exclusionesEducacionSeleccionadas;
  }

  /**
   * Establece el valor de la propiedad exclusionesEducacionSeleccionadas
   *
   * @param exclusionesEducacionSeleccionadas
   *          el exclusionesEducacionSeleccionadas para establecer
   */
  public void setExclusionesEducacionSeleccionadas(final List<String> exclusionesEducacionSeleccionadas) {
    this.exclusionesEducacionSeleccionadas = exclusionesEducacionSeleccionadas;
  }

  /**
   * Obtiene la propiedad exclusionesFormacionSeleccionadas
   *
   * @return el exclusionesFormacionSeleccionadas
   */
  public List<String> getExclusionesFormacionSeleccionadas() {
    return exclusionesFormacionSeleccionadas;
  }

  /**
   * Establece el valor de la propiedad exclusionesFormacionSeleccionadas
   *
   * @param exclusionesFormacionSeleccionadas
   *          el exclusionesFormacionSeleccionadas para establecer
   */
  public void setExclusionesFormacionSeleccionadas(final List<String> exclusionesFormacionSeleccionadas) {
    this.exclusionesFormacionSeleccionadas = exclusionesFormacionSeleccionadas;
  }

  /**
   * Obtiene la propiedad exclusionesPaisesPriorizadosSeleccionadas
   *
   * @return el exclusionesPaisesPriorizadosSeleccionadas
   */
  public List<String> getExclusionesPaisesPriorizadosSeleccionadas() {
    return exclusionesPaisesPriorizadosSeleccionadas;
  }

  /**
   * Establece el valor de la propiedad exclusionesPaisesPriorizadosSeleccionadas
   *
   * @param exclusionesPaisesPriorizadosSeleccionadas
   *          el exclusionesPaisesPriorizadosSeleccionadas para establecer
   */
  public void setExclusionesPaisesPriorizadosSeleccionadas(final List<String> exclusionesPaisesPriorizadosSeleccionadas) {
    this.exclusionesPaisesPriorizadosSeleccionadas = exclusionesPaisesPriorizadosSeleccionadas;
  }

  /**
   * Obtiene la propiedad exclusionesAndaluciaSeleccionadas
   *
   * @return el exclusionesAndaluciaSeleccionadas
   */
  public List<String> getExclusionesAndaluciaSeleccionadas() {
    return exclusionesAndaluciaSeleccionadas;
  }

  /**
   * Establece el valor de la propiedad exclusionesAndaluciaSeleccionadas
   *
   * @param exclusionesAndaluciaSeleccionadas
   *          el exclusionesAndaluciaSeleccionadas para establecer
   */
  public void setExclusionesAndaluciaSeleccionadas(final List<String> exclusionesAndaluciaSeleccionadas) {
    this.exclusionesAndaluciaSeleccionadas = exclusionesAndaluciaSeleccionadas;
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
   * @return the exclusionesInadmisionSeleccionadas
   */
  public List<String> getExclusionesInadmisionSeleccionadas() {
    return exclusionesInadmisionSeleccionadas;
  }

  /**
   * @param exclusionesInadmisionSeleccionadas
   *          the exclusionesInadmisionSeleccionadas to set
   */
  public void setExclusionesInadmisionSeleccionadas(final List<String> exclusionesInadmisionSeleccionadas) {
    this.exclusionesInadmisionSeleccionadas = exclusionesInadmisionSeleccionadas;
  }

  /**
   * @return the exclusionesProyCooperacionSeleccionadas
   */
  public List<String> getExclusionesProyCooperacionSeleccionadas() {
    return exclusionesProyCooperacionSeleccionadas;
  }

  /**
   * @param exclusionesProyCooperacionSeleccionadas
   *          the exclusionesProyCooperacionSeleccionadas to set
   */
  public void setExclusionesProyCooperacionSeleccionadas(final List<String> exclusionesProyCooperacionSeleccionadas) {
    this.exclusionesProyCooperacionSeleccionadas = exclusionesProyCooperacionSeleccionadas;
  }

  /**
   * @return the exclusionesProyAccHumanitariaSeleccionadas
   */
  public List<String> getExclusionesProyAccHumanitariaSeleccionadas() {
    return exclusionesProyAccHumanitariaSeleccionadas;
  }

  /**
   * @param exclusionesProyAccHumanitariaSeleccionadas
   *          the exclusionesProyAccHumanitariaSeleccionadas to set
   */
  public void setExclusionesProyAccHumanitariaSeleccionadas(final List<String> exclusionesProyAccHumanitariaSeleccionadas) {
    this.exclusionesProyAccHumanitariaSeleccionadas = exclusionesProyAccHumanitariaSeleccionadas;
  }

  /**
   * @return the exclusionesProyDesarrolloSeleccionadas
   */
  public List<String> getExclusionesProyDesarrolloSeleccionadas() {
    return exclusionesProyDesarrolloSeleccionadas;
  }

  /**
   * @param exclusionesProyDesarrolloSeleccionadas
   *          the exclusionesProyDesarrolloSeleccionadas to set
   */
  public void setExclusionesProyDesarrolloSeleccionadas(final List<String> exclusionesProyDesarrolloSeleccionadas) {
    this.exclusionesProyDesarrolloSeleccionadas = exclusionesProyDesarrolloSeleccionadas;
  }

  /**
   * Obtiene la propiedad exclusionesExcepcionTipo2
   *
   * @return el exclusionesExcepcionTipo2
   */
  public List<String> getExclusionesExcepcionTipo2() {
    return exclusionesExcepcionTipo2;
  }

  /**
   * Establece el valor de la propiedad exclusionesExcepcionTipo2
   *
   * @param exclusionesExcepcionTipo2
   *          el exclusionesExcepcionTipo2 para establecer
   */
  public void setExclusionesExcepcionTipo2(final List<String> exclusionesExcepcionTipo2) {
    this.exclusionesExcepcionTipo2 = exclusionesExcepcionTipo2;
  }

  /**
   * Obtiene la propiedad exclusionesExcepcionTipo2Seleccionadas
   *
   * @return el exclusionesExcepcionTipo2Seleccionadas
   */
  public List<String> getExclusionesExcepcionTipo2Seleccionadas() {
    return exclusionesExcepcionTipo2Seleccionadas;
  }

  /**
   * Establece el valor de la propiedad exclusionesExcepcionTipo2Seleccionadas
   *
   * @param exclusionesExcepcionTipo2Seleccionadas
   *          el exclusionesExcepcionTipo2Seleccionadas para establecer
   */
  public void setExclusionesExcepcionTipo2Seleccionadas(final List<String> exclusionesExcepcionTipo2Seleccionadas) {
    this.exclusionesExcepcionTipo2Seleccionadas = exclusionesExcepcionTipo2Seleccionadas;
  }

  /**
   * Obtiene la propiedad noTieneExclusiones
   *
   * @return el noTieneExclusiones
   */
  public boolean isNoTieneExclusiones() {
    return noTieneExclusiones;
  }

  /**
   * Establece el valor de la propiedad noTieneExclusiones
   *
   * @param noTieneExclusiones
   *          el noTieneExclusiones para establecer
   */
  public void setNoTieneExclusiones(final boolean noTieneExclusiones) {
    this.noTieneExclusiones = noTieneExclusiones;
  }

}