package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo;

import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "PAE_AGRUPACIONES")
public class PaeAgrupaciones {
  private Long idAgrupacion;
  private String denominacion;
  private String siglas;
  private String inscripcion;
  private String nif;
  private String ongLider;
  private PaeSolicitudes paeSolicitudesByFkSolicitud;

  @Id
  @Column(name = "ID_AGRUPACION")
  public Long getIdAgrupacion() {
    return idAgrupacion;
  }

  public void setIdAgrupacion(final Long idAgrupacion) {
    this.idAgrupacion = idAgrupacion;
  }

  @Basic
  @Column(name = "DENOMINACION")
  public String getDenominacion() {
    return denominacion;
  }

  public void setDenominacion(final String denominacion) {
    this.denominacion = denominacion;
  }

  @Basic
  @Column(name = "SIGLAS")
  public String getSiglas() {
    return siglas;
  }

  public void setSiglas(final String siglas) {
    this.siglas = siglas;
  }

  @Basic
  @Column(name = "INSCRIPCION")
  public String getInscripcion() {
    return inscripcion;
  }

  public void setInscripcion(final String inscripcion) {
    this.inscripcion = inscripcion;
  }

  @Basic
  @Column(name = "NIF")
  public String getNif() {
    return nif;
  }

  public void setNif(final String nif) {
    this.nif = nif;
  }

  @Basic
  @Column(name = "ONG_LIDER")
  public String getOngLider() {
    return ongLider;
  }

  public void setOngLider(final String ongLider) {
    this.ongLider = ongLider;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final PaeAgrupaciones that = (PaeAgrupaciones) o;
    return Objects.equals(idAgrupacion, that.idAgrupacion) && Objects.equals(denominacion, that.denominacion) && Objects.equals(siglas, that.siglas)
        && Objects.equals(inscripcion, that.inscripcion) && Objects.equals(nif, that.nif) && Objects.equals(ongLider, that.ongLider);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idAgrupacion, denominacion, siglas, inscripcion, nif, ongLider);
  }

  @LazyCollection(LazyCollectionOption.FALSE)
  @ManyToOne
  @JoinColumn(name = "FK_SOLICITUD", referencedColumnName = "ID_SOLICITUD")
  public PaeSolicitudes getPaeSolicitudesByFkSolicitud() {
    return paeSolicitudesByFkSolicitud;
  }

  public void setPaeSolicitudesByFkSolicitud(final PaeSolicitudes paeSolicitudesByFkSolicitud) {
    this.paeSolicitudesByFkSolicitud = paeSolicitudesByFkSolicitud;
  }
}
