package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.vea;

import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "AACI_AGRUPACIONES", schema = "AACID_OWNER", catalog = "")
public class AaciAgrupaciones {
  private Long agruNuId;
  private String agruLiDenominacion;
  private String agruLiSiglas;
  private String agruCoInscripcion;
  private String agruLiNif;
  private String agruLiOngdLider;
  private AaciTSolicitudsubongd aaciTSolicitudsubongdByIdSolicitud;

  @Id
  @Column(name = "AGRU_NU_ID")
  public Long getAgruNuId() {
    return agruNuId;
  }

  public void setAgruNuId(final Long agruNuId) {
    this.agruNuId = agruNuId;
  }

  @Basic
  @Column(name = "AGRU_LI_DENOMINACION")
  public String getAgruLiDenominacion() {
    return agruLiDenominacion;
  }

  public void setAgruLiDenominacion(final String agruLiDenominacion) {
    this.agruLiDenominacion = agruLiDenominacion;
  }

  @Basic
  @Column(name = "AGRU_LI_SIGLAS")
  public String getAgruLiSiglas() {
    return agruLiSiglas;
  }

  public void setAgruLiSiglas(final String agruLiSiglas) {
    this.agruLiSiglas = agruLiSiglas;
  }

  @Basic
  @Column(name = "AGRU_CO_INSCRIPCION")
  public String getAgruCoInscripcion() {
    return agruCoInscripcion;
  }

  public void setAgruCoInscripcion(final String agruCoInscripcion) {
    this.agruCoInscripcion = agruCoInscripcion;
  }

  @Basic
  @Column(name = "AGRU_LI_NIF")
  public String getAgruLiNif() {
    return agruLiNif;
  }

  public void setAgruLiNif(final String agruLiNif) {
    this.agruLiNif = agruLiNif;
  }

  @Basic
  @Column(name = "AGRU_LI_ONGD_LIDER")
  public String getAgruLiOngdLider() {
    return agruLiOngdLider;
  }

  public void setAgruLiOngdLider(final String agruLiOngdLider) {
    this.agruLiOngdLider = agruLiOngdLider;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final AaciAgrupaciones that = (AaciAgrupaciones) o;
    return Objects.equals(agruNuId, that.agruNuId) && Objects.equals(agruLiDenominacion, that.agruLiDenominacion)
        && Objects.equals(agruLiSiglas, that.agruLiSiglas) && Objects.equals(agruCoInscripcion, that.agruCoInscripcion)
        && Objects.equals(agruLiNif, that.agruLiNif) && Objects.equals(agruLiOngdLider, that.agruLiOngdLider);
  }

  @Override
  public int hashCode() {
    return Objects.hash(agruNuId, agruLiDenominacion, agruLiSiglas, agruCoInscripcion, agruLiNif, agruLiOngdLider);
  }

  @ManyToOne
  @JoinColumn(name = "ID_SOLICITUD", referencedColumnName = "ID_SOLICITUD")
  public AaciTSolicitudsubongd getAaciTSolicitudsubongdByIdSolicitud() {
    return aaciTSolicitudsubongdByIdSolicitud;
  }

  public void setAaciTSolicitudsubongdByIdSolicitud(final AaciTSolicitudsubongd aaciTSolicitudsubongdByIdSolicitud) {
    this.aaciTSolicitudsubongdByIdSolicitud = aaciTSolicitudsubongdByIdSolicitud;
  }
}
