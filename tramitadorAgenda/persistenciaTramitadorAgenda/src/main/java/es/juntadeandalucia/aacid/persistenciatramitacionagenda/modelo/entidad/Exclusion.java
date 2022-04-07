package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad;

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
@Table(name = "AACI_EXCLUSION", schema = "AACID_OWNER", catalog = "")
public class Exclusion {
  private Long exclNuId;
  private String exclLiDescripcion;
  private String exclLiPerfil;
  private Integer exclNuOrden;
  private Long exclNuAnio;
  private TipoExclusion aaciTipoExclusionByTexcNuId;
  private List<ExclusionesSolicitud> aaciExclusionesSolicitudsByExclNuId;

  @Id
  @GeneratedValue
  @Column(name = "EXCL_NU_ID", nullable = false, precision = 0)
  public Long getExclNuId() {
    return exclNuId;
  }

  public void setExclNuId(Long exclNuId) {
    this.exclNuId = exclNuId;
  }

  @Basic
  @Column(name = "EXCL_LI_DESCRIPCION", nullable = true, length = 1000)
  public String getExclLiDescripcion() {
    return exclLiDescripcion;
  }

  public void setExclLiDescripcion(String exclLiDescripcion) {
    this.exclLiDescripcion = exclLiDescripcion;
  }

  @Basic
  @Column(name = "EXCL_LI_PERFIL", nullable = true, length = 50)
  public String getExclLiPerfil() {
    return exclLiPerfil;
  }

  public void setExclLiPerfil(String exclLiPerfil) {
    this.exclLiPerfil = exclLiPerfil;
  }

  @Basic
  @Column(name = "EXCL_NU_ORDEN", nullable = true, precision = 0)
  public Integer getExclNuOrden() {
    return exclNuOrden;
  }

  public void setExclNuOrden(Integer exclNuOrden) {
    this.exclNuOrden = exclNuOrden;
  }

  @Basic
  @Column(name = "EXCL_NU_ANIO", nullable = true, precision = 0)
  public Long getExclNuAnio() {
    return exclNuAnio;
  }

  public void setExclNuAnio(Long exclNuAnio) {
    this.exclNuAnio = exclNuAnio;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Exclusion that = (Exclusion) o;
    return Objects.equals(exclNuId, that.exclNuId) && Objects.equals(exclLiDescripcion, that.exclLiDescripcion)
        && Objects.equals(exclLiPerfil, that.exclLiPerfil) && Objects.equals(exclNuOrden, that.exclNuOrden) && Objects.equals(exclNuAnio, that.exclNuAnio);
  }

  @Override
  public int hashCode() {
    return Objects.hash(exclNuId, exclLiDescripcion, exclLiPerfil, exclNuOrden, exclNuAnio);
  }

  @ManyToOne
  @JoinColumn(name = "TEXC_NU_ID", referencedColumnName = "TEXC_NU_ID")
  public TipoExclusion getAaciTipoExclusionByTexcNuId() {
    return aaciTipoExclusionByTexcNuId;
  }

  public void setAaciTipoExclusionByTexcNuId(TipoExclusion aaciTipoExclusionByTexcNuId) {
    this.aaciTipoExclusionByTexcNuId = aaciTipoExclusionByTexcNuId;
  }

  @OneToMany(mappedBy = "aaciExclusionByExclNuId")
  public List<ExclusionesSolicitud> getAaciExclusionesSolicitudsByExclNuId() {
    return aaciExclusionesSolicitudsByExclNuId;
  }

  public void setAaciExclusionesSolicitudsByExclNuId(List<ExclusionesSolicitud> aaciExclusionesSolicitudsByExclNuId) {
    this.aaciExclusionesSolicitudsByExclNuId = aaciExclusionesSolicitudsByExclNuId;
  }

}
