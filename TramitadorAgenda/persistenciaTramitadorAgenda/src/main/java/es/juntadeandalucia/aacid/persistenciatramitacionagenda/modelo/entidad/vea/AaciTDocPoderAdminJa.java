package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.vea;

import java.util.Date;
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
@Table(name = "AACI_T_DOC_PODER_ADMIN_JA", schema = "AACID_OWNER", catalog = "")
public class AaciTDocPoderAdminJa {
  private Integer idDocPoderAdminJa;
  private String txConsejeriaYOrgano;
  private Date fhFechaPresent;
  private String txProcedEmitio;
  private String txNomDocumento;
  private AaciTSolicitudsubongd aaciTSolicitudsubongdByIdSolicitud;

  @Id
  @Column(name = "ID_DOC_PODER_ADMIN_JA")
  @SequenceGenerator(name = "seqDocPoderJA", sequenceName = "AACI_SEQ_DOCS_PODER_JA", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqDocPoderJA")
  public Integer getIdDocPoderAdminJa() {
    return idDocPoderAdminJa;
  }

  public void setIdDocPoderAdminJa(final Integer idDocPoderAdminJa) {
    this.idDocPoderAdminJa = idDocPoderAdminJa;
  }

  @Basic
  @Column(name = "TX_CONSEJERIA_Y_ORGANO")
  public String getTxConsejeriaYOrgano() {
    return txConsejeriaYOrgano;
  }

  public void setTxConsejeriaYOrgano(final String txConsejeriaYOrgano) {
    this.txConsejeriaYOrgano = txConsejeriaYOrgano;
  }

  @Basic
  @Column(name = "FH_FECHA_PRESENT")
  public Date getFhFechaPresent() {
    return fhFechaPresent;
  }

  public void setFhFechaPresent(final Date fhFechaPresent) {
    this.fhFechaPresent = fhFechaPresent;
  }

  @Basic
  @Column(name = "TX_PROCED_EMITIO")
  public String getTxProcedEmitio() {
    return txProcedEmitio;
  }

  public void setTxProcedEmitio(final String txProcedEmitio) {
    this.txProcedEmitio = txProcedEmitio;
  }

  @Basic
  @Column(name = "TX_NOM_DOCUMENTO")
  public String getTxNomDocumento() {
    return txNomDocumento;
  }

  public void setTxNomDocumento(final String txNomDocumento) {
    this.txNomDocumento = txNomDocumento;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final AaciTDocPoderAdminJa that = (AaciTDocPoderAdminJa) o;
    return Objects.equals(idDocPoderAdminJa, that.idDocPoderAdminJa) && Objects.equals(txConsejeriaYOrgano, that.txConsejeriaYOrgano)
        && Objects.equals(fhFechaPresent, that.fhFechaPresent) && Objects.equals(txProcedEmitio, that.txProcedEmitio)
        && Objects.equals(txNomDocumento, that.txNomDocumento);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idDocPoderAdminJa, txConsejeriaYOrgano, fhFechaPresent, txProcedEmitio, txNomDocumento);
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
