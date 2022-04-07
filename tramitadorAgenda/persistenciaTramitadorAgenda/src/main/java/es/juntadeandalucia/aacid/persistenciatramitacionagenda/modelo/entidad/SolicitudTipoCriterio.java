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
@Table(name = "AACI_SOLICITUD_TCRITERIO", schema = "AACID_OWNER", catalog = "")
public class SolicitudTipoCriterio {
  private Long sotcNuId;
  private String sotcTxObservaciones;
  private BigDecimal sotcNuPuntuacionTotal;
  private Solicitud aaciTSolicitudsubongdByIdSolicitud;
  private TiposCriterios aaciTCriteriosByTcriNuId;

  @Id
  @SequenceGenerator(name = "seqSolicitudTipoCriterio", sequenceName = "AACI_SEQ_SOLI_TCRITERIO", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqSolicitudTipoCriterio")
  @Column(name = "SOTC_NU_ID")
  public Long getSotcNuId() {
    return sotcNuId;
  }

  public void setSotcNuId(final Long sotcNuId) {
    this.sotcNuId = sotcNuId;
  }

  @Basic
  @Column(name = "SOTC_TX_OBSERVACIONES")
  public String getSotcTxObservaciones() {
    return sotcTxObservaciones;
  }

  public void setSotcTxObservaciones(final String sotcTxObservaciones) {
    this.sotcTxObservaciones = sotcTxObservaciones;
  }

  @Basic
  @Column(name = "SOTC_NU_PUNTUACION_TOTAL")
  public BigDecimal getSotcNuPuntuacionTotal() {
    return sotcNuPuntuacionTotal;
  }

  public void setSotcNuPuntuacionTotal(final BigDecimal sotcNuPuntuacionTotal) {
    this.sotcNuPuntuacionTotal = sotcNuPuntuacionTotal;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final SolicitudTipoCriterio that = (SolicitudTipoCriterio) o;
    return Objects.equals(sotcNuId, that.sotcNuId) && Objects.equals(sotcTxObservaciones, that.sotcTxObservaciones)
        && Objects.equals(sotcNuPuntuacionTotal, that.sotcNuPuntuacionTotal);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sotcNuId, sotcTxObservaciones, sotcNuPuntuacionTotal);
  }

  @ManyToOne
  @JoinColumn(name = "ID_SOLICITUD", referencedColumnName = "ID_SOLICITUD")
  public Solicitud getAaciTSolicitudsubongdByIdSolicitud() {
    return aaciTSolicitudsubongdByIdSolicitud;
  }

  public void setAaciTSolicitudsubongdByIdSolicitud(final Solicitud aaciTSolicitudsubongdByIdSolicitud) {
    this.aaciTSolicitudsubongdByIdSolicitud = aaciTSolicitudsubongdByIdSolicitud;
  }

  @ManyToOne
  @JoinColumn(name = "TCRI_NU_ID", referencedColumnName = "TCRI_NU_ID")
  public TiposCriterios getAaciTCriteriosByTcriNuId() {
    return aaciTCriteriosByTcriNuId;
  }

  public void setAaciTCriteriosByTcriNuId(final TiposCriterios aaciTCriteriosByTcriNuId) {
    this.aaciTCriteriosByTcriNuId = aaciTCriteriosByTcriNuId;
  }
}
