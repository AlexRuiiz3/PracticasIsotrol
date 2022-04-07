package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service;

import es.juntadeandalucia.aacid.comuntramitacion.dto.DocumentoExpedienteDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.DocumentoExpediente;

public interface IDocumentoExpedienteService {

  void guardarDocumentoExpediente(DocumentoExpedienteDTO doc) throws TramitacionException;

  void borrarDocumentoExpedienteByidDoc(Long idDoc);

  DocumentoExpediente obtenerDocumentoExpediente(Long idDocExpediente);
}
