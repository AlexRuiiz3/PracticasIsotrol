package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.ITipoEntidadParticipanteDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.TipoEntidadParticipante;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.persistence.CustomHibernateDaoSupport;

public class TipoEntidadParticipanteDao extends CustomHibernateDaoSupport implements ITipoEntidadParticipanteDao {

  @Override
  public TipoEntidadParticipante obtenerTipoEntidadParticipanteByCodigo(final String codigo) {
    final CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
    final CriteriaQuery<TipoEntidadParticipante> cq = cb.createQuery(TipoEntidadParticipante.class);
    final Root<TipoEntidadParticipante> root = cq.from(TipoEntidadParticipante.class);
    cq.select(root);
    final Predicate conjuncion = cb.conjunction();
    conjuncion.getExpressions().add(cb.equal(root.get("tepaTxCodigo"), codigo));
    cq.where(conjuncion);

    return getEntityManager().createQuery(cq).getResultList().stream().findFirst().orElse(null);
  }
}
