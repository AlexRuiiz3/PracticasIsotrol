package es.juntadeandalucia.aacid.comuntramitacion.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author isotrol
 *
 */
public class SolicitudDTO implements Comparable<SolicitudDTO> {

  /** entidad */
  private String entidad;
  /** numExpediente */
  private Long numExpediente;
  /** cif */
  private String cif;
  /** importe */
  private Long importe;
  /** ongd */
  private String ongd;
  /** impSol */
  private double impSol;
  /** presupuestoTotal */
  private double presupuestoTotal;
  /** localizacion */
  private String localizacion;
  /** plazoEjecucion */
  private String plazoEjecucion;
  /** serviciosSocialesBasicos */
  private String serviciosSocialesBasicos;
  /** numeroIdentificativoExpediente */
  private String numeroIdentificativoExpediente;
  /** casusas de exclusión */
  private String causasExc;
  /** numero expediente en trewa */
  private String numExpTrewa;
  /** causas de subsanacion */
  private String causasSubsanacion;
  /** CIF de la entidad */
  private String cifEntidad;

  /** campos para repetible paises */
  private String pais1;
  private String pais2;
  private String pais3;

  /***********************/
  /** campos subsanacion */
  /***********************/
  /** idSolicitud */
  private Long idSolicitud;
  /** siglas */
  private String siglas;
  /** codigo identificativo tramitador viejo */
  private String codIdentificativo;
  /** Solicitante Tipo Via */
  private String soliTipoVia;
  /** Solicitante nombre Via */
  private String soliNombreVia;
  /** Solicitante numero Via */
  private String soliNumeroVia;
  /** Solicitante letra Via */
  private String soliLetraVia;
  /** Solicitante km Via */
  private String soliKmVia;
  /** Solicitante bloque Via */
  private String soliBloqueVia;
  /** Solicitante portal Via */
  private String soliPortalVia;
  /** Solicitante escalera Via */
  private String soliEscaleraVia;
  /** Solicitante piso Via */
  private String soliPisoVia;
  /** Solicitante puerta Via */
  private String soliPuertaVia;
  /** Solicitante provincia */
  private String soliProvincia;
  /** Solicitante municipio */
  private String soliMunicipio;
  /** poblacion */
  private String poblacion;
  /** Solicitante CP */
  private int soliCP;
  /** Solicitante Telefono */
  private int soliTelefono;
  /** Solicitante Movil */
  private int soliMovil;
  /** Solicitante Email */
  private String soliEmail;

  /** Representante En Calidad de */
  private String repreEnCalidadDe;
  /** Representante sexo */
  private String repreSexo;

  private List<AgrupacionDTO> agrupacion;
  /* NOTIFICACIÓN ELECTRÓNICA OBLIGATORIA */
  private boolean altaNotifica;

  /** titulo */
  private String titulo;
  /** País de actividad */
  private String pais;
  /** Finalidad */
  private String finalidad;

  /** Respuesta al requerimiento subsanacion */
  private String respReqSubsanacion;

  /** Documentacion */
  private boolean checkDoc1;
  private boolean checkDoc2;
  private boolean checkDoc3;
  private boolean checkDoc4;
  private boolean checkDoc5;
  private boolean checkDoc6;
  private boolean checkDoc7;
  private boolean checkDoc8;
  private boolean checkDoc9;
  private boolean checkDoc10;
  private boolean checkDoc11;
  private boolean checkDoc12;
  private boolean checkDoc13;
  private boolean checkDoc14;
  private boolean checkDoc15;
  private boolean checkDoc16;
  private boolean checkDoc17;
  private boolean checkDoc18;
  private boolean checkDoc19;
  private boolean checkDoc20;
  private boolean checkDoc21;
  private boolean checkDoc22;
  private boolean checkDoc23;
  private boolean checkDoc24;
  private boolean checkDoc25;
  private boolean checkDoc26;
  private boolean checkDoc27;
  private boolean checkDoc28;
  private boolean checkDoc29;
  private boolean checkDoc30;
  private boolean checkDoc31;
  private boolean checkDoc32;

  /** Documentación Administración JA */
  private List<DocPoderAdminJADTO> docsPoderAdminJA;
  private String autorizaJunta0AdmDocumento;
  private String autorizaJunta0AdmConsejeria;
  private Date autorizaJunta0AdmFecha;
  private String autorizaJunta0AdmProcedimiento;
  private String autorizaJunta1AdmDocumento;
  private String autorizaJunta1AdmConsejeria;
  private Date autorizaJunta1AdmFecha;
  private String autorizaJunta1AdmProcedimiento;

  /** Documentación otra Administración */
  private List<DocPoderOtraAdminDTO> docsPoderOtraAdmin;
  private String autorizaGestor0OtrasDocumento;
  private String autorizaGestor0OtrasAdministracion;
  private Date autorizaGestor0OtrasFecha;
  private String autorizaGestor0OtrasProcedimiento;
  private String autorizaGestor1OtrasDocumento;
  private String autorizaGestor1OtrasAdministracion;
  private Date autorizaGestor1OtrasFecha;
  private String autorizaGestor1OtrasProcedimiento;

  /** Lugar y firma */
  private String lugarFirma;
  private String firma;
  /** email */
  private String email;
  private String presupuesto;
  private String subvencion;
  private String subvencionSolicitada;

  private String puntuacion;
  private BigDecimal puntuacionNum;
  private BigDecimal plazo;
  private String desestimientos;
  /** Si es nulo no está definido */
  private Boolean beneficiaria;

  /**
   * Constructor por defcto
   */
  public SolicitudDTO() {
  }

  /**
   * @param idSolicitud
   * @param entidad
   * @param titulo
   */
  public SolicitudDTO(final Long idSolicitud, final String entidad, final String titulo, final String codIdentificativo, final String cifEntidad,
      final String pais, final String email, final String pais1, final String pais2, final String pais3) {
    this.idSolicitud = idSolicitud;
    this.entidad = entidad;
    this.titulo = titulo;
    this.codIdentificativo = codIdentificativo;
    this.cifEntidad = cifEntidad;
    this.pais = pais;
    this.email = email;
    this.pais1 = pais1;
    this.pais2 = pais2;
    this.pais3 = pais3;
  }

  /**
   * @param idSolicitud
   * @param entidad
   * @param titulo
   * @param numExpediente
   * @param poblacion
   * @param cif
   * @param importe
   */
  public SolicitudDTO(final Long idSolicitud, final Long numExpediente, final String titulo, final String poblacion, final String entidad, final String cif,
      final Long importe) {
    this.idSolicitud = idSolicitud;
    this.numExpediente = numExpediente;
    this.titulo = titulo;
    this.poblacion = poblacion;
    this.entidad = entidad;
    this.cif = cif;
    this.importe = importe;
  }

  /**
   * @param idSolicitud
   * @param entidad
   * @param titulo
   * @param numExpediente
   * @param poblacion
   * @param cif
   * @param importe
   * @param codigo
   */
  public SolicitudDTO(final Long idSolicitud, final Long numExpediente, final String titulo, final String poblacion, final String entidad, final String cif,
      final Long importe, final String codigo, final BigDecimal plazo) {
    this.idSolicitud = idSolicitud;
    this.numExpediente = numExpediente;
    this.titulo = titulo;
    this.poblacion = poblacion;
    this.entidad = entidad;
    this.cif = cif;
    this.importe = importe;
    codIdentificativo = codigo;
    this.plazo = plazo;
  }

  /**
   * @param idSolicitud
   * @param entidad
   * @param titulo
   * @param numExpediente
   * @param poblacion
   * @param cif
   * @param importe
   * @param codigo
   */
  public SolicitudDTO(final Long idSolicitud, final Long numExpediente, final String titulo, final String poblacion, final String entidad, final String cif,
      final Long importe, final String codigo) {
    this.idSolicitud = idSolicitud;
    this.numExpediente = numExpediente;
    this.titulo = titulo;
    this.poblacion = poblacion;
    this.entidad = entidad;
    this.cif = cif;
    this.importe = importe;
    codIdentificativo = codigo;
  }

  public SolicitudDTO(final String numeroIdentificativoExpediente, final String titulo, final String ongd, final String cif, final double impSol,
      final double presupuestoTotal, final String localizacion, final String plazoEjecucion, final String serviciosSocialesBasicos) {
    this.titulo = titulo;
    this.ongd = ongd;
    this.cif = cif;
    this.impSol = impSol;
    this.presupuestoTotal = presupuestoTotal;
    this.localizacion = localizacion;
    this.plazoEjecucion = plazoEjecucion;
    this.serviciosSocialesBasicos = serviciosSocialesBasicos;
    this.numeroIdentificativoExpediente = numeroIdentificativoExpediente;
  }

  /**
   * Constructor para la consulta que nos trae los datos modificados en la subsanación
   */
  public SolicitudDTO(final Long idSolicitud, final String siglas, final String codIdentificativo, final String soliTipoVia, final String soliNombreVia,
      final String soliNumeroVia, final String soliLetraVia, final String soliKmVia, final String soliBloqueVia, final String soliPortalVia,
      final String soliEscaleraVia, final String soliPisoVia, final String soliPuertaVia, final String soliProvincia, final String soliMunicipio,
      final String poblacion, final int soliCP, final int soliTelefono, final int soliMovil, final String soliEmail, final String repreEnCalidadDe,
      final String repreSexo, final String repetAgrupaciones0AgrupNombre, final String repetAgrupaciones0AgrupSiglas, final String repetAgrupaciones0AgrupRacda,
      final String repetAgrupaciones0AgrupIdentificador, final String repetAgrupaciones1AgrupNombre, final String repetAgrupaciones1AgrupSiglas,
      final String repetAgrupaciones1AgrupRacda, final String repetAgrupaciones1AgrupIdentificador, final String repetAgrupaciones2AgrupNombre,
      final String repetAgrupaciones2AgrupSiglas, final String repetAgrupaciones2AgrupRacda, final String repetAgrupaciones2AgrupIdentificador,
      final boolean altaNotifica, final String titulo, final String pais, final String finalidad, final String respReqSubsanacion, final boolean checkDoc1,
      final boolean checkDoc2, final boolean checkDoc3, final boolean checkDoc4, final boolean checkDoc5, final boolean checkDoc6, final boolean checkDoc7,
      final boolean checkDoc8, final boolean checkDoc9, final boolean checkDoc10, final boolean checkDoc11, final boolean checkDoc12, final boolean checkDoc13,
      final boolean checkDoc14, final boolean checkDoc15, final boolean checkDoc16, final boolean checkDoc17, final boolean checkDoc18,
      final boolean checkDoc19, final boolean checkDoc20, final boolean checkDoc21, final boolean checkDoc22, final boolean checkDoc23,
      final boolean checkDoc24, final boolean checkDoc25, final boolean checkDoc26, final boolean checkDoc27, final boolean checkDoc28,
      final boolean checkDoc29, final boolean checkDoc30, final boolean checkDoc31, final boolean checkDoc32, final String autorizaJunta0AdmDocumento,
      final String autorizaJunta0AdmConsejeria, final Date autorizaJunta0AdmFecha, final String autorizaJunta0AdmProcedimiento,
      final String autorizaJunta1AdmDocumento, final String autorizaJunta1AdmConsejeria, final Date autorizaJunta1AdmFecha,
      final String autorizaJunta1AdmProcedimiento, final String autorizaGestor0OtrasDocumento, final String autorizaGestor0OtrasAdministracion,
      final Date autorizaGestor0OtrasFecha, final String autorizaGestor0OtrasProcedimiento, final String autorizaGestor1OtrasDocumento,
      final String autorizaGestor1OtrasAdministracion, final Date autorizaGestor1OtrasFecha, final String autorizaGestor1OtrasProcedimiento,
      final String lugarFirma, final String firma) {
    this.idSolicitud = idSolicitud;
    this.siglas = siglas;
    this.codIdentificativo = codIdentificativo;
    this.soliTipoVia = soliTipoVia;
    this.soliNombreVia = soliNombreVia;
    this.soliNumeroVia = soliNumeroVia;
    this.soliLetraVia = soliLetraVia;
    this.soliKmVia = soliKmVia;
    this.soliBloqueVia = soliBloqueVia;
    this.soliPortalVia = soliPortalVia;
    this.soliEscaleraVia = soliEscaleraVia;
    this.soliPisoVia = soliPisoVia;
    this.soliPuertaVia = soliPuertaVia;
    this.soliProvincia = soliProvincia;
    this.soliMunicipio = soliMunicipio;
    this.poblacion = poblacion;
    this.soliCP = soliCP;
    this.soliTelefono = soliTelefono;
    this.soliMovil = soliMovil;
    this.soliEmail = soliEmail;
    this.repreEnCalidadDe = repreEnCalidadDe;
    this.repreSexo = repreSexo;
    this.altaNotifica = altaNotifica;
    this.titulo = titulo;
    this.finalidad = finalidad;
    this.respReqSubsanacion = respReqSubsanacion;
    this.checkDoc1 = checkDoc1;
    this.checkDoc2 = checkDoc2;
    this.checkDoc3 = checkDoc3;
    this.checkDoc4 = checkDoc4;
    this.checkDoc5 = checkDoc5;
    this.checkDoc6 = checkDoc6;
    this.checkDoc7 = checkDoc7;
    this.checkDoc8 = checkDoc8;
    this.checkDoc9 = checkDoc9;
    this.checkDoc10 = checkDoc10;
    this.checkDoc11 = checkDoc11;
    this.checkDoc12 = checkDoc12;
    this.checkDoc13 = checkDoc13;
    this.checkDoc14 = checkDoc14;
    this.checkDoc15 = checkDoc15;
    this.checkDoc16 = checkDoc16;
    this.checkDoc17 = checkDoc17;
    this.checkDoc18 = checkDoc18;
    this.checkDoc19 = checkDoc19;
    this.checkDoc20 = checkDoc20;
    this.checkDoc21 = checkDoc21;
    this.checkDoc22 = checkDoc22;
    this.checkDoc23 = checkDoc23;
    this.checkDoc24 = checkDoc24;
    this.checkDoc25 = checkDoc25;
    this.checkDoc26 = checkDoc26;
    this.checkDoc27 = checkDoc27;
    this.checkDoc28 = checkDoc28;
    this.checkDoc29 = checkDoc29;
    this.checkDoc30 = checkDoc30;
    this.checkDoc31 = checkDoc31;
    this.checkDoc32 = checkDoc32;
    this.autorizaJunta0AdmDocumento = autorizaJunta0AdmDocumento;
    this.autorizaJunta0AdmConsejeria = autorizaJunta0AdmConsejeria;
    this.autorizaJunta0AdmFecha = autorizaJunta0AdmFecha;
    this.autorizaJunta0AdmProcedimiento = autorizaJunta0AdmProcedimiento;
    this.autorizaJunta1AdmDocumento = autorizaJunta1AdmDocumento;
    this.autorizaJunta1AdmConsejeria = autorizaJunta1AdmConsejeria;
    this.autorizaJunta1AdmFecha = autorizaJunta1AdmFecha;
    this.autorizaJunta1AdmProcedimiento = autorizaJunta1AdmProcedimiento;
    this.autorizaGestor0OtrasDocumento = autorizaGestor0OtrasDocumento;
    this.autorizaGestor0OtrasAdministracion = autorizaGestor0OtrasAdministracion;
    this.autorizaGestor0OtrasFecha = autorizaGestor0OtrasFecha;
    this.autorizaGestor0OtrasProcedimiento = autorizaGestor0OtrasProcedimiento;
    this.autorizaGestor1OtrasDocumento = autorizaGestor1OtrasDocumento;
    this.autorizaGestor1OtrasAdministracion = autorizaGestor1OtrasAdministracion;
    this.autorizaGestor1OtrasFecha = autorizaGestor1OtrasFecha;
    this.autorizaGestor1OtrasProcedimiento = autorizaGestor1OtrasProcedimiento;
    this.lugarFirma = lugarFirma;
    this.firma = firma;
    this.pais = pais;
  }

  /**
   * Constructor con los datos de relación provisional de concesión y desestimiento
   *
   * @param numExpTrewa
   * @param codIdentificativo
   * @param titulo
   * @param localizacion
   * @param entidad
   * @param cif
   * @param puntuacion
   * @param desestimientos
   */
  public SolicitudDTO(final String numExpTrewa, final String codIdentificativo, final String titulo, final String localizacion, final String entidad,
      final String cif, final String puntuacion, final String desestimientos) {
    this.numExpTrewa = numExpTrewa;
    this.codIdentificativo = codIdentificativo;
    this.titulo = titulo;
    this.localizacion = localizacion;
    this.entidad = entidad;
    this.cif = cif;
    this.puntuacion = puntuacion;
    this.desestimientos = desestimientos;
  }

  /**
   * @return the idSolicitud
   */
  public Long getIdSolicitud() {
    return idSolicitud;
  }

  /**
   * @param idSolicitud
   *          the idSolicitud to set
   */
  public void setIdSolicitud(final Long idSolicitud) {
    this.idSolicitud = idSolicitud;
  }

  /**
   * @return the entidad
   */
  public String getEntidad() {
    return entidad;
  }

  /**
   * @param entidad
   *          the entidad to set
   */
  public void setEntidad(final String entidad) {
    this.entidad = entidad;
  }

  /**
   * @return the titulo
   */
  public String getTitulo() {
    return titulo;
  }

  /**
   * @param titulo
   *          the titulo to set
   */
  public void setTitulo(final String titulo) {
    this.titulo = titulo;
  }

  /**
   * @return the numExpediente
   */
  public Long getNumExpediente() {
    return numExpediente;
  }

  /**
   * @param numExpediente
   *          the numExpediente to set
   */
  public void setNumExpediente(final Long numExpediente) {
    this.numExpediente = numExpediente;
  }

  /**
   * @return the poblacion
   */
  public String getPoblacion() {
    return poblacion;
  }

  /**
   * @param poblacion
   *          the poblacion to set
   */
  public void setPoblacion(final String poblacion) {
    this.poblacion = poblacion;
  }

  /**
   * @return the cif
   */
  public String getCif() {
    return cif;
  }

  /**
   * @param cif
   *          the cif to set
   */
  public void setCif(final String cif) {
    this.cif = cif;
  }

  /**
   * @return the importe
   */
  public Long getImporte() {
    return importe;
  }

  /**
   * @param importe
   *          the importe to set
   */
  public void setImporte(final Long importe) {
    this.importe = importe;
  }

  /**
   * @return the codIdentificativo
   */
  public String getCodIdentificativo() {
    return codIdentificativo;
  }

  /**
   * @param codIdentificativo
   *          the codIdentificativo to set
   */
  public void setCodIdentificativo(final String codIdentificativo) {
    this.codIdentificativo = codIdentificativo;
  }

  public String getOngd() {
    return ongd;
  }

  public void setOngd(final String ongd) {
    this.ongd = ongd;
  }

  public double getImpSol() {
    return impSol;
  }

  public void setImpSol(final double impSol) {
    this.impSol = impSol;
  }

  public double getPresupuestoTotal() {
    return presupuestoTotal;
  }

  public void setPresupuestoTotal(final double presupuestoTotal) {
    this.presupuestoTotal = presupuestoTotal;
  }

  public String getLocalizacion() {
    return localizacion;
  }

  public void setLocalizacion(final String localizacion) {
    this.localizacion = localizacion;
  }

  public String getPlazoEjecucion() {
    return plazoEjecucion;
  }

  public void setPlazoEjecucion(final String plazoEjecucion) {
    this.plazoEjecucion = plazoEjecucion;
  }

  public String getServiciosSocialesBasicos() {
    return serviciosSocialesBasicos;
  }

  public void setServiciosSocialesBasicos(final String serviciosSocialesBasicos) {
    this.serviciosSocialesBasicos = serviciosSocialesBasicos;
  }

  public String getNumeroIdentificativoExpediente() {
    return numeroIdentificativoExpediente;
  }

  public void setNumeroIdentificativoExpediente(final String numeroIdentificativoExpediente) {
    this.numeroIdentificativoExpediente = numeroIdentificativoExpediente;
  }

  public String getCausasExc() {
    return causasExc;
  }

  public void setCausasExc(final String causasExc) {
    this.causasExc = causasExc;
  }

  public String getNumExpTrewa() {
    return numExpTrewa;
  }

  public void setNumExpTrewa(final String numExpTrewa) {
    this.numExpTrewa = numExpTrewa;
  }

  public String getCausasSubsanacion() {
    return causasSubsanacion;
  }

  public void setCausasSubsanacion(final String causasSubsanacion) {
    this.causasSubsanacion = causasSubsanacion;
  }

  /**
   * Obtiene la propiedad cifEntidad
   *
   * @return el cifEntidad
   */
  public String getCifEntidad() {
    return cifEntidad;
  }

  /**
   * Establece el valor de la propiedad cifEntidad
   *
   * @param cifEntidad
   *          el cifEntidad para establecer
   */
  public void setCifEntidad(final String cifEntidad) {
    this.cifEntidad = cifEntidad;
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
   * Obtiene la propiedad siglas
   *
   * @return el siglas
   */
  public String getSiglas() {
    return siglas;
  }

  /**
   * Establece el valor de la propiedad siglas
   *
   * @param siglas
   *          el siglas para establecer
   */
  public void setSiglas(final String siglas) {
    this.siglas = siglas;
  }

  /**
   * Obtiene la propiedad soliTipoVia
   *
   * @return el soliTipoVia
   */
  public String getSoliTipoVia() {
    return soliTipoVia;
  }

  /**
   * Establece el valor de la propiedad soliTipoVia
   *
   * @param soliTipoVia
   *          el soliTipoVia para establecer
   */
  public void setSoliTipoVia(final String soliTipoVia) {
    this.soliTipoVia = soliTipoVia;
  }

  /**
   * Obtiene la propiedad soliNombreVia
   *
   * @return el soliNombreVia
   */
  public String getSoliNombreVia() {
    return soliNombreVia;
  }

  /**
   * Establece el valor de la propiedad soliNombreVia
   *
   * @param soliNombreVia
   *          el soliNombreVia para establecer
   */
  public void setSoliNombreVia(final String soliNombreVia) {
    this.soliNombreVia = soliNombreVia;
  }

  /**
   * Obtiene la propiedad soliNumeroVia
   *
   * @return el soliNumeroVia
   */
  public String getSoliNumeroVia() {
    return soliNumeroVia;
  }

  /**
   * Establece el valor de la propiedad soliNumeroVia
   *
   * @param soliNumeroVia
   *          el soliNumeroVia para establecer
   */
  public void setSoliNumeroVia(final String soliNumeroVia) {
    this.soliNumeroVia = soliNumeroVia;
  }

  /**
   * Obtiene la propiedad soliLetraVia
   *
   * @return el soliLetraVia
   */
  public String getSoliLetraVia() {
    return soliLetraVia;
  }

  /**
   * Establece el valor de la propiedad soliLetraVia
   *
   * @param soliLetraVia
   *          el soliLetraVia para establecer
   */
  public void setSoliLetraVia(final String soliLetraVia) {
    this.soliLetraVia = soliLetraVia;
  }

  /**
   * Obtiene la propiedad soliKmVia
   *
   * @return el soliKmVia
   */
  public String getSoliKmVia() {
    return soliKmVia;
  }

  /**
   * Establece el valor de la propiedad soliKmVia
   *
   * @param soliKmVia
   *          el soliKmVia para establecer
   */
  public void setSoliKmVia(final String soliKmVia) {
    this.soliKmVia = soliKmVia;
  }

  /**
   * Obtiene la propiedad soliBloqueVia
   *
   * @return el soliBloqueVia
   */
  public String getSoliBloqueVia() {
    return soliBloqueVia;
  }

  /**
   * Establece el valor de la propiedad soliBloqueVia
   *
   * @param soliBloqueVia
   *          el soliBloqueVia para establecer
   */
  public void setSoliBloqueVia(final String soliBloqueVia) {
    this.soliBloqueVia = soliBloqueVia;
  }

  /**
   * Obtiene la propiedad soliPortalVia
   *
   * @return el soliPortalVia
   */
  public String getSoliPortalVia() {
    return soliPortalVia;
  }

  /**
   * Establece el valor de la propiedad soliPortalVia
   *
   * @param soliPortalVia
   *          el soliPortalVia para establecer
   */
  public void setSoliPortalVia(final String soliPortalVia) {
    this.soliPortalVia = soliPortalVia;
  }

  /**
   * Obtiene la propiedad soliEscaleraVia
   *
   * @return el soliEscaleraVia
   */
  public String getSoliEscaleraVia() {
    return soliEscaleraVia;
  }

  /**
   * Establece el valor de la propiedad soliEscaleraVia
   *
   * @param soliEscaleraVia
   *          el soliEscaleraVia para establecer
   */
  public void setSoliEscaleraVia(final String soliEscaleraVia) {
    this.soliEscaleraVia = soliEscaleraVia;
  }

  /**
   * Obtiene la propiedad soliPisoVia
   *
   * @return el soliPisoVia
   */
  public String getSoliPisoVia() {
    return soliPisoVia;
  }

  /**
   * Establece el valor de la propiedad soliPisoVia
   *
   * @param soliPisoVia
   *          el soliPisoVia para establecer
   */
  public void setSoliPisoVia(final String soliPisoVia) {
    this.soliPisoVia = soliPisoVia;
  }

  /**
   * Obtiene la propiedad soliPuertaVia
   *
   * @return el soliPuertaVia
   */
  public String getSoliPuertaVia() {
    return soliPuertaVia;
  }

  /**
   * Establece el valor de la propiedad soliPuertaVia
   *
   * @param soliPuertaVia
   *          el soliPuertaVia para establecer
   */
  public void setSoliPuertaVia(final String soliPuertaVia) {
    this.soliPuertaVia = soliPuertaVia;
  }

  /**
   * Obtiene la propiedad soliProvincia
   *
   * @return el soliProvincia
   */
  public String getSoliProvincia() {
    return soliProvincia;
  }

  /**
   * Establece el valor de la propiedad soliProvincia
   *
   * @param soliProvincia
   *          el soliProvincia para establecer
   */
  public void setSoliProvincia(final String soliProvincia) {
    this.soliProvincia = soliProvincia;
  }

  /**
   * Obtiene la propiedad soliMunicipio
   *
   * @return el soliMunicipio
   */
  public String getSoliMunicipio() {
    return soliMunicipio;
  }

  /**
   * Establece el valor de la propiedad soliMunicipio
   *
   * @param soliMunicipio
   *          el soliMunicipio para establecer
   */
  public void setSoliMunicipio(final String soliMunicipio) {
    this.soliMunicipio = soliMunicipio;
  }

  /**
   * Obtiene la propiedad soliCP
   *
   * @return el soliCP
   */
  public int getSoliCP() {
    return soliCP;
  }

  /**
   * Establece el valor de la propiedad soliCP
   *
   * @param soliCP
   *          el soliCP para establecer
   */
  public void setSoliCP(final int soliCP) {
    this.soliCP = soliCP;
  }

  /**
   * Obtiene la propiedad soliTelefono
   *
   * @return el soliTelefono
   */
  public int getSoliTelefono() {
    return soliTelefono;
  }

  /**
   * Establece el valor de la propiedad soliTelefono
   *
   * @param soliTelefono
   *          el soliTelefono para establecer
   */
  public void setSoliTelefono(final int soliTelefono) {
    this.soliTelefono = soliTelefono;
  }

  /**
   * Obtiene la propiedad soliMovil
   *
   * @return el soliMovil
   */
  public int getSoliMovil() {
    return soliMovil;
  }

  /**
   * Establece el valor de la propiedad soliMovil
   *
   * @param soliMovil
   *          el soliMovil para establecer
   */
  public void setSoliMovil(final int soliMovil) {
    this.soliMovil = soliMovil;
  }

  /**
   * Obtiene la propiedad soliEmail
   *
   * @return el soliEmail
   */
  public String getSoliEmail() {
    return soliEmail;
  }

  /**
   * Establece el valor de la propiedad soliEmail
   *
   * @param soliEmail
   *          el soliEmail para establecer
   */
  public void setSoliEmail(final String soliEmail) {
    this.soliEmail = soliEmail;
  }

  /**
   * Obtiene la propiedad repreEnCalidadDe
   *
   * @return el repreEnCalidadDe
   */
  public String getRepreEnCalidadDe() {
    return repreEnCalidadDe;
  }

  /**
   * Establece el valor de la propiedad repreEnCalidadDe
   *
   * @param repreEnCalidadDe
   *          el repreEnCalidadDe para establecer
   */
  public void setRepreEnCalidadDe(final String repreEnCalidadDe) {
    this.repreEnCalidadDe = repreEnCalidadDe;
  }

  /**
   * Obtiene la propiedad repreSexo
   *
   * @return el repreSexo
   */
  public String getRepreSexo() {
    return repreSexo;
  }

  /**
   * Establece el valor de la propiedad repreSexo
   *
   * @param repreSexo
   *          el repreSexo para establecer
   */
  public void setRepreSexo(final String repreSexo) {
    this.repreSexo = repreSexo;
  }

  /**
   * Obtiene la propiedad agrupacion
   *
   * @return el agrupacion
   */
  public List<AgrupacionDTO> getAgrupacion() {
    return agrupacion;
  }

  /**
   * Establece el valor de la propiedad agrupacion
   *
   * @param agrupacion
   *          el agrupacion para establecer
   */
  public void setAgrupacion(final List<AgrupacionDTO> agrupacion) {
    this.agrupacion = agrupacion;
  }

  /**
   * Obtiene la propiedad altaNotifica
   *
   * @return el altaNotifica
   */
  public boolean isAltaNotifica() {
    return altaNotifica;
  }

  /**
   * Establece el valor de la propiedad altaNotifica
   *
   * @param altaNotifica
   *          el altaNotifica para establecer
   */
  public void setAltaNotifica(final boolean altaNotifica) {
    this.altaNotifica = altaNotifica;
  }

  /**
   * Obtiene la propiedad respReqSubsanacion
   *
   * @return el respReqSubsanacion
   */
  public String getRespReqSubsanacion() {
    return respReqSubsanacion;
  }

  /**
   * Establece el valor de la propiedad respReqSubsanacion
   *
   * @param respReqSubsanacion
   *          el respReqSubsanacion para establecer
   */
  public void setRespReqSubsanacion(final String respReqSubsanacion) {
    this.respReqSubsanacion = respReqSubsanacion;
  }

  /**
   * Obtiene la propiedad checkDoc1
   *
   * @return el checkDoc1
   */
  public boolean isCheckDoc1() {
    return checkDoc1;
  }

  /**
   * Establece el valor de la propiedad checkDoc1
   *
   * @param checkDoc1
   *          el checkDoc1 para establecer
   */
  public void setCheckDoc1(final boolean checkDoc1) {
    this.checkDoc1 = checkDoc1;
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
   * Establece el valor de la propiedad finalidad
   *
   * @param finalidad
   *          el finalidad para establecer
   */
  public void setFinalidad(final String finalidad) {
    this.finalidad = finalidad;
  }

  /**
   * Obtiene la propiedad checkDoc2
   *
   * @return el checkDoc2
   */
  public boolean isCheckDoc2() {
    return checkDoc2;
  }

  /**
   * Establece el valor de la propiedad checkDoc2
   *
   * @param checkDoc2
   *          el checkDoc2 para establecer
   */
  public void setCheckDoc2(final boolean checkDoc2) {
    this.checkDoc2 = checkDoc2;
  }

  /**
   * Obtiene la propiedad checkDoc3
   *
   * @return el checkDoc3
   */
  public boolean isCheckDoc3() {
    return checkDoc3;
  }

  /**
   * Establece el valor de la propiedad checkDoc3
   *
   * @param checkDoc3
   *          el checkDoc3 para establecer
   */
  public void setCheckDoc3(final boolean checkDoc3) {
    this.checkDoc3 = checkDoc3;
  }

  /**
   * Obtiene la propiedad checkDoc4
   *
   * @return el checkDoc4
   */
  public boolean isCheckDoc4() {
    return checkDoc4;
  }

  /**
   * Establece el valor de la propiedad checkDoc4
   *
   * @param checkDoc4
   *          el checkDoc4 para establecer
   */
  public void setCheckDoc4(final boolean checkDoc4) {
    this.checkDoc4 = checkDoc4;
  }

  /**
   * Obtiene la propiedad checkDoc5
   *
   * @return el checkDoc5
   */
  public boolean isCheckDoc5() {
    return checkDoc5;
  }

  /**
   * Establece el valor de la propiedad checkDoc5
   *
   * @param checkDoc5
   *          el checkDoc5 para establecer
   */
  public void setCheckDoc5(final boolean checkDoc5) {
    this.checkDoc5 = checkDoc5;
  }

  /**
   * Obtiene la propiedad checkDoc6
   *
   * @return el checkDoc6
   */
  public boolean isCheckDoc6() {
    return checkDoc6;
  }

  /**
   * Establece el valor de la propiedad checkDoc6
   *
   * @param checkDoc6
   *          el checkDoc6 para establecer
   */
  public void setCheckDoc6(final boolean checkDoc6) {
    this.checkDoc6 = checkDoc6;
  }

  /**
   * Obtiene la propiedad checkDoc7
   *
   * @return el checkDoc7
   */
  public boolean isCheckDoc7() {
    return checkDoc7;
  }

  /**
   * Establece el valor de la propiedad checkDoc7
   *
   * @param checkDoc7
   *          el checkDoc7 para establecer
   */
  public void setCheckDoc7(final boolean checkDoc7) {
    this.checkDoc7 = checkDoc7;
  }

  /**
   * Obtiene la propiedad checkDoc8
   *
   * @return el checkDoc8
   */
  public boolean isCheckDoc8() {
    return checkDoc8;
  }

  /**
   * Establece el valor de la propiedad checkDoc8
   *
   * @param checkDoc8
   *          el checkDoc8 para establecer
   */
  public void setCheckDoc8(final boolean checkDoc8) {
    this.checkDoc8 = checkDoc8;
  }

  /**
   * Obtiene la propiedad checkDoc9
   *
   * @return el checkDoc9
   */
  public boolean isCheckDoc9() {
    return checkDoc9;
  }

  /**
   * Establece el valor de la propiedad checkDoc9
   *
   * @param checkDoc9
   *          el checkDoc9 para establecer
   */
  public void setCheckDoc9(final boolean checkDoc9) {
    this.checkDoc9 = checkDoc9;
  }

  /**
   * Obtiene la propiedad checkDoc10
   *
   * @return el checkDoc10
   */
  public boolean isCheckDoc10() {
    return checkDoc10;
  }

  /**
   * Establece el valor de la propiedad checkDoc10
   *
   * @param checkDoc10
   *          el checkDoc10 para establecer
   */
  public void setCheckDoc10(final boolean checkDoc10) {
    this.checkDoc10 = checkDoc10;
  }

  /**
   * Obtiene la propiedad checkDoc11
   *
   * @return el checkDoc11
   */
  public boolean isCheckDoc11() {
    return checkDoc11;
  }

  /**
   * Establece el valor de la propiedad checkDoc11
   *
   * @param checkDoc11
   *          el checkDoc11 para establecer
   */
  public void setCheckDoc11(final boolean checkDoc11) {
    this.checkDoc11 = checkDoc11;
  }

  /**
   * Obtiene la propiedad checkDoc12
   *
   * @return el checkDoc12
   */
  public boolean isCheckDoc12() {
    return checkDoc12;
  }

  /**
   * Establece el valor de la propiedad checkDoc12
   *
   * @param checkDoc12
   *          el checkDoc12 para establecer
   */
  public void setCheckDoc12(final boolean checkDoc12) {
    this.checkDoc12 = checkDoc12;
  }

  /**
   * Obtiene la propiedad checkDoc13
   *
   * @return el checkDoc13
   */
  public boolean isCheckDoc13() {
    return checkDoc13;
  }

  /**
   * Establece el valor de la propiedad checkDoc13
   *
   * @param checkDoc13
   *          el checkDoc13 para establecer
   */
  public void setCheckDoc13(final boolean checkDoc13) {
    this.checkDoc13 = checkDoc13;
  }

  /**
   * Obtiene la propiedad checkDoc14
   *
   * @return el checkDoc14
   */
  public boolean isCheckDoc14() {
    return checkDoc14;
  }

  /**
   * Establece el valor de la propiedad checkDoc14
   *
   * @param checkDoc14
   *          el checkDoc14 para establecer
   */
  public void setCheckDoc14(final boolean checkDoc14) {
    this.checkDoc14 = checkDoc14;
  }

  /**
   * Obtiene la propiedad checkDoc15
   *
   * @return el checkDoc15
   */
  public boolean isCheckDoc15() {
    return checkDoc15;
  }

  /**
   * Establece el valor de la propiedad checkDoc15
   *
   * @param checkDoc15
   *          el checkDoc15 para establecer
   */
  public void setCheckDoc15(final boolean checkDoc15) {
    this.checkDoc15 = checkDoc15;
  }

  /**
   * Obtiene la propiedad checkDoc16
   *
   * @return el checkDoc16
   */
  public boolean isCheckDoc16() {
    return checkDoc16;
  }

  /**
   * Establece el valor de la propiedad checkDoc16
   *
   * @param checkDoc16
   *          el checkDoc16 para establecer
   */
  public void setCheckDoc16(final boolean checkDoc16) {
    this.checkDoc16 = checkDoc16;
  }

  /**
   * Obtiene la propiedad checkDoc17
   *
   * @return el checkDoc17
   */
  public boolean isCheckDoc17() {
    return checkDoc17;
  }

  /**
   * Establece el valor de la propiedad checkDoc17
   *
   * @param checkDoc17
   *          el checkDoc17 para establecer
   */
  public void setCheckDoc17(final boolean checkDoc17) {
    this.checkDoc17 = checkDoc17;
  }

  /**
   * Obtiene la propiedad checkDoc18
   *
   * @return el checkDoc18
   */
  public boolean isCheckDoc18() {
    return checkDoc18;
  }

  /**
   * Establece el valor de la propiedad checkDoc18
   *
   * @param checkDoc18
   *          el checkDoc18 para establecer
   */
  public void setCheckDoc18(final boolean checkDoc18) {
    this.checkDoc18 = checkDoc18;
  }

  /**
   * Obtiene la propiedad checkDoc19
   *
   * @return el checkDoc19
   */
  public boolean isCheckDoc19() {
    return checkDoc19;
  }

  /**
   * Establece el valor de la propiedad checkDoc19
   *
   * @param checkDoc19
   *          el checkDoc19 para establecer
   */
  public void setCheckDoc19(final boolean checkDoc19) {
    this.checkDoc19 = checkDoc19;
  }

  /**
   * Obtiene la propiedad checkDoc20
   *
   * @return el checkDoc20
   */
  public boolean isCheckDoc20() {
    return checkDoc20;
  }

  /**
   * Establece el valor de la propiedad checkDoc20
   *
   * @param checkDoc20
   *          el checkDoc20 para establecer
   */
  public void setCheckDoc20(final boolean checkDoc20) {
    this.checkDoc20 = checkDoc20;
  }

  /**
   * Obtiene la propiedad checkDoc21
   *
   * @return el checkDoc21
   */
  public boolean isCheckDoc21() {
    return checkDoc21;
  }

  /**
   * Establece el valor de la propiedad checkDoc21
   *
   * @param checkDoc21
   *          el checkDoc21 para establecer
   */
  public void setCheckDoc21(final boolean checkDoc21) {
    this.checkDoc21 = checkDoc21;
  }

  /**
   * Obtiene la propiedad checkDoc22
   *
   * @return el checkDoc22
   */
  public boolean isCheckDoc22() {
    return checkDoc22;
  }

  /**
   * Establece el valor de la propiedad checkDoc22
   *
   * @param checkDoc22
   *          el checkDoc22 para establecer
   */
  public void setCheckDoc22(final boolean checkDoc22) {
    this.checkDoc22 = checkDoc22;
  }

  /**
   * Obtiene la propiedad checkDoc23
   *
   * @return el checkDoc23
   */
  public boolean isCheckDoc23() {
    return checkDoc23;
  }

  /**
   * Establece el valor de la propiedad checkDoc23
   *
   * @param checkDoc23
   *          el checkDoc23 para establecer
   */
  public void setCheckDoc23(final boolean checkDoc23) {
    this.checkDoc23 = checkDoc23;
  }

  /**
   * Obtiene la propiedad checkDoc24
   *
   * @return el checkDoc24
   */
  public boolean isCheckDoc24() {
    return checkDoc24;
  }

  /**
   * Establece el valor de la propiedad checkDoc24
   *
   * @param checkDoc24
   *          el checkDoc24 para establecer
   */
  public void setCheckDoc24(final boolean checkDoc24) {
    this.checkDoc24 = checkDoc24;
  }

  /**
   * Obtiene la propiedad checkDoc25
   *
   * @return el checkDoc25
   */
  public boolean isCheckDoc25() {
    return checkDoc25;
  }

  /**
   * Establece el valor de la propiedad checkDoc25
   *
   * @param checkDoc25
   *          el checkDoc25 para establecer
   */
  public void setCheckDoc25(final boolean checkDoc25) {
    this.checkDoc25 = checkDoc25;
  }

  /**
   * Obtiene la propiedad checkDoc26
   *
   * @return el checkDoc26
   */
  public boolean isCheckDoc26() {
    return checkDoc26;
  }

  /**
   * Establece el valor de la propiedad checkDoc26
   *
   * @param checkDoc26
   *          el checkDoc26 para establecer
   */
  public void setCheckDoc26(final boolean checkDoc26) {
    this.checkDoc26 = checkDoc26;
  }

  /**
   * Obtiene la propiedad checkDoc27
   *
   * @return el checkDoc27
   */
  public boolean isCheckDoc27() {
    return checkDoc27;
  }

  /**
   * Establece el valor de la propiedad checkDoc27
   *
   * @param checkDoc27
   *          el checkDoc27 para establecer
   */
  public void setCheckDoc27(final boolean checkDoc27) {
    this.checkDoc27 = checkDoc27;
  }

  /**
   * Obtiene la propiedad checkDoc28
   *
   * @return el checkDoc28
   */
  public boolean isCheckDoc28() {
    return checkDoc28;
  }

  /**
   * Establece el valor de la propiedad checkDoc28
   *
   * @param checkDoc28
   *          el checkDoc28 para establecer
   */
  public void setCheckDoc28(final boolean checkDoc28) {
    this.checkDoc28 = checkDoc28;
  }

  /**
   * Obtiene la propiedad checkDoc29
   *
   * @return el checkDoc29
   */
  public boolean isCheckDoc29() {
    return checkDoc29;
  }

  /**
   * Establece el valor de la propiedad checkDoc29
   *
   * @param checkDoc29
   *          el checkDoc29 para establecer
   */
  public void setCheckDoc29(final boolean checkDoc29) {
    this.checkDoc29 = checkDoc29;
  }

  /**
   * Obtiene la propiedad checkDoc30
   *
   * @return el checkDoc30
   */
  public boolean isCheckDoc30() {
    return checkDoc30;
  }

  /**
   * Establece el valor de la propiedad checkDoc30
   *
   * @param checkDoc30
   *          el checkDoc30 para establecer
   */
  public void setCheckDoc30(final boolean checkDoc30) {
    this.checkDoc30 = checkDoc30;
  }

  /**
   * Obtiene la propiedad checkDoc31
   *
   * @return el checkDoc31
   */
  public boolean isCheckDoc31() {
    return checkDoc31;
  }

  /**
   * Establece el valor de la propiedad checkDoc31
   *
   * @param checkDoc31
   *          el checkDoc31 para establecer
   */
  public void setCheckDoc31(final boolean checkDoc31) {
    this.checkDoc31 = checkDoc31;
  }

  /**
   * Obtiene la propiedad checkDoc32
   *
   * @return el checkDoc32
   */
  public boolean isCheckDoc32() {
    return checkDoc32;
  }

  /**
   * Establece el valor de la propiedad checkDoc32
   *
   * @param checkDoc32
   *          el checkDoc32 para establecer
   */
  public void setCheckDoc32(final boolean checkDoc32) {
    this.checkDoc32 = checkDoc32;
  }

  /**
   * Obtiene la propiedad docsPoderAdminJA
   *
   * @return el docsPoderAdminJA
   */
  public List<DocPoderAdminJADTO> getDocsPoderAdminJA() {
    return docsPoderAdminJA;
  }

  /**
   * Establece el valor de la propiedad docsPoderAdminJA
   *
   * @param docsPoderAdminJA
   *          el docsPoderAdminJA para establecer
   */
  public void setDocsPoderAdminJA(final List<DocPoderAdminJADTO> docsPoderAdminJA) {
    this.docsPoderAdminJA = docsPoderAdminJA;
  }

  /**
   * Obtiene la propiedad autorizaJunta0AdmDocumento
   *
   * @return el autorizaJunta0AdmDocumento
   */
  public String getAutorizaJunta0AdmDocumento() {
    return autorizaJunta0AdmDocumento;
  }

  /**
   * Establece el valor de la propiedad autorizaJunta0AdmDocumento
   *
   * @param autorizaJunta0AdmDocumento
   *          el autorizaJunta0AdmDocumento para establecer
   */
  public void setAutorizaJunta0AdmDocumento(final String autorizaJunta0AdmDocumento) {
    this.autorizaJunta0AdmDocumento = autorizaJunta0AdmDocumento;
  }

  /**
   * Obtiene la propiedad autorizaJunta0AdmConsejeria
   *
   * @return el autorizaJunta0AdmConsejeria
   */
  public String getAutorizaJunta0AdmConsejeria() {
    return autorizaJunta0AdmConsejeria;
  }

  /**
   * Establece el valor de la propiedad autorizaJunta0AdmConsejeria
   *
   * @param autorizaJunta0AdmConsejeria
   *          el autorizaJunta0AdmConsejeria para establecer
   */
  public void setAutorizaJunta0AdmConsejeria(final String autorizaJunta0AdmConsejeria) {
    this.autorizaJunta0AdmConsejeria = autorizaJunta0AdmConsejeria;
  }

  /**
   * Obtiene la propiedad autorizaJunta0AdmFecha
   *
   * @return el autorizaJunta0AdmFecha
   */
  public Date getAutorizaJunta0AdmFecha() {
    return autorizaJunta0AdmFecha;
  }

  /**
   * Establece el valor de la propiedad autorizaJunta0AdmFecha
   *
   * @param autorizaJunta0AdmFecha
   *          el autorizaJunta0AdmFecha para establecer
   */
  public void setAutorizaJunta0AdmFecha(final Date autorizaJunta0AdmFecha) {
    this.autorizaJunta0AdmFecha = autorizaJunta0AdmFecha;
  }

  /**
   * Obtiene la propiedad autorizaJunta0AdmProcedimiento
   *
   * @return el autorizaJunta0AdmProcedimiento
   */
  public String getAutorizaJunta0AdmProcedimiento() {
    return autorizaJunta0AdmProcedimiento;
  }

  /**
   * Establece el valor de la propiedad autorizaJunta0AdmProcedimiento
   *
   * @param autorizaJunta0AdmProcedimiento
   *          el autorizaJunta0AdmProcedimiento para establecer
   */
  public void setAutorizaJunta0AdmProcedimiento(final String autorizaJunta0AdmProcedimiento) {
    this.autorizaJunta0AdmProcedimiento = autorizaJunta0AdmProcedimiento;
  }

  /**
   * Obtiene la propiedad autorizaJunta1AdmDocumento
   *
   * @return el autorizaJunta1AdmDocumento
   */
  public String getAutorizaJunta1AdmDocumento() {
    return autorizaJunta1AdmDocumento;
  }

  /**
   * Establece el valor de la propiedad autorizaJunta1AdmDocumento
   *
   * @param autorizaJunta1AdmDocumento
   *          el autorizaJunta1AdmDocumento para establecer
   */
  public void setAutorizaJunta1AdmDocumento(final String autorizaJunta1AdmDocumento) {
    this.autorizaJunta1AdmDocumento = autorizaJunta1AdmDocumento;
  }

  /**
   * Obtiene la propiedad autorizaJunta1AdmConsejeria
   *
   * @return el autorizaJunta1AdmConsejeria
   */
  public String getAutorizaJunta1AdmConsejeria() {
    return autorizaJunta1AdmConsejeria;
  }

  /**
   * Establece el valor de la propiedad autorizaJunta1AdmConsejeria
   *
   * @param autorizaJunta1AdmConsejeria
   *          el autorizaJunta1AdmConsejeria para establecer
   */
  public void setAutorizaJunta1AdmConsejeria(final String autorizaJunta1AdmConsejeria) {
    this.autorizaJunta1AdmConsejeria = autorizaJunta1AdmConsejeria;
  }

  /**
   * Obtiene la propiedad autorizaJunta1AdmFecha
   *
   * @return el autorizaJunta1AdmFecha
   */
  public Date getAutorizaJunta1AdmFecha() {
    return autorizaJunta1AdmFecha;
  }

  /**
   * Establece el valor de la propiedad autorizaJunta1AdmFecha
   *
   * @param autorizaJunta1AdmFecha
   *          el autorizaJunta1AdmFecha para establecer
   */
  public void setAutorizaJunta1AdmFecha(final Date autorizaJunta1AdmFecha) {
    this.autorizaJunta1AdmFecha = autorizaJunta1AdmFecha;
  }

  /**
   * Obtiene la propiedad autorizaJunta1AdmProcedimiento
   *
   * @return el autorizaJunta1AdmProcedimiento
   */
  public String getAutorizaJunta1AdmProcedimiento() {
    return autorizaJunta1AdmProcedimiento;
  }

  /**
   * Establece el valor de la propiedad autorizaJunta1AdmProcedimiento
   *
   * @param autorizaJunta1AdmProcedimiento
   *          el autorizaJunta1AdmProcedimiento para establecer
   */
  public void setAutorizaJunta1AdmProcedimiento(final String autorizaJunta1AdmProcedimiento) {
    this.autorizaJunta1AdmProcedimiento = autorizaJunta1AdmProcedimiento;
  }

  /**
   * Obtiene la propiedad docsPoderOtraAdmin
   *
   * @return el docsPoderOtraAdmin
   */
  public List<DocPoderOtraAdminDTO> getDocsPoderOtraAdmin() {
    return docsPoderOtraAdmin;
  }

  /**
   * Establece el valor de la propiedad docsPoderOtraAdmin
   *
   * @param docsPoderOtraAdmin
   *          el docsPoderOtraAdmin para establecer
   */
  public void setDocsPoderOtraAdmin(final List<DocPoderOtraAdminDTO> docsPoderOtraAdmin) {
    this.docsPoderOtraAdmin = docsPoderOtraAdmin;
  }

  /**
   * Obtiene la propiedad autorizaGestor0OtrasDocumento
   *
   * @return el autorizaGestor0OtrasDocumento
   */
  public String getAutorizaGestor0OtrasDocumento() {
    return autorizaGestor0OtrasDocumento;
  }

  /**
   * Establece el valor de la propiedad autorizaGestor0OtrasDocumento
   *
   * @param autorizaGestor0OtrasDocumento
   *          el autorizaGestor0OtrasDocumento para establecer
   */
  public void setAutorizaGestor0OtrasDocumento(final String autorizaGestor0OtrasDocumento) {
    this.autorizaGestor0OtrasDocumento = autorizaGestor0OtrasDocumento;
  }

  /**
   * Obtiene la propiedad autorizaGestor0OtrasAdministracion
   *
   * @return el autorizaGestor0OtrasAdministracion
   */
  public String getAutorizaGestor0OtrasAdministracion() {
    return autorizaGestor0OtrasAdministracion;
  }

  /**
   * Establece el valor de la propiedad autorizaGestor0OtrasAdministracion
   *
   * @param autorizaGestor0OtrasAdministracion
   *          el autorizaGestor0OtrasAdministracion para establecer
   */
  public void setAutorizaGestor0OtrasAdministracion(final String autorizaGestor0OtrasAdministracion) {
    this.autorizaGestor0OtrasAdministracion = autorizaGestor0OtrasAdministracion;
  }

  /**
   * Obtiene la propiedad autorizaGestor0OtrasFecha
   *
   * @return el autorizaGestor0OtrasFecha
   */
  public Date getAutorizaGestor0OtrasFecha() {
    return autorizaGestor0OtrasFecha;
  }

  /**
   * Establece el valor de la propiedad autorizaGestor0OtrasFecha
   *
   * @param autorizaGestor0OtrasFecha
   *          el autorizaGestor0OtrasFecha para establecer
   */
  public void setAutorizaGestor0OtrasFecha(final Date autorizaGestor0OtrasFecha) {
    this.autorizaGestor0OtrasFecha = autorizaGestor0OtrasFecha;
  }

  /**
   * Obtiene la propiedad autorizaGestor0OtrasProcedimiento
   *
   * @return el autorizaGestor0OtrasProcedimiento
   */
  public String getAutorizaGestor0OtrasProcedimiento() {
    return autorizaGestor0OtrasProcedimiento;
  }

  /**
   * Establece el valor de la propiedad autorizaGestor0OtrasProcedimiento
   *
   * @param autorizaGestor0OtrasProcedimiento
   *          el autorizaGestor0OtrasProcedimiento para establecer
   */
  public void setAutorizaGestor0OtrasProcedimiento(final String autorizaGestor0OtrasProcedimiento) {
    this.autorizaGestor0OtrasProcedimiento = autorizaGestor0OtrasProcedimiento;
  }

  /**
   * Obtiene la propiedad autorizaGestor1OtrasDocumento
   *
   * @return el autorizaGestor1OtrasDocumento
   */
  public String getAutorizaGestor1OtrasDocumento() {
    return autorizaGestor1OtrasDocumento;
  }

  /**
   * Establece el valor de la propiedad autorizaGestor1OtrasDocumento
   *
   * @param autorizaGestor1OtrasDocumento
   *          el autorizaGestor1OtrasDocumento para establecer
   */
  public void setAutorizaGestor1OtrasDocumento(final String autorizaGestor1OtrasDocumento) {
    this.autorizaGestor1OtrasDocumento = autorizaGestor1OtrasDocumento;
  }

  /**
   * Obtiene la propiedad autorizaGestor1OtrasAdministracion
   *
   * @return el autorizaGestor1OtrasAdministracion
   */
  public String getAutorizaGestor1OtrasAdministracion() {
    return autorizaGestor1OtrasAdministracion;
  }

  /**
   * Establece el valor de la propiedad autorizaGestor1OtrasAdministracion
   *
   * @param autorizaGestor1OtrasAdministracion
   *          el autorizaGestor1OtrasAdministracion para establecer
   */
  public void setAutorizaGestor1OtrasAdministracion(final String autorizaGestor1OtrasAdministracion) {
    this.autorizaGestor1OtrasAdministracion = autorizaGestor1OtrasAdministracion;
  }

  /**
   * Obtiene la propiedad autorizaGestor1OtrasFecha
   *
   * @return el autorizaGestor1OtrasFecha
   */
  public Date getAutorizaGestor1OtrasFecha() {
    return autorizaGestor1OtrasFecha;
  }

  /**
   * Establece el valor de la propiedad autorizaGestor1OtrasFecha
   *
   * @param autorizaGestor1OtrasFecha
   *          el autorizaGestor1OtrasFecha para establecer
   */
  public void setAutorizaGestor1OtrasFecha(final Date autorizaGestor1OtrasFecha) {
    this.autorizaGestor1OtrasFecha = autorizaGestor1OtrasFecha;
  }

  /**
   * Obtiene la propiedad autorizaGestor1OtrasProcedimiento
   *
   * @return el autorizaGestor1OtrasProcedimiento
   */
  public String getAutorizaGestor1OtrasProcedimiento() {
    return autorizaGestor1OtrasProcedimiento;
  }

  /**
   * Establece el valor de la propiedad autorizaGestor1OtrasProcedimiento
   *
   * @param autorizaGestor1OtrasProcedimiento
   *          el autorizaGestor1OtrasProcedimiento para establecer
   */
  public void setAutorizaGestor1OtrasProcedimiento(final String autorizaGestor1OtrasProcedimiento) {
    this.autorizaGestor1OtrasProcedimiento = autorizaGestor1OtrasProcedimiento;
  }

  /**
   * Obtiene la propiedad lugarFirma
   *
   * @return el lugarFirma
   */
  public String getLugarFirma() {
    return lugarFirma;
  }

  /**
   * Establece el valor de la propiedad lugarFirma
   *
   * @param lugarFirma
   *          el lugarFirma para establecer
   */
  public void setLugarFirma(final String lugarFirma) {
    this.lugarFirma = lugarFirma;
  }

  /**
   * Obtiene la propiedad firma
   *
   * @return el firma
   */
  public String getFirma() {
    return firma;
  }

  /**
   * Establece el valor de la propiedad firma
   *
   * @param firma
   *          el firma para establecer
   */
  public void setFirma(final String firma) {
    this.firma = firma;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(final String email) {
    this.email = email;
  }

  /**
   * Obtiene la propiedad pais1
   *
   * @return el pais1
   */
  public String getPais1() {
    return pais1;
  }

  /**
   * Establece el valor de la propiedad pais1
   *
   * @param pais1
   *          el pais1 para establecer
   */
  public void setPais1(final String pais1) {
    this.pais1 = pais1;
  }

  /**
   * Obtiene la propiedad pais2
   *
   * @return el pais2
   */
  public String getPais2() {
    return pais2;
  }

  /**
   * Establece el valor de la propiedad pais2
   *
   * @param pais2
   *          el pais2 para establecer
   */
  public void setPais2(final String pais2) {
    this.pais2 = pais2;
  }

  /**
   * Obtiene la propiedad pais3
   *
   * @return el pais3
   */
  public String getPais3() {
    return pais3;
  }

  /**
   * Establece el valor de la propiedad pais3
   *
   * @param pais3
   *          el pais3 para establecer
   */
  public void setPais3(final String pais3) {
    this.pais3 = pais3;
  }

  public String getPresupuesto() {
    return presupuesto;
  }

  public void setPresupuesto(final String presupuesto) {
    this.presupuesto = presupuesto;
  }

  public String getSubvencion() {
    return subvencion;
  }

  public void setSubvencion(final String subvencion) {
    this.subvencion = subvencion;
  }

  public String getPuntuacion() {
    return puntuacion;
  }

  public void setPuntuacion(final String puntuacion) {
    this.puntuacion = puntuacion;
  }

  @Override
  public int compareTo(final SolicitudDTO solicitudDTO) {
    if (Double.parseDouble(puntuacion) > Double.parseDouble(solicitudDTO.getPuntuacion())) {
      return 1;
    }
    return 0;
  }

  public BigDecimal getPuntuacionNum() {
    return puntuacionNum;
  }

  public void setPuntuacionNum(final BigDecimal puntuacionNum) {
    this.puntuacionNum = puntuacionNum;
  }

  public String getSubvencionSolicitada() {
    return subvencionSolicitada;
  }

  public void setSubvencionSolicitada(final String subvencionSolicitada) {
    this.subvencionSolicitada = subvencionSolicitada;
  }

  public BigDecimal getPlazo() {
    return plazo;
  }

  public void setPlazo(final BigDecimal plazo) {
    this.plazo = plazo;
  }

  /**
   * Obtiene la propiedad desestimientos
   *
   * @return el desestimientos
   */
  public String getDesestimientos() {
    return desestimientos;
  }

  /**
   * Establece el valor de la propiedad desestimientos
   *
   * @param desestimientos
   *          el desestimientos para establecer
   */
  public void setDesestimientos(final String desestimientos) {
    this.desestimientos = desestimientos;
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