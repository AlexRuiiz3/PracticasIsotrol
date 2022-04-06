package es.juntadeandalucia.aacid.comuntramitacion.dto;

import java.util.Date;

/**
 * Objeto para enviar los datos de la convocatoria a la pantalla de modificación.
 *
 * @author Isotrol (AACID)
 *
 */
public class ConvocatoriaDTO {

  /** id de la convocatoria */
  private Long idConv;
  /** id expediente de trewa */
  private Long idExpTrewa;
  /** titulo */
  private String titulo;
  /** descripcion */
  private String descripcion;
  /** fecha de incio */
  private Date fhInicio;
  /** fecha de fin */
  private Date fhFin;
  /** fecha de incio de la valoracion */
  private Date fhInicioValoracion;
  /** fecha resolución provisonal */
  private Date fhPropResolProv;
  /** fecha limite documentacion resolucion definitiva */
  private Date fhLimitDocResolDef;
  /** fecha resolución de */
  private Date fhResolConc;
  /** id tipo convocatoria */
  private Long idTipConv;
  /** abreviatura */
  private String abreviatura;
  /** año */
  private Integer anio;

  // Campos parametrizar fechas de fases de recepción
  private ConvocatoriaFechaDTO convocatoriaFechasDTO;

  /**
   * @return the idConv
   */
  public Long getIdConv() {
    return idConv;
  }

  /**
   * @param idConv
   *          the idConv to set
   */
  public void setIdConv(final Long idConv) {
    this.idConv = idConv;
  }

  /**
   * @return the idExpTrewa
   */
  public Long getIdExpTrewa() {
    return idExpTrewa;
  }

  /**
   * @param idExpTrewa
   *          the idExpTrewa to set
   */
  public void setIdExpTrewa(final Long idExpTrewa) {
    this.idExpTrewa = idExpTrewa;
  }

  /**
   * Obtiene la propiedad titulo
   *
   * @return el titulo
   */
  public String getTitulo() {
    return titulo;
  }

  /**
   * Establece el valor de la propiedad titulo
   *
   * @param titulo
   *          el titulo para establecer
   */
  public void setTitulo(final String titulo) {
    this.titulo = titulo;
  }

  /**
   * Obtiene la propiedad descripcion
   *
   * @return el descripcion
   */
  public String getDescripcion() {
    return descripcion;
  }

  /**
   * Establece el valor de la propiedad descripcion
   *
   * @param descripcion
   *          el descripcion para establecer
   */
  public void setDescripcion(final String descripcion) {
    this.descripcion = descripcion;
  }

  /**
   * @return the idTipConv
   */
  public Long getIdTipConv() {
    return idTipConv;
  }

  /**
   * @param idTipConv
   *          the idTipConv to set
   */
  public void setIdTipConv(final Long idTipConv) {
    this.idTipConv = idTipConv;
  }

  /**
   * @return the abreviatura
   */
  public String getAbreviatura() {
    return abreviatura;
  }

  /**
   * @param abreviatura
   *          the abreviatura to set
   */
  public void setAbreviatura(final String abreviatura) {
    this.abreviatura = abreviatura;
  }

  /**
   * @return the fhInicio
   */
  public Date getFhInicio() {
    return fhInicio;
  }

  /**
   * @param fhInicio
   *          the fhInicio to set
   */
  public void setFhInicio(final Date fhInicio) {
    this.fhInicio = fhInicio;
  }

  /**
   * @return the fhFin
   */
  public Date getFhFin() {
    return fhFin;
  }

  /**
   * @param fhFin
   *          the fhFin to set
   */
  public void setFhFin(final Date fhFin) {
    this.fhFin = fhFin;
  }

  /**
   * @return the fhInicioValoracion
   */
  public Date getFhInicioValoracion() {
    return fhInicioValoracion;
  }

  /**
   * @param fhInicioValoracion
   *          the fhInicioValoracion to set
   */
  public void setFhInicioValoracion(final Date fhInicioValoracion) {
    this.fhInicioValoracion = fhInicioValoracion;
  }

  /**
   * @return the fhPropResolProv
   */
  public Date getFhPropResolProv() {
    return fhPropResolProv;
  }

  /**
   * @param fhPropResolProv
   *          the fhPropResolProv to set
   */
  public void setFhPropResolProv(final Date fhPropResolProv) {
    this.fhPropResolProv = fhPropResolProv;
  }

  /**
   * @return the fhLimitDocResolDef
   */
  public Date getFhLimitDocResolDef() {
    return fhLimitDocResolDef;
  }

  /**
   * @param fhLimitDocResolDef
   *          the fhLimitDocResolDef to set
   */
  public void setFhLimitDocResolDef(final Date fhLimitDocResolDef) {
    this.fhLimitDocResolDef = fhLimitDocResolDef;
  }

  /**
   * @return the fhResolConc
   */
  public Date getFhResolConc() {
    return fhResolConc;
  }

  /**
   * @param fhResolConc
   *          the fhResolConc to set
   */
  public void setFhResolConc(final Date fhResolConc) {
    this.fhResolConc = fhResolConc;
  }

  /**
   * Obtiene la propiedad anio
   *
   * @return el anio
   */
  public Integer getAnio() {
    return anio;
  }

  /**
   * Establece el valor de la propiedad anio
   *
   * @param anio
   *          el anio para establecer
   */
  public void setAnio(final Integer anio) {
    this.anio = anio;
  }

  /**
   * Obtiene la propiedad convocatoriaFechasDTO
   *
   * @return el convocatoriaFechasDTO
   */
  public ConvocatoriaFechaDTO getConvocatoriaFechasDTO() {
    return convocatoriaFechasDTO;
  }

  /**
   * Establece el valor de la propiedad convocatoriaFechasDTO
   *
   * @param convocatoriaFechasDTO
   *          el convocatoriaFechasDTO para establecer
   */
  public void setConvocatoriaFechasDTO(final ConvocatoriaFechaDTO convocatoriaFechasDTO) {
    this.convocatoriaFechasDTO = convocatoriaFechasDTO;
  }

}
