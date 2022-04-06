package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.ICapacidadGestionDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.CapacidadGestion;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.persistence.CustomHibernateDaoSupport;

@Repository
public class CapacidadGestionDao extends CustomHibernateDaoSupport implements ICapacidadGestionDao {

  @Override
  public List<CapacidadGestion> obtenerCapacidadesGestion(final CapacidadGestion filtro) {
    final CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
    final CriteriaQuery<CapacidadGestion> cq = cb.createQuery(CapacidadGestion.class);
    final Root<CapacidadGestion> root = cq.from(CapacidadGestion.class);
    cq.select(root);
    final Predicate conjuncion = cb.conjunction();
    if (filtro.getTmcgNuId() != null) {
      conjuncion.getExpressions().add(cb.equal(root.get("tmcgNuId"), filtro.getTmcgNuId()));
    }
    if (filtro.getTmcgTxCif() != null) {
      conjuncion.getExpressions().add(cb.equal(cb.upper(root.get("tmcgTxCif")), filtro.getTmcgTxCif().toUpperCase()));
    }
    if (filtro.getTmcgTxEnt() != null) {
      conjuncion.getExpressions().add(cb.equal(cb.upper(root.get("tmcgTxEnt")), filtro.getTmcgTxEnt().toUpperCase()));
    }
    if (filtro.getTmcgNuAnio() != null){
      conjuncion.getExpressions().add(cb.equal(root.get("tmcgNuAnio"), filtro.getTmcgNuAnio()));
    }
    cq.where(conjuncion);

    return getEntityManager().createQuery(cq).getResultList();
  }
}