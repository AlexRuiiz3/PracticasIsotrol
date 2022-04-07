package es.juntadeandalucia.aacid.tramitacionongd.pojo.anexov;

import java.util.ArrayList;
import java.util.List;

public class AnexoV {

  private Long idSolicitud;

  // ----------------------------------------------------------------------------------------------------
  // 1 DATOS DE LA ENTIDAD SOLICITANTE Y DE LA PERSONA REPRESENTANTE
  // ----------------------------------------------------------------------------------------------------

  // SOLICITANTE
  private String soli1Nombre;
  private String soli1Codigo;
  private String soli1Numident;
  private String soli1Siglas;
  private String soli1Tipoident;
  private String soli1Apellido1;
  private String soli1Apellido2;
  private String soli1Sexo;

  private String soli1Tipovia;
  private String soli1Nombrevia;
  private String soli1Numero;
  private String soli1Letra;
  private String soli1Kmvia;
  private String soli1Bloque;
  private String soli1Portal;
  private String soli1Escalera;
  private String soli1Piso;
  private String soli1Puerta;
  private Long soli1Codprov;
  private String soli1Codmunicipio;
  private String soli1Poblacion;
  private Long soli1Cp;
  private Long soli1Telefono;
  private Long soli1Tlfmovil;
  private String soli1Email;
  private String soli1MedioNotifOrdinaria;
  private String soli1MedioNotifTelematica;
  private Boolean soli1AltaNotifica;

  // REPRESENTANTE
  private String repr1Apellido1;
  private String repr1Apellido2;
  private String repr1Nombre;
  private String repr1Sexo;
  private String repr1Numident;
  private String repr1Titulo;
  private String repr1Tipoident;
  private String repr1Tipovia;
  private String repr1Nombrevia;
  private String repr1Codmunicipio;
  private Long repr1Codprov;

  // ----------------------------------------------------------------------------------------------------
  // 2 NOTIFICACIÓN ELECTRÓNICA OBLIGATORIA
  // ----------------------------------------------------------------------------------------------------
  private String notifEmailAutLugar;
  private String notifTelefonoAutLugar;

  // ----------------------------------------------------------------------------------------------------
  // 3 DATOS BANCARIOS
  // ----------------------------------------------------------------------------------------------------
  private String bancoIban;
  private String bancoCodigo;
  private String bancoPais;
  private String bancoLocalidad;
  private String bancoSucursal;
  private String bancoNumcuenta;

  private String bancoSCodbanco;
  private String bancoSPais;
  private String swiftLocalidad;
  private String bancoSSucur;

  private String bancoEntidad;
  private String bancoDomicilio;
  private String bancoCodmunicipio;
  private String bancoCodprov;
  private String bancoCp;

  // ----------------------------------------------------------------------------------------------------
  // 4 IDENTIFICACIÓN DEL PROYECTO PARA EL QUE SE SOLICITA LA SUBVENCIÓN
  // ----------------------------------------------------------------------------------------------------
  private String numExpediente;
  private String tituloProyecto;

  // ----------------------------------------------------------------------------------------------------
  // 5 ALEGACIONES/ACEPTACIÓN/REFORMULACIÓN
  // ----------------------------------------------------------------------------------------------------
  private Boolean conceUno;
  private Boolean conceDos;
  private Boolean conceTres;
  private Boolean conceCuatro;
  private Boolean conceCinco;
  private Boolean conceSeis;
  private Boolean conceSiete;
  private Boolean conceOcho;
  private String refor;
  private String noAcepto;
  private String otroA;

  // ----------------------------------------------------------------------------------------------------
  // 6 DOCUMENTACIÓN
  // ----------------------------------------------------------------------------------------------------
  private Boolean docu1;
  private Boolean docu2;
  private Boolean docu3;
  private Boolean docu4;
  private List<DocumentoAdministracionJA> documentosJA = new ArrayList<>();
  private List<DocumentoOtraAdministracion> documentosOtrasAdministraciones = new ArrayList<>();

  // ----------------------------------------------------------------------------------------------------
  // 7 DECLARACIÓN, LUGAR, FECHA Y FIRMA
  // ----------------------------------------------------------------------------------------------------
  private String firm1Fdo;
  private String firm1Lugar;

  // ----------------------------------------------------------------------------------------------------
  // OTROS CAMPOS DE VeA
  // ----------------------------------------------------------------------------------------------------
  private String tipoDestinatario;
  private String codDir3;
  private String notifNumidentLugar;
  private Boolean altaNotifica;
  private String medioNotifTelematica;
  private String notifNombreLugar;

  // GETTERS AND SETTERS

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
   * @return the soli1Nombre
   */
  public String getSoli1Nombre() {
    return soli1Nombre;
  }

  /**
   * @param soli1Nombre
   *          the soli1Nombre to set
   */
  public void setSoli1Nombre(final String soli1Nombre) {
    this.soli1Nombre = soli1Nombre;
  }

  /**
   * @return the soli1Codigo
   */
  public String getSoli1Codigo() {
    return soli1Codigo;
  }

  /**
   * @param soli1Codigo
   *          the soli1Codigo to set
   */
  public void setSoli1Codigo(final String soli1Codigo) {
    this.soli1Codigo = soli1Codigo;
  }

  /**
   * @return the soli1Numident
   */
  public String getSoli1Numident() {
    return soli1Numident;
  }

  /**
   * @param soli1Numident
   *          the soli1Numident to set
   */
  public void setSoli1Numident(final String soli1Numident) {
    this.soli1Numident = soli1Numident;
  }

  /**
   * @return the soli1Siglas
   */
  public String getSoli1Siglas() {
    return soli1Siglas;
  }

  /**
   * @param soli1Siglas
   *          the soli1Siglas to set
   */
  public void setSoli1Siglas(final String soli1Siglas) {
    this.soli1Siglas = soli1Siglas;
  }

  /**
   * @return the soli1Tipoident
   */
  public String getSoli1Tipoident() {
    return soli1Tipoident;
  }

  /**
   * @param soli1Tipoident
   *          the soli1Tipoident to set
   */
  public void setSoli1Tipoident(final String soli1Tipoident) {
    this.soli1Tipoident = soli1Tipoident;
  }

  /**
   * @return the soli1Apellido1
   */
  public String getSoli1Apellido1() {
    return soli1Apellido1;
  }

  /**
   * @param soli1Apellido1
   *          the soli1Apellido1 to set
   */
  public void setSoli1Apellido1(final String soli1Apellido1) {
    this.soli1Apellido1 = soli1Apellido1;
  }

  /**
   * @return the soli1Apellido2
   */
  public String getSoli1Apellido2() {
    return soli1Apellido2;
  }

  /**
   * @param soli1Apellido2
   *          the soli1Apellido2 to set
   */
  public void setSoli1Apellido2(final String soli1Apellido2) {
    this.soli1Apellido2 = soli1Apellido2;
  }

  /**
   * @return the soli1Sexo
   */
  public String getSoli1Sexo() {
    return soli1Sexo;
  }

  /**
   * @param soli1Sexo
   *          the soli1Sexo to set
   */
  public void setSoli1Sexo(final String soli1Sexo) {
    this.soli1Sexo = soli1Sexo;
  }

  /**
   * @return the soli1Tipovia
   */
  public String getSoli1Tipovia() {
    return soli1Tipovia;
  }

  /**
   * @param soli1Tipovia
   *          the soli1Tipovia to set
   */
  public void setSoli1Tipovia(final String soli1Tipovia) {
    this.soli1Tipovia = soli1Tipovia;
  }

  /**
   * @return the soli1Nombrevia
   */
  public String getSoli1Nombrevia() {
    return soli1Nombrevia;
  }

  /**
   * @param soli1Nombrevia
   *          the soli1Nombrevia to set
   */
  public void setSoli1Nombrevia(final String soli1Nombrevia) {
    this.soli1Nombrevia = soli1Nombrevia;
  }

  /**
   * @return the soli1Numero
   */
  public String getSoli1Numero() {
    return soli1Numero;
  }

  /**
   * @param soli1Numero
   *          the soli1Numero to set
   */
  public void setSoli1Numero(final String soli1Numero) {
    this.soli1Numero = soli1Numero;
  }

  /**
   * @return the soli1Letra
   */
  public String getSoli1Letra() {
    return soli1Letra;
  }

  /**
   * @param soli1Letra
   *          the soli1Letra to set
   */
  public void setSoli1Letra(final String soli1Letra) {
    this.soli1Letra = soli1Letra;
  }

  /**
   * @return the soli1Kmvia
   */
  public String getSoli1Kmvia() {
    return soli1Kmvia;
  }

  /**
   * @param soli1Kmvia
   *          the soli1Kmvia to set
   */
  public void setSoli1Kmvia(final String soli1Kmvia) {
    this.soli1Kmvia = soli1Kmvia;
  }

  /**
   * @return the soli1Bloque
   */
  public String getSoli1Bloque() {
    return soli1Bloque;
  }

  /**
   * @param soli1Bloque
   *          the soli1Bloque to set
   */
  public void setSoli1Bloque(final String soli1Bloque) {
    this.soli1Bloque = soli1Bloque;
  }

  /**
   * @return the soli1Portal
   */
  public String getSoli1Portal() {
    return soli1Portal;
  }

  /**
   * @param soli1Portal
   *          the soli1Portal to set
   */
  public void setSoli1Portal(final String soli1Portal) {
    this.soli1Portal = soli1Portal;
  }

  /**
   * @return the soli1Escalera
   */
  public String getSoli1Escalera() {
    return soli1Escalera;
  }

  /**
   * @param soli1Escalera
   *          the soli1Escalera to set
   */
  public void setSoli1Escalera(final String soli1Escalera) {
    this.soli1Escalera = soli1Escalera;
  }

  /**
   * @return the soli1Piso
   */
  public String getSoli1Piso() {
    return soli1Piso;
  }

  /**
   * @param soli1Piso
   *          the soli1Piso to set
   */
  public void setSoli1Piso(final String soli1Piso) {
    this.soli1Piso = soli1Piso;
  }

  /**
   * @return the soli1Puerta
   */
  public String getSoli1Puerta() {
    return soli1Puerta;
  }

  /**
   * @param soli1Puerta
   *          the soli1Puerta to set
   */
  public void setSoli1Puerta(final String soli1Puerta) {
    this.soli1Puerta = soli1Puerta;
  }

  /**
   * @return the soli1Codprov
   */
  public Long getSoli1Codprov() {
    return soli1Codprov;
  }

  /**
   * @param soli1Codprov
   *          the soli1Codprov to set
   */
  public void setSoli1Codprov(final Long soli1Codprov) {
    this.soli1Codprov = soli1Codprov;
  }

  /**
   * @return the soli1Codmunicipio
   */
  public String getSoli1Codmunicipio() {
    return soli1Codmunicipio;
  }

  /**
   * @param soli1Codmunicipio
   *          the soli1Codmunicipio to set
   */
  public void setSoli1Codmunicipio(final String soli1Codmunicipio) {
    this.soli1Codmunicipio = soli1Codmunicipio;
  }

  /**
   * @return the soli1Poblacion
   */
  public String getSoli1Poblacion() {
    return soli1Poblacion;
  }

  /**
   * @param soli1Poblacion
   *          the soli1Poblacion to set
   */
  public void setSoli1Poblacion(final String soli1Poblacion) {
    this.soli1Poblacion = soli1Poblacion;
  }

  /**
   * @return the soli1Cp
   */
  public Long getSoli1Cp() {
    return soli1Cp;
  }

  /**
   * @param soli1Cp
   *          the soli1Cp to set
   */
  public void setSoli1Cp(final Long soli1Cp) {
    this.soli1Cp = soli1Cp;
  }

  /**
   * @return the soli1Telefono
   */
  public Long getSoli1Telefono() {
    return soli1Telefono;
  }

  /**
   * @param soli1Telefono
   *          the soli1Telefono to set
   */
  public void setSoli1Telefono(final Long soli1Telefono) {
    this.soli1Telefono = soli1Telefono;
  }

  /**
   * @return the soli1Tlfmovil
   */
  public Long getSoli1Tlfmovil() {
    return soli1Tlfmovil;
  }

  /**
   * @param soli1Tlfmovil
   *          the soli1Tlfmovil to set
   */
  public void setSoli1Tlfmovil(final Long soli1Tlfmovil) {
    this.soli1Tlfmovil = soli1Tlfmovil;
  }

  /**
   * @return the soli1Email
   */
  public String getSoli1Email() {
    return soli1Email;
  }

  /**
   * @param soli1Email
   *          the soli1Email to set
   */
  public void setSoli1Email(final String soli1Email) {
    this.soli1Email = soli1Email;
  }

  /**
   * Obtiene la propiedad soli1MedioNotifOrdinaria
   *
   * @return el soli1MedioNotifOrdinaria
   */
  public String getSoli1MedioNotifOrdinaria() {
    return soli1MedioNotifOrdinaria;
  }

  /**
   * Establece el valor de la propiedad soli1MedioNotifOrdinaria
   *
   * @param soli1MedioNotifOrdinaria
   *          el soli1MedioNotifOrdinaria para establecer
   */
  public void setSoli1MedioNotifOrdinaria(final String soli1MedioNotifOrdinaria) {
    this.soli1MedioNotifOrdinaria = soli1MedioNotifOrdinaria;
  }

  /**
   * @return the repr1Apellido1
   */
  public String getRepr1Apellido1() {
    return repr1Apellido1;
  }

  /**
   * @param repr1Apellido1
   *          the repr1Apellido1 to set
   */
  public void setRepr1Apellido1(final String repr1Apellido1) {
    this.repr1Apellido1 = repr1Apellido1;
  }

  /**
   * @return the repr1Apellido2
   */
  public String getRepr1Apellido2() {
    return repr1Apellido2;
  }

  /**
   * @param repr1Apellido2
   *          the repr1Apellido2 to set
   */
  public void setRepr1Apellido2(final String repr1Apellido2) {
    this.repr1Apellido2 = repr1Apellido2;
  }

  /**
   * @return the repr1Nombre
   */
  public String getRepr1Nombre() {
    return repr1Nombre;
  }

  /**
   * @param repr1Nombre
   *          the repr1Nombre to set
   */
  public void setRepr1Nombre(final String repr1Nombre) {
    this.repr1Nombre = repr1Nombre;
  }

  /**
   * @return the repr1Sexo
   */
  public String getRepr1Sexo() {
    return repr1Sexo;
  }

  /**
   * @param repr1Sexo
   *          the repr1Sexo to set
   */
  public void setRepr1Sexo(final String repr1Sexo) {
    this.repr1Sexo = repr1Sexo;
  }

  /**
   * @return the repr1Numident
   */
  public String getRepr1Numident() {
    return repr1Numident;
  }

  /**
   * @param repr1Numident
   *          the repr1Numident to set
   */
  public void setRepr1Numident(final String repr1Numident) {
    this.repr1Numident = repr1Numident;
  }

  /**
   * @return the repr1Titulo
   */
  public String getRepr1Titulo() {
    return repr1Titulo;
  }

  /**
   * @param repr1Titulo
   *          the repr1Titulo to set
   */
  public void setRepr1Titulo(final String repr1Titulo) {
    this.repr1Titulo = repr1Titulo;
  }

  /**
   * @return the repr1Tipoident
   */
  public String getRepr1Tipoident() {
    return repr1Tipoident;
  }

  /**
   * @param repr1Tipoident
   *          the repr1Tipoident to set
   */
  public void setRepr1Tipoident(final String repr1Tipoident) {
    this.repr1Tipoident = repr1Tipoident;
  }

  /**
   * @return the notifEmailAutLugar
   */
  public String getNotifEmailAutLugar() {
    return notifEmailAutLugar;
  }

  /**
   * @param notifEmailAutLugar
   *          the notifEmailAutLugar to set
   */
  public void setNotifEmailAutLugar(final String notifEmailAutLugar) {
    this.notifEmailAutLugar = notifEmailAutLugar;
  }

  /**
   * @return the notifTelefonoAutLugar
   */
  public String getNotifTelefonoAutLugar() {
    return notifTelefonoAutLugar;
  }

  /**
   * @param notifTelefonoAutLugar
   *          the notifTelefonoAutLugar to set
   */
  public void setNotifTelefonoAutLugar(final String notifTelefonoAutLugar) {
    this.notifTelefonoAutLugar = notifTelefonoAutLugar;
  }

  /**
   * @return the bancoIban
   */
  public String getBancoIban() {
    return bancoIban;
  }

  /**
   * @param bancoIban
   *          the bancoIban to set
   */
  public void setBancoIban(final String bancoIban) {
    this.bancoIban = bancoIban;
  }

  /**
   * @return the bancoCodigo
   */
  public String getBancoCodigo() {
    return bancoCodigo;
  }

  /**
   * @param bancoCodigo
   *          the bancoCodigo to set
   */
  public void setBancoCodigo(final String bancoCodigo) {
    this.bancoCodigo = bancoCodigo;
  }

  /**
   * @return the bancoPais
   */
  public String getBancoPais() {
    return bancoPais;
  }

  /**
   * @param bancoPais
   *          the bancoPais to set
   */
  public void setBancoPais(final String bancoPais) {
    this.bancoPais = bancoPais;
  }

  /**
   * @return the bancoLocalidad
   */
  public String getBancoLocalidad() {
    return bancoLocalidad;
  }

  /**
   * @param bancoLocalidad
   *          the bancoLocalidad to set
   */
  public void setBancoLocalidad(final String bancoLocalidad) {
    this.bancoLocalidad = bancoLocalidad;
  }

  /**
   * @return the bancoSucursal
   */
  public String getBancoSucursal() {
    return bancoSucursal;
  }

  /**
   * @param bancoSucursal
   *          the bancoSucursal to set
   */
  public void setBancoSucursal(final String bancoSucursal) {
    this.bancoSucursal = bancoSucursal;
  }

  /**
   * @return the bancoNumcuenta
   */
  public String getBancoNumcuenta() {
    return bancoNumcuenta;
  }

  /**
   * @param bancoNumcuenta
   *          the bancoNumcuenta to set
   */
  public void setBancoNumcuenta(final String bancoNumcuenta) {
    this.bancoNumcuenta = bancoNumcuenta;
  }

  /**
   * @return the bancoSCodbanco
   */
  public String getBancoSCodbanco() {
    return bancoSCodbanco;
  }

  /**
   * @param bancoSCodbanco
   *          the bancoSCodbanco to set
   */
  public void setBancoSCodbanco(final String bancoSCodbanco) {
    this.bancoSCodbanco = bancoSCodbanco;
  }

  /**
   * @return the bancoSPais
   */
  public String getBancoSPais() {
    return bancoSPais;
  }

  /**
   * @param bancoSPais
   *          the bancoSPais to set
   */
  public void setBancoSPais(final String bancoSPais) {
    this.bancoSPais = bancoSPais;
  }

  /**
   * @return the swiftLocalidad
   */
  public String getSwiftLocalidad() {
    return swiftLocalidad;
  }

  /**
   * @param swiftLocalidad
   *          the swiftLocalidad to set
   */
  public void setSwiftLocalidad(final String swiftLocalidad) {
    this.swiftLocalidad = swiftLocalidad;
  }

  /**
   * @return the bancoSSucur
   */
  public String getBancoSSucur() {
    return bancoSSucur;
  }

  /**
   * @param bancoSSucur
   *          the bancoSSucur to set
   */
  public void setBancoSSucur(final String bancoSSucur) {
    this.bancoSSucur = bancoSSucur;
  }

  /**
   * @return the bancoEntidad
   */
  public String getBancoEntidad() {
    return bancoEntidad;
  }

  /**
   * @param bancoEntidad
   *          the bancoEntidad to set
   */
  public void setBancoEntidad(final String bancoEntidad) {
    this.bancoEntidad = bancoEntidad;
  }

  /**
   * @return the bancoDomicilio
   */
  public String getBancoDomicilio() {
    return bancoDomicilio;
  }

  /**
   * @param bancoDomicilio
   *          the bancoDomicilio to set
   */
  public void setBancoDomicilio(final String bancoDomicilio) {
    this.bancoDomicilio = bancoDomicilio;
  }

  /**
   * @return the bancoCodmunicipio
   */
  public String getBancoCodmunicipio() {
    return bancoCodmunicipio;
  }

  /**
   * @param bancoCodmunicipio
   *          the bancoCodmunicipio to set
   */
  public void setBancoCodmunicipio(final String bancoCodmunicipio) {
    this.bancoCodmunicipio = bancoCodmunicipio;
  }

  /**
   * @return the bancoCodprov
   */
  public String getBancoCodprov() {
    return bancoCodprov;
  }

  /**
   * @param bancoCodprov
   *          the bancoCodprov to set
   */
  public void setBancoCodprov(final String bancoCodprov) {
    this.bancoCodprov = bancoCodprov;
  }

  /**
   * @return the bancoCp
   */
  public String getBancoCp() {
    return bancoCp;
  }

  /**
   * @param bancoCp
   *          the bancoCp to set
   */
  public void setBancoCp(final String bancoCp) {
    this.bancoCp = bancoCp;
  }

  /**
   * @return the numExpediente
   */
  public String getNumExpediente() {
    return numExpediente;
  }

  /**
   * @param numExpediente
   *          the numExpediente to set
   */
  public void setNumExpediente(final String numExpediente) {
    this.numExpediente = numExpediente;
  }

  /**
   * @return the tituloProyecto
   */
  public String getTituloProyecto() {
    return tituloProyecto;
  }

  /**
   * @param tituloProyecto
   *          the tituloProyecto to set
   */
  public void setTituloProyecto(final String tituloProyecto) {
    this.tituloProyecto = tituloProyecto;
  }

  /**
   * @return the docu1
   */
  public Boolean getDocu1() {
    return docu1;
  }

  /**
   * @param docu1
   *          the docu1 to set
   */
  public void setDocu1(final Boolean docu1) {
    this.docu1 = docu1;
  }

  /**
   * @return the docu2
   */
  public Boolean getDocu2() {
    return docu2;
  }

  /**
   * @param docu2
   *          the docu2 to set
   */
  public void setDocu2(final Boolean docu2) {
    this.docu2 = docu2;
  }

  /**
   * @return the docu3
   */
  public Boolean getDocu3() {
    return docu3;
  }

  /**
   * @param docu3
   *          the docu3 to set
   */
  public void setDocu3(final Boolean docu3) {
    this.docu3 = docu3;
  }

  /**
   * @return the docu4
   */
  public Boolean getDocu4() {
    return docu4;
  }

  /**
   * @param docu4
   *          the docu4 to set
   */
  public void setDocu4(final Boolean docu4) {
    this.docu4 = docu4;
  }

  /**
   * @return the documentosJA
   */
  public List<DocumentoAdministracionJA> getDocumentosJA() {
    return documentosJA;
  }

  /**
   * @param documentosJA
   *          the documentosJA to set
   */
  public void setDocumentosJA(final List<DocumentoAdministracionJA> documentosJA) {
    this.documentosJA = documentosJA;
  }

  /**
   * @return the documentosOtrasAdministraciones
   */
  public List<DocumentoOtraAdministracion> getDocumentosOtrasAdministraciones() {
    return documentosOtrasAdministraciones;
  }

  /**
   * @param documentosOtrasAdministraciones
   *          the documentosOtrasAdministraciones to set
   */
  public void setDocumentosOtrasAdministraciones(final List<DocumentoOtraAdministracion> documentosOtrasAdministraciones) {
    this.documentosOtrasAdministraciones = documentosOtrasAdministraciones;
  }

  /**
   * @return the firm1Fdo
   */
  public String getFirm1Fdo() {
    return firm1Fdo;
  }

  /**
   * @param firm1Fdo
   *          the firm1Fdo to set
   */
  public void setFirm1Fdo(final String firm1Fdo) {
    this.firm1Fdo = firm1Fdo;
  }

  /**
   * @return the firm1Lugar
   */
  public String getFirm1Lugar() {
    return firm1Lugar;
  }

  /**
   * @param firm1Lugar
   *          the firm1Lugar to set
   */
  public void setFirm1Lugar(final String firm1Lugar) {
    this.firm1Lugar = firm1Lugar;
  }

  /**
   * @return the tipoDestinatario
   */
  public String getTipoDestinatario() {
    return tipoDestinatario;
  }

  /**
   * @param tipoDestinatario
   *          the tipoDestinatario to set
   */
  public void setTipoDestinatario(final String tipoDestinatario) {
    this.tipoDestinatario = tipoDestinatario;
  }

  /**
   * @return the codDir3
   */
  public String getCodDir3() {
    return codDir3;
  }

  /**
   * @param codDir3
   *          the codDir3 to set
   */
  public void setCodDir3(final String codDir3) {
    this.codDir3 = codDir3;
  }

  /**
   * Obtiene la propiedad otroA
   *
   * @return el otroA
   */
  public String getOtroA() {
    return otroA;
  }

  /**
   * Establece el valor de la propiedad otroA
   *
   * @param otroA
   *          el otroA para establecer
   */
  public void setOtroA(final String otroA) {
    this.otroA = otroA;
  }

  /**
   * Obtiene la propiedad conceSeis
   *
   * @return el conceSeis
   */
  public Boolean getConceSeis() {
    return conceSeis;
  }

  /**
   * Establece el valor de la propiedad conceSeis
   *
   * @param conceSeis
   *          el conceSeis para establecer
   */
  public void setConceSeis(final Boolean conceSeis) {
    this.conceSeis = conceSeis;
  }

  /**
   * Obtiene la propiedad notifNumidentLugar
   *
   * @return el notifNumidentLugar
   */
  public String getNotifNumidentLugar() {
    return notifNumidentLugar;
  }

  /**
   * Establece el valor de la propiedad notifNumidentLugar
   *
   * @param notifNumidentLugar
   *          el notifNumidentLugar para establecer
   */
  public void setNotifNumidentLugar(final String notifNumidentLugar) {
    this.notifNumidentLugar = notifNumidentLugar;
  }

  /**
   * Obtiene la propiedad soli1AltaNotifica
   *
   * @return el soli1AltaNotifica
   */
  public Boolean getSoli1AltaNotifica() {
    return soli1AltaNotifica;
  }

  /**
   * Establece el valor de la propiedad soli1AltaNotifica
   *
   * @param soli1AltaNotifica
   *          el soli1AltaNotifica para establecer
   */
  public void setSoli1AltaNotifica(final Boolean soli1AltaNotifica) {
    this.soli1AltaNotifica = soli1AltaNotifica;
  }

  /**
   * Obtiene la propiedad altaNotifica
   *
   * @return el altaNotifica
   */
  public Boolean getAltaNotifica() {
    return altaNotifica;
  }

  /**
   * Establece el valor de la propiedad altaNotifica
   *
   * @param altaNotifica
   *          el altaNotifica para establecer
   */
  public void setAltaNotifica(final Boolean altaNotifica) {
    this.altaNotifica = altaNotifica;
  }

  /**
   * Obtiene la propiedad repr1Tipovia
   *
   * @return el repr1Tipovia
   */
  public String getRepr1Tipovia() {
    return repr1Tipovia;
  }

  /**
   * Establece el valor de la propiedad repr1Tipovia
   *
   * @param repr1Tipovia
   *          el repr1Tipovia para establecer
   */
  public void setRepr1Tipovia(final String repr1Tipovia) {
    this.repr1Tipovia = repr1Tipovia;
  }

  /**
   * Obtiene la propiedad repr1Nombrevia
   *
   * @return el repr1Nombrevia
   */
  public String getRepr1Nombrevia() {
    return repr1Nombrevia;
  }

  /**
   * Establece el valor de la propiedad repr1Nombrevia
   *
   * @param repr1Nombrevia
   *          el repr1Nombrevia para establecer
   */
  public void setRepr1Nombrevia(final String repr1Nombrevia) {
    this.repr1Nombrevia = repr1Nombrevia;
  }

  /**
   * Obtiene la propiedad refor
   *
   * @return el refor
   */
  public String getRefor() {
    return refor;
  }

  /**
   * Establece el valor de la propiedad refor
   *
   * @param refor
   *          el refor para establecer
   */
  public void setRefor(final String refor) {
    this.refor = refor;
  }

  /**
   * Obtiene la propiedad conceUno
   *
   * @return el conceUno
   */
  public Boolean getConceUno() {
    return conceUno;
  }

  /**
   * Establece el valor de la propiedad conceUno
   *
   * @param conceUno
   *          el conceUno para establecer
   */
  public void setConceUno(final Boolean conceUno) {
    this.conceUno = conceUno;
  }

  /**
   * Obtiene la propiedad conceDos
   *
   * @return el conceDos
   */
  public Boolean getConceDos() {
    return conceDos;
  }

  /**
   * Establece el valor de la propiedad conceDos
   *
   * @param conceDos
   *          el conceDos para establecer
   */
  public void setConceDos(final Boolean conceDos) {
    this.conceDos = conceDos;
  }

  /**
   * Obtiene la propiedad conceTres
   *
   * @return el conceTres
   */
  public Boolean getConceTres() {
    return conceTres;
  }

  /**
   * Establece el valor de la propiedad conceTres
   *
   * @param conceTres
   *          el conceTres para establecer
   */
  public void setConceTres(final Boolean conceTres) {
    this.conceTres = conceTres;
  }

  /**
   * Obtiene la propiedad conceCuatro
   *
   * @return el conceCuatro
   */
  public Boolean getConceCuatro() {
    return conceCuatro;
  }

  /**
   * Establece el valor de la propiedad conceCuatro
   *
   * @param conceCuatro
   *          el conceCuatro para establecer
   */
  public void setConceCuatro(final Boolean conceCuatro) {
    this.conceCuatro = conceCuatro;
  }

  /**
   * Establece el valor de la propiedad conceCinco
   *
   * @param conceCinco
   *          el conceCinco para establecer
   */
  public void setConceCinco(final Boolean conceCinco) {
    this.conceCinco = conceCinco;
  }

  /**
   * Obtiene la propiedad conceSiete
   *
   * @return el conceSiete
   */
  public Boolean getConceSiete() {
    return conceSiete;
  }

  /**
   * Establece el valor de la propiedad conceSiete
   *
   * @param conceSiete
   *          el conceSiete para establecer
   */
  public void setConceSiete(final Boolean conceSiete) {
    this.conceSiete = conceSiete;
  }

  /**
   * Obtiene la propiedad conceCinco
   *
   * @return el conceCinco
   */
  public Boolean getConceCinco() {
    return conceCinco;
  }

  /**
   * Obtiene la propiedad noAcepto
   *
   * @return el noAcepto
   */
  public String getNoAcepto() {
    return noAcepto;
  }

  /**
   * Establece el valor de la propiedad noAcepto
   *
   * @param noAcepto
   *          el noAcepto para establecer
   */
  public void setNoAcepto(final String noAcepto) {
    this.noAcepto = noAcepto;
  }

  /**
   * Obtiene la propiedad repr1Codprov
   *
   * @return el repr1Codprov
   */
  public Long getRepr1Codprov() {
    return repr1Codprov;
  }

  /**
   * Establece el valor de la propiedad repr1Codprov
   *
   * @param repr1Codprov
   *          el repr1Codprov para establecer
   */
  public void setRepr1Codprov(final Long repr1Codprov) {
    this.repr1Codprov = repr1Codprov;
  }

  /**
   * Obtiene la propiedad repr1Codmunicipio
   *
   * @return el repr1Codmunicipio
   */
  public String getRepr1Codmunicipio() {
    return repr1Codmunicipio;
  }

  /**
   * Establece el valor de la propiedad repr1Codmunicipio
   *
   * @param repr1Codmunicipio
   *          el repr1Codmunicipio para establecer
   */
  public void setRepr1Codmunicipio(final String repr1Codmunicipio) {
    this.repr1Codmunicipio = repr1Codmunicipio;
  }

  /**
   * Obtiene la propiedad conceOcho
   *
   * @return el conceOcho
   */
  public Boolean getConceOcho() {
    return conceOcho;
  }

  /**
   * Establece el valor de la propiedad conceOcho
   *
   * @param conceOcho
   *          el conceOcho para establecer
   */
  public void setConceOcho(final Boolean conceOcho) {
    this.conceOcho = conceOcho;
  }

  /**
   * Obtiene la propiedad medioNotifTelematica
   *
   * @return el medioNotifTelematica
   */
  public String getMedioNotifTelematica() {
    return medioNotifTelematica;
  }

  /**
   * Establece el valor de la propiedad medioNotifTelematica
   *
   * @param medioNotifTelematica
   *          el medioNotifTelematica para establecer
   */
  public void setMedioNotifTelematica(final String medioNotifTelematica) {
    this.medioNotifTelematica = medioNotifTelematica;
  }

  /**
   * Obtiene la propiedad notifNombreLugar
   *
   * @return el notifNombreLugar
   */
  public String getNotifNombreLugar() {
    return notifNombreLugar;
  }

  /**
   * Establece el valor de la propiedad notifNombreLugar
   *
   * @param notifNombreLugar
   *          el notifNombreLugar para establecer
   */
  public void setNotifNombreLugar(final String notifNombreLugar) {
    this.notifNombreLugar = notifNombreLugar;
  }

  /**
   * Obtiene la propiedad soli1MedioNotifTelematica
   *
   * @return el soli1MedioNotifTelematica
   */
  public String getSoli1MedioNotifTelematica() {
    return soli1MedioNotifTelematica;
  }

  /**
   * Establece el valor de la propiedad soli1MedioNotifTelematica
   *
   * @param soli1MedioNotifTelematica
   *          el soli1MedioNotifTelematica para establecer
   */
  public void setSoli1MedioNotifTelematica(final String soli1MedioNotifTelematica) {
    this.soli1MedioNotifTelematica = soli1MedioNotifTelematica;
  }
}
