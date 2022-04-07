package es.juntadeandalucia.aacid.comuntramitacion.dto;

public class DocumentoExpedienteDTO {

  private Long idExpediente;
  private Long idDocumento;
  private byte[] fichero;

  public DocumentoExpedienteDTO() {
  }

  public byte[] getFichero() {
    return fichero;
  }

  public void setFichero(byte[] fichero) {
    this.fichero = fichero;
  }

  public Long getIdExpediente() {
    return idExpediente;
  }

  public void setIdExpediente(Long idExpediente) {
    this.idExpediente = idExpediente;
  }

  public Long getIdDocumento() {
    return idDocumento;
  }

  public void setIdDocumento(Long idDocumento) {
    this.idDocumento = idDocumento;
  }
}
