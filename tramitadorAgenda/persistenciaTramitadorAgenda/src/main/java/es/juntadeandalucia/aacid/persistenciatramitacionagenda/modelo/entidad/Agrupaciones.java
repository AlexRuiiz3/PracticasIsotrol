package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad;

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
@Table(name = "AACI_AGRUPACIONES", schema = "AACID_OWNER", catalog = "")
public class Agrupaciones {
  private Long agrIdAgrupacion;
  private String agrDenominacion;
  private String agrSiglas;
  private String agrInscripcion;
  private String agrNif;
  private String agrOngLider;
  private Solicitud idSolicitud;

  @Id
  @SequenceGenerator(name = "seqAgrupaciones", sequenceName = "AACI_SEQ_AGRUPACIONES", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqAgrupaciones")
  @Column(name = "AGRU_NU_ID")
  public Long getAgrIdAgrupacion() {
    return agrIdAgrupacion;
  }

  public void setAgrIdAgrupacion(Long agrIdAgrupacion) {
    this.agrIdAgrupacion = agrIdAgrupacion;
  }

  @Basic
  @Column(name = "AGRU_LI_DENOMINACION")
  public String getAgrDenominacion() {
    return agrDenominacion;
  }

  public void setAgrDenominacion(String agrDenominacion) {
    this.agrDenominacion = agrDenominacion;
  }

  @Basic
  @Column(name = "AGRU_LI_SIGLAS")
  public String getAgrSiglas() {
    return agrSiglas;
  }

  public void setAgrSiglas(String tagrSiglas) {
    this.agrSiglas = tagrSiglas;
  }

  @Basic
  @Column(name = "AGRU_CO_INSCRIPCION")
  public String getAgrInscripcion() {
    return agrInscripcion;
  }

  public void setAgrInscripcion(String agrInscripcion) {
    this.agrInscripcion = agrInscripcion;
  }

  @Basic
  @Column(name = "AGRU_LI_NIF")
  public String getAgrNif() {
    return agrNif;
  }

  public void setAgrNif(String agrNif) {
    this.agrNif = agrNif;
  }

  @Basic
  @Column(name = "AGRU_LI_ONGD_LIDER")
  public String getAgrOngLider() {
    return agrOngLider;
  }

  public void setAgrOngLider(String agrOngLider) {
    this.agrOngLider = agrOngLider;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Agrupaciones that = (Agrupaciones) o;
    return Objects.equals(agrIdAgrupacion, that.agrIdAgrupacion) && Objects.equals(agrDenominacion, that.agrDenominacion)
        && Objects.equals(agrSiglas, that.agrSiglas) && Objects.equals(agrInscripcion, that.agrInscripcion) && Objects.equals(agrNif, that.agrNif)
        && Objects.equals(agrOngLider, that.agrOngLider);
  }

  @Override
  public int hashCode() {
    return Objects.hash(agrIdAgrupacion, agrDenominacion, agrSiglas, agrInscripcion, agrNif, agrOngLider);
  }

  @ManyToOne
  @JoinColumn(name = "ID_SOLICITUD", referencedColumnName = "ID_SOLICITUD")
  public Solicitud getIdSolicitud() {
    return idSolicitud;
  }

  public void setIdSolicitud(Solicitud idSolicitud) {
    this.idSolicitud = idSolicitud;
  }
}
