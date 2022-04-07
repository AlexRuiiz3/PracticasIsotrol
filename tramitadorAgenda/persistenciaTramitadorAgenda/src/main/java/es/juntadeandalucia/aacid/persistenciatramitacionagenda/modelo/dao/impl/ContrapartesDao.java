package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.IContrapartesDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Contrapartes;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.persistence.CustomHibernateDaoSupport;

public class ContrapartesDao extends CustomHibernateDaoSupport implements IContrapartesDao {

  @Override
  public List<Object> obtenerContrapartesBySolicitud(final Long idSolicitud) throws TramitacionException {
    try {
      @SuppressWarnings("unchecked")
      final List<Object> contrapartes = getEntityManager().createNativeQuery( //
          "SELECT CONT_LI_CONTRAPARTE, CONT_LI_LOCALIDAD,CONT_LI_PAIS" //
              + " FROM AACI_CONTRAPARTES" //
              + " WHERE CONT_FK_SOLICITUD= ?")
          .setParameter(1, idSolicitud).getResultList();

      return contrapartes;
    } catch (final NoResultException nre) {
      return new ArrayList<>();
    } catch (final Exception e) {
      throw new TramitacionException("Error obteniendo las contrapartes para la solicitud: " + idSolicitud + ". Causa: " + e.getMessage());
    }
  }

  @Override
  @Transactional
  public int saveOrUpdateContraparte(final Contrapartes contrapartes, final Long idSolicitud) throws TramitacionException {
    try {
      getEntityManager().setFlushMode(FlushModeType.AUTO);
      int resultado;
      resultado = getEntityManager().createNativeQuery(
          "INSERT INTO AACI_CONTRAPARTES(CONT_NU_ID,CONT_LI_CONTRAPARTE, CONT_FK_SOLICITUD,CONT_LI_LOCALIDAD,CONT_LI_PAIS) VALUES (AACI_SEQ_CONTRAPARTES.nextval,?,?,?,?)")
          .setParameter(1, contrapartes.getContContraparte()).setParameter(2, idSolicitud).setParameter(3, contrapartes.getContLocalidad())
          .setParameter(4, contrapartes.getContPais()).executeUpdate();
      getEntityManager().clear();
      return resultado;
    } catch (final Exception e) {
      throw new TramitacionException("Se ha producido un error al guardar o actualizar la contraparte. Causa: " + e.getMessage(), e);
    }
  }

}
