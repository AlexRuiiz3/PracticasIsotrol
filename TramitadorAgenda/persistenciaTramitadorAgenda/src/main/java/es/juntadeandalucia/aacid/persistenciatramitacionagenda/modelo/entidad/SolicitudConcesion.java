package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad;

import java.math.BigDecimal;

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
@Table(name = "AACI_SOLICITUD_CONCESION", catalog = "")
public class SolicitudConcesion {

  private Long socoNuId;
  private Long socoNuSolicitud;
  private String socoTxTipoConcesion;
  private BigDecimal socoNuPresupuestoValidado;
  private BigDecimal socoNuSubvencionConceder;
  private BigDecimal socoNuSubvencionSolicitada;
  private TipoConvocatoria socoTxTipoConvocatoria;

  @Id
  @Column(name = "SOCO_NU_ID", nullable = false, precision = 0)
  @SequenceGenerator(name = "seqSolicitudConcesion", sequenceName = "AACI_SEQ_SOLICITUD_CONCESION", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqSolicitudConcesion")
  public Long getSocoNuId() {
    return socoNuId;
  }

  public void setSocoNuId(final Long socoNuId) {
    this.socoNuId = socoNuId;
  }

  @Column(name = "SOCO_NU_SOLICITUD")
  public Long getSocoNuSolicitud() {
    return socoNuSolicitud;
  }

  public void setSocoNuSolicitud(final long socoNuSolicitud) {
    this.socoNuSolicitud = socoNuSolicitud;
  }

  @Column(name = "SOCO_TX_TIPO_CONCESION")
  public String getSocoTxTipoConcesion() {
    return socoTxTipoConcesion;
  }

  public void setSocoTxTipoConcesion(final String socoTxTipoConcesion) {
    this.socoTxTipoConcesion = socoTxTipoConcesion;
  }

  @Column(name = "SOCO_NU_PRESUPUESTO_VALIDADO", precision = 0)
  public BigDecimal getSocoNuPresupuestoValidado() {
    return socoNuPresupuestoValidado;
  }

  public void setSocoNuPresupuestoValidado(final BigDecimal socoNuPresupuestoValidado) {
    this.socoNuPresupuestoValidado = socoNuPresupuestoValidado;
  }

  @Column(name = "SOCO_NU_SUBVENCION_SOLICITADA", precision = 0)
  public BigDecimal getSocoNuSubvencionSolicitada() {
    return socoNuSubvencionSolicitada;
  }

  public void setSocoNuSubvencionSolicitada(final BigDecimal socoNuSubvencionSolicitada) {
    this.socoNuSubvencionSolicitada = socoNuSubvencionSolicitada;
  }

  @ManyToOne
  @JoinColumn(name = "SOCO_TCONV_NU_ID", referencedColumnName = "TCONV_NU_ID")
  public TipoConvocatoria getSocoTxTipoConvocatoria() {
    return socoTxTipoConvocatoria;
  }

  public void setSocoTxTipoConvocatoria(final TipoConvocatoria socoTxTipoConvocatoria) {
    this.socoTxTipoConvocatoria = socoTxTipoConvocatoria;
  }

  @Column(name = "SOCO_NU_SUBVENCION_CONCEDER", precision = 0)
  public BigDecimal getSocoNuSubvencionConceder() {
    return socoNuSubvencionConceder;
  }

  public void setSocoNuSubvencionConceder(final BigDecimal socoNuSubvencionConceder) {
    this.socoNuSubvencionConceder = socoNuSubvencionConceder;
  }
}
