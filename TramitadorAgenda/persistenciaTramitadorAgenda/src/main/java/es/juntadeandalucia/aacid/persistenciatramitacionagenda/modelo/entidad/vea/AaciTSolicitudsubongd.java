package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.vea;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "AACI_T_SOLICITUDSUBONGD", schema = "AACID_OWNER", catalog = "")
public class AaciTSolicitudsubongd {
  private Long id;
  private Long numExpediente;
  private String personaNotif;
  private String repetAgrupaciones1AgrupRacda;
  private String soli1MedioNotifOrdinaria;
  private String repetAgrupaciones2AgrupSiglas;
  private String soli1Apellido1;
  private String repr1Nombre;
  private String soli1Apellido2;
  private String agrupRepNombre;
  private Date autorizaGestor1OtrasFecha;
  private String autorizaGest1OtrasDocumento;
  private String soli1Piso;
  private String firm1Ano;
  private String repDatos1Ejecucion;
  private String notifTelefonoAutLugar;
  private String autorizaGestor1OtrasProcedimiento;
  private BigDecimal solicitadas0SolImporte;
  private String repr1Sexo;
  private String soli1Siglas;
  private String concedidas0ConAdmin;
  private BigDecimal firm1Importe;
  private String notifNumidentLugar;
  private String repetAgrupaciones0AgrupRacda;
  private Boolean soli1AltaNotifica;
  private String repetAgrupaciones2AgrupRacda;
  private String soli1Numident;
  private String firm1Fdo;
  private Timestamp autorizaJunta1AdmFecha;
  private String solicitadasOSolAdmin;
  private String autorizaGestor0OtrasDocumento;
  private String autorizaJunta0AdmDocumento;
  private String agrupRepApellido2;
  private Boolean altaNotifica;
  private String agrupRepApellido1;
  private String repetAgrupaciones0AgrupNombre;
  private String solicitadas1SolMinimis;
  private String agrupRepIdentificador;
  private String firm1Dia;
  private String solicitadasOSolFecha;
  private String codigoIdentificativo;
  private String repr1Tipovia;
  private String repetAgrupaciones2AgrupNombre;
  private String soli1Email;
  private String soli1Numero;
  private String soli1Codmunicipio;
  private String datosTitulo;
  private String concedidasOConMinimis;
  private String repetAgrupaciones1AgrupSiglas;
  private String repr1Nombrevia;
  private String autorizaJunta1AdmConsejeria;
  private String errorFinalidadProyecto;
  private String notifEmailAutLugar;
  private String repr1Tipoident;
  private String solicitadasOSolMinimis;
  private Long soli1Tlfmovil;
  private String repr1Titulo;
  private Long soli1Cp;
  private String repetAgrupaciones1AgrupIdentificador;
  private Timestamp concedidas1ConFecha;
  private Long repr1Codprov;
  private String soli1Poblacion;
  private String textoOtras;
  private String soli1Nombrevia;
  private String repDatos0DatosEjecucion;
  private String soli1Escalera;
  private String repetAgrupaciones0AgrupIdentificador;
  private String soli1Sexo;
  private Boolean checkOtra;
  private String soli1Portal;
  private Boolean consentExpresoRepr;
  private String soli1Codigo;
  private Date autorizaGestor0OtrasFecha;
  private String autorizaJunta1AdmDocumento;
  private String concedidas1ConAdmin;
  private String soli1Kmvia;
  private Boolean cbDeclaro3;
  private Boolean cbDeclaro2;
  private Boolean cbDeclaro1;
  private String tipoDestinatario;
  private String autorizaJunta0AdmProcedimiento;
  private Date autorizaJunta0AdmFecha;
  private String autorizaGestor0OtrasProcedimiento;
  private Timestamp concedidasOConFecha;
  private String calidadDe;
  private Long concedidas0ConImporte;
  private String concedidas1ConMinimis;
  private String repetAgrupaciones0AgrupSiglas;
  private String soli1Nombre;
  private String repr1Numident;
  private String soli1Tipovia;
  private Boolean checkTipo2;
  private Boolean checkFinalidad3;
  private Boolean checkTipo1;
  private Boolean checkFinalidad4;
  private Boolean checkFinalidad1;
  private Boolean checkFinalidad2;
  private String soli1Bloque;
  private String repetAgrupaciones2AgrupIdentificador;
  private String repr1Codmunicipio;
  private String soli1Letra;
  private String repDatos2DatosEjecucion;
  private Long concedidas1ConImporte;
  private Long soli1Codprov;
  private String repetAgrupaciones1AgrupNombre;
  private Long solicitadas1SolImporte;
  private Timestamp solicitadas1SolFecha;
  private String soli1Puerta;
  private String autorizaGestor1OtrasAdministracion;
  private String autorizaJunta0AdmConsejeria;
  private String autorizaJunta1AdmProcedimiento;
  private Boolean medioNotifTelematica;
  private String solicitadas1SolAdmin;
  private String firm1Lugar;
  private Boolean cbDeclaro4;
  private Boolean cbDeclaro5;
  private Boolean cbDeclaro6;
  private String repr1Apellido1;
  private String notifNombreLugar;
  private String repr1Apellido2;
  private String autorizaGestor0OtrasAdministracion;
  private String firm1Mes;
  private Boolean soli1MedioNotifTelematica;
  private Long soli1Telefono;
  private String soli1Tipoident;
  private Boolean vbTecnico;
  private Boolean vbCoordinador;
  private String descripcion;
  private String municipio;
  private Time fhInicio;
  private String fkPais;
  private Long plazo;
  private Time fhPagoSubvencion;
  private Time fhPresentacion;
  private Long nuNuminterno;
  private Boolean lgRepresentacion;
  private String txLugarRegistro;
  private Time fhRegistro;
  private String txRespApellidos;
  private String txRespNombre;
  private String txRespNif;
  private Boolean checkTipo3;
  private Boolean checkTipo4;
  private Boolean checkDoc4Bis;
  private Boolean checkDoc14Bis;
  // Documentos subsanación ONGD
  private Boolean oCheckDoc1;
  private Boolean oCheckDoc2;
  private Boolean oCheckDoc3;
  private Boolean oCheckDoc4;
  private Boolean oCheckDoc5;
  private Boolean oCheckDoc6;
  private Boolean oCheckDoc7;
  private Boolean oCheckDoc8;
  private Boolean oCheckDoc9;
  private Boolean oCheckDoc10;
  private Boolean oCheckDoc11;
  private Boolean oCheckDoc12;
  private Boolean oCheckDoc13;
  private Boolean oCheckDoc14;
  private Boolean oCheckDoc15;
  private Boolean oCheckDoc16;
  private Boolean oCheckDoc17;
  private Boolean oCheckDoc18;
  private Boolean oCheckDoc19;
  private Boolean oCheckDoc20;
  private Boolean oCheckDoc21;
  private Boolean oCheckDoc22;
  private Boolean oCheckDoc23;
  private Boolean oCheckDoc24;
  private Boolean oCheckDoc25;
  private Boolean oCheckDoc26;
  private Boolean oCheckDoc27;
  private Boolean oCheckDoc28;
  private Boolean oCheckDoc29;
  private Boolean oCheckDoc30;
  private Boolean oCheckDoc31;
  private Boolean oCheckDoc32;
  private Boolean tCheckDoc1;
  private Boolean tCheckDoc2;
  private Boolean tCheckDoc3;
  private Boolean tCheckDoc4;
  private Boolean tCheckDoc5;
  private Boolean tCheckDoc6;
  private Boolean tCheckDoc7;
  private Boolean tCheckDoc8;
  private Boolean tCheckDoc9;
  private Boolean tCheckDoc10;
  private Boolean tCheckDoc11;
  private Boolean tCheckDoc12;
  private Boolean tCheckDoc13;
  private Boolean tCheckDoc14;
  private Boolean tCheckDoc15;
  private Boolean tCheckDoc16;
  private Boolean tCheckDoc17;
  private Boolean tCheckDoc18;
  private Boolean tCheckDoc19;
  private Boolean tCheckDoc20;
  private Boolean tCheckDoc21;
  private Boolean tCheckDoc22;
  private Boolean tCheckDoc23;
  private Boolean tCheckDoc24;
  private Boolean tCheckDoc25;
  private Boolean tCheckDoc26;
  private Boolean tCheckDoc27;
  private Boolean tCheckDoc28;
  private Boolean tCheckDoc29;
  private Boolean tCheckDoc30;
  private Boolean tCheckDoc31;
  private Boolean tCheckDoc32;
  private String codDir3;
  private String textoSubsanacion;
  private Long repr1Tlfmovil;
  private Long repr1Telefono;
  private Boolean checkDoc8Bis;
  private String repr1Email;
  private List<AaciAgrupaciones> aaciAgrupacionesByIdSolicitud;
  private List<AaciTConOtrasSubvenciones> aaciTConOtrasSubvencionesByIdSolicitud;
  private List<AaciTDocPoderAdminJa> aaciTDocPoderAdminJasByIdSolicitud;
  private List<AaciTDocPoderOtrAdmin> aaciTDocPoderOtrAdminsByIdSolicitud;
  private List<AaciTPaisesSolicitud> aaciTPaisesSolicitudsByIdSolicitud;
  private AaciTConvocatorias aaciTConvocatoriasByConvNuId;
  private List<AaciTSolOtrasSubvenciones> aaciTSolOtrasSubvencionesByIdSolicitud;
  private String respReqSubsanacion;
  private String paisIntervencion;

  @Id
  @Column(name = "ID_SOLICITUD")
  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  @Basic
  @Column(name = "NU_NUMEXPEDIENTE")
  public Long getNumExpediente() {
    return numExpediente;
  }

  public void setNumExpediente(final Long nuNumexpediente) {
    numExpediente = nuNumexpediente;
  }

  @Basic
  @Column(name = "TX_PERSONANOTIF")
  public String getPersonaNotif() {
    return personaNotif;
  }

  public void setPersonaNotif(final String txPersonanotif) {
    personaNotif = txPersonanotif;
  }

  @Basic
  @Column(name = "TX_REPETAGRUP1RACDA")
  public String getRepetAgrupaciones1AgrupRacda() {
    return repetAgrupaciones1AgrupRacda;
  }

  public void setRepetAgrupaciones1AgrupRacda(final String txRepetagrup1Racda) {
    repetAgrupaciones1AgrupRacda = txRepetagrup1Racda;
  }

  @Basic
  @Column(name = "TX_SOLI1MEDNOTIFORD")
  public String getSoli1MedioNotifOrdinaria() {
    return soli1MedioNotifOrdinaria;
  }

  public void setSoli1MedioNotifOrdinaria(final String txSoli1Mednotiford) {
    soli1MedioNotifOrdinaria = txSoli1Mednotiford;
  }

  @Basic
  @Column(name = "TX_REPETAGRUP2SIGLAS")
  public String getRepetAgrupaciones2AgrupSiglas() {
    return repetAgrupaciones2AgrupSiglas;
  }

  public void setRepetAgrupaciones2AgrupSiglas(final String txRepetagrup2Siglas) {
    repetAgrupaciones2AgrupSiglas = txRepetagrup2Siglas;
  }

  @Basic
  @Column(name = "TX_SOLI1APELLIDO1")
  public String getSoli1Apellido1() {
    return soli1Apellido1;
  }

  public void setSoli1Apellido1(final String txSoli1Apellido1) {
    soli1Apellido1 = txSoli1Apellido1;
  }

  @Basic
  @Column(name = "TX_REPR1NOMBRE")
  public String getRepr1Nombre() {
    return repr1Nombre;
  }

  public void setRepr1Nombre(final String txRepr1Nombre) {
    repr1Nombre = txRepr1Nombre;
  }

  @Basic
  @Column(name = "TX_SOLI1APELLIDO2")
  public String getSoli1Apellido2() {
    return soli1Apellido2;
  }

  public void setSoli1Apellido2(final String txSoli1Apellido2) {
    soli1Apellido2 = txSoli1Apellido2;
  }

  @Basic
  @Column(name = "TX_AGRUPREPNOMBRE")
  public String getAgrupRepNombre() {
    return agrupRepNombre;
  }

  public void setAgrupRepNombre(final String txAgruprepnombre) {
    agrupRepNombre = txAgruprepnombre;
  }

  @Basic
  @Column(name = "FH_AUTORIZAGEST1OTRASFEC")
  public Date getAutorizaGestor1OtrasFecha() {
    return autorizaGestor1OtrasFecha;
  }

  public void setAutorizaGestor1OtrasFecha(final Date fhAutorizagest1Otrasfec) {
    autorizaGestor1OtrasFecha = fhAutorizagest1Otrasfec;
  }

  @Basic
  @Column(name = "TX_AUTORIZAGEST1OTRASDOC")
  public String getAutorizaGest1OtrasDocumento() {
    return autorizaGest1OtrasDocumento;
  }

  public void setAutorizaGest1OtrasDocumento(final String txAutorizagest1Otrasdoc) {
    autorizaGest1OtrasDocumento = txAutorizagest1Otrasdoc;
  }

  @Basic
  @Column(name = "TX_SOLI1PISO")
  public String getSoli1Piso() {
    return soli1Piso;
  }

  public void setSoli1Piso(final String txSoli1Piso) {
    soli1Piso = txSoli1Piso;
  }

  @Basic
  @Column(name = "TX_FIRM1ANO")
  public String getFirm1Ano() {
    return firm1Ano;
  }

  public void setFirm1Ano(final String txFirm1Ano) {
    firm1Ano = txFirm1Ano;
  }

  @Basic
  @Column(name = "TX_REPDATOS1EJECUCION")
  public String getRepDatos1Ejecucion() {
    return repDatos1Ejecucion;
  }

  public void setRepDatos1Ejecucion(final String txRepdatos1Ejecucion) {
    repDatos1Ejecucion = txRepdatos1Ejecucion;
  }

  @Basic
  @Column(name = "TX_NOTIFTELEFONOAUTLUGAR")
  public String getNotifTelefonoAutLugar() {
    return notifTelefonoAutLugar;
  }

  public void setNotifTelefonoAutLugar(final String txNotiftelefonoautlugar) {
    notifTelefonoAutLugar = txNotiftelefonoautlugar;
  }

  @Basic
  @Column(name = "TX_AUTORIZAGEST1OTRASPRO")
  public String getAutorizaGestor1OtrasProcedimiento() {
    return autorizaGestor1OtrasProcedimiento;
  }

  public void setAutorizaGestor1OtrasProcedimiento(final String txAutorizagest1Otraspro) {
    autorizaGestor1OtrasProcedimiento = txAutorizagest1Otraspro;
  }

  @Basic
  @Column(name = "NU_SOLICITADASOLIMPORTE")
  public BigDecimal getSolicitadas0SolImporte() {
    return solicitadas0SolImporte;
  }

  public void setSolicitadas0SolImporte(final BigDecimal nuSolicitadasolimporte) {
    solicitadas0SolImporte = nuSolicitadasolimporte;
  }

  @Basic
  @Column(name = "TX_REPR1SEXO")
  public String getRepr1Sexo() {
    return repr1Sexo;
  }

  public void setRepr1Sexo(final String txRepr1Sexo) {
    repr1Sexo = txRepr1Sexo;
  }

  @Basic
  @Column(name = "TX_SOLI1SIGLAS")
  public String getSoli1Siglas() {
    return soli1Siglas;
  }

  public void setSoli1Siglas(final String txSoli1Siglas) {
    soli1Siglas = txSoli1Siglas;
  }

  @Basic
  @Column(name = "TX_CONCEDIDASCONADMIN")
  public String getConcedidas0ConAdmin() {
    return concedidas0ConAdmin;
  }

  public void setConcedidas0ConAdmin(final String txConcedidasconadmin) {
    concedidas0ConAdmin = txConcedidasconadmin;
  }

  @Basic
  @Column(name = "NU_FIRM1IMPORTE")
  public BigDecimal getFirm1Importe() {
    return firm1Importe;
  }

  public void setFirm1Importe(final BigDecimal nuFirm1Importe) {
    firm1Importe = nuFirm1Importe;
  }

  @Basic
  @Column(name = "TX_NOTIFNUMIDENTLUGAR")
  public String getNotifNumidentLugar() {
    return notifNumidentLugar;
  }

  public void setNotifNumidentLugar(final String txNotifnumidentlugar) {
    notifNumidentLugar = txNotifnumidentlugar;
  }

  @Basic
  @Column(name = "TX_REPETAGRUPRACDA")
  public String getRepetAgrupaciones0AgrupRacda() {
    return repetAgrupaciones0AgrupRacda;
  }

  public void setRepetAgrupaciones0AgrupRacda(final String txRepetagrupracda) {
    repetAgrupaciones0AgrupRacda = txRepetagrupracda;
  }

  @Basic
  @Column(name = "LG_SOLI1ALTANOTIFICA")
  public Boolean getSoli1AltaNotifica() {
    return soli1AltaNotifica;
  }

  public void setSoli1AltaNotifica(final Boolean lgSoli1Altanotifica) {
    soli1AltaNotifica = lgSoli1Altanotifica;
  }

  @Basic
  @Column(name = "TX_REPETAGRUP2RACDA")
  public String getRepetAgrupaciones2AgrupRacda() {
    return repetAgrupaciones2AgrupRacda;
  }

  public void setRepetAgrupaciones2AgrupRacda(final String txRepetagrup2Racda) {
    repetAgrupaciones2AgrupRacda = txRepetagrup2Racda;
  }

  @Basic
  @Column(name = "TX_SOLI1NUMIDENT")
  public String getSoli1Numident() {
    return soli1Numident;
  }

  public void setSoli1Numident(final String txSoli1Numident) {
    soli1Numident = txSoli1Numident;
  }

  @Basic
  @Column(name = "TX_FIRM1FDO")
  public String getFirm1Fdo() {
    return firm1Fdo;
  }

  public void setFirm1Fdo(final String txFirm1Fdo) {
    firm1Fdo = txFirm1Fdo;
  }

  @Basic
  @Column(name = "FH_AUTOJUNTA1ADMFECHA")
  public Timestamp getAutorizaJunta1AdmFecha() {
    return autorizaJunta1AdmFecha;
  }

  public void setAutorizaJunta1AdmFecha(final Timestamp fhAutojunta1Admfecha) {
    autorizaJunta1AdmFecha = fhAutojunta1Admfecha;
  }

  @Basic
  @Column(name = "TX_SOLICITADAS0ADMIN")
  public String getSolicitadasOSolAdmin() {
    return solicitadasOSolAdmin;
  }

  public void setSolicitadasOSolAdmin(final String txSolicitadas0Admin) {
    solicitadasOSolAdmin = txSolicitadas0Admin;
  }

  @Basic
  @Column(name = "TX_AUTORIZAGEST0OTRASDOC")
  public String getAutorizaGestor0OtrasDocumento() {
    return autorizaGestor0OtrasDocumento;
  }

  public void setAutorizaGestor0OtrasDocumento(final String txAutorizagest0Otrasdoc) {
    autorizaGestor0OtrasDocumento = txAutorizagest0Otrasdoc;
  }

  @Basic
  @Column(name = "TX_AUTORIZAJUNTA0ADMDOC")
  public String getAutorizaJunta0AdmDocumento() {
    return autorizaJunta0AdmDocumento;
  }

  public void setAutorizaJunta0AdmDocumento(final String txAutorizajunta0Admdoc) {
    autorizaJunta0AdmDocumento = txAutorizajunta0Admdoc;
  }

  @Basic
  @Column(name = "TX_AGRUPREPAPELLIDO2")
  public String getAgrupRepApellido2() {
    return agrupRepApellido2;
  }

  public void setAgrupRepApellido2(final String txAgruprepapellido2) {
    agrupRepApellido2 = txAgruprepapellido2;
  }

  @Basic
  @Column(name = "LG_ALTANOFIFICA")
  public Boolean getAltaNotifica() {
    return altaNotifica;
  }

  public void setAltaNotifica(final Boolean lgAltanofifica) {
    altaNotifica = lgAltanofifica;
  }

  @Basic
  @Column(name = "TX_AGRUPREPAPELLIDO1")
  public String getAgrupRepApellido1() {
    return agrupRepApellido1;
  }

  public void setAgrupRepApellido1(final String txAgruprepapellido1) {
    agrupRepApellido1 = txAgruprepapellido1;
  }

  @Basic
  @Column(name = "TX_REPETAGRUPNOMBRE")
  public String getRepetAgrupaciones0AgrupNombre() {
    return repetAgrupaciones0AgrupNombre;
  }

  public void setRepetAgrupaciones0AgrupNombre(final String txRepetagrupnombre) {
    repetAgrupaciones0AgrupNombre = txRepetagrupnombre;
  }

  @Basic
  @Column(name = "TX_SOLICITADAS1MINIMIS")
  public String getSolicitadas1SolMinimis() {
    return solicitadas1SolMinimis;
  }

  public void setSolicitadas1SolMinimis(final String txSolicitadas1Minimis) {
    solicitadas1SolMinimis = txSolicitadas1Minimis;
  }

  @Basic
  @Column(name = "TX_AGRUPREPIDENTIFIC")
  public String getAgrupRepIdentificador() {
    return agrupRepIdentificador;
  }

  public void setAgrupRepIdentificador(final String txAgruprepidentific) {
    agrupRepIdentificador = txAgruprepidentific;
  }

  @Basic
  @Column(name = "TX_FIRM1DIA")
  public String getFirm1Dia() {
    return firm1Dia;
  }

  public void setFirm1Dia(final String txFirm1Dia) {
    firm1Dia = txFirm1Dia;
  }

  @Basic
  @Column(name = "TX_SOLICITADAS0FECHA")
  public String getSolicitadasOSolFecha() {
    return solicitadasOSolFecha;
  }

  public void setSolicitadasOSolFecha(final String txSolicitadas0Fecha) {
    solicitadasOSolFecha = txSolicitadas0Fecha;
  }

  @Basic
  @Column(name = "TX_CODIDENTIFICATIVO")
  public String getCodigoIdentificativo() {
    return codigoIdentificativo;
  }

  public void setCodigoIdentificativo(final String txCodidentificativo) {
    codigoIdentificativo = txCodidentificativo;
  }

  @Basic
  @Column(name = "TX_REPR1TIPOVIA")
  public String getRepr1Tipovia() {
    return repr1Tipovia;
  }

  public void setRepr1Tipovia(final String txRepr1Tipovia) {
    repr1Tipovia = txRepr1Tipovia;
  }

  @Basic
  @Column(name = "TX_REPETAGRUP2NOMBRE")
  public String getRepetAgrupaciones2AgrupNombre() {
    return repetAgrupaciones2AgrupNombre;
  }

  public void setRepetAgrupaciones2AgrupNombre(final String txRepetagrup2Nombre) {
    repetAgrupaciones2AgrupNombre = txRepetagrup2Nombre;
  }

  @Basic
  @Column(name = "TX_SOLI1EMAIL")
  public String getSoli1Email() {
    return soli1Email;
  }

  public void setSoli1Email(final String txSoli1Email) {
    soli1Email = txSoli1Email;
  }

  @Basic
  @Column(name = "TX_SOLI1NUMERO")
  public String getSoli1Numero() {
    return soli1Numero;
  }

  public void setSoli1Numero(final String txSoli1Numero) {
    soli1Numero = txSoli1Numero;
  }

  @Basic
  @Column(name = "TX_SOLI1CODMUNICIPIO")
  public String getSoli1Codmunicipio() {
    return soli1Codmunicipio;
  }

  public void setSoli1Codmunicipio(final String txSoli1Codmunicipio) {
    soli1Codmunicipio = txSoli1Codmunicipio;
  }

  @Basic
  @Column(name = "TX_DATOSTITULO")
  public String getDatosTitulo() {
    return datosTitulo;
  }

  public void setDatosTitulo(final String txDatostitulo) {
    datosTitulo = txDatostitulo;
  }

  @Basic
  @Column(name = "TX_CONCEDIDAS0MINIMIS")
  public String getConcedidasOConMinimis() {
    return concedidasOConMinimis;
  }

  public void setConcedidasOConMinimis(final String txConcedidas0Minimis) {
    concedidasOConMinimis = txConcedidas0Minimis;
  }

  @Basic
  @Column(name = "TX_REPETAGRUP1SIGLAS")
  public String getRepetAgrupaciones1AgrupSiglas() {
    return repetAgrupaciones1AgrupSiglas;
  }

  public void setRepetAgrupaciones1AgrupSiglas(final String txRepetagrup1Siglas) {
    repetAgrupaciones1AgrupSiglas = txRepetagrup1Siglas;
  }

  @Basic
  @Column(name = "TX_REPR1NOMBREVIA")
  public String getRepr1Nombrevia() {
    return repr1Nombrevia;
  }

  public void setRepr1Nombrevia(final String txRepr1Nombrevia) {
    repr1Nombrevia = txRepr1Nombrevia;
  }

  @Basic
  @Column(name = "TX_AUTORIZAJUNTA1ADMCONS")
  public String getAutorizaJunta1AdmConsejeria() {
    return autorizaJunta1AdmConsejeria;
  }

  public void setAutorizaJunta1AdmConsejeria(final String txAutorizajunta1Admcons) {
    autorizaJunta1AdmConsejeria = txAutorizajunta1Admcons;
  }

  @Basic
  @Column(name = "TX_ERRORFINALIDADPROY")
  public String getErrorFinalidadProyecto() {
    return errorFinalidadProyecto;
  }

  public void setErrorFinalidadProyecto(final String txErrorfinalidadproy) {
    errorFinalidadProyecto = txErrorfinalidadproy;
  }

  @Basic
  @Column(name = "TX_NOTIFEMAILAUTLUGAR")
  public String getNotifEmailAutLugar() {
    return notifEmailAutLugar;
  }

  public void setNotifEmailAutLugar(final String txNotifemailautlugar) {
    notifEmailAutLugar = txNotifemailautlugar;
  }

  @Basic
  @Column(name = "TX_REPR1TIPOIDENT")
  public String getRepr1Tipoident() {
    return repr1Tipoident;
  }

  public void setRepr1Tipoident(final String txRepr1Tipoident) {
    repr1Tipoident = txRepr1Tipoident;
  }

  @Basic
  @Column(name = "TX_SOLICITADAS0MINIMIS")
  public String getSolicitadasOSolMinimis() {
    return solicitadasOSolMinimis;
  }

  public void setSolicitadasOSolMinimis(final String txSolicitadas0Minimis) {
    solicitadasOSolMinimis = txSolicitadas0Minimis;
  }

  @Basic
  @Column(name = "NU_SOLI1TLFMOVIL")
  public Long getSoli1Tlfmovil() {
    return soli1Tlfmovil;
  }

  public void setSoli1Tlfmovil(final Long nuSoli1Tlfmovil) {
    soli1Tlfmovil = nuSoli1Tlfmovil;
  }

  @Basic
  @Column(name = "TX_REPR1TITULO")
  public String getRepr1Titulo() {
    return repr1Titulo;
  }

  public void setRepr1Titulo(final String txRepr1Titulo) {
    repr1Titulo = txRepr1Titulo;
  }

  @Basic
  @Column(name = "NU_SOLI1CP")
  public Long getSoli1Cp() {
    return soli1Cp;
  }

  public void setSoli1Cp(final Long nuSoli1Cp) {
    soli1Cp = nuSoli1Cp;
  }

  @Basic
  @Column(name = "TX_REPETAGRUP1IDENT")
  public String getRepetAgrupaciones1AgrupIdentificador() {
    return repetAgrupaciones1AgrupIdentificador;
  }

  public void setRepetAgrupaciones1AgrupIdentificador(final String txRepetagrup1Ident) {
    repetAgrupaciones1AgrupIdentificador = txRepetagrup1Ident;
  }

  @Basic
  @Column(name = "FH_CONCEDIDAS1FECHA")
  public Timestamp getConcedidas1ConFecha() {
    return concedidas1ConFecha;
  }

  public void setConcedidas1ConFecha(final Timestamp fhConcedidas1Fecha) {
    concedidas1ConFecha = fhConcedidas1Fecha;
  }

  @Basic
  @Column(name = "NU_REPR1CODPROV")
  public Long getRepr1Codprov() {
    return repr1Codprov;
  }

  public void setRepr1Codprov(final Long nuRepr1Codprov) {
    repr1Codprov = nuRepr1Codprov;
  }

  @Basic
  @Column(name = "TX_SOLI1POBLACION")
  public String getSoli1Poblacion() {
    return soli1Poblacion;
  }

  public void setSoli1Poblacion(final String txSoli1Poblacion) {
    soli1Poblacion = txSoli1Poblacion;
  }

  @Basic
  @Column(name = "LB_TEXTOOTRAAS")
  public String getTextoOtras() {
    return textoOtras;
  }

  public void setTextoOtras(final String lbTextootraas) {
    textoOtras = lbTextootraas;
  }

  @Basic
  @Column(name = "TX_SOLI1NOMBREVIA")
  public String getSoli1Nombrevia() {
    return soli1Nombrevia;
  }

  public void setSoli1Nombrevia(final String txSoli1Nombrevia) {
    soli1Nombrevia = txSoli1Nombrevia;
  }

  @Basic
  @Column(name = "TX_REPDATOS0EJECUCION")
  public String getRepDatos0DatosEjecucion() {
    return repDatos0DatosEjecucion;
  }

  public void setRepDatos0DatosEjecucion(final String txRepdatos0Ejecucion) {
    repDatos0DatosEjecucion = txRepdatos0Ejecucion;
  }

  @Basic
  @Column(name = "TX_SOLI1ESCALERA")
  public String getSoli1Escalera() {
    return soli1Escalera;
  }

  public void setSoli1Escalera(final String txSoli1Escalera) {
    soli1Escalera = txSoli1Escalera;
  }

  @Basic
  @Column(name = "TX_REPET0AGRUPIDENTIF")
  public String getRepetAgrupaciones0AgrupIdentificador() {
    return repetAgrupaciones0AgrupIdentificador;
  }

  public void setRepetAgrupaciones0AgrupIdentificador(final String txRepet0Agrupidentif) {
    repetAgrupaciones0AgrupIdentificador = txRepet0Agrupidentif;
  }

  @Basic
  @Column(name = "TX_SOLI1SEXO")
  public String getSoli1Sexo() {
    return soli1Sexo;
  }

  public void setSoli1Sexo(final String txSoli1Sexo) {
    soli1Sexo = txSoli1Sexo;
  }

  @Basic
  @Column(name = "TX_SOLI1PORTAL")
  public String getSoli1Portal() {
    return soli1Portal;
  }

  public void setSoli1Portal(final String txSoli1Portal) {
    soli1Portal = txSoli1Portal;
  }

  @Basic
  @Column(name = "LG_CONSENTEXPRESOREPR")
  public Boolean getConsentExpresoRepr() {
    return consentExpresoRepr;
  }

  public void setConsentExpresoRepr(final Boolean lgConsentexpresorepr) {
    consentExpresoRepr = lgConsentexpresorepr;
  }

  @Basic
  @Column(name = "TX_SOLI1CODIGO")
  public String getSoli1Codigo() {
    return soli1Codigo;
  }

  public void setSoli1Codigo(final String txSoli1Codigo) {
    soli1Codigo = txSoli1Codigo;
  }

  @Basic
  @Column(name = "FH_AUTORIZAGEST0OTRASFEC")
  public Date getAutorizaGestor0OtrasFecha() {
    return autorizaGestor0OtrasFecha;
  }

  public void setAutorizaGestor0OtrasFecha(final Date fhAutorizagest0Otrasfec) {
    autorizaGestor0OtrasFecha = fhAutorizagest0Otrasfec;
  }

  @Basic
  @Column(name = "TX_AUTORIZAJUNTA1ADMDOC")
  public String getAutorizaJunta1AdmDocumento() {
    return autorizaJunta1AdmDocumento;
  }

  public void setAutorizaJunta1AdmDocumento(final String txAutorizajunta1Admdoc) {
    autorizaJunta1AdmDocumento = txAutorizajunta1Admdoc;
  }

  @Basic
  @Column(name = "TX_CONCEDIDAS1CONADMIN")
  public String getConcedidas1ConAdmin() {
    return concedidas1ConAdmin;
  }

  public void setConcedidas1ConAdmin(final String txConcedidas1Conadmin) {
    concedidas1ConAdmin = txConcedidas1Conadmin;
  }

  @Basic
  @Column(name = "TX_SOLI1KMVIA")
  public String getSoli1Kmvia() {
    return soli1Kmvia;
  }

  public void setSoli1Kmvia(final String txSoli1Kmvia) {
    soli1Kmvia = txSoli1Kmvia;
  }

  @Basic
  @Column(name = "LG_CBDECLARO3")
  public Boolean getCbDeclaro3() {
    return cbDeclaro3;
  }

  public void setCbDeclaro3(final Boolean lgCbdeclaro3) {
    cbDeclaro3 = lgCbdeclaro3;
  }

  @Basic
  @Column(name = "LG_CBDECLARO2")
  public Boolean getCbDeclaro2() {
    return cbDeclaro2;
  }

  public void setCbDeclaro2(final Boolean lgCbdeclaro2) {
    cbDeclaro2 = lgCbdeclaro2;
  }

  @Basic
  @Column(name = "LG_CBDECLARO1")
  public Boolean getCbDeclaro1() {
    return cbDeclaro1;
  }

  public void setCbDeclaro1(final Boolean lgCbdeclaro1) {
    cbDeclaro1 = lgCbdeclaro1;
  }

  @Basic
  @Column(name = "TX_TIPODESTINATARIO")
  public String getTipoDestinatario() {
    return tipoDestinatario;
  }

  public void setTipoDestinatario(final String txTipodestinatario) {
    tipoDestinatario = txTipodestinatario;
  }

  @Basic
  @Column(name = "LG_CHECKOTRA")
  public Boolean getCheckOtra() {
    return checkOtra;
  }

  public void setCheckOtra(final Boolean lgCheckotra) {
    checkOtra = lgCheckotra;
  }

  @Basic
  @Column(name = "TX_AUTORIZAJUNTA0ADMPROC")
  public String getAutorizaJunta0AdmProcedimiento() {
    return autorizaJunta0AdmProcedimiento;
  }

  public void setAutorizaJunta0AdmProcedimiento(final String txAutorizajunta0Admproc) {
    autorizaJunta0AdmProcedimiento = txAutorizajunta0Admproc;
  }

  @Basic
  @Column(name = "FH_AUTOJUNTA0ADMFECHA")
  public Date getAutorizaJunta0AdmFecha() {
    return autorizaJunta0AdmFecha;
  }

  public void setAutorizaJunta0AdmFecha(final Date fhAutojunta0Admfecha) {
    autorizaJunta0AdmFecha = fhAutojunta0Admfecha;
  }

  @Basic
  @Column(name = "TX_AUTORIZAGEST0OTRASPRO")
  public String getAutorizaGestor0OtrasProcedimiento() {
    return autorizaGestor0OtrasProcedimiento;
  }

  public void setAutorizaGestor0OtrasProcedimiento(final String txAutorizagest0Otraspro) {
    autorizaGestor0OtrasProcedimiento = txAutorizagest0Otraspro;
  }

  @Basic
  @Column(name = "FH_CONCEDIDAS0FECHA")
  public Timestamp getConcedidasOConFecha() {
    return concedidasOConFecha;
  }

  public void setConcedidasOConFecha(final Timestamp fhConcedidas0Fecha) {
    concedidasOConFecha = fhConcedidas0Fecha;
  }

  @Basic
  @Column(name = "TX_CALIDADDE")
  public String getCalidadDe() {
    return calidadDe;
  }

  public void setCalidadDe(final String txCalidadde) {
    calidadDe = txCalidadde;
  }

  @Basic
  @Column(name = "NU_CONCEDIDAS0CONIMPORTE")
  public Long getConcedidas0ConImporte() {
    return concedidas0ConImporte;
  }

  public void setConcedidas0ConImporte(final Long nuConcedidas0Conimporte) {
    concedidas0ConImporte = nuConcedidas0Conimporte;
  }

  @Basic
  @Column(name = "TX_CONCEDIDAS1MINIMIS")
  public String getConcedidas1ConMinimis() {
    return concedidas1ConMinimis;
  }

  public void setConcedidas1ConMinimis(final String txConcedidas1Minimis) {
    concedidas1ConMinimis = txConcedidas1Minimis;
  }

  @Basic
  @Column(name = "TX_REPETAGRUP0SIGLAS")
  public String getRepetAgrupaciones0AgrupSiglas() {
    return repetAgrupaciones0AgrupSiglas;
  }

  public void setRepetAgrupaciones0AgrupSiglas(final String txRepetagrup0Siglas) {
    repetAgrupaciones0AgrupSiglas = txRepetagrup0Siglas;
  }

  @Basic
  @Column(name = "TX_SOLI1NOMBRE")
  public String getSoli1Nombre() {
    return soli1Nombre;
  }

  public void setSoli1Nombre(final String txSoli1Nombre) {
    soli1Nombre = txSoli1Nombre;
  }

  @Basic
  @Column(name = "TX_REPR1NUMIDENT")
  public String getRepr1Numident() {
    return repr1Numident;
  }

  public void setRepr1Numident(final String txRepr1Numident) {
    repr1Numident = txRepr1Numident;
  }

  @Basic
  @Column(name = "TX_SOLI1TIPOVIA")
  public String getSoli1Tipovia() {
    return soli1Tipovia;
  }

  public void setSoli1Tipovia(final String txSoli1Tipovia) {
    soli1Tipovia = txSoli1Tipovia;
  }

  @Basic
  @Column(name = "LG_CHECKTIPO2")
  public Boolean getCheckTipo2() {
    return checkTipo2;
  }

  public void setCheckTipo2(final Boolean lgChecktipo2) {
    checkTipo2 = lgChecktipo2;
  }

  @Basic
  @Column(name = "LG_CHECKFINALIDAD3")
  public Boolean getCheckFinalidad3() {
    return checkFinalidad3;
  }

  public void setCheckFinalidad3(final Boolean lgCheckfinalidad3) {
    checkFinalidad3 = lgCheckfinalidad3;
  }

  @Basic
  @Column(name = "LG_CHECKTIPO1")
  public Boolean getCheckTipo1() {
    return checkTipo1;
  }

  public void setCheckTipo1(final Boolean lgChecktipo1) {
    checkTipo1 = lgChecktipo1;
  }

  @Basic
  @Column(name = "LG_CHECKFINALIDAD4")
  public Boolean getCheckFinalidad4() {
    return checkFinalidad4;
  }

  public void setCheckFinalidad4(final Boolean lgCheckfinalidad4) {
    checkFinalidad4 = lgCheckfinalidad4;
  }

  @Basic
  @Column(name = "LG_CHECKFINALIDAD1")
  public Boolean getCheckFinalidad1() {
    return checkFinalidad1;
  }

  public void setCheckFinalidad1(final Boolean lgCheckfinalidad1) {
    checkFinalidad1 = lgCheckfinalidad1;
  }

  @Basic
  @Column(name = "LG_CHECKFINALIDAD2")
  public Boolean getCheckFinalidad2() {
    return checkFinalidad2;
  }

  public void setCheckFinalidad2(final Boolean lgCheckfinalidad2) {
    checkFinalidad2 = lgCheckfinalidad2;
  }

  @Basic
  @Column(name = "TX_SOLI1BLOQUE")
  public String getSoli1Bloque() {
    return soli1Bloque;
  }

  public void setSoli1Bloque(final String txSoli1Bloque) {
    soli1Bloque = txSoli1Bloque;
  }

  @Basic
  @Column(name = "TX_REPET2AGRUPIDENTIF")
  public String getRepetAgrupaciones2AgrupIdentificador() {
    return repetAgrupaciones2AgrupIdentificador;
  }

  public void setRepetAgrupaciones2AgrupIdentificador(final String txRepet2Agrupidentif) {
    repetAgrupaciones2AgrupIdentificador = txRepet2Agrupidentif;
  }

  @Basic
  @Column(name = "NU_REPR1CODMUNICIPIO")
  public String getRepr1Codmunicipio() {
    return repr1Codmunicipio;
  }

  public void setRepr1Codmunicipio(final String nuRepr1Codmunicipio) {
    repr1Codmunicipio = nuRepr1Codmunicipio;
  }

  @Basic
  @Column(name = "TX_SOL1LETRA")
  public String getSoli1Letra() {
    return soli1Letra;
  }

  public void setSoli1Letra(final String txSol1Letra) {
    soli1Letra = txSol1Letra;
  }

  @Basic
  @Column(name = "TX_REPDATOS2EJECUCION")
  public String getRepDatos2DatosEjecucion() {
    return repDatos2DatosEjecucion;
  }

  public void setRepDatos2DatosEjecucion(final String txRepdatos2Ejecucion) {
    repDatos2DatosEjecucion = txRepdatos2Ejecucion;
  }

  @Basic
  @Column(name = "NU_CONCEDIDAS1CONIMPORTE")
  public Long getConcedidas1ConImporte() {
    return concedidas1ConImporte;
  }

  public void setConcedidas1ConImporte(final Long nuConcedidas1Conimporte) {
    concedidas1ConImporte = nuConcedidas1Conimporte;
  }

  @Basic
  @Column(name = "NU_SOLI1CODPROV")
  public Long getSoli1Codprov() {
    return soli1Codprov;
  }

  public void setSoli1Codprov(final Long nuSoli1Codprov) {
    soli1Codprov = nuSoli1Codprov;
  }

  @Basic
  @Column(name = "TX_REPETAGRUP1NOMBRE")
  public String getRepetAgrupaciones1AgrupNombre() {
    return repetAgrupaciones1AgrupNombre;
  }

  public void setRepetAgrupaciones1AgrupNombre(final String txRepetagrup1Nombre) {
    repetAgrupaciones1AgrupNombre = txRepetagrup1Nombre;
  }

  @Basic
  @Column(name = "NU_SOLICITADAS1IMPORTE")
  public Long getSolicitadas1SolImporte() {
    return solicitadas1SolImporte;
  }

  public void setSolicitadas1SolImporte(final Long nuSolicitadas1Importe) {
    solicitadas1SolImporte = nuSolicitadas1Importe;
  }

  @Basic
  @Column(name = "FH_SOLICITADAS1FECHA")
  public Timestamp getSolicitadas1SolFecha() {
    return solicitadas1SolFecha;
  }

  public void setSolicitadas1SolFecha(final Timestamp fhSolicitadas1Fecha) {
    solicitadas1SolFecha = fhSolicitadas1Fecha;
  }

  @Basic
  @Column(name = "TX_SOLI1PUERTA")
  public String getSoli1Puerta() {
    return soli1Puerta;
  }

  public void setSoli1Puerta(final String txSoli1Puerta) {
    soli1Puerta = txSoli1Puerta;
  }

  @Basic
  @Column(name = "TX_AUTORIZAGEST1OTRASADM")
  public String getAutorizaGestor1OtrasAdministracion() {
    return autorizaGestor1OtrasAdministracion;
  }

  public void setAutorizaGestor1OtrasAdministracion(final String txAutorizagest1Otrasadm) {
    autorizaGestor1OtrasAdministracion = txAutorizagest1Otrasadm;
  }

  @Basic
  @Column(name = "TX_AUTORIZAJUNTA0ADMCONS")
  public String getAutorizaJunta0AdmConsejeria() {
    return autorizaJunta0AdmConsejeria;
  }

  public void setAutorizaJunta0AdmConsejeria(final String txAutorizajunta0Admcons) {
    autorizaJunta0AdmConsejeria = txAutorizajunta0Admcons;
  }

  @Basic
  @Column(name = "TX_AUTORIZAJUNTA1ADMPROC")
  public String getAutorizaJunta1AdmProcedimiento() {
    return autorizaJunta1AdmProcedimiento;
  }

  public void setAutorizaJunta1AdmProcedimiento(final String txAutorizajunta1Admproc) {
    autorizaJunta1AdmProcedimiento = txAutorizajunta1Admproc;
  }

  @Basic
  @Column(name = "LG_MEDIONOTIFTELEMATICA")
  public Boolean getMedioNotifTelematica() {
    return medioNotifTelematica;
  }

  public void setMedioNotifTelematica(final Boolean lgMedionotiftelematica) {
    medioNotifTelematica = lgMedionotiftelematica;
  }

  @Basic
  @Column(name = "TX_SOLICITADAS1ADMIN")
  public String getSolicitadas1SolAdmin() {
    return solicitadas1SolAdmin;
  }

  public void setSolicitadas1SolAdmin(final String txSolicitadas1Admin) {
    solicitadas1SolAdmin = txSolicitadas1Admin;
  }

  @Basic
  @Column(name = "TX_FIRM1LUGAR")
  public String getFirm1Lugar() {
    return firm1Lugar;
  }

  public void setFirm1Lugar(final String txFirm1Lugar) {
    firm1Lugar = txFirm1Lugar;
  }

  @Basic
  @Column(name = "LG_CBDECLARO4")
  public Boolean getCbDeclaro4() {
    return cbDeclaro4;
  }

  public void setCbDeclaro4(final Boolean lgCbdeclaro4) {
    cbDeclaro4 = lgCbdeclaro4;
  }

  @Basic
  @Column(name = "LG_CBDECLARO5")
  public Boolean getCbDeclaro5() {
    return cbDeclaro5;
  }

  public void setCbDeclaro5(final Boolean lgCbdeclaro5) {
    cbDeclaro5 = lgCbdeclaro5;
  }

  @Basic
  @Column(name = "LG_CBDECLARO6")
  public Boolean getCbDeclaro6() {
    return cbDeclaro6;
  }

  public void setCbDeclaro6(final Boolean lgCbdeclaro6) {
    cbDeclaro6 = lgCbdeclaro6;
  }

  @Basic
  @Column(name = "TX_REPR1APELLIDO1")
  public String getRepr1Apellido1() {
    return repr1Apellido1;
  }

  public void setRepr1Apellido1(final String txRepr1Apellido1) {
    repr1Apellido1 = txRepr1Apellido1;
  }

  @Basic
  @Column(name = "TX_NOTIFNOMBRELUGAR")
  public String getNotifNombreLugar() {
    return notifNombreLugar;
  }

  public void setNotifNombreLugar(final String txNotifnombrelugar) {
    notifNombreLugar = txNotifnombrelugar;
  }

  @Basic
  @Column(name = "TX_REPR1APELLIDO2")
  public String getRepr1Apellido2() {
    return repr1Apellido2;
  }

  public void setRepr1Apellido2(final String txRepr1Apellido2) {
    repr1Apellido2 = txRepr1Apellido2;
  }

  @Basic
  @Column(name = "TX_AUTORIZAGEST0OTRASADM")
  public String getAutorizaGestor0OtrasAdministracion() {
    return autorizaGestor0OtrasAdministracion;
  }

  public void setAutorizaGestor0OtrasAdministracion(final String txAutorizagest0Otrasadm) {
    autorizaGestor0OtrasAdministracion = txAutorizagest0Otrasadm;
  }

  @Basic
  @Column(name = "TX_FIRM1MES")
  public String getFirm1Mes() {
    return firm1Mes;
  }

  public void setFirm1Mes(final String txFirm1Mes) {
    firm1Mes = txFirm1Mes;
  }

  @Basic
  @Column(name = "LG_SOLI1MEDIONOTIFTELEMA")
  public Boolean getSoli1MedioNotifTelematica() {
    return soli1MedioNotifTelematica;
  }

  public void setSoli1MedioNotifTelematica(final Boolean lgSoli1Medionotiftelema) {
    soli1MedioNotifTelematica = lgSoli1Medionotiftelema;
  }

  @Basic
  @Column(name = "NU_SOLI1TELEFONO")
  public Long getSoli1Telefono() {
    return soli1Telefono;
  }

  public void setSoli1Telefono(final Long nuSoli1Telefono) {
    soli1Telefono = nuSoli1Telefono;
  }

  @Basic
  @Column(name = "TX_SOLI1TIPOIDENT")
  public String getSoli1Tipoident() {
    return soli1Tipoident;
  }

  public void setSoli1Tipoident(final String txSoli1Tipoident) {
    soli1Tipoident = txSoli1Tipoident;
  }

  @Basic
  @Column(name = "VB_TECNICO")
  public Boolean getVbTecnico() {
    return vbTecnico;
  }

  public void setVbTecnico(final Boolean vbTecnico) {
    this.vbTecnico = vbTecnico;
  }

  @Basic
  @Column(name = "VB_COORDINADOR")
  public Boolean getVbCoordinador() {
    return vbCoordinador;
  }

  public void setVbCoordinador(final Boolean vbCoordinador) {
    this.vbCoordinador = vbCoordinador;
  }

  @Basic
  @Column(name = "DESCRIPCION")
  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(final String descripcion) {
    this.descripcion = descripcion;
  }

  @Basic
  @Column(name = "MUNICIPIO")
  public String getMunicipio() {
    return municipio;
  }

  public void setMunicipio(final String municipio) {
    this.municipio = municipio;
  }

  @Basic
  @Column(name = "FH_INICIO")
  public Time getFhInicio() {
    return fhInicio;
  }

  public void setFhInicio(final Time fhInicio) {
    this.fhInicio = fhInicio;
  }

  @Basic
  @Column(name = "FK_PAIS")
  public String getFkPais() {
    return fkPais;
  }

  public void setFkPais(final String fkPais) {
    this.fkPais = fkPais;
  }

  @Basic
  @Column(name = "PLAZO")
  public Long getPlazo() {
    return plazo;
  }

  public void setPlazo(final Long plazo) {
    this.plazo = plazo;
  }

  @Basic
  @Column(name = "FH_PAGO_SUBVENCION")
  public Time getFhPagoSubvencion() {
    return fhPagoSubvencion;
  }

  public void setFhPagoSubvencion(final Time fhPagoSubvencion) {
    this.fhPagoSubvencion = fhPagoSubvencion;
  }

  @Basic
  @Column(name = "FH_PRESENTACION")
  public Time getFhPresentacion() {
    return fhPresentacion;
  }

  public void setFhPresentacion(final Time fhPresentacion) {
    this.fhPresentacion = fhPresentacion;
  }

  @Basic
  @Column(name = "NU_NUMINTERNO")
  public Long getNuNuminterno() {
    return nuNuminterno;
  }

  public void setNuNuminterno(final Long nuNuminterno) {
    this.nuNuminterno = nuNuminterno;
  }

  @Basic
  @Column(name = "LG_REPRESENTACION")
  public Boolean getLgRepresentacion() {
    return lgRepresentacion;
  }

  public void setLgRepresentacion(final Boolean lgRepresentacion) {
    this.lgRepresentacion = lgRepresentacion;
  }

  @Basic
  @Column(name = "TX_LUGAR_REGISTRO")
  public String getTxLugarRegistro() {
    return txLugarRegistro;
  }

  public void setTxLugarRegistro(final String txLugarRegistro) {
    this.txLugarRegistro = txLugarRegistro;
  }

  @Basic
  @Column(name = "FH_REGISTRO")
  public Time getFhRegistro() {
    return fhRegistro;
  }

  public void setFhRegistro(final Time fhRegistro) {
    this.fhRegistro = fhRegistro;
  }

  @Basic
  @Column(name = "TX_RESP_APELLIDOS")
  public String getTxRespApellidos() {
    return txRespApellidos;
  }

  public void setTxRespApellidos(final String txRespApellidos) {
    this.txRespApellidos = txRespApellidos;
  }

  @Basic
  @Column(name = "TX_RESP_NOMBRE")
  public String getTxRespNombre() {
    return txRespNombre;
  }

  public void setTxRespNombre(final String txRespNombre) {
    this.txRespNombre = txRespNombre;
  }

  @Basic
  @Column(name = "TX_RESP_NIF")
  public String getTxRespNif() {
    return txRespNif;
  }

  public void setTxRespNif(final String txRespNif) {
    this.txRespNif = txRespNif;
  }

  @Basic
  @Column(name = "LG_CHECKTIPO3")
  public Boolean getCheckTipo3() {
    return checkTipo3;
  }

  public void setCheckTipo3(final Boolean lgChecktipo3) {
    checkTipo3 = lgChecktipo3;
  }

  @Basic
  @Column(name = "LG_CHECKTIPO4")
  public Boolean getCheckTipo4() {
    return checkTipo4;
  }

  public void setCheckTipo4(final Boolean lgChecktipo4) {
    checkTipo4 = lgChecktipo4;
  }

  @Basic
  @Column(name = "LG_CHECKDOC4BIS")
  public Boolean getCheckDoc4Bis() {
    return checkDoc4Bis;
  }

  public void setCheckDoc4Bis(final Boolean checkDoc4Bis) {
    this.checkDoc4Bis = checkDoc4Bis;
  }

  @Basic
  @Column(name = "LG_CHECKDOC14BIS")
  public Boolean getCheckDoc14Bis() {
    return checkDoc14Bis;
  }

  public void setCheckDoc14Bis(final Boolean checkDoc14Bis) {
    this.checkDoc14Bis = checkDoc14Bis;
  }

  @Basic
  @Column(name = "NU_REPR1TLFMOVIL")
  public Long getRepr1Tlfmovil() {
    return repr1Tlfmovil;
  }

  public void setRepr1Tlfmovil(final Long nuRepr1Tlfmovil) {
    repr1Tlfmovil = nuRepr1Tlfmovil;
  }

  @Basic
  @Column(name = "NU_REPR1TELEFONO")
  public Long getRepr1Telefono() {
    return repr1Telefono;
  }

  public void setRepr1Telefono(final Long nuRepr1Telefono) {
    repr1Telefono = nuRepr1Telefono;
  }

  @Basic
  @Column(name = "LG_CHECKDOC8BIS")
  public Boolean getCheckDoc8Bis() {
    return checkDoc8Bis;
  }

  public void setCheckDoc8Bis(final Boolean lgCheckdoc8Bis) {
    checkDoc8Bis = lgCheckdoc8Bis;
  }

  @Basic
  @Column(name = "TX_REPR1EMAIL")
  public String getRepr1Email() {
    return repr1Email;
  }

  public void setRepr1Email(final String repr1Email) {
    this.repr1Email = repr1Email;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final AaciTSolicitudsubongd that = (AaciTSolicitudsubongd) o;
    return Objects.equals(id, that.id) && Objects.equals(numExpediente, that.numExpediente) && Objects.equals(personaNotif, that.personaNotif)
        && Objects.equals(repetAgrupaciones1AgrupRacda, that.repetAgrupaciones1AgrupRacda)
        && Objects.equals(soli1MedioNotifOrdinaria, that.soli1MedioNotifOrdinaria)
        && Objects.equals(repetAgrupaciones2AgrupSiglas, that.repetAgrupaciones2AgrupSiglas) && Objects.equals(soli1Apellido1, that.soli1Apellido1)
        && Objects.equals(repr1Nombre, that.repr1Nombre) && Objects.equals(soli1Apellido2, that.soli1Apellido2)
        && Objects.equals(agrupRepNombre, that.agrupRepNombre) && Objects.equals(autorizaGestor1OtrasFecha, that.autorizaGestor1OtrasFecha)
        && Objects.equals(autorizaGest1OtrasDocumento, that.autorizaGest1OtrasDocumento) && Objects.equals(soli1Piso, that.soli1Piso)
        && Objects.equals(firm1Ano, that.firm1Ano) && Objects.equals(repDatos1Ejecucion, that.repDatos1Ejecucion)
        && Objects.equals(notifTelefonoAutLugar, that.notifTelefonoAutLugar)
        && Objects.equals(autorizaGestor1OtrasProcedimiento, that.autorizaGestor1OtrasProcedimiento)
        && Objects.equals(solicitadas0SolImporte, that.solicitadas0SolImporte) && Objects.equals(repr1Sexo, that.repr1Sexo)
        && Objects.equals(soli1Siglas, that.soli1Siglas) && Objects.equals(concedidas0ConAdmin, that.concedidas0ConAdmin)
        && Objects.equals(firm1Importe, that.firm1Importe) && Objects.equals(notifNumidentLugar, that.notifNumidentLugar)
        && Objects.equals(repetAgrupaciones0AgrupRacda, that.repetAgrupaciones0AgrupRacda) && Objects.equals(soli1AltaNotifica, that.soli1AltaNotifica)
        && Objects.equals(repetAgrupaciones2AgrupRacda, that.repetAgrupaciones2AgrupRacda) && Objects.equals(soli1Numident, that.soli1Numident)
        && Objects.equals(firm1Fdo, that.firm1Fdo) && Objects.equals(autorizaJunta1AdmFecha, that.autorizaJunta1AdmFecha)
        && Objects.equals(solicitadasOSolAdmin, that.solicitadasOSolAdmin) && Objects.equals(autorizaGestor0OtrasDocumento, that.autorizaGestor0OtrasDocumento)
        && Objects.equals(autorizaJunta0AdmDocumento, that.autorizaJunta0AdmDocumento) && Objects.equals(agrupRepApellido2, that.agrupRepApellido2)
        && Objects.equals(altaNotifica, that.altaNotifica) && Objects.equals(agrupRepApellido1, that.agrupRepApellido1)
        && Objects.equals(repetAgrupaciones0AgrupNombre, that.repetAgrupaciones0AgrupNombre)
        && Objects.equals(solicitadas1SolMinimis, that.solicitadas1SolMinimis) && Objects.equals(agrupRepIdentificador, that.agrupRepIdentificador)
        && Objects.equals(firm1Dia, that.firm1Dia) && Objects.equals(solicitadasOSolFecha, that.solicitadasOSolFecha)
        && Objects.equals(codigoIdentificativo, that.codigoIdentificativo) && Objects.equals(repr1Tipovia, that.repr1Tipovia)
        && Objects.equals(repetAgrupaciones2AgrupNombre, that.repetAgrupaciones2AgrupNombre) && Objects.equals(soli1Email, that.soli1Email)
        && Objects.equals(soli1Numero, that.soli1Numero) && Objects.equals(soli1Codmunicipio, that.soli1Codmunicipio)
        && Objects.equals(datosTitulo, that.datosTitulo) && Objects.equals(concedidasOConMinimis, that.concedidasOConMinimis)
        && Objects.equals(repetAgrupaciones1AgrupSiglas, that.repetAgrupaciones1AgrupSiglas) && Objects.equals(repr1Nombrevia, that.repr1Nombrevia)
        && Objects.equals(autorizaJunta1AdmConsejeria, that.autorizaJunta1AdmConsejeria) && Objects.equals(errorFinalidadProyecto, that.errorFinalidadProyecto)
        && Objects.equals(notifEmailAutLugar, that.notifEmailAutLugar) && Objects.equals(repr1Tipoident, that.repr1Tipoident)
        && Objects.equals(solicitadasOSolMinimis, that.solicitadasOSolMinimis) && Objects.equals(soli1Tlfmovil, that.soli1Tlfmovil)
        && Objects.equals(repr1Titulo, that.repr1Titulo) && Objects.equals(soli1Cp, that.soli1Cp)
        && Objects.equals(repetAgrupaciones1AgrupIdentificador, that.repetAgrupaciones1AgrupIdentificador)
        && Objects.equals(concedidas1ConFecha, that.concedidas1ConFecha) && Objects.equals(repr1Codprov, that.repr1Codprov)
        && Objects.equals(soli1Poblacion, that.soli1Poblacion) && Objects.equals(textoOtras, that.textoOtras)
        && Objects.equals(soli1Nombrevia, that.soli1Nombrevia) && Objects.equals(repDatos0DatosEjecucion, that.repDatos0DatosEjecucion)
        && Objects.equals(soli1Escalera, that.soli1Escalera) && Objects.equals(repetAgrupaciones0AgrupIdentificador, that.repetAgrupaciones0AgrupIdentificador)
        && Objects.equals(soli1Sexo, that.soli1Sexo) && Objects.equals(soli1Portal, that.soli1Portal)
        && Objects.equals(consentExpresoRepr, that.consentExpresoRepr) && Objects.equals(soli1Codigo, that.soli1Codigo)
        && Objects.equals(autorizaGestor0OtrasFecha, that.autorizaGestor0OtrasFecha)
        && Objects.equals(autorizaJunta1AdmDocumento, that.autorizaJunta1AdmDocumento) && Objects.equals(concedidas1ConAdmin, that.concedidas1ConAdmin)
        && Objects.equals(soli1Kmvia, that.soli1Kmvia) && Objects.equals(cbDeclaro3, that.cbDeclaro3) && Objects.equals(cbDeclaro2, that.cbDeclaro2)
        && Objects.equals(cbDeclaro1, that.cbDeclaro1) && Objects.equals(tipoDestinatario, that.tipoDestinatario) && Objects.equals(checkOtra, that.checkOtra)
        && Objects.equals(autorizaJunta0AdmProcedimiento, that.autorizaJunta0AdmProcedimiento)
        && Objects.equals(autorizaJunta0AdmFecha, that.autorizaJunta0AdmFecha)
        && Objects.equals(autorizaGestor0OtrasProcedimiento, that.autorizaGestor0OtrasProcedimiento)
        && Objects.equals(concedidasOConFecha, that.concedidasOConFecha) && Objects.equals(calidadDe, that.calidadDe)
        && Objects.equals(concedidas0ConImporte, that.concedidas0ConImporte) && Objects.equals(concedidas1ConMinimis, that.concedidas1ConMinimis)
        && Objects.equals(repetAgrupaciones0AgrupSiglas, that.repetAgrupaciones0AgrupSiglas) && Objects.equals(soli1Nombre, that.soli1Nombre)
        && Objects.equals(repr1Numident, that.repr1Numident) && Objects.equals(soli1Tipovia, that.soli1Tipovia) && Objects.equals(checkTipo2, that.checkTipo2)
        && Objects.equals(checkFinalidad3, that.checkFinalidad3) && Objects.equals(checkTipo1, that.checkTipo1)
        && Objects.equals(checkFinalidad4, that.checkFinalidad4) && Objects.equals(checkFinalidad1, that.checkFinalidad1)
        && Objects.equals(checkFinalidad2, that.checkFinalidad2) && Objects.equals(soli1Bloque, that.soli1Bloque)
        && Objects.equals(repetAgrupaciones2AgrupIdentificador, that.repetAgrupaciones2AgrupIdentificador)
        && Objects.equals(repr1Codmunicipio, that.repr1Codmunicipio) && Objects.equals(soli1Letra, that.soli1Letra)
        && Objects.equals(repDatos2DatosEjecucion, that.repDatos2DatosEjecucion) && Objects.equals(concedidas1ConImporte, that.concedidas1ConImporte)
        && Objects.equals(soli1Codprov, that.soli1Codprov) && Objects.equals(repetAgrupaciones1AgrupNombre, that.repetAgrupaciones1AgrupNombre)
        && Objects.equals(solicitadas1SolImporte, that.solicitadas1SolImporte) && Objects.equals(solicitadas1SolFecha, that.solicitadas1SolFecha)
        && Objects.equals(soli1Puerta, that.soli1Puerta) && Objects.equals(autorizaGestor1OtrasAdministracion, that.autorizaGestor1OtrasAdministracion)
        && Objects.equals(autorizaJunta0AdmConsejeria, that.autorizaJunta0AdmConsejeria)
        && Objects.equals(autorizaJunta1AdmProcedimiento, that.autorizaJunta1AdmProcedimiento)
        && Objects.equals(medioNotifTelematica, that.medioNotifTelematica) && Objects.equals(solicitadas1SolAdmin, that.solicitadas1SolAdmin)
        && Objects.equals(firm1Lugar, that.firm1Lugar) && Objects.equals(cbDeclaro4, that.cbDeclaro4) && Objects.equals(cbDeclaro5, that.cbDeclaro5)
        && Objects.equals(cbDeclaro6, that.cbDeclaro6) && Objects.equals(repr1Apellido1, that.repr1Apellido1)
        && Objects.equals(notifNombreLugar, that.notifNombreLugar) && Objects.equals(repr1Apellido2, that.repr1Apellido2)
        && Objects.equals(autorizaGestor0OtrasAdministracion, that.autorizaGestor0OtrasAdministracion) && Objects.equals(firm1Mes, that.firm1Mes)
        && Objects.equals(soli1MedioNotifTelematica, that.soli1MedioNotifTelematica) && Objects.equals(soli1Telefono, that.soli1Telefono)
        && Objects.equals(soli1Tipoident, that.soli1Tipoident) && Objects.equals(vbTecnico, that.vbTecnico) && Objects.equals(vbCoordinador, that.vbCoordinador)
        && Objects.equals(descripcion, that.descripcion) && Objects.equals(municipio, that.municipio) && Objects.equals(fhInicio, that.fhInicio)
        && Objects.equals(fkPais, that.fkPais) && Objects.equals(plazo, that.plazo) && Objects.equals(fhPagoSubvencion, that.fhPagoSubvencion)
        && Objects.equals(fhPresentacion, that.fhPresentacion) && Objects.equals(nuNuminterno, that.nuNuminterno)
        && Objects.equals(lgRepresentacion, that.lgRepresentacion) && Objects.equals(txLugarRegistro, that.txLugarRegistro)
        && Objects.equals(fhRegistro, that.fhRegistro) && Objects.equals(txRespApellidos, that.txRespApellidos)
        && Objects.equals(txRespNombre, that.txRespNombre) && Objects.equals(txRespNif, that.txRespNif) && Objects.equals(checkTipo3, that.checkTipo3)
        && Objects.equals(checkTipo4, that.checkTipo4) && Objects.equals(checkDoc4Bis, that.checkDoc4Bis) && Objects.equals(checkDoc14Bis, that.checkDoc14Bis);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, numExpediente, personaNotif, repetAgrupaciones1AgrupRacda, soli1MedioNotifOrdinaria, repetAgrupaciones2AgrupSiglas, soli1Apellido1,
        repr1Nombre, soli1Apellido2, agrupRepNombre, autorizaGestor1OtrasFecha, autorizaGest1OtrasDocumento, soli1Piso, firm1Ano, repDatos1Ejecucion,
        notifTelefonoAutLugar, autorizaGestor1OtrasProcedimiento, solicitadas0SolImporte, repr1Sexo, soli1Siglas, concedidas0ConAdmin, firm1Importe,
        notifNumidentLugar, repetAgrupaciones0AgrupRacda, soli1AltaNotifica, repetAgrupaciones2AgrupRacda, soli1Numident, firm1Fdo, autorizaJunta1AdmFecha,
        solicitadasOSolAdmin, autorizaGestor0OtrasDocumento, autorizaJunta0AdmDocumento, agrupRepApellido2, altaNotifica, agrupRepApellido1,
        repetAgrupaciones0AgrupNombre, solicitadas1SolMinimis, agrupRepIdentificador, firm1Dia, solicitadasOSolFecha, codigoIdentificativo, repr1Tipovia,
        repetAgrupaciones2AgrupNombre, soli1Email, soli1Numero, soli1Codmunicipio, datosTitulo, concedidasOConMinimis, repetAgrupaciones1AgrupSiglas,
        repr1Nombrevia, autorizaJunta1AdmConsejeria, errorFinalidadProyecto, notifEmailAutLugar, repr1Tipoident, solicitadasOSolMinimis, soli1Tlfmovil,
        repr1Titulo, soli1Cp, repetAgrupaciones1AgrupIdentificador, concedidas1ConFecha, repr1Codprov, soli1Poblacion, textoOtras, soli1Nombrevia,
        repDatos0DatosEjecucion, soli1Escalera, repetAgrupaciones0AgrupIdentificador, soli1Sexo, soli1Portal, consentExpresoRepr, soli1Codigo,
        autorizaGestor0OtrasFecha, autorizaJunta1AdmDocumento, concedidas1ConAdmin, soli1Kmvia, cbDeclaro3, cbDeclaro2, cbDeclaro1, tipoDestinatario, checkOtra,
        autorizaJunta0AdmProcedimiento, autorizaJunta0AdmFecha, autorizaGestor0OtrasProcedimiento, concedidasOConFecha, calidadDe, concedidas0ConImporte,
        concedidas1ConMinimis, repetAgrupaciones0AgrupSiglas, soli1Nombre, repr1Numident, soli1Tipovia, checkTipo2, checkFinalidad3, checkTipo1,
        checkFinalidad4, checkFinalidad1, checkFinalidad2, soli1Bloque, repetAgrupaciones2AgrupIdentificador, repr1Codmunicipio, soli1Letra,
        repDatos2DatosEjecucion, concedidas1ConImporte, soli1Codprov, repetAgrupaciones1AgrupNombre, solicitadas1SolImporte, solicitadas1SolFecha, soli1Puerta,
        autorizaGestor1OtrasAdministracion, autorizaJunta0AdmConsejeria, autorizaJunta1AdmProcedimiento, medioNotifTelematica, solicitadas1SolAdmin, firm1Lugar,
        cbDeclaro4, cbDeclaro5, cbDeclaro6, repr1Apellido1, notifNombreLugar, repr1Apellido2, autorizaGestor0OtrasAdministracion, firm1Mes,
        soli1MedioNotifTelematica, soli1Telefono, soli1Tipoident, vbTecnico, vbCoordinador, descripcion, municipio, fhInicio, fkPais, plazo, fhPagoSubvencion,
        fhPresentacion, nuNuminterno, lgRepresentacion, txLugarRegistro, fhRegistro, txRespApellidos, txRespNombre, txRespNif, checkTipo3, checkTipo4,
        checkDoc4Bis, checkDoc14Bis);
  }

  @OneToMany(mappedBy = "aaciTSolicitudsubongdByIdSolicitud")
  @LazyCollection(LazyCollectionOption.FALSE)
  public List<AaciAgrupaciones> getAaciAgrupacionesByIdSolicitud() {
    return aaciAgrupacionesByIdSolicitud;
  }

  public void setAaciAgrupacionesByIdSolicitud(final List<AaciAgrupaciones> aaciAgrupacionesByIdSolicitud) {
    this.aaciAgrupacionesByIdSolicitud = aaciAgrupacionesByIdSolicitud;
  }

  @OneToMany(mappedBy = "aaciTSolicitudsubongdByIdSolicitud")
  @LazyCollection(LazyCollectionOption.FALSE)
  public List<AaciTConOtrasSubvenciones> getAaciTConOtrasSubvencionesByIdSolicitud() {
    return aaciTConOtrasSubvencionesByIdSolicitud;
  }

  public void setAaciTConOtrasSubvencionesByIdSolicitud(final List<AaciTConOtrasSubvenciones> aaciTConOtrasSubvencionesByIdSolicitud) {
    this.aaciTConOtrasSubvencionesByIdSolicitud = aaciTConOtrasSubvencionesByIdSolicitud;
  }

  @OneToMany(mappedBy = "aaciTSolicitudsubongdByIdSolicitud")
  @LazyCollection(LazyCollectionOption.FALSE)
  public List<AaciTDocPoderAdminJa> getAaciTDocPoderAdminJasByIdSolicitud() {
    return aaciTDocPoderAdminJasByIdSolicitud;
  }

  public void setAaciTDocPoderAdminJasByIdSolicitud(final List<AaciTDocPoderAdminJa> aaciTDocPoderAdminJasByIdSolicitud) {
    this.aaciTDocPoderAdminJasByIdSolicitud = aaciTDocPoderAdminJasByIdSolicitud;
  }

  @OneToMany(mappedBy = "aaciTSolicitudsubongdByIdSolicitud")
  @LazyCollection(LazyCollectionOption.FALSE)
  public List<AaciTDocPoderOtrAdmin> getAaciTDocPoderOtrAdminsByIdSolicitud() {
    return aaciTDocPoderOtrAdminsByIdSolicitud;
  }

  public void setAaciTDocPoderOtrAdminsByIdSolicitud(final List<AaciTDocPoderOtrAdmin> aaciTDocPoderOtrAdminsByIdSolicitud) {
    this.aaciTDocPoderOtrAdminsByIdSolicitud = aaciTDocPoderOtrAdminsByIdSolicitud;
  }

  @OneToMany(mappedBy = "aaciTSolicitudsubongdByIdSolicitud")
  @LazyCollection(LazyCollectionOption.FALSE)
  public List<AaciTPaisesSolicitud> getAaciTPaisesSolicitudsByIdSolicitud() {
    return aaciTPaisesSolicitudsByIdSolicitud;
  }

  public void setAaciTPaisesSolicitudsByIdSolicitud(final List<AaciTPaisesSolicitud> aaciTPaisesSolicitudsByIdSolicitud) {
    this.aaciTPaisesSolicitudsByIdSolicitud = aaciTPaisesSolicitudsByIdSolicitud;
  }

  @ManyToOne
  @JoinColumn(name = "CONV_NU_ID", referencedColumnName = "CONV_NU_ID")
  public AaciTConvocatorias getAaciTConvocatoriasByConvNuId() {
    return aaciTConvocatoriasByConvNuId;
  }

  public void setAaciTConvocatoriasByConvNuId(final AaciTConvocatorias aaciTConvocatoriasByConvNuId) {
    this.aaciTConvocatoriasByConvNuId = aaciTConvocatoriasByConvNuId;
  }

  @OneToMany(mappedBy = "aaciTSolicitudsubongdByIdSolicitud")
  @LazyCollection(LazyCollectionOption.FALSE)
  public List<AaciTSolOtrasSubvenciones> getAaciTSolOtrasSubvencionesByIdSolicitud() {
    return aaciTSolOtrasSubvencionesByIdSolicitud;
  }

  public void setAaciTSolOtrasSubvencionesByIdSolicitud(final List<AaciTSolOtrasSubvenciones> aaciTSolOtrasSubvencionesByIdSolicitud) {
    this.aaciTSolOtrasSubvencionesByIdSolicitud = aaciTSolOtrasSubvencionesByIdSolicitud;
  }

  /**
   * Obtiene la propiedad respReqSubsanacion
   *
   * @return el respReqSubsanacion
   */
  @Basic
  @Column(name = "TX_RESP_REQ_SUBSANACION", insertable = false, updatable = false)
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
   * Obtiene la propiedad oCheckDoc1
   *
   * @return el oCheckDoc1
   */
  public Boolean getoCheckDoc1() {
    return oCheckDoc1;
  }

  /**
   * Establece el valor de la propiedad oCheckDoc1
   *
   * @param oCheckDoc1
   *          el oCheckDoc1 para establecer
   */
  public void setoCheckDoc1(final Boolean oCheckDoc1) {
    this.oCheckDoc1 = oCheckDoc1;
  }

  /**
   * Obtiene la propiedad oCheckDoc2
   *
   * @return el oCheckDoc2
   */
  public Boolean getoCheckDoc2() {
    return oCheckDoc2;
  }

  /**
   * Establece el valor de la propiedad oCheckDoc2
   *
   * @param oCheckDoc2
   *          el oCheckDoc2 para establecer
   */
  public void setoCheckDoc2(final Boolean oCheckDoc2) {
    this.oCheckDoc2 = oCheckDoc2;
  }

  /**
   * Obtiene la propiedad oCheckDoc3
   *
   * @return el oCheckDoc3
   */
  public Boolean getoCheckDoc3() {
    return oCheckDoc3;
  }

  /**
   * Establece el valor de la propiedad oCheckDoc3
   *
   * @param oCheckDoc3
   *          el oCheckDoc3 para establecer
   */
  public void setoCheckDoc3(final Boolean oCheckDoc3) {
    this.oCheckDoc3 = oCheckDoc3;
  }

  /**
   * Obtiene la propiedad oCheckDoc4
   *
   * @return el oCheckDoc4
   */
  public Boolean getoCheckDoc4() {
    return oCheckDoc4;
  }

  /**
   * Establece el valor de la propiedad oCheckDoc4
   *
   * @param oCheckDoc4
   *          el oCheckDoc4 para establecer
   */
  public void setoCheckDoc4(final Boolean oCheckDoc4) {
    this.oCheckDoc4 = oCheckDoc4;
  }

  /**
   * Obtiene la propiedad oCheckDoc5
   *
   * @return el oCheckDoc5
   */
  public Boolean getoCheckDoc5() {
    return oCheckDoc5;
  }

  /**
   * Establece el valor de la propiedad oCheckDoc5
   *
   * @param oCheckDoc5
   *          el oCheckDoc5 para establecer
   */
  public void setoCheckDoc5(final Boolean oCheckDoc5) {
    this.oCheckDoc5 = oCheckDoc5;
  }

  /**
   * Obtiene la propiedad oCheckDoc6
   *
   * @return el oCheckDoc6
   */
  public Boolean getoCheckDoc6() {
    return oCheckDoc6;
  }

  /**
   * Establece el valor de la propiedad oCheckDoc6
   *
   * @param oCheckDoc6
   *          el oCheckDoc6 para establecer
   */
  public void setoCheckDoc6(final Boolean oCheckDoc6) {
    this.oCheckDoc6 = oCheckDoc6;
  }

  /**
   * Obtiene la propiedad oCheckDoc7
   *
   * @return el oCheckDoc7
   */
  public Boolean getoCheckDoc7() {
    return oCheckDoc7;
  }

  /**
   * Establece el valor de la propiedad oCheckDoc7
   *
   * @param oCheckDoc7
   *          el oCheckDoc7 para establecer
   */
  public void setoCheckDoc7(final Boolean oCheckDoc7) {
    this.oCheckDoc7 = oCheckDoc7;
  }

  /**
   * Obtiene la propiedad oCheckDoc8
   *
   * @return el oCheckDoc8
   */
  public Boolean getoCheckDoc8() {
    return oCheckDoc8;
  }

  /**
   * Establece el valor de la propiedad oCheckDoc8
   *
   * @param oCheckDoc8
   *          el oCheckDoc8 para establecer
   */
  public void setoCheckDoc8(final Boolean oCheckDoc8) {
    this.oCheckDoc8 = oCheckDoc8;
  }

  /**
   * Obtiene la propiedad oCheckDoc9
   *
   * @return el oCheckDoc9
   */
  public Boolean getoCheckDoc9() {
    return oCheckDoc9;
  }

  /**
   * Establece el valor de la propiedad oCheckDoc9
   *
   * @param oCheckDoc9
   *          el oCheckDoc9 para establecer
   */
  public void setoCheckDoc9(final Boolean oCheckDoc9) {
    this.oCheckDoc9 = oCheckDoc9;
  }

  /**
   * Obtiene la propiedad oCheckDoc10
   *
   * @return el oCheckDoc10
   */
  public Boolean getoCheckDoc10() {
    return oCheckDoc10;
  }

  /**
   * Establece el valor de la propiedad oCheckDoc10
   *
   * @param oCheckDoc10
   *          el oCheckDoc10 para establecer
   */
  public void setoCheckDoc10(final Boolean oCheckDoc10) {
    this.oCheckDoc10 = oCheckDoc10;
  }

  /**
   * Obtiene la propiedad oCheckDoc11
   *
   * @return el oCheckDoc11
   */
  public Boolean getoCheckDoc11() {
    return oCheckDoc11;
  }

  /**
   * Establece el valor de la propiedad oCheckDoc11
   *
   * @param oCheckDoc11
   *          el oCheckDoc11 para establecer
   */
  public void setoCheckDoc11(final Boolean oCheckDoc11) {
    this.oCheckDoc11 = oCheckDoc11;
  }

  /**
   * Obtiene la propiedad oCheckDoc12
   *
   * @return el oCheckDoc12
   */
  public Boolean getoCheckDoc12() {
    return oCheckDoc12;
  }

  /**
   * Establece el valor de la propiedad oCheckDoc12
   *
   * @param oCheckDoc12
   *          el oCheckDoc12 para establecer
   */
  public void setoCheckDoc12(final Boolean oCheckDoc12) {
    this.oCheckDoc12 = oCheckDoc12;
  }

  /**
   * Obtiene la propiedad oCheckDoc13
   *
   * @return el oCheckDoc13
   */
  public Boolean getoCheckDoc13() {
    return oCheckDoc13;
  }

  /**
   * Establece el valor de la propiedad oCheckDoc13
   *
   * @param oCheckDoc13
   *          el oCheckDoc13 para establecer
   */
  public void setoCheckDoc13(final Boolean oCheckDoc13) {
    this.oCheckDoc13 = oCheckDoc13;
  }

  /**
   * Obtiene la propiedad oCheckDoc14
   *
   * @return el oCheckDoc14
   */
  public Boolean getoCheckDoc14() {
    return oCheckDoc14;
  }

  /**
   * Establece el valor de la propiedad oCheckDoc14
   *
   * @param oCheckDoc14
   *          el oCheckDoc14 para establecer
   */
  public void setoCheckDoc14(final Boolean oCheckDoc14) {
    this.oCheckDoc14 = oCheckDoc14;
  }

  /**
   * Obtiene la propiedad oCheckDoc15
   *
   * @return el oCheckDoc15
   */
  public Boolean getoCheckDoc15() {
    return oCheckDoc15;
  }

  /**
   * Establece el valor de la propiedad oCheckDoc15
   *
   * @param oCheckDoc15
   *          el oCheckDoc15 para establecer
   */
  public void setoCheckDoc15(final Boolean oCheckDoc15) {
    this.oCheckDoc15 = oCheckDoc15;
  }

  /**
   * Obtiene la propiedad oCheckDoc16
   *
   * @return el oCheckDoc16
   */
  public Boolean getoCheckDoc16() {
    return oCheckDoc16;
  }

  /**
   * Establece el valor de la propiedad oCheckDoc16
   *
   * @param oCheckDoc16
   *          el oCheckDoc16 para establecer
   */
  public void setoCheckDoc16(final Boolean oCheckDoc16) {
    this.oCheckDoc16 = oCheckDoc16;
  }

  /**
   * Obtiene la propiedad oCheckDoc17
   *
   * @return el oCheckDoc17
   */
  public Boolean getoCheckDoc17() {
    return oCheckDoc17;
  }

  /**
   * Establece el valor de la propiedad oCheckDoc17
   *
   * @param oCheckDoc17
   *          el oCheckDoc17 para establecer
   */
  public void setoCheckDoc17(final Boolean oCheckDoc17) {
    this.oCheckDoc17 = oCheckDoc17;
  }

  /**
   * Obtiene la propiedad oCheckDoc18
   *
   * @return el oCheckDoc18
   */
  public Boolean getoCheckDoc18() {
    return oCheckDoc18;
  }

  /**
   * Establece el valor de la propiedad oCheckDoc18
   *
   * @param oCheckDoc18
   *          el oCheckDoc18 para establecer
   */
  public void setoCheckDoc18(final Boolean oCheckDoc18) {
    this.oCheckDoc18 = oCheckDoc18;
  }

  /**
   * Obtiene la propiedad oCheckDoc19
   *
   * @return el oCheckDoc19
   */
  public Boolean getoCheckDoc19() {
    return oCheckDoc19;
  }

  /**
   * Establece el valor de la propiedad oCheckDoc19
   *
   * @param oCheckDoc19
   *          el oCheckDoc19 para establecer
   */
  public void setoCheckDoc19(final Boolean oCheckDoc19) {
    this.oCheckDoc19 = oCheckDoc19;
  }

  /**
   * Obtiene la propiedad oCheckDoc20
   *
   * @return el oCheckDoc20
   */
  public Boolean getoCheckDoc20() {
    return oCheckDoc20;
  }

  /**
   * Establece el valor de la propiedad oCheckDoc20
   *
   * @param oCheckDoc20
   *          el oCheckDoc20 para establecer
   */
  public void setoCheckDoc20(final Boolean oCheckDoc20) {
    this.oCheckDoc20 = oCheckDoc20;
  }

  /**
   * Obtiene la propiedad oCheckDoc21
   *
   * @return el oCheckDoc21
   */
  public Boolean getoCheckDoc21() {
    return oCheckDoc21;
  }

  /**
   * Establece el valor de la propiedad oCheckDoc21
   *
   * @param oCheckDoc21
   *          el oCheckDoc21 para establecer
   */
  public void setoCheckDoc21(final Boolean oCheckDoc21) {
    this.oCheckDoc21 = oCheckDoc21;
  }

  /**
   * Obtiene la propiedad oCheckDoc22
   *
   * @return el oCheckDoc22
   */
  public Boolean getoCheckDoc22() {
    return oCheckDoc22;
  }

  /**
   * Establece el valor de la propiedad oCheckDoc22
   *
   * @param oCheckDoc22
   *          el oCheckDoc22 para establecer
   */
  public void setoCheckDoc22(final Boolean oCheckDoc22) {
    this.oCheckDoc22 = oCheckDoc22;
  }

  /**
   * Obtiene la propiedad oCheckDoc23
   *
   * @return el oCheckDoc23
   */
  public Boolean getoCheckDoc23() {
    return oCheckDoc23;
  }

  /**
   * Establece el valor de la propiedad oCheckDoc23
   *
   * @param oCheckDoc23
   *          el oCheckDoc23 para establecer
   */
  public void setoCheckDoc23(final Boolean oCheckDoc23) {
    this.oCheckDoc23 = oCheckDoc23;
  }

  /**
   * Obtiene la propiedad oCheckDoc24
   *
   * @return el oCheckDoc24
   */
  public Boolean getoCheckDoc24() {
    return oCheckDoc24;
  }

  /**
   * Establece el valor de la propiedad oCheckDoc24
   *
   * @param oCheckDoc24
   *          el oCheckDoc24 para establecer
   */
  public void setoCheckDoc24(final Boolean oCheckDoc24) {
    this.oCheckDoc24 = oCheckDoc24;
  }

  /**
   * Obtiene la propiedad oCheckDoc25
   *
   * @return el oCheckDoc25
   */
  public Boolean getoCheckDoc25() {
    return oCheckDoc25;
  }

  /**
   * Establece el valor de la propiedad oCheckDoc25
   *
   * @param oCheckDoc25
   *          el oCheckDoc25 para establecer
   */
  public void setoCheckDoc25(final Boolean oCheckDoc25) {
    this.oCheckDoc25 = oCheckDoc25;
  }

  /**
   * Obtiene la propiedad oCheckDoc26
   *
   * @return el oCheckDoc26
   */
  public Boolean getoCheckDoc26() {
    return oCheckDoc26;
  }

  /**
   * Establece el valor de la propiedad oCheckDoc26
   *
   * @param oCheckDoc26
   *          el oCheckDoc26 para establecer
   */
  public void setoCheckDoc26(final Boolean oCheckDoc26) {
    this.oCheckDoc26 = oCheckDoc26;
  }

  /**
   * Obtiene la propiedad oCheckDoc27
   *
   * @return el oCheckDoc27
   */
  public Boolean getoCheckDoc27() {
    return oCheckDoc27;
  }

  /**
   * Establece el valor de la propiedad oCheckDoc27
   *
   * @param oCheckDoc27
   *          el oCheckDoc27 para establecer
   */
  public void setoCheckDoc27(final Boolean oCheckDoc27) {
    this.oCheckDoc27 = oCheckDoc27;
  }

  /**
   * Obtiene la propiedad oCheckDoc28
   *
   * @return el oCheckDoc28
   */
  public Boolean getoCheckDoc28() {
    return oCheckDoc28;
  }

  /**
   * Establece el valor de la propiedad oCheckDoc28
   *
   * @param oCheckDoc28
   *          el oCheckDoc28 para establecer
   */
  public void setoCheckDoc28(final Boolean oCheckDoc28) {
    this.oCheckDoc28 = oCheckDoc28;
  }

  /**
   * Obtiene la propiedad oCheckDoc29
   *
   * @return el oCheckDoc29
   */
  public Boolean getoCheckDoc29() {
    return oCheckDoc29;
  }

  /**
   * Establece el valor de la propiedad oCheckDoc29
   *
   * @param oCheckDoc29
   *          el oCheckDoc29 para establecer
   */
  public void setoCheckDoc29(final Boolean oCheckDoc29) {
    this.oCheckDoc29 = oCheckDoc29;
  }

  /**
   * Obtiene la propiedad oCheckDoc30
   *
   * @return el oCheckDoc30
   */
  public Boolean getoCheckDoc30() {
    return oCheckDoc30;
  }

  /**
   * Establece el valor de la propiedad oCheckDoc30
   *
   * @param oCheckDoc30
   *          el oCheckDoc30 para establecer
   */
  public void setoCheckDoc30(final Boolean oCheckDoc30) {
    this.oCheckDoc30 = oCheckDoc30;
  }

  /**
   * Obtiene la propiedad oCheckDoc31
   *
   * @return el oCheckDoc31
   */
  public Boolean getoCheckDoc31() {
    return oCheckDoc31;
  }

  /**
   * Establece el valor de la propiedad oCheckDoc31
   *
   * @param oCheckDoc31
   *          el oCheckDoc31 para establecer
   */
  public void setoCheckDoc31(final Boolean oCheckDoc31) {
    this.oCheckDoc31 = oCheckDoc31;
  }

  /**
   * Obtiene la propiedad oCheckDoc32
   *
   * @return el oCheckDoc32
   */
  public Boolean getoCheckDoc32() {
    return oCheckDoc32;
  }

  /**
   * Establece el valor de la propiedad oCheckDoc32
   *
   * @param oCheckDoc32
   *          el oCheckDoc32 para establecer
   */
  public void setoCheckDoc32(final Boolean oCheckDoc32) {
    this.oCheckDoc32 = oCheckDoc32;
  }

  /**
   * Obtiene la propiedad tCheckDoc1
   *
   * @return el tCheckDoc1
   */
  public Boolean gettCheckDoc1() {
    return tCheckDoc1;
  }

  /**
   * Establece el valor de la propiedad tCheckDoc1
   *
   * @param tCheckDoc1
   *          el tCheckDoc1 para establecer
   */
  public void settCheckDoc1(final Boolean tCheckDoc1) {
    this.tCheckDoc1 = tCheckDoc1;
  }

  /**
   * Obtiene la propiedad tCheckDoc2
   *
   * @return el tCheckDoc2
   */
  public Boolean gettCheckDoc2() {
    return tCheckDoc2;
  }

  /**
   * Establece el valor de la propiedad tCheckDoc2
   *
   * @param tCheckDoc2
   *          el tCheckDoc2 para establecer
   */
  public void settCheckDoc2(final Boolean tCheckDoc2) {
    this.tCheckDoc2 = tCheckDoc2;
  }

  /**
   * Obtiene la propiedad tCheckDoc3
   *
   * @return el tCheckDoc3
   */
  public Boolean gettCheckDoc3() {
    return tCheckDoc3;
  }

  /**
   * Establece el valor de la propiedad tCheckDoc3
   *
   * @param tCheckDoc3
   *          el tCheckDoc3 para establecer
   */
  public void settCheckDoc3(final Boolean tCheckDoc3) {
    this.tCheckDoc3 = tCheckDoc3;
  }

  /**
   * Obtiene la propiedad tCheckDoc4
   *
   * @return el tCheckDoc4
   */
  public Boolean gettCheckDoc4() {
    return tCheckDoc4;
  }

  /**
   * Establece el valor de la propiedad tCheckDoc4
   *
   * @param tCheckDoc4
   *          el tCheckDoc4 para establecer
   */
  public void settCheckDoc4(final Boolean tCheckDoc4) {
    this.tCheckDoc4 = tCheckDoc4;
  }

  /**
   * Obtiene la propiedad tCheckDoc5
   *
   * @return el tCheckDoc5
   */
  public Boolean gettCheckDoc5() {
    return tCheckDoc5;
  }

  /**
   * Establece el valor de la propiedad tCheckDoc5
   *
   * @param tCheckDoc5
   *          el tCheckDoc5 para establecer
   */
  public void settCheckDoc5(final Boolean tCheckDoc5) {
    this.tCheckDoc5 = tCheckDoc5;
  }

  /**
   * Obtiene la propiedad tCheckDoc6
   *
   * @return el tCheckDoc6
   */
  public Boolean gettCheckDoc6() {
    return tCheckDoc6;
  }

  /**
   * Establece el valor de la propiedad tCheckDoc6
   *
   * @param tCheckDoc6
   *          el tCheckDoc6 para establecer
   */
  public void settCheckDoc6(final Boolean tCheckDoc6) {
    this.tCheckDoc6 = tCheckDoc6;
  }

  /**
   * Obtiene la propiedad tCheckDoc7
   *
   * @return el tCheckDoc7
   */
  public Boolean gettCheckDoc7() {
    return tCheckDoc7;
  }

  /**
   * Establece el valor de la propiedad tCheckDoc7
   *
   * @param tCheckDoc7
   *          el tCheckDoc7 para establecer
   */
  public void settCheckDoc7(final Boolean tCheckDoc7) {
    this.tCheckDoc7 = tCheckDoc7;
  }

  /**
   * Obtiene la propiedad tCheckDoc8
   *
   * @return el tCheckDoc8
   */
  public Boolean gettCheckDoc8() {
    return tCheckDoc8;
  }

  /**
   * Establece el valor de la propiedad tCheckDoc8
   *
   * @param tCheckDoc8
   *          el tCheckDoc8 para establecer
   */
  public void settCheckDoc8(final Boolean tCheckDoc8) {
    this.tCheckDoc8 = tCheckDoc8;
  }

  /**
   * Obtiene la propiedad tCheckDoc9
   *
   * @return el tCheckDoc9
   */
  public Boolean gettCheckDoc9() {
    return tCheckDoc9;
  }

  /**
   * Establece el valor de la propiedad tCheckDoc9
   *
   * @param tCheckDoc9
   *          el tCheckDoc9 para establecer
   */
  public void settCheckDoc9(final Boolean tCheckDoc9) {
    this.tCheckDoc9 = tCheckDoc9;
  }

  /**
   * Obtiene la propiedad tCheckDoc10
   *
   * @return el tCheckDoc10
   */
  public Boolean gettCheckDoc10() {
    return tCheckDoc10;
  }

  /**
   * Establece el valor de la propiedad tCheckDoc10
   *
   * @param tCheckDoc10
   *          el tCheckDoc10 para establecer
   */
  public void settCheckDoc10(final Boolean tCheckDoc10) {
    this.tCheckDoc10 = tCheckDoc10;
  }

  /**
   * Obtiene la propiedad tCheckDoc11
   *
   * @return el tCheckDoc11
   */
  public Boolean gettCheckDoc11() {
    return tCheckDoc11;
  }

  /**
   * Establece el valor de la propiedad tCheckDoc11
   *
   * @param tCheckDoc11
   *          el tCheckDoc11 para establecer
   */
  public void settCheckDoc11(final Boolean tCheckDoc11) {
    this.tCheckDoc11 = tCheckDoc11;
  }

  /**
   * Obtiene la propiedad tCheckDoc12
   *
   * @return el tCheckDoc12
   */
  public Boolean gettCheckDoc12() {
    return tCheckDoc12;
  }

  /**
   * Establece el valor de la propiedad tCheckDoc12
   *
   * @param tCheckDoc12
   *          el tCheckDoc12 para establecer
   */
  public void settCheckDoc12(final Boolean tCheckDoc12) {
    this.tCheckDoc12 = tCheckDoc12;
  }

  /**
   * Obtiene la propiedad tCheckDoc13
   *
   * @return el tCheckDoc13
   */
  public Boolean gettCheckDoc13() {
    return tCheckDoc13;
  }

  /**
   * Establece el valor de la propiedad tCheckDoc13
   *
   * @param tCheckDoc13
   *          el tCheckDoc13 para establecer
   */
  public void settCheckDoc13(final Boolean tCheckDoc13) {
    this.tCheckDoc13 = tCheckDoc13;
  }

  /**
   * Obtiene la propiedad tCheckDoc14
   *
   * @return el tCheckDoc14
   */
  public Boolean gettCheckDoc14() {
    return tCheckDoc14;
  }

  /**
   * Establece el valor de la propiedad tCheckDoc14
   *
   * @param tCheckDoc14
   *          el tCheckDoc14 para establecer
   */
  public void settCheckDoc14(final Boolean tCheckDoc14) {
    this.tCheckDoc14 = tCheckDoc14;
  }

  /**
   * Obtiene la propiedad tCheckDoc15
   *
   * @return el tCheckDoc15
   */
  public Boolean gettCheckDoc15() {
    return tCheckDoc15;
  }

  /**
   * Establece el valor de la propiedad tCheckDoc15
   *
   * @param tCheckDoc15
   *          el tCheckDoc15 para establecer
   */
  public void settCheckDoc15(final Boolean tCheckDoc15) {
    this.tCheckDoc15 = tCheckDoc15;
  }

  /**
   * Obtiene la propiedad tCheckDoc16
   *
   * @return el tCheckDoc16
   */
  public Boolean gettCheckDoc16() {
    return tCheckDoc16;
  }

  /**
   * Establece el valor de la propiedad tCheckDoc16
   *
   * @param tCheckDoc16
   *          el tCheckDoc16 para establecer
   */
  public void settCheckDoc16(final Boolean tCheckDoc16) {
    this.tCheckDoc16 = tCheckDoc16;
  }

  /**
   * Obtiene la propiedad tCheckDoc17
   *
   * @return el tCheckDoc17
   */
  public Boolean gettCheckDoc17() {
    return tCheckDoc17;
  }

  /**
   * Establece el valor de la propiedad tCheckDoc17
   *
   * @param tCheckDoc17
   *          el tCheckDoc17 para establecer
   */
  public void settCheckDoc17(final Boolean tCheckDoc17) {
    this.tCheckDoc17 = tCheckDoc17;
  }

  /**
   * Obtiene la propiedad tCheckDoc18
   *
   * @return el tCheckDoc18
   */
  public Boolean gettCheckDoc18() {
    return tCheckDoc18;
  }

  /**
   * Establece el valor de la propiedad tCheckDoc18
   *
   * @param tCheckDoc18
   *          el tCheckDoc18 para establecer
   */
  public void settCheckDoc18(final Boolean tCheckDoc18) {
    this.tCheckDoc18 = tCheckDoc18;
  }

  /**
   * Obtiene la propiedad tCheckDoc19
   *
   * @return el tCheckDoc19
   */
  public Boolean gettCheckDoc19() {
    return tCheckDoc19;
  }

  /**
   * Establece el valor de la propiedad tCheckDoc19
   *
   * @param tCheckDoc19
   *          el tCheckDoc19 para establecer
   */
  public void settCheckDoc19(final Boolean tCheckDoc19) {
    this.tCheckDoc19 = tCheckDoc19;
  }

  /**
   * Obtiene la propiedad tCheckDoc20
   *
   * @return el tCheckDoc20
   */
  public Boolean gettCheckDoc20() {
    return tCheckDoc20;
  }

  /**
   * Establece el valor de la propiedad tCheckDoc20
   *
   * @param tCheckDoc20
   *          el tCheckDoc20 para establecer
   */
  public void settCheckDoc20(final Boolean tCheckDoc20) {
    this.tCheckDoc20 = tCheckDoc20;
  }

  /**
   * Obtiene la propiedad tCheckDoc21
   *
   * @return el tCheckDoc21
   */
  public Boolean gettCheckDoc21() {
    return tCheckDoc21;
  }

  /**
   * Establece el valor de la propiedad tCheckDoc21
   *
   * @param tCheckDoc21
   *          el tCheckDoc21 para establecer
   */
  public void settCheckDoc21(final Boolean tCheckDoc21) {
    this.tCheckDoc21 = tCheckDoc21;
  }

  /**
   * Obtiene la propiedad tCheckDoc22
   *
   * @return el tCheckDoc22
   */
  public Boolean gettCheckDoc22() {
    return tCheckDoc22;
  }

  /**
   * Establece el valor de la propiedad tCheckDoc22
   *
   * @param tCheckDoc22
   *          el tCheckDoc22 para establecer
   */
  public void settCheckDoc22(final Boolean tCheckDoc22) {
    this.tCheckDoc22 = tCheckDoc22;
  }

  /**
   * Obtiene la propiedad tCheckDoc23
   *
   * @return el tCheckDoc23
   */
  public Boolean gettCheckDoc23() {
    return tCheckDoc23;
  }

  /**
   * Establece el valor de la propiedad tCheckDoc23
   *
   * @param tCheckDoc23
   *          el tCheckDoc23 para establecer
   */
  public void settCheckDoc23(final Boolean tCheckDoc23) {
    this.tCheckDoc23 = tCheckDoc23;
  }

  /**
   * Obtiene la propiedad tCheckDoc24
   *
   * @return el tCheckDoc24
   */
  public Boolean gettCheckDoc24() {
    return tCheckDoc24;
  }

  /**
   * Establece el valor de la propiedad tCheckDoc24
   *
   * @param tCheckDoc24
   *          el tCheckDoc24 para establecer
   */
  public void settCheckDoc24(final Boolean tCheckDoc24) {
    this.tCheckDoc24 = tCheckDoc24;
  }

  /**
   * Obtiene la propiedad tCheckDoc25
   *
   * @return el tCheckDoc25
   */
  public Boolean gettCheckDoc25() {
    return tCheckDoc25;
  }

  /**
   * Establece el valor de la propiedad tCheckDoc25
   *
   * @param tCheckDoc25
   *          el tCheckDoc25 para establecer
   */
  public void settCheckDoc25(final Boolean tCheckDoc25) {
    this.tCheckDoc25 = tCheckDoc25;
  }

  /**
   * Obtiene la propiedad tCheckDoc26
   *
   * @return el tCheckDoc26
   */
  public Boolean gettCheckDoc26() {
    return tCheckDoc26;
  }

  /**
   * Establece el valor de la propiedad tCheckDoc26
   *
   * @param tCheckDoc26
   *          el tCheckDoc26 para establecer
   */
  public void settCheckDoc26(final Boolean tCheckDoc26) {
    this.tCheckDoc26 = tCheckDoc26;
  }

  /**
   * Obtiene la propiedad tCheckDoc27
   *
   * @return el tCheckDoc27
   */
  public Boolean gettCheckDoc27() {
    return tCheckDoc27;
  }

  /**
   * Establece el valor de la propiedad tCheckDoc27
   *
   * @param tCheckDoc27
   *          el tCheckDoc27 para establecer
   */
  public void settCheckDoc27(final Boolean tCheckDoc27) {
    this.tCheckDoc27 = tCheckDoc27;
  }

  /**
   * Obtiene la propiedad tCheckDoc28
   *
   * @return el tCheckDoc28
   */
  public Boolean gettCheckDoc28() {
    return tCheckDoc28;
  }

  /**
   * Establece el valor de la propiedad tCheckDoc28
   *
   * @param tCheckDoc28
   *          el tCheckDoc28 para establecer
   */
  public void settCheckDoc28(final Boolean tCheckDoc28) {
    this.tCheckDoc28 = tCheckDoc28;
  }

  /**
   * Obtiene la propiedad tCheckDoc29
   *
   * @return el tCheckDoc29
   */
  public Boolean gettCheckDoc29() {
    return tCheckDoc29;
  }

  /**
   * Establece el valor de la propiedad tCheckDoc29
   *
   * @param tCheckDoc29
   *          el tCheckDoc29 para establecer
   */
  public void settCheckDoc29(final Boolean tCheckDoc29) {
    this.tCheckDoc29 = tCheckDoc29;
  }

  /**
   * Obtiene la propiedad tCheckDoc30
   *
   * @return el tCheckDoc30
   */
  public Boolean gettCheckDoc30() {
    return tCheckDoc30;
  }

  /**
   * Establece el valor de la propiedad tCheckDoc30
   *
   * @param tCheckDoc30
   *          el tCheckDoc30 para establecer
   */
  public void settCheckDoc30(final Boolean tCheckDoc30) {
    this.tCheckDoc30 = tCheckDoc30;
  }

  /**
   * Obtiene la propiedad tCheckDoc31
   *
   * @return el tCheckDoc31
   */
  public Boolean gettCheckDoc31() {
    return tCheckDoc31;
  }

  /**
   * Establece el valor de la propiedad tCheckDoc31
   *
   * @param tCheckDoc31
   *          el tCheckDoc31 para establecer
   */
  public void settCheckDoc31(final Boolean tCheckDoc31) {
    this.tCheckDoc31 = tCheckDoc31;
  }

  /**
   * Obtiene la propiedad tCheckDoc32
   *
   * @return el tCheckDoc32
   */
  public Boolean gettCheckDoc32() {
    return tCheckDoc32;
  }

  /**
   * Establece el valor de la propiedad tCheckDoc32
   *
   * @param tCheckDoc32
   *          el tCheckDoc32 para establecer
   */
  public void settCheckDoc32(final Boolean tCheckDoc32) {
    this.tCheckDoc32 = tCheckDoc32;
  }

  /**
   * Obtiene la propiedad codDir3
   *
   * @return el codDir3
   */
  public String getCodDir3() {
    return codDir3;
  }

  /**
   * Establece el valor de la propiedad codDir3
   *
   * @param codDir3
   *          el codDir3 para establecer
   */
  public void setCodDir3(final String codDir3) {
    this.codDir3 = codDir3;
  }

  /**
   * Obtiene la propiedad textoSubsanacion
   *
   * @return el textoSubsanacion
   */
  @Basic
  @Column(name = "TX_RESP_REQ_SUBSANACION")
  public String getTextoSubsanacion() {
    return textoSubsanacion;
  }

  /**
   * Establece el valor de la propiedad textoSubsanacion
   *
   * @param textoSubsanacion
   *          el textoSubsanacion para establecer
   */
  public void setTextoSubsanacion(final String textoSubsanacion) {
    this.textoSubsanacion = textoSubsanacion;
  }

  /**
   * Obtiene la propiedad paisIntervencion
   *
   * @return el paisIntervencion
   */
  public String getPaisIntervencion() {
    return paisIntervencion;
  }

  /**
   * Establece el valor de la propiedad paisIntervencion
   *
   * @param paisIntervencion
   *          el paisIntervencion para establecer
   */
  public void setPaisIntervencion(final String paisIntervencion) {
    this.paisIntervencion = paisIntervencion;
  }

}
