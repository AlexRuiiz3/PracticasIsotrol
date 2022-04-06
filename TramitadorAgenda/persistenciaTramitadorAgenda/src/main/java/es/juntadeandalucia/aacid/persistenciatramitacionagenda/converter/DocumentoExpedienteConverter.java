package es.juntadeandalucia.aacid.persistenciatramitacionagenda.converter;

import es.juntadeandalucia.aacid.comuntramitacion.dto.DocumentoExpedienteDTO;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.DocumentoExpediente;

public class DocumentoExpedienteConverter {

  public static DocumentoExpedienteDTO convertDocumentosExpedienteToDTO(DocumentoExpediente documentoExpediente) {
    DocumentoExpedienteDTO documentoExpedienteDTO = new DocumentoExpedienteDTO();
    documentoExpedienteDTO.setIdExpediente(documentoExpediente.getDoexXExpe());
    documentoExpedienteDTO.setIdDocumento(documentoExpediente.getDoexXExpe());
    documentoExpedienteDTO.setFichero(documentoExpediente.getDoexBFichero());
    return documentoExpedienteDTO;
  }

  public static DocumentoExpediente convertDocumentoExpedienteDTOToDao(DocumentoExpedienteDTO documentoExpedienteDTO) {
    DocumentoExpediente documentoExpediente = new DocumentoExpediente();
    documentoExpediente.setDoexXDoc(documentoExpedienteDTO.getIdDocumento());
    documentoExpediente.setDoexBFichero(documentoExpedienteDTO.getFichero());
    documentoExpediente.setDoexXExpe(documentoExpedienteDTO.getIdExpediente());
    return documentoExpediente;
  }
}
