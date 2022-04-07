package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.vea;

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
@Table(name = "AACI_FINALIDAD", schema = "AACID_OWNER", catalog = "")
public class AaciFinalidad {
  private Integer finaNuId;
  private String finaTxDescripcion;
  private String finaCoCodigo;
  private AaciTTipoConv aaciTTipoConvByTconNuId;

  @Id
  @Column(name = "FINA_NU_ID")
  @SequenceGenerator(name = "seqFinalidad", sequenceName = "AACI_SEQ_FINALIDAD", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqFinalidad")
  public Integer getFinaNuId() {
    return finaNuId;
  }

  public void setFinaNuId(final Integer finaNuId) {
    this.finaNuId = finaNuId;
  }

  @Basic
  @Column(name = "FINA_TX_DESCRIPCION")
  public String getFinaTxDescripcion() {
    return finaTxDescripcion;
  }

  public void setFinaTxDescripcion(final String finaTxDescripcion) {
    this.finaTxDescripcion = finaTxDescripcion;
  }

  @Basic
  @Column(name = "FINA_CO_CODIGO")
  public String getFinaCoCodigo() {
    return finaCoCodigo;
  }

  public void setFinaCoCodigo(final String finaCoCodigo) {
    this.finaCoCodigo = finaCoCodigo;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final AaciFinalidad that = (AaciFinalidad) o;
    return Objects.equals(finaNuId, that.finaNuId) && Objects.equals(finaTxDescripcion, that.finaTxDescripcion)
        && Objects.equals(finaCoCodigo, that.finaCoCodigo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(finaNuId, finaTxDescripcion, finaCoCodigo);
  }

  @ManyToOne
  @JoinColumn(name = "TCON_NU_ID", referencedColumnName = "TCONV_NU_ID")
  public AaciTTipoConv getAaciTTipoConvByTconNuId() {
    return aaciTTipoConvByTconNuId;
  }

  public void setAaciTTipoConvByTconNuId(final AaciTTipoConv aaciTTipoConvByTconNuId) {
    this.aaciTTipoConvByTconNuId = aaciTTipoConvByTconNuId;
  }
}
