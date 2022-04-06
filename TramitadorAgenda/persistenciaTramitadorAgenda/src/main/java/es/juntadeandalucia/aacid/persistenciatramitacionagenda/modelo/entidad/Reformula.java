package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import es.juntadeandalucia.aacid.comuntramitacion.objects.Importe;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.converter.ImporteConverter;

@Entity
@Table(name = "AACI_REFORMULA", schema = "AACID_OWNER", catalog = "")
public class Reformula {

  @Id
  @Column(name = "ID_REFORMULA", nullable = false, precision = 0)
  @SequenceGenerator(name = "seqReformula", sequenceName = "AACI_SEQ_REFORMULA", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqReformula")
  private Long id;

  @Column(name = "ID_SOLICITUD", nullable = false, precision = 0)
  private Long idSolicitud;

  @Column(name = "NU_MAXIMO", nullable = false)
  @Convert(converter = ImporteConverter.class)
  private Importe maximo;

  @Column(name = "NU_MINIMO", nullable = false)
  @Convert(converter = ImporteConverter.class)
  private Importe minimo;

  @Column(name = "TX_TIPO", nullable = false, length = 1)
  private String tipo;

  // GETTERS AND SETTERS

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getIdSolicitud() {
    return idSolicitud;
  }

  public void setIdSolicitud(Long idSolicitud) {
    this.idSolicitud = idSolicitud;
  }

  public Importe getMaximo() {
    return maximo;
  }

  public void setMaximo(Importe maximo) {
    this.maximo = maximo;
  }

  public Importe getMinimo() {
    return minimo;
  }

  public void setMinimo(Importe minimo) {
    this.minimo = minimo;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  // EQUALS AND HASHCODE

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Reformula that = (Reformula) o;
    return Objects.equals(id, that.id) && Objects.equals(maximo, that.maximo) && Objects.equals(minimo, that.minimo) && Objects.equals(tipo, that.tipo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, maximo, minimo, tipo);
  }

}
