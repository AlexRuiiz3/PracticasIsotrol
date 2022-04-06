package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AACI_TM_ENTIDAD_CONTRAPARTE", catalog = "")
public class TipoEntidadContraparte {

  private long tMecNuId;
  private String tMecTxCodigo;
  private String tMecTxDescripcion;

  @GeneratedValue
  @Id
  @Column(name = "TMEC_NU_ID", nullable = false, precision = 0)
  public long gettMecNuId() {
    return tMecNuId;
  }

  public void settMecNuId(final long tMecNuId) {
    this.tMecNuId = tMecNuId;
  }

  @Basic
  @Column(name = "TMEC_TX_CODIGO", nullable = false, precision = 0)
  public String gettMecTxCodigo() {
    return tMecTxCodigo;
  }

  public void settMecTxCodigo(final String tMecTxCodigo) {
    this.tMecTxCodigo = tMecTxCodigo;
  }

  @Basic
  @Column(name = "TMEC_TX_DESCRIPTION", nullable = false, precision = 0)
  public String gettMecTxDescripcion() {
    return tMecTxDescripcion;
  }

  public void settMecTxDescripcion(final String tMecTxDescripcion) {
    this.tMecTxDescripcion = tMecTxDescripcion;
  }

}
