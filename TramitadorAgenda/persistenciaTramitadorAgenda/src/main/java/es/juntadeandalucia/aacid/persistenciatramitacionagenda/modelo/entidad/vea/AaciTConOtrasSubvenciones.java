package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.vea;

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
@Table(name = "AACI_T_CON_OTRAS_SUBVENCIONES", schema = "AACID_OWNER", catalog = "")
public class AaciTConOtrasSubvenciones {
  private Integer idConOtrSubven;
  private Long fhFecha;
  private String txAdmConcede;
  private Integer nuImporte;
  private Boolean lgMinimis;
  private AaciTSolicitudsubongd aaciTSolicitudsubongdByIdSolicitud;

  @Id
  @Column(name = "ID_CON_OTR_SUBVEN")
  @SequenceGenerator(name = "seqOtrasSubvenciones", sequenceName = "AACI_SEQ_CON_OTRAS_SUB", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqOtrasSubvenciones")
  public Integer getIdConOtrSubven() {
    return idConOtrSubven;
  }

  public void setIdConOtrSubven(final Integer idConOtrSubven) {
    this.idConOtrSubven = idConOtrSubven;
  }

  @Basic
  @Column(name = "FH_FECHA")
  public Long getFhFecha() {
    return fhFecha;
  }

  public void setFhFecha(final Long fhFecha) {
    this.fhFecha = fhFecha;
  }

  @Basic
  @Column(name = "TX_ADM_CONCEDE")
  public String getTxAdmConcede() {
    return txAdmConcede;
  }

  public void setTxAdmConcede(final String txAdmConcede) {
    this.txAdmConcede = txAdmConcede;
  }

  @Basic
  @Column(name = "NU_IMPORTE")
  public Integer getNuImporte() {
    return nuImporte;
  }

  public void setNuImporte(final Integer nuImporte) {
    this.nuImporte = nuImporte;
  }

  @Basic
  @Column(name = "LG_MINIMIS")
  public Boolean getLgMinimis() {
    return lgMinimis;
  }

  public void setLgMinimis(final Boolean lgMinimis) {
    this.lgMinimis = lgMinimis;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final AaciTConOtrasSubvenciones that = (AaciTConOtrasSubvenciones) o;
    return Objects.equals(idConOtrSubven, that.idConOtrSubven) && Objects.equals(fhFecha, that.fhFecha) && Objects.equals(txAdmConcede, that.txAdmConcede)
        && Objects.equals(nuImporte, that.nuImporte) && Objects.equals(lgMinimis, that.lgMinimis);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idConOtrSubven, fhFecha, txAdmConcede, nuImporte, lgMinimis);
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
