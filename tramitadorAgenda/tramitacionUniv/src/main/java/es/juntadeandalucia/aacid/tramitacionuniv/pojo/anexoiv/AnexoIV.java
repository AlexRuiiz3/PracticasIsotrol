package es.juntadeandalucia.aacid.tramitacionuniv.pojo.anexoiv;

import java.util.ArrayList;
import java.util.List;

public class AnexoIV {

  private Long idSolicitud;

  // ----------------------------------------------------------------------------------------------------
  // 1 DATOS DE LA ENTIDAD SOLICITANTE Y DE LA PERSONA REPRESENTANTE
  // ----------------------------------------------------------------------------------------------------

  // SOLICITANTE
  private String soli1Nombre;
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
  private Long repr1Codprov;
  private String repr1Codmunicipio;

  // ----------------------------------------------------------------------------------------------------
  // 2 NOTIFICACIÓN ELECTRÓNICA OBLIGATORIA
  // ----------------------------------------------------------------------------------------------------
  private String notificaEmail;
  private String notificaTlfmovil;

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
  private String datosTitulo;

  // ----------------------------------------------------------------------------------------------------
  // 5 ALEGACIONES/ACEPTACIÓN/REFORMULACIÓN
  // ----------------------------------------------------------------------------------------------------
  private Boolean conceUno;
  private Boolean conceDos;
  private Boolean conceTres;
  private Boolean conceCuatro;
  private Boolean conceCinco;
  private Boolean conceSeis;
  private String refor;
  private Boolean conceSiete;
  private String noAcepto;
  private Boolean conceOcho;
  private String otroA;

  // ----------------------------------------------------------------------------------------------------
  // 6 DOCUMENTACIÓN
  // ----------------------------------------------------------------------------------------------------
  private Boolean doc1;
  private Boolean doc2;
  private Boolean doc3;
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
  private Boolean soli1AltaNotifica;
  private Boolean medioNotifTelematica;
  private String personaNotif;
  private String notifNombreLugar;
  private String notifNumidentLugar;
  private String tipoDestinatario;
  private Boolean soli1MedioNotifOrdinaria;
  private Boolean soli1MedioNotifTelematica;
  private String codDir3;
  private String notifEmailAutLugar;
  private String notifTelefonoAutLugar;

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
   * @return the repr1Tipovia
   */
  public String getRepr1Tipovia() {
    return repr1Tipovia;
  }

  /**
   * @param repr1Tipovia
   *          the repr1Tipovia to set
   */
  public void setRepr1Tipovia(final String repr1Tipovia) {
    this.repr1Tipovia = repr1Tipovia;
  }

  /**
   * @return the repr1Nombrevia
   */
  public String getRepr1Nombrevia() {
    return repr1Nombrevia;
  }

  /**
   * @param repr1Nombrevia
   *          the repr1Nombrevia to set
   */
  public void setRepr1Nombrevia(final String repr1Nombrevia) {
    this.repr1Nombrevia = repr1Nombrevia;
  }

  /**
   * @return the repr1Codprov
   */
  public Long getRepr1Codprov() {
    return repr1Codprov;
  }

  /**
   * @param repr1Codprov
   *          the repr1Codprov to set
   */
  public void setRepr1Codprov(final Long repr1Codprov) {
    this.repr1Codprov = repr1Codprov;
  }

  /**
   * @return the repr1Codmunicipio
   */
  public String getRepr1Codmunicipio() {
    return repr1Codmunicipio;
  }

  /**
   * @param repr1Codmunicipio
   *          the repr1Codmunicipio to set
   */
  public void setRepr1Codmunicipio(final String repr1Codmunicipio) {
    this.repr1Codmunicipio = repr1Codmunicipio;
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
   * @return the datosTitulo
   */
  public String getDatosTitulo() {
    return datosTitulo;
  }

  /**
   * @param datosTitulo
   *          the datosTitulo to set
   */
  public void setDatosTitulo(final String datosTitulo) {
    this.datosTitulo = datosTitulo;
  }

  /**
   * @return the conceUno
   */
  public Boolean getConceUno() {
    return conceUno;
  }

  /**
   * @param conceUno
   *          the conceUno to set
   */
  public void setConceUno(final Boolean conceUno) {
    this.conceUno = conceUno;
  }

  /**
   * @return the conceDos
   */
  public Boolean getConceDos() {
    return conceDos;
  }

  /**
   * @param conceDos
   *          the conceDos to set
   */
  public void setConceDos(final Boolean conceDos) {
    this.conceDos = conceDos;
  }

  /**
   * @return the conceTres
   */
  public Boolean getConceTres() {
    return conceTres;
  }

  /**
   * @param conceTres
   *          the conceTres to set
   */
  public void setConceTres(final Boolean conceTres) {
    this.conceTres = conceTres;
  }

  /**
   * @return the conceCuatro
   */
  public Boolean getConceCuatro() {
    return conceCuatro;
  }

  /**
   * @param conceCuatro
   *          the conceCuatro to set
   */
  public void setConceCuatro(final Boolean conceCuatro) {
    this.conceCuatro = conceCuatro;
  }

  /**
   * @return the conceCinco
   */
  public Boolean getConceCinco() {
    return conceCinco;
  }

  /**
   * @param conceCinco
   *          the conceCinco to set
   */
  public void setConceCinco(final Boolean conceCinco) {
    this.conceCinco = conceCinco;
  }

  /**
   * @return the conceSeis
   */
  public Boolean getConceSeis() {
    return conceSeis;
  }

  /**
   * @param conceSeis
   *          the conceSeis to set
   */
  public void setConceSeis(final Boolean conceSeis) {
    this.conceSeis = conceSeis;
  }

  /**
   * @return the refor
   */
  public String getRefor() {
    return refor;
  }

  /**
   * @param refor
   *          the refor to set
   */
  public void setRefor(final String refor) {
    this.refor = refor;
  }

  /**
   * @return the conceSiete
   */
  public Boolean getConceSiete() {
    return conceSiete;
  }

  /**
   * @param conceSiete
   *          the conceSiete to set
   */
  public void setConceSiete(final Boolean conceSiete) {
    this.conceSiete = conceSiete;
  }

  /**
   * @return the noAcepto
   */
  public String getNoAcepto() {
    return noAcepto;
  }

  /**
   * @param noAcepto
   *          the noAcepto to set
   */
  public void setNoAcepto(final String noAcepto) {
    this.noAcepto = noAcepto;
  }

  /**
   * @return the conceOcho
   */
  public Boolean getConceOcho() {
    return conceOcho;
  }

  /**
   * @param conceOcho
   *          the conceOcho to set
   */
  public void setConceOcho(final Boolean conceOcho) {
    this.conceOcho = conceOcho;
  }

  /**
   * @return the otroA
   */
  public String getOtroA() {
    return otroA;
  }

  /**
   * @param otroA
   *          the otroA to set
   */
  public void setOtroA(final String otroA) {
    this.otroA = otroA;
  }

  /**
   * @return the doc1
   */
  public Boolean getDoc1() {
    return doc1;
  }

  /**
   * @param doc1
   *          the doc1 to set
   */
  public void setDoc1(final Boolean doc1) {
    this.doc1 = doc1;
  }

  /**
   * @return the doc2
   */
  public Boolean getDoc2() {
    return doc2;
  }

  /**
   * @param doc2
   *          the doc2 to set
   */
  public void setDoc2(final Boolean doc2) {
    this.doc2 = doc2;
  }

  /**
   * @return the doc3
   */
  public Boolean getDoc3() {
    return doc3;
  }

  /**
   * @param doc3
   *          the doc3 to set
   */
  public void setDoc3(final Boolean doc3) {
    this.doc3 = doc3;
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
   * @return the soli1AltaNotifica
   */
  public Boolean getSoli1AltaNotifica() {
    return soli1AltaNotifica;
  }

  /**
   * @param soli1AltaNotifica
   *          the soli1AltaNotifica to set
   */
  public void setSoli1AltaNotifica(final Boolean soli1AltaNotifica) {
    this.soli1AltaNotifica = soli1AltaNotifica;
  }

  /**
   * @return the medioNotifTelematica
   */
  public Boolean getMedioNotifTelematica() {
    return medioNotifTelematica;
  }

  /**
   * @param medioNotifTelematica
   *          the medioNotifTelematica to set
   */
  public void setMedioNotifTelematica(final Boolean medioNotifTelematica) {
    this.medioNotifTelematica = medioNotifTelematica;
  }

  /**
   * @return the notificaEmail
   */
  public String getNotificaEmail() {
    return notificaEmail;
  }

  /**
   * @param notificaEmail
   *          the notificaEmail to set
   */
  public void setNotificaEmail(final String notificaEmail) {
    this.notificaEmail = notificaEmail;
  }

  /**
   * @return the notificaTlfmovil
   */
  public String getNotificaTlfmovil() {
    return notificaTlfmovil;
  }

  /**
   * @param notificaTlfmovil
   *          the notificaTlfmovil to set
   */
  public void setNotificaTlfmovil(final String notificaTlfmovil) {
    this.notificaTlfmovil = notificaTlfmovil;
  }

  /**
   * @return the personaNotif
   */
  public String getPersonaNotif() {
    return personaNotif;
  }

  /**
   * @param personaNotif
   *          the personaNotif to set
   */
  public void setPersonaNotif(final String personaNotif) {
    this.personaNotif = personaNotif;
  }

  /**
   * @return the notifNombreLugar
   */
  public String getNotifNombreLugar() {
    return notifNombreLugar;
  }

  /**
   * @param notifNombreLugar
   *          the notifNombreLugar to set
   */
  public void setNotifNombreLugar(final String notifNombreLugar) {
    this.notifNombreLugar = notifNombreLugar;
  }

  /**
   * @return the notifNumidentLugar
   */
  public String getNotifNumidentLugar() {
    return notifNumidentLugar;
  }

  /**
   * @param notifNumidentLugar
   *          the notifNumidentLugar to set
   */
  public void setNotifNumidentLugar(final String notifNumidentLugar) {
    this.notifNumidentLugar = notifNumidentLugar;
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
   * @return the soli1MedioNotifOrdinaria
   */
  public Boolean getSoli1MedioNotifOrdinaria() {
    return soli1MedioNotifOrdinaria;
  }

  /**
   * @param soli1MedioNotifOrdinaria
   *          the soli1MedioNotifOrdinaria to set
   */
  public void setSoli1MedioNotifOrdinaria(final Boolean soli1MedioNotifOrdinaria) {
    this.soli1MedioNotifOrdinaria = soli1MedioNotifOrdinaria;
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
   * @return the soli1MedioNotifTelematica
   */
  public Boolean getSoli1MedioNotifTelematica() {
    return soli1MedioNotifTelematica;
  }

  /**
   * @param soli1MedioNotifTelematica
   *          the soli1MedioNotifTelematica to set
   */
  public void setSoli1MedioNotifTelematica(final Boolean soli1MedioNotifTelematica) {
    this.soli1MedioNotifTelematica = soli1MedioNotifTelematica;
  }

  /**
   * Obtiene la propiedad notifEmailAutLugar
   *
   * @return el notifEmailAutLugar
   */
  public String getNotifEmailAutLugar() {
    return notifEmailAutLugar;
  }

  /**
   * Establece el valor de la propiedad notifEmailAutLugar
   *
   * @param notifEmailAutLugar
   *          el notifEmailAutLugar para establecer
   */
  public void setNotifEmailAutLugar(final String notifEmailAutLugar) {
    this.notifEmailAutLugar = notifEmailAutLugar;
  }

  /**
   * Obtiene la propiedad notifTelefonoAutLugar
   *
   * @return el notifTelefonoAutLugar
   */
  public String getNotifTelefonoAutLugar() {
    return notifTelefonoAutLugar;
  }

  /**
   * Establece el valor de la propiedad notifTelefonoAutLugar
   *
   * @param notifTelefonoAutLugar
   *          el notifTelefonoAutLugar para establecer
   */
  public void setNotifTelefonoAutLugar(final String notifTelefonoAutLugar) {
    this.notifTelefonoAutLugar = notifTelefonoAutLugar;
  }

}
