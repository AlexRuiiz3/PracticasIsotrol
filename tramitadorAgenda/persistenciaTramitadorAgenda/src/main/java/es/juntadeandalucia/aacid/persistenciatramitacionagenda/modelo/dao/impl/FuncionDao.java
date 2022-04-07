package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.IFuncionDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Funcion;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.persistence.CustomHibernateDaoSupport;

public class FuncionDao extends CustomHibernateDaoSupport implements IFuncionDao {

  @Override
  public Funcion obtenerFuncionByCodigo(final String codigo) {
    final CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
    final CriteriaQuery<Funcion> cq = cb.createQuery(Funcion.class);
    final Root<Funcion> root = cq.from(Funcion.class);
    cq.select(root);
    final Predicate conjuncion = cb.conjunction();
    conjuncion.getExpressions().add(cb.equal(root.get("funcTxCodigo"), codigo));
    cq.where(conjuncion);

    return getEntityManager().createQuery(cq).getResultList().stream().findFirst().orElse(null);
  }
}
