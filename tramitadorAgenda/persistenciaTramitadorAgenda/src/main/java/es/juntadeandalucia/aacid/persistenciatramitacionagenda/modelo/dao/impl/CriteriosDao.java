package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesTramitacion;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.ICriteriosDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Criterios;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.persistence.CustomHibernateDaoSupport;

@Repository
public class CriteriosDao extends CustomHibernateDaoSupport implements ICriteriosDao {

  @Override
  public List<Criterios> obtenerListaCriteriosByFinalidadAndConvocatoria(final String codFinalidad, final String nombreTCriterio, final boolean isUniv,
      final Long anyo, final boolean comprobarSubFinalidad) throws TramitacionException {
    String tipoConvocatoria = "";
    if (isUniv) {
      tipoConvocatoria = ConstantesTramitacion.TIPO_CONV_UNIV;
    } else {
      tipoConvocatoria = ConstantesTramitacion.TIPO_CONV_ONGD;
    }

    final StringBuilder query = new StringBuilder("SELECT  new Criterios (c.critNuId, c.critTxDescripcion, c.critNuValMax) ")//
        .append("FROM Criterios c ").append("LEFT JOIN c.aaciTCriteriosByTcriNuId tc ")//
        .append("LEFT JOIN tc.aaciFinalidad f ")//
        .append("LEFT JOIN tc.aaciTTipoConvByTconNuId tconv ")//
        .append("WHERE tc.tcriTxNombre=:nombreTCriterio ")//
        .append("AND f.finaCoCodigo = :codFinalidad ")//
        .append("AND tconv.tconvLiDenominacion= :tipoConvocatoria ")//
        .append("AND c.critNuAnio = :anyo ");
    if (comprobarSubFinalidad) {
      query.append(" AND c.critSubFinalidad = 'F'");
    } else {
      query.append(" AND c.critSubFinalidad is null");
    }
    query.append(" ORDER BY c.critNuOrden");

    try {
      return getEntityManager().createQuery(query.toString(), Criterios.class).setParameter("nombreTCriterio", nombreTCriterio)
          .setParameter("codFinalidad", codFinalidad).setParameter("tipoConvocatoria", tipoConvocatoria).setParameter("anyo", anyo).getResultList();
    } catch (final Exception e) {
      throw new TramitacionException(e.getMessage(), e);
    }
  }
}
