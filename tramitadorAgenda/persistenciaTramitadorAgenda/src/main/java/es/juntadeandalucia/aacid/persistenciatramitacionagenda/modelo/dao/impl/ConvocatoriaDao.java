package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import es.juntadeandalucia.aacid.comuntramitacion.dto.ConvocatoriaDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.IConvocatoriaDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Convocatoria;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.TipoConvocatoria;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.persistence.CustomHibernateDaoSupport;

/**
 * ConvocatoriaRepository class.
 *
 * @author isotrol
 *
 */
@Repository
public class ConvocatoriaDao extends CustomHibernateDaoSupport implements IConvocatoriaDao {

  @Override
  @Transactional
  public Convocatoria obtenerDatosConvocatoriaPorExpediente(final String idExp) throws TramitacionException {
    Convocatoria convocatoria = null;
    try {
      final CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
      final CriteriaQuery<Convocatoria> criteriaQuery = cb.createQuery(Convocatoria.class);
      final Root<Convocatoria> rConv = criteriaQuery.from(Convocatoria.class);
      final Predicate conjunction = cb.conjunction();
      if (idExp != null && !idExp.equals("")) {
        conjunction.getExpressions().add(cb.equal((rConv.get("convXExpe").as(Long.class)), Long.valueOf(idExp)));
      }

      if (CollectionUtils.isNotEmpty(conjunction.getExpressions())) {
        criteriaQuery.where(conjunction);
      }
      convocatoria = getEntityManager().createQuery(criteriaQuery).getSingleResult();
    } catch (final NoResultException e) {
      return convocatoria;
    } catch (final Exception e) {
      throw new TramitacionException("Se ha producido un error al obtener los datos de la convocatoria", e);
    }
    return convocatoria;
  }

  /**
   * Obtiene el tipo de convocatoria
   *
   * @param procedimiento
   *          procedimiento
   * @return TipoConvocatoriaDao tipoConvocatoriaDao
   * @throws Exception
   *           exception
   */
  @Override
  @Transactional
  public TipoConvocatoria buscarIdTipConvPorAbreviatura(final String denominacion) throws TramitacionException {
    TipoConvocatoria tipoConvocatoria = null;
    try {
      final CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
      final CriteriaQuery<TipoConvocatoria> criteriaQuery = cb.createQuery(TipoConvocatoria.class);
      final Root<TipoConvocatoria> tipConv = criteriaQuery.from(TipoConvocatoria.class);
      final Predicate conjunction = cb.conjunction();

      if (denominacion != null && !denominacion.equals("")) {
        conjunction.getExpressions().add(cb.like(cb.upper(tipConv.get("tconvLiDenominacion").as(String.class)), "%" + denominacion.toUpperCase() + "%"));
      }
      if (CollectionUtils.isNotEmpty(conjunction.getExpressions())) {
        criteriaQuery.where(conjunction);
      }
      tipoConvocatoria = getEntityManager().createQuery(criteriaQuery).getSingleResult();
    } catch (final Exception e) {
      throw new TramitacionException(e.getMessage(), e);
    }
    return tipoConvocatoria;
  }

  /**
   * Crea o modifica una convocatoria
   *
   * @param Convocatoria
   *          convocatoriaDao
   * @return TipoConvocatoriaDao tipoConvocatoriaDao
   * @throws Exception
   *           exception
   */
  @Override
  @Transactional
  public Convocatoria guardarOmodificarConvocatoria(Convocatoria convocatoria) throws TramitacionException {
    try {
      convocatoria = getEntityManager().merge(convocatoria);
    } catch (final Exception e) {
      throw new TramitacionException(e.getMessage(), e);
    }

    return convocatoria;
  }

  @Override
  public List<Convocatoria> obtenerConvocatoriasByFiltro(final ConvocatoriaDTO filtro) {
    final CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
    final CriteriaQuery<Convocatoria> criteriaQuery = cb.createQuery(Convocatoria.class);
    final Root<Convocatoria> conv = criteriaQuery.from(Convocatoria.class);
    final Predicate conjunction = cb.conjunction();

    if (StringUtils.isNotBlank(filtro.getAbreviatura())) {
      conjunction.getExpressions().add(cb.equal(conv.get("convLiAbrevProcedimiento"), filtro.getAbreviatura()));
    }
    if (filtro.getAnio() != null) {
      conjunction.getExpressions().add(cb.equal(conv.get("convNuAnio"), filtro.getAnio()));
    }
    if (filtro.getFhInicio() != null) {
      conjunction.getExpressions().add(cb.lessThanOrEqualTo(conv.get("convFhInicio"), filtro.getFhInicio()));
    }
    if (filtro.getFhInicio() != null) {
      conjunction.getExpressions().add(cb.greaterThanOrEqualTo(conv.get("convFhFin"), filtro.getFhFin()));
    }
    if (CollectionUtils.isNotEmpty(conjunction.getExpressions())) {
      criteriaQuery.where(conjunction);
    }
    return getEntityManager().createQuery(criteriaQuery).getResultList();
  }
}
