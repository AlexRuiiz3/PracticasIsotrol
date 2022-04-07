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
@Table(name = "AACI_SOLICITUDES_AUXILIAR", schema = "AACID_OWNER", catalog = "")
public class SolicitudesAuxiliar {
  private Long soauNuId;
  private String soauTxObservacionesVal;
  private Long soauNuNumReintegros;
  private Long soauNuCausaReintegro;
  private Long soauNuMagnitudReintegro;
  private BigDecimal soauNuPuntuacionTotal;
  private Solicitud aaciTSolicitudsubongdByIdSolicitud;

  @Id
  @SequenceGenerator(name = "AACI_SOLICITUDES_AUXILIAR_SOAUNUID_GENERATOR", sequenceName = "AACI_SEQ_SOLICITUDES_AUXILIAR", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AACI_SOLICITUDES_AUXILIAR_SOAUNUID_GENERATOR")
  @Column(name = "SOAU_NU_ID")
  public Long getSoauNuId() {
    return soauNuId;
  }

  public void setSoauNuId(Long soauNuId) {
    this.soauNuId = soauNuId;
  }

  @Basic
  @Column(name = "SOAU_TX_OBSERVACIONES_VAL")
  public String getSoauTxObservacionesVal() {
    return soauTxObservacionesVal;
  }

  public void setSoauTxObservacionesVal(String soauTxObservacionesVal) {
    this.soauTxObservacionesVal = soauTxObservacionesVal;
  }

  @Basic
  @Column(name = "SOAU_NU_NUM_REINTEGROS")
  public Long getSoauNuNumReintegros() {
    return soauNuNumReintegros;
  }

  public void setSoauNuNumReintegros(Long soauNuNumReintegros) {
    this.soauNuNumReintegros = soauNuNumReintegros;
  }

  @Basic
  @Column(name = "SOAU_NU_CAUSA_REINTEGRO")
  public Long getSoauNuCausaReintegro() {
    return soauNuCausaReintegro;
  }

  public void setSoauNuCausaReintegro(Long soauNuCausaReintegro) {
    this.soauNuCausaReintegro = soauNuCausaReintegro;
  }

  @Basic
  @Column(name = "SOAU_NU_MAGNITUD_REINTEGRO")
  public Long getSoauNuMagnitudReintegro() {
    return soauNuMagnitudReintegro;
  }

  public void setSoauNuMagnitudReintegro(Long soauNuMagnitudReintegro) {
    this.soauNuMagnitudReintegro = soauNuMagnitudReintegro;
  }

  @Basic
  @Column(name = "SOAU_NU_PUNTUACION_TOTAL")
  public BigDecimal getSoauNuPuntuacionTotal() {
    return soauNuPuntuacionTotal;
  }

  public void setSoauNuPuntuacionTotal(BigDecimal soauNuPuntuacionTotal) {
    this.soauNuPuntuacionTotal = soauNuPuntuacionTotal;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    SolicitudesAuxiliar that = (SolicitudesAuxiliar) o;
    return Objects.equals(soauNuId, that.soauNuId) && Objects.equals(soauTxObservacionesVal, that.soauTxObservacionesVal)
        && Objects.equals(soauNuNumReintegros, that.soauNuNumReintegros) && Objects.equals(soauNuCausaReintegro, that.soauNuCausaReintegro)
        && Objects.equals(soauNuMagnitudReintegro, that.soauNuMagnitudReintegro) && Objects.equals(soauNuPuntuacionTotal, that.soauNuPuntuacionTotal);
  }

  @Override
  public int hashCode() {
    return Objects.hash(soauNuId, soauTxObservacionesVal, soauNuNumReintegros, soauNuCausaReintegro, soauNuMagnitudReintegro, soauNuPuntuacionTotal);
  }

  @ManyToOne
  @JoinColumn(name = "ID_SOLICITUD", referencedColumnName = "ID_SOLICITUD")
  public Solicitud getAaciTSolicitudsubongdByIdSolicitud() {
    return aaciTSolicitudsubongdByIdSolicitud;
  }

  public void setAaciTSolicitudsubongdByIdSolicitud(Solicitud aaciTSolicitudsubongdByIdSolicitud) {
    this.aaciTSolicitudsubongdByIdSolicitud = aaciTSolicitudsubongdByIdSolicitud;
  }
}
