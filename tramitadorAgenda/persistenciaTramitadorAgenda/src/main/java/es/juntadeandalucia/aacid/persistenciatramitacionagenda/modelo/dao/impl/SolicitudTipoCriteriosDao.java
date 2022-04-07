package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;

import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudTipoCriterioDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.ISolicitudTipoCriteriosDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.SolicitudTipoCriterio;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.persistence.CustomHibernateDaoSupport;

public class SolicitudTipoCriteriosDao extends CustomHibernateDaoSupport implements ISolicitudTipoCriteriosDao {

  @Override
  public SolicitudTipoCriterioDTO obtenerSolicitudTipoCriterioDTOByIdSolicitud(final Long idSolicitud, final String nombreTipoCriterio)
      throws TramitacionException {
    try {
      final StringBuilder query = new StringBuilder("SELECT NEW es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudTipoCriterioDTO ")
          .append("(solTCrit.sotcNuId, solTCrit.sotcTxObservaciones, solTCrit.sotcNuPuntuacionTotal) ")
          .append("FROM SolicitudTipoCriterio solTCrit WHERE solTCrit.aaciTSolicitudsubongdByIdSolicitud.idSolicitud =:idSoli");

      if (StringUtils.isNotBlank(nombreTipoCriterio)) {
        query.append(" and solTCrit.aaciTCriteriosByTcriNuId.tcriTxNombre =: nombreTipoCriterio");
        return getEntityManager().createQuery(query.toString(), SolicitudTipoCriterioDTO.class).setParameter("idSoli", idSolicitud)
            .setParameter("nombreTipoCriterio", nombreTipoCriterio).getSingleResult();
      } else {
        return getEntityManager().createQuery(query.toString(), SolicitudTipoCriterioDTO.class).setParameter("idSoli", idSolicitud).getSingleResult();
      }

    } catch (final NoResultException nre) {
      return null;
    } catch (final Exception e) {
      throw new TramitacionException(e.getMessage(), e);
    }
  }

  @Override
  public int guardarValoracionTipoCriterio(final SolicitudTipoCriterio solicitudTipoCriterio) throws TramitacionException {
    try {
      Query query;
      if (solicitudTipoCriterio.getSotcNuId() != null) {
        query = getEntityManager()
            .createNativeQuery("UPDATE AACI_SOLICITUD_TCRITERIO SET SOTC_TX_OBSERVACIONES = ? , SOTC_NU_PUNTUACION_TOTAL = ? where SOTC_NU_ID =?")
            .setParameter(1, solicitudTipoCriterio.getSotcTxObservaciones()).setParameter(2, solicitudTipoCriterio.getSotcNuPuntuacionTotal())
            .setParameter(3, solicitudTipoCriterio.getSotcNuId());
      } else {
        query = getEntityManager().createNativeQuery(
            "INSERT INTO AACI_SOLICITUD_TCRITERIO (SOTC_NU_ID, SOTC_TX_OBSERVACIONES, SOTC_NU_PUNTUACION_TOTAL, ID_SOLICITUD, TCRI_NU_ID) VALUES (AACI_SEQ_SOLI_TCRITERIO.nextval,?,?,?,?)")
            .setParameter(1, solicitudTipoCriterio.getSotcTxObservaciones()).setParameter(2, solicitudTipoCriterio.getSotcNuPuntuacionTotal())
            .setParameter(3, solicitudTipoCriterio.getAaciTSolicitudsubongdByIdSolicitud().getIdSolicitud())
            .setParameter(4, solicitudTipoCriterio.getAaciTCriteriosByTcriNuId().getTcriNuId());
      }
      return query.executeUpdate();

    } catch (final Exception e) {
      throw new TramitacionException("Se ha producido un error al guardar la valoraci\u00F3n del tipo del criterio", e);
    }
  }

}
