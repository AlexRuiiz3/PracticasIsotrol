package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad;

import java.util.Collection;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "AACI_FUNCIONES", catalog = "")
public class Funcion {
  private Integer funcNuId;
  private String funcTxDescripcion;
  private Collection<EntidadesParticipantes> aaciEntidadesParticipantesByFuncNuId;
  private String funcTxCodigo;

  @Id
  @Column(name = "FUNC_NU_ID")
  public Integer getFuncNuId() {
    return funcNuId;
  }

  public void setFuncNuId(final Integer funcNuId) {
    this.funcNuId = funcNuId;
  }

  @Basic
  @Column(name = "FUNC_TX_DESCRIPCION")
  public String getFuncTxDescripcion() {
    return funcTxDescripcion;
  }

  public void setFuncTxDescripcion(final String funcTxDescripcion) {
    this.funcTxDescripcion = funcTxDescripcion;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final Funcion that = (Funcion) o;
    return Objects.equals(funcNuId, that.funcNuId) && Objects.equals(funcTxDescripcion, that.funcTxDescripcion);
  }

  @Override
  public int hashCode() {
    return Objects.hash(funcNuId, funcTxDescripcion);
  }

  @OneToMany(mappedBy = "funcion")
  public Collection<EntidadesParticipantes> getAaciEntidadesParticipantesByFuncNuId() {
    return aaciEntidadesParticipantesByFuncNuId;
  }

  public void setAaciEntidadesParticipantesByFuncNuId(final Collection<EntidadesParticipantes> aaciEntidadesParticipantesByFuncNuId) {
    this.aaciEntidadesParticipantesByFuncNuId = aaciEntidadesParticipantesByFuncNuId;
  }

  @Basic
  @Column(name = "FUNC_TX_CODIGO")
  public String getFuncTxCodigo() {
    return funcTxCodigo;
  }

  public void setFuncTxCodigo(final String funcTxCodigo) {
    this.funcTxCodigo = funcTxCodigo;
  }
}
