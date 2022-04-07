package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad;

import java.util.List;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "AACI_CATALOGO", schema = "AACID_OWNER", catalog = "")
public class Catalogo {
  private Integer catNuId;
  private String catLiDescripcion;
  private Boolean catLoMotivo;
  private Boolean catLoValorador;
  private String catCoCodigo;
  private Boolean catLgValorador;
  private Boolean catLgDga;
  private Boolean catLgCoordinador;

  private TipoCatalogo aaciTipoCatalogoByTcatNuId;
  private List<CatalogoSubsanacion> aaciCatalogoSubsanacionsByCatNuId;

  @Id
  @Column(name = "CAT_NU_ID", nullable = false, precision = 0)
  @SequenceGenerator(name = "seqCatalogo", sequenceName = "AACI_SEQ_CATALOGO", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCatalogo")
  public Integer getCatNuId() {
    return catNuId;
  }

  public void setCatNuId(Integer catNuId) {
    this.catNuId = catNuId;
  }

  @Basic
  @Column(name = "CAT_LI_DESCRIPCION", nullable = true, length = 1000)
  public String getCatLiDescripcion() {
    return catLiDescripcion;
  }

  public void setCatLiDescripcion(String catLiDescripcion) {
    this.catLiDescripcion = catLiDescripcion;
  }

  @Basic
  @Column(name = "CAT_LO_MOTIVO", nullable = true, precision = 0)
  public Boolean getCatLoMotivo() {
    return catLoMotivo;
  }

  public void setCatLoMotivo(Boolean catLoMotivo) {
    this.catLoMotivo = catLoMotivo;
  }

  @Basic
  @Column(name = "CAT_LO_VALORADOR", nullable = true, precision = 0)
  public Boolean getCatLoValorador() {
    return catLoValorador;
  }

  public void setCatLoValorador(Boolean catLoValorador) {
    this.catLoValorador = catLoValorador;
  }

  @Basic
  @Column(name = "CAT_CO_CODIGO", nullable = true, length = 10)
  public String getCatCoCodigo() {
    return catCoCodigo;
  }

  public void setCatCoCodigo(String catCoCodigo) {
    this.catCoCodigo = catCoCodigo;
  }

  @Basic
  @Column(name = "CAT_LG_VALORADOR")
  public Boolean getCatLgValorador() {
    return catLgValorador;
  }

  public void setCatLgValorador(Boolean catLgValorador) {
    this.catLgValorador = catLgValorador;
  }

  @Basic
  @Column(name = "CAT_LG_DGA")
  public Boolean getCatLgDga() {
    return catLgDga;
  }

  public void setCatLgDga(Boolean catLgDga) {
    this.catLgDga = catLgDga;
  }

  @Basic
  @Column(name = "CAT_LG_COORDINADOR")
  public Boolean getCatLgCoordinador() {
    return catLgCoordinador;
  }

  public void setCatLgCoordinador(Boolean catLgCoordinador) {
    this.catLgCoordinador = catLgCoordinador;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Catalogo that = (Catalogo) o;
    return Objects.equals(catNuId, that.catNuId) && Objects.equals(catLiDescripcion, that.catLiDescripcion) && Objects.equals(catLoMotivo, that.catLoMotivo)
        && Objects.equals(catLoValorador, that.catLoValorador) && Objects.equals(catCoCodigo, that.catCoCodigo)
        && Objects.equals(catLgValorador, that.catLgValorador) && Objects.equals(catLgDga, that.catLgDga)
        && Objects.equals(catLgCoordinador, that.catLgCoordinador);
  }

  @Override
  public int hashCode() {
    return Objects.hash(catNuId, catLiDescripcion, catLoMotivo, catLoValorador, catCoCodigo, catLgValorador, catLgDga, catLgCoordinador);
  }

  @ManyToOne
  @JoinColumn(name = "TCAT_NU_ID", referencedColumnName = "TCAT_NU_ID")
  public TipoCatalogo getAaciTipoCatalogoByTcatNuId() {
    return aaciTipoCatalogoByTcatNuId;
  }

  public void setAaciTipoCatalogoByTcatNuId(TipoCatalogo aaciTipoCatalogoByTcatNuId) {
    this.aaciTipoCatalogoByTcatNuId = aaciTipoCatalogoByTcatNuId;
  }

  @OneToMany(mappedBy = "aaciCatalogoByCatNuId")
  public List<CatalogoSubsanacion> getAaciCatalogoSubsanacionsByCatNuId() {
    return aaciCatalogoSubsanacionsByCatNuId;
  }

  public void setAaciCatalogoSubsanacionsByCatNuId(List<CatalogoSubsanacion> aaciCatalogoSubsanacionsByCatNuId) {
    this.aaciCatalogoSubsanacionsByCatNuId = aaciCatalogoSubsanacionsByCatNuId;
  }
}
