package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.IDocumentoExpedienteDAO;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.DocumentoExpediente;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.persistence.CustomHibernateDaoSupport;

@Repository
public class DocumentoExpedienteDAO extends CustomHibernateDaoSupport implements IDocumentoExpedienteDAO {

  @Override
  @Transactional
  public void saveOrUpdateDocumentoExpediente(DocumentoExpediente documento) throws TramitacionException {
    try {
      getEntityManager().merge(documento);
    } catch (Exception e) {
      throw new TramitacionException("Se ha producido un error al guardar o actualizar el documento del expediente. Causa: " + e.getMessage(), e);
    }
  }

  @Override
  public void deleteDocumentoExpediente(final DocumentoExpediente docExp) {
    getEntityManager().remove(docExp);
  }

  @Override
  public DocumentoExpediente getDocumentoExpedienteByIdDocExpTrewa(Long idExpTrewa) {
    CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
    CriteriaQuery<DocumentoExpediente> cq = cb.createQuery(DocumentoExpediente.class);
    Root<DocumentoExpediente> root = cq.from(DocumentoExpediente.class);
    ParameterExpression<Long> p = cb.parameter(Long.class);
    cq.select(root).where(cb.equal(root.get("doexXDoc"), p));

    return getEntityManager().createQuery(cq).setParameter(p, idExpTrewa).getResultList().stream().findFirst().orElse(null);

  }
}
