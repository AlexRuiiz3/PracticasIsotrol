package es.juntadeandalucia.aacid.comuntramitacion.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * DTO de solicitud para datos generales de la pantalla DGA
 *
 * @author isotrol
 *
 */
public class SolicitudDatosGeneralesDTO {

  private String idExpTrewa;
  private String apellidoResp;
  private List<ContraparteDTO> contrapartes;
  private List<ContribucionDTO> contribuciones;
  private String descripcion;
  /** email de notificación */
  private String email;
  private Date fechaInicio;
  private Date fhPagoSubvencion;
  private Date fhPresentacion;
  private Date fhRegistro;
  private String finalidad;
  /** idSolicitud */
  private Long idSolicitud;
  private boolean lgRepresentacion;
  private String lugarRegistro;

  private String municipio;
  private String nifResp;
  private String nombreResp;
  private Long nuNumInterno;
  private String pais;
  private BigDecimal plazo;
  /** titulo */
  private String titulo;

  private String txCodidentificativo;

  private boolean vbCoordinador;
  /** VB Técnico */
  private boolean vbTecnico;

  private String numExpediente;

  private Date fechaPostegracionComunInicio;

  /**
   * Constructor por defecto
   *
   */
  public SolicitudDatosGeneralesDTO() {
  }

  /**
   * Constructro con campos
   *
   * @param idSolicitud
   * @param vbTecnico
   * @param vbCoordinador
   * @param titulo
   * @param descripcion
   * @param municipio
   * @param fechaInicio
   * @param finalidad
   * @param plazo
   * @param fhPagoSubvencion
   */
  public SolicitudDatosGeneralesDTO(final Long idSolicitud, final boolean vbTecnico, final boolean vbCoordinador, final String titulo, final String descripcion,
      final String municipio, final Date fechaInicio, final String finalidad, final BigDecimal plazo, final Date fhPagoSubvencion, final Date fhPresentacion,
      final String apellidoResp, final String nombreResp, final String nifResp, final Long nuNumInterno, final Date fhRegistro, final boolean lgRepresentacion,
      final String lugarRegistro, final String pais, final String txCodidentificativo, final String email, final Date fechaPostegracionComunInicio) {
    this.idSolicitud = idSolicitud;
    this.vbTecnico = vbTecnico;
    this.vbCoordinador = vbCoordinador;
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.municipio = municipio;
    this.fechaInicio = fechaInicio;
    this.finalidad = finalidad;
    this.plazo = plazo;
    this.fhPagoSubvencion = fhPagoSubvencion;
    this.fhPresentacion = fhPresentacion;
    this.apellidoResp = apellidoResp;
    this.nombreResp = nombreResp;
    this.nifResp = nifResp;
    this.fhRegistro = fhRegistro;
    this.lgRepresentacion = lgRepresentacion;
    this.lugarRegistro = lugarRegistro;
    this.nuNumInterno = nuNumInterno;
    this.pais = pais;
    this.txCodidentificativo = txCodidentificativo;
    this.email = email;
    this.fechaPostegracionComunInicio = fechaPostegracionComunInicio;
  }

  /**
   * Obtiene la propiedad apellidoResp
   *
   * @return el apellidoResp
   */
  public String getApellidoResp() {
    return apellidoResp;
  }

  /**
   * Lista de contrapartes
   *
   * @return contrapartes
   */
  public List<ContraparteDTO> getContrapartes() {
    return contrapartes;
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
   * Obtiene la propiedad email
   *
   * @return el email
   */
  public String getEmail() {
    return email;
  }

  /**
   * Obtiene la propiedad fechaInicio
   *
   * @return el fechaInicio
   */
  public Date getFechaInicio() {
    return fechaInicio;
  }

  /**
   * Obtiene la propiedad fhPagoSubvencion
   *
   * @return el fhPagoSubvencion
   */
  public Date getFhPagoSubvencion() {
    return fhPagoSubvencion;
  }

  /**
   * Obtiene la propiedad fhPresentacion
   *
   * @return el fhPresentacion
   */
  public Date getFhPresentacion() {
    return fhPresentacion;
  }

  /**
   * Obtiene la propiedad fhRegistro
   *
   * @return el fhRegistro
   */
  public Date getFhRegistro() {
    return fhRegistro;
  }

  /**
   * Obtiene la propiedad finalidad
   *
   * @return el finalidad
   */
  public String getFinalidad() {
    return finalidad;
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
   * Obtiene la propiedad lugarRegistro
   *
   * @return el lugarRegistro
   */
  public String getLugarRegistro() {
    return lugarRegistro;
  }

  /**
   * Obtiene la propiedad municipio
   *
   * @return el municipio
   */
  public String getMunicipio() {
    return municipio;
  }

  /**
   * Obtiene la propiedad nifResp
   *
   * @return el nifResp
   */
  public String getNifResp() {
    return nifResp;
  }

  /**
   * Obtiene la propiedad nombreResp
   *
   * @return el nombreResp
   */
  public String getNombreResp() {
    return nombreResp;
  }

  /**
   * Obtiene la propiedad nuNumInterno
   *
   * @return el nuNumInterno
   */
  public Long getNuNumInterno() {
    return nuNumInterno;
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
   * Obtiene la propiedad plazo
   *
   * @return el plazo
   */
  public BigDecimal getPlazo() {
    return plazo;
  }

  /**
   * Obtiene la propiedad titulo
   *
   * @return el titulo
   */
  public String getTitulo() {
    return titulo;
  }

  public String getTxCodidentificativo() {
    return txCodidentificativo;
  }

  /**
   * Obtiene la propiedad lgRepresentacion
   *
   * @return el lgRepresentacion
   */
  public boolean isLgRepresentacion() {
    return lgRepresentacion;
  }

  /**
   * Obtiene la propiedad vbCoordinador
   *
   * @return el vbCoordinador
   */
  public boolean isVbCoordinador() {
    return vbCoordinador;
  }

  /**
   * Obtiene la propiedad vbTecnico
   *
   * @return el vbTecnico
   */
  public boolean isVbTecnico() {
    return vbTecnico;
  }

  /**
   * Establece el valor de la propiedad apellidoResp
   *
   * @param apellidoResp
   *          el apellidoResp para establecer
   */
  public void setApellidoResp(final String apellidoResp) {
    this.apellidoResp = apellidoResp;
  }

  /**
   * Establece el valor de la propiedad contrapartes
   *
   * @param lista
   *          de contrapartes
   */
  public void setContrapartes(final List<ContraparteDTO> contrapartes) {
    this.contrapartes = contrapartes;
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
   * Establece el valor de la propiedad email
   *
   * @param email
   *          el email para establecer
   */
  public void setEmail(final String email) {
    this.email = email;
  }

  /**
   * Establece el valor de la propiedad fechaInicio
   *
   * @param fechaInicio
   *          el fechaInicio para establecer
   */
  public void setFechaInicio(final Date fechaInicio) {
    this.fechaInicio = fechaInicio;
  }

  /**
   * Establece el valor de la propiedad fhPagoSubvencion
   *
   * @param fhPagoSubvencion
   *          el fhPagoSubvencion para establecer
   */
  public void setFhPagoSubvencion(final Date fhPagoSubvencion) {
    this.fhPagoSubvencion = fhPagoSubvencion;
  }

  /**
   * Establece el valor de la propiedad fhPresentacion
   *
   * @param fhPresentacion
   *          el fhPresentacion para establecer
   */
  public void setFhPresentacion(final Date fhPresentacion) {
    this.fhPresentacion = fhPresentacion;
  }

  /**
   * Establece el valor de la propiedad fhRegistro
   *
   * @param fhRegistro
   *          el fhRegistro para establecer
   */
  public void setFhRegistro(final Date fhRegistro) {
    this.fhRegistro = fhRegistro;
  }

  /**
   * Establece el valor de la propiedad finalidad
   *
   * @param finalidad
   *          el finalidad para establecer
   */
  public void setFinalidad(final String finalidad) {
    this.finalidad = finalidad;
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
   * Establece el valor de la propiedad lgRepresentacion
   *
   * @param lgRepresentacion
   *          el lgRepresentacion para establecer
   */
  public void setLgRepresentacion(final boolean lgRepresentacion) {
    this.lgRepresentacion = lgRepresentacion;
  }

  /**
   * Establece el valor de la propiedad lugarRegistro
   *
   * @param lugarRegistro
   *          el lugarRegistro para establecer
   */
  public void setLugarRegistro(final String lugarRegistro) {
    this.lugarRegistro = lugarRegistro;
  }

  /**
   * Establece el valor de la propiedad municipio
   *
   * @param municipio
   *          el municipio para establecer
   */
  public void setMunicipio(final String municipio) {
    this.municipio = municipio;
  }

  /**
   * Establece el valor de la propiedad nifResp
   *
   * @param nifResp
   *          el nifResp para establecer
   */
  public void setNifResp(final String nifResp) {
    this.nifResp = nifResp;
  }

  /**
   * Establece el valor de la propiedad nombreResp
   *
   * @param nombreResp
   *          el nombreResp para establecer
   */
  public void setNombreResp(final String nombreResp) {
    this.nombreResp = nombreResp;
  }

  /**
   * Establece el valor de la propiedad nuNumInterno
   *
   * @param nuNumInterno
   *          el nuNumInterno para establecer
   */
  public void setNuNumInterno(final Long nuNumInterno) {
    this.nuNumInterno = nuNumInterno;
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
   * Establece el valor de la propiedad plazo
   *
   * @param plazo
   *          el plazo para establecer
   */
  public void setPlazo(final BigDecimal plazo) {
    this.plazo = plazo;
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

  public void setTxCodidentificativo(final String txCodidentificativo) {
    this.txCodidentificativo = txCodidentificativo;
  }

  /**
   * Establece el valor de la propiedad vbCoordinador
   *
   * @param vbCoordinador
   *          el vbCoordinador para establecer
   */
  public void setVbCoordinador(final boolean vbCoordinador) {
    this.vbCoordinador = vbCoordinador;
  }

  /**
   * Establece el valor de la propiedad vbTecnico
   *
   * @param vbTecnico
   *          el vbTecnico para establecer
   */
  public void setVbTecnico(final boolean vbTecnico) {
    this.vbTecnico = vbTecnico;
  }

  public List<ContribucionDTO> getContribuciones() {
    return contribuciones;
  }

  public void setContribuciones(final List<ContribucionDTO> contribuciones) {
    this.contribuciones = contribuciones;
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
   * Obtiene la propiedad idExpTrewa
   *
   * @return el idExpTrewa
   */
  public String getIdExpTrewa() {
    return idExpTrewa;
  }

  /**
   * Establece el valor de la propiedad idExpTrewa
   *
   * @param idExpTrewa
   *          el idExpTrewa para establecer
   */
  public void setIdExpTrewa(final String idExpTrewa) {
    this.idExpTrewa = idExpTrewa;
  }

  /**
   * Obtiene la propiedad fechaPostegracionComunInicio
   *
   * @return el fechaPostegracionComunInicio
   */
  public Date getFechaPostegracionComunInicio() {
    return fechaPostegracionComunInicio;
  }

  /**
   * Establece el valor de la propiedad fechaPostegracionComunInicio
   *
   * @param fechaPostegracionComunInicio
   *          el fechaPostegracionComunInicio para establecer
   */
  public void setFechaPostegracionComunInicio(final Date fechaPostegracionComunInicio) {
    this.fechaPostegracionComunInicio = fechaPostegracionComunInicio;
  }

}
