package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "AACI_T_PAISES")
public class Pais {

  private Long idPais;
  private String nombre;
  private String codigo;
  private Integer anio;
  private BigDecimal puntuacion;
  private List<PaisesSolicitud> paisesSolicitudsByIdPais;

  @Id
  @Column(name = "ID_PAIS", nullable = false, precision = 19, scale = 0)
  public Long getIdPais() {
    return idPais;
  }

  public void setIdPais(final Long idPais) {
    this.idPais = idPais;
  }

  @Column(name = "TX_NOMBRE", length = 500)
  public String getNombre() {
    return nombre;
  }

  public void setNombre(final String nombre) {
    this.nombre = nombre;
  }

  @Column(name = "TX_CODIGO", length = 100)
  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(final String codigo) {
    this.codigo = codigo;
  }

  @Column(name = "NU_ANIO", precision = 5, scale = 0)
  public Integer getAnio() {
    return anio;
  }

  public void setAnio(final Integer anio) {
    this.anio = anio;
  }

  @Column(name = "NU_PUNTUACION", precision = 2, scale = 2)
  public BigDecimal getPuntuacion() {
    return puntuacion;
  }

  public void setPuntuacion(final BigDecimal puntuacion) {
    this.puntuacion = puntuacion;
  }

  @OneToMany(mappedBy = "aaciTPaisesByIdPais")
  public List<PaisesSolicitud> getPaisesSolicitudsByIdPais() {
    return paisesSolicitudsByIdPais;
  }

  public void setPaisesSolicitudsByIdPais(final List<PaisesSolicitud> paisesSolicitudsByIdPais) {
    this.paisesSolicitudsByIdPais = paisesSolicitudsByIdPais;
  }

}
