package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao;

import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.DocumentoExpediente;

public interface IDocumentoExpedienteDAO {

  void saveOrUpdateDocumentoExpediente(DocumentoExpediente documento) throws TramitacionException;

  void deleteDocumentoExpediente(final DocumentoExpediente docExp);

  DocumentoExpediente getDocumentoExpedienteByIdDocExpTrewa(Long idExpTrewa);
}
