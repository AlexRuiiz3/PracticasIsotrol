package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.comuntramitacion.utils.UtilidadesNumero;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.IExclusionDAO;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Exclusion;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.TExclusionesSolicitud;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.persistence.CustomHibernateDaoSupport;
import es.juntadeandalucia.plataforma.comunes.excepciones.ArchitectureException;

/**
 * ConvocatoriaRepository class.
 *
 * @author isotrol
 *
 */
@Repository
public class ExclusionDAO extends CustomHibernateDaoSupport implements IExclusionDAO {

  @Override
  @Transactional
  public List<Exclusion> obtenerExclusionesByOrdenTipo(final String strTipoConv, final Long numTipo, final Long orden, final Long anio) {
    final CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
    final CriteriaQuery<Exclusion> cq = cb.createQuery(Exclusion.class);
    final Root<Exclusion> root = cq.from(Exclusion.class);
    cq.select(root);
    final Predicate conjuncion = cb.conjunction();
    final Path<Object> tipoExclusion = root.get("aaciTipoExclusionByTexcNuId");
    final Path<Object> tipoConv = tipoExclusion.get("aaciTTipoConvByTconvNuId");
    conjuncion.getExpressions().add(cb.equal(tipoConv.get("tconvLiDenominacion"), strTipoConv));
    conjuncion.getExpressions().add(cb.equal(tipoExclusion.get("texcNuOrden"), orden));
    conjuncion.getExpressions().add(cb.equal(tipoExclusion.get("texcNuTipo"), numTipo));
    conjuncion.getExpressions().add(cb.equal(tipoExclusion.get("texcNuAnio"), anio));

    cq.where(conjuncion);
    cq.orderBy(cb.asc(root.get("exclNuOrden")));

    return getEntityManager().createQuery(cq).getResultList();
  }

  @Override
  public List<Exclusion> obtenerExclusionesByIds(final List<Long> ids) {
    final CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
    final CriteriaQuery<Exclusion> cq = cb.createQuery(Exclusion.class);
    final Root<Exclusion> root = cq.from(Exclusion.class);

    cq.select(root).where(root.get("exclNuId").in(ids));
    cq.orderBy(cb.asc(root.get("exclNuOrden")));

    return getEntityManager().createQuery(cq).getResultList();
  }

  @Override
  @Transactional
  public Exclusion findById(final Long id) throws ArchitectureException {
    return getEntityManager().find(Exclusion.class, id);
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Integer> obtenerExclusionesSolicitudByTipoExclusion(final String numExpediente, final Integer tipo) throws TramitacionException {
    List<Integer> resultado = new ArrayList<>();
    getEntityManager().clear();
    try {
      final StringBuilder queryStr = new StringBuilder("select excl.exclNuOrden from Solicitud soli" //
          + " inner join ExclusionesSolicitud excl_sol on soli.idSolicitud=excl_sol.aaciTSolicitudsubongdBySoliNuId" //
          + " inner join Exclusion excl on excl.exclNuId=excl_sol.aaciExclusionByExclNuId" //
          + " inner join TipoExclusion texcl on excl.aaciTipoExclusionByTexcNuId=texcl.texcNuId" //
          + " where soli.nuNumexpediente = :numExpediente");
      if (tipo != null) {
        queryStr.append(" and texcl.texcNuTipo = :tipo");
      }
      final Query query = getEntityManager().createQuery(queryStr.toString()).setParameter("numExpediente", UtilidadesNumero.parseStringToLong(numExpediente));
      if (tipo != null) {
        query.setParameter("tipo", tipo);
      }
      resultado = query.getResultList();
    } catch (final Exception e) {
      throw new TramitacionException("Se ha producido un error obtener las exclusiones de tipo " + tipo + " para el expediente " + numExpediente, e);
    }
    return resultado;
  }

  @Override
  public Object tieneExclusionesTipoExclusion(final Long idSolicitud, final Integer tipo) throws TramitacionException {
    try {
      @SuppressWarnings("unchecked")
      final Object tieneExclusiones = getEntityManager().createNativeQuery("SELECT DISTINCT ats.* FROM AACI_TEXCLUSIONES_SOLICITUD ats "//
          + "INNER JOIN AACI_T_SOLICITUDSUBONGD ats2 ON ats.ID_SOLICITUD =ats2.ID_SOLICITUD "//
          + "INNER JOIN AACI_TIPO_EXCLUSION ate ON ate.TEXC_NU_TIPO =ats.TEXC_NU_TIPO "//
          + "WHERE ats2.ID_SOLICITUD = :idSolicitud "//
          + "AND ate.TEXC_NU_TIPO = :tipo")//
          .setParameter("idSolicitud", idSolicitud)//
          .setParameter("tipo", tipo).getResultList().stream().findFirst().orElse(null);
      return tieneExclusiones;
    } catch (final Exception e) {
      throw new TramitacionException(e.getMessage(), e);
    }
  }

  @Override
  public int guardarComprobacionExclusionPorTipo(final TExclusionesSolicitud tieneExclusiones) throws TramitacionException {
    getEntityManager().setFlushMode(FlushModeType.AUTO);
    int resultado = 0;
    try {
      if (tieneExclusiones.getTesoNuId() == null) {
        resultado = getEntityManager().createNativeQuery("INSERT INTO AACI_TEXCLUSIONES_SOLICITUD "//
            + " (teso_nu_id, texc_Nu_Tipo, ID_SOLICITUD, TESO_LG_EXCLUSIONES) "//
            + " VALUES (AACI_SEQ_TEXCLU_SOLICITUD.nextval,?,?,?)")//
            .setParameter(1, tieneExclusiones.getTexcNuTipo())//
            .setParameter(2, tieneExclusiones.getSolicitud().getIdSolicitud())//
            .setParameter(3, tieneExclusiones.getTesoLgExclusiones()).executeUpdate();
      } else {
        resultado = getEntityManager().createNativeQuery("UPDATE AACI_TEXCLUSIONES_SOLICITUD SET TESO_LG_EXCLUSIONES = ? WHERE teso_nu_id=?")//
            .setParameter(1, tieneExclusiones.getTesoLgExclusiones())//
            .setParameter(2, tieneExclusiones.getTesoNuId()).executeUpdate();

      }
      getEntityManager().clear();
    } catch (final Exception e) {
      throw new TramitacionException(
          "Se ha producido un error al guardar la comprobación de exclusiones para la solicitud " + tieneExclusiones.getSolicitud().getIdSolicitud(), e);
    }
    return resultado;

  }
}
