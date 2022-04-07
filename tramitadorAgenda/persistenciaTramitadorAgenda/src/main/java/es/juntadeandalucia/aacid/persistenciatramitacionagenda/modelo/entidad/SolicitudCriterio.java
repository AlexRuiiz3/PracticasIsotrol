package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "AACI_SOLICITUD_CRITERIO", schema = "AACID_OWNER", catalog = "")
public class SolicitudCriterio {

  private Long socrNuId;
  private BigDecimal socrNuValor;
  private BigDecimal socrNuPuntuacion;
  private Solicitud aaciTSolicitudsubongdByIdSolicitud;
  private Criterios aaciCriteriosByCritNuId;

  public SolicitudCriterio(BigDecimal bigDecimal, BigDecimal socrNuPuntuacion) {
    super();
    this.socrNuValor = bigDecimal;
    this.socrNuPuntuacion = socrNuPuntuacion;
  }

  public SolicitudCriterio() {
  }

  @Id
  @SequenceGenerator(name = "seqSolicitudCriterio", sequenceName = "AACI_SEQ_SOLICITUD_CRITERIO", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqSolicitudCriterio")
  @Column(name = "SOCR_NU_ID", nullable = false, precision = 0)
  public Long getSocrNuId() {
    return socrNuId;
  }

  public void setSocrNuId(Long socrNuId) {
    this.socrNuId = socrNuId;
  }

  @Basic
  @Column(name = "SOCR_NU_VALOR")
  public BigDecimal getSocrNuValor() {
    return socrNuValor;
  }

  public void setSocrNuValor(BigDecimal socrNuValor) {
    this.socrNuValor = socrNuValor;
  }

  @Basic
  @Column(name = "SOCR_NU_PUNTUACION")
  public BigDecimal getSocrNuPuntuacion() {
    return socrNuPuntuacion;
  }

  public void setSocrNuPuntuacion(BigDecimal socrNuPuntuacion) {
    this.socrNuPuntuacion = socrNuPuntuacion;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    SolicitudCriterio that = (SolicitudCriterio) o;
    return Objects.equals(socrNuId, that.socrNuId) && Objects.equals(socrNuValor, that.socrNuValor);
  }

  @Override
  public int hashCode() {
    return Objects.hash(socrNuId, socrNuValor);
  }

  @ManyToOne
  @JoinColumn(name = "ID_SOLICITUD", referencedColumnName = "ID_SOLICITUD")
  public Solicitud getAaciTSolicitudsubongdByIdSolicitud() {
    return aaciTSolicitudsubongdByIdSolicitud;
  }

  public void setAaciTSolicitudsubongdByIdSolicitud(Solicitud aaciTSolicitudsubongdByIdSolicitud) {
    this.aaciTSolicitudsubongdByIdSolicitud = aaciTSolicitudsubongdByIdSolicitud;
  }

  @ManyToOne
  @JoinColumn(name = "CRIT_NU_ID", referencedColumnName = "CRIT_NU_ID")
  public Criterios getAaciCriteriosByCritNuId() {
    return aaciCriteriosByCritNuId;
  }

  public void setAaciCriteriosByCritNuId(Criterios aaciCriteriosByCritNuId) {
    this.aaciCriteriosByCritNuId = aaciCriteriosByCritNuId;
  }
}
