package es.juntadeandalucia.aacid.comuntramitacion.dto;

import java.util.ArrayList;
import java.util.List;

public class ListasExclusionesDTO implements java.io.Serializable {
  /**
   *
   */
  private static final long serialVersionUID = -1851950153523492691L;
  private List<ExclusionDTO> listaExclusionesComunes = new ArrayList<>();
  private List<ExclusionDTO> listaExclusionesCooperacion = new ArrayList<>();
  private List<ExclusionDTO> listaExclusionesHumanitaria = new ArrayList<>();
  private List<ExclusionDTO> listaExclusionesEducacion = new ArrayList<>();
  private List<ExclusionDTO> listaExclusionesFormacion = new ArrayList<>();
  private List<ExclusionDTO> listaExclusionesPaisesPriorizados = new ArrayList<>();
  private List<ExclusionDTO> listaExclusionesAndalucia = new ArrayList<>();
  private List<ExclusionDTO> listaExclusionesInadDesestimiento = new ArrayList<>();
  private List<ExclusionDTO> listaExclusionesProyectosCooperacion = new ArrayList<>();
  private List<ExclusionDTO> listaExclusionesProyectosAccHumanitaria = new ArrayList<>();
  private List<ExclusionDTO> listaExclusionesProyectosDesarrolloInnov = new ArrayList<>();
  // Tipo 2 excepciones exclusiones
  private List<ExclusionDTO> listaExclusionesExcepcionTipo2 = new ArrayList<>();

  /**
   * Obtiene la propiedad listaExclusionesComunes
   *
   * @return el listaExclusionesComunes
   */
  public List<ExclusionDTO> getListaExclusionesComunes() {
    return listaExclusionesComunes;
  }

  /**
   * Establece el valor de la propiedad listaExclusionesComunes
   *
   * @param listaExclusionesComunes
   *          el listaExclusionesComunes para establecer
   */
  public void setListaExclusionesComunes(final List<ExclusionDTO> listaExclusionesComunes) {
    this.listaExclusionesComunes = listaExclusionesComunes;
  }

  /**
   * Obtiene la propiedad listaExclusionesCooperacion
   *
   * @return el listaExclusionesCooperacion
   */
  public List<ExclusionDTO> getListaExclusionesCooperacion() {
    return listaExclusionesCooperacion;
  }

  /**
   * Establece el valor de la propiedad listaExclusionesCooperacion
   *
   * @param listaExclusionesCooperacion
   *          el listaExclusionesCooperacion para establecer
   */
  public void setListaExclusionesCooperacion(final List<ExclusionDTO> listaExclusionesCooperacion) {
    this.listaExclusionesCooperacion = listaExclusionesCooperacion;
  }

  /**
   * Obtiene la propiedad listaExclusionesHumanitaria
   *
   * @return el listaExclusionesHumanitaria
   */
  public List<ExclusionDTO> getListaExclusionesHumanitaria() {
    return listaExclusionesHumanitaria;
  }

  /**
   * Establece el valor de la propiedad listaExclusionesHumanitaria
   *
   * @param listaExclusionesHumanitaria
   *          el listaExclusionesHumanitaria para establecer
   */
  public void setListaExclusionesHumanitaria(final List<ExclusionDTO> listaExclusionesHumanitaria) {
    this.listaExclusionesHumanitaria = listaExclusionesHumanitaria;
  }

  /**
   * Obtiene la propiedad listaExclusionesEducacion
   *
   * @return el listaExclusionesEducacion
   */
  public List<ExclusionDTO> getListaExclusionesEducacion() {
    return listaExclusionesEducacion;
  }

  /**
   * Establece el valor de la propiedad listaExclusionesEducacion
   *
   * @param listaExclusionesEducacion
   *          el listaExclusionesEducacion para establecer
   */
  public void setListaExclusionesEducacion(final List<ExclusionDTO> listaExclusionesEducacion) {
    this.listaExclusionesEducacion = listaExclusionesEducacion;
  }

  /**
   * Obtiene la propiedad listaExclusionesFormacion
   *
   * @return el listaExclusionesFormacion
   */
  public List<ExclusionDTO> getListaExclusionesFormacion() {
    return listaExclusionesFormacion;
  }

  /**
   * Establece el valor de la propiedad listaExclusionesFormacion
   *
   * @param listaExclusionesFormacion
   *          el listaExclusionesFormacion para establecer
   */
  public void setListaExclusionesFormacion(final List<ExclusionDTO> listaExclusionesFormacion) {
    this.listaExclusionesFormacion = listaExclusionesFormacion;
  }

  /**
   * Obtiene la propiedad listaExclusionesPaisesPriorizados
   *
   * @return el listaExclusionesPaisesPriorizados
   */
  public List<ExclusionDTO> getListaExclusionesPaisesPriorizados() {
    return listaExclusionesPaisesPriorizados;
  }

  /**
   * Establece el valor de la propiedad listaExclusionesPaisesPriorizados
   *
   * @param listaExclusionesPaisesPriorizados
   *          el listaExclusionesPaisesPriorizados para establecer
   */
  public void setListaExclusionesPaisesPriorizados(final List<ExclusionDTO> listaExclusionesPaisesPriorizados) {
    this.listaExclusionesPaisesPriorizados = listaExclusionesPaisesPriorizados;
  }

  /**
   * Obtiene la propiedad listaExclusionesAndalucia
   *
   * @return el listaExclusionesAndalucia
   */
  public List<ExclusionDTO> getListaExclusionesAndalucia() {
    return listaExclusionesAndalucia;
  }

  /**
   * Establece el valor de la propiedad listaExclusionesAndalucia
   *
   * @param listaExclusionesAndalucia
   *          el listaExclusionesAndalucia para establecer
   */
  public void setListaExclusionesAndalucia(final List<ExclusionDTO> listaExclusionesAndalucia) {
    this.listaExclusionesAndalucia = listaExclusionesAndalucia;
  }

  /**
   * @return the listaExclusionesInadDesestimiento
   */
  public List<ExclusionDTO> getListaExclusionesInadDesestimiento() {
    return listaExclusionesInadDesestimiento;
  }

  /**
   * @param listaExclusionesInadDesestimiento
   *          the listaExclusionesInadDesestimiento to set
   */
  public void setListaExclusionesInadDesestimiento(final List<ExclusionDTO> listaExclusionesInadDesestimiento) {
    this.listaExclusionesInadDesestimiento = listaExclusionesInadDesestimiento;
  }

  /**
   * @return the listaExclusionesProyectosCooperacion
   */
  public List<ExclusionDTO> getListaExclusionesProyectosCooperacion() {
    return listaExclusionesProyectosCooperacion;
  }

  /**
   * @param listaExclusionesProyectosCooperacion
   *          the listaExclusionesProyectosCooperacion to set
   */
  public void setListaExclusionesProyectosCooperacion(final List<ExclusionDTO> listaExclusionesProyectosCooperacion) {
    this.listaExclusionesProyectosCooperacion = listaExclusionesProyectosCooperacion;
  }

  /**
   * @return the listaExclusionesProyectosAccHumanitaria
   */
  public List<ExclusionDTO> getListaExclusionesProyectosAccHumanitaria() {
    return listaExclusionesProyectosAccHumanitaria;
  }

  /**
   * @param listaExclusionesProyectosAccHumanitaria
   *          the listaExclusionesProyectosAccHumanitaria to set
   */
  public void setListaExclusionesProyectosAccHumanitaria(final List<ExclusionDTO> listaExclusionesProyectosAccHumanitaria) {
    this.listaExclusionesProyectosAccHumanitaria = listaExclusionesProyectosAccHumanitaria;
  }

  /**
   * @return the listaExclusionesProyectosDesarrolloInnov
   */
  public List<ExclusionDTO> getListaExclusionesProyectosDesarrolloInnov() {
    return listaExclusionesProyectosDesarrolloInnov;
  }

  /**
   * @param listaExclusionesProyectosDesarrolloInnov
   *          the listaExclusionesProyectosDesarrolloInnov to set
   */
  public void setListaExclusionesProyectosDesarrolloInnov(final List<ExclusionDTO> listaExclusionesProyectosDesarrolloInnov) {
    this.listaExclusionesProyectosDesarrolloInnov = listaExclusionesProyectosDesarrolloInnov;
  }

  /**
   * Obtiene la propiedad listaExclusionesExcepcionTipo2
   *
   * @return el listaExclusionesExcepcionTipo2
   */
  public List<ExclusionDTO> getListaExclusionesExcepcionTipo2() {
    return listaExclusionesExcepcionTipo2;
  }

  /**
   * Establece el valor de la propiedad listaExclusionesExcepcionTipo2
   *
   * @param listaExclusionesExcepcionTipo2
   *          el listaExclusionesExcepcionTipo2 para establecer
   */
  public void setListaExclusionesExcepcionTipo2(final List<ExclusionDTO> listaExclusionesExcepcionTipo2) {
    this.listaExclusionesExcepcionTipo2 = listaExclusionesExcepcionTipo2;
  }

}
