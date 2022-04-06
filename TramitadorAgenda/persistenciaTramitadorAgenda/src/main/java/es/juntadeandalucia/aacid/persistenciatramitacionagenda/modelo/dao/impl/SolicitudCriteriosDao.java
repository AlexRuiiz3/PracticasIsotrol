package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.impl;

import java.math.BigDecimal;

import javax.persistence.FlushModeType;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudCriterioDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.ISolicitudCriteriosDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.persistence.CustomHibernateDaoSupport;

@Repository
public class SolicitudCriteriosDao extends CustomHibernateDaoSupport implements ISolicitudCriteriosDao {

  @Override
  public SolicitudCriterioDTO obtenerValoracionCriterios(final Long nExp, final Long idCriterio) {
    try {
      final StringBuilder query = new StringBuilder("SELECT NEW es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudCriterioDTO ")
          .append("(solCrit.socrNuId, solCrit.socrNuValor, solCrit.socrNuPuntuacion) ")
          .append("FROM SolicitudCriterio solCrit WHERE solCrit.aaciTSolicitudsubongdByIdSolicitud.nuNumexpediente =: nExp "
              + " and solCrit.aaciCriteriosByCritNuId.critNuId =: idCriterio");

      return getEntityManager().createQuery(query.toString(), SolicitudCriterioDTO.class).setParameter("nExp", nExp).setParameter("idCriterio", idCriterio)
          .getSingleResult();

    } catch (final Exception nre) {
      return null;
    }
  }
  
  @Override
  public SolicitudCriterioDTO obtenerValoracionCriteriosValorMaximo(final Long nExp, final Long idCriterio) {
    try {
      final StringBuilder query = new StringBuilder("SELECT NEW es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudCriterioDTO ")
          .append("(solCrit.socrNuId, solCrit.socrNuValor, solCrit.socrNuPuntuacion,solCrit.aaciCriteriosByCritNuId.critNuValMax) ")
          .append("FROM SolicitudCriterio solCrit WHERE solCrit.aaciTSolicitudsubongdByIdSolicitud.nuNumexpediente =: nExp "
              + " and solCrit.aaciCriteriosByCritNuId.critNuId =: idCriterio");

      return getEntityManager().createQuery(query.toString(), SolicitudCriterioDTO.class).setParameter("nExp", nExp).setParameter("idCriterio", idCriterio)
          .getSingleResult();

    } catch (final Exception nre) {
      return null;
    }
  }


  @Override
  public SolicitudCriterioDTO obtenerValoracionCriteriosByIdSolIdCrit(final long idSolicitud, final Long idCriterio) {
    final StringBuilder query = new StringBuilder("SELECT NEW es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudCriterioDTO ")
        .append("(solCrit.socrNuId, solCrit.socrNuValor, solCrit.socrNuPuntuacion) ")
        .append("FROM SolicitudCriterio solCrit WHERE solCrit.aaciTSolicitudsubongdByIdSolicitud.idSolicitud =: idSolicitud "
            + " and solCrit.aaciCriteriosByCritNuId.critNuId =: idCriterio");

    return getEntityManager().createQuery(query.toString(), SolicitudCriterioDTO.class).setParameter("idSolicitud", idSolicitud)
        .setParameter("idCriterio", idCriterio).getResultList().stream().findFirst().orElse(null);
  }

  @Override
  public int guardarValoracionCriterio(final SolicitudCriterioDTO solCrit) throws TramitacionException {
    getEntityManager().setFlushMode(FlushModeType.AUTO);
    int resultado = 0;
    Query query;
    try {
      if (solCrit.getId() != null) {
        query = getEntityManager().createNativeQuery("UPDATE AACI_SOLICITUD_CRITERIO SET SOCR_NU_VALOR = ?, SOCR_NU_PUNTUACION=? where SOCR_NU_ID=?")
            .setParameter(1, solCrit.getValor()).setParameter(2, solCrit.getPuntuacion()).setParameter(3, solCrit.getId());
      } else {
        query = getEntityManager().createNativeQuery(
            "INSERT INTO AACI_SOLICITUD_CRITERIO (SOCR_NU_ID,SOCR_NU_VALOR, SOCR_NU_PUNTUACION, ID_SOLICITUD, CRIT_NU_ID) VALUES (AACI_SEQ_SOLICITUD_CRITERIO.nextval,?,?,?,?)")
            .setParameter(1, solCrit.getValor()).setParameter(2, solCrit.getPuntuacion()).setParameter(3, solCrit.getIdSolicitud())
            .setParameter(4, solCrit.getIdCriterio());
      }
      resultado = query.executeUpdate();

      getEntityManager().clear();

    } catch (final Exception e) {
      throw new TramitacionException("Se ha producido un error al guardar la valoración de la solicitud" + solCrit.getIdSolicitud(), e);
    }
    return resultado;
  }
  
  @Override
  public BigDecimal obtenerPuntuacionTotalSolicitud(Long idSolicitud) {
    Query query = getEntityManager().createNativeQuery("SELECT sum(SOTC_NU_PUNTUACION_TOTAL) FROM AACI_SOLICITUD_TCRITERIO asc2 WHERE ID_SOLICITUD =?")
        .setParameter(1,idSolicitud);
    Object resultado=query.getSingleResult();
    if(resultado instanceof BigDecimal) {
        return (BigDecimal)resultado;
    }
    return BigDecimal.valueOf(0);
  }
}
