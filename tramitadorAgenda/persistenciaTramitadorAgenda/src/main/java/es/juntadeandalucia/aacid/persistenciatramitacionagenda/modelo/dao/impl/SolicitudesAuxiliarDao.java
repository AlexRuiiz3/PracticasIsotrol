package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudAuxiliarDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.ISolicitudesAuxiliarDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.SolicitudesAuxiliar;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.persistence.CustomHibernateDaoSupport;

public class SolicitudesAuxiliarDao extends CustomHibernateDaoSupport implements ISolicitudesAuxiliarDao {

  @Override
  public SolicitudAuxiliarDTO findByIdSolicitud(final Long idSoli) throws TramitacionException {
    try {
      return getEntityManager()
          .createQuery("SELECT NEW es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudAuxiliarDTO "
              + "(solAux.soauNuId, solAux.soauTxObservacionesVal, solAux.soauNuNumReintegros, solAux.soauNuCausaReintegro, solAux.soauNuMagnitudReintegro, solAux.soauNuPuntuacionTotal) "
              + "FROM SolicitudesAuxiliar solAux WHERE solAux.aaciTSolicitudsubongdByIdSolicitud.idSolicitud =:idSoli", SolicitudAuxiliarDTO.class)
          .setParameter("idSoli", idSoli).getSingleResult();
    } catch (final NoResultException nre) {
      return null;
    } catch (final Exception e) {
      throw new TramitacionException(e.getMessage(), e);
    }
  }

  @Override
  public int saveOrUpdateSolicitudesAuxiliar(final SolicitudesAuxiliar solicitudesAuxiliar) throws TramitacionException {
    try {
      Query query;
      if (solicitudesAuxiliar.getSoauNuId() != null) {
        query = getEntityManager().createNativeQuery("UPDATE AACI_SOLICITUDES_AUXILIAR SET SOAU_TX_OBSERVACIONES_VAL = ?, SOAU_NU_PUNTUACION_TOTAL = ? where SOAU_NU_ID=?")
            .setParameter(1, solicitudesAuxiliar.getSoauTxObservacionesVal()).setParameter(2, solicitudesAuxiliar.getSoauNuPuntuacionTotal()).setParameter(3, solicitudesAuxiliar.getSoauNuId());
      } else {
        query = getEntityManager().createNativeQuery(
            "INSERT INTO AACI_SOLICITUDES_AUXILIAR (SOAU_NU_ID, SOAU_TX_OBSERVACIONES_VAL, SOAU_NU_PUNTUACION_TOTAL, ID_SOLICITUD) VALUES (AACI_SEQ_SOLICITUDES_AUXILIAR.nextval,?,?,?)")
            .setParameter(1, solicitudesAuxiliar.getSoauTxObservacionesVal())
            .setParameter(2, solicitudesAuxiliar.getSoauNuPuntuacionTotal())
            .setParameter(3, solicitudesAuxiliar.getAaciTSolicitudsubongdByIdSolicitud().getIdSolicitud());
      }
      return query.executeUpdate();
    } catch (final Exception e) {
      throw new TramitacionException("Se ha producido un error al guardar o actualizar la subsanación.", e);
    }
  }
}
