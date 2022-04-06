package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "PAE_SOLICITUDES")
public class PaeSolicitudes {
  private Integer idSolicitud;
  private List<PaeMedidasSolicitud> paeMedidasSolicitudsByIdSolicitud;
  private PaeFinalidad paeFinalidadByFkFinalidad;
  private PaePaises paePais;
  private Long expTrewa;
  private List<PaeMedidasPais> paeMedidaPais;
  private List<PaeAgrupaciones> paeAgrupacionesByIdSolicitud;
  private List<PaeContrapartes> paeContrapartesByIdSolicitud;
  private BigDecimal plazo;
  private List<PaeContribuciones> paeContribucionesByIdSolicitud;
  private String codigoExpediente;
  private PaeConvocatorias paeConvocatorias;
  private List<PaeProgramacion> paeProgramaciones = new ArrayList<>();
  private PaeTipoSolicitud paeTipoSolicitud;
  private List<PaeEntidadesParticipantes> paeEntidadesParticipantes = new ArrayList<>();

  @OneToMany(mappedBy = "paeSolicitudesByFkSolicitud")
  public List<PaeMedidasSolicitud> getPaeMedidasSolicitudsByIdSolicitud() {
    return paeMedidasSolicitudsByIdSolicitud;
  }

  public void setPaeMedidasSolicitudsByIdSolicitud(List<PaeMedidasSolicitud> paeMedidasSolicitudsByIdSolicitud) {
    this.paeMedidasSolicitudsByIdSolicitud = paeMedidasSolicitudsByIdSolicitud;
  }

  @ManyToOne
  @JoinColumn(name = "FK_FINALIDAD", referencedColumnName = "ID_FINALIDAD")
  public PaeFinalidad getPaeFinalidadByFkFinalidad() {
    return paeFinalidadByFkFinalidad;
  }

  public void setPaeFinalidadByFkFinalidad(PaeFinalidad paeFinalidadByFkFinalidad) {
    this.paeFinalidadByFkFinalidad = paeFinalidadByFkFinalidad;
  }

  @Id
  @Column(name = "ID_SOLICITUD")
  public Integer getIdSolicitud() {
    return idSolicitud;
  }

  public void setIdSolicitud(Integer idSolicitud) {
    this.idSolicitud = idSolicitud;
  }

  /**
   * Getter de expTrewa
   *
   * @return espTrewa Long
   */
  @Column(name = "EXP_TREWA", precision = 19, scale = 0)
  public Long getExpTrewa() {
    return this.expTrewa;
  }

  /**
   * Setter de expTrewa
   *
   * @param expTrewa
   *          Long
   */
  public void setExpTrewa(Long expTrewa) {
    this.expTrewa = expTrewa;
  }

  /**
   * Getter de paePaises
   *
   * @return paePaises PaePaises
   */
  @ManyToOne
  @JoinColumn(name = "FK_PAIS", referencedColumnName = "ID_PAIS")
  public PaePaises getPaePais() {
    return paePais;
  }

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinTable(name = "PAE_MEDIDA_SOLICITUD", joinColumns = { @JoinColumn(name = "ID_SOLICITUD", referencedColumnName = "ID_SOLICITUD") }, inverseJoinColumns = {
      @JoinColumn(name = "ID_MED_PAIS", referencedColumnName = "ID_MED_PAIS") })
  public List<PaeMedidasPais> getPaeMedidaPais() {
    return paeMedidaPais;
  }

  public void setPaePais(PaePaises paePais) {
    this.paePais = paePais;
  }

  public void setPaeMedidaPais(List<PaeMedidasPais> paeMedidaPais) {
    this.paeMedidaPais = paeMedidaPais;
  }

  //@LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany(mappedBy = "paeSolicitudesByFkSolicitud")
  public List<PaeAgrupaciones> getPaeAgrupacionesByIdSolicitud() {
    return paeAgrupacionesByIdSolicitud;
  }

 // @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany(mappedBy = "paeSolicitudes")
  @LazyCollection(LazyCollectionOption.FALSE)
  public List<PaeContribuciones> getPaeContribucionesByIdSolicitud() {
    return paeContribucionesByIdSolicitud;
  }

  public void setPaeContribucionesByIdSolicitud(List<PaeContribuciones> paeContribucionesByIdSolicitud) {
    this.paeContribucionesByIdSolicitud = paeContribucionesByIdSolicitud;
  }

  public void setPaeAgrupacionesByIdSolicitud(List<PaeAgrupaciones> paeAgrupacionesByIdSolicitud) {
    this.paeAgrupacionesByIdSolicitud = paeAgrupacionesByIdSolicitud;
  }

  @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany(mappedBy = "paeSolicitudesByFkSolicitud")
  public List<PaeContrapartes> getPaeContrapartesByIdSolicitud() {
    return paeContrapartesByIdSolicitud;
  }

  public void setPaeContrapartesByIdSolicitud(List<PaeContrapartes> paeContrapartesByIdSolicitud) {
    this.paeContrapartesByIdSolicitud = paeContrapartesByIdSolicitud;
  }

  /**
   * Getter de plazo
   *
   * @return plazo BigDecimal
   */
  @Column(name = "PLAZO", precision = 8)
  public BigDecimal getPlazo() {
    return this.plazo;
  }

  /**
   * Setter de plazo
   *
   * @param plazo
   *          BigDecimal
   */
  public void setPlazo(BigDecimal plazo) {
    this.plazo = plazo;
  }

  @Column(name = "EXP_INTERNO", precision = 100, scale = 0)
  public String getCodigoExpediente() {
    return codigoExpediente;
  }

  public void setCodigoExpediente(String codigoExpediente) {
    this.codigoExpediente = codigoExpediente;
  }

  /**
   * Getter de paeConvocatorias
   *
   * @return paeConvocatorias PaeConvocatorias
   */
  @LazyCollection(LazyCollectionOption.FALSE)
  @ManyToOne
  @JoinColumn(name = "FK_CONVOCATORIA", referencedColumnName = "ID_CONVOCATORIA")
  public PaeConvocatorias getPaeConvocatorias() {
    return this.paeConvocatorias;
  }

  /**
   * Setter de paeConvocatorias
   *
   * @param paeConvocatorias
   *          PaeConvocatorias
   */
  public void setPaeConvocatorias(PaeConvocatorias paeConvocatorias) {
    this.paeConvocatorias = paeConvocatorias;
  }

  @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY,mappedBy = "paeSolicitudes")
  public List<PaeProgramacion> getPaeProgramaciones() {
    return paeProgramaciones;
  }

  /**
   * Setter de paeProgramaciones
   *
   * @param paeProgramaciones
   *          Set<PaeProgramacion>
   */
  public void setPaeProgramaciones(List<PaeProgramacion> paeProgramaciones) {
    this.paeProgramaciones = paeProgramaciones;
  }

  /**
   * Getter de paeTiposolicitud
   *
   * @return paeTiposolicitud PaeTipoSolicitud
   */
  @LazyCollection(LazyCollectionOption.FALSE)
  @ManyToOne
  @JoinColumn(name = "FK_TPAE_SOLICITUD")
  public PaeTipoSolicitud getPaeTipoSolicitud() {
    return paeTipoSolicitud;
  }

  /**
   * Setter de paeTipoSolicitud
   *
   * @param paeTipoSolicitud
   *          PaeTipoSolicitud
   */
  public void setPaeTipoSolicitud(PaeTipoSolicitud paeTipoSolicitud) {
    this.paeTipoSolicitud = paeTipoSolicitud;
  }
  
  @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "paeSolicitudes")
  public List<PaeEntidadesParticipantes> getPaeEntidadesParticipanteses() {
    return this.paeEntidadesParticipantes;
  }
  
  public void setPaeEntidadesParticipanteses(List<PaeEntidadesParticipantes> paeEntidadesParticipantes) {
    this.paeEntidadesParticipantes = paeEntidadesParticipantes;
  }
  
}
