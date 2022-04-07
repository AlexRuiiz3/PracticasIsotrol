package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * The persistent class for the AACI_CONVOCATORIA_FECHAS database table.
 *
 */
@Entity
@Table(name = "AACI_CONVOCATORIA_FECHAS")
@NamedQuery(name = "ConvocatoriaFecha.findAll", query = "SELECT c FROM ConvocatoriaFecha c")
public class ConvocatoriaFecha implements Serializable {
  /**
   *
   */
  private static final long serialVersionUID = 8561501232432123155L;

  @Id
  @SequenceGenerator(name = "AACI_CONVOCATORIA_FECHAS_CONVFNUID_GENERATOR", sequenceName = "AACI_SEQ_CONVOCATORIA_FECHAS", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AACI_CONVOCATORIA_FECHAS_CONVFNUID_GENERATOR")
  @Column(name = "CONVF_NU_ID")
  private Long convfNuId;

  @Column(name = "CONVF_CO_FASE")
  private String convfCoFase;

  @Temporal(TemporalType.DATE)
  @Column(name = "CONVF_FH_APERTURA")
  private Date convfFhApertura;

  @Temporal(TemporalType.DATE)
  @Column(name = "CONVF_FH_CIERRE")
  private Date convfFhCierre;

  @Column(name = "CONVF_LI_FASE")
  private String convfLiFase;

  // bi-directional many-to-one association to Convocatoria
  @LazyCollection(LazyCollectionOption.FALSE)
  @ManyToOne
  @JoinColumn(name = "CONV_NU_ID", referencedColumnName = "CONV_NU_ID")
  private Convocatoria convocatoria;

  public ConvocatoriaFecha() {
    //
  }

  public Long getConvfNuId() {
    return convfNuId;
  }

  public void setConvfNuId(final Long convfNuId) {
    this.convfNuId = convfNuId;
  }

  public String getConvfCoFase() {
    return convfCoFase;
  }

  public void setConvfCoFase(final String convfCoFase) {
    this.convfCoFase = convfCoFase;
  }

  public Date getConvfFhApertura() {
    return convfFhApertura;
  }

  public void setConvfFhApertura(final Date convfFhApertura) {
    this.convfFhApertura = convfFhApertura;
  }

  public Date getConvfFhCierre() {
    return convfFhCierre;
  }

  public void setConvfFhCierre(final Date convfFhCierre) {
    this.convfFhCierre = convfFhCierre;
  }

  public String getConvfLiFase() {
    return convfLiFase;
  }

  public void setConvfLiFase(final String convfLiFase) {
    this.convfLiFase = convfLiFase;
  }

  public Convocatoria getConvocatoria() {
    return convocatoria;
  }

  public void setConvocatoria(final Convocatoria convocatoria) {
    this.convocatoria = convocatoria;
  }
}