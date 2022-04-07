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
@Table(name = "AACI_T_SOL_OTRAS_SUBVENCIONES", schema = "AACID_OWNER", catalog = "")
public class AaciTSolOtrasSubvenciones {
  private Integer idSolOtrSubven;
  private Long fhFecha;
  private String txAdmSolicita;
  private Integer nuImporte;
  private Boolean lgMinimis;
  private AaciTSolicitudsubongd aaciTSolicitudsubongdByIdSolicitud;

  @Id
  @Column(name = "ID_SOL_OTR_SUBVEN")
  @SequenceGenerator(name = "seqSolOtrasSub", sequenceName = "AACI_SEQ_SOL_OTRAS_SUB", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqSolOtrasSub")
  public Integer getIdSolOtrSubven() {
    return idSolOtrSubven;
  }

  public void setIdSolOtrSubven(final Integer idSolOtrSubven) {
    this.idSolOtrSubven = idSolOtrSubven;
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
  @Column(name = "TX_ADM_SOLICITA")
  public String getTxAdmSolicita() {
    return txAdmSolicita;
  }

  public void setTxAdmSolicita(final String txAdmSolicita) {
    this.txAdmSolicita = txAdmSolicita;
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
    final AaciTSolOtrasSubvenciones that = (AaciTSolOtrasSubvenciones) o;
    return Objects.equals(idSolOtrSubven, that.idSolOtrSubven) && Objects.equals(fhFecha, that.fhFecha) && Objects.equals(txAdmSolicita, that.txAdmSolicita)
        && Objects.equals(nuImporte, that.nuImporte) && Objects.equals(lgMinimis, that.lgMinimis);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idSolOtrSubven, fhFecha, txAdmSolicita, nuImporte, lgMinimis);
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
