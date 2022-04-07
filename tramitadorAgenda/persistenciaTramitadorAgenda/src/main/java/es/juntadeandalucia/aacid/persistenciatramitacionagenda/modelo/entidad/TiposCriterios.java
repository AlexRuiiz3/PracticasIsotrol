package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad;

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
@Table(name = "AACI_T_CRITERIOS", schema = "AACID_OWNER", catalog = "")
public class TiposCriterios {
  private Long tcriNuId;
  private String tcriTxNombre;
  private Collection<Criterios> aaciCriteriosByTcriNuId;
  private Collection<SolicitudTipoCriterio> aaciSolicitudTcriteriosByTcriNuId;
  private TipoConvocatoria aaciTTipoConvByTconNuId;
  private Finalidad aaciFinalidad;

  @Id
  @Column(name = "TCRI_NU_ID")
  public Long getTcriNuId() {
    return tcriNuId;
  }

  public void setTcriNuId(final Long tcriNuId) {
    this.tcriNuId = tcriNuId;
  }

  @Basic
  @Column(name = "TCRI_TX_NOMBRE")
  public String getTcriTxNombre() {
    return tcriTxNombre;
  }

  public void setTcriTxNombre(final String tcriTxNombre) {
    this.tcriTxNombre = tcriTxNombre;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final TiposCriterios that = (TiposCriterios) o;
    return Objects.equals(tcriNuId, that.tcriNuId) && Objects.equals(tcriTxNombre, that.tcriTxNombre);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tcriNuId, tcriTxNombre);
  }

  @OneToMany(mappedBy = "aaciTCriteriosByTcriNuId")
  public Collection<Criterios> getAaciCriteriosByTcriNuId() {
    return aaciCriteriosByTcriNuId;
  }

  public void setAaciCriteriosByTcriNuId(final Collection<Criterios> aaciCriteriosByTcriNuId) {
    this.aaciCriteriosByTcriNuId = aaciCriteriosByTcriNuId;
  }

  @OneToMany(mappedBy = "aaciTCriteriosByTcriNuId")
  public Collection<SolicitudTipoCriterio> getAaciSolicitudTcriteriosByTcriNuId() {
    return aaciSolicitudTcriteriosByTcriNuId;
  }

  public void setAaciSolicitudTcriteriosByTcriNuId(final Collection<SolicitudTipoCriterio> aaciSolicitudTcriteriosByTcriNuId) {
    this.aaciSolicitudTcriteriosByTcriNuId = aaciSolicitudTcriteriosByTcriNuId;
  }

  @ManyToOne
  @JoinColumn(name = "TCON_NU_ID", referencedColumnName = "TCONV_NU_ID")
  public TipoConvocatoria getAaciTTipoConvByTconNuId() {
    return aaciTTipoConvByTconNuId;
  }

  public void setAaciTTipoConvByTconNuId(final TipoConvocatoria aaciTTipoConvByTconNuId) {
    this.aaciTTipoConvByTconNuId = aaciTTipoConvByTconNuId;
  }

  @ManyToOne
  @JoinColumn(name = "FINA_NU_ID", referencedColumnName = "FINA_NU_ID")
  public Finalidad getAaciFinalidad() {
    return aaciFinalidad;
  }

  public void setAaciFinalidad(final Finalidad aaciFinalidad) {
    this.aaciFinalidad = aaciFinalidad;
  }
}
