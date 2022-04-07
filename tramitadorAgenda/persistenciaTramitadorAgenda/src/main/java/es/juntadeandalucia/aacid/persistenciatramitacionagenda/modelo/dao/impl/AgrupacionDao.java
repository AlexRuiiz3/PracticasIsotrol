package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.IAgrupacionDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Agrupaciones;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.persistence.CustomHibernateDaoSupport;

public class AgrupacionDao extends CustomHibernateDaoSupport implements IAgrupacionDao {

  @Override
  public List<Object> getAgrupacionesBySolicitud(final Long idSolicitud) throws TramitacionException {
    try {
      @SuppressWarnings("unchecked")
      final List<Object> agrupaciones = getEntityManager().createNativeQuery("SELECT AGRU_LI_DENOMINACION, AGRU_CO_INSCRIPCION" //
          + " FROM AACI_AGRUPACIONES " //
          + "WHERE ID_SOLICITUD= ?")//
          .setParameter(1, idSolicitud).getResultList();

      return agrupaciones;
    } catch (final NoResultException nre) {
      return new ArrayList<>();
    } catch (final Exception e) {
      throw new TramitacionException("Error obteniendo las agrupaciones para la solicitud: " + idSolicitud);
    }
  }

  @Override
  @Transactional
  public int saveOrUpdateAgrupacion(final Agrupaciones agrupacion, final Long idSolicitud) throws TramitacionException {

    try {
      getEntityManager().setFlushMode(FlushModeType.AUTO);
      int resultado;
      resultado = getEntityManager()
          .createNativeQuery(
              "INSERT INTO AACI_AGRUPACIONES(AGRU_NU_ID,AGRU_LI_DENOMINACION, ID_SOLICITUD,AGRU_CO_INSCRIPCION) VALUES (AACI_SEQ_AGRUPACIONES.nextval,?,?,?)")
          .setParameter(1, agrupacion.getAgrDenominacion()).setParameter(2, idSolicitud).setParameter(3, agrupacion.getAgrInscripcion()).executeUpdate();
      getEntityManager().clear();
      return resultado;
    } catch (final Exception e) {
      throw new TramitacionException("Se ha producido un error al guardar o actualizar la agrupacion", e);
    }
  }

  @Override
  public int existeAgrupaciones(final Long id) {
    return ((BigDecimal) getEntityManager().createNativeQuery("select count(*) FROM AACI_AGRUPACIONES WHERE ID_SOLICITUD =:idSolicitud")
        .setParameter("idSolicitud", id).getSingleResult()).intValue();
  }
}
