package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.IExclusionesSolicitudDAO;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.ExclusionesSolicitud;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.persistence.CustomHibernateDaoSupport;

/**
 * ConvocatoriaRepository class.
 *
 * @author isotrol
 *
 */
@Repository
public class ExclusionesSolicitudDAO extends CustomHibernateDaoSupport implements IExclusionesSolicitudDAO {
  static final String T_EXC_NU_ORDEN = "texcNuOrden";
  static final String EXC_NU_ORDEN = "exclNuOrden";

  @Override
  @Transactional
  public List<Long> obtenerIdsExclusionesSolicitud(final Long idSoli, final Long numTipo, final Long orden) throws TramitacionException {
    try {
      getEntityManager().setFlushMode(FlushModeType.AUTO);
      getEntityManager().clear();
      final CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
      final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
      final Root<ExclusionesSolicitud> root = cq.from(ExclusionesSolicitud.class);
      final Predicate conjuncion = cb.conjunction();
      final Path<Object> exclusion = root.get("aaciExclusionByExclNuId");
      final Path<Object> solicitud = root.get("aaciTSolicitudsubongdBySoliNuId");
      final Path<Object> tipoExclusion = exclusion.get("aaciTipoExclusionByTexcNuId");
      conjuncion.getExpressions().add(cb.equal(solicitud.get("idSolicitud"), idSoli));

      conjuncion.getExpressions().add(cb.equal(tipoExclusion.get(T_EXC_NU_ORDEN), orden));
      conjuncion.getExpressions().add(cb.equal(tipoExclusion.get("texcNuTipo"), numTipo));
      cq.select(exclusion.get("exclNuId"));
      cq.where(conjuncion);
      cq.orderBy(cb.asc(tipoExclusion.get(T_EXC_NU_ORDEN)));
      return getEntityManager().createQuery(cq).getResultList();
    } catch (final Exception e) {
      throw new TramitacionException(
          "Se ha producido un error obtener los id de las exclusiones de la solicitud" + idSoli + " para el tipo y orden " + numTipo + "/" + orden, e);
    }
  }

  @Override
  @Transactional
  public List<Integer> obtenerTodasIdsExclusionesSolicitud(final Long idSoli) throws TramitacionException {
    try {
      getEntityManager().setFlushMode(FlushModeType.AUTO);
      getEntityManager().clear();
      final CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
      final CriteriaQuery<Integer> cq = cb.createQuery(Integer.class);
      final Root<ExclusionesSolicitud> root = cq.from(ExclusionesSolicitud.class);
      final Predicate conjuncion = cb.conjunction();
      final Path<Object> exclusion = root.get("aaciExclusionByExclNuId");
      final Path<Object> solicitud = root.get("aaciTSolicitudsubongdBySoliNuId");
      conjuncion.getExpressions().add(cb.equal(solicitud.get("idSolicitud"), idSoli));
      cq.select(exclusion.get(EXC_NU_ORDEN));
      cq.where(conjuncion);
      cq.orderBy(cb.asc(exclusion.get(EXC_NU_ORDEN)));
      return getEntityManager().createQuery(cq).getResultList();
    } catch (final Exception e) {
      throw new TramitacionException("Se ha producido un error obtener los id de las exclusiones de la solicitud" + idSoli, e);
    }
  }

  @Override
  @Transactional
  public List<Integer> obtenerIdsExclusionesSolicitudByTipo(final Long idSoli, final Integer numTipo) throws TramitacionException {
    try {
      getEntityManager().setFlushMode(FlushModeType.AUTO);
      getEntityManager().clear();
      final CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
      final CriteriaQuery<Integer> cq = cb.createQuery(Integer.class);
      final Root<ExclusionesSolicitud> root = cq.from(ExclusionesSolicitud.class);
      final Predicate conjuncion = cb.conjunction();
      final Path<Object> exclusion = root.get("aaciExclusionByExclNuId");
      final Path<Object> solicitud = root.get("aaciTSolicitudsubongdBySoliNuId");
      final Path<Object> tipoExclusion = exclusion.get("aaciTipoExclusionByTexcNuId");
      conjuncion.getExpressions().add(cb.equal(solicitud.get("idSolicitud"), idSoli));
      if (numTipo != null) {
        conjuncion.getExpressions().add(cb.equal(tipoExclusion.get("texcNuTipo"), numTipo));
      }
      cq.select(exclusion.get(EXC_NU_ORDEN));
      cq.where(conjuncion);
      cq.orderBy(cb.asc(exclusion.get(EXC_NU_ORDEN)));
      return getEntityManager().createQuery(cq).getResultList();
    } catch (final Exception e) {
      throw new TramitacionException("Se ha producido un error obtener los id de las exclusiones de la solicitud" + idSoli + " para el tipo y orden " + numTipo,
          e);
    }
  }

  @Override
  @Transactional
  public int createEntidad(final ExclusionesSolicitud entidad) throws TramitacionException {
    getEntityManager().setFlushMode(FlushModeType.AUTO);
    int resultado = 0;
    try {
      resultado = getEntityManager().createNativeQuery("INSERT INTO AACI_EXCLUSIONES_SOLICITUD (EXCL_NU_ID, SOLI_NU_ID) VALUES (?,?)")
          .setParameter(1, entidad.getAaciExclusionByExclNuId().getExclNuId()).setParameter(2, entidad.getAaciTSolicitudsubongdBySoliNuId().getIdSolicitud())
          .executeUpdate();
      getEntityManager().clear();
    } catch (final Exception e) {
      throw new TramitacionException("Se ha producido un error al guardar la exclusión " + entidad.getAaciExclusionByExclNuId().getExclNuId()
          + " para la solicitud " + entidad.getAaciTSolicitudsubongdBySoliNuId().getIdSolicitud(), e);
    }
    return resultado;
  }

  @Override
  public List<Long> obtenerIdsExclusionesBySoli(final Long idSolicitud, final Long tipoExcl) throws TramitacionException {
    getEntityManager().setFlushMode(FlushModeType.AUTO);
    getEntityManager().clear();
    try {
      final CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
      final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
      final Root<ExclusionesSolicitud> root = cq.from(ExclusionesSolicitud.class);
      final Predicate conjuncion = cb.conjunction();
      final Path<Object> exclusion = root.get("aaciExclusionByExclNuId");
      final Path<Object> solicitud = root.get("aaciTSolicitudsubongdBySoliNuId");
      final Path<Object> tipoExclusion = exclusion.get("aaciTipoExclusionByTexcNuId");
      conjuncion.getExpressions().add(cb.equal(solicitud.get("idSolicitud"), idSolicitud));
      conjuncion.getExpressions().add(cb.equal(tipoExclusion.get("texcNuTipo"), tipoExcl));
      cq.select(exclusion.get("exclNuId"));
      cq.where(conjuncion);
      cq.orderBy(cb.asc(tipoExclusion.get(T_EXC_NU_ORDEN)));
      return getEntityManager().createQuery(cq).getResultList();
    } catch (final Exception e) {
      throw new TramitacionException("Se ha producido un error al obtener las exclusiones de la solicitud " + idSolicitud, e);
    }
  }

  @Override
  @Transactional
  public int eliminarExclusionSolicitud(final Long idExcl, final Long idSolicitud) throws TramitacionException {
    getEntityManager().setFlushMode(FlushModeType.AUTO);
    int resultado = 0;
    try {
      resultado = getEntityManager().createNativeQuery("delete from AACI_EXCLUSIONES_SOLICITUD where EXCL_NU_ID = ? and SOLI_NU_ID = ? ")
          .setParameter(1, idExcl).setParameter(2, idSolicitud).executeUpdate();
      getEntityManager().clear();
    } catch (final Exception e) {
      throw new TramitacionException("Se ha producido un error al eliminar la exclusión " + idExcl + " de la solicitud " + idSolicitud, e);
    }
    return resultado;

  }

  @Override
  @Transactional
  public BigDecimal existsExlusionesBySolicitud(final Long idSolicitud) throws TramitacionException {
    getEntityManager().setFlushMode(FlushModeType.AUTO);
    BigDecimal resultado = null;
    try {
      resultado = (BigDecimal) getEntityManager().createNativeQuery("SELECT COUNT(SOLI_NU_ID) FROM AACI_EXCLUSIONES_SOLICITUD WHERE SOLI_NU_ID = ?")
          .setParameter(1, idSolicitud).getSingleResult();
      getEntityManager().clear();
    } catch (final Exception e) {
      throw new TramitacionException("Se ha producido un error al obtener el total de exlusiones de la solicitud " + idSolicitud, e);
    }
    return resultado;
  }
}
