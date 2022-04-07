package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.IContribucionDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Contribuciones;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.persistence.CustomHibernateDaoSupport;

public class ContribucionDao extends CustomHibernateDaoSupport implements IContribucionDao {

  @Override
  @Transactional
  public Contribuciones saveOrUpdateContribuciones(final Contribuciones contribucion) throws TramitacionException {
    try {
      if (contribucion.getIdContribucion() != null) {
        return getEntityManager().merge(contribucion);
      } else {
        getEntityManager().persist(contribucion);
        return contribucion;
      }
    } catch (final Exception e) {
      throw new TramitacionException(e.getMessage(), e);
    }
  }

  @Override
  public List<Contribuciones> obtenerContribucionesBySolicitud(final Long idSolicitud) throws TramitacionException {
    try {
      final List<Contribuciones> contribuciones = getEntityManager().createQuery("select cont from Contribuciones cont " //
          + "where cont.fkProyectos= :idSolicitud", Contribuciones.class)//
          .setParameter("idSolicitud", idSolicitud).getResultList();

      return contribuciones;

    } catch (final NoResultException nre) {
      return new ArrayList<>();
    } catch (final Exception e) {
      throw new TramitacionException(e.getMessage(), e);
    }

  }
}
