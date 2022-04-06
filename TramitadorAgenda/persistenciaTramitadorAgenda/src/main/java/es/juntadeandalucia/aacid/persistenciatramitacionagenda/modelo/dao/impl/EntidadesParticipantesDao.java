package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.IEntidadParticipanteDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.EntidadesParticipantes;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.persistence.CustomHibernateDaoSupport;

public class EntidadesParticipantesDao extends CustomHibernateDaoSupport implements IEntidadParticipanteDao {

  @Override
  public EntidadesParticipantes saveOrUpdateEntidadParticipante(final EntidadesParticipantes entidad) throws TramitacionException {
    try {
      return getEntityManager().merge(entidad);
    } catch (final Exception e) {
      throw new TramitacionException("Se ha producido un error al guardar o actualizar la contraparte. Causa: " + e.getMessage(), e);
    }
  }

  @Override
  public List<EntidadesParticipantes> obtenerEntidadesParticipantesBySolicitud(final Long idSolicitud) {
    final CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
    final CriteriaQuery<EntidadesParticipantes> cq = cb.createQuery(EntidadesParticipantes.class);
    final Root<EntidadesParticipantes> root = cq.from(EntidadesParticipantes.class);
    cq.select(root);
    final Predicate conjuncion = cb.conjunction();
    final Path<Object> solicitud = root.get("aaciTSolicitudsubongdByIdSolicitud");
    conjuncion.getExpressions().add(cb.equal(solicitud.get("idSolicitud"), idSolicitud));
    cq.where(conjuncion);

    return getEntityManager().createQuery(cq).getResultList();
  }

}
