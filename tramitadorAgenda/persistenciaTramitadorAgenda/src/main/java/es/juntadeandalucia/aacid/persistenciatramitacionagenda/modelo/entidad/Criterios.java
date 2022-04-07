package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "AACI_CRITERIOS", schema = "AACID_OWNER", catalog = "")
public class Criterios {
  private Long critNuId;
  private String critTxDescripcion;
  private Long critNuAnio;
  private BigDecimal critNuValMax;
  private TiposCriterios aaciTCriteriosByTcriNuId;
  private Collection<SolicitudCriterio> aaciSolicitudCriteriosByCritNuId;
  private String critSubFinalidad;
  private Integer critNuOrden;

  public Criterios() {
    super();
  }

  /**
   * @param critTxDescripcion
   * @param critNuValMax
   */
  public Criterios(Long critNuId, String critTxDescripcion, BigDecimal critNuValMax) {
    super();
    this.critNuId = critNuId;
    this.critTxDescripcion = critTxDescripcion;
    this.critNuValMax = critNuValMax;
  }

  @Id
  @Column(name = "CRIT_NU_ID")
  public Long getCritNuId() {
    return critNuId;
  }

  public void setCritNuId(Long critNuId) {
    this.critNuId = critNuId;
  }

  @Basic
  @Column(name = "CRIT_TX_DESCRIPCION")
  public String getCritTxDescripcion() {
    return critTxDescripcion;
  }

  public void setCritTxDescripcion(String critTxDescripcion) {
    this.critTxDescripcion = critTxDescripcion;
  }

  @Basic
  @Column(name = "CRIT_NU_ANIO")
  public Long getCritNuAnio() {
    return critNuAnio;
  }

  public void setCritNuAnio(Long critNuAnio) {
    this.critNuAnio = critNuAnio;
  }

  @Basic
  @Column(name = "CRIT_NU_VAL_MAX")
  public BigDecimal getCritNuValMax() {
    return critNuValMax;
  }

  public void setCritNuValMax(BigDecimal critNuValMax) {
    this.critNuValMax = critNuValMax;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Criterios that = (Criterios) o;
    return Objects.equals(critNuId, that.critNuId) && Objects.equals(critTxDescripcion, that.critTxDescripcion) && Objects.equals(critNuAnio, that.critNuAnio)
        && Objects.equals(critNuValMax, that.critNuValMax);
  }

  @Override
  public int hashCode() {
    return Objects.hash(critNuId, critTxDescripcion, critNuAnio, critNuValMax);
  }

  @ManyToOne
  @JoinColumn(name = "TCRI_NU_ID", referencedColumnName = "TCRI_NU_ID")
  public TiposCriterios getAaciTCriteriosByTcriNuId() {
    return aaciTCriteriosByTcriNuId;
  }

  public void setAaciTCriteriosByTcriNuId(TiposCriterios aaciTCriteriosByTcriNuId) {
    this.aaciTCriteriosByTcriNuId = aaciTCriteriosByTcriNuId;
  }

  @OneToMany(mappedBy = "aaciCriteriosByCritNuId")
  public Collection<SolicitudCriterio> getAaciSolicitudCriteriosByCritNuId() {
    return aaciSolicitudCriteriosByCritNuId;
  }

  public void setAaciSolicitudCriteriosByCritNuId(Collection<SolicitudCriterio> aaciSolicitudCriteriosByCritNuId) {
    this.aaciSolicitudCriteriosByCritNuId = aaciSolicitudCriteriosByCritNuId;
  }

  @Basic
  @Column(name = "CRIT_SUB_FINALIDAD")
  public String getCritSubFinalidad() {
    return critSubFinalidad;
  }

  public void setCritSubFinalidad(String critSubFinalidad) {
    this.critSubFinalidad = critSubFinalidad;
  }
  @Basic
  @Column(name = "CRIT_NU_ORDEN")
  public Integer getCritNuOrden() {
    return critNuOrden;
  }

  public void setCritNuOrden(Integer critNuOrden) {
    this.critNuOrden = critNuOrden;
  }
}
