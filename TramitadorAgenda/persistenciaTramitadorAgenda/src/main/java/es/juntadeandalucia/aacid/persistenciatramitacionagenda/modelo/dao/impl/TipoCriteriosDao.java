package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.ITipoCriteriosDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.TiposCriterios;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.persistence.CustomHibernateDaoSupport;

public class TipoCriteriosDao extends CustomHibernateDaoSupport implements ITipoCriteriosDao {

  @Override
  public Long obtenerIdTipoCriterioByConvocatoriaFinalidadNombre(final String finalidad, final String nombreTipoCriterio) throws TramitacionException {
    try {
      return getEntityManager().createQuery(
          "SELECT new java.lang.Long(tc.tcriNuId) FROM TiposCriterios tc left JOIN tc.aaciFinalidad f WHERE tc.tcriTxNombre =: nombreTipoCriterio  AND f.finaCoCodigo =: finalidad",
          Long.class).setParameter("nombreTipoCriterio", nombreTipoCriterio).setParameter("finalidad", finalidad).getResultList().stream().findFirst()
          .orElse(null);
    } catch (final Exception e) {
      throw new TramitacionException(e.getMessage(), e);
    }
  }

  @Override
  public String obtenerNombreTipoCriterioByidCriterio (Long idCriterio) throws TramitacionException {
    try {
      return getEntityManager().createQuery(
                      "SELECT new java.lang.String(tc.tcriTxNombre) FROM TiposCriterios tc left JOIN tc.aaciCriteriosByTcriNuId cri WHERE cri.critNuId =: idCriterio",
                      String.class).setParameter("idCriterio", idCriterio).getResultList().stream().findFirst()
              .orElse(null);
    } catch (final Exception e) {
      throw new TramitacionException(e.getMessage(), e);
    }
  }

  @Override
  public TiposCriterios obtenerTipoCriterioByNombreYFinalidad(final String nombreTipoCriterio, final String codFinalidad) {
    final CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
    final CriteriaQuery<TiposCriterios> cq = cb.createQuery(TiposCriterios.class);
    final Root<TiposCriterios> root = cq.from(TiposCriterios.class);
    cq.select(root);
    final Predicate conjuncion = cb.conjunction();
    final Path<Object> finalidad = root.get("aaciFinalidad");
    conjuncion.getExpressions().add(cb.equal(root.get("tcriTxNombre"), nombreTipoCriterio));
    conjuncion.getExpressions().add(cb.equal(finalidad.get("finaCoCodigo"), codFinalidad));
    cq.where(conjuncion);

    return getEntityManager().createQuery(cq).getResultList().stream().findFirst().orElse(null);
  }
}
