package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "AACI_TIPO_EXCLUSION", schema = "AACID_OWNER", catalog = "")
public class TipoExclusion implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 8793735579085741437L;

  private Integer texcNuId;
  private Integer texcNuOrden;
  private String texcLiDescripcion;
  private Integer texcNuAnio;
  private Integer texcNuTipo;
  private List<Exclusion> aaciExclusionsByTexcNuId;
  private TipoConvocatoria aaciTTipoConvByTconvNuId;

  @Id
  @GeneratedValue
  @Column(name = "TEXC_NU_ID", nullable = false, precision = 0)
  public Integer getTexcNuId() {
    return texcNuId;
  }

  public void setTexcNuId(final Integer texcNuId) {
    this.texcNuId = texcNuId;
  }

  @Basic
  @Column(name = "TEXC_NU_ORDEN", nullable = true, precision = 0)
  public Integer getTexcNuOrden() {
    return texcNuOrden;
  }

  public void setTexcNuOrden(final Integer texcNuOrden) {
    this.texcNuOrden = texcNuOrden;
  }

  @Basic
  @Column(name = "TEXC_LI_DESCRIPCION", nullable = true, length = 1000)
  public String getTexcLiDescripcion() {
    return texcLiDescripcion;
  }

  public void setTexcLiDescripcion(final String texcLiDescripcion) {
    this.texcLiDescripcion = texcLiDescripcion;
  }

  @Basic
  @Column(name = "TEXC_NU_ANIO", nullable = true, precision = 0)
  public Integer getTexcNuAnio() {
    return texcNuAnio;
  }

  public void setTexcNuAnio(final Integer texcNuAnio) {
    this.texcNuAnio = texcNuAnio;
  }

  @Basic
  @Column(name = "TEXC_NU_TIPO", nullable = true, precision = 0)
  public Integer getTexcNuTipo() {
    return texcNuTipo;
  }

  public void setTexcNuTipo(final Integer texcNuTipo) {
    this.texcNuTipo = texcNuTipo;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final TipoExclusion that = (TipoExclusion) o;
    return Objects.equals(texcNuId, that.texcNuId) && Objects.equals(texcNuOrden, that.texcNuOrden) && Objects.equals(texcLiDescripcion, that.texcLiDescripcion)
        && Objects.equals(texcNuAnio, that.texcNuAnio) && Objects.equals(texcNuTipo, that.texcNuTipo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(texcNuId, texcNuOrden, texcLiDescripcion, texcNuAnio, texcNuTipo);
  }

  @OneToMany(mappedBy = "aaciTipoExclusionByTexcNuId")
  public List<Exclusion> getAaciExclusionsByTexcNuId() {
    return aaciExclusionsByTexcNuId;
  }

  public void setAaciExclusionsByTexcNuId(final List<Exclusion> aaciExclusionsByTexcNuId) {
    this.aaciExclusionsByTexcNuId = aaciExclusionsByTexcNuId;
  }

  @ManyToOne
  @JoinColumn(name = "TCONV_NU_ID", referencedColumnName = "TCONV_NU_ID")
  public TipoConvocatoria getAaciTTipoConvByTconvNuId() {
    return aaciTTipoConvByTconvNuId;
  }

  public void setAaciTTipoConvByTconvNuId(final TipoConvocatoria aaciTTipoConvByTconvNuId) {
    this.aaciTTipoConvByTconvNuId = aaciTTipoConvByTconvNuId;
  }

}
