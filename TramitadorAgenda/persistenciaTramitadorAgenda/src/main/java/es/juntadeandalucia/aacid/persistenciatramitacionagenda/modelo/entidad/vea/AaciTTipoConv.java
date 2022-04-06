package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.vea;

import java.util.List;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "AACI_T_TIPO_CONV", schema = "AACID_OWNER", catalog = "")
public class AaciTTipoConv {
  private Long tconvNuId;
  private String tconvLiDenominacion;
  private List<AaciFinalidad> aaciFinalidadsByTconvNuId;

  @GeneratedValue
  @Id
  @Column(name = "TCONV_NU_ID", nullable = false, precision = 0)
  public Long getTconvNuId() {
    return tconvNuId;
  }

  public void setTconvNuId(final Long tconvNuId) {
    this.tconvNuId = tconvNuId;
  }

  @Basic
  @Column(name = "TCONV_LI_DENOMINACION", nullable = true, length = 56)
  public String getTconvLiDenominacion() {
    return tconvLiDenominacion;
  }

  public void setTconvLiDenominacion(final String tconvLiDenominacion) {
    this.tconvLiDenominacion = tconvLiDenominacion;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final AaciTTipoConv that = (AaciTTipoConv) o;
    return Objects.equals(tconvNuId, that.tconvNuId) && Objects.equals(tconvLiDenominacion, that.tconvLiDenominacion);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tconvNuId, tconvLiDenominacion);
  }

  @OneToMany(mappedBy = "aaciTTipoConvByTconNuId")
  public List<AaciFinalidad> getAaciFinalidadsByTconvNuId() {
    return aaciFinalidadsByTconvNuId;
  }

  public void setAaciFinalidadsByTconvNuId(final List<AaciFinalidad> aaciFinalidadsByTconvNuId) {
    this.aaciFinalidadsByTconvNuId = aaciFinalidadsByTconvNuId;
  }
}
