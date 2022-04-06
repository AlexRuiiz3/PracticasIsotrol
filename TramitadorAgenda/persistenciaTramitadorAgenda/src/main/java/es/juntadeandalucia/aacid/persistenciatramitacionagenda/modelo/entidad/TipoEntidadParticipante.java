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
@Table(name = "AACI_T_ENTIDAD_PARTICIPANTE", schema = "AACID_OWNER", catalog = "")
public class TipoEntidadParticipante {
  private Integer tepaNuId;
  private String tepaTxDescripcion;
  private String tepaTxCodigo;
  private Collection<EntidadesParticipantes> aaciEntidadesParticipantesByTepaNuId;

  @Id
  @Column(name = "TEPA_NU_ID")
  public Integer getTepaNuId() {
    return tepaNuId;
  }

  public void setTepaNuId(final Integer tepaNuId) {
    this.tepaNuId = tepaNuId;
  }

  @OneToMany(mappedBy = "tipoEntidadParticipante")
  public Collection<EntidadesParticipantes> getAaciEntidadesParticipantesByTepaNuId() {
    return aaciEntidadesParticipantesByTepaNuId;
  }

  public void setAaciEntidadesParticipantesByTepaNuId(final Collection<EntidadesParticipantes> aaciEntidadesParticipantesByTepaNuId) {
    this.aaciEntidadesParticipantesByTepaNuId = aaciEntidadesParticipantesByTepaNuId;
  }

  @Basic
  @Column(name = "TEPA_TX_CODIGO")
  public String getTepaTxCodigo() {
    return tepaTxCodigo;
  }

  public void setTepaTxCodigo(final String tepaTxCodigo) {
    this.tepaTxCodigo = tepaTxCodigo;
  }

  /**
   * Obtiene la propiedad tepaTxDescripcion
   *
   * @return el tepaTxDescripcion
   */
  @Basic
  @Column(name = "TEPA_TX_DESCRIPCION")
  public String getTepaTxDescripcion() {
    return tepaTxDescripcion;
  }

  /**
   * Establece el valor de la propiedad tepaTxDescripcion
   *
   * @param tepaTxDescripcion
   *          el tepaTxDescripcion para establecer
   */
  public void setTepaTxDescripcion(final String tepaTxDescripcion) {
    this.tepaTxDescripcion = tepaTxDescripcion;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final TipoEntidadParticipante that = (TipoEntidadParticipante) o;
    return Objects.equals(tepaNuId, that.tepaNuId) && Objects.equals(tepaTxDescripcion, that.tepaTxDescripcion);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tepaNuId, tepaTxDescripcion);
  }

}
