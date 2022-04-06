package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad;

import java.util.Arrays;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "AACI_T_DOCUMENTO_EXPEDIENTE", schema = "AACID_OWNER", catalog = "")
public class DocumentoExpediente {
  private Long doexNuId;
  private Long doexXExpe;
  private Long doexXDoc;
  private byte[] doexBFichero;

  @Id
  @Column(name = "DOEX_NU_ID")
  @SequenceGenerator(name = "seqDocumentoExpediente", sequenceName = "AACI_SEQ_DOCUMENTO_EXPEDIENTE", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqDocumentoExpediente")
  public Long getDoexNuId() {
    return this.doexNuId;
  }

  public void setDoexNuId(Long doexNuId) {
    this.doexNuId = doexNuId;
  }

  @Basic
  @Column(name = "DOEX_X_EXPE")
  public Long getDoexXExpe() {
    return this.doexXExpe;
  }

  public void setDoexXExpe(Long doexXExpe) {
    this.doexXExpe = doexXExpe;
  }

  @Basic
  @Column(name = "DOEX_X_DOC")
  public Long getDoexXDoc() {
    return this.doexXDoc;
  }

  public void setDoexXDoc(Long doexXDoc) {
    this.doexXDoc = doexXDoc;
  }

  @Lob
  @Basic
  @Column(name = "DOEX_B_FICHERO")
  public byte[] getDoexBFichero() {
    return this.doexBFichero;
  }

  public void setDoexBFichero(byte[] doexBFichero) {
    this.doexBFichero = doexBFichero;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DocumentoExpediente that = (DocumentoExpediente) o;
    return Objects.equals(this.doexNuId, that.doexNuId) && Objects.equals(this.doexXExpe, that.doexXExpe) && Objects.equals(this.doexXDoc, that.doexXDoc)
        && Arrays.equals(this.doexBFichero, that.doexBFichero);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(this.doexNuId, this.doexXExpe, this.doexXDoc);
    result = 31 * result + Arrays.hashCode(this.doexBFichero);
    return result;
  }
}
