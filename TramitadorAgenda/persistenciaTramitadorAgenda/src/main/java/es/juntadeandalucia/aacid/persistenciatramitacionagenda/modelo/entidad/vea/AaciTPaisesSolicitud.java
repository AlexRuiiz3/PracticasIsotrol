package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.vea;

import java.util.Objects;

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
@Table(name = "AACI_T_PAISES_SOLICITUD", schema = "AACID_OWNER", catalog = "")
public class AaciTPaisesSolicitud {
  private Integer idPaisSolicitud;
  private AaciTSolicitudsubongd aaciTSolicitudsubongdByIdSolicitud;
  private AaciTPaises aaciTPaisesByIdPais;

  public AaciTPaisesSolicitud() {
  }

  public AaciTPaisesSolicitud(final AaciTSolicitudsubongd aaciTSolicitudsubongdByIdSolicitud, final AaciTPaises aaciTPaisesByIdPais) {
    this.aaciTSolicitudsubongdByIdSolicitud = aaciTSolicitudsubongdByIdSolicitud;
    this.aaciTPaisesByIdPais = aaciTPaisesByIdPais;
  }

  @Id
  @Column(name = "ID_PAIS_SOLICITUD")
  @SequenceGenerator(name = "seqPaisSolicitud", sequenceName = "AACI_SEQ_PAISES_SOLICITUD", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqPaisSolicitud")
  public Integer getIdPaisSolicitud() {
    return idPaisSolicitud;
  }

  public void setIdPaisSolicitud(final Integer idPaisSolicitud) {
    this.idPaisSolicitud = idPaisSolicitud;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final AaciTPaisesSolicitud that = (AaciTPaisesSolicitud) o;
    return Objects.equals(idPaisSolicitud, that.idPaisSolicitud);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idPaisSolicitud);
  }

  @ManyToOne
  @JoinColumn(name = "ID_SOLICITUD", referencedColumnName = "ID_SOLICITUD")
  public AaciTSolicitudsubongd getAaciTSolicitudsubongdByIdSolicitud() {
    return aaciTSolicitudsubongdByIdSolicitud;
  }

  public void setAaciTSolicitudsubongdByIdSolicitud(final AaciTSolicitudsubongd aaciTSolicitudsubongdByIdSolicitud) {
    this.aaciTSolicitudsubongdByIdSolicitud = aaciTSolicitudsubongdByIdSolicitud;
  }

  @ManyToOne
  @JoinColumn(name = "ID_PAIS", referencedColumnName = "ID_PAIS")
  public AaciTPaises getAaciTPaisesByIdPais() {
    return aaciTPaisesByIdPais;
  }

  public void setAaciTPaisesByIdPais(final AaciTPaises aaciTPaisesByIdPais) {
    this.aaciTPaisesByIdPais = aaciTPaisesByIdPais;
  }
}
