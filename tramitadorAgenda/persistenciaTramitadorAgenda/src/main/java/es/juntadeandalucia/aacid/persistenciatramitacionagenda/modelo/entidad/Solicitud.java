package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "AACI_T_SOLICITUDSUBONGD", schema = "AACID_OWNER", catalog = "")
public class Solicitud implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = -8043280260070882217L;

  private Long idSolicitud;
  private Long nuNumexpediente;
  private Long nuNumInterno;
  private String txPersonanotif;
  private String txRepetagrup1Racda;
  private String txSoli1Mednotiford;
  private String txRepetagrup2Siglas;
  private String txSoli1Apellido1;
  private String txRepr1Nombre;
  private String txSoli1Apellido2;
  private String txAgruprepnombre;
  private Date fhAutorizagest1Otrasfec;
  private String txAutorizagest1Otrasdoc;
  private String txSoli1Piso;
  private String txFirm1Ano;
  private String txRepdatos1Ejecucion;
  private String txNotiftelefonoautlugar;
  private String txAutorizagest1Otraspro;
  private Long nuSolicitadasolimporte;
  private Boolean lgCheckdoc1;
  private String txRepr1Sexo;
  private String txSoli1Siglas;
  private Boolean lgCheckdoc2;
  private String txConcedidasconadmin;
  private Boolean lgCheckdoc3;
  private Long nuFirm1Importe;
  private Boolean lgCheckdoc4;
  private Boolean lgCheckdoc5;
  private Boolean lgCheckdoc6;
  private Boolean lgCheckdoc7;
  private Boolean lgCheckdoc8;
  private String txNotifnumidentlugar;
  private Boolean lgCheckdoc9;
  private String txRepetagrupracda;
  private Boolean lgSoli1Altanotifica;
  private String txRepetagrup2Racda;
  private String txSoli1Numident;
  private String txFirm1Fdo;
  private Date fhAutojunta1Admfecha;
  private String txSolicitadas0Admin;
  private String txAutorizagest0Otrasdoc;
  private String txAutorizajunta0Admdoc;
  private String txAgruprepapellido2;
  private Boolean lgAltanofifica;
  private String txAgruprepapellido1;
  private String txRepetagrupnombre;
  private String txSolicitadas1Minimis;
  private String txAgruprepidentific;
  private String txFirm1Dia;
  private String txSolicitadas0Fecha;
  private String txCodidentificativo;
  private String txRepr1Tipovia;
  private String txRepetagrup2Nombre;
  private String txSoli1Email;
  private String txSoli1Numero;
  private String txSoli1Codmunicipio;
  private String txDatostitulo;
  private String txConcedidas0Minimis;
  private String txRepetagrup1Siglas;
  private String txRepr1Nombrevia;
  private String txAutorizajunta1Admcons;
  private String txErrorfinalidadproy;
  private String txNotifemailautlugar;
  private String txRepr1Tipoident;
  private String txSolicitadas0Minimis;
  private Long nuSoli1Tlfmovil;
  private String txRepr1Titulo;
  private Long nuSoli1Cp;
  private String txRepetagrup1Ident;
  private Date fhConcedidas1Fecha;
  private Long nuRepr1Codprov;
  private String txSoli1Poblacion;
  private String lbTextootraas;
  private String txSoli1Nombrevia;
  private String txRepdatos0Ejecucion;
  private String txSoli1Escalera;
  private String txRepet0Agrupidentif;
  private String txSoli1Sexo;
  private Boolean lgCheckdoc20;
  private String txSoli1Portal;
  private Boolean lgConsentexpresorepr;
  private String txSoli1Codigo;
  private Date fhAutorizagest0Otrasfec;
  private String txAutorizajunta1Admdoc;
  private String txConcedidas1Conadmin;
  private String txSoli1Kmvia;
  private Boolean lgCheckdoc18;
  private Boolean lgCbdeclaro3;
  private Boolean lgCbdeclaro2;
  private Boolean lgCheckdoc19;
  private Boolean lgCbdeclaro1;
  private Boolean lgCheckdoc10;
  private String txTipodestinatario;
  private Boolean lgCheckdoc11;
  private Boolean lgCheckdoc12;
  private Boolean lgCheckdoc13;
  private Boolean lgCheckdoc14;
  private Boolean lgCheckdoc15;
  private Boolean lgCheckotra;
  private Boolean lgCheckdoc16;
  private String txAutorizajunta0Admproc;
  private Boolean lgCheckdoc17;
  private Date fhAutojunta0Admfecha;
  private String txAutorizagest0Otraspro;
  private Date fhConcedidas0Fecha;
  private String txCalidadde;
  private Long nuConcedidas0Conimporte;
  private String txConcedidas1Minimis;
  private String txRepetagrup0Siglas;
  private String txSoli1Nombre;
  private String txRepr1Numident;
  private String txSoli1Tipovia;
  private Boolean lgChecktipo2;
  private Boolean lgCheckfinalidad3;
  private Boolean lgChecktipo1;
  private Boolean lgCheckfinalidad4;
  private Boolean lgCheckfinalidad1;
  private Boolean lgCheckfinalidad2;
  private String txSoli1Bloque;
  private String txRepet2Agrupidentif;
  private String nuRepr1Codmunicipio;
  private String txSol1Letra;
  private String txRepdatos2Ejecucion;
  private Long nuConcedidas1Conimporte;
  private Long nuSoli1Codprov;
  private String txRepetagrup1Nombre;
  private Long nuSolicitadas1Importe;
  private Date fhSolicitadas1Fecha;
  private Date fhPagoSubvencion;
  private String txSoli1Puerta;
  private String txAutorizagest1Otrasadm;
  private String txAutorizajunta0Admcons;
  private String txAutorizajunta1Admproc;
  private Boolean lgMedionotiftelematica;
  private String txSolicitadas1Admin;
  private String txFirm1Lugar;
  private Boolean lgCbdeclaro4;
  private Boolean lgCbdeclaro5;
  private Boolean lgCbdeclaro6;
  private String txRepr1Apellido1;
  private String txNotifnombrelugar;
  private String txRepr1Apellido2;
  private String txAutorizagest0Otrasadm;
  private String txFirm1Mes;
  private Boolean lgSoli1Medionotiftelema;
  private Long nuSoli1Telefono;
  private String txSoli1Tipoident;
  private Boolean lgCheckdoc21;
  private transient List<Subsanacion> aaciSubsanacionsByIdSolicitud;
  private transient List<ExclusionesSolicitud> aaciExclusionesSolicitudsByIdSolicitud;
  private transient Convocatoria aaciTConvocatoriasByIdConvocatoria;
  private transient List<Contrapartes> aaciContrapartesByIdSolicitud;
  private transient List<Agrupaciones> agrupacionesByIdSolicitud;
  private transient List<PaisesSolicitud> paisesSolicitudsByIdSolicitud;

  private String txRepr1Email;
  private String txRepr1TlfMovil;
  private String txRepr1Telefono;

  private Boolean vbTecnico;
  private Boolean vbCoordinador;
  private String descripcion;

  private BigDecimal plazo;
  private String municipio;
  private String fkPais;
  private Date fhInicio;
  private Date fhPresentacion;
  private Date fhRegistro;

  private String txLugarRegistro;

  private Boolean lgRepresentacion;

  private String txRespNombre;
  private String txRespApellidos;
  private String txRespNif;

  private Boolean lgChecktipo3;
  private Boolean lgChecktipo4;

  private Date fhFechaInicio;
  private Date fhFechaFin;

  private String txBancoIban;

  private String txBancoCodigo;

  private String txBancoPais;

  private String txBancoLocalidad;

  private String txBancoSucursal;

  private String txBancoNumCuenta;

  private String txBancoSwiftCodBanco;

  private String txBancoSwiftPais;

  private String txBancoSwiftLocalidad;

  private String txBancoSwiftSucur;

  private String txBancoEntidad;

  private String txBancoDomicilio;

  private String txBancoCodProv;

  private String txBancoCodMunicipio;

  private String txBancoCP;

  private Boolean lgConceUno;

  private Boolean lgConceDos;

  private Boolean lgConceTres;

  private Boolean lgConceCuatro;

  private Boolean lgConceCinco;

  private Boolean lgConceSeis;

  private Boolean lgConceSiete;

  private Boolean lgConceOcho;

  private Boolean lgDoc1;

  private Boolean lgDoc2;

  private Boolean lgDoc3;

  private Boolean lgDoc4;

  private String txNoAcepto;

  private String txOtroA;

  private String txRefor;

  private List<DocumentoPoderAdminJA> documentosJA = new ArrayList<>();

  private List<DocumentoPoderOtrasAdmin> documentosOtrasAdmin = new ArrayList<>();

  private Date fhPostegracionComunIni;
  private Boolean lgAlego;
  private String txAlego;
  private Boolean lgOtroAlego;
  private String txOtroAlego;
  private String txCodDir3;

  private List<TExclusionesSolicitud> tExclusionesSolicitud;

  @Id
  @Column(name = "ID_SOLICITUD", nullable = false, precision = 0)
  @SequenceGenerator(name = "seqSolicitud", sequenceName = "AACI_SEQ_SOLIONGD", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqSolicitud")
  public Long getIdSolicitud() {
    return idSolicitud;
  }

  public void setIdSolicitud(final Long idSolicitud) {
    this.idSolicitud = idSolicitud;
  }

  @Basic
  @Column(name = "NU_NUMEXPEDIENTE", nullable = false, precision = 0)
  public Long getNuNumexpediente() {
    return nuNumexpediente;
  }

  public void setNuNumexpediente(final Long nuNumexpediente) {
    this.nuNumexpediente = nuNumexpediente;
  }

  /**
   * Obtiene la propiedad nuNumInterno
   *
   * @return el nuNumInterno
   */
  @Basic
  @Column(name = "NU_NUMINTERNO", nullable = false, precision = 0)
  public Long getNuNumInterno() {
    return nuNumInterno;
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

  @Basic
  @Column(name = "TX_PERSONANOTIF", nullable = true, length = 20)
  public String getTxPersonanotif() {
    return txPersonanotif;
  }

  public void setTxPersonanotif(final String txPersonanotif) {
    this.txPersonanotif = txPersonanotif;
  }

  @Basic
  @Column(name = "TX_REPETAGRUP1RACDA", nullable = true, length = 50)
  public String getTxRepetagrup1Racda() {
    return txRepetagrup1Racda;
  }

  public void setTxRepetagrup1Racda(final String txRepetagrup1Racda) {
    this.txRepetagrup1Racda = txRepetagrup1Racda;
  }

  @Basic
  @Column(name = "TX_SOLI1MEDNOTIFORD", nullable = true, length = 250)
  public String getTxSoli1Mednotiford() {
    return txSoli1Mednotiford;
  }

  public void setTxSoli1Mednotiford(final String txSoli1Mednotiford) {
    this.txSoli1Mednotiford = txSoli1Mednotiford;
  }

  @Basic
  @Column(name = "TX_REPETAGRUP2SIGLAS", nullable = true, length = 250)
  public String getTxRepetagrup2Siglas() {
    return txRepetagrup2Siglas;
  }

  public void setTxRepetagrup2Siglas(final String txRepetagrup2Siglas) {
    this.txRepetagrup2Siglas = txRepetagrup2Siglas;
  }

  @Basic
  @Column(name = "TX_SOLI1APELLIDO1", nullable = true, length = 50)
  public String getTxSoli1Apellido1() {
    return txSoli1Apellido1;
  }

  public void setTxSoli1Apellido1(final String txSoli1Apellido1) {
    this.txSoli1Apellido1 = txSoli1Apellido1;
  }

  @Basic
  @Column(name = "TX_REPR1NOMBRE", nullable = true, length = 50)
  public String getTxRepr1Nombre() {
    return txRepr1Nombre;
  }

  public void setTxRepr1Nombre(final String txRepr1Nombre) {
    this.txRepr1Nombre = txRepr1Nombre;
  }

  @Basic
  @Column(name = "TX_SOLI1APELLIDO2", nullable = true, length = 50)
  public String getTxSoli1Apellido2() {
    return txSoli1Apellido2;
  }

  public void setTxSoli1Apellido2(final String txSoli1Apellido2) {
    this.txSoli1Apellido2 = txSoli1Apellido2;
  }

  @Basic
  @Column(name = "TX_AGRUPREPNOMBRE", nullable = true, length = 150)
  public String getTxAgruprepnombre() {
    return txAgruprepnombre;
  }

  public void setTxAgruprepnombre(final String txAgruprepnombre) {
    this.txAgruprepnombre = txAgruprepnombre;
  }

  @Basic
  @Column(name = "FH_AUTORIZAGEST1OTRASFEC", nullable = true)
  public Date getFhAutorizagest1Otrasfec() {
    return fhAutorizagest1Otrasfec;
  }

  public void setFhAutorizagest1Otrasfec(final Date fhAutorizagest1Otrasfec) {
    this.fhAutorizagest1Otrasfec = fhAutorizagest1Otrasfec;
  }

  @Basic
  @Column(name = "TX_AUTORIZAGEST1OTRASDOC", nullable = true, length = 80)
  public String getTxAutorizagest1Otrasdoc() {
    return txAutorizagest1Otrasdoc;
  }

  public void setTxAutorizagest1Otrasdoc(final String txAutorizagest1Otrasdoc) {
    this.txAutorizagest1Otrasdoc = txAutorizagest1Otrasdoc;
  }

  @Basic
  @Column(name = "TX_SOLI1PISO", nullable = true, length = 20)
  public String getTxSoli1Piso() {
    return txSoli1Piso;
  }

  public void setTxSoli1Piso(final String txSoli1Piso) {
    this.txSoli1Piso = txSoli1Piso;
  }

  @Basic
  @Column(name = "TX_FIRM1ANO", nullable = true, length = 10)
  public String getTxFirm1Ano() {
    return txFirm1Ano;
  }

  public void setTxFirm1Ano(final String txFirm1Ano) {
    this.txFirm1Ano = txFirm1Ano;
  }

  @Basic
  @Column(name = "TX_REPDATOS1EJECUCION", nullable = true, length = 20)
  public String getTxRepdatos1Ejecucion() {
    return txRepdatos1Ejecucion;
  }

  public void setTxRepdatos1Ejecucion(final String txRepdatos1Ejecucion) {
    this.txRepdatos1Ejecucion = txRepdatos1Ejecucion;
  }

  @Basic
  @Column(name = "TX_NOTIFTELEFONOAUTLUGAR", nullable = true, length = 20)
  public String getTxNotiftelefonoautlugar() {
    return txNotiftelefonoautlugar;
  }

  public void setTxNotiftelefonoautlugar(final String txNotiftelefonoautlugar) {
    this.txNotiftelefonoautlugar = txNotiftelefonoautlugar;
  }

  @Basic
  @Column(name = "TX_AUTORIZAGEST1OTRASPRO", nullable = true, length = 80)
  public String getTxAutorizagest1Otraspro() {
    return txAutorizagest1Otraspro;
  }

  public void setTxAutorizagest1Otraspro(final String txAutorizagest1Otraspro) {
    this.txAutorizagest1Otraspro = txAutorizagest1Otraspro;
  }

  @Basic
  @Column(name = "NU_SOLICITADASOLIMPORTE", nullable = true, precision = 2)
  public Long getNuSolicitadasolimporte() {
    return nuSolicitadasolimporte;
  }

  public void setNuSolicitadasolimporte(final Long nuSolicitadasolimporte) {
    this.nuSolicitadasolimporte = nuSolicitadasolimporte;
  }

  @Basic
  @Column(name = "LG_CHECKDOC1", nullable = true, precision = 0)
  public Boolean getLgCheckdoc1() {
    return lgCheckdoc1;
  }

  public void setLgCheckdoc1(final Boolean lgCheckdoc1) {
    this.lgCheckdoc1 = lgCheckdoc1;
  }

  @Basic
  @Column(name = "TX_REPR1SEXO", nullable = true, length = 10)
  public String getTxRepr1Sexo() {
    return txRepr1Sexo;
  }

  public void setTxRepr1Sexo(final String txRepr1Sexo) {
    this.txRepr1Sexo = txRepr1Sexo;
  }

  @Basic
  @Column(name = "TX_SOLI1SIGLAS", nullable = true, length = 20)
  public String getTxSoli1Siglas() {
    return txSoli1Siglas;
  }

  public void setTxSoli1Siglas(final String txSoli1Siglas) {
    this.txSoli1Siglas = txSoli1Siglas;
  }

  @Basic
  @Column(name = "LG_CHECKDOC2", nullable = true, precision = 0)
  public Boolean getLgCheckdoc2() {
    return lgCheckdoc2;
  }

  public void setLgCheckdoc2(final Boolean lgCheckdoc2) {
    this.lgCheckdoc2 = lgCheckdoc2;
  }

  @Basic
  @Column(name = "TX_CONCEDIDASCONADMIN", nullable = true, length = 80)
  public String getTxConcedidasconadmin() {
    return txConcedidasconadmin;
  }

  public void setTxConcedidasconadmin(final String txConcedidasconadmin) {
    this.txConcedidasconadmin = txConcedidasconadmin;
  }

  @Basic
  @Column(name = "LG_CHECKDOC3", nullable = true, precision = 0)
  public Boolean getLgCheckdoc3() {
    return lgCheckdoc3;
  }

  public void setLgCheckdoc3(final Boolean lgCheckdoc3) {
    this.lgCheckdoc3 = lgCheckdoc3;
  }

  @Basic
  @Column(name = "NU_FIRM1IMPORTE", nullable = true, precision = 2)
  public Long getNuFirm1Importe() {
    return nuFirm1Importe;
  }

  public void setNuFirm1Importe(final Long nuFirm1Importe) {
    this.nuFirm1Importe = nuFirm1Importe;
  }

  @Basic
  @Column(name = "LG_CHECKDOC4", nullable = true, precision = 0)
  public Boolean getLgCheckdoc4() {
    return lgCheckdoc4;
  }

  public void setLgCheckdoc4(final Boolean lgCheckdoc4) {
    this.lgCheckdoc4 = lgCheckdoc4;
  }

  @Basic
  @Column(name = "LG_CHECKDOC5", nullable = true, precision = 0)
  public Boolean getLgCheckdoc5() {
    return lgCheckdoc5;
  }

  public void setLgCheckdoc5(final Boolean lgCheckdoc5) {
    this.lgCheckdoc5 = lgCheckdoc5;
  }

  @Basic
  @Column(name = "LG_CHECKDOC6", nullable = true, precision = 0)
  public Boolean getLgCheckdoc6() {
    return lgCheckdoc6;
  }

  public void setLgCheckdoc6(final Boolean lgCheckdoc6) {
    this.lgCheckdoc6 = lgCheckdoc6;
  }

  @Basic
  @Column(name = "LG_CHECKDOC7", nullable = true, precision = 0)
  public Boolean getLgCheckdoc7() {
    return lgCheckdoc7;
  }

  public void setLgCheckdoc7(final Boolean lgCheckdoc7) {
    this.lgCheckdoc7 = lgCheckdoc7;
  }

  @Basic
  @Column(name = "LG_CHECKDOC8", nullable = true, precision = 0)
  public Boolean getLgCheckdoc8() {
    return lgCheckdoc8;
  }

  public void setLgCheckdoc8(final Boolean lgCheckdoc8) {
    this.lgCheckdoc8 = lgCheckdoc8;
  }

  @Basic
  @Column(name = "TX_NOTIFNUMIDENTLUGAR", nullable = true, length = 250)
  public String getTxNotifnumidentlugar() {
    return txNotifnumidentlugar;
  }

  public void setTxNotifnumidentlugar(final String txNotifnumidentlugar) {
    this.txNotifnumidentlugar = txNotifnumidentlugar;
  }

  @Basic
  @Column(name = "LG_CHECKDOC9", nullable = true, precision = 0)
  public Boolean getLgCheckdoc9() {
    return lgCheckdoc9;
  }

  public void setLgCheckdoc9(final Boolean lgCheckdoc9) {
    this.lgCheckdoc9 = lgCheckdoc9;
  }

  @Basic
  @Column(name = "TX_REPETAGRUPRACDA", nullable = true, length = 50)
  public String getTxRepetagrupracda() {
    return txRepetagrupracda;
  }

  public void setTxRepetagrupracda(final String txRepetagrupracda) {
    this.txRepetagrupracda = txRepetagrupracda;
  }

  @Basic
  @Column(name = "LG_SOLI1ALTANOTIFICA", nullable = true, precision = 0)
  public Boolean getLgSoli1Altanotifica() {
    return lgSoli1Altanotifica;
  }

  public void setLgSoli1Altanotifica(final Boolean lgSoli1Altanotifica) {
    this.lgSoli1Altanotifica = lgSoli1Altanotifica;
  }

  @Basic
  @Column(name = "TX_REPETAGRUP2RACDA", nullable = true, length = 50)
  public String getTxRepetagrup2Racda() {
    return txRepetagrup2Racda;
  }

  public void setTxRepetagrup2Racda(final String txRepetagrup2Racda) {
    this.txRepetagrup2Racda = txRepetagrup2Racda;
  }

  @Basic
  @Column(name = "TX_SOLI1NUMIDENT", nullable = true, length = 50)
  public String getTxSoli1Numident() {
    return txSoli1Numident;
  }

  public void setTxSoli1Numident(final String txSoli1Numident) {
    this.txSoli1Numident = txSoli1Numident;
  }

  @Basic
  @Column(name = "TX_FIRM1FDO", nullable = true, length = 4000)
  public String getTxFirm1Fdo() {
    return txFirm1Fdo;
  }

  public void setTxFirm1Fdo(final String txFirm1Fdo) {
    this.txFirm1Fdo = txFirm1Fdo;
  }

  @Basic
  @Column(name = "FH_AUTOJUNTA1ADMFECHA", nullable = true)
  public Date getFhAutojunta1Admfecha() {
    return fhAutojunta1Admfecha;
  }

  public void setFhAutojunta1Admfecha(final Date fhAutojunta1Admfecha) {
    this.fhAutojunta1Admfecha = fhAutojunta1Admfecha;
  }

  @Basic
  @Column(name = "TX_SOLICITADAS0ADMIN", nullable = true, length = 80)
  public String getTxSolicitadas0Admin() {
    return txSolicitadas0Admin;
  }

  public void setTxSolicitadas0Admin(final String txSolicitadas0Admin) {
    this.txSolicitadas0Admin = txSolicitadas0Admin;
  }

  @Basic
  @Column(name = "TX_AUTORIZAGEST0OTRASDOC", nullable = true, length = 80)
  public String getTxAutorizagest0Otrasdoc() {
    return txAutorizagest0Otrasdoc;
  }

  public void setTxAutorizagest0Otrasdoc(final String txAutorizagest0Otrasdoc) {
    this.txAutorizagest0Otrasdoc = txAutorizagest0Otrasdoc;
  }

  @Basic
  @Column(name = "TX_AUTORIZAJUNTA0ADMDOC", nullable = true, length = 80)
  public String getTxAutorizajunta0Admdoc() {
    return txAutorizajunta0Admdoc;
  }

  public void setTxAutorizajunta0Admdoc(final String txAutorizajunta0Admdoc) {
    this.txAutorizajunta0Admdoc = txAutorizajunta0Admdoc;
  }

  @Basic
  @Column(name = "TX_AGRUPREPAPELLIDO2", nullable = true, length = 250)
  public String getTxAgruprepapellido2() {
    return txAgruprepapellido2;
  }

  public void setTxAgruprepapellido2(final String txAgruprepapellido2) {
    this.txAgruprepapellido2 = txAgruprepapellido2;
  }

  @Basic
  @Column(name = "LG_ALTANOFIFICA", nullable = true, precision = 0)
  public Boolean getLgAltanofifica() {
    return lgAltanofifica;
  }

  public void setLgAltanofifica(final Boolean lgAltanofifica) {
    this.lgAltanofifica = lgAltanofifica;
  }

  @Basic
  @Column(name = "TX_AGRUPREPAPELLIDO1", nullable = true, length = 50)
  public String getTxAgruprepapellido1() {
    return txAgruprepapellido1;
  }

  public void setTxAgruprepapellido1(final String txAgruprepapellido1) {
    this.txAgruprepapellido1 = txAgruprepapellido1;
  }

  @Basic
  @Column(name = "TX_REPETAGRUPNOMBRE", nullable = true, length = 150)
  public String getTxRepetagrupnombre() {
    return txRepetagrupnombre;
  }

  public void setTxRepetagrupnombre(final String txRepetagrupnombre) {
    this.txRepetagrupnombre = txRepetagrupnombre;
  }

  @Basic
  @Column(name = "TX_SOLICITADAS1MINIMIS", nullable = true, length = 50)
  public String getTxSolicitadas1Minimis() {
    return txSolicitadas1Minimis;
  }

  public void setTxSolicitadas1Minimis(final String txSolicitadas1Minimis) {
    this.txSolicitadas1Minimis = txSolicitadas1Minimis;
  }

  @Basic
  @Column(name = "TX_AGRUPREPIDENTIFIC", nullable = true, length = 10)
  public String getTxAgruprepidentific() {
    return txAgruprepidentific;
  }

  public void setTxAgruprepidentific(final String txAgruprepidentific) {
    this.txAgruprepidentific = txAgruprepidentific;
  }

  @Basic
  @Column(name = "TX_FIRM1DIA", nullable = true, length = 10)
  public String getTxFirm1Dia() {
    return txFirm1Dia;
  }

  public void setTxFirm1Dia(final String txFirm1Dia) {
    this.txFirm1Dia = txFirm1Dia;
  }

  @Basic
  @Column(name = "TX_SOLICITADAS0FECHA", nullable = true, length = 20)
  public String getTxSolicitadas0Fecha() {
    return txSolicitadas0Fecha;
  }

  public void setTxSolicitadas0Fecha(final String txSolicitadas0Fecha) {
    this.txSolicitadas0Fecha = txSolicitadas0Fecha;
  }

  @Basic
  @Column(name = "TX_CODIDENTIFICATIVO", nullable = true, length = 50)
  public String getTxCodidentificativo() {
    return txCodidentificativo;
  }

  public void setTxCodidentificativo(final String txCodidentificativo) {
    this.txCodidentificativo = txCodidentificativo;
  }

  @Basic
  @Column(name = "TX_REPR1TIPOVIA", nullable = true, length = 10)
  public String getTxRepr1Tipovia() {
    return txRepr1Tipovia;
  }

  public void setTxRepr1Tipovia(final String txRepr1Tipovia) {
    this.txRepr1Tipovia = txRepr1Tipovia;
  }

  @Basic
  @Column(name = "TX_REPETAGRUP2NOMBRE", nullable = true, length = 4000)
  public String getTxRepetagrup2Nombre() {
    return txRepetagrup2Nombre;
  }

  public void setTxRepetagrup2Nombre(final String txRepetagrup2Nombre) {
    this.txRepetagrup2Nombre = txRepetagrup2Nombre;
  }

  @Basic
  @Column(name = "TX_SOLI1EMAIL", nullable = true, length = 250)
  public String getTxSoli1Email() {
    return txSoli1Email;
  }

  public void setTxSoli1Email(final String txSoli1Email) {
    this.txSoli1Email = txSoli1Email;
  }

  @Basic
  @Column(name = "TX_SOLI1NUMERO", nullable = true, length = 20)
  public String getTxSoli1Numero() {
    return txSoli1Numero;
  }

  public void setTxSoli1Numero(final String txSoli1Numero) {
    this.txSoli1Numero = txSoli1Numero;
  }

  @Basic
  @Column(name = "TX_SOLI1CODMUNICIPIO", nullable = true, length = 20)
  public String getTxSoli1Codmunicipio() {
    return txSoli1Codmunicipio;
  }

  public void setTxSoli1Codmunicipio(final String txSoli1Codmunicipio) {
    this.txSoli1Codmunicipio = txSoli1Codmunicipio;
  }

  @Basic
  @Column(name = "TX_DATOSTITULO", nullable = true, length = 100)
  public String getTxDatostitulo() {
    return txDatostitulo;
  }

  public void setTxDatostitulo(final String txDatostitulo) {
    this.txDatostitulo = txDatostitulo;
  }

  @Basic
  @Column(name = "TX_CONCEDIDAS0MINIMIS", nullable = true, length = 10)
  public String getTxConcedidas0Minimis() {
    return txConcedidas0Minimis;
  }

  public void setTxConcedidas0Minimis(final String txConcedidas0Minimis) {
    this.txConcedidas0Minimis = txConcedidas0Minimis;
  }

  @Basic
  @Column(name = "TX_REPETAGRUP1SIGLAS", nullable = true, length = 20)
  public String getTxRepetagrup1Siglas() {
    return txRepetagrup1Siglas;
  }

  public void setTxRepetagrup1Siglas(final String txRepetagrup1Siglas) {
    this.txRepetagrup1Siglas = txRepetagrup1Siglas;
  }

  @Basic
  @Column(name = "TX_REPR1NOMBREVIA", nullable = true, length = 100)
  public String getTxRepr1Nombrevia() {
    return txRepr1Nombrevia;
  }

  public void setTxRepr1Nombrevia(final String txRepr1Nombrevia) {
    this.txRepr1Nombrevia = txRepr1Nombrevia;
  }

  @Basic
  @Column(name = "TX_AUTORIZAJUNTA1ADMCONS", nullable = true, length = 80)
  public String getTxAutorizajunta1Admcons() {
    return txAutorizajunta1Admcons;
  }

  public void setTxAutorizajunta1Admcons(final String txAutorizajunta1Admcons) {
    this.txAutorizajunta1Admcons = txAutorizajunta1Admcons;
  }

  @Basic
  @Column(name = "TX_ERRORFINALIDADPROY", nullable = true, length = 250)
  public String getTxErrorfinalidadproy() {
    return txErrorfinalidadproy;
  }

  public void setTxErrorfinalidadproy(final String txErrorfinalidadproy) {
    this.txErrorfinalidadproy = txErrorfinalidadproy;
  }

  @Basic
  @Column(name = "TX_NOTIFEMAILAUTLUGAR", nullable = true, length = 250)
  public String getTxNotifemailautlugar() {
    return txNotifemailautlugar;
  }

  public void setTxNotifemailautlugar(final String txNotifemailautlugar) {
    this.txNotifemailautlugar = txNotifemailautlugar;
  }

  @Basic
  @Column(name = "TX_REPR1TIPOIDENT", nullable = true, length = 50)
  public String getTxRepr1Tipoident() {
    return txRepr1Tipoident;
  }

  public void setTxRepr1Tipoident(final String txRepr1Tipoident) {
    this.txRepr1Tipoident = txRepr1Tipoident;
  }

  @Basic
  @Column(name = "TX_SOLICITADAS0MINIMIS", nullable = true, length = 10)
  public String getTxSolicitadas0Minimis() {
    return txSolicitadas0Minimis;
  }

  public void setTxSolicitadas0Minimis(final String txSolicitadas0Minimis) {
    this.txSolicitadas0Minimis = txSolicitadas0Minimis;
  }

  @Basic
  @Column(name = "NU_SOLI1TLFMOVIL", nullable = true, precision = 0)
  public Long getNuSoli1Tlfmovil() {
    return nuSoli1Tlfmovil;
  }

  public void setNuSoli1Tlfmovil(final Long nuSoli1Tlfmovil) {
    this.nuSoli1Tlfmovil = nuSoli1Tlfmovil;
  }

  @Basic
  @Column(name = "TX_REPR1TITULO", nullable = true, length = 50)
  public String getTxRepr1Titulo() {
    return txRepr1Titulo;
  }

  public void setTxRepr1Titulo(final String txRepr1Titulo) {
    this.txRepr1Titulo = txRepr1Titulo;
  }

  @Basic
  @Column(name = "NU_SOLI1CP", nullable = true, precision = 5)
  public Long getNuSoli1Cp() {
    return nuSoli1Cp;
  }

  public void setNuSoli1Cp(final Long nuSoli1Cp) {
    this.nuSoli1Cp = nuSoli1Cp;
  }

  @Basic
  @Column(name = "TX_REPETAGRUP1IDENT", nullable = true, length = 50)
  public String getTxRepetagrup1Ident() {
    return txRepetagrup1Ident;
  }

  public void setTxRepetagrup1Ident(final String txRepetagrup1Ident) {
    this.txRepetagrup1Ident = txRepetagrup1Ident;
  }

  @Basic
  @Column(name = "FH_CONCEDIDAS1FECHA", nullable = true)
  public Date getFhConcedidas1Fecha() {
    return fhConcedidas1Fecha;
  }

  public void setFhConcedidas1Fecha(final Date fhConcedidas1Fecha) {
    this.fhConcedidas1Fecha = fhConcedidas1Fecha;
  }

  @Basic
  @Column(name = "NU_REPR1CODPROV", nullable = true, precision = 0)
  public Long getNuRepr1Codprov() {
    return nuRepr1Codprov;
  }

  public void setNuRepr1Codprov(final Long nuRepr1Codprov) {
    this.nuRepr1Codprov = nuRepr1Codprov;
  }

  @Basic
  @Column(name = "TX_SOLI1POBLACION", nullable = true, length = 50)
  public String getTxSoli1Poblacion() {
    return txSoli1Poblacion;
  }

  public void setTxSoli1Poblacion(final String txSoli1Poblacion) {
    this.txSoli1Poblacion = txSoli1Poblacion;
  }

  @Basic
  @Column(name = "LB_TEXTOOTRAAS", nullable = true)
  public String getLbTextootraas() {
    return lbTextootraas;
  }

  public void setLbTextootraas(final String lbTextootraas) {
    this.lbTextootraas = lbTextootraas;
  }

  @Basic
  @Column(name = "TX_SOLI1NOMBREVIA", nullable = true, length = 100)
  public String getTxSoli1Nombrevia() {
    return txSoli1Nombrevia;
  }

  public void setTxSoli1Nombrevia(final String txSoli1Nombrevia) {
    this.txSoli1Nombrevia = txSoli1Nombrevia;
  }

  @Basic
  @Column(name = "TX_REPDATOS0EJECUCION", nullable = true, length = 20)
  public String getTxRepdatos0Ejecucion() {
    return txRepdatos0Ejecucion;
  }

  public void setTxRepdatos0Ejecucion(final String txRepdatos0Ejecucion) {
    this.txRepdatos0Ejecucion = txRepdatos0Ejecucion;
  }

  @Basic
  @Column(name = "TX_SOLI1ESCALERA", nullable = true, length = 50)
  public String getTxSoli1Escalera() {
    return txSoli1Escalera;
  }

  public void setTxSoli1Escalera(final String txSoli1Escalera) {
    this.txSoli1Escalera = txSoli1Escalera;
  }

  @Basic
  @Column(name = "TX_REPET0AGRUPIDENTIF", nullable = true, length = 50)
  public String getTxRepet0Agrupidentif() {
    return txRepet0Agrupidentif;
  }

  public void setTxRepet0Agrupidentif(final String txRepet0Agrupidentif) {
    this.txRepet0Agrupidentif = txRepet0Agrupidentif;
  }

  @Basic
  @Column(name = "TX_SOLI1SEXO", nullable = true, length = 10)
  public String getTxSoli1Sexo() {
    return txSoli1Sexo;
  }

  public void setTxSoli1Sexo(final String txSoli1Sexo) {
    this.txSoli1Sexo = txSoli1Sexo;
  }

  @Basic
  @Column(name = "LG_CHECKDOC20", nullable = true, precision = 0)
  public Boolean getLgCheckdoc20() {
    return lgCheckdoc20;
  }

  public void setLgCheckdoc20(final Boolean lgCheckdoc20) {
    this.lgCheckdoc20 = lgCheckdoc20;
  }

  @Basic
  @Column(name = "TX_SOLI1PORTAL", nullable = true, length = 50)
  public String getTxSoli1Portal() {
    return txSoli1Portal;
  }

  public void setTxSoli1Portal(final String txSoli1Portal) {
    this.txSoli1Portal = txSoli1Portal;
  }

  @Basic
  @Column(name = "LG_CONSENTEXPRESOREPR", nullable = true, precision = 0)
  public Boolean getLgConsentexpresorepr() {
    return lgConsentexpresorepr;
  }

  public void setLgConsentexpresorepr(final Boolean lgConsentexpresorepr) {
    this.lgConsentexpresorepr = lgConsentexpresorepr;
  }

  @Basic
  @Column(name = "TX_SOLI1CODIGO", nullable = true, length = 50)
  public String getTxSoli1Codigo() {
    return txSoli1Codigo;
  }

  public void setTxSoli1Codigo(final String txSoli1Codigo) {
    this.txSoli1Codigo = txSoli1Codigo;
  }

  @Basic
  @Column(name = "FH_AUTORIZAGEST0OTRASFEC", nullable = true)
  public Date getFhAutorizagest0Otrasfec() {
    return fhAutorizagest0Otrasfec;
  }

  public void setFhAutorizagest0Otrasfec(final Date fhAutorizagest0Otrasfec) {
    this.fhAutorizagest0Otrasfec = fhAutorizagest0Otrasfec;
  }

  @Basic
  @Column(name = "TX_AUTORIZAJUNTA1ADMDOC", nullable = true, length = 80)
  public String getTxAutorizajunta1Admdoc() {
    return txAutorizajunta1Admdoc;
  }

  public void setTxAutorizajunta1Admdoc(final String txAutorizajunta1Admdoc) {
    this.txAutorizajunta1Admdoc = txAutorizajunta1Admdoc;
  }

  @Basic
  @Column(name = "TX_CONCEDIDAS1CONADMIN", nullable = true, length = 80)
  public String getTxConcedidas1Conadmin() {
    return txConcedidas1Conadmin;
  }

  public void setTxConcedidas1Conadmin(final String txConcedidas1Conadmin) {
    this.txConcedidas1Conadmin = txConcedidas1Conadmin;
  }

  @Basic
  @Column(name = "TX_SOLI1KMVIA", nullable = true, length = 20)
  public String getTxSoli1Kmvia() {
    return txSoli1Kmvia;
  }

  public void setTxSoli1Kmvia(final String txSoli1Kmvia) {
    this.txSoli1Kmvia = txSoli1Kmvia;
  }

  @Basic
  @Column(name = "LG_CHECKDOC18", nullable = true, precision = 0)
  public Boolean getLgCheckdoc18() {
    return lgCheckdoc18;
  }

  public void setLgCheckdoc18(final Boolean lgCheckdoc18) {
    this.lgCheckdoc18 = lgCheckdoc18;
  }

  @Basic
  @Column(name = "LG_CBDECLARO3", nullable = true, precision = 0)
  public Boolean getLgCbdeclaro3() {
    return lgCbdeclaro3;
  }

  public void setLgCbdeclaro3(final Boolean lgCbdeclaro3) {
    this.lgCbdeclaro3 = lgCbdeclaro3;
  }

  @Basic
  @Column(name = "LG_CBDECLARO2", nullable = true, precision = 0)
  public Boolean getLgCbdeclaro2() {
    return lgCbdeclaro2;
  }

  public void setLgCbdeclaro2(final Boolean lgCbdeclaro2) {
    this.lgCbdeclaro2 = lgCbdeclaro2;
  }

  @Basic
  @Column(name = "LG_CHECKDOC19", nullable = true, precision = 0)
  public Boolean getLgCheckdoc19() {
    return lgCheckdoc19;
  }

  public void setLgCheckdoc19(final Boolean lgCheckdoc19) {
    this.lgCheckdoc19 = lgCheckdoc19;
  }

  @Basic
  @Column(name = "LG_CBDECLARO1", nullable = true, precision = 0)
  public Boolean getLgCbdeclaro1() {
    return lgCbdeclaro1;
  }

  public void setLgCbdeclaro1(final Boolean lgCbdeclaro1) {
    this.lgCbdeclaro1 = lgCbdeclaro1;
  }

  @Basic
  @Column(name = "LG_CHECKDOC10", nullable = true, precision = 0)
  public Boolean getLgCheckdoc10() {
    return lgCheckdoc10;
  }

  public void setLgCheckdoc10(final Boolean lgCheckdoc10) {
    this.lgCheckdoc10 = lgCheckdoc10;
  }

  @Basic
  @Column(name = "TX_TIPODESTINATARIO", nullable = true, length = 10)
  public String getTxTipodestinatario() {
    return txTipodestinatario;
  }

  public void setTxTipodestinatario(final String txTipodestinatario) {
    this.txTipodestinatario = txTipodestinatario;
  }

  @Basic
  @Column(name = "LG_CHECKDOC11", nullable = true, precision = 0)
  public Boolean getLgCheckdoc11() {
    return lgCheckdoc11;
  }

  public void setLgCheckdoc11(final Boolean lgCheckdoc11) {
    this.lgCheckdoc11 = lgCheckdoc11;
  }

  @Basic
  @Column(name = "LG_CHECKDOC12", nullable = true, precision = 0)
  public Boolean getLgCheckdoc12() {
    return lgCheckdoc12;
  }

  public void setLgCheckdoc12(final Boolean lgCheckdoc12) {
    this.lgCheckdoc12 = lgCheckdoc12;
  }

  @Basic
  @Column(name = "LG_CHECKDOC13", nullable = true, precision = 0)
  public Boolean getLgCheckdoc13() {
    return lgCheckdoc13;
  }

  public void setLgCheckdoc13(final Boolean lgCheckdoc13) {
    this.lgCheckdoc13 = lgCheckdoc13;
  }

  @Basic
  @Column(name = "LG_CHECKDOC14", nullable = true, precision = 0)
  public Boolean getLgCheckdoc14() {
    return lgCheckdoc14;
  }

  public void setLgCheckdoc14(final Boolean lgCheckdoc14) {
    this.lgCheckdoc14 = lgCheckdoc14;
  }

  @Basic
  @Column(name = "LG_CHECKDOC15", nullable = true, precision = 0)
  public Boolean getLgCheckdoc15() {
    return lgCheckdoc15;
  }

  public void setLgCheckdoc15(final Boolean lgCheckdoc15) {
    this.lgCheckdoc15 = lgCheckdoc15;
  }

  @Basic
  @Column(name = "LG_CHECKOTRA", nullable = true, precision = 0)
  public Boolean getLgCheckotra() {
    return lgCheckotra;
  }

  public void setLgCheckotra(final Boolean lgCheckotra) {
    this.lgCheckotra = lgCheckotra;
  }

  @Basic
  @Column(name = "LG_CHECKDOC16", nullable = true, precision = 0)
  public Boolean getLgCheckdoc16() {
    return lgCheckdoc16;
  }

  public void setLgCheckdoc16(final Boolean lgCheckdoc16) {
    this.lgCheckdoc16 = lgCheckdoc16;
  }

  @Basic
  @Column(name = "TX_AUTORIZAJUNTA0ADMPROC", nullable = true, length = 80)
  public String getTxAutorizajunta0Admproc() {
    return txAutorizajunta0Admproc;
  }

  public void setTxAutorizajunta0Admproc(final String txAutorizajunta0Admproc) {
    this.txAutorizajunta0Admproc = txAutorizajunta0Admproc;
  }

  @Basic
  @Column(name = "LG_CHECKDOC17", nullable = true, precision = 0)
  public Boolean getLgCheckdoc17() {
    return lgCheckdoc17;
  }

  public void setLgCheckdoc17(final Boolean lgCheckdoc17) {
    this.lgCheckdoc17 = lgCheckdoc17;
  }

  @Basic
  @Column(name = "FH_AUTOJUNTA0ADMFECHA", nullable = true)
  public Date getFhAutojunta0Admfecha() {
    return fhAutojunta0Admfecha;
  }

  public void setFhAutojunta0Admfecha(final Date fhAutojunta0Admfecha) {
    this.fhAutojunta0Admfecha = fhAutojunta0Admfecha;
  }

  @Basic
  @Column(name = "TX_AUTORIZAGEST0OTRASPRO", nullable = true, length = 80)
  public String getTxAutorizagest0Otraspro() {
    return txAutorizagest0Otraspro;
  }

  public void setTxAutorizagest0Otraspro(final String txAutorizagest0Otraspro) {
    this.txAutorizagest0Otraspro = txAutorizagest0Otraspro;
  }

  @Basic
  @Column(name = "FH_CONCEDIDAS0FECHA", nullable = true)
  public Date getFhConcedidas0Fecha() {
    return fhConcedidas0Fecha;
  }

  public void setFhConcedidas0Fecha(final Date fhConcedidas0Fecha) {
    this.fhConcedidas0Fecha = fhConcedidas0Fecha;
  }

  @Basic
  @Column(name = "TX_CALIDADDE", nullable = true, length = 50)
  public String getTxCalidadde() {
    return txCalidadde;
  }

  public void setTxCalidadde(final String txCalidadde) {
    this.txCalidadde = txCalidadde;
  }

  @Basic
  @Column(name = "NU_CONCEDIDAS0CONIMPORTE", nullable = true, precision = 2)
  public Long getNuConcedidas0Conimporte() {
    return nuConcedidas0Conimporte;
  }

  public void setNuConcedidas0Conimporte(final Long nuConcedidas0Conimporte) {
    this.nuConcedidas0Conimporte = nuConcedidas0Conimporte;
  }

  @Basic
  @Column(name = "TX_CONCEDIDAS1MINIMIS", nullable = true, length = 50)
  public String getTxConcedidas1Minimis() {
    return txConcedidas1Minimis;
  }

  public void setTxConcedidas1Minimis(final String txConcedidas1Minimis) {
    this.txConcedidas1Minimis = txConcedidas1Minimis;
  }

  @Basic
  @Column(name = "TX_REPETAGRUP0SIGLAS", nullable = true, length = 20)
  public String getTxRepetagrup0Siglas() {
    return txRepetagrup0Siglas;
  }

  public void setTxRepetagrup0Siglas(final String txRepetagrup0Siglas) {
    this.txRepetagrup0Siglas = txRepetagrup0Siglas;
  }

  @Basic
  @Column(name = "TX_SOLI1NOMBRE", nullable = true, length = 150)
  public String getTxSoli1Nombre() {
    return txSoli1Nombre;
  }

  public void setTxSoli1Nombre(final String txSoli1Nombre) {
    this.txSoli1Nombre = txSoli1Nombre;
  }

  @Basic
  @Column(name = "TX_REPR1NUMIDENT", nullable = true, length = 20)
  public String getTxRepr1Numident() {
    return txRepr1Numident;
  }

  public void setTxRepr1Numident(final String txRepr1Numident) {
    this.txRepr1Numident = txRepr1Numident;
  }

  @Basic
  @Column(name = "TX_SOLI1TIPOVIA", nullable = true, length = 20)
  public String getTxSoli1Tipovia() {
    return txSoli1Tipovia;
  }

  public void setTxSoli1Tipovia(final String txSoli1Tipovia) {
    this.txSoli1Tipovia = txSoli1Tipovia;
  }

  @Basic
  @Column(name = "LG_CHECKTIPO2", nullable = true, precision = 0)
  public Boolean getLgChecktipo2() {
    return lgChecktipo2;
  }

  public void setLgChecktipo2(final Boolean lgChecktipo2) {
    this.lgChecktipo2 = lgChecktipo2;
  }

  @Basic
  @Column(name = "LG_CHECKFINALIDAD3", nullable = true, precision = 0)
  public Boolean getLgCheckfinalidad3() {
    return lgCheckfinalidad3;
  }

  public void setLgCheckfinalidad3(final Boolean lgCheckfinalidad3) {
    this.lgCheckfinalidad3 = lgCheckfinalidad3;
  }

  @Basic
  @Column(name = "LG_CHECKTIPO1", nullable = true, precision = 0)
  public Boolean getLgChecktipo1() {
    return lgChecktipo1;
  }

  public void setLgChecktipo1(final Boolean lgChecktipo1) {
    this.lgChecktipo1 = lgChecktipo1;
  }

  @Basic
  @Column(name = "LG_CHECKFINALIDAD4", nullable = true, precision = 0)
  public Boolean getLgCheckfinalidad4() {
    return lgCheckfinalidad4;
  }

  public void setLgCheckfinalidad4(final Boolean lgCheckfinalidad4) {
    this.lgCheckfinalidad4 = lgCheckfinalidad4;
  }

  @Basic
  @Column(name = "LG_CHECKFINALIDAD1", nullable = true, precision = 0)
  public Boolean getLgCheckfinalidad1() {
    return lgCheckfinalidad1;
  }

  public void setLgCheckfinalidad1(final Boolean lgCheckfinalidad1) {
    this.lgCheckfinalidad1 = lgCheckfinalidad1;
  }

  @Basic
  @Column(name = "LG_CHECKFINALIDAD2", nullable = true, precision = 0)
  public Boolean getLgCheckfinalidad2() {
    return lgCheckfinalidad2;
  }

  public void setLgCheckfinalidad2(final Boolean lgCheckfinalidad2) {
    this.lgCheckfinalidad2 = lgCheckfinalidad2;
  }

  @Basic
  @Column(name = "TX_SOLI1BLOQUE", nullable = true, length = 50)
  public String getTxSoli1Bloque() {
    return txSoli1Bloque;
  }

  public void setTxSoli1Bloque(final String txSoli1Bloque) {
    this.txSoli1Bloque = txSoli1Bloque;
  }

  @Basic
  @Column(name = "TX_REPET2AGRUPIDENTIF", nullable = true, length = 20)
  public String getTxRepet2Agrupidentif() {
    return txRepet2Agrupidentif;
  }

  public void setTxRepet2Agrupidentif(final String txRepet2Agrupidentif) {
    this.txRepet2Agrupidentif = txRepet2Agrupidentif;
  }

  @Basic
  @Column(name = "NU_REPR1CODMUNICIPIO", nullable = true, length = 10)
  public String getNuRepr1Codmunicipio() {
    return nuRepr1Codmunicipio;
  }

  public void setNuRepr1Codmunicipio(final String nuRepr1Codmunicipio) {
    this.nuRepr1Codmunicipio = nuRepr1Codmunicipio;
  }

  @Basic
  @Column(name = "TX_SOL1LETRA", nullable = true, length = 10)
  public String getTxSol1Letra() {
    return txSol1Letra;
  }

  public void setTxSol1Letra(final String txSol1Letra) {
    this.txSol1Letra = txSol1Letra;
  }

  @Basic
  @Column(name = "TX_REPDATOS2EJECUCION", nullable = true, length = 20)
  public String getTxRepdatos2Ejecucion() {
    return txRepdatos2Ejecucion;
  }

  public void setTxRepdatos2Ejecucion(final String txRepdatos2Ejecucion) {
    this.txRepdatos2Ejecucion = txRepdatos2Ejecucion;
  }

  @Basic
  @Column(name = "NU_CONCEDIDAS1CONIMPORTE", nullable = true, precision = 2)
  public Long getNuConcedidas1Conimporte() {
    return nuConcedidas1Conimporte;
  }

  public void setNuConcedidas1Conimporte(final Long nuConcedidas1Conimporte) {
    this.nuConcedidas1Conimporte = nuConcedidas1Conimporte;
  }

  @Basic
  @Column(name = "NU_SOLI1CODPROV", nullable = true, precision = 0)
  public Long getNuSoli1Codprov() {
    return nuSoli1Codprov;
  }

  public void setNuSoli1Codprov(final Long nuSoli1Codprov) {
    this.nuSoli1Codprov = nuSoli1Codprov;
  }

  @Basic
  @Column(name = "TX_REPETAGRUP1NOMBRE", nullable = true, length = 150)
  public String getTxRepetagrup1Nombre() {
    return txRepetagrup1Nombre;
  }

  public void setTxRepetagrup1Nombre(final String txRepetagrup1Nombre) {
    this.txRepetagrup1Nombre = txRepetagrup1Nombre;
  }

  @Basic
  @Column(name = "NU_SOLICITADAS1IMPORTE", nullable = true, precision = 2)
  public Long getNuSolicitadas1Importe() {
    return nuSolicitadas1Importe;
  }

  public void setNuSolicitadas1Importe(final Long nuSolicitadas1Importe) {
    this.nuSolicitadas1Importe = nuSolicitadas1Importe;
  }

  @Basic
  @Column(name = "FH_SOLICITADAS1FECHA", nullable = true)
  public Date getFhSolicitadas1Fecha() {
    return fhSolicitadas1Fecha;
  }

  public void setFhSolicitadas1Fecha(final Date fhSolicitadas1Fecha) {
    this.fhSolicitadas1Fecha = fhSolicitadas1Fecha;
  }

  /**
   * Obtiene la propiedad fhPagoSubvencion
   *
   * @return el fhPagoSubvencion
   */
  @Basic
  @Column(name = "FH_PAGO_SUBVENCION", nullable = true)
  public Date getFhPagoSubvencion() {
    return fhPagoSubvencion;
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

  @Basic
  @Column(name = "TX_SOLI1PUERTA", nullable = true, length = 50)
  public String getTxSoli1Puerta() {
    return txSoli1Puerta;
  }

  public void setTxSoli1Puerta(final String txSoli1Puerta) {
    this.txSoli1Puerta = txSoli1Puerta;
  }

  @Basic
  @Column(name = "TX_AUTORIZAGEST1OTRASADM", nullable = true, length = 80)
  public String getTxAutorizagest1Otrasadm() {
    return txAutorizagest1Otrasadm;
  }

  public void setTxAutorizagest1Otrasadm(final String txAutorizagest1Otrasadm) {
    this.txAutorizagest1Otrasadm = txAutorizagest1Otrasadm;
  }

  @Basic
  @Column(name = "TX_AUTORIZAJUNTA0ADMCONS", nullable = true, length = 80)
  public String getTxAutorizajunta0Admcons() {
    return txAutorizajunta0Admcons;
  }

  public void setTxAutorizajunta0Admcons(final String txAutorizajunta0Admcons) {
    this.txAutorizajunta0Admcons = txAutorizajunta0Admcons;
  }

  @Basic
  @Column(name = "TX_AUTORIZAJUNTA1ADMPROC", nullable = true, length = 80)
  public String getTxAutorizajunta1Admproc() {
    return txAutorizajunta1Admproc;
  }

  public void setTxAutorizajunta1Admproc(final String txAutorizajunta1Admproc) {
    this.txAutorizajunta1Admproc = txAutorizajunta1Admproc;
  }

  @Basic
  @Column(name = "LG_MEDIONOTIFTELEMATICA", nullable = true, precision = 0)
  public Boolean getLgMedionotiftelematica() {
    return lgMedionotiftelematica;
  }

  public void setLgMedionotiftelematica(final Boolean lgMedionotiftelematica) {
    this.lgMedionotiftelematica = lgMedionotiftelematica;
  }

  @Basic
  @Column(name = "TX_SOLICITADAS1ADMIN", nullable = true, length = 80)
  public String getTxSolicitadas1Admin() {
    return txSolicitadas1Admin;
  }

  public void setTxSolicitadas1Admin(final String txSolicitadas1Admin) {
    this.txSolicitadas1Admin = txSolicitadas1Admin;
  }

  @Basic
  @Column(name = "TX_FIRM1LUGAR", nullable = true, length = 50)
  public String getTxFirm1Lugar() {
    return txFirm1Lugar;
  }

  public void setTxFirm1Lugar(final String txFirm1Lugar) {
    this.txFirm1Lugar = txFirm1Lugar;
  }

  @Basic
  @Column(name = "LG_CBDECLARO4", nullable = true, precision = 0)
  public Boolean getLgCbdeclaro4() {
    return lgCbdeclaro4;
  }

  public void setLgCbdeclaro4(final Boolean lgCbdeclaro4) {
    this.lgCbdeclaro4 = lgCbdeclaro4;
  }

  @Basic
  @Column(name = "LG_CBDECLARO5", nullable = true, precision = 0)
  public Boolean getLgCbdeclaro5() {
    return lgCbdeclaro5;
  }

  public void setLgCbdeclaro5(final Boolean lgCbdeclaro5) {
    this.lgCbdeclaro5 = lgCbdeclaro5;
  }

  @Basic
  @Column(name = "LG_CBDECLARO6", nullable = true, precision = 0)
  public Boolean getLgCbdeclaro6() {
    return lgCbdeclaro6;
  }

  public void setLgCbdeclaro6(final Boolean lgCbdeclaro6) {
    this.lgCbdeclaro6 = lgCbdeclaro6;
  }

  @Basic
  @Column(name = "TX_REPR1APELLIDO1", nullable = true, length = 50)
  public String getTxRepr1Apellido1() {
    return txRepr1Apellido1;
  }

  public void setTxRepr1Apellido1(final String txRepr1Apellido1) {
    this.txRepr1Apellido1 = txRepr1Apellido1;
  }

  @Basic
  @Column(name = "TX_NOTIFNOMBRELUGAR", nullable = true, length = 250)
  public String getTxNotifnombrelugar() {
    return txNotifnombrelugar;
  }

  public void setTxNotifnombrelugar(final String txNotifnombrelugar) {
    this.txNotifnombrelugar = txNotifnombrelugar;
  }

  @Basic
  @Column(name = "TX_REPR1APELLIDO2", nullable = true, length = 50)
  public String getTxRepr1Apellido2() {
    return txRepr1Apellido2;
  }

  public void setTxRepr1Apellido2(final String txRepr1Apellido2) {
    this.txRepr1Apellido2 = txRepr1Apellido2;
  }

  @Basic
  @Column(name = "TX_AUTORIZAGEST0OTRASADM", nullable = true, length = 80)
  public String getTxAutorizagest0Otrasadm() {
    return txAutorizagest0Otrasadm;
  }

  public void setTxAutorizagest0Otrasadm(final String txAutorizagest0Otrasadm) {
    this.txAutorizagest0Otrasadm = txAutorizagest0Otrasadm;
  }

  @Basic
  @Column(name = "TX_FIRM1MES", nullable = true, length = 20)
  public String getTxFirm1Mes() {
    return txFirm1Mes;
  }

  public void setTxFirm1Mes(final String txFirm1Mes) {
    this.txFirm1Mes = txFirm1Mes;
  }

  @Basic
  @Column(name = "LG_SOLI1MEDIONOTIFTELEMA", nullable = true, precision = 0)
  public Boolean getLgSoli1Medionotiftelema() {
    return lgSoli1Medionotiftelema;
  }

  public void setLgSoli1Medionotiftelema(final Boolean lgSoli1Medionotiftelema) {
    this.lgSoli1Medionotiftelema = lgSoli1Medionotiftelema;
  }

  @Basic
  @Column(name = "NU_SOLI1TELEFONO", nullable = true, precision = 0)
  public Long getNuSoli1Telefono() {
    return nuSoli1Telefono;
  }

  public void setNuSoli1Telefono(final Long nuSoli1Telefono) {
    this.nuSoli1Telefono = nuSoli1Telefono;
  }

  @Basic
  @Column(name = "TX_SOLI1TIPOIDENT", nullable = true, length = 20)
  public String getTxSoli1Tipoident() {
    return txSoli1Tipoident;
  }

  public void setTxSoli1Tipoident(final String txSoli1Tipoident) {
    this.txSoli1Tipoident = txSoli1Tipoident;
  }

  @Basic
  @Column(name = "LG_CHECKDOC21", nullable = true, precision = 0)
  public Boolean getLgCheckdoc21() {
    return lgCheckdoc21;
  }

  public void setLgCheckdoc21(final Boolean lgCheckdoc21) {
    this.lgCheckdoc21 = lgCheckdoc21;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final Solicitud that = (Solicitud) o;
    return Objects.equals(idSolicitud, that.idSolicitud) && Objects.equals(nuNumexpediente, that.nuNumexpediente)
        && Objects.equals(txPersonanotif, that.txPersonanotif) && Objects.equals(txRepetagrup1Racda, that.txRepetagrup1Racda)
        && Objects.equals(txSoli1Mednotiford, that.txSoli1Mednotiford) && Objects.equals(txRepetagrup2Siglas, that.txRepetagrup2Siglas)
        && Objects.equals(txSoli1Apellido1, that.txSoli1Apellido1) && Objects.equals(txRepr1Nombre, that.txRepr1Nombre)
        && Objects.equals(txSoli1Apellido2, that.txSoli1Apellido2) && Objects.equals(txAgruprepnombre, that.txAgruprepnombre)
        && Objects.equals(fhAutorizagest1Otrasfec, that.fhAutorizagest1Otrasfec) && Objects.equals(txAutorizagest1Otrasdoc, that.txAutorizagest1Otrasdoc)
        && Objects.equals(txSoli1Piso, that.txSoli1Piso) && Objects.equals(txFirm1Ano, that.txFirm1Ano)
        && Objects.equals(txRepdatos1Ejecucion, that.txRepdatos1Ejecucion) && Objects.equals(txNotiftelefonoautlugar, that.txNotiftelefonoautlugar)
        && Objects.equals(txAutorizagest1Otraspro, that.txAutorizagest1Otraspro) && Objects.equals(nuSolicitadasolimporte, that.nuSolicitadasolimporte)
        && Objects.equals(lgCheckdoc1, that.lgCheckdoc1) && Objects.equals(txRepr1Sexo, that.txRepr1Sexo) && Objects.equals(txSoli1Siglas, that.txSoli1Siglas)
        && Objects.equals(lgCheckdoc2, that.lgCheckdoc2) && Objects.equals(txConcedidasconadmin, that.txConcedidasconadmin)
        && Objects.equals(lgCheckdoc3, that.lgCheckdoc3) && Objects.equals(nuFirm1Importe, that.nuFirm1Importe) && Objects.equals(lgCheckdoc4, that.lgCheckdoc4)
        && Objects.equals(lgCheckdoc5, that.lgCheckdoc5) && Objects.equals(lgCheckdoc6, that.lgCheckdoc6) && Objects.equals(lgCheckdoc7, that.lgCheckdoc7)
        && Objects.equals(lgCheckdoc8, that.lgCheckdoc8) && Objects.equals(txNotifnumidentlugar, that.txNotifnumidentlugar)
        && Objects.equals(lgCheckdoc9, that.lgCheckdoc9) && Objects.equals(txRepetagrupracda, that.txRepetagrupracda)
        && Objects.equals(lgSoli1Altanotifica, that.lgSoli1Altanotifica) && Objects.equals(txRepetagrup2Racda, that.txRepetagrup2Racda)
        && Objects.equals(txSoli1Numident, that.txSoli1Numident) && Objects.equals(txFirm1Fdo, that.txFirm1Fdo)
        && Objects.equals(fhAutojunta1Admfecha, that.fhAutojunta1Admfecha) && Objects.equals(txSolicitadas0Admin, that.txSolicitadas0Admin)
        && Objects.equals(txAutorizagest0Otrasdoc, that.txAutorizagest0Otrasdoc) && Objects.equals(txAutorizajunta0Admdoc, that.txAutorizajunta0Admdoc)
        && Objects.equals(txAgruprepapellido2, that.txAgruprepapellido2) && Objects.equals(lgAltanofifica, that.lgAltanofifica)
        && Objects.equals(txAgruprepapellido1, that.txAgruprepapellido1) && Objects.equals(txRepetagrupnombre, that.txRepetagrupnombre)
        && Objects.equals(txSolicitadas1Minimis, that.txSolicitadas1Minimis) && Objects.equals(txAgruprepidentific, that.txAgruprepidentific)
        && Objects.equals(txFirm1Dia, that.txFirm1Dia) && Objects.equals(txSolicitadas0Fecha, that.txSolicitadas0Fecha)
        && Objects.equals(txCodidentificativo, that.txCodidentificativo) && Objects.equals(txRepr1Tipovia, that.txRepr1Tipovia)
        && Objects.equals(txRepetagrup2Nombre, that.txRepetagrup2Nombre) && Objects.equals(txSoli1Email, that.txSoli1Email)
        && Objects.equals(txSoli1Numero, that.txSoli1Numero) && Objects.equals(txSoli1Codmunicipio, that.txSoli1Codmunicipio)
        && Objects.equals(txDatostitulo, that.txDatostitulo) && Objects.equals(txConcedidas0Minimis, that.txConcedidas0Minimis)
        && Objects.equals(txRepetagrup1Siglas, that.txRepetagrup1Siglas) && Objects.equals(txRepr1Nombrevia, that.txRepr1Nombrevia)
        && Objects.equals(txAutorizajunta1Admcons, that.txAutorizajunta1Admcons) && Objects.equals(txErrorfinalidadproy, that.txErrorfinalidadproy)
        && Objects.equals(txNotifemailautlugar, that.txNotifemailautlugar) && Objects.equals(txRepr1Tipoident, that.txRepr1Tipoident)
        && Objects.equals(txSolicitadas0Minimis, that.txSolicitadas0Minimis) && Objects.equals(nuSoli1Tlfmovil, that.nuSoli1Tlfmovil)
        && Objects.equals(txRepr1Titulo, that.txRepr1Titulo) && Objects.equals(nuSoli1Cp, that.nuSoli1Cp)
        && Objects.equals(txRepetagrup1Ident, that.txRepetagrup1Ident) && Objects.equals(fhConcedidas1Fecha, that.fhConcedidas1Fecha)
        && Objects.equals(nuRepr1Codprov, that.nuRepr1Codprov) && Objects.equals(txSoli1Poblacion, that.txSoli1Poblacion)
        && Objects.equals(lbTextootraas, that.lbTextootraas) && Objects.equals(txSoli1Nombrevia, that.txSoli1Nombrevia)
        && Objects.equals(txRepdatos0Ejecucion, that.txRepdatos0Ejecucion) && Objects.equals(txSoli1Escalera, that.txSoli1Escalera)
        && Objects.equals(txRepet0Agrupidentif, that.txRepet0Agrupidentif) && Objects.equals(txSoli1Sexo, that.txSoli1Sexo)
        && Objects.equals(lgCheckdoc20, that.lgCheckdoc20) && Objects.equals(txSoli1Portal, that.txSoli1Portal)
        && Objects.equals(lgConsentexpresorepr, that.lgConsentexpresorepr) && Objects.equals(txSoli1Codigo, that.txSoli1Codigo)
        && Objects.equals(fhAutorizagest0Otrasfec, that.fhAutorizagest0Otrasfec) && Objects.equals(txAutorizajunta1Admdoc, that.txAutorizajunta1Admdoc)
        && Objects.equals(txConcedidas1Conadmin, that.txConcedidas1Conadmin) && Objects.equals(txSoli1Kmvia, that.txSoli1Kmvia)
        && Objects.equals(lgCheckdoc18, that.lgCheckdoc18) && Objects.equals(lgCbdeclaro3, that.lgCbdeclaro3) && Objects.equals(lgCbdeclaro2, that.lgCbdeclaro2)
        && Objects.equals(lgCheckdoc19, that.lgCheckdoc19) && Objects.equals(lgCbdeclaro1, that.lgCbdeclaro1) && Objects.equals(lgCheckdoc10, that.lgCheckdoc10)
        && Objects.equals(txTipodestinatario, that.txTipodestinatario) && Objects.equals(lgCheckdoc11, that.lgCheckdoc11)
        && Objects.equals(lgCheckdoc12, that.lgCheckdoc12) && Objects.equals(lgCheckdoc13, that.lgCheckdoc13) && Objects.equals(lgCheckdoc14, that.lgCheckdoc14)
        && Objects.equals(lgCheckdoc15, that.lgCheckdoc15) && Objects.equals(lgCheckotra, that.lgCheckotra) && Objects.equals(lgCheckdoc16, that.lgCheckdoc16)
        && Objects.equals(txAutorizajunta0Admproc, that.txAutorizajunta0Admproc) && Objects.equals(lgCheckdoc17, that.lgCheckdoc17)
        && Objects.equals(fhAutojunta0Admfecha, that.fhAutojunta0Admfecha) && Objects.equals(txAutorizagest0Otraspro, that.txAutorizagest0Otraspro)
        && Objects.equals(fhConcedidas0Fecha, that.fhConcedidas0Fecha) && Objects.equals(txCalidadde, that.txCalidadde)
        && Objects.equals(nuConcedidas0Conimporte, that.nuConcedidas0Conimporte) && Objects.equals(txConcedidas1Minimis, that.txConcedidas1Minimis)
        && Objects.equals(txRepetagrup0Siglas, that.txRepetagrup0Siglas) && Objects.equals(txSoli1Nombre, that.txSoli1Nombre)
        && Objects.equals(txRepr1Numident, that.txRepr1Numident) && Objects.equals(txSoli1Tipovia, that.txSoli1Tipovia)
        && Objects.equals(lgChecktipo2, that.lgChecktipo2) && Objects.equals(lgCheckfinalidad3, that.lgCheckfinalidad3)
        && Objects.equals(lgChecktipo1, that.lgChecktipo1) && Objects.equals(lgCheckfinalidad4, that.lgCheckfinalidad4)
        && Objects.equals(lgCheckfinalidad1, that.lgCheckfinalidad1) && Objects.equals(lgCheckfinalidad2, that.lgCheckfinalidad2)
        && Objects.equals(txSoli1Bloque, that.txSoli1Bloque) && Objects.equals(txRepet2Agrupidentif, that.txRepet2Agrupidentif)
        && Objects.equals(nuRepr1Codmunicipio, that.nuRepr1Codmunicipio) && Objects.equals(txSol1Letra, that.txSol1Letra)
        && Objects.equals(txRepdatos2Ejecucion, that.txRepdatos2Ejecucion) && Objects.equals(nuConcedidas1Conimporte, that.nuConcedidas1Conimporte)
        && Objects.equals(nuSoli1Codprov, that.nuSoli1Codprov) && Objects.equals(txRepetagrup1Nombre, that.txRepetagrup1Nombre)
        && Objects.equals(nuSolicitadas1Importe, that.nuSolicitadas1Importe) && Objects.equals(fhSolicitadas1Fecha, that.fhSolicitadas1Fecha)
        && Objects.equals(txSoli1Puerta, that.txSoli1Puerta) && Objects.equals(txAutorizagest1Otrasadm, that.txAutorizagest1Otrasadm)
        && Objects.equals(txAutorizajunta0Admcons, that.txAutorizajunta0Admcons) && Objects.equals(txAutorizajunta1Admproc, that.txAutorizajunta1Admproc)
        && Objects.equals(lgMedionotiftelematica, that.lgMedionotiftelematica) && Objects.equals(txSolicitadas1Admin, that.txSolicitadas1Admin)
        && Objects.equals(txFirm1Lugar, that.txFirm1Lugar) && Objects.equals(lgCbdeclaro4, that.lgCbdeclaro4) && Objects.equals(lgCbdeclaro5, that.lgCbdeclaro5)
        && Objects.equals(lgCbdeclaro6, that.lgCbdeclaro6) && Objects.equals(txRepr1Apellido1, that.txRepr1Apellido1)
        && Objects.equals(txNotifnombrelugar, that.txNotifnombrelugar) && Objects.equals(txRepr1Apellido2, that.txRepr1Apellido2)
        && Objects.equals(txAutorizagest0Otrasadm, that.txAutorizagest0Otrasadm) && Objects.equals(txFirm1Mes, that.txFirm1Mes)
        && Objects.equals(lgSoli1Medionotiftelema, that.lgSoli1Medionotiftelema) && Objects.equals(nuSoli1Telefono, that.nuSoli1Telefono)
        && Objects.equals(txSoli1Tipoident, that.txSoli1Tipoident) && Objects.equals(lgCheckdoc21, that.lgCheckdoc21)
        && Objects.equals(txBancoIban, that.txBancoIban) && Objects.equals(txBancoCodigo, that.txBancoCodigo) && Objects.equals(txBancoPais, that.txBancoPais)
        && Objects.equals(txBancoLocalidad, that.txBancoLocalidad) && Objects.equals(txBancoSucursal, that.txBancoSucursal)
        && Objects.equals(txBancoNumCuenta, that.txBancoNumCuenta) && Objects.equals(txBancoSwiftCodBanco, that.txBancoSwiftCodBanco)
        && Objects.equals(txBancoSwiftPais, that.txBancoSwiftPais) && Objects.equals(txBancoSwiftLocalidad, that.txBancoSwiftLocalidad)
        && Objects.equals(txBancoSwiftSucur, that.txBancoSwiftSucur) && Objects.equals(txBancoEntidad, that.txBancoEntidad)
        && Objects.equals(txBancoDomicilio, that.txBancoDomicilio) && Objects.equals(txBancoCodProv, that.txBancoCodProv)
        && Objects.equals(txBancoCodMunicipio, that.txBancoCodMunicipio) && Objects.equals(txBancoCP, that.txBancoCP)
        && Objects.equals(lgConceUno, that.lgConceUno) && Objects.equals(lgConceDos, that.lgConceDos) && Objects.equals(lgConceTres, that.lgConceTres)
        && Objects.equals(lgConceCuatro, that.lgConceCuatro) && Objects.equals(lgConceCinco, that.lgConceCinco) && Objects.equals(lgConceSeis, that.lgConceSeis)
        && Objects.equals(lgConceSiete, that.lgConceSiete) && Objects.equals(lgConceOcho, that.lgConceOcho) && Objects.equals(lgDoc1, that.lgDoc1)
        && Objects.equals(lgDoc2, that.lgDoc2) && Objects.equals(lgDoc3, that.lgDoc3) && Objects.equals(lgDoc4, that.lgDoc4)
        && Objects.equals(txNoAcepto, that.txNoAcepto) && Objects.equals(txOtroA, that.txOtroA) && Objects.equals(txRefor, that.txRefor);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idSolicitud, nuNumexpediente, txPersonanotif, txRepetagrup1Racda, txSoli1Mednotiford, txRepetagrup2Siglas, txSoli1Apellido1,
        txRepr1Nombre, txSoli1Apellido2, txAgruprepnombre, fhAutorizagest1Otrasfec, txAutorizagest1Otrasdoc, txSoli1Piso, txFirm1Ano, txRepdatos1Ejecucion,
        txNotiftelefonoautlugar, txAutorizagest1Otraspro, nuSolicitadasolimporte, lgCheckdoc1, txRepr1Sexo, txSoli1Siglas, lgCheckdoc2, txConcedidasconadmin,
        lgCheckdoc3, nuFirm1Importe, lgCheckdoc4, lgCheckdoc5, lgCheckdoc6, lgCheckdoc7, lgCheckdoc8, txNotifnumidentlugar, lgCheckdoc9, txRepetagrupracda,
        lgSoli1Altanotifica, txRepetagrup2Racda, txSoli1Numident, txFirm1Fdo, fhAutojunta1Admfecha, txSolicitadas0Admin, txAutorizagest0Otrasdoc,
        txAutorizajunta0Admdoc, txAgruprepapellido2, lgAltanofifica, txAgruprepapellido1, txRepetagrupnombre, txSolicitadas1Minimis, txAgruprepidentific,
        txFirm1Dia, txSolicitadas0Fecha, txCodidentificativo, txRepr1Tipovia, txRepetagrup2Nombre, txSoli1Email, txSoli1Numero, txSoli1Codmunicipio,
        txDatostitulo, txConcedidas0Minimis, txRepetagrup1Siglas, txRepr1Nombrevia, txAutorizajunta1Admcons, txErrorfinalidadproy, txNotifemailautlugar,
        txRepr1Tipoident, txSolicitadas0Minimis, nuSoli1Tlfmovil, txRepr1Titulo, nuSoli1Cp, txRepetagrup1Ident, fhConcedidas1Fecha, nuRepr1Codprov,
        txSoli1Poblacion, lbTextootraas, txSoli1Nombrevia, txRepdatos0Ejecucion, txSoli1Escalera, txRepet0Agrupidentif, txSoli1Sexo, lgCheckdoc20,
        txSoli1Portal, lgConsentexpresorepr, txSoli1Codigo, fhAutorizagest0Otrasfec, txAutorizajunta1Admdoc, txConcedidas1Conadmin, txSoli1Kmvia, lgCheckdoc18,
        lgCbdeclaro3, lgCbdeclaro2, lgCheckdoc19, lgCbdeclaro1, lgCheckdoc10, txTipodestinatario, lgCheckdoc11, lgCheckdoc12, lgCheckdoc13, lgCheckdoc14,
        lgCheckdoc15, lgCheckotra, lgCheckdoc16, txAutorizajunta0Admproc, lgCheckdoc17, fhAutojunta0Admfecha, txAutorizagest0Otraspro, fhConcedidas0Fecha,
        txCalidadde, nuConcedidas0Conimporte, txConcedidas1Minimis, txRepetagrup0Siglas, txSoli1Nombre, txRepr1Numident, txSoli1Tipovia, lgChecktipo2,
        lgCheckfinalidad3, lgChecktipo1, lgCheckfinalidad4, lgCheckfinalidad1, lgCheckfinalidad2, txSoli1Bloque, txRepet2Agrupidentif, nuRepr1Codmunicipio,
        txSol1Letra, txRepdatos2Ejecucion, nuConcedidas1Conimporte, nuSoli1Codprov, txRepetagrup1Nombre, nuSolicitadas1Importe, fhSolicitadas1Fecha,
        txSoli1Puerta, txAutorizagest1Otrasadm, txAutorizajunta0Admcons, txAutorizajunta1Admproc, lgMedionotiftelematica, txSolicitadas1Admin, txFirm1Lugar,
        lgCbdeclaro4, lgCbdeclaro5, lgCbdeclaro6, txRepr1Apellido1, txNotifnombrelugar, txRepr1Apellido2, txAutorizagest0Otrasadm, txFirm1Mes,
        lgSoli1Medionotiftelema, nuSoli1Telefono, txSoli1Tipoident, lgCheckdoc21, txBancoIban, txBancoCodigo, txBancoPais, txBancoLocalidad, txBancoSucursal,
        txBancoNumCuenta, txBancoSwiftCodBanco, txBancoSwiftPais, txBancoSwiftLocalidad, txBancoSwiftSucur, txBancoEntidad, txBancoDomicilio, txBancoCodProv,
        txBancoCodMunicipio, txBancoCP, lgConceUno, lgConceDos, lgConceTres, lgConceCuatro, lgConceCinco, lgConceSeis, lgConceSiete, lgConceOcho, lgDoc1,
        lgDoc2, lgDoc3, lgDoc4, txNoAcepto, txOtroA, txRefor);
  }

  @OneToMany(mappedBy = "aaciTSolicitudsubongdByFkSolicitud")
  public List<Subsanacion> getAaciSubsanacionsByIdSolicitud() {
    return aaciSubsanacionsByIdSolicitud;
  }

  public void setAaciSubsanacionsByIdSolicitud(final List<Subsanacion> aaciSubsanacionsByIdSolicitud) {
    this.aaciSubsanacionsByIdSolicitud = aaciSubsanacionsByIdSolicitud;
  }

  @OneToMany(mappedBy = "aaciTSolicitudsubongdBySoliNuId")
  public List<ExclusionesSolicitud> getAaciExclusionesSolicitudsByIdSolicitud() {
    return aaciExclusionesSolicitudsByIdSolicitud;
  }

  public void setAaciExclusionesSolicitudsByIdSolicitud(final List<ExclusionesSolicitud> aaciExclusionesSolicitudsByIdSolicitud) {
    this.aaciExclusionesSolicitudsByIdSolicitud = aaciExclusionesSolicitudsByIdSolicitud;
  }

  @ManyToOne
  @JoinColumn(name = "CONV_NU_ID", referencedColumnName = "CONV_NU_ID")
  public Convocatoria getAaciTConvocatoriasByIdConvocatoria() {
    return aaciTConvocatoriasByIdConvocatoria;
  }

  public void setAaciTConvocatoriasByIdConvocatoria(final Convocatoria aaciTConvocatoriasByIdConvocatoria) {
    this.aaciTConvocatoriasByIdConvocatoria = aaciTConvocatoriasByIdConvocatoria;
  }

  /**
   * Obtiene la propiedad vbTecnico
   *
   * @return el vbTecnico
   */
  @Column(name = "VB_TECNICO", precision = 1)
  public Boolean isVbTecnico() {
    return vbTecnico;
  }

  /**
   * Establece el valor de la propiedad vbTecnico
   *
   * @param vbTecnico
   *          el vbTecnico para establecer
   */
  public void setVbTecnico(final Boolean vbTecnico) {
    this.vbTecnico = vbTecnico;
  }

  /**
   * Obtiene la propiedad vbCoordinador
   *
   * @return el vbCoordinador
   */
  @Column(name = "VB_COORDINADOR", precision = 1)
  public Boolean isVbCoordinador() {
    return vbCoordinador;
  }

  /**
   * Establece el valor de la propiedad vbCoordinador
   *
   * @param vbCoordinador
   *          el vbCoordinador para establecer
   */
  public void setVbCoordinador(final Boolean vbCoordinador) {
    this.vbCoordinador = vbCoordinador;
  }

  /**
   * Obtiene la propiedad descripcion
   *
   * @return el descripcion
   */
  @Column(name = "DESCRIPCION")
  @Lob
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

  @Column(name = "PLAZO", precision = 8)
  public BigDecimal getPlazo() {
    return plazo;
  }

  public void setPlazo(final BigDecimal plazo) {
    this.plazo = plazo;
  }

  /**
   * Obtiene la propiedad municipio
   *
   * @return el municipio
   */
  @Basic
  @Column(name = "MUNICIPIO", nullable = true, length = 4000)
  public String getMunicipio() {
    return municipio;
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
   * Obtiene la propiedad fhInicio
   *
   * @return el fhInicio
   */
  @Basic
  @Column(name = "FH_INICIO", nullable = true)
  public Date getFhInicio() {
    return fhInicio;
  }

  /**
   * Establece el valor de la propiedad fhInicio
   *
   * @param fhInicio
   *          el fhInicio para establecer
   */
  public void setFhInicio(final Date fhInicio) {
    this.fhInicio = fhInicio;
  }

  /**
   * Obtiene la propiedad fhPresentacion
   *
   * @return el fhPresentacion
   */
  @Basic
  @Column(name = "FH_PRESENTACION", nullable = true)
  public Date getFhPresentacion() {
    return fhPresentacion;
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
   * Obtiene la propiedad fhRegistro
   *
   * @return el fhRegistro
   */
  @Basic
  @Column(name = "FH_REGISTRO", nullable = true)
  public Date getFhRegistro() {
    return fhRegistro;
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
   * Obtiene la propiedad lgRepresentacion
   *
   * @return el lgRepresentacion
   */
  @Column(name = "LG_REPRESENTACION", precision = 1)
  public Boolean isLgRepresentacion() {
    return lgRepresentacion;
  }

  /**
   * Establece el valor de la propiedad lgRepresentacion
   *
   * @param lgRepresentacion
   *          el lgRepresentacion para establecer
   */
  public void setLgRepresentacion(final Boolean lgRepresentacion) {
    this.lgRepresentacion = lgRepresentacion;
  }

  /**
   * Obtiene la propiedad txLugarRegistro
   *
   * @return el txLugarRegistro
   */
  @Basic
  @Column(name = "TX_LUGAR_REGISTRO", nullable = true, length = 50)
  public String getTxLugarRegistro() {
    return txLugarRegistro;
  }

  /**
   * Establece el valor de la propiedad txLugarRegistro
   *
   * @param txLugarRegistro
   *          el txLugarRegistro para establecer
   */
  public void setTxLugarRegistro(final String txLugarRegistro) {
    this.txLugarRegistro = txLugarRegistro;
  }

  /**
   * Obtiene la propiedad txRepNombre
   *
   * @return el txRepNombre
   */
  @Basic
  @Column(name = "TX_RESP_NOMBRE", nullable = true, length = 50)
  public String getTxRespNombre() {
    return txRespNombre;
  }

  /**
   * Establece el valor de la propiedad txRepNombre
   *
   * @param txRepNombre
   *          el txRepNombre para establecer
   */
  public void setTxRespNombre(final String txRepNombre) {
    txRespNombre = txRepNombre;
  }

  /**
   * Obtiene la propiedad txRepApellidos
   *
   * @return el txRepApellidos
   */
  @Basic
  @Column(name = "TX_RESP_APELLIDOS", nullable = true, length = 50)
  public String getTxRespApellidos() {
    return txRespApellidos;
  }

  /**
   * Establece el valor de la propiedad txRepApellidos
   *
   * @param txRepApellidos
   *          el txRepApellidos para establecer
   */
  public void setTxRespApellidos(final String txRepApellidos) {
    txRespApellidos = txRepApellidos;
  }

  /**
   * Obtiene la propiedad txRepNif
   *
   * @return el txRepNif
   */
  @Basic
  @Column(name = "TX_RESP_NIF", nullable = true, length = 12)
  public String getTxRespNif() {
    return txRespNif;
  }

  /**
   * Establece el valor de la propiedad txRepNif
   *
   * @param txRepNif
   *          el txRepNif para establecer
   */
  public void setTxRespNif(final String txRepNif) {
    txRespNif = txRepNif;
  }

  /**
   * Establece el valor de la propiedad fkPais
   *
   * @param fkPais
   *          el fkPais para establecer
   */
  public void setFkPais(final String fkPais) {
    this.fkPais = fkPais;
  }

  /**
   * Obtiene la propiedad fkPais
   *
   * @return el fkPais
   */
  @Basic
  @Column(name = "FK_PAIS", nullable = true, length = 72)
  public String getFkPais() {
    return fkPais;
  }

  @OneToMany(mappedBy = "solicitud")
  public List<Contrapartes> getAaciContrapartesByIdSolicitud() {
    return aaciContrapartesByIdSolicitud;
  }

  public void setAaciContrapartesByIdSolicitud(final List<Contrapartes> aaciContrapartesByIdSolicitud) {
    this.aaciContrapartesByIdSolicitud = aaciContrapartesByIdSolicitud;
  }

  @OneToMany(mappedBy = "idSolicitud")
  public List<Agrupaciones> getAaciTAgrupacionesByIdSolicitud() {
    return agrupacionesByIdSolicitud;
  }

  public void setAaciTAgrupacionesByIdSolicitud(final List<Agrupaciones> agrupacionesByIdSolicitud) {
    this.agrupacionesByIdSolicitud = agrupacionesByIdSolicitud;
  }

  @OneToMany(mappedBy = "aaciTSolicitudsubongdByIdSolicitud")
  public List<PaisesSolicitud> getAPaisesSolicitudsByIdSolicitud() {
    return paisesSolicitudsByIdSolicitud;
  }

  public void setAPaisesSolicitudsByIdSolicitud(final List<PaisesSolicitud> paisesSolicitudsByIdSolicitud) {
    this.paisesSolicitudsByIdSolicitud = paisesSolicitudsByIdSolicitud;
  }

  @Basic
  @Column(name = "LG_CHECKTIPO3", nullable = true, precision = 0)
  public Boolean getLgChecktipo3() {
    return lgChecktipo3;
  }

  public void setLgChecktipo3(final Boolean lgChecktipo3) {
    this.lgChecktipo3 = lgChecktipo3;
  }

  @Basic
  @Column(name = "LG_CHECKTIPO4", nullable = true, precision = 0)
  public Boolean getLgChecktipo4() {
    return lgChecktipo4;
  }

  public void setLgChecktipo4(final Boolean lgChecktipo4) {
    this.lgChecktipo4 = lgChecktipo4;
  }

  /**
   * Obtiene la propiedad txBancoIban
   *
   * @return el txBancoIban
   */
  @Basic
  @Column(name = "TX_BANCO_IBAN", nullable = true, length = 4)
  public String getTxBancoIban() {
    return txBancoIban;
  }

  /**
   * Estable el valor de la propiedad txBancoIban
   *
   * @param txBancoIban
   *          el txBancoIban para establecer
   */
  public void setTxBancoIban(final String txBancoIban) {
    this.txBancoIban = txBancoIban;
  }

  /**
   * Obtiene la propiedad txBancoCodigo
   *
   * @return el txBancoCodigo
   */
  @Basic
  @Column(name = "TX_BANCO_CODIGO", nullable = true, length = 4)
  public String getTxBancoCodigo() {
    return txBancoCodigo;
  }

  /**
   * Estable el valor de la propiedad txBancoCodigo
   *
   * @param txBancoCodigo
   *          el txBancoCodigo para establecer
   */
  public void setTxBancoCodigo(final String txBancoCodigo) {
    this.txBancoCodigo = txBancoCodigo;
  }

  /**
   * Obtiene la propiedad txBancoPais
   *
   * @return el txBancoPais
   */
  @Basic
  @Column(name = "TX_BANCO_PAIS", nullable = true, length = 4)
  public String getTxBancoPais() {
    return txBancoPais;
  }

  /**
   * Estable el valor de la propiedad txBancoPais
   *
   * @param txBancoPais
   *          el txBancoPais para establecer
   */
  public void setTxBancoPais(final String txBancoPais) {
    this.txBancoPais = txBancoPais;
  }

  /**
   * Obtiene la propiedad txBancoLocalidad
   *
   * @return el txBancoLocalidad
   */
  @Basic
  @Column(name = "TX_BANCO_LOCALIDAD", nullable = true, length = 4)
  public String getTxBancoLocalidad() {
    return txBancoLocalidad;
  }

  /**
   * Estable el valor de la propiedad txBancoLocalidad
   *
   * @param txBancoLocalidad
   *          el txBancoLocalidad para establecer
   */
  public void setTxBancoLocalidad(final String txBancoLocalidad) {
    this.txBancoLocalidad = txBancoLocalidad;
  }

  /**
   * Obtiene la propiedad txBancoSucursal
   *
   * @return el txBancoSucursal
   */
  @Basic
  @Column(name = "TX_BANCO_SUCURSAL", nullable = true, length = 4)
  public String getTxBancoSucursal() {
    return txBancoSucursal;
  }

  /**
   * Estable el valor de la propiedad txBancoSucursal
   *
   * @param txBancoSucursal
   *          el txBancoSucursal para establecer
   */
  public void setTxBancoSucursal(final String txBancoSucursal) {
    this.txBancoSucursal = txBancoSucursal;
  }

  /**
   * Obtiene la propiedad txBancoNumCuenta
   *
   * @return el txBancoNumCuenta
   */
  @Basic
  @Column(name = "TX_BANCO_NUMCUENTA", nullable = true, length = 4)
  public String getTxBancoNumCuenta() {
    return txBancoNumCuenta;
  }

  /**
   * Estable el valor de la propiedad txBancoNumCuenta
   *
   * @param txBancoNumCuenta
   *          el txBancoNumCuenta para establecer
   */
  public void setTxBancoNumCuenta(final String txBancoNumCuenta) {
    this.txBancoNumCuenta = txBancoNumCuenta;
  }

  /**
   * Obtiene la propiedad txBancoSwiftCodBanco
   *
   * @return el txBancoSwiftCodBanco
   */
  @Basic
  @Column(name = "TX_BANCO_S_CODBANCO", nullable = true, length = 4)
  public String getTxBancoSwiftCodBanco() {
    return txBancoSwiftCodBanco;
  }

  /**
   * Estable el valor de la propiedad txBancoSwiftCodBanco
   *
   * @param txBancoSwiftCodBanco
   *          el txBancoSwiftCodBanco para establecer
   */
  public void setTxBancoSwiftCodBanco(final String txBancoSwiftCodBanco) {
    this.txBancoSwiftCodBanco = txBancoSwiftCodBanco;
  }

  /**
   * Obtiene la propiedad txBancoSwiftPais
   *
   * @return el txBancoSwiftPais
   */
  @Basic
  @Column(name = "TX_BANCO_S_PAIS", nullable = true, length = 2)
  public String getTxBancoSwiftPais() {
    return txBancoSwiftPais;
  }

  /**
   * Estable el valor de la propiedad txBancoSwiftPais
   *
   * @param txBancoSwiftPais
   *          el txBancoSwiftPais para establecer
   */
  public void setTxBancoSwiftPais(final String txBancoSwiftPais) {
    this.txBancoSwiftPais = txBancoSwiftPais;
  }

  /**
   * Obtiene la propiedad txBancoSwiftLocalidad
   *
   * @return el txBancoSwiftLocalidad
   */
  @Basic
  @Column(name = "TX_SWIFT_LOCALIDAD", nullable = true, length = 2)
  public String getTxBancoSwiftLocalidad() {
    return txBancoSwiftLocalidad;
  }

  /**
   * Estable el valor de la propiedad txBancoSwiftLocalidad
   *
   * @param txBancoSwiftLocalidad
   *          el txBancoSwiftLocalidad para establecer
   */
  public void setTxBancoSwiftLocalidad(final String txBancoSwiftLocalidad) {
    this.txBancoSwiftLocalidad = txBancoSwiftLocalidad;
  }

  /**
   * Obtiene la propiedad txBancoSwiftSucur
   *
   * @return el txBancoSwiftSucur
   */
  @Basic
  @Column(name = "TX_BANCO_S_SUCUR", nullable = true, length = 3)
  public String getTxBancoSwiftSucur() {
    return txBancoSwiftSucur;
  }

  /**
   * Estable el valor de la propiedad txBancoSwiftSucur
   *
   * @param txBancoSwiftSucur
   *          el txBancoSwiftSucur para establecer
   */
  public void setTxBancoSwiftSucur(final String txBancoSwiftSucur) {
    this.txBancoSwiftSucur = txBancoSwiftSucur;
  }

  /**
   * Obtiene la propiedad txBancoEntidad
   *
   * @return el txBancoEntidad
   */
  @Basic
  @Column(name = "TX_BANCO_ENTIDAD", nullable = true, length = 255)
  public String getTxBancoEntidad() {
    return txBancoEntidad;
  }

  /**
   * Estable el valor de la propiedad txBancoEntidad
   *
   * @param txBancoEntidad
   *          el txBancoEntidad para establecer
   */
  public void setTxBancoEntidad(final String txBancoEntidad) {
    this.txBancoEntidad = txBancoEntidad;
  }

  /**
   * Obtiene la propiedad txBancoDomicilio
   *
   * @return el txBancoDomicilio
   */
  @Basic
  @Column(name = "TX_BANCO_DOMICILIO", nullable = true, length = 255)
  public String getTxBancoDomicilio() {
    return txBancoDomicilio;
  }

  /**
   * Estable el valor de la propiedad txBancoDomicilio
   *
   * @param txBancoDomicilio
   *          el txBancoDomicilio para establecer
   */
  public void setTxBancoDomicilio(final String txBancoDomicilio) {
    this.txBancoDomicilio = txBancoDomicilio;
  }

  /**
   * Obtiene la propiedad txBancoCodProv
   *
   * @return el txBancoCodProv
   */
  @Basic
  @Column(name = "TX_BANCO_CODPROV", nullable = true, length = 2)
  public String getTxBancoCodProv() {
    return txBancoCodProv;
  }

  /**
   * Estable el valor de la propiedad txBancoCodProv
   *
   * @param txBancoCodProv
   *          el txBancoCodProv para establecer
   */
  public void setTxBancoCodProv(final String txBancoCodProv) {
    this.txBancoCodProv = txBancoCodProv;
  }

  /**
   * Obtiene la propiedad txBancoCodMunicipio
   *
   * @return el txBancoCodMunicipio
   */
  @Basic
  @Column(name = "TX_BANCO_CODMUNICIPIO", nullable = true, length = 20)
  public String getTxBancoCodMunicipio() {
    return txBancoCodMunicipio;
  }

  /**
   * Estable el valor de la propiedad txBancoCodMunicipio
   *
   * @param txBancoCodMunicipio
   *          el txBancoCodMunicipio para establecer
   */
  public void setTxBancoCodMunicipio(final String txBancoCodMunicipio) {
    this.txBancoCodMunicipio = txBancoCodMunicipio;
  }

  /**
   * Obtiene la propiedad txBancoCP
   *
   * @return el txBancoCP
   */
  @Basic
  @Column(name = "TX_BANCO_CP", nullable = true, length = 5)
  public String getTxBancoCP() {
    return txBancoCP;
  }

  /**
   * Estable el valor de la propiedad txBancoCP
   *
   * @param txBancoCP
   *          el txBancoCP para establecer
   */
  public void setTxBancoCP(final String txBancoCP) {
    this.txBancoCP = txBancoCP;
  }

  /**
   * Obtiene la propiedad lgConceUno
   *
   * @return el lgConceUno
   */
  @Basic
  @Column(name = "LG_CONCE_UNO", nullable = true, precision = 0)
  public Boolean getLgConceUno() {
    return lgConceUno;
  }

  /**
   * Estable el valor de la propiedad lgConceUno
   *
   * @param lgConceUno
   *          el lgConceUno para establecer
   */
  public void setLgConceUno(final Boolean lgConceUno) {
    this.lgConceUno = lgConceUno;
  }

  /**
   * Obtiene la propiedad lgConceDos
   *
   * @return el lgConceDos
   */
  @Basic
  @Column(name = "LG_CONCE_DOS", nullable = true, precision = 0)
  public Boolean getLgConceDos() {
    return lgConceDos;
  }

  /**
   * Estable el valor de la propiedad lgConceDos
   *
   * @param lgConceDos
   *          el lgConceDos para establecer
   */
  public void setLgConceDos(final Boolean lgConceDos) {
    this.lgConceDos = lgConceDos;
  }

  /**
   * Obtiene la propiedad lgConceTres
   *
   * @return el lgConceTres
   */
  @Basic
  @Column(name = "LG_CONCE_TRES", nullable = true, precision = 0)
  public Boolean getLgConceTres() {
    return lgConceTres;
  }

  /**
   * Estable el valor de la propiedad lgConceTres
   *
   * @param lgConceTres
   *          el lgConceTres para establecer
   */
  public void setLgConceTres(final Boolean lgConceTres) {
    this.lgConceTres = lgConceTres;
  }

  /**
   * Obtiene la propiedad lgConceCuatro
   *
   * @return el lgConceCuatro
   */
  @Basic
  @Column(name = "LG_CONCE_CUATRO", nullable = true, precision = 0)
  public Boolean getLgConceCuatro() {
    return lgConceCuatro;
  }

  /**
   * Estable el valor de la propiedad lgConceCuatro
   *
   * @param lgConceCuatro
   *          el lgConceCuatro para establecer
   */
  public void setLgConceCuatro(final Boolean lgConceCuatro) {
    this.lgConceCuatro = lgConceCuatro;
  }

  /**
   * Obtiene la propiedad lgConceCinco
   *
   * @return el lgConceCinco
   */
  @Basic
  @Column(name = "LG_CONCE_CINCO", nullable = true, precision = 0)
  public Boolean getLgConceCinco() {
    return lgConceCinco;
  }

  /**
   * Estable el valor de la propiedad lgConceCinco
   *
   * @param lgConceCinco
   *          el lgConceCinco para establecer
   */
  public void setLgConceCinco(final Boolean lgConceCinco) {
    this.lgConceCinco = lgConceCinco;
  }

  /**
   * Obtiene la propiedad lgConceSeis
   *
   * @return el lgConceSeis
   */
  @Basic
  @Column(name = "LG_CONCE_SEIS", nullable = true, precision = 0)
  public Boolean getLgConceSeis() {
    return lgConceSeis;
  }

  /**
   * Estable el valor de la propiedad lgConceSeis
   *
   * @param lgConceSeis
   *          el lgConceSeis para establecer
   */
  public void setLgConceSeis(final Boolean lgConceSeis) {
    this.lgConceSeis = lgConceSeis;
  }

  /**
   * Obtiene la propiedad lgConceSiete
   *
   * @return el lgConceSiete
   */
  @Basic
  @Column(name = "LG_CONCE_SIETE", nullable = true, precision = 0)
  public Boolean getLgConceSiete() {
    return lgConceSiete;
  }

  /**
   * Estable el valor de la propiedad lgConceSiete
   *
   * @param lgConceSiete
   *          el lgConceSiete para establecer
   */
  public void setLgConceSiete(final Boolean lgConceSiete) {
    this.lgConceSiete = lgConceSiete;
  }

  /**
   * Obtiene la propiedad lgConceOcho
   *
   * @return el lgConceOcho
   */
  @Basic
  @Column(name = "LG_CONCE_OCHO", nullable = true, precision = 0)
  public Boolean getLgConceOcho() {
    return lgConceOcho;
  }

  /**
   * Estable el valor de la propiedad lgConceOcho
   *
   * @param lgConceOcho
   *          el lgConceOcho para establecer
   */
  public void setLgConceOcho(final Boolean lgConceOcho) {
    this.lgConceOcho = lgConceOcho;
  }

  /**
   * Obtiene la propiedad lgDoc1
   *
   * @return el lgDoc1
   */
  @Basic
  @Column(name = "LG_DOC1", nullable = true, precision = 0)
  public Boolean getLgDoc1() {
    return lgDoc1;
  }

  /**
   * Estable el valor de la propiedad lgDoc1
   *
   * @param lgDoc1
   *          el lgDoc1 para establecer
   */
  public void setLgDoc1(final Boolean lgDoc1) {
    this.lgDoc1 = lgDoc1;
  }

  /**
   * Obtiene la propiedad lgDoc2
   *
   * @return el lgDoc2
   */
  @Basic
  @Column(name = "LG_DOC2", nullable = true, precision = 0)
  public Boolean getLgDoc2() {
    return lgDoc2;
  }

  /**
   * Estable el valor de la propiedad lgDoc2
   *
   * @param lgDoc2
   *          el lgDoc2 para establecer
   */
  public void setLgDoc2(final Boolean lgDoc2) {
    this.lgDoc2 = lgDoc2;
  }

  /**
   * Obtiene la propiedad lgDoc3
   *
   * @return el lgDoc3
   */
  @Basic
  @Column(name = "LG_DOC3", nullable = true, precision = 0)
  public Boolean getLgDoc3() {
    return lgDoc3;
  }

  /**
   * Estable el valor de la propiedad lgDoc3
   *
   * @param lgDoc3
   *          el lgDoc3 para establecer
   */
  public void setLgDoc3(final Boolean lgDoc3) {
    this.lgDoc3 = lgDoc3;
  }

  /**
   * @return the lgDoc4
   */
  @Basic
  @Column(name = "LG_DOC4", nullable = true, precision = 0)
  public Boolean getLgDoc4() {
    return lgDoc4;
  }

  /**
   * @param lgDoc4
   *          the lgDoc4 to set
   */
  public void setLgDoc4(final Boolean lgDoc4) {
    this.lgDoc4 = lgDoc4;
  }

  /**
   * Obtiene la propiedad txNoAcepto
   *
   * @return el txNoAcepto
   */
  @Basic
  @Column(name = "TX_NO_ACEPTO", nullable = true, length = 4000)
  public String getTxNoAcepto() {
    return txNoAcepto;
  }

  /**
   * Estable el valor de la propiedad txNoAcepto
   *
   * @param txNoAcepto
   *          el txNoAcepto para establecer
   */
  public void setTxNoAcepto(final String txNoAcepto) {
    this.txNoAcepto = txNoAcepto;
  }

  /**
   * Obtiene la propiedad txOtroA
   *
   * @return el txOtroA
   */
  @Basic
  @Column(name = "TX_OTRO_A", nullable = true, length = 4000)
  public String getTxOtroA() {
    return txOtroA;
  }

  /**
   * Estable el valor de la propiedad txOtroA
   *
   * @param txOtroA
   *          el txOtroA para establecer
   */
  public void setTxOtroA(final String txOtroA) {
    this.txOtroA = txOtroA;
  }

  /**
   * Obtiene la propiedad txRefor
   *
   * @return el txRefor
   */
  @Basic
  @Column(name = "TX_REFOR", nullable = true, length = 4000)
  public String getTxRefor() {
    return txRefor;
  }

  /**
   * Estable el valor de la propiedad txRefor
   *
   * @param txRefor
   *          el txRefor para establecer
   */
  public void setTxRefor(final String txRefor) {
    this.txRefor = txRefor;
  }

  /**
   * @return the documentosJA
   */
  @OneToMany(mappedBy = "solicitud")
  public List<DocumentoPoderAdminJA> getDocumentosJA() {
    return documentosJA;
  }

  /**
   * @param documentosJA
   *          the documentosJA to set
   */
  public void setDocumentosJA(final List<DocumentoPoderAdminJA> documentosJA) {
    this.documentosJA = documentosJA;
  }

  /**
   * @return the documentosOtrasAdmin
   */
  @OneToMany(mappedBy = "solicitud")
  public List<DocumentoPoderOtrasAdmin> getDocumentosOtrasAdmin() {
    return documentosOtrasAdmin;
  }

  /**
   * @param documentosOtrasAdmin
   *          the documentosOtrasAdmin to set
   */
  public void setDocumentosOtrasAdmin(final List<DocumentoPoderOtrasAdmin> documentosOtrasAdmin) {
    this.documentosOtrasAdmin = documentosOtrasAdmin;
  }

  @Basic
  @Column(name = "FH_INICIOCOM", nullable = true)
  public Date getFhFechaInicio() {
    return fhFechaInicio;
  }

  public void setFhFechaInicio(final Date fhFechaInicio) {
    this.fhFechaInicio = fhFechaInicio;
  }

  @Basic
  @Column(name = "FH_FINCOM", nullable = true)
  public Date getFhFechaFin() {
    return fhFechaFin;
  }

  public void setFhFechaFin(final Date fhFechaFin) {
    this.fhFechaFin = fhFechaFin;
  }

  /**
   * Obtiene la propiedad fhPostegracionComunIni
   *
   * @return el fhPostegracionComunIni
   */
  @Basic
  @Column(name = "FH_POSTEGRACION_COMUN_INI", nullable = true)
  public Date getFhPostegracionComunIni() {
    return fhPostegracionComunIni;
  }

  /**
   * Establece el valor de la propiedad fhPostegracionComunIni
   *
   * @param fhPostegracionComunIni
   *          el fhPostegracionComunIni para establecer
   */
  public void setFhPostegracionComunIni(final Date fhPostegracionComunIni) {
    this.fhPostegracionComunIni = fhPostegracionComunIni;
  }

  @Basic
  @Column(name = "LG_ALEGO", nullable = true, precision = 0)
  public Boolean getLgAlego() {
    return lgAlego;
  }

  public void setLgAlego(final Boolean lgAlego) {
    this.lgAlego = lgAlego;
  }

  @Basic
  @Column(name = "TX_ALEGO", nullable = true, length = 4000)
  public String getTxAlego() {
    return txAlego;
  }

  public void setTxAlego(final String txAlego) {
    this.txAlego = txAlego;
  }

  @Basic
  @Column(name = "LG_OTRO_ALEGO", nullable = true, precision = 0)
  public Boolean getLgOtroAlego() {
    return lgOtroAlego;
  }

  public void setLgOtroAlego(final Boolean lgOtroAlego) {
    this.lgOtroAlego = lgOtroAlego;
  }

  @Basic
  @Column(name = "TX_OTRO_ALEGO", nullable = true, length = 4000)
  public String getTxOtroAlego() {
    return txOtroAlego;
  }

  public void setTxOtroAlego(final String txOtroAlego) {
    this.txOtroAlego = txOtroAlego;
  }

  @Basic
  @Column(name = "TX_COD_DIR3")
  public String getTxCodDir3() {
    return txCodDir3;
  }

  public void setTxCodDir3(final String txCodDir3) {
    this.txCodDir3 = txCodDir3;
  }

  @Basic
  @Column(name = "TX_REPR1_EMAIL")
  public String getTxRepr1Email() {
    return txRepr1Email;
  }

  public void setTxRepr1Email(final String txRepr1Email) {
    this.txRepr1Email = txRepr1Email;
  }

  @Basic
  @Column(name = "TX_REPR1_TLFMOVIL")
  public String getTxRepr1TlfMovil() {
    return txRepr1TlfMovil;
  }

  public void setTxRepr1TlfMovil(final String txRepr1TlfMovil) {
    this.txRepr1TlfMovil = txRepr1TlfMovil;
  }

  @Basic
  @Column(name = "TX_REPR1_TELEFONO")
  public String getTxRepr1Telefono() {
    return txRepr1Telefono;
  }

  public void setTxRepr1Telefono(final String txRepr1Telefono) {
    this.txRepr1Telefono = txRepr1Telefono;
  }

  @OneToMany(mappedBy = "solicitud")
  public List<TExclusionesSolicitud> getTExclusionesSolicitud() {
    return tExclusionesSolicitud;
  }

  public void setTExclusionesSolicitud(final List<TExclusionesSolicitud> tExclusionesSolicitud) {
    this.tExclusionesSolicitud = tExclusionesSolicitud;
  }
}
