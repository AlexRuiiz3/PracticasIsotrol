package es.juntadeandalucia.aacid.tramitacionuniv.pojo.anexoiii;

import java.util.ArrayList;
import java.util.List;

public class AnexoIII {

  private Long idSolicitud;

  // ----------------------------------------------------------------------------------------------------
  // 1 DATOS DE LA ENTIDAD SOLICITANTE Y DE LA PERSONA REPRESENTANTE
  // ----------------------------------------------------------------------------------------------------

  // SOLICITANTE
  private String soli1Nombre; // *
  private String soli1Tipoident;
  private String soli1Apellido1;
  private String soli1Apellido2;
  private String soli1Numident;// *
  private String soli1Siglas;// *
  private String soli1Tipovia;// *
  private String soli1Nombrevia;// *
  private String soli1Numero;
  private String soli1Letra;
  private String soli1Kmvia;
  private String soli1Bloque;
  private String soli1Portal;
  private String soli1Escalera;
  private String soli1Piso;
  private String soli1Puerta;
  private Long soli1Codprov;// *
  private String soli1Codmunicipio;// *
  private String soli1Poblacion;// *
  private Long soli1Cp;// *
  private Long soli1Telefono;// Debe venir uno de los dos telefonos si o si
  private Long soli1Tlfmovil;// Debe venir uno de los dos telefonos si o si
  private String soli1Email;// *
  private String soli1Sexo;// *

  // REPRESENTANTE
  private String repr1Nombre; // *
  private String repr1Apellido1; // *
  private String repr1Apellido2;
  private String repr1Titulo;// *
  private String repr1Numident;// *
  private String repr1Sexo;// *
  private String repr1Email;// *
  private String repr1Telefono;// Debe venir uno de los dos telefonos si o si
  private String repr1Tlfmovil;// Debe venir uno de los dos telefonos si o si
  private String repr1Tipoident;
  private String repr1Tipovia;
  private String repr1Nombrevia;
  private Long repr1Codprov;
  private String repr1Codmunicipio;
  // ----------------------------------------------------------------------------------------------------
  // 2 NOTIFICACIÓN ELECTRÓNICA OBLIGATORIA
  // ----------------------------------------------------------------------------------------------------
  private String personaNotif;
  private Boolean altaNotifica;
  // ----------------------------------------------------------------------------------------------------
  // 3 IDENTIFICACIÓN DEL PROYECTO PARA EL QUE SE SOLICITA LA SUBVENCIÓN
  // ----------------------------------------------------------------------------------------------------
  private String numExpediente;
  private String datosTitulo;

  // ----------------------------------------------------------------------------------------------------
  // 4 ALEGACIONES
  // ----------------------------------------------------------------------------------------------------
  private Boolean alego;// * Obligatoria a TRUE
  private Boolean alegoOtra;// *

  private String textoAlego;//
  private String textoOtra;// * OBLIGATORIA SI alegoOtra VIENE A TRUE

  // ----------------------------------------------------------------------------------------------------
  // 5 DOCUMENTACIÓN
  // ----------------------------------------------------------------------------------------------------

  private List<String> presentoDocumento;
  private List<DocumentoAdministracionJA> documentosJA = new ArrayList<>();
  private List<DocumentoOtraAdministracion> documentosOtrasAdministraciones = new ArrayList<>();
  /*
   * private String errorImpInf; private String autorizaJunta; private String autorizaGestor;
   */

  // ----------------------------------------------------------------------------------------------------
  // 6 DECLARACIÓN, LUGAR, FECHA Y FIRMA
  // ----------------------------------------------------------------------------------------------------

  private String firm1Lugar;
  private String firm1Fdo;
  private String codDir3;

  // OTROS CAMPOS VEAJA
  private Boolean soli1AltaNotifica;
  private Boolean medioNotifTelematica;
  private String soli1MedioNotifOrdinaria;
  private String tipoDestinatario;
  private String notifEmailAutLugar;
  private Boolean soli1MedioNotifTelematica;
  private String notifTelefonoAutLugar;
  private String notifNombreLugar;
  private String notifNumidentLugar;

  private String presentoDocumento0Documento;
  /*
   * private String tipoDestinatario; private String calidadDe; private String soli1Codigo; private String agrupNombre; private String
   * agrupRacda; private String agrupSiglas; private String agrupIdentificador; private String repetAgrupaciones; private String
   * agrupRepTitulo; private String agrupRepApellido1; private String agrupRepApellido2; private String agrupRepIdentificador;
   *
   * private boolean checkTipo1; private boolean checkTipo2; private boolean checkFinalidad1; private boolean checkFinalidad2; private
   * boolean checkFinalidad3; private boolean checkFinalidad4;
   *
   * private boolean checkDoc1; private boolean checkDoc2; private boolean checkDoc3; private boolean checkDoc4; private boolean checkDoc5;
   * private boolean checkDoc6; private boolean checkDoc7; private boolean checkDoc8; private boolean checkDoc9; private boolean checkDoc10;
   * private boolean checkDoc11; private boolean checkDoc12; private boolean checkDoc13; private boolean checkDoc14; private boolean
   * checkDoc15; private boolean checkDoc16; private boolean checkDoc17; private boolean checkDoc18; private boolean checkDoc19; private
   * boolean checkDoc20; private boolean checkDoc21;
   *
   * private String otrasOrgano;
   *
   * private boolean consentExpresoSoli; private boolean cbDeclaro1; private boolean cbDeclaro2; private boolean cbDeclaro3; private boolean
   * cbDeclaro4; private boolean cbDeclaro5; private boolean cbDeclaro6;
   *
   * private String f1SolFecha; private String f1SolAdmin; private String f1SolImporte; private String f1SolMinimis; private String
   * solicitadas;
   *
   * private String f1ConFecha; private String f1ConAdmin; private String f1ConImporte; private String f1ConMinimis; private String
   * concedidas; private boolean cbDeclaro8; private String firm1Importe; private Integer firm1Dia; private Integer firm1Mes; private
   * Integer firm1Ano;
   */

  public String getSoli1Nombre() {
    return soli1Nombre;
  }

  public void setSoli1Nombre(final String soli1Nombre) {
    this.soli1Nombre = soli1Nombre;
  }

  public String getSoli1Numident() {
    return soli1Numident;
  }

  public void setSoli1Numident(final String soli1Numident) {
    this.soli1Numident = soli1Numident;
  }

  public String getSoli1Siglas() {
    return soli1Siglas;
  }

  public void setSoli1Siglas(final String soli1Siglas) {
    this.soli1Siglas = soli1Siglas;
  }

  public String getSoli1Tipovia() {
    return soli1Tipovia;
  }

  public void setSoli1Tipovia(final String soli1Tipovia) {
    this.soli1Tipovia = soli1Tipovia;
  }

  public String getSoli1Nombrevia() {
    return soli1Nombrevia;
  }

  public void setSoli1Nombrevia(final String soli1Nombrevia) {
    this.soli1Nombrevia = soli1Nombrevia;
  }

  public String getSoli1Numero() {
    return soli1Numero;
  }

  public void setSoli1Numero(final String soli1Numero) {
    this.soli1Numero = soli1Numero;
  }

  public String getSoli1Letra() {
    return soli1Letra;
  }

  public void setSoli1Letra(final String soli1Letra) {
    this.soli1Letra = soli1Letra;
  }

  public String getSoli1Kmvia() {
    return soli1Kmvia;
  }

  public void setSoli1Kmvia(final String soli1Kmvia) {
    this.soli1Kmvia = soli1Kmvia;
  }

  public String getSoli1Bloque() {
    return soli1Bloque;
  }

  public void setSoli1Bloque(final String soli1Bloque) {
    this.soli1Bloque = soli1Bloque;
  }

  public String getSoli1Portal() {
    return soli1Portal;
  }

  public void setSoli1Portal(final String soli1Portal) {
    this.soli1Portal = soli1Portal;
  }

  public String getSoli1Escalera() {
    return soli1Escalera;
  }

  public void setSoli1Escalera(final String soli1Escalera) {
    this.soli1Escalera = soli1Escalera;
  }

  public String getSoli1Piso() {
    return soli1Piso;
  }

  public void setSoli1Piso(final String soli1Piso) {
    this.soli1Piso = soli1Piso;
  }

  public String getSoli1Puerta() {
    return soli1Puerta;
  }

  public void setSoli1Puerta(final String soli1Puerta) {
    this.soli1Puerta = soli1Puerta;
  }

  public Long getSoli1Codprov() {
    return soli1Codprov;
  }

  public void setSoli1Codoprov(final Long soli1Codprov) {
    this.soli1Codprov = soli1Codprov;
  }

  public String getSoli1Codmunicipio() {
    return soli1Codmunicipio;
  }

  public void setSoli1Codmunicipio(final String soli1Codmunicipio) {
    this.soli1Codmunicipio = soli1Codmunicipio;
  }

  public String getSoli1Poblacion() {
    return soli1Poblacion;
  }

  public void setSoli1Poblacion(final String soli1Poblacion) {
    this.soli1Poblacion = soli1Poblacion;
  }

  public Long getSoli1Cp() {
    return soli1Cp;
  }

  public void setSoli1Cp(final Long soli1Cp) {
    this.soli1Cp = soli1Cp;
  }

  public Long getSoli1Telefono() {
    return soli1Telefono;
  }

  public void setSoli1Telefono(final Long soli1Telefono) {
    this.soli1Telefono = soli1Telefono;
  }

  public Long getSoli1Tlfmovil() {
    return soli1Tlfmovil;
  }

  public void setSoli1Tlfmovil(final Long soli1Tlfmovil) {
    this.soli1Tlfmovil = soli1Tlfmovil;
  }

  public String getSoli1Email() {
    return soli1Email;
  }

  public void setSoli1Email(final String soli1Email) {
    this.soli1Email = soli1Email;
  }

  public String getRepr1Nombre() {
    return repr1Nombre;
  }

  public void setRepr1Nombre(final String repr1Nombre) {
    this.repr1Nombre = repr1Nombre;
  }

  public String getRepr1Apellido1() {
    return repr1Apellido1;
  }

  public void setRepr1Apellido1(final String repr1Apellido1) {
    this.repr1Apellido1 = repr1Apellido1;
  }

  public String getRepr1Apellido2() {
    return repr1Apellido2;
  }

  public void setRepr1Apellido2(final String repr1Apellido2) {
    this.repr1Apellido2 = repr1Apellido2;
  }

  public String getRepr1Titulo() {
    return repr1Titulo;
  }

  public void setRepr1Titulo(final String repr1Titulo) {
    this.repr1Titulo = repr1Titulo;
  }

  public String getRepr1Numident() {
    return repr1Numident;
  }

  public void setRepr1Numident(final String repr1Numident) {
    this.repr1Numident = repr1Numident;
  }

  public String getRepr1Sexo() {
    return repr1Sexo;
  }

  public void setRepr1Sexo(final String repr1Sexo) {
    this.repr1Sexo = repr1Sexo;
  }

  public String getRepr1Email() {
    return repr1Email;
  }

  public void setRepr1Email(final String repr1Email) {
    this.repr1Email = repr1Email;
  }

  public String getRepr1Telefono() {
    return repr1Telefono;
  }

  public void setRepr1Telefono(final String repr1Telefono) {
    this.repr1Telefono = repr1Telefono;
  }

  public String getRepr1Tlfmovil() {
    return repr1Tlfmovil;
  }

  public void setRepr1TlfMovil(final String repr1Tlfmovil) {
    this.repr1Tlfmovil = repr1Tlfmovil;
  }

  public String getPersonaNotif() {
    return personaNotif;
  }

  public void setPersonaNotif(final String personaNotif) {
    this.personaNotif = personaNotif;
  }

  public Boolean getAltaNotifica() {
    return altaNotifica;
  }

  public void setAltaNotifica(final Boolean altaNotifica) {
    this.altaNotifica = altaNotifica;
  }

  public String getNumExpediente() {
    return numExpediente;
  }

  public void setNumExpediente(final String numExpediente) {
    this.numExpediente = numExpediente;
  }

  public String getDatosTitulo() {
    return datosTitulo;
  }

  public void setDatosTitulo(final String datosTitulo) {
    this.datosTitulo = datosTitulo;
  }

  public Boolean getAlego() {
    return alego;
  }

  public void setAlego(final Boolean alego) {
    this.alego = alego;
  }

  public String getTextoAlego() {
    return textoAlego;
  }

  public void setTextoAlego(final String textoAlego) {
    this.textoAlego = textoAlego;
  }

  public Boolean getAlegoOtra() {
    return alegoOtra;
  }

  public void setAlegoOtra(final Boolean alegoOtra) {
    this.alegoOtra = alegoOtra;
  }

  public String getTextoOtra() {
    return textoOtra;
  }

  public void setTextoOtra(final String textoOtra) {
    this.textoOtra = textoOtra;
  }

  public List<String> getPresentoDocumento() {
    return presentoDocumento;
  }

  public void setPresentoDocumento(final List<String> presentoDocumento) {
    this.presentoDocumento = presentoDocumento;
  }

  public String getFirm1Lugar() {
    return firm1Lugar;
  }

  public void setFirm1Lugar(final String firm1Lugar) {
    this.firm1Lugar = firm1Lugar;
  }

  public String getFirm1Fdo() {
    return firm1Fdo;
  }

  public void setFirm1Fdo(final String firm1Fdo) {
    this.firm1Fdo = firm1Fdo;
  }

  public String getCodDir3() {
    return codDir3;
  }

  public void setCodDir3(final String codDir3) {
    this.codDir3 = codDir3;
  }

  public List<DocumentoAdministracionJA> getDocumentosJA() {
    return documentosJA;
  }

  public void setDocumentosJA(final List<DocumentoAdministracionJA> documentosJA) {
    this.documentosJA = documentosJA;
  }

  public List<DocumentoOtraAdministracion> getDocumentosOtrasAdministraciones() {
    return documentosOtrasAdministraciones;
  }

  public void setDocumentosOtrasAdministraciones(final List<DocumentoOtraAdministracion> documentosOtrasAdministraciones) {
    this.documentosOtrasAdministraciones = documentosOtrasAdministraciones;
  }

  public Long getIdSolicitud() {
    return idSolicitud;
  }

  public void setIdSolicitud(final Long idSolicitud) {
    this.idSolicitud = idSolicitud;
  }

  public String getSoli1MedioNotifOrdinaria() {
    return soli1MedioNotifOrdinaria;
  }

  public void setSoli1MedioNotifOrdinaria(final String soli1MedioNotifOrdinaria) {
    this.soli1MedioNotifOrdinaria = soli1MedioNotifOrdinaria;
  }

  public String getSoli1Sexo() {
    return soli1Sexo;
  }

  public void setSoli1Sexo(final String soli1Sexo) {
    this.soli1Sexo = soli1Sexo;
  }

  public String getSoli1Tipoident() {
    return soli1Tipoident;
  }

  public void setSoli1Tipoident(final String soli1Tipoident) {
    this.soli1Tipoident = soli1Tipoident;
  }

  public String getSoli1Apellido1() {
    return soli1Apellido1;
  }

  public void setSoli1Apellido1(final String soli1Apellido1) {
    this.soli1Apellido1 = soli1Apellido1;
  }

  public String getSoli1Apellido2() {
    return soli1Apellido2;
  }

  public void setSoli1Apellido2(final String soli1Apellido2) {
    this.soli1Apellido2 = soli1Apellido2;
  }

  public String getTipoDestinatario() {
    return tipoDestinatario;
  }

  public void setTipoDestinatario(final String tipoDestinatario) {
    this.tipoDestinatario = tipoDestinatario;
  }

  public String getNotifEmailAutLugar() {
    return notifEmailAutLugar;
  }

  public void setNotifEmailAutLugar(final String notifEmailAutLugar) {
    this.notifEmailAutLugar = notifEmailAutLugar;
  }

  public String getNotifTelefonoAutLugar() {
    return notifTelefonoAutLugar;
  }

  public void setNotifTelefonoAutLugar(final String notifTelefonoAutLugar) {
    this.notifTelefonoAutLugar = notifTelefonoAutLugar;
  }

  public String getNotifNombreLugar() {
    return notifNombreLugar;
  }

  public void setNotifNombreLugar(final String notifNombreLugar) {
    this.notifNombreLugar = notifNombreLugar;
  }

  public String getNotifNumidentLugar() {
    return notifNumidentLugar;
  }

  public void setNotifNumidentLugar(final String notifNumidentLugar) {
    this.notifNumidentLugar = notifNumidentLugar;
  }

  public Boolean getSoli1AltaNotifica() {
    return soli1AltaNotifica;
  }

  public void setSoli1AltaNotifica(final Boolean soli1AltaNotifica) {
    this.soli1AltaNotifica = soli1AltaNotifica;
  }

  public Boolean getMedioNotifTelematica() {
    return medioNotifTelematica;
  }

  public void setMedioNotifTelematica(final Boolean medioNotifTelematica) {
    this.medioNotifTelematica = medioNotifTelematica;
  }

  public String getRepr1Tipovia() {
    return repr1Tipovia;
  }

  public void setRepr1Tipovia(final String repr1Tipovia) {
    this.repr1Tipovia = repr1Tipovia;
  }

  public String getRepr1Nombrevia() {
    return repr1Nombrevia;
  }

  public void setRepr1Nombrevia(final String repr1Nombrevia) {
    this.repr1Nombrevia = repr1Nombrevia;
  }

  public Long getRepr1Codprov() {
    return repr1Codprov;
  }

  public void setRepr1Codprov(final Long repr1Codprov) {
    this.repr1Codprov = repr1Codprov;
  }

  public String getRepr1Codmunicipio() {
    return repr1Codmunicipio;
  }

  public void setRepr1Codmunicipio(final String repr1Codmunicipio) {
    this.repr1Codmunicipio = repr1Codmunicipio;
  }

  public String getRepr1Tipoident() {
    return repr1Tipoident;
  }

  public void setRepr1Tipoident(final String repr1Tipoident) {
    this.repr1Tipoident = repr1Tipoident;
  }

  public void setSoli1Codprov(final Long soli1Codprov) {
    this.soli1Codprov = soli1Codprov;
  }

  public void setRepr1Tlfmovil(final String repr1Tlfmovil) {
    this.repr1Tlfmovil = repr1Tlfmovil;
  }

  public String getPresentoDocumento0Documento() {
    return presentoDocumento0Documento;
  }

  public void setPresentoDocumento0Documento(final String presentoDocumento0Documento) {
    this.presentoDocumento0Documento = presentoDocumento0Documento;
  }

  public Boolean getSoli1MedioNotifTelematica() {
    return soli1MedioNotifTelematica;
  }

  public void setSoli1MedioNotifTelematica(final Boolean soli1MedioNotifTelematica) {
    this.soli1MedioNotifTelematica = soli1MedioNotifTelematica;
  }

  /*
   * public String getTipoDestinatario() { return tipoDestinatario; }
   *
   * public void setTipoDestinatario(String tipoDestinatario) { this.tipoDestinatario = tipoDestinatario; }
   *
   * public String getCalidadDe() { return calidadDe; }
   *
   * public void setCalidadDe(String calidadDe) { this.calidadDe = calidadDe; }
   *
   * public String getSoli1Codigo() { return soli1Codigo; }
   *
   * public void setSoli1Codigo(String soli1Codigo) { this.soli1Codigo = soli1Codigo; }
   *
   * public String getAgrupNombre() { return agrupNombre; }
   *
   * public void setAgrupNombre(String agrupNombre) { this.agrupNombre = agrupNombre; }
   *
   * public String getAgrupRacda() { return agrupRacda; }
   *
   * public void setAgrupRacda(String agrupRacda) { this.agrupRacda = agrupRacda; }
   *
   * public String getAgrupSiglas() { return agrupSiglas; }
   *
   * public void setAgrupSiglas(String agrupSiglas) { this.agrupSiglas = agrupSiglas; }
   *
   * public String getAgrupIdentificador() { return agrupIdentificador; }
   *
   * public void setAgrupIdentificador(String agrupIdentificador) { this.agrupIdentificador = agrupIdentificador; }
   *
   * public String getRepetAgrupaciones() { return repetAgrupaciones; }
   *
   * public void setRepetAgrupaciones(String repetAgrupaciones) { this.repetAgrupaciones = repetAgrupaciones; }
   *
   * public String getAgrupRepTitulo() { return agrupRepTitulo; }
   *
   * public void setAgrupRepTitulo(String agrupRepTitulo) { this.agrupRepTitulo = agrupRepTitulo; }
   *
   * public String getAgrupRepApellido1() { return agrupRepApellido1; }
   *
   * public void setAgrupRepApellido1(String agrupRepApellido1) { this.agrupRepApellido1 = agrupRepApellido1; }
   *
   * public String getAgrupRepApellido2() { return agrupRepApellido2; }
   *
   * public void setAgrupRepApellido2(String agrupRepApellido2) { this.agrupRepApellido2 = agrupRepApellido2; }
   *
   * public String getAgrupRepIdentificador() { return agrupRepIdentificador; }
   *
   * public void setAgrupRepIdentificador(String agrupRepIdentificador) { this.agrupRepIdentificador = agrupRepIdentificador; }
   *
   * public boolean isCheckTipo1() { return checkTipo1; }
   *
   * public void setCheckTipo1(boolean checkTipo1) { this.checkTipo1 = checkTipo1; }
   *
   * public boolean isCheckTipo2() { return checkTipo2; }
   *
   * public void setCheckTipo2(boolean checkTipo2) { this.checkTipo2 = checkTipo2; }
   *
   * public boolean isCheckFinalidad1() { return checkFinalidad1; }
   *
   * public void setCheckFinalidad1(boolean checkFinalidad1) { this.checkFinalidad1 = checkFinalidad1; }
   *
   * public boolean isCheckFinalidad2() { return checkFinalidad2; }
   *
   * public void setCheckFinalidad2(boolean checkFinalidad2) { this.checkFinalidad2 = checkFinalidad2; }
   *
   * public boolean isCheckFinalidad3() { return checkFinalidad3; }
   *
   * public void setCheckFinalidad3(boolean checkFinalidad3) { this.checkFinalidad3 = checkFinalidad3; }
   *
   * public boolean isCheckFinalidad4() { return checkFinalidad4; }
   *
   * public void setCheckFinalidad4(boolean checkFinalidad4) { this.checkFinalidad4 = checkFinalidad4; }
   *
   * public boolean isCheckDoc1() { return checkDoc1; }
   *
   * public void setCheckDoc1(boolean checkDoc1) { this.checkDoc1 = checkDoc1; }
   *
   * public boolean isCheckDoc2() { return checkDoc2; }
   *
   * public void setCheckDoc2(boolean checkDoc2) { this.checkDoc2 = checkDoc2; }
   *
   * public boolean isCheckDoc3() { return checkDoc3; }
   *
   * public void setCheckDoc3(boolean checkDoc3) { this.checkDoc3 = checkDoc3; }
   *
   * public boolean isCheckDoc4() { return checkDoc4; }
   *
   * public void setCheckDoc4(boolean checkDoc4) { this.checkDoc4 = checkDoc4; }
   *
   * public boolean isCheckDoc5() { return checkDoc5; }
   *
   * public void setCheckDoc5(boolean checkDoc5) { this.checkDoc5 = checkDoc5; }
   *
   * public boolean isCheckDoc6() { return checkDoc6; }
   *
   * public void setCheckDoc6(boolean checkDoc6) { this.checkDoc6 = checkDoc6; }
   *
   * public boolean isCheckDoc7() { return checkDoc7; }
   *
   * public void setCheckDoc7(boolean checkDoc7) { this.checkDoc7 = checkDoc7; }
   *
   * public boolean isCheckDoc8() { return checkDoc8; }
   *
   * public void setCheckDoc8(boolean checkDoc8) { this.checkDoc8 = checkDoc8; }
   *
   * public boolean isCheckDoc9() { return checkDoc9; }
   *
   * public void setCheckDoc9(boolean checkDoc9) { this.checkDoc9 = checkDoc9; }
   *
   * public boolean isCheckDoc10() { return checkDoc10; }
   *
   * public void setCheckDoc10(boolean checkDoc10) { this.checkDoc10 = checkDoc10; }
   *
   * public boolean isCheckDoc11() { return checkDoc11; }
   *
   * public void setCheckDoc11(boolean checkDoc11) { this.checkDoc11 = checkDoc11; }
   *
   * public boolean isCheckDoc12() { return checkDoc12; }
   *
   * public void setCheckDoc12(boolean checkDoc12) { this.checkDoc12 = checkDoc12; }
   *
   * public boolean isCheckDoc13() { return checkDoc13; }
   *
   * public void setCheckDoc13(boolean checkDoc13) { this.checkDoc13 = checkDoc13; }
   *
   * public boolean isCheckDoc14() { return checkDoc14; }
   *
   * public void setCheckDoc14(boolean checkDoc14) { this.checkDoc14 = checkDoc14; }
   *
   * public boolean isCheckDoc15() { return checkDoc15; }
   *
   * public void setCheckDoc15(boolean checkDoc15) { this.checkDoc15 = checkDoc15; }
   *
   * public boolean isCheckDoc16() { return checkDoc16; }
   *
   * public void setCheckDoc16(boolean checkDoc16) { this.checkDoc16 = checkDoc16; }
   *
   * public boolean isCheckDoc17() { return checkDoc17; }
   *
   * public void setCheckDoc17(boolean checkDoc17) { this.checkDoc17 = checkDoc17; }
   *
   * public boolean isCheckDoc18() { return checkDoc18; }
   *
   * public void setCheckDoc18(boolean checkDoc18) { this.checkDoc18 = checkDoc18; }
   *
   * public boolean isCheckDoc19() { return checkDoc19; }
   *
   * public void setCheckDoc19(boolean checkDoc19) { this.checkDoc19 = checkDoc19; }
   *
   * public boolean isCheckDoc20() { return checkDoc20; }
   *
   * public void setCheckDoc20(boolean checkDoc20) { this.checkDoc20 = checkDoc20; }
   *
   * public boolean isCheckDoc21() { return checkDoc21; }
   *
   * public void setCheckDoc21(boolean checkDoc21) { this.checkDoc21 = checkDoc21; }
   *
   * public String getOtrasOrgano() { return otrasOrgano; }
   *
   * public void setOtrasOrgano(String otrasOrgano) { this.otrasOrgano = otrasOrgano; }
   *
   * public boolean isConsentExpresoSoli() { return consentExpresoSoli; }
   *
   * public void setConsentExpresoSoli(boolean consentExpresoSoli) { this.consentExpresoSoli = consentExpresoSoli; }
   *
   * public boolean isCbDeclaro1() { return cbDeclaro1; }
   *
   * public void setCbDeclaro1(boolean cbDeclaro1) { this.cbDeclaro1 = cbDeclaro1; }
   *
   * public boolean isCbDeclaro2() { return cbDeclaro2; }
   *
   * public void setCbDeclaro2(boolean cbDeclaro2) { this.cbDeclaro2 = cbDeclaro2; }
   *
   * public boolean isCbDeclaro3() { return cbDeclaro3; }
   *
   * public void setCbDeclaro3(boolean cbDeclaro3) { this.cbDeclaro3 = cbDeclaro3; }
   *
   * public boolean isCbDeclaro4() { return cbDeclaro4; }
   *
   * public void setCbDeclaro4(boolean cbDeclaro4) { this.cbDeclaro4 = cbDeclaro4; }
   *
   * public boolean isCbDeclaro5() { return cbDeclaro5; }
   *
   * public void setCbDeclaro5(boolean cbDeclaro5) { this.cbDeclaro5 = cbDeclaro5; }
   *
   * public boolean isCbDeclaro6() { return cbDeclaro6; }
   *
   * public void setCbDeclaro6(boolean cbDeclaro6) { this.cbDeclaro6 = cbDeclaro6; }
   *
   * public String getF1SolFecha() { return f1SolFecha; }
   *
   * public void setF1SolFecha(String f1SolFecha) { this.f1SolFecha = f1SolFecha; }
   *
   * public String getF1SolAdmin() { return f1SolAdmin; }
   *
   * public void setF1SolAdmin(String f1SolAdmin) { this.f1SolAdmin = f1SolAdmin; }
   *
   * public String getF1SolImporte() { return f1SolImporte; }
   *
   * public void setF1SolImporte(String f1SolImporte) { this.f1SolImporte = f1SolImporte; }
   *
   * public String getF1SolMinimis() { return f1SolMinimis; }
   *
   * public void setF1SolMinimis(String f1SolMinimis) { this.f1SolMinimis = f1SolMinimis; }
   *
   * public String getSolicitadas() { return solicitadas; }
   *
   * public void setSolicitadas(String solicitadas) { this.solicitadas = solicitadas; }
   *
   * public String getF1ConFecha() { return f1ConFecha; }
   *
   * public void setF1ConFecha(String f1ConFecha) { this.f1ConFecha = f1ConFecha; }
   *
   * public String getF1ConAdmin() { return f1ConAdmin; }
   *
   * public void setF1ConAdmin(String f1ConAdmin) { this.f1ConAdmin = f1ConAdmin; }
   *
   * public String getF1ConImporte() { return f1ConImporte; }
   *
   * public void setF1ConImporte(String f1ConImporte) { this.f1ConImporte = f1ConImporte; }
   *
   * public String getF1ConMinimis() { return f1ConMinimis; }
   *
   * public void setF1ConMinimis(String f1ConMinimis) { this.f1ConMinimis = f1ConMinimis; }
   *
   * public String getConcedidas() { return concedidas; }
   *
   * public void setConcedidas(String concedidas) { this.concedidas = concedidas; }
   *
   * public boolean isCbDeclaro8() { return cbDeclaro8; }
   *
   * public void setCbDeclaro8(boolean cbDeclaro8) { this.cbDeclaro8 = cbDeclaro8; }
   *
   * public String getFirm1Importe() { return firm1Importe; }
   *
   * public void setFirm1Importe(String firm1Importe) { this.firm1Importe = firm1Importe; }
   *
   * public Integer getFirm1Dia() { return firm1Dia; }
   *
   * public void setFirm1Dia(Integer firm1Dia) { this.firm1Dia = firm1Dia; }
   *
   * public Integer getFirm1Mes() { return firm1Mes; }
   *
   * public void setFirm1Mes(Integer firm1Mes) { this.firm1Mes = firm1Mes; }
   *
   * public Integer getFirm1Ano() { return firm1Ano; }
   *
   * public void setFirm1Ano(Integer firm1Ano) { this.firm1Ano = firm1Ano; }
   */

}
