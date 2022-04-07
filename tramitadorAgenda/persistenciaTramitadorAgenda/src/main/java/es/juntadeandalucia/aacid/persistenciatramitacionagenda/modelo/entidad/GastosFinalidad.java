package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "AACI_GASTOS_FINALIDAD", schema = "AACID_OWNER", catalog = "")
public class GastosFinalidad {
  private Integer gafiNuId;
  private Gasto aaciGasto;
  private Finalidad aaciFinalidad;

  @Id
  @Column(name = "GAFI_NU_ID")
  public Integer getGafiNuId() {
    return gafiNuId;
  }

  public void setGafiNuId(final Integer gafiNuId) {
    this.gafiNuId = gafiNuId;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final GastosFinalidad that = (GastosFinalidad) o;
    return Objects.equals(gafiNuId, that.gafiNuId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(gafiNuId);
  }

  @ManyToOne
  @JoinColumn(name = "FINA_NU_ID", referencedColumnName = "FINA_NU_ID")
  public Finalidad getAaciFinalidad() {
    return aaciFinalidad;
  }

  public void setAaciFinalidad(final Finalidad aaciFinalidad) {
    this.aaciFinalidad = aaciFinalidad;
  }

  @ManyToOne
  @JoinColumn(name = "GAST_NU_ID", referencedColumnName = "GAST_NU_ID")
  public Gasto getAaciGasto() {
    return aaciGasto;
  }

  public void setAaciGasto(final Gasto aaciGasto) {
    this.aaciGasto = aaciGasto;
  }
}
