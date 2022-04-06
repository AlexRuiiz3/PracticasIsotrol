package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.impl;

import javax.transaction.Transactional;

import es.juntadeandalucia.aacid.comuntramitacion.dto.DocumentoExpedienteDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.converter.DocumentoExpedienteConverter;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.impl.DocumentoExpedienteDAO;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.DocumentoExpediente;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IDocumentoExpedienteService;

public class DocumentoExpedienteServiceImpl implements IDocumentoExpedienteService {

  private DocumentoExpedienteDAO documentoExpedienteDao;

  @Override
  public void guardarDocumentoExpediente(DocumentoExpedienteDTO doc) throws TramitacionException {
    DocumentoExpediente docExp = DocumentoExpedienteConverter.convertDocumentoExpedienteDTOToDao(doc);
    documentoExpedienteDao.saveOrUpdateDocumentoExpediente(docExp);
  }

  @Override
  @Transactional
  public void borrarDocumentoExpedienteByidDoc(Long idDoc) {
    DocumentoExpediente docExp = this.obtenerDocumentoExpediente(idDoc);
    if (docExp != null) {
      documentoExpedienteDao.deleteDocumentoExpediente(docExp);
    }
  }

  @Override
  public DocumentoExpediente obtenerDocumentoExpediente(Long idDocExpediente) {
    return documentoExpedienteDao.getDocumentoExpedienteByIdDocExpTrewa(idDocExpediente);
  }

  public DocumentoExpedienteDAO getDocumentoExpedienteDao() {
    return documentoExpedienteDao;
  }

  public void setDocumentoExpedienteDao(DocumentoExpedienteDAO documentoExpedienteDao) {
    this.documentoExpedienteDao = documentoExpedienteDao;
  }
}
