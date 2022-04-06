package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.vea;

import java.util.List;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "AACI_T_PAISES", schema = "AACID_OWNER", catalog = "")
public class AaciTPaises {
  private Long idPais;
  private String txCodigo;
  private String txNombre;
  private Long nuAnio;
  private Long nuPuntuacion;
  private List<AaciTPaisesSolicitud> aaciTPaisesSolicitudsByIdPais;

  @Id
  @Column(name = "ID_PAIS")
  public Long getIdPais() {
    return idPais;
  }

  public void setIdPais(final Long idPais) {
    this.idPais = idPais;
  }

  @Basic
  @Column(name = "TX_CODIGO")
  public String getTxCodigo() {
    return txCodigo;
  }

  public void setTxCodigo(final String txCodigo) {
    this.txCodigo = txCodigo;
  }

  @Basic
  @Column(name = "TX_NOMBRE")
  public String getTxNombre() {
    return txNombre;
  }

  public void setTxNombre(final String txNombre) {
    this.txNombre = txNombre;
  }

  @Basic
  @Column(name = "NU_ANIO")
  public Long getNuAnio() {
    return nuAnio;
  }

  public void setNuAnio(final Long nuAnio) {
    this.nuAnio = nuAnio;
  }

  @Basic
  @Column(name = "NU_PUNTUACION")
  public Long getNuPuntuacion() {
    return nuPuntuacion;
  }

  public void setNuPuntuacion(final Long nuPuntuacion) {
    this.nuPuntuacion = nuPuntuacion;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final AaciTPaises that = (AaciTPaises) o;
    return Objects.equals(idPais, that.idPais) && Objects.equals(txCodigo, that.txCodigo) && Objects.equals(txNombre, that.txNombre)
        && Objects.equals(nuAnio, that.nuAnio) && Objects.equals(nuPuntuacion, that.nuPuntuacion);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idPais, txCodigo, txNombre, nuAnio, nuPuntuacion);
  }

  @OneToMany(mappedBy = "aaciTPaisesByIdPais")
  public List<AaciTPaisesSolicitud> getAaciTPaisesSolicitudsByIdPais() {
    return aaciTPaisesSolicitudsByIdPais;
  }

  public void setAaciTPaisesSolicitudsByIdPais(final List<AaciTPaisesSolicitud> aaciTPaisesSolicitudsByIdPais) {
    this.aaciTPaisesSolicitudsByIdPais = aaciTPaisesSolicitudsByIdPais;
  }
}
