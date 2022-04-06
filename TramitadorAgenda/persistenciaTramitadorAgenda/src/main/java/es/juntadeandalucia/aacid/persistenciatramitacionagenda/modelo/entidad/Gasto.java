package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad;

import java.util.List;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "AACI_GASTOS", schema = "AACID_OWNER", catalog = "")
public class Gasto {
  private Long aaciGasto;
  private String gastTxDescripcion;
  private String gastCoCodigo;
  private Long gastNuOrden;
  private List<GastosFinalidad> aaciGastosFinalidades;
  private List<Contribuciones> contribuciones;

  @Id
  @Column(name = "GAST_NU_ID")
  public Long getAaciGasto() {
    return aaciGasto;
  }

  public void setAaciGasto(final Long aaciGasto) {
    this.aaciGasto = aaciGasto;
  }

  @Basic
  @Column(name = "GAST_TX_DESCRIPCION")
  public String getGastTxDescripcion() {
    return gastTxDescripcion;
  }

  public void setGastTxDescripcion(final String gastTxDescripcion) {
    this.gastTxDescripcion = gastTxDescripcion;
  }

  @Basic
  @Column(name = "GAST_CO_CODIGO")
  public String getGastCoCodigo() {
    return gastCoCodigo;
  }

  public void setGastCoCodigo(final String gastCoCodigo) {
    this.gastCoCodigo = gastCoCodigo;
  }

  @Basic
  @Column(name = "GAST_NU_ORDEN")
  public Long getGastNuOrden() {
    return gastNuOrden;
  }

  public void setGastNuOrden(final Long gastNuOrden) {
    this.gastNuOrden = gastNuOrden;
  }

  /**
   * Obtiene la propiedad contribuciones
   *
   * @return el aaciContribuciones
   */
  // @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany(mappedBy = "gasto")
  public List<Contribuciones> getContribuciones() {
    return contribuciones;
  }

  /**
   * Establece el valor de la propiedad contribuciones
   *
   * @param contribuciones
   *          el contribuciones para establecer
   */
  public void setContribuciones(final List<Contribuciones> contribuciones) {
    this.contribuciones = contribuciones;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final Gasto that = (Gasto) o;
    return Objects.equals(aaciGasto, that.aaciGasto) && Objects.equals(gastTxDescripcion, that.gastTxDescripcion)
        && Objects.equals(gastCoCodigo, that.gastCoCodigo) && Objects.equals(gastNuOrden, that.gastNuOrden);
  }

  @Override
  public int hashCode() {
    return Objects.hash(aaciGasto, gastTxDescripcion, gastCoCodigo, gastNuOrden);
  }

  /**
   * Obtiene la propiedad aaciGastosFinalidades
   *
   * @return el aaciGastosFinalidades
   */
  @OneToMany(mappedBy = "aaciGasto")
  public List<GastosFinalidad> getAaciGastosFinalidades() {
    return aaciGastosFinalidades;
  }

  /**
   * Establece el valor de la propiedad aaciGastosFinalidades
   *
   * @param aaciGastosFinalidades
   *          el aaciGastosFinalidades para establecer
   */
  public void setAaciGastosFinalidades(final List<GastosFinalidad> aaciGastosFinalidades) {
    this.aaciGastosFinalidades = aaciGastosFinalidades;
  }

}
