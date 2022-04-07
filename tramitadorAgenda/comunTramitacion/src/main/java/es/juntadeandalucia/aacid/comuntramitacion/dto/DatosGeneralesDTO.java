package es.juntadeandalucia.aacid.comuntramitacion.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import es.juntadeandalucia.aacid.comuntramitacion.validation.annotation.FechaAnteriorActual;
import es.juntadeandalucia.aacid.comuntramitacion.validation.annotation.Nif;

/**
 * Datos Generales
 *
 * @author Isotrol
 */
public class DatosGeneralesDTO implements Serializable {

  /** Serial id */
  private static final long serialVersionUID = 4896822284751542365L;

  /** Campos Subsanaciones */
  private Integer idSubsanacion;

  @DateTimeFormat(pattern = "dd/MM/yyyy")
  @FechaAnteriorActual
  private Date fechaRegistro;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  @FechaAnteriorActual
  private Date fechaEnvSub;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  @FechaAnteriorActual
  private Date fechaLimite;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  @FechaAnteriorActual
  private Date fechaEntrada;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  @FechaAnteriorActual
  private Date fechaPagoSubvencion;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  private Date fechaInicio;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  @FechaAnteriorActual
  private Date fechaRecepSub;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  private Date fechaPostegracionComunInicio;

  @Size(min = 0, max = 4000, message = "Supera el tama\u00F1o m\u00E1ximo")
  private String observaciones;
  @Size(min = 0, max = 50, message = "Supera el tama\u00F1o m\u00E1ximo")
  private String lugarRegistro;
  @Size(min = 0, max = 4000, message = "Supera el tama\u00F1o m\u00E1ximo")
  private String motivoDesestimacion;

  private boolean prevRiesgos;
  private boolean africa;
  private boolean servSoc;
  private boolean otros;
  private boolean noEspec;
  /** Provincias. */
  private boolean almeria;
  private boolean cadiz;
  private boolean huelva;
  private boolean sevilla;
  private boolean cordoba;
  private boolean granada;
  private boolean jaen;
  private boolean malaga;

  /** Campos Solicitud */
  private Long idSolicitud;
  @Size(min = 0, max = 8, message = "Supera el tama\u00F1o m\u00E1ximo")
  private String plazoEjec;
  private Long impoSoli;
  private Long impoTotal;
  private Long impoEntidadLider;
  private String numExp;
  @Size(min = 0, max = 19, message = "Supera el tama\u00F1o m\u00E1ximo")
  private String numExpInterno;
  private String persRespon;
  private String unidRespon;
  private String tieneSub;
  private String pasaAValoracion;
  private String presupFormu;

  private String resumen;
  @Size(min = 0, max = 72, message = "Supera el tama\u00F1o m\u00E1ximo")
  private String pais;
  @Size(min = 0, max = 100, message = "Supera el tama\u00F1o m\u00E1ximo")
  private String tituloProy;
  private String descripcionProy;
  private String localidadEjec;
  @Size(min = 0, max = 50, message = "Supera el tama\u00F1o m\u00E1ximo")
  private String apellidosSol;
  @Size(min = 0, max = 50, message = "Supera el tama\u00F1o m\u00E1ximo")
  private String nombreSol;
  @Size(min = 0, max = 9, message = "Supera el tama\u00F1o m\u00E1ximo")
  @Nif
  private String nifSol;
  private boolean ostentaRepresentacion;

  private boolean editarVBTecnico;
  private boolean editarVBCoordinador;
  private boolean vbTecnico;
  private boolean vbCoord;
  private boolean tieneExcl;
  private boolean accionHuman;
  private boolean cooperacion;
  private boolean formacion;
  private boolean educacion;
  private boolean tieneContrapartesLocal;
  private boolean tieneAgrupacionesLocal;

  private List<ContraparteDTO> listaContraparte;
  private List<AgrupacionDTO> listaAgrupacion;

  private EntidadSolicitanteDTO entidadSolicitante;

  private String numOV;

  private boolean tienePermisosGuardado;

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
   * Obtiene la propiedad plazoEjec
   *
   * @return el plazoEjec
   */
  public String getPlazoEjec() {
    return plazoEjec;
  }

  /**
   * Establece el valor de la propiedad plazoEjec
   *
   * @param plazoEjec
   *          el plazoEjec para establecer
   */
  public void setPlazoEjec(final String plazoEjec) {
    this.plazoEjec = plazoEjec;
  }

  /**
   * Obtiene la propiedad impoSoli
   *
   * @return el impoSoli
   */
  public Long getImpoSoli() {
    return impoSoli;
  }

  /**
   * Establece el valor de la propiedad impoSoli
   *
   * @param impoSoli
   *          el impoSoli para establecer
   */
  public void setImpoSoli(final Long impoSoli) {
    this.impoSoli = impoSoli;
  }

  /**
   * Obtiene la propiedad numExp
   *
   * @return el numExp
   */
  public String getNumExp() {
    return numExp;
  }

  /**
   * Establece el valor de la propiedad numExp
   *
   * @param numExp
   *          el numExp para establecer
   */
  public void setNumExp(final String numExp) {
    this.numExp = numExp;
  }

  /**
   * Obtiene la propiedad persRespon
   *
   * @return el persRespon
   */
  public String getPersRespon() {
    return persRespon;
  }

  /**
   * Establece el valor de la propiedad persRespon
   *
   * @param persRespon
   *          el persRespon para establecer
   */
  public void setPersRespon(final String persRespon) {
    this.persRespon = persRespon;
  }

  /**
   * Obtiene la propiedad unidRespon
   *
   * @return el unidRespon
   */
  public String getUnidRespon() {
    return unidRespon;
  }

  /**
   * Establece el valor de la propiedad unidRespon
   *
   * @param unidRespon
   *          el unidRespon para establecer
   */
  public void setUnidRespon(final String unidRespon) {
    this.unidRespon = unidRespon;
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
   * Establece el valor de la propiedad lugarRegistro
   *
   * @param lugarRegistro
   *          el lugarRegistro para establecer
   */
  public void setLugarRegistro(final String lugarRegistro) {
    this.lugarRegistro = lugarRegistro;
  }

  /**
   * Obtiene la propiedad resumen
   *
   * @return el resumen
   */
  public String getResumen() {
    return resumen;
  }

  /**
   * Establece el valor de la propiedad resumen
   *
   * @param resumen
   *          el resumen para establecer
   */
  public void setResumen(final String resumen) {
    this.resumen = resumen;
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
   * Establece el valor de la propiedad pais
   *
   * @param pais
   *          el pais para establecer
   */
  public void setPais(final String pais) {
    this.pais = pais;
  }

  /**
   * Obtiene la propiedad descripcionProy
   *
   * @return el descripcionProy
   */
  public String getDescripcionProy() {
    return descripcionProy;
  }

  /**
   * Establece el valor de la propiedad descripcionProy
   *
   * @param descripcionProy
   *          el descripcionProy para establecer
   */
  public void setDescripcionProy(final String descripcionProy) {
    this.descripcionProy = descripcionProy;
  }

  /**
   * Obtiene la propiedad localidadEjec
   *
   * @return el localidadEjec
   */
  public String getLocalidadEjec() {
    return localidadEjec;
  }

  /**
   * Establece el valor de la propiedad localidadEjec
   *
   * @param localidadEjec
   *          el localidadEjec para establecer
   */
  public void setLocalidadEjec(final String localidadEjec) {
    this.localidadEjec = localidadEjec;
  }

  /**
   * Obtiene la propiedad fechaEntrada
   *
   * @return el fechaEntrada
   */
  public Date getFechaEntrada() {
    return fechaEntrada;
  }

  /**
   * Establece el valor de la propiedad fechaEntrada
   *
   * @param fechaEntrada
   *          el fechaEntrada para establecer
   */
  public void setFechaEntrada(final Date fechaEntrada) {
    this.fechaEntrada = fechaEntrada;
  }

  /**
   * Obtiene la propiedad fechaRegistro
   *
   * @return el fechaRegistro
   */
  public Date getFechaRegistro() {
    return fechaRegistro;
  }

  /**
   * Establece el valor de la propiedad fechaRegistro
   *
   * @param fechaRegistro
   *          el fechaRegistro para establecer
   */
  public void setFechaRegistro(final Date fechaRegistro) {
    this.fechaRegistro = fechaRegistro;
  }

  /**
   * Obtiene la propiedad fechaPagoSubvencion
   *
   * @return el fechaPagoSubvencion
   */
  public Date getFechaPagoSubvencion() {
    return fechaPagoSubvencion;
  }

  /**
   * Establece el valor de la propiedad fechaPagoSubvencion
   *
   * @param fechaPagoSubvencion
   *          el fechaPagoSubvencion para establecer
   */
  public void setFechaPagoSubvencion(final Date fechaPagoSubvencion) {
    this.fechaPagoSubvencion = fechaPagoSubvencion;
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
   * Establece el valor de la propiedad fechaInicio
   *
   * @param fechaInicio
   *          el fechaInicio para establecer
   */
  public void setFechaInicio(final Date fechaInicio) {
    this.fechaInicio = fechaInicio;
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
   * Establece el valor de la propiedad vbTecnico
   *
   * @param vbTecnico
   *          el vbTecnico para establecer
   */
  public void setVbTecnico(final boolean vbTecnico) {
    this.vbTecnico = vbTecnico;
  }

  /**
   * Obtiene la propiedad vbCoord
   *
   * @return el vbCoord
   */
  public boolean isVbCoord() {
    return vbCoord;
  }

  /**
   * Establece el valor de la propiedad vbCoord
   *
   * @param vbCoord
   *          el vbCoord para establecer
   */
  public void setVbCoord(final boolean vbCoord) {
    this.vbCoord = vbCoord;
  }

  /**
   * Obtiene la propiedad tieneExcl
   *
   * @return el tieneExcl
   */
  public boolean isTieneExcl() {
    return tieneExcl;
  }

  /**
   * Establece el valor de la propiedad tieneExcl
   *
   * @param tieneExcl
   *          el tieneExcl para establecer
   */
  public void setTieneExcl(final boolean tieneExcl) {
    this.tieneExcl = tieneExcl;
  }

  /**
   * Obtiene la propiedad accionHuman
   *
   * @return el accionHuman
   */
  public boolean isAccionHuman() {
    return accionHuman;
  }

  /**
   * Establece el valor de la propiedad accionHuman
   *
   * @param accionHuman
   *          el accionHuman para establecer
   */
  public void setAccionHuman(final boolean accionHuman) {
    this.accionHuman = accionHuman;
  }

  /**
   * Obtiene la propiedad cooperacion
   *
   * @return el cooperacion
   */
  public boolean isCooperacion() {
    return cooperacion;
  }

  /**
   * Establece el valor de la propiedad cooperacion
   *
   * @param cooperacion
   *          el cooperacion para establecer
   */
  public void setCooperacion(final boolean cooperacion) {
    this.cooperacion = cooperacion;
  }

  /**
   * Obtiene la propiedad formacion
   *
   * @return el formacion
   */
  public boolean isFormacion() {
    return formacion;
  }

  /**
   * Establece el valor de la propiedad formacion
   *
   * @param formacion
   *          el formacion para establecer
   */
  public void setFormacion(final boolean formacion) {
    this.formacion = formacion;
  }

  /**
   * Obtiene la propiedad educacion
   *
   * @return el educacion
   */
  public boolean isEducacion() {
    return educacion;
  }

  /**
   * Establece el valor de la propiedad educacion
   *
   * @param educacion
   *          el educacion para establecer
   */
  public void setEducacion(final boolean educacion) {
    this.educacion = educacion;
  }

  /**
   * Obtiene la propiedad africa
   *
   * @return el africa
   */
  public boolean isAfrica() {
    return africa;
  }

  /**
   * Establece el valor de la propiedad africa
   *
   * @param africa
   *          el africa para establecer
   */
  public void setAfrica(final boolean africa) {
    this.africa = africa;
  }

  /**
   * Obtiene la propiedad servSoc
   *
   * @return el servSoc
   */
  public boolean isServSoc() {
    return servSoc;
  }

  /**
   * Establece el valor de la propiedad servSoc
   *
   * @param servSoc
   *          el servSoc para establecer
   */
  public void setServSoc(final boolean servSoc) {
    this.servSoc = servSoc;
  }

  /**
   * Obtiene la propiedad otros
   *
   * @return el otros
   */
  public boolean isOtros() {
    return otros;
  }

  /**
   * Establece el valor de la propiedad otros
   *
   * @param otros
   *          el otros para establecer
   */
  public void setOtros(final boolean otros) {
    this.otros = otros;
  }

  /**
   * Obtiene la propiedad noEspec
   *
   * @return el noEspec
   */
  public boolean isNoEspec() {
    return noEspec;
  }

  /**
   * Establece el valor de la propiedad noEspec
   *
   * @param noEspec
   *          el noEspec para establecer
   */
  public void setNoEspec(final boolean noEspec) {
    this.noEspec = noEspec;
  }

  /**
   * Obtiene la propiedad almeria
   *
   * @return el almeria
   */
  public boolean getAlmeria() {
    return almeria;
  }

  /**
   * Establece el valor de la propiedad almeria
   *
   * @param almeria
   *          el almeria para establecer
   */
  public void setAlmeria(final boolean almeria) {
    this.almeria = almeria;
  }

  /**
   * Obtiene la propiedad cadiz
   *
   * @return el cadiz
   */
  public boolean getCadiz() {
    return cadiz;
  }

  /**
   * Establece el valor de la propiedad cadiz
   *
   * @param cadiz
   *          el cadiz para establecer
   */
  public void setCadiz(final boolean cadiz) {
    this.cadiz = cadiz;
  }

  /**
   * Obtiene la propiedad huelva
   *
   * @return el huelva
   */
  public boolean getHuelva() {
    return huelva;
  }

  /**
   * Establece el valor de la propiedad huelva
   *
   * @param huelva
   *          el huelva para establecer
   */
  public void setHuelva(final boolean huelva) {
    this.huelva = huelva;
  }

  /**
   * Obtiene la propiedad sevilla
   *
   * @return el sevilla
   */
  public boolean getSevilla() {
    return sevilla;
  }

  /**
   * Establece el valor de la propiedad sevilla
   *
   * @param sevilla
   *          el sevilla para establecer
   */
  public void setSevilla(final boolean sevilla) {
    this.sevilla = sevilla;
  }

  /**
   * Obtiene la propiedad cordoba
   *
   * @return el cordoba
   */
  public boolean getCordoba() {
    return cordoba;
  }

  /**
   * Establece el valor de la propiedad cordoba
   *
   * @param cordoba
   *          el cordoba para establecer
   */
  public void setCordoba(final boolean cordoba) {
    this.cordoba = cordoba;
  }

  /**
   * Obtiene la propiedad granada
   *
   * @return el granada
   */
  public boolean getGranada() {
    return granada;
  }

  /**
   * Establece el valor de la propiedad granada
   *
   * @param granada
   *          el granada para establecer
   */
  public void setGranada(final boolean granada) {
    this.granada = granada;
  }

  /**
   * Obtiene la propiedad jaen
   *
   * @return el jaen
   */
  public boolean getJaen() {
    return jaen;
  }

  /**
   * Establece el valor de la propiedad jaen
   *
   * @param jaen
   *          el jaen para establecer
   */
  public void setJaen(final boolean jaen) {
    this.jaen = jaen;
  }

  /**
   * Obtiene la propiedad malaga
   *
   * @return el malaga
   */
  public boolean getMalaga() {
    return malaga;
  }

  /**
   * Establece el valor de la propiedad malaga
   *
   * @param malaga
   *          el malaga para establecer
   */
  public void setMalaga(final boolean malaga) {
    this.malaga = malaga;
  }

  /**
   * Obtiene la propiedad listaContraparte
   *
   * @return el listaContraparte
   */
  public List<ContraparteDTO> getListaContraparte() {
    return listaContraparte;
  }

  /**
   * Establece el valor de la propiedad listaContraparte
   *
   * @param listaContraparte
   *          el listaContraparte para establecer
   */
  public void setListaContraparte(final List<ContraparteDTO> listaContraparte) {
    this.listaContraparte = listaContraparte;
  }

  /**
   * Obtiene la propiedad entidadSolicitante
   *
   * @return el entidadSolicitante
   */
  public EntidadSolicitanteDTO getEntidadSolicitante() {
    return entidadSolicitante;
  }

  /**
   * Establece el valor de la propiedad entidadSolicitante
   *
   * @param entidadSolicitante
   *          el entidadSolicitante para establecer
   */
  public void setEntidadSolicitante(final EntidadSolicitanteDTO entidadSolicitante) {
    this.entidadSolicitante = entidadSolicitante;
  }

  /**
   * Obtiene la propiedad listaAgrupacion
   *
   * @return el listaAgrupacion
   */
  public List<AgrupacionDTO> getListaAgrupacion() {
    return listaAgrupacion;
  }

  /**
   * Establece el valor de la propiedad listaAgrupacion
   *
   * @param listaAgrupacion
   *          el listaAgrupacion para establecer
   */
  public void setListaAgrupacion(final List<AgrupacionDTO> listaAgrupacion) {
    this.listaAgrupacion = listaAgrupacion;
  }

  /**
   * Obtiene la propiedad apellidosSol
   *
   * @return el apellidosSol
   */
  public String getApellidosSol() {
    return apellidosSol;
  }

  /**
   * Establece el valor de la propiedad apellidosSol
   *
   * @param apellidosSol
   *          el apellidosSol para establecer
   */
  public void setApellidosSol(final String apellidosSol) {
    this.apellidosSol = apellidosSol;
  }

  /**
   * Obtiene la propiedad nombreSol
   *
   * @return el nombreSol
   */
  public String getNombreSol() {
    return nombreSol;
  }

  /**
   * Establece el valor de la propiedad nombreSol
   *
   * @param nombreSol
   *          el nombreSol para establecer
   */
  public void setNombreSol(final String nombreSol) {
    this.nombreSol = nombreSol;
  }

  /**
   * Obtiene la propiedad nifSol
   *
   * @return el nifSol
   */
  public String getNifSol() {
    return nifSol;
  }

  /**
   * Establece el valor de la propiedad nifSol
   *
   * @param nifSol
   *          el nifSol para establecer
   */
  public void setNifSol(final String nifSol) {
    this.nifSol = nifSol;
  }

  /**
   * Obtiene la propiedad observaciones
   *
   * @return el observaciones
   */
  public String getObservaciones() {
    return observaciones;
  }

  /**
   * Establece el valor de la propiedad observaciones
   *
   * @param observaciones
   *          el observaciones para establecer
   */
  public void setObservaciones(final String observaciones) {
    this.observaciones = observaciones;
  }

  /**
   * Obtiene la propiedad motivoDesestimacion
   *
   * @return el motivoDesestimacion
   */
  public String getMotivoDesestimacion() {
    return motivoDesestimacion;
  }

  /**
   * Establece el valor de la propiedad motivoDesestimacion
   *
   * @param motivoDesestimacion
   *          el motivoDesestimacion para establecer
   */
  public void setMotivoDesestimacion(final String motivoDesestimacion) {
    this.motivoDesestimacion = motivoDesestimacion;
  }

  /**
   * Obtiene la propiedad numExpInterno
   *
   * @return el numExpInterno
   */
  public String getNumExpInterno() {
    return numExpInterno;
  }

  /**
   * Establece el valor de la propiedad numExpInterno
   *
   * @param numExpInterno
   *          el numExpInterno para establecer
   */
  public void setNumExpInterno(final String numExpInterno) {
    this.numExpInterno = numExpInterno;
  }

  /**
   * Obtiene la propiedad editarVBTecnico
   *
   * @return el editarVBTecnico
   */
  public boolean isEditarVBTecnico() {
    return editarVBTecnico;
  }

  /**
   * Establece el valor de la propiedad editarVBTecnico
   *
   * @param editarVBTecnico
   *          el editarVBTecnico para establecer
   */
  public void setEditarVBTecnico(final boolean editarVBTecnico) {
    this.editarVBTecnico = editarVBTecnico;
  }

  /**
   * Obtiene la propiedad editarVBCoordinador
   *
   * @return el editarVBCoordinador
   */
  public boolean isEditarVBCoordinador() {
    return editarVBCoordinador;
  }

  /**
   * Establece el valor de la propiedad editarVBCoordinador
   *
   * @param editarVBCoordinador
   *          el editarVBCoordinador para establecer
   */
  public void setEditarVBCoordinador(final boolean editarVBCoordinador) {
    this.editarVBCoordinador = editarVBCoordinador;
  }

  /**
   * Obtiene la propiedad prevRiesgos
   *
   * @return el prevRiesgos
   */
  public boolean isPrevRiesgos() {
    return prevRiesgos;
  }

  /**
   * Establece el valor de la propiedad prevRiesgos
   *
   * @param prevRiesgos
   *          el prevRiesgos para establecer
   */
  public void setPrevRiesgos(final boolean prevRiesgos) {
    this.prevRiesgos = prevRiesgos;
  }

  /**
   * Obtiene la propiedad fechaEnvSub
   *
   * @return el fechaEnvSub
   */
  public Date getFechaEnvSub() {
    return fechaEnvSub;
  }

  /**
   * Establece el valor de la propiedad fechaEnvSub
   *
   * @param fechaEnvSub
   *          el fechaEnvSub para establecer
   */
  public void setFechaEnvSub(final Date fechaEnvSub) {
    this.fechaEnvSub = fechaEnvSub;
  }

  /**
   * Obtiene la propiedad fechaLimite
   *
   * @return el fechaLimite
   */
  public Date getFechaLimite() {
    return fechaLimite;
  }

  /**
   * Establece el valor de la propiedad fechaLimite
   *
   * @param fechaLimite
   *          el fechaLimite para establecer
   */
  public void setFechaLimite(final Date fechaLimite) {
    this.fechaLimite = fechaLimite;
  }

  /**
   * Obtiene la propiedad fechaRecepSub
   *
   * @return el fechaRecepSub
   */
  public Date getFechaRecepSub() {
    return fechaRecepSub;
  }

  /**
   * Establece el valor de la propiedad fechaRecepSub
   *
   * @param fechaRecepSub
   *          el fechaRecepSub para establecer
   */
  public void setFechaRecepSub(final Date fechaRecepSub) {
    this.fechaRecepSub = fechaRecepSub;
  }

  /**
   * Obtiene la propiedad tituloProy
   *
   * @return el tituloProy
   */
  public String getTituloProy() {
    return tituloProy;
  }

  /**
   * Establece el valor de la propiedad tituloProy
   *
   * @param tituloProy
   *          el tituloProy para establecer
   */
  public void setTituloProy(final String tituloProy) {
    this.tituloProy = tituloProy;
  }

  /**
   * Obtiene la propiedad idSubsanacion
   *
   * @return el idSubsanacion
   */
  public Integer getIdSubsanacion() {
    return idSubsanacion;
  }

  /**
   * Establece el valor de la propiedad idSubsanacion
   *
   * @param idSubsanacion
   *          el idSubsanacion para establecer
   */
  public void setIdSubsanacion(final Integer idSubsanacion) {
    this.idSubsanacion = idSubsanacion;
  }

  /**
   * Obtiene la propiedad ostentaRepresentacion
   *
   * @return el ostentaRepresentacion
   */
  public boolean isOstentaRepresentacion() {
    return ostentaRepresentacion;
  }

  /**
   * Establece el valor de la propiedad ostentaRepresentacion
   *
   * @param ostentaRepresentacion
   *          el ostentaRepresentacion para establecer
   */
  public void setOstentaRepresentacion(final boolean ostentaRepresentacion) {
    this.ostentaRepresentacion = ostentaRepresentacion;
  }

  /**
   * Obtiene la propiedad tieneSub
   *
   * @return el tieneSub
   */
  public String getTieneSub() {
    return tieneSub;
  }

  /**
   * Establece el valor de la propiedad tieneSub
   *
   * @param tieneSub
   *          el tieneSub para establecer
   */
  public void setTieneSub(final String tieneSub) {
    this.tieneSub = tieneSub;
  }

  public String getNumOV() {
    return numOV;
  }

  public void setNumOV(final String numOV) {
    this.numOV = numOV;
  }

  public boolean isTienePermisosGuardado() {
    return tienePermisosGuardado;
  }

  public void setTienePermisosGuardado(final boolean tienePermisosGuardado) {
    this.tienePermisosGuardado = tienePermisosGuardado;
  }

  public boolean isTieneContrapartesLocal() {
    return tieneContrapartesLocal;
  }

  public void setTieneContrapartesLocal(final boolean tieneContrapartesLocal) {
    this.tieneContrapartesLocal = tieneContrapartesLocal;
  }

  public boolean isTieneAgrupacionesLocal() {
    return tieneAgrupacionesLocal;
  }

  public void setTieneAgrupacionesLocal(final boolean tieneAgrupacionesLocal) {
    this.tieneAgrupacionesLocal = tieneAgrupacionesLocal;
  }

  public Long getImpoTotal() {
    return impoTotal;
  }

  public void setImpoTotal(final Long impoTotal) {
    this.impoTotal = impoTotal;
  }

  public Long getImpoEntidadLider() {
    return impoEntidadLider;
  }

  public void setImpoEntidadLider(final Long impoEntidadLider) {
    this.impoEntidadLider = impoEntidadLider;
  }

  /**
   * Obtiene la propiedad pasaAValoracion
   *
   * @return el pasaAValoracion
   */
  public String getPasaAValoracion() {
    return pasaAValoracion;
  }

  /**
   * Establece el valor de la propiedad pasaAValoracion
   *
   * @param pasaAValoracion
   *          el pasaAValoracion para establecer
   */
  public void setPasaAValoracion(final String pasaAValoracion) {
    this.pasaAValoracion = pasaAValoracion;
  }

  /**
   * Obtiene la propiedad presupFormu
   *
   * @return el presupFormu
   */
  public String getPresupFormu() {
    return presupFormu;
  }

  /**
   * Establece el valor de la propiedad presupFormu
   *
   * @param presupFormu
   *          el presupFormu para establecer
   */
  public void setPresupFormu(final String presupFormu) {
    this.presupFormu = presupFormu;
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
